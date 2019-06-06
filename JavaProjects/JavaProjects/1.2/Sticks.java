/** 
 * Sticks.java 
 * 
 * Version:   Java 10
 *     
 * Revisions: 1     
 */

/** 
 * Declaring and allotting values to the arrays for given stick lengths 
 * and unknown stick lengths
 */
public class Sticks {
    static int[] stickLengths = { 1, 5, 8, 12, 12, 35, 35, 35, 61 };
    static int[] unknowStickLengths = { 1, 6, 9, 24, 110, 111, 115, 
                                        62, 202, 203, 204, 205};

/** 
 * For traversing through the unknown stick lengths and printing them
 */
    public static void main( String[] arguments ) {
        for ( int index = 0; index < unknowStickLengths.length; index++ ) {
            System.out.print( unknowStickLengths[index] + " inch:" + "\t" );
            doTestLength( unknowStickLengths[index] );
        }
    }

/** 
 * For calculating the sum to find a combination from the given
 * stick lengths and printing the same
 */
    public static void doTestLength( int n ) {
        int sum;
        int i;
        int[] sticksUsed = new int[stickLengths.length];
        String output="";

        // Loop added as Greedy approach failed for a few test cases.
        for ( int iv = stickLengths.length - 1; iv > 0; iv-- ) {                         
            sum = 0;
            i = 0;
            System.out.print(" ");

            // Greedy Approach for getting the solution.
            for ( int index = iv; index >= 0; index-- ) {                          
                if ( stickLengths[index] <= n ) {
                    if ( sum > n ) {
                        break;
                    } else {
                      if ( sum + stickLengths[index] > n ) {
                        continue;
                      } else {

                        // Add the Sticks sizes used in array sticksUsed to print it later.
                        sticksUsed[i] = stickLengths[index];                
                        i++;
                        sum = sum + stickLengths[index];
                      }

                    }
                }
            }

            if ( sum == n ) {
                output = "yes";

                //Print Output if Output is "yes", i.e., if sum is achieved.
                System.out.print( output + ";\t" + "used stickLengths = " );      
                for ( int j = 0; j < sticksUsed.length - 1; j++ ) {
                    if ( sticksUsed[j] == 0 ) {
                        continue;
                    } else {

                      //Print the Stick sizes used for the current unknown Stick Length.
                      System.out.print( sticksUsed[j] + " inch " );             
                    }
                }
                System.out.println();

                //Get out of the loop (and method) as soon as you get the Output as "yes".
                break;                                                          
            } else {

              //Keep iterating till the loop ends if your Output is "no".
              output = "no";                                                  
            }
        }
        if ( output == "no" ) {

            //Print "no" if there is no combination available for current Stick length.
            System.out.println( output + ";" );                                   
        }
    }
}