package view;

import controller.RepositoryController;
import controller.UserController;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Repository;
import model.User;

/**
 * UserView.java.
 * Memanfaatkan berbagai controller dan data model,
 * Menyediakan user interface untuk melihat profil user github.
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

@SuppressWarnings("serial")
public class UserView extends JFrame {
  /** 
   * Atribut user yang ditampilkan oleh view.
   */
  private User user;
  
  /**
    * Konstruktor dengan parameter.
    * @param s nama username yang akan dibuat viewnya.
    */
  public UserView(String s) {
    UserController uc = new UserController(s);
    user = uc.getUserInfo();
    String username = user.getUsername();
    
    setTitle(username);
    setSize(500,500);
    setLayout(new GridBagLayout());
    setLocationByPlatform(true);
     
    Insets insets1 = new Insets(0,10,10,15);
    add(new JLabel("Username "),new GridBagConstraints(
        0,0,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
    add(new JLabel(username),new GridBagConstraints(
        1,0,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
    add(new JLabel("E-mail "),new GridBagConstraints(
        0,1,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
    String email = user.getEmail();
    add(new JLabel(email),new GridBagConstraints(
        1,1,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
    add(new JLabel("Nama Pengguna "),new GridBagConstraints(
        0,2,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
    String namaPengguna = user.getNamaPengguna();
    add(new JLabel(namaPengguna),new GridBagConstraints(
        1,2,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
    add(new JLabel("Jumlah repository "),new GridBagConstraints(
        0,3,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
    add(new JLabel(user.getNRepo().toString()),new GridBagConstraints(
        1,3,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
    add(new JLabel("Jumlah follower "),new GridBagConstraints(
        0,4,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
    add(new JLabel(user.getNFollower().toString()),new GridBagConstraints(
        1,4,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
    add(new RepositoryPanel(),new GridBagConstraints(
        0,5,GridBagConstraints.REMAINDER,1,0,0,GridBagConstraints.WEST,
        GridBagConstraints.NONE,insets1,450,250));
    
    JButton close = new JButton("Close");
    close.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    
    add(close,new GridBagConstraints(
        0,6,GridBagConstraints.REMAINDER,1,0,0,GridBagConstraints.CENTER,
        GridBagConstraints.NONE,insets1,30,0));
  }
  
  class RepositoryPanel extends JPanel {
    /**
     * Atribut bertipe array of repository.
     */
    private Repository[] listItems;
    /**
     * Atribut bertipe jlist of string.
     */
    private JList<String> repoList;
     
    /**
     * Konstruktor.
     */
    public RepositoryPanel() {
      setLayout(new BorderLayout());
      RepositoryController rc = new RepositoryController(user);
      listItems = rc.getRepos();
      DefaultListModel<String> model = new DefaultListModel<String>();
      for (int i = 0; i < listItems.length;i++) {
        model.addElement(i + 1 + ": " + listItems[i].getNama());
        model.addElement(listItems[i].getDeskripsi());
        model.addElement(listItems[i].getUrl());
        model.addElement(" ");
      }
      repoList = new JList<String>(model);
      repoList.setLayoutOrientation(JList.VERTICAL);
      JScrollPane p = new JScrollPane(repoList);
      add(p);
    }
  }
}