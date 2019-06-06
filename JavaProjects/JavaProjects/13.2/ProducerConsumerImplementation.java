/**
 * ProducerConsumerImplementation.java
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
import java.rmi.server.UnicastRemoteObject;

/***
 * ProducerConsumerImplementation class implements the remote interface
 */
class ProducerConsumerImplementation
        extends UnicastRemoteObject
        implements ProducerConsumerInterface {
    int Storage = 100;
    final Storage.ProducerConsumer ConsumerProducerThread =
            new Storage.ProducerConsumer( Storage );
    Thread Consumer[];
    Thread Producer[];
    static Boolean ProducerFlag = false;
    static Boolean ConsumerFlag = false;

    public ProducerConsumerImplementation() throws RemoteException {
    }

    public String sayHelloConsumer(int ConsumerCount, int rate) throws RemoteException {
        try {
            ConsumerFlag = true;
            //producer thread
            //trigger
            Consumer = new Thread[ConsumerCount];
            for (int consumer = 0; consumer < ConsumerCount; consumer++) {
                int finalConsumer = consumer;
                Consumer[consumer] = new Thread( new Runnable() {
                    @Override
                    public void run() {
                        ConsumerProducerThread.consume( rate,
                                finalConsumer + 1 );
                    }
                } );
            }
            for (int consumerIndex = 0; consumerIndex < ConsumerCount;
                 consumerIndex++)
                Consumer[consumerIndex].start();
            return "Consumer";
        } catch (Exception e) {
            return "error occured";
        }
    }

    public String sayHelloProducer(int ProducerCount, int rate) throws RemoteException {
        try {
            ProducerFlag = true;
            Producer = new Thread[ProducerCount];
            for (int producer = 0; producer < ProducerCount; producer++) {
                int finalProducer = producer;
                Producer[producer] = new Thread( new Runnable() {
                    @Override
                    public void run() {
                        ConsumerProducerThread.produce( rate,
                                (finalProducer) % 3 + 1, finalProducer + 1 );
                    }
                } );
            }
            for (int producerIndex = 0; producerIndex < ProducerCount;
                 producerIndex++)
                Producer[producerIndex].start();
            return "Producer";
        } catch (Exception e) {
            return "error";
        }
    }
}
