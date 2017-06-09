package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private int last = 0;
    private String[] listItems = new String[100];
    private JList<String> userList;
    
    public ResultPanel(){
      setLayout(new BorderLayout());
      userList = new JList<String>(listItems);
      userList.setLayoutOrientation(JList.VERTICAL);
      userList.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent e) {
          if (e.getClickCount() >= 1){
            int index = userList.locationToIndex(e.getPoint());
            if (index>=0 && userList!=null){
              Object o = userList.getModel().getElementAt(index);
              if (o!=null){
                String s = o.toString();
                UserView userFrame = new UserView(s);
                userFrame.setVisible(true);
              }
            }
          }
        }
      });
      
      /* Automatic Scrolling */
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
  
  public void searchByUser() {
    //resultPanel.clear();
    resultPanel.add(searchKey);
  }
  
  public static void searchByEmail() {
    //to-do
  }
  
  public static void searchByNamaPengguna() {
    //to-do
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
    
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    
    Insets insets1 = new Insets(0,0,30,0);
    
    JLabel label1 = new JLabel("Search by Audry Nyonata");
    label1.setFont(new Font(label1.getFont().getName(),Font.PLAIN,32));
    add(label1,new GridBagConstraints(0,0,GridBagConstraints.REMAINDER,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,insets1,0,0));
    
    add(new JLabel("Enter keyword : "),new GridBagConstraints(0,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,insets1,0,0));

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
    add(searchField,new GridBagConstraints(1,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,insets1,0,0));

    Insets insets2 = new Insets(0,0,20,20);
    searchByPanel = new SearchByPanel();
    add(searchByPanel,new GridBagConstraints(0,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,insets2,0,0));

    filterPanel = new FilterPanel();
    add(filterPanel,new GridBagConstraints(1,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,insets1,0,0));

    searchButton = new JButton("Search");
    searchButton.setEnabled(false);
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
    add(searchButton,new GridBagConstraints(0,3,GridBagConstraints.REMAINDER,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,insets1,0,0));
   
    Insets insets3 = new Insets(0,0,20,0);
    resultPanel = new ResultPanel();
    add(resultPanel,new GridBagConstraints(0,4,GridBagConstraints.REMAINDER,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,insets3,0,150));
    
    JButton exit = new JButton("Exit");
    exit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.exit(0);
      }
    });
    add(exit,new GridBagConstraints(0,5,GridBagConstraints.REMAINDER,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,insets3,0,0));
  }
}