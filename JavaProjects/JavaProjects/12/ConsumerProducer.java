import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

/**
 * ConsumerProducer.java
 * <p>
 * Version: 2.0
 * <p>
 * Revisions: 2
 *
 * @author Aditya Landge
 * @author Rupa Karumanchi
 */
class ConsumerProducer {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in	 = null;

    /**
     * Paratmetrised Constructor that acts like a Server
     * @param port
     * @param args
     */
    public ConsumerProducer(int port, String[] args)
    {
        int Storage;
        try {
            if (args.length != 5) {
                throw new InSufficientArguments();
            }
            final int ConsumerCount = Integer.valueOf(args[0]);
            final int ProducerCount = Integer.valueOf(args[1]);
            final int ConsumingRate = Integer.valueOf(args[3]);
            final int ProducingRate = Integer.valueOf(args[2]);
            Storage = Integer.valueOf(args[4]);

            final ProducerConsumer ConsumerProducerThread =
                    new ProducerConsumer(Storage);

            Thread Consumer[] = new Thread[ConsumerCount];
            Thread Producer[] = new Thread[ProducerCount];
            server = new ServerSocket( port );

            System.out.println( "Server started" );
            System.out.println( "Waiting for a client ..." );
            for(;;) {
                Boolean producerFlag = false;
                Boolean consumerFlag = false;
                socket = server.accept();
                System.out.println( "Client accepted" );
                in = new DataInputStream(
                        new BufferedInputStream( socket.getInputStream() ) );
                String line = "";
                try {
                    line = in.readUTF();
                    System.out.println( line );
                    if (line.equals( "Consumer" )) {
                        consumerFlag = true;
                        socket.close();
                        in.close();
                    } else if (line.equals( "Producer" )) {
                        producerFlag = true;
                        socket.close();
                        in.close();
                    }
                } catch (IOException i) {
                    System.out.println( i );
                }
                System.out.println( "Closing connection" );

                Consumer = new Thread[ConsumerCount];
                for (int consumer = 0; consumer < ConsumerCount; consumer++) {
                    int finalConsumer = consumer;
                    Consumer[consumer] = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ConsumerProducerThread.consume(ConsumingRate,
                                    finalConsumer + 1);
                        }
                    });
                }
                Producer = new Thread[ProducerCount];
                for (int producer = 0; producer < ProducerCount; producer++) {
                    int finalProducer = producer;
                    Producer[producer] = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ConsumerProducerThread.produce(ProducingRate,
                                    (finalProducer) % 3 + 1, finalProducer + 1);
                        }
                    });
                }
                    if (producerFlag) ;
                    for (int producerIndex = 0; producerIndex < ProducerCount;
                         producerIndex++)
                        Producer[producerIndex].start();

                    for (int producerIndex = 0; producerIndex < ProducerCount;
                         producerIndex++)
                        Producer[producerIndex].join();

                    if (consumerFlag)
                        for (int consumerIndex = 0; consumerIndex < ConsumerCount;
                             consumerIndex++)
                            Consumer[consumerIndex].start();

                    for (int consumerIndex = 0; consumerIndex < ConsumerCount;
                         consumerIndex++)
                        Consumer[consumerIndex].join();

                    for (int producerIndex = 0; producerIndex < ProducerCount;
                         producerIndex++) {
                        Producer[producerIndex].stop();
                        Producer[producerIndex].interrupt();
                    }

                    for (int consumerIndex = 0; consumerIndex < ConsumerCount;
                         consumerIndex++) {
                        Consumer[consumerIndex].stop();
                        Consumer[consumerIndex].interrupt();
                    }
            }
        } catch (IOException i) {
            System.out.println( i );
        } catch (ArithmeticException e) {
            System.err.println("Please enter valid numeric values");
            e.printStackTrace();
        } catch (
                InSufficientArguments e) {
            System.err.println("Arguments are not enough or Exceeds the" +
                    " required amount");
            System.err.println("Arguments should be in the following order\n" +
                    "# 1 consumer consuming 3 items\n" +
                    "# 3 producer producing 2 items\n" +
                    "# 100 storage spaces\n");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Main Method takes command line inputs from the user and create an array
     * of Consumer and Producer thread on basis of the user inout.
     *
     * @param args how many consumer, rate of consumption, rate of production,
     *             how many producer, and the length of the storage
     */
    public static void main(String[] args) {
        ConsumerProducer server;
        server = new ConsumerProducer(5539, args);
    }

    /**
     * ProducerConsumer class contains actual functionality and it adds
     * elements to take the Storage when asked to produce by a Producer
     * and removes elements from the Storage when ased to consume by a
     * Consumer. The rate of production and consumption is decided by the User
     */
    private static class ProducerConsumer {
        static int StorageSpace;
        static int[] items = new int[5];
        static Semaphore sem = new Semaphore(1);

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
            doProduce(rate, n, producer);
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
                                System.out.printf("Production Stopped for item %d%n "
                                        , n);
                                sem.release();
                                Thread.currentThread().stop();
                                Thread.currentThread().interrupt();
                            }
                        System.out.printf("Producer %s: produce %d item type: %d%n",
                                producer, count, n);
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
            doConsume(rate, consumer);

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
                        System.out.printf("Consumer %s: consume %d 3 type: 1 items 5 " +
                                        "type: 2 items & 3 type : 3 items %n",
                                consumer, rate);
                    }
                    else{
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
        super(s);
    }
}