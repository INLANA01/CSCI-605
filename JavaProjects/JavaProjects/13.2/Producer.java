/**
 * Producer.java
 *
 * Version: 1.0
 *
 * Revisions: 1
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */
import java.rmi.*;

/***
 * Producer class is a Client which calls the Implementation class by providing the required input information
 */
public class Producer {
    public static void main(String args[] ) {
        String message = "";
        try {
            if (args.length != 2) {
                throw new InSufficientArguments();
            }
            final int ProducerCount = Integer.valueOf( args[0] );
            final int ProducingRate = Integer.valueOf( args[1] );
            while(true) {
                ProducerConsumerInterface obj = (ProducerConsumerInterface) Naming.
                lookup( "//localhost/HelloProducer" );
                message = obj.sayHelloProducer( ProducerCount, ProducingRate );
                System.out.println( message );
            }
        } catch (Exception e) {
            System.out.println("Producer exception: " +
                    e.getMessage());
            e.printStackTrace();
        }
    }
}