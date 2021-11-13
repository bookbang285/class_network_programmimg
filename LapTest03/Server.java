
//6103051613116
//Rattanan Sakulratchata
import java.net.*;

public class Server {
   public static void main(String[] args) {
      try {
         String allMonth[] = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

         int port = 55555;
         DatagramSocket s = new DatagramSocket(port);
         while (true) {
            byte[] sendBuff;
            byte[] recvBuff = new byte[8000];

            DatagramPacket dp = new DatagramPacket(recvBuff, recvBuff.length);

            s.receive(dp);
            String message = new String(dp.getData(), 0, dp.getLength());

            // Mon 10 split " "
            String[] array = message.split(" ");
            String month = array[0];
            String number = array[1];

            // check month
            int check = 0;
            for (int i = 0; i < allMonth.length; i++) {
               if (month.equalsIgnoreCase(allMonth[i]))
                  check = i;
            }

            int tran = (check + Integer.parseInt(number)) % allMonth.length;

            if (tran % 12 == 0)
               tran = check;
            else if (tran <= -1 && tran >= -12)
               tran = allMonth.length + tran;
            else if (tran < -12)
               tran = ((allMonth.length / tran) + 1) + tran;

            // Print Now Past Future
            if (Integer.parseInt(number) == 0)
               System.out.println("Now");
            else if (Integer.parseInt(number) >= 1)
               System.out.println("Future");
            else
               System.out.println("Past");

            // send data
            sendBuff = String.valueOf(tran).getBytes();
            DatagramPacket dp2 = new DatagramPacket(sendBuff, sendBuff.length, dp.getAddress(), dp.getPort());
            s.send(dp2);

         }
      } catch (Exception e) {

      }
   }
   /
}
