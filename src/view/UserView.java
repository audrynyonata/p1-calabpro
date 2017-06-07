package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * UserView.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

@SuppressWarnings("serial")
public class UserView extends JFrame {
   private String username;
   private String email;
   private String namapengguna;
   private Integer nRepo;
   private Integer nFollower;
   private RepositoryPanel repositoryPanel;
   
   public UserView(String s) {
     username = s;
     email = "aaa@email.com";
     namapengguna = "AA A";
     nRepo = 0;
     nFollower = 100;
     setTitle(username);
     setSize(500,500);
     setLayout(new GridBagLayout());
     
     GridBagConstraints constraint = new GridBagConstraints(); 
     constraint.fill = GridBagConstraints.NONE;
     constraint.insets = new Insets(0,10,10,15);
     constraint.anchor = GridBagConstraints.WEST;
     constraint.gridx = 0;
     constraint.gridy = 0;      
     constraint.gridwidth = 1;
     add(new JLabel("Username "),constraint);
     JLabel label1 = new JLabel(username);
     constraint.gridwidth = 1;
     constraint.gridx++;
     add(label1,constraint);

     constraint.gridwidth = 1;
     constraint.gridx--;
     constraint.gridy++;
     add(new JLabel("E-mail "),constraint);
     JLabel label2 = new JLabel(email);
     constraint.gridwidth = 1;
     constraint.gridx++;
     add(label2,constraint);
     
     constraint.gridwidth = 1;
     constraint.gridx--;
     constraint.gridy++;
     add(new JLabel("Nama Pengguna "),constraint);
     JLabel label3 = new JLabel(namapengguna);
     constraint.gridwidth = 1;
     constraint.gridx++;
     add(label3,constraint);
     
     
     constraint.gridwidth = 1;
     constraint.gridx--;
     constraint.gridy++;
     add(new JLabel("Jumlah repository "),constraint);
     JLabel label4 = new JLabel(nRepo.toString());
     constraint.gridwidth = 1;
     constraint.gridx++;
     add(label4,constraint);
     
     constraint.gridwidth = 1;
     constraint.gridx--;
     constraint.gridy++;
     add(new JLabel("Jumlah follower "),constraint);
     JLabel label5 = new JLabel(nFollower.toString());
     constraint.gridwidth = 1;
     constraint.gridx++;
     add(label5,constraint);
     
     repositoryPanel = new RepositoryPanel();
     constraint.gridwidth = GridBagConstraints.REMAINDER;
     constraint.gridx--;
     constraint.gridy++;
     constraint.ipadx = 450;
     constraint.ipady = 250;
     add(repositoryPanel,constraint);
     
     JButton close = new JButton("Close");
     close.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
         setVisible(false);
       }
     });
     
     constraint.anchor = GridBagConstraints.CENTER;
     constraint.ipadx = 0;
     constraint.ipady = 0;
     constraint.gridy++;
     add(close,constraint);
   }
   
   class RepositoryPanel extends JPanel {
     private int last = 0;
     private String[] listItems = new String[100]; //to-do: string diganti repository
     private JList<String> repoList; //to-do: string diganti repository
     
     public RepositoryPanel(){
       setLayout(new BorderLayout());
       repoList = new JList<String>(listItems);
       repoList.setLayoutOrientation(JList.VERTICAL);
       //print repo name
       //print repo description
       //print repo url
     
       JScrollPane p = new JScrollPane(repoList);
       add(p);
     }
     
     public void add(String s){
       listItems[last] = s;
       last++;
       repoList.setListData(listItems);
     }
   }
}