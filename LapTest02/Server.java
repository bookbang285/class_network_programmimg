
//6103051613116
//Rattanan Sakulratchata
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {
   Socket s1 = null;

   public Server(Socket s1) {
      this.s1 = s1;
   }

   public static void main(String[] args) {
      try {
         InetAddress ip = InetAddress.getLocalHost();
         System.out.println("Server OK!");
      } catch (Exception e) {
      }

      ServerSocket serv = null;
      ExecutorService es = Executors.newFixedThreadPool(20);

      try {
         serv = new ServerSocket(55555);
      } catch (Exception e) {
         System.out.println("Server Error");
         System.exit(1);
      }

      while (true) {
         try {
            Socket s1 = serv.accept();
            Server sv = new Server(s1);
            es.execute(sv);
         } catch (Exception e) {
         }
      }
   }

   public void run() {
      try {
         InputStream in1 = s1.getInputStream();
         BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
         PrintWriter pw1 = new PrintWriter(s1.getOutputStream());
         String commands = br1.readLine();
         if (commands.equals("mode")) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            String number;
            while (true) {
               number = br1.readLine();
               if (number.equals("END")) {
                  break;
               } else {
                  list.add(Integer.parseInt(number));
               }
            }

            pw1.println("mode = " + mode(list));
            pw1.flush();
         } else if (commands.equals("sum")) {
            int sum = 0;
            String number;
            while (true) {
               number = br1.readLine();
               if (number.equals("END")) {
                  break;
               } else {
                  sum += Integer.parseInt(number);
               }
            }
            pw1.println("sum = " + sum);
            pw1.flush();

         } else {
            pw1.println("command not found");
            pw1.flush();
         }

         System.out.println("Server OK!");
         br1.close();
         pw1.close();
         s1.close();
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

   /*
    * static int mode(ArrayList<String> list, int n) { int maxValue = -1, maxCount
    * = 1;
    * 
    * for (int i = 0; i < list.size(); i++) { int count = 0; for (int j = 0; j <
    * list.size(); ++j) { if (list.get(j) == list.get(i)) ++count; }
    * 
    * if (count > maxCount) { maxCount = count; maxValue =
    * Integer.parseInt(list.get(i)); } } System.out.println(maxValue); return
    * maxValue; }
    */
   public static int mode(ArrayList<Integer> list) {
      int maxValue = -1, maxCount = 0;

      for (int i = 0; i < list.size(); ++i) {
         int count = 0;
         for (int j = 0; j < list.size(); ++j) {
            if (list.get(j) == list.get(i))
               ++count;
         }
         if (count > maxCount) {
            maxCount = count;
            maxValue = Integer.valueOf(list.get(i));
         } else if ((count == maxCount) && (list.get(i)) != maxValue) {
            maxValue = -1;
         }
      }
      return maxValue;
   }
}
