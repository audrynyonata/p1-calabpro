package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

/**
 * MainView.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

@SuppressWarnings("serial")
public class MainView extends JFrame {
  class SearchByPanel extends JPanel {
    private ButtonGroup group = new ButtonGroup();
    private String[] value = {"Username", "E-mail", "Nama Pengguna"}; 
    
    public SearchByPanel() {
      setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
      add(new JLabel("Search by : "));
      for(int i = 0; i<value.length; i++) {
        JRadioButton button = new JRadioButton();
        button.setText(value[i]);
        button.setActionCommand(value[i]);
        if (i==0){
          button.setSelected(true);
        }
        add(button);
        group.add(button);
      }
    }
    
    public ButtonGroup getGroup(){
      return group;
    }
  }
   
  class FilterPanel extends JPanel {
    private JFormattedTextField repoField;
    private JFormattedTextField followerField;
    
    public FilterPanel() {
      setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
      NumberFormat f = NumberFormat.getNumberInstance();
      f.setGroupingUsed(false);
      NumberFormatter nf = new NumberFormatter(f) {
        public Object stringToValue(String s) throws ParseException{
          if (s.equals("")){
            return null;
          } else {
            return super.stringToValue(s);
          }
        }
      };
      nf.setValueClass(Integer.class);
      nf.setAllowsInvalid(false);
      nf.setMinimum(0);
      add(new JLabel("Filter : "));
      add(new JLabel("Jumlah repository : "));
      repoField = new JFormattedTextField(nf);
      add(repoField);
      add(new JLabel("Jumlah follower   : "));
      followerField = new JFormattedTextField(nf);
      add(followerField);
    }
    
    public int getNRepo(){
      if (repoField.getText().equals("")){
        return -1;
      } else {
        return (Integer.parseInt(repoField.getText()));
      }
    }

    public int getNFollower(){
      if (followerField.getText().equals("")){
        return -1;
      } else {
        return (Integer.parseInt(followerField.getText()));
      }
    }
  }
  
  class ResultPanel extends JPanel {
    private JList<String> userList;
    private String[] listItems = new String[100];
    private int last = 0;
    public ResultPanel(){
      setLayout(new BorderLayout());
      userList = new JList<String>(listItems);
      userList.setLayoutOrientation(JList.VERTICAL);
      JScrollPane p = new JScrollPane(userList);
      p.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
        public void adjustmentValueChanged(AdjustmentEvent e) {
          userList.ensureIndexIsVisible(resultPanel.getLast());
        }
      });
      add(p);
    }
    
    public JList<String> getList(){
      return userList;
    }
    
    public String[] getArray(){
      return listItems;
    }
    
    public int getLast(){
      return last;
    }
    
    public void clear(){
      for(int i = 0; i<listItems.length; i++){
        listItems[i]="";
      }
      last = 0;
      userList.setListData(listItems);
    }
    
    public void add(String s){
      listItems[last] = s;
      last++;
      userList.setListData(listItems);
    }
  }
  
  public JTextField searchField;
  public SearchByPanel searchByPanel;
  public FilterPanel filterPanel;
  public JButton searchButton;
  public ResultPanel resultPanel;
  
  public String searchKey;
  public Integer nRepo;
  public Integer nFollower;
  
  public MainView() {
    setTitle("Search by Audry Nyonata");
    setSize(600,600);
    setLayout(new GridBagLayout());
    
    GridBagConstraints constraint = new GridBagConstraints(); 
    constraint.insets = new Insets(0,0,30,0);
    constraint.gridx = 0;
    constraint.gridy = 0;
        
    constraint.gridwidth = GridBagConstraints.REMAINDER;
    JLabel label1 = new JLabel("Search by Audry Nyonata");
    label1.setFont(new Font(label1.getFont().getName(),Font.PLAIN,32));
    add(label1,constraint);
    
    constraint.fill = GridBagConstraints.HORIZONTAL;
    
    constraint.gridwidth = 1;
    constraint.gridy++;   
    add(new JLabel("Enter keyword : "),constraint);

    searchField = new JTextField(20);
    searchField.getDocument().addDocumentListener(new DocumentListener(){
      public void changedUpdate(DocumentEvent e){
        check();
      }
      public void insertUpdate(DocumentEvent e){
        check();
      }
      public void removeUpdate(DocumentEvent e){
        check();
      }
      public void check(){
        if (searchField.getText().equals("")){
          searchButton.setEnabled(false);
        } else {
          searchButton.setEnabled(true);
        }
      }
    });
    constraint.gridwidth = 1;
    constraint.gridx++;
    add(searchField,constraint);
    constraint.gridx--;

    constraint.insets = new Insets(0,0,20,20);
    searchByPanel = new SearchByPanel();
    constraint.gridwidth = 1;
    constraint.gridy++;   
    add(searchByPanel,constraint);
    
    constraint.insets = new Insets(0,0,20,0);
    filterPanel = new FilterPanel();
    constraint.gridwidth = 1;
    constraint.gridx++;    
    add(filterPanel,constraint);
    constraint.gridx--;
    
    constraint.fill = GridBagConstraints.NONE;
    
    searchButton = new JButton("Search");
    searchButton.setEnabled(false);
    constraint.gridwidth = GridBagConstraints.REMAINDER;
    constraint.gridy++;
    add(searchButton,constraint);
    searchButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        searchKey = searchField.getText();
        nRepo = filterPanel.getNRepo();
        nFollower = filterPanel.getNFollower();
        if (searchByPanel.getGroup().getSelection().getActionCommand().equals("Username")){
          searchByUser();
        } else if (searchByPanel.getGroup().getSelection().getActionCommand().equals("E-mail")){
          searchByEmail();
        } else  {
          searchByNamaPengguna();
        }
      }
    });
    
    constraint.fill = GridBagConstraints.HORIZONTAL;
    
    resultPanel = new ResultPanel();
    constraint.gridwidth = GridBagConstraints.REMAINDER;
    constraint.gridy++;   
    constraint.ipady = 150;
    add(resultPanel,constraint);
  }
  
  public void searchByUser() {
    //resultPanel.clear();
    //resultPanel.add(searchKey);
  }
  
  public static void searchByEmail() {
    //to-do
  }
  
  public static void searchByNamaPengguna() {
    //to-do
  }
}