import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.Entry;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileMenuHandler implements ActionListener {
   JFrame jframe;
   static String chosenFileName;
   static TreeMap<Integer, Clock> treeMap = new TreeMap();

   public FileMenuHandler(JFrame jf) {
      this.jframe = jf;
   }

   public void actionPerformed(ActionEvent event) {
      String menuName = event.getActionCommand();
      if (menuName.equals("Open")) {
         this.openFile();
      } else if (menuName.equals("Quit")) {
         System.exit(0);
      }

   }

   private void openFile() {
      JFileChooser chooser = new JFileChooser();
      int status = chooser.showOpenDialog((Component)null);
      if (status == 0) {
         this.readSource(chooser.getSelectedFile());
      } else {
         JOptionPane.showMessageDialog((Component)null, "Open File dialog canceled");
      }

   }

   private void readSource(File chosenFile) throws IllegalClockException {
      Color DeeP = new Color(193, 188, 239);
      Color DeeB = new Color(188, 220, 239);
      chosenFileName = chosenFile.getAbsolutePath();
      TextFileInput inFile = new TextFileInput(chosenFileName);
      this.jframe.getContentPane().removeAll();
      Container myContentPane = this.jframe.getContentPane();
      TextArea myTextArea = new TextArea();
      TextArea Sorted = new TextArea();
      myContentPane.add(myTextArea);
      myContentPane.add(Sorted);
      Sorted.setBackground(DeeP);
      myTextArea.setBackground(DeeB);
      Sorted.setText("Sorted: \n");
      myTextArea.setText("Unsorted: \n");

      for(String line = inFile.readLine(); line != null; line = inFile.readLine()) {
         StringTokenizer Str = new StringTokenizer(line, ":");

         try {
            if (Str.countTokens() == 3) {
               int hours = Integer.parseInt(Str.nextToken());
               int minutes = Integer.parseInt(Str.nextToken());
               int seconds = Integer.parseInt(Str.nextToken());
               Clock clock = new Clock(hours, minutes, seconds);
               myTextArea.append(clock.toString() + "\n");
               treeMap.put(clock.getHours() * 100 + clock.getMinutes(), clock);
            } else {
               System.out.println(line);
            }
         } catch (IllegalClockException var14) {
            System.out.println(var14);
            System.out.println(line);
         }
      }

      Set set = treeMap.entrySet();
      Iterator i = set.iterator();

      while(i.hasNext()) {
         Entry me = (Entry)i.next();
         Sorted.append(me.getValue() + "\n");
      }

      this.jframe.setVisible(true);
   }
}
