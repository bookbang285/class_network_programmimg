
//6103051613116
//Rattanan Sakulratchata
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
   public static void main(String[] args) {
      String command = "";
      try {
         command = args[0].toLowerCase();
         if (args.length < 1 || !command.equals("mode") && !command.equals("sum")) {
            System.out.println("Command not found");
            System.exit(1);
         }
         if (args.length < 2) {
            System.out.println("Data not found");
            System.exit(1);
         }
      } catch (Exception e) {
         System.out.println("Command not found");
         System.exit(1);
      }

      try {
         Socket s = new Socket("127.0.0.1", Integer.parseInt("55555"));
         BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
         PrintWriter pw = new PrintWriter(s.getOutputStream());

         pw.println(command);
         pw.flush();

         for (int i = 1; i < args.length; i++) {
            if (isNumeric(args[i])) {
               pw.println(args[i]);
               pw.flush();
            } else {
               System.out.println("Error Args");
               System.exit(1);
            }
         }
         pw.println("END");
         pw.flush();

         String ss = br.readLine();
         System.out.println(ss);
      } catch (Exception e) {
      }

   }

   public static boolean isNumeric(String strNum) {
      if (strNum == null) {
         return false;
      }
      try {
         double d = Double.parseDouble(strNum);
      } catch (NumberFormatException nfe) {
         return false;
      }
      return true;
   }
}