/*
 * TestMath.java
 *
 * Version: 1.0
 *
 * Revisions: 0
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */

/**
 *
 * The TestMath class defined below contains three methods which perform
 * mathematical tasks : (1) finding the absolute of a number, (2) finding
 * the maximum of two given integers and (3) calculating the square root
 *
 */

public class TestMath {

    /**
     * This method calls the testMethods function
     */

    public static void main( String args[] ) {
        testMethods();

    }

    /**
     * This method prints the absolute value of any number
     *
     * @param a any number in double format for which we
     *          want to arrive at the absolute value
     */

    public static void abs( double a ) {
        if ( a >= 0 ) {

            // printing absolute value of a positive number including zero

            System.out.println("The absolute of " + a + " is : " + a );
        } else {

            // printing absolute value of a negative number

            System.out.println( "The absolute of " + a + " is : " + (-a) );
        }
    }

    /**
     * This method prints the maximum out of two integers
     *
     * @param a any number in integer format for comparing maximum value
     * @param b any number in integer format for comparing maximum value
     *
     */

    public static void max( int a, int b ) {
        if ( a < b ) {

            // printing out larger number if b is greater than a

            System.out.println( "The maximum of " + a +
                                " and " + b + " is : " + b );
        } else if ( a == b ) {

            // printing that both the numbers are equal

            System.out.println( a + " is equal to " + b );
        } else {

            // printing out larger number if a is greater than b

            System.out.println( "The maximum of " + a + " and "
                                + b + " is : " + a );
        }
    }

    /**
     * This method calculates and print the square root of a number
     *
     * @param b any number for which we want to calculate the square root
     *
     */

    public static void sqRoot( double b ) {
        double ROOT = b; // for storing value of square root at each step
        ROOT = ROOT / 2; // approximating square root as ( b / 2 )
        double y = 0; // for storing value of ( number / square root )
        double e = 0.00001;  // e decides the accuracy level

        // calculating square root for the case of a positive number

        if ( b >= 0 ) {
            while ( ROOT - y > e ) {
                ROOT = ( ROOT + y ) / 2;
                y = b / ROOT;

                // print all the approximations of the square root

                System.out.println( "b_n  = " + ROOT );
            }

            // print final value of the square root after
            // achieving the acceptable level of accuracy

            System.out.println( "The square root of " + b +
                                " is : " + ROOT + "\n" );
        } else {

            // for a negative number print that the square root does not exist

            System.out.println( "The square root of " + b +
                                " is : imaginary " + "\n" );
        }
    }

    /**
     * This method contains multiple test cases for the three
     * mathematical functions defined above viz abs, max and sqRoot
     */

    public static void testMethods() {
        System.out.println( "----------------------------------------" );

        // test cases for checking correctness of the abs function

        abs(0 );  // zero
        abs(42 ); // positive number
        abs(-9 ); // negative number

        // test cases for checking correctness of the max function

        System.out.println( "----------------------------------------" );
        max(-2, 1 );  // - +
        max(2, -8 );  // + -
        max(82, 8 );  // + +
        max(-3, -7 ); // - -
        max(11, 11 ); // = =
        max(0, -8 );  // 0 -
        max(0, 8 );   // 0 +

        // test cases for checking correctness of the sqRoot function

        System.out.println( "----------------------------------------" );
        sqRoot(25 ); // perfect square
        sqRoot(57 ); // not a perfect square
        sqRoot(-4 ); // NaN (doesn't exist)
        sqRoot(0 );  // zero
    }
}