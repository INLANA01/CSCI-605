import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Finds all prime numbers p, 1 < p ≤ Number.
 * This class makes use of the 'Sieve of Eratosthenes' method to find prime
 * numbers up to a desired number. By this method, starting from 2 (we have
 * further optimised this method by starting from the square of the number (2))
 * all multiples of 2 are crossed out and this is repeated for multiples of the
 * next smallest prime number and so on. This is done until the next smallest
 * prime is greater than the square root of the number up to which we are
 * generating primes. The numbers remaining after striking out these multiples
 * are prime numbers.
 */
public class PrimeAsFastAsPossible extends Thread{
    static int Number = 214748364;
    int index;
    static boolean[] PrimeNumber;

    static {
        PrimeNumber = new boolean[Number];
    }

    private static long milliSeconds;

    /**
     * Constructor
     * @param index index of array to be change the boolean value of PrimeNumber.
     */
    public PrimeAsFastAsPossible(int index){
        this.index  = index;
    }

    /**
     * Strike out the the Multiples of prime numbers and set their boolean
     * values as false
     * @param primeNumber
     */
    public void strike(int primeNumber){
        synchronized (PrimeNumber) {
            int Multiples;
            Multiples = primeNumber * primeNumber;
            do {
                if (Multiples >= Number)
                    break;
                PrimeNumber[Multiples] = false;
                Multiples += primeNumber;
            } while (true);
        }
    }

    /**
     *Start execution of the thread
     */
    public void run(){
        strike(index);
    }

    /**
     * Initialize the time counter.
     */
    public static void init()  {
        milliSeconds = System.currentTimeMillis();
    }

    /**
     * End the time counter.
     * @param s String to display with the time counter
     */
    public static void end(String s)   {
        System.err.printf( "%s:       %dms%n",
                s, System.currentTimeMillis() - milliSeconds );
    }

    /**
     *
     * @param args Number upto which the program need to find prime numbers.
     * @throws NANException
     * @throws InCorrectArguments
     * @throws NegativeNumbersException
     * @throws ArithmeticException
     * @throws NumberFormatException
     */
    public static void main(String[] args) throws NANException,
            InCorrectArguments, NegativeNumbersException ,ArithmeticException,
            NumberFormatException {
        int threads = Runtime.getRuntime().availableProcessors();
        boolean flag = true;
        if (args.length == 1 && args[0] != null ){
            try {
                Number = Integer.valueOf( args[0] );//1000;
                if(Number < 0)
                    throw new NANException("Number should be positive" +
                            " (i.e. grater than ZERO)");
            }catch(ArithmeticException e){
                System.err.println( "The Argument should be of numeric" +
                        " value." );
                flag = false;
            }catch(NumberFormatException e){
                System.out.println( "The Argument should be of numeric " +
                        "value." );
                flag = false;
            }
        }
        else {
            throw new InCorrectArguments( "There should Exactly be one" +
                    " Argument and that should of numeric value" );
        }

        if(flag){
            for (int index = 0; index < Number; index++)
                PrimeNumber[index] = true;

            PrimeNumber[1] = false;
            PrimeNumber[2] = true;

            ExecutorService pool = Executors.newFixedThreadPool(threads);
            init();
            for (int index = 2; index < Math.sqrt( Number ); index++)
                if (PrimeNumber[index])
                    pool.execute( new PrimeAsFastAsPossible( index ) );

            for (int index = 2; index <= Number; index++)
                if (PrimeNumber[index])
                    System.out.println( index );
            end("Time Required");

            System.out.println( threads + " threads are optimal." );
            System.exit( 0 );
        }
    }
}

/**
 * Handles Incorrect Arguements
 */
class InCorrectArguments extends Exception
{
    public InCorrectArguments(){ }
    public InCorrectArguments(String s) {
        // Call constructor of parent Exception
        super(s);
    }
}

class NANException extends RuntimeException {
    public NANException() { }
    public NANException(String s) {
        super(s);
    }
}


class NegativeNumbersException extends RuntimeException {
    String s;
    public NegativeNumbersException() {
    }
    public NegativeNumbersException(String s) {
        super(s);
    }
}

