import java.awt.GridLayout;
import java.awt.TextArea;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClockGUI extends JFrame {
   private JFrame myGUI;
   TextArea text;
   TextArea textArea2;
   private JMenuBar menuBar;
   private String chosenFileName;
   TreeMap<Clock, String> sortedMap = new TreeMap();

   public ClockGUI() {
      this.setTitle("clocks");
      this.setSize(400, 400);
      this.setLocation(400, 200);
      this.text = new TextArea();
      this.textArea2 = new TextArea();
      this.myGUI = new JFrame();
      this.createFileMenu(this.myGUI);
      this.myGUI.setTitle("Clocks");
      this.myGUI.setDefaultCloseOperation(3);
      this.myGUI.setSize(400, 400);
      this.myGUI.setLocation(100, 100);
      this.myGUI.setLayout(new GridLayout(1, 2));
      this.myGUI.setVisible(true);
   }

   private void createFileMenu(JFrame myGUI) {
      this.menuBar = new JMenuBar();
      JMenuBar menuBar = new JMenuBar();
      JMenu fileMenu1 = new JMenu("Files");
      JMenu fileMenu2 = new JMenu("Edit");
      FileMenuHandler fmh = new FileMenuHandler(myGUI);
      EditMenuHandler emh = new EditMenuHandler(myGUI);
      JMenuItem item1 = new JMenuItem("Open");
      item1.addActionListener(fmh);
      fileMenu1.add(item1);
      fileMenu1.addSeparator();
      JMenuItem item2 = new JMenuItem("Quit");
      item2.addActionListener(fmh);
      fileMenu1.add(item2);
      JMenuItem item3 = new JMenuItem("Search");
      item3.addActionListener(emh);
      fileMenu2.add(item3);
      menuBar.add(fileMenu1);
      menuBar.add(fileMenu2);
      myGUI.setJMenuBar(menuBar);
   }
}
