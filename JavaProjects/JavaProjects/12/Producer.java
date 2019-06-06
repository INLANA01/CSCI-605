/**
 * Producer.java
 * <p>
 * Version: 2.0
 * <p>
 * Revisions: 2
 *
 * @author Aditya Landge
 * @author Rupa Karumanchi
 */
import java.net.*;
import java.io.*;

public class Producer
{
    private Socket socket		 = null;
    private DataInputStream input = null;
    private DataOutputStream out	 = null;

    public Producer(String address, int port)
    {
        while(true) {
            try
            {
                socket = new Socket(address, port);
                socket.setSoTimeout( 1000 );
                System.out.println( "Connected" );
                out = new DataOutputStream( socket.getOutputStream() );
            }
            catch(UnknownHostException e)
            {
                System.err.println(e);
            }
            catch(IOException e)
            {
                System.err.println(e);
            }
            String line = "";
            try {
                line = "Producer";
                out.writeUTF( line );
            } catch (IOException i) {
                System.out.println( i );
            }
            try {
                out.close();
                socket.close();
            } catch (IOException i) {
                System.out.println( i );
            }
        }
    }

    public static void main(String args[]) {
        Producer client = new Producer("glados.cs.rit.edu", 5539);
    }
}