import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextFileInput {
   private String filename;
   private BufferedReader br;
   private int lineCount = 0;

   public TextFileInput(String filename) {
      this.filename = filename;

      try {
         this.br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public void close() {
      try {
         this.br.close();
         this.br = null;
      } catch (NullPointerException var2) {
         throw new NullPointerException(this.filename + "already closed.");
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public String readLine() {
      return this.readLineOriginal();
   }

   public int getLineCount() {
      return this.lineCount;
   }

   public static boolean isOneOf(char toBeChecked, char[] options) {
      boolean oneOf = false;

      for(int i = 0; i < options.length && !oneOf; ++i) {
         if (Character.toUpperCase(toBeChecked) == Character.toUpperCase(options[i])) {
            oneOf = true;
         }
      }

      return oneOf;
   }

   public static boolean isOneOf(String toBeChecked, String[] options) {
      boolean oneOf = false;

      for(int i = 0; i < options.length && !oneOf; ++i) {
         if (toBeChecked.equalsIgnoreCase(options[i])) {
            oneOf = true;
         }
      }

      return oneOf;
   }

   public String readSelection(String[] options) {
      if (options != null && options.length != 0) {
         String answer = this.readLine();
         if (answer == null) {
            throw new NullPointerException("End of file " + this.filename + "has been reached.");
         } else if (isOneOf(answer, options)) {
            return answer;
         } else {
            String optionString = options[0];

            for(int i = 1; i < options.length; ++i) {
               optionString = optionString + ", " + options[i];
            }

            throw new RuntimeException("File " + this.filename + ", line " + this.lineCount + ": \"" + answer + "\" not one of " + optionString + ".");
         }
      } else {
         throw new NullPointerException("No options provided for  selection to be read in file " + this.filename + ", line " + (this.lineCount + 1) + ".");
      }
   }

   public boolean readBooleanSelection() {
      String[] options = new String[]{"Y", "N", "yes", "no", "1", "0", "T", "F", "true", "false"};
      String answer = this.readSelection(options);
      return isOneOf(answer, new String[]{"Y", "yes", "1", "T", "true"});
   }

   protected final String readLineOriginal() {
      try {
         if (this.br == null) {
            throw new RuntimeException("Cannot read from closed file " + this.filename + ".");
         } else {
            String line = this.br.readLine();
            if (line != null) {
               ++this.lineCount;
            }

            return line;
         }
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }
}
