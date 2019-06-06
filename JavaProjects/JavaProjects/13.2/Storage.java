/**
 * Storage.java
 *
 * Version: 2.0
 *
 * Revisions: 2
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */
import java.rmi.*;
import java.util.concurrent.Semaphore;

/***
 * Storage class is a RMI Server which accepts tasks from Producers and Consumers,
 * runs the tasks, and returns the results.
 */
public class Storage {

    public static void main(String args[]) {
        try {
            try {
                ProducerConsumerInterface prod = new ProducerConsumerImplementation();
                ProducerConsumerInterface cons = new ProducerConsumerImplementation();
                Naming.rebind( "//localhost/HelloConsumer", prod );
                Naming.rebind( "//localhost/HelloProducer", cons );
                System.out.println( "Storage bound in registry" );

            } catch (Exception e) {
                System.out.println( "Producer Consumer Implementation err: " + e.getMessage() );
                e.printStackTrace();
                System.exit( 0 );
            }
        }
        catch (ArithmeticException e) {
            System.err.println( "Please enter valid numeric values" );
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ProducerConsumer class contains actual functionality and it adds
     * elements to take the Storage when asked to produce by a Producer
     * and removes elements from the Storage when ased to consume by a
     * Consumer. The rate of production and consumption is decided by the User
     */
     public static class ProducerConsumer {
        static int StorageSpace;
        static int[] items = new int[5];
        static Semaphore sem = new Semaphore( 1 );

        /**
         * Default constructor to get the total Storage Space from the user
         *
         * @param StorageSpace is the Space decided by user available for Storage
         */
        public ProducerConsumer(int StorageSpace) {
            this.StorageSpace = StorageSpace;
        }

        /**
         * Produces items to Storage in Storage
         *
         * @param rate     rate at which the Producer Produces an item
         * @param n        inducate the item to be produced
         * @param producer indicate the ID(number / index) of consumer
         * @throws InterruptedException
         */

        public void produce(int rate, int n, int producer) {
            doProduce( rate, n, producer );
        }

        /**
         * Produces items to Storage in Storage
         *
         * @param rate     rate at which the Producer Produces an item
         * @param n        inducate the item to be produced
         * @param producer indicate the ID(number / index) of consumer
         * @throws InterruptedException
         */
        public void doProduce(int rate, int n, int producer) {
            while (true) {
                try {
                    if (items[n] + rate <= StorageSpace) {
                        sem.acquire();
                        int count = 0;
                        for (int index = 0; index < rate; index++)
                            if (items[n] <= StorageSpace) {
                                items[n] += 1;
                                count += 1;
                            } else {
                                System.out.printf( "Production Stopped for item %d%n "
                                        , n );
                                sem.release();
                                Thread.currentThread().stop();
                                Thread.currentThread().interrupt();
                            }
                        System.out.printf( "Producer %s: produce %d item type: %d%n",
                                producer, count, n );
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    sem.release();
                    if (items[n] + rate >= StorageSpace)
                        Thread.currentThread().stop();
                }
            }
        }

        /***
         * Consumes items from the Storage.
         * @param rate rate at which the Consumer Consumes an item
         * @param consumer indicate the ID(number / index) of consumer
         * @throws InterruptedException
         */
        public void consume(int rate, int consumer) {
            doConsume( rate, consumer );

        }

        /***
         * Consumes items from the Storage.
         * @param rate rate at which the Consumer Consumes an item
         * @param consumer indicate the ID(number / index) of consumer
         * @throws InterruptedException
         */
        public void doConsume(int rate, int consumer) {
            while (true) {
                try {
                    if (items[1] - (3 * rate) >= 0 && items[2] - (5 * rate) >= 0 && items[3] - (2 * rate) >= 0) {
                        sem.acquire();
                        for (int index = 0; index < rate; index++) {
                            items[1] -= 3;
                            items[2] -= 5;
                            items[3] -= 2;

                        }
                        System.out.printf( "Consumer %s: consume %d 3 type: 1 items 5 " +
                                        "type: 2 items & 3 type : 3 items %n",
                                consumer, rate );
                    } else {
                        sem.release();
                        Thread.currentThread().stop();
                        Thread.currentThread().interrupt();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (items[1] - (3 * rate) <= 0 || items[2] - (5 * rate) <= 0 || items[3] - (2 * rate) <= 0) {
                        Thread.currentThread().stop();
                    }
                    sem.release();
                }
            }
        }
    }
}

    /***
     * Custom Exception for Insufficient argument provided by the user
     */
    class InSufficientArguments extends Exception {
        public InSufficientArguments() {

        }

        public InSufficientArguments(String s) {
            // Call constructor of parent Exception
            super( s );
        }
    }
