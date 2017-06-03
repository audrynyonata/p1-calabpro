package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * MainView.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

@SuppressWarnings("serial")
public class MainView extends JFrame {
  public JTextField searchField;
  public FilterPanel filterPanel;
  public JButton searchButton;
  public ResultPanel resultPanel;
  
  public MainView() {
    setTitle("Search by Audry Nyonata");
    setSize(600,400);
    setLayout(new GridBagLayout());
    
    GridBagConstraints constraint = new GridBagConstraints(); 
    constraint.insets = new Insets(0,0,20,0);
    constraint.gridx = 0;
    constraint.gridy = 0;
    
    JLabel label1 = new JLabel("Search by Audry Nyonata");
    constraint.gridwidth = GridBagConstraints.REMAINDER;
    add(label1,constraint);
    
    JLabel label2 = new JLabel("Enter keyword : ");
    constraint.gridwidth = 1;
    constraint.gridy++;   
    add(label2,constraint);

    searchField = new JTextField(20);
    constraint.gridwidth = 1;
    constraint.gridx++;
    add(searchField,constraint);
    constraint.gridx--;

    constraint.insets = new Insets(0,0,10,0);
    filterPanel = new FilterPanel();
    constraint.gridwidth = GridBagConstraints.REMAINDER;
    constraint.gridy++;   
    add(filterPanel,constraint);
    
    searchButton = new JButton("Search");
    constraint.gridwidth = GridBagConstraints.REMAINDER;
    constraint.gridy++;
    add(searchButton,constraint);
    
    resultPanel = new ResultPanel();
    constraint.fill = GridBagConstraints.HORIZONTAL;
    constraint.gridwidth = GridBagConstraints.REMAINDER;
    constraint.gridy++;   
	  add(resultPanel,constraint);
	}
  
  class FilterPanel extends JPanel {
    public ButtonGroup group;
    
    public FilterPanel() {
      setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
      
      JLabel label1 = new JLabel("Search by : ");
      add(label1);
      JRadioButton usernameButton = new JRadioButton("Username");
      add(usernameButton);
      usernameButton.setSelected(true);
      JRadioButton emailButton = new JRadioButton("E-mail");
      add(emailButton);
      JRadioButton namaPenggunaButton = new JRadioButton("Nama Pengguna");
      add(namaPenggunaButton);
           
      group = new ButtonGroup();
      group.add(usernameButton);
      group.add(emailButton);
      group.add(namaPenggunaButton);      
    }
  }
   
  class ResultPanel extends JPanel {
    public JList<String> userList;
    public String[] listItems;
    
    public ResultPanel() {
      setLayout(new BorderLayout());
      listItems = new String[100];
      listItems[0] = "audrynyonata";
      listItems[1] = "audrynyonata";
      listItems[2] = "audrynyonata";
      listItems[3] = "audrynyonata";
      userList = new JList<String>(listItems);
      userList.setLayoutOrientation(JList.VERTICAL);
      add(new JScrollPane(userList));
    }
  }
}