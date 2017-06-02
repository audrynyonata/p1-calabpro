package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * MainView.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

@SuppressWarnings("serial")
public class MainView extends JFrame {
  public FilterPanel filterPanel;
  public ResultPanel resultPanel;

  public MainView() {
    setTitle("SEARCH by Audry Nyonata");
    setSize(400,300);
    setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
    filterPanel = new FilterPanel();
    add(filterPanel);
    resultPanel = new ResultPanel();
	  add(resultPanel);
	}
  
  class FilterPanel extends JPanel {
    public JButton searchButton;
    public JTextArea searchField;
    
    public FilterPanel() {
      searchField = new JTextArea(1,20);
      add(searchField);
      searchButton = new JButton();
      searchButton.setText("Search");
      add(searchButton);
    }
  }
   
  class ResultPanel extends JPanel {
    
  }
}