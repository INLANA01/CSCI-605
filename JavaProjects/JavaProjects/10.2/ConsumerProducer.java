/**
 * ConsumerProducer.java
 *
 * Version: 2.0
 *
 * Revisions: 2
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
                int finalProducer = producer;
                Producer[producer] = new Thread( new Runnable() {
                    @Override
                    public void run() {
                        try {
//                            Random rand = new Random();
//                            int  n = rand.nextInt(4) + 1;
                            ConsumerProducerThread.produce(ProducingRate ,
                                    (finalProducer)%3 + 1 , finalProducer + 1 );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } );
            }

            Thread Consumer[] = new Thread[ConsumerCount];
            for (int consumer = 0; consumer < ConsumerCount; consumer++) {
                int finalConsumer = consumer;
                Consumer[consumer] = new Thread( new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ConsumerProducerThread.consume( ConsumingRate,
                                    finalConsumer+1 );
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
        static int StorageSpace;
        static int[] items = new int[5];

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
         * @param n inducate the item to be produced
         * @param producer indicate the ID(number / index) of consumer
         * @throws InterruptedException
         */
        public void produce(int rate, int n, int producer) throws InterruptedException
        {
            while (true) synchronized (this) {
                int count = 0;
                while (items[n] >= StorageSpace) wait();
                for (int index = 0; index < rate; index++)
                    if (items[n] <= StorageSpace) {
                        items[n] += 1;
                        count += 1;
                        wait( 100 );
                    } else {
                        System.out.printf( "Production Stopped for item %d%n "
                                , n );
                        wait();
                    }
                System.out.printf( "Producer %s: produce %d item type: %d%n",
                        producer, count, n );
                if(items[1] < StorageSpace && items[2] < StorageSpace &&
                        items[3] < StorageSpace)
                    notify();
            }
        }

        /***
         * Consumes items from the Storage.
         * @param rate rate at which the Consumer Consumes an item
         * @param consumer indicate the ID(number / index) of consumer
         * @throws InterruptedException
         */
        public void consume(int rate, int consumer) throws InterruptedException
        {
            while (true) synchronized (this) {
                while (items[1] == 0 || items[2]==0 || items[3]==0) wait();
                for (int index = 0; index < rate; index++)
                    if (items[1] >= 3 && items[2] >= 5 && items[3] >= 2) {
                        items[1] -= 3;
                        items[2] -= 5;
                        items[3] -= 2;
                        wait( 1 );
                    } else wait();
                System.out.printf( "Consumer %s: consume %d 3 type: 1 items 5 " +
                	"type: 2 items & 3 type : 3 items %n", consumer, rate );
                if((items[1] < StorageSpace && items[2] < StorageSpace &&
                        items[3] < StorageSpace) ||
                        (items[1] > 0 && items[2] > 0 && items[3]  > 0)
                )
                    notify();
            }
        }
    }
}

/***
 * Custom Exception for Insufficient argument provided by the user
 */
class InSufficientArguments extends Exception
{
    public InSufficientArguments(){

    }
    public InSufficientArguments(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }
}
