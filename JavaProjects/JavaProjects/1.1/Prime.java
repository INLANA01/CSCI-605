/** 
 * Prime.java 
 * 
 * Version:   Java 10
 *     
 * Revisions: 1     
 */

/** 
 * Declaring arrays for prime numbers, factors and quotients
 */
class Prime {
  static int[] Primes = {2,3,5,7};
  static int[] Factors = new int[10];
  static int[] Q = new int[10];       //to store quotients

  /** 
   * Function for checking if a number is prime or not
   */
  public static boolean isPrime( int n ) {
	  for ( int index = 2; index < n; index++ ) {
		  if ( n % index  == 0 )
			  return false;
	  } 
	  return true;
  }

  /** 
   * Extracting factors of numbers, checking for prime factors out of those factors, 
   * summation of prime factors and displaying the prime factors and sum of prime factors
   */
  public static void main( String args[] ) {
    int quotient = 0;
    int quotient1 = 0;
    int j = 0;
    int k = 0;
    for ( int index = 2; index <= 10; index ++ ) {
	    if ( isPrime( index ) )

        // Printing prime no. as itself, its prime factor and sum of prime factor
        System.out.println( "The sum of all primes for " + index + " : "
                            + index + " ( " + index + " ) " ); 
      else {
      j = 0;
      k = 0;
      int sum = 0;
      int i = 0;
      for ( i = 0; i < Primes.length; i++ ) {
        if ( index % Primes[i] != 0 )  //ensures that next steps happen only for divisible prime no.
                 continue;

         // Calculating quotient
         Q[j] = index / Primes[i];

         // Assigning the divisible prime no. to the prime factor array
         Factors[k] = Primes[i];

         // If quotient is prime, adding that to the prime factor array
        if ( isPrime ( Q[j] ) ) {
         Factors[k + 1] = Q[j];

         //Compute sum of prime factors
         sum = Factors[k] + Factors[k+1];

         // Print for those composite numbers which have only two prime factors 
         // since quotient is prime there are no further prime factors
         System.out.println( "The sum of all primes for " + index + " : " 
                             + sum + " ( " + Factors[0] + " + " + Factors[1]+ " ) " );
            break; // breaks further iterations of prime no.s  
        } else {
            if( Q[j] % Primes[i] != 0 ) //ensures that next steps happen only for divisible prime no.
             continue;

             // Since quotient is not prime further dividing it with prime no.
             Q[j + 1] = Q[j] / Primes[i];

             // Assigning the divisible prime no. to the prime factor array
             Factors[k + 1] = Primes[i];

             // If quotient is prime, adding that to the prime factor array
            if ( isPrime ( Q[j + 1] ) ) {
             Factors[k + 2] = Q[j + 1];

             //Compute sum of prime factors
             sum = Factors[k] + Factors[k + 1] + Factors[k + 2];
            
            // Print for those composite numbers which have three prime factors
            System.out.println( "The sum of all primes for " + index + " : " 
                      + sum + " ( " + Factors[0] + " + " + Factors[1]+ " + " + Factors[2] + " ) " );
            }
          
        }
      }
      }
    }
  }
}