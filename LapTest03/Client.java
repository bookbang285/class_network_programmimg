//6103051613116
//Rattanan Sakulratchata

import java.net.*;

public class Client {
   public static void main(String[] args) {
      try {
         String allMonth[] = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

         String month = "";
         String number = "";
         int port = 55555;
         InetAddress ia = InetAddress.getByName("127.0.0.1");
         DatagramSocket s = new DatagramSocket();

         if (args.length != 2) {
            System.out.println("incorrect command");
            System.exit(1);
         }
         int count = 0;
         for (int i = 0; i < allMonth.length; i++) {
            if (allMonth[i].equalsIgnoreCase(args[0]))
               count++;
         }
         if (count != 1) {
            System.out.println("illegal month abbreviation");
            System.exit(1);
         }

         if (isNumeric(args[0])) {
            System.out.println("illegal month abbreviation");
            System.exit(1);
         } else if (!isNumeric(args[1]) || args[1].indexOf(".") != -1) {
            System.out.println("invalid number");
            System.exit(1);
         }

         month = args[0].toLowerCase();
         number = args[1];
         byte[] sendBuffer;
         byte[] recvBuffer = new byte[512];

         sendBuffer = (month + " " + number).getBytes();
         DatagramPacket sendDp = new DatagramPacket(sendBuffer, sendBuffer.length, ia, port);
         s.send(sendDp);

         DatagramPacket recvDp = new DatagramPacket(recvBuffer, recvBuffer.length);
         s.receive(recvDp);

         String t = new String(recvDp.getData(), 0, recvDp.getLength());
         System.out.println(allMonth[Integer.parseInt(t)]);
         s.close();

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
