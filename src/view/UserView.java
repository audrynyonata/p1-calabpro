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
   private String namaPengguna;
   private Integer nRepo;
   private Integer nFollower;
   
   public UserView(String s) {
     username = s;
     email = "aaa@email.com";
     namaPengguna = "AA A";
     nRepo = 0;
     nFollower = 100;

     setTitle(username);
     setSize(500,500);
     setLayout(new GridBagLayout());
     setLocationByPlatform(true);
     
     Insets insets1 = new Insets(0,10,10,15);
     add(new JLabel("Username "),new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
     add(new JLabel(username),new GridBagConstraints(1,0,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
     add(new JLabel("E-mail "),new GridBagConstraints(0,1,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
     add(new JLabel(email),new GridBagConstraints(1,1,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
     add(new JLabel("Nama Pengguna "),new GridBagConstraints(0,2,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
     add(new JLabel(namaPengguna),new GridBagConstraints(1,2,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
     add(new JLabel("Jumlah repository "),new GridBagConstraints(0,3,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
     add(new JLabel(nRepo.toString()),new GridBagConstraints(1,3,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
     add(new JLabel("Jumlah follower "),new GridBagConstraints(0,4,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
     add(new JLabel(nFollower.toString()),new GridBagConstraints(1,4,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,0,0));
     add(new RepositoryPanel(),new GridBagConstraints(0,5,GridBagConstraints.REMAINDER,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets1,450,250));
     
     JButton close = new JButton("Close");
     close.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
         dispose();
       }
     });
     
     add(close,new GridBagConstraints(0,6,GridBagConstraints.REMAINDER,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,insets1,0,0));
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