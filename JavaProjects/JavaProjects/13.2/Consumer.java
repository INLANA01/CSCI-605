/**
 * Consumer.java
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
 * Consumer class is a Client which calls the Implementation class by providing the required input information
 */
public class Consumer {
    public static void main(String args[] ) {
        String message = "";
        try {
            if (args.length != 2) {
                throw new InSufficientArguments();
            }
            final int ConsumerCount = Integer.valueOf( args[0] );
            final int ConsumingRate = Integer.valueOf( args[1] );

            while(true) {
                ProducerConsumerInterface obj = (ProducerConsumerInterface) Naming.
                lookup( "//localhost/HelloConsumer" );

                message = obj.sayHelloConsumer( ConsumerCount, ConsumingRate );

                System.out.println( message );
            }
        } catch (Exception e) {
            System.out.println("Consumer exception: " +
                    e.getMessage());
            e.printStackTrace();
        }
    }
}