/**
 * ProducerConsumerInterface.java
 *
 * Version: 1.0
 *
 * Revisions: 1
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */

public interface ProducerConsumerInterface extends java.rmi.Remote {
    String sayHelloProducer(int Producers, int rate) throws java.rmi.RemoteException;
    String sayHelloConsumer(int Consumers, int rate) throws java.rmi.RemoteException;
}