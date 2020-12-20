import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EditMenuHandler implements ActionListener {
   JFrame jframe;

   public EditMenuHandler(JFrame jf) {
      this.jframe = jf;
   }

   public void actionPerformed(ActionEvent event) {
      String menuName = event.getActionCommand();
      if (menuName.equals("Search")) {
         String query = JOptionPane.showInputDialog((Component)null, "Enter an hour for a clock: ");
         this.readSource(query);
      }

   }

   private void readSource(String input) throws IllegalClockException {
      Color DeeP = new Color(193, 188, 239);
      Color DeeB = new Color(188, 220, 239);
      if (Integer.parseInt(input) < 24 && Integer.parseInt(input) > 0) {
         JFrame jframe = new JFrame();
         jframe.setTitle("Search");
         jframe.setDefaultCloseOperation(3);
         jframe.setSize(400, 250);
         jframe.setLocation(0, 0);
         jframe.setLayout(new GridLayout(1, 2));
         Container myContentPane = jframe.getContentPane();
         TextArea myTextArea = new TextArea();
         TextArea Sorted = new TextArea();
         myContentPane.add(myTextArea);
         myContentPane.add(Sorted);
         Sorted.setText("Search Sorted: \n");
         Sorted.setBackground(DeeP);
         myTextArea.setText("Search Unsorted: \n");
         myTextArea.setBackground(DeeB);
         TextFileInput inFile = new TextFileInput(FileMenuHandler.chosenFileName);

         for(String line = inFile.readLine(); line != null; line = inFile.readLine()) {
            StringTokenizer Str = new StringTokenizer(line, ":");

            try {
               if (Str.countTokens() == 3) {
                  int hours = Integer.parseInt(Str.nextToken());
                  int minutes = Integer.parseInt(Str.nextToken());
                  int seconds = Integer.parseInt(Str.nextToken());
                  Clock clock = new Clock(hours, minutes, seconds);
                  if (hours == Integer.parseInt(input)) {
                     myTextArea.append(clock.toString() + "\n");
                  }
               }
            } catch (IllegalClockException var15) {
            }
         }

         Set set = FileMenuHandler.treeMap.entrySet();
         Iterator i = set.iterator();

         while(i.hasNext()) {
            Entry me = (Entry)i.next();
            StringTokenizer Str = new StringTokenizer(me.getValue().toString(), ":");
            int hours = Integer.parseInt(Str.nextToken());
            if (hours == Integer.parseInt(input)) {
               Sorted.append(me.getValue() + "\n");
            }
         }

         jframe.setVisible(true);
      } else {
         input = JOptionPane.showInputDialog((Component)null, "That is not a real hour, enter an hour for a clock: ");
         this.readSource(input);
      }

   }
}
