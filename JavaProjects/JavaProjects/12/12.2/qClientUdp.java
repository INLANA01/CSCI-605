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

import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.*;

// java qClientUdp -port 1234 -server localhost

/*
 * The class qClientUdp connects with a server through UDP and
 * after authenticating through the secret code, receives a quote
 * for the day. The base code has been taken from lecture code 'Datagram
 * Client' and has been modified to fit the specifications and requirements.
 */

public class qClientUdp {

    String hostName = "karumanchis-MacBook-Air.local";
    int port = 1313;

    /**
     * Parse the commandline arguments and sets variables.
     */
    public void parseArgs(String args[]) {

        for (int i = 0; i < args.length; i ++) {
            if (args[i].equals("-port"))
                port = Integer.valueOf(args[++i]);
            else if(args[i].equals("-server"))
                hostName = args[++i];
        }
    }

    public void doTheJob()	{
        try {

            byte buf[] = new byte[256];

            InetAddress aInetAddress = InetAddress.getByName(hostName);
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(buf,
                    buf.length, aInetAddress, port);
            socket.send(packet);

            // Receive request for secret code
            socket.receive(dp);
            System.out.println(new String(dp.getData())); // Secret Code authentication
            Scanner scanner = new Scanner(System.in);
            String password = scanner.nextLine();
            buf = password.getBytes();
            packet = new DatagramPacket(buf, buf.length, aInetAddress, port);
            // Send the secret code / password
            socket.send(packet);

            // Receive either Quote or Error message
            socket.receive(dp);
            System.out.println(new String(dp.getData()));
            socket.close();

        } catch (Exception e) {
            System.out.println (e);
            e.printStackTrace();
        }
    }

    public static void main(String argv[]) {
        qClientUdp aqClientUdp = new qClientUdp();
        aqClientUdp.parseArgs(argv);
        aqClientUdp.doTheJob();
    }
}