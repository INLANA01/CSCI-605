/*
 * qServerUdp.java
 *
 * Version: 2.0
 *
 * Revisions: 1
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */


import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// java qServerUdp -port 53818

/*
 * The class qServerUdp connects with a client through UDP and
 * after receiving the secret code, returns a quote of the day.
 * The base code has been taken from lecture code 'Datagram Server'
 * and has been modified to fit the specifications and requirements.
 *
 */

public class qServerUdp extends Thread {

    DatagramSocket socket;
    static String hostName = "karumanchis-MacBook-Air.local";
    int	port = 1313;

    public qServerUdp()	{
    }

    public qServerUdp(int port)	{
        try {
            socket = new DatagramSocket(port);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Parse the commandline arguments and sets variables.
     */
    public void parseArgs(String args[]) {
        for (int i = 0; i < args.length; i ++) {
            if (args[i].equals("-port")) {
                port = Integer.valueOf(args[++i]);
                new qServerUdp(port).start();
            }
        }
    }

    /**
     * Here the sending and receiving of the packets takes place
     * The authentication of the client is done in this method
     */
    public void run()	{
        byte[] buf = new byte[256];
        try {

            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            InetAddress address = packet.getAddress();
            int port = packet.getPort();

            // Ask for authentication / secret code
            String authentication = "What is the secret code?";
            buf = authentication.getBytes();
            DatagramPacket forSend = new DatagramPacket(buf, buf.length, address, port);
            socket.send(forSend);

            // receive password
            socket.receive(packet);
            String received_password = new String(packet.getData(), 0, packet.getLength());
            System.out.println(received_password);

            // return quote if correct password received
            if ((received_password.equals("OPEN SESAME"))) {

                Random random = new Random();
                int rand = random.nextInt(8);

                // read a random quote from the file
                String sendThis = Files.readAllLines(Paths.get("copy.txt")).get(rand);

                buf = sendThis.getBytes();
                packet = new DatagramPacket(buf, buf.length, address, port);

                System.out.println("Sending data: " + new String(buf));
                socket.send(packet);
            }

            // display error message and exit if wrong password
            else {
                String sendThis = "Wrong password. Goodbye";
                buf = sendThis.getBytes();
                packet = new DatagramPacket(buf, buf.length, address, port);
                System.out.println(sendThis);
                socket.send(packet);
                System.exit(2);
            }

        } catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void main(String argv[]) {
        if ( argv.length == 0 )
            new qServerUdp(0).start();
        else
            new qServerUdp().parseArgs(argv);
    }
}