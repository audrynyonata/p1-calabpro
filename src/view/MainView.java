package view;

import controller.ParserJson;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

/**
 * MainView.java.
 * Memanfaatkan berbagai controller dan data model,
 * Menyediakan user interface utama program.
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

@SuppressWarnings("serial")
public class MainView extends JFrame {
  class SearchByPanel extends JPanel {
    /** 
     * Atribut buttonGroup.
     */
    private ButtonGroup group = new ButtonGroup();
    /**
     * Atribut array of string bernilai "Username", "E-mail", "Nama Pengguna".
     */
    private String[] text = {"Username", "E-mail", "Nama Pengguna"}; 
    /**
     * Atribut array of string bernilai "login", "email", "fullname".
     */
    private String[] value = {"login", "email", "fullname"}; 
       
    /**
     * Konstruktor.
     */
    public SearchByPanel() {
      setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
      add(new JLabel("Search by : "));
      for (int i = 0;i < value.length;i++) {
        JRadioButton button = new JRadioButton();
        button.setText(text[i]);
        button.setActionCommand(value[i]);
        if (i == 0) {
          button.setSelected(true);
        }
        add(button);
        group.add(button);
      }
    }
    
    /**
     * Mengembalikan buttonGroup dari radiobutton.
     * @return group bertipe buttonGroup.
     */
    public ButtonGroup getGroup() {
      return group;
    }
  }
   
  class FilterPanel extends JPanel {
    /**
     * Atribut textfield untuk filter jumlah repository minimum.
     * Format angka (0 s.d. 99999999) tanpa karakter minus, koma, maupun spasi.
     */
    private JFormattedTextField repoField;
    /**
     * Atribut textfield untuk filter jumlah follower minimum.
     * Format angka (0 s.d. 99999999) tanpa karakter minus, koma, maupun spasi.
     */
    private JFormattedTextField followerField;
    
    /**
     * Konstruktor.
     */
    public FilterPanel() {
      setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
      NumberFormat f = NumberFormat.getNumberInstance();
      f.setGroupingUsed(false);
      NumberFormatter nf = new NumberFormatter(f) {
        public Object stringToValue(String s) throws ParseException {
          if (s.equals("")) {
            return null;
          } else {
            return super.stringToValue(s);
          }
        }
      };
      nf.setValueClass(Integer.class);
      nf.setAllowsInvalid(false);
      nf.setMinimum(0);
      nf.setMaximum(99999999);
      add(new JLabel("Filter : "));
      add(new JLabel("Jumlah repository : "));
      repoField = new JFormattedTextField(nf);
      add(repoField);
      add(new JLabel("Jumlah follower   : "));
      followerField = new JFormattedTextField(nf);
      add(followerField);
    }
    
    /**
     * Mengembalikan nilai int repository minimum dari repoField.
     * @return int jumlah repository.
     */
    public int getNRepo() {
      if (repoField.getText().equals("")) {
        return ParserJson.FILTER_NONE;
      } else {
        return (Integer.parseInt(repoField.getText()));
      }
    }

    /**
     * Mengembalikan nilai int follower minimum dari repoField.
     * @return int jumlah follower.
     */
    public int getNFollower() {
      if (followerField.getText().equals("")) {
        return ParserJson.FILTER_NONE;
      } else {
        return (Integer.parseInt(followerField.getText()));
      }
    }
  }
  
  class ResultPanel extends JPanel {
    /**
     * Atribut array of string untuk menyimpan username.
     */
    private String[] listItems = new String[100];
    /**
     * Atribut jlist of string untuk menyimpan username.
     */
    private JList<String> userList;
    
    /**
     * Konstruktor.
     */
    public ResultPanel() {
      setLayout(new BorderLayout());
      userList = new JList<String>(listItems);
      userList.setLayoutOrientation(JList.VERTICAL);
      userList.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          if (e.getClickCount() >= 1) {
            int index = userList.locationToIndex(e.getPoint());
            if (index >= 0 && userList != null) {
              String o = userList.getModel().getElementAt(index).toString();
              if (!o.equals("") && !o.equals("Tidak ditemukan")) {
                String s = o.toString();
                UserView userFrame = new UserView(s);
                userFrame.setVisible(true);
              }
            }
          }
        }
      });
      
      JScrollPane p = new JScrollPane(userList);
      add(p);
    }
    
    /**
     * Mengembalikan data yang tersimpan pada jlist of string.
     * @return jlist of string.
     */
    public JList<String> getList() {
      return userList;
    }
    
    /**
     * Mengembalikan data yang tersimpan pada array of string.
     * @return array of string.
     */
    public String[] getArray() {
      return listItems;
    }
    
    /**
     * Mengubah data yang tersimpan pada atribut array sekaligus jlist.
     * @param s array of string yang ingin disimpan di atribut.
     */
    public void setArray(String[] s) {
      clear();
      for (int i = 0; i < s.length; i++) {
        listItems[i] = s[i];
      }
      userList.setListData(listItems);
    }
    
    /**
     * Menghapus data yang tersimpan pada atribut array sekaligus jlist.
     */
    public void clear() {
      for (int i = 0; i < listItems.length; i++) {
        listItems[i] = "";
      }
      userList.setListData(listItems);
    }
  }
  
  /**
   * Atribut textbox untuk menyimpan keyword pencarian.
   */
  public JTextField searchField;
  /**
   * Atribut searchByPanel.
   * Berisi group radiobutton mode pencarian login, email, dan fullname.
   */
  public SearchByPanel searchByPanel;
  /**
   * Atribut filterPanel.
   * Berisi textbox untuk filter jumlah repository dan follower minimum.
   */
  public FilterPanel filterPanel;
  /**
   * Atribut searchButton bertipe jbutton untuk memulai pencarian.
   */
  public JButton searchButton;
  /**
   * Atribut resultPanel berisi jlist of string yang menyimpan hasil pencarian.
   */
  public ResultPanel resultPanel;
  
  /**
   * Atribut string keyword pencarian.
   */
  public String keyword;
  /** 
   * Atribut string mode pencarian, login, email, atau fullname. 
   */
  public String searchIn;
  /**
   * Atribut Integer filter jumlah repository minimum pencarian.
   */
  public Integer numRepo;
  /**
   * Atribut Integer filter jumlah follower minimum pencarian.
   */  
  public Integer numFollower;

  /**
   * Konstruktor.
   */
  public MainView() {
    setTitle("Search by Audry Nyonata");
    setSize(600,600);
    setLayout(new GridBagLayout());
    
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(
        dim.width / 2 - this.getSize().width / 2, 
        dim.height / 2 - this.getSize().height / 2);
    
    Insets insets1 = new Insets(0,0,25,0);
    
    JLabel label1 = new JLabel("Search by Audry Nyonata");
    label1.setFont(new Font(label1.getFont().getName(),Font.PLAIN,32));
    add(label1,new GridBagConstraints(0,0,GridBagConstraints.REMAINDER,1,0,0,
        GridBagConstraints.CENTER,GridBagConstraints.NONE,insets1,0,0));
    
    add(new JLabel("Enter keyword : "),new GridBagConstraints(0,1,1,1,0,0,
        GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,insets1,0,0));

    searchField = new JTextField(20);
    searchField.getDocument().addDocumentListener(new DocumentListener() {
      public void changedUpdate(DocumentEvent e) {
        check();
      }
      
      public void insertUpdate(DocumentEvent e) {
        check();
      }
      
      public void removeUpdate(DocumentEvent e) {
        check();
      }
      
      public void check() {
        if (searchField.getText().equals("")) {
          searchButton.setEnabled(false);
        } else {
          searchButton.setEnabled(true);
        }
      }
    });
    add(searchField,new GridBagConstraints(1,1,1,1,0,0,
        GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,insets1,0,0));

    Insets insets2 = new Insets(0,0,15,20);
    searchByPanel = new SearchByPanel();
    add(searchByPanel,new GridBagConstraints(0,2,1,1,0,0,
        GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,insets2,0,0));

    Insets insets3 = new Insets(0,0,15,0);
    filterPanel = new FilterPanel();
    add(filterPanel,new GridBagConstraints(1,2,1,1,0,0,
        GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,insets3,0,0));

    searchButton = new JButton("Search");
    searchButton.setEnabled(false);
    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        keyword = searchField.getText();
        searchIn = searchByPanel.getGroup().getSelection().getActionCommand();
        numRepo = filterPanel.getNRepo();
        numFollower = filterPanel.getNFollower();
        search();
      }
    });
    add(searchButton,new GridBagConstraints(0,3,GridBagConstraints.REMAINDER,
        1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,insets2,0,0));
   
    resultPanel = new ResultPanel();
    add(resultPanel,new GridBagConstraints(0,4,GridBagConstraints.REMAINDER,
        1,0,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,insets3,0,200));
    
    JButton credits = new JButton("Credits");
    credits.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String message = 
            "\"Search by Audry Nyonata\" offers to search among users "
            + "by their username, fullname, or email.\nTheir repositories will "
            + "also be listed if existed. Thanks to Github API and all of the\n"
            + "thoughtful programming laboratory assistants (2013, 2014) "
            + "for making this possible.\n"
            + "Seleksi Calon Asisten Lab Programming. Labtek V, ITB. 2017.\n";
        JOptionPane.showMessageDialog(
            new JFrame(), message, "Credits", JOptionPane.INFORMATION_MESSAGE);
      }
    });
    add(credits,new GridBagConstraints(0,5,1,1,0,0,
        GridBagConstraints.CENTER,GridBagConstraints.NONE,insets3,0,0));

    JButton exit = new JButton("Exit");
    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    add(exit,new GridBagConstraints(1,5,1,1,0,0,
        GridBagConstraints.CENTER,GridBagConstraints.NONE,insets3,0,0));
  }
  
  
  /**
   * Fungsi getUsers melakukan search pada API berdasarkan keyword.
   * Jika hasil kosong, array berisi 1 string berisi "Tidak ditemukan".
   * @return sebuah array hasil search. 
   * */
  public String[] getUsers() {
    ParserJson x = new ParserJson();
    x.setAsJsonSearch(keyword, searchIn, numRepo, numFollower);
    int count = Integer.parseInt(x.get(ParserJson.TOTAL_COUNT,0));
    String[] result = new String[count];
    if (count == 0) {
      result = new String[1];
      result[0] = "Tidak ditemukan";
    } else {
      if (count > 100) {
        count = 100;
      }
      result = new String[count];
      int fromIndex = x.getString().indexOf("\"login\"");
      String lastElement = "";
      String username = "";
      for (int n = 0; n < count; n++) {
        if (n > 0) {
          fromIndex = x.getString().indexOf(lastElement,fromIndex) + 1;
        }
        lastElement = x.get(ParserJson.SITE_ADMIN,fromIndex);
        username = x.get(ParserJson.USERNAME,fromIndex);
        result[n] = username;
      }
    }
    return result;
  }
  
  /**
   * Melakukan search.
   * Menghapus isi resultPanel dan diisi hasil pemanggilan fungsi getUsers.
   */
  public void search() {
    resultPanel.clear();
    resultPanel.setArray(getUsers());
  }
}