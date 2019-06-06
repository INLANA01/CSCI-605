/**
 * ConsumerProducer.java
 *
 * Version: 1.0
 *
 * Revisions: 0
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */
class ConsumerProducer
{
    /**
     * Main Method takes command line inputs from the user and create an array
     * of Consumer and Producer thread on basis of the user inout.
     * @param args  how many consumer, rate of consumption, rate of production,
     * how many producer, and the length of the storage
     */
    public static void main(String[] args) {
        int Storage;
        try {
            if (args.length != 5) {
                throw new InSufficientArguments();
            }
            final int ConsumerCount = Integer.valueOf( args[0] );
            final int ProducerCount = Integer.valueOf( args[1] );
            final int ConsumingRate = Integer.valueOf( args[3] );
            final int ProducingRate = Integer.valueOf( args[2] );
            Storage = Integer.valueOf( args[4] );

            final ProducerConsumer ConsumerProducerThread =
                    new ProducerConsumer( Storage );

            Thread Producer[] = new Thread[ProducerCount];
            for (int producer = 0; producer < ProducerCount; producer++) {
                Producer[producer] = new Thread( new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ConsumerProducerThread.produce( ProducingRate );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } );
            }

            Thread Consumer[] = new Thread[ConsumerCount];
            for (int consumer = 0; consumer < ConsumerCount; consumer++) {
                Consumer[consumer] = new Thread( new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ConsumerProducerThread.consume( ConsumingRate );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } );
            }

            for (int producerIndex = 0; producerIndex < ProducerCount;
                 producerIndex++)   Producer[producerIndex].start();

            for (int consumerIndex = 0; consumerIndex < ConsumerCount;
                 consumerIndex++)   Consumer[consumerIndex].start();

            for (int producerIndex = 0; producerIndex < ProducerCount;
                 producerIndex++)   Producer[producerIndex].join();

            for (int consumerIndex = 0; consumerIndex < ConsumerCount;
                 consumerIndex++)   Consumer[consumerIndex].join();
        }catch(ArithmeticException e){
            System.err.println( "Please enter valid numeric values" );
            e.printStackTrace();
        }
        catch(InSufficientArguments e){
            System.err.println( "Arguments are not enough or Exceeds the" +
                    " required amount" );
            System.err.println( "Arguments should be in the following order\n" +
                    "# 1 consumer consuming 3 items\n" +
                    "# 3 producer producing 2 items\n" +
                    "# 100 storage spaces\n" );
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * ProducerConsumer class contains actual functionality and it adds
     * elements to take the Storage when asked to produce by a Producer
     * and removes elements from the Storage when ased to consume by a
     * Consumer. The rate of production and consumption is decided by the User
     */
    private static class ProducerConsumer
    {
        int StorageSpace;
        static int Storage = 0;

        /**
         * Default constructor to get the total Storage Space from the user
         * @param StorageSpace is the Space decided by user available for Storage
         */
        public ProducerConsumer(int StorageSpace){
            this.StorageSpace = StorageSpace;
        }

        /**
         * Produces items to Storage in Storage
         * @param rate rate at which the Producer Produces an item
         */
        public void produce(int rate) throws InterruptedException
        {
            while (true) synchronized (this) {
                while (Storage == StorageSpace) wait();
                for (int index = 0; index < rate; index++)
                    if (Storage < StorageSpace) {
                        Storage += 1;
                        wait( 10 );
                    } else wait();
                System.out.printf( "%s: produce %d%n",
                        Thread.currentThread().getName(), rate );
                notify();
            }
        }
        /**
         * Consumes items from the Storage.
         * @param rate rate at which the Consumer Consumes an item
         */
        public void consume(int rate) throws InterruptedException
        {
            while (true) synchronized (this) {
                while (Storage == 0) wait();
                for (int index = 0; index < rate; index++)
                    if (Storage != 0) {
                        Storage -= 1;
                        wait( 100 );
                    } else wait();
                System.out.printf( "%s: consume %d%n",
                        Thread.currentThread().getName(), rate );
                notify();
            }
        }
    }
}

//class InSufficientArguments extends Exception
//{
//    public InSufficientArguments(){
//
//    }
//    public InSufficientArguments(String s)
//    {
//        // Call constructor of parent Exception
//        super(s);
//    }
//}
