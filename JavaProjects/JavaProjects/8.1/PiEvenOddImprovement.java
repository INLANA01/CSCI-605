/*
 * PiEvenOddImprovement.java
 *
 * Version: 2.0
 *
 * Revisions: 1
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */

import java.io.*;
import java.io.File;
import java.util.zip.GZIPInputStream;

/**
 *
 * EmptyFileException is a customized exception created for
 * throwing an exception when the input file is empty
 *
 */

class EmptyFileException extends RuntimeException {
    String s;
    public EmptyFileException() {
    }
    public EmptyFileException(String s) {
        super(s);
    }
}

/**
 *
 * NoNumbersException is a customized exception created for
 * throwing an exception when the input file does not
 * encounter any numbers
 *
 */

class NoNumbersException extends RuntimeException {
    String s;
    public NoNumbersException() {
    }
    public NoNumbersException(String s) {
        super(s);
    }
}

/**
 *
 * PiEvenOddImprovement calculates the number of even integers
 * and odd integers in a given input which may be a file or
 * standard input. It also given the ratio of odd to even.
 *
 * The performance in terms of speed is good. For processing file
 * pi-billion.txt program takes about 10 - 15 s on an average.
 *
 */

public class PiEvenOddImprovement extends File {

    // Constructor

    public PiEvenOddImprovement() { // default constructor
        super("abc.txt");
    }

    /**
     * In this method we take an input of a user either as a file (in txt
     * format or gzip format) or as a standard input. This method then
     * calculates the even and odd numbers and shows that as output.
     */

    public static void main(String[] args) throws IOException {

        long beginTime = System.nanoTime(); // keep track of time

        BufferedReader br = null;
        DataInputStream dis = null;
        if (args.length == 1) {
            try {
                File f = new File(args[0]);
                String s = args[0];
                String sub = s.substring(Math.max(0, s.length() - 2));
                // System.out.println(sub);

                // Checking if file is a gzip file
                if (sub.equals("gz")) {
                    dis = new DataInputStream(new GZIPInputStream
                            (new FileInputStream(f)));
                } else

                    // for txt file
                    br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException ff) {
                System.err.println(ff.getMessage());
            }

            // standard input case
        } else
            br = new BufferedReader(new InputStreamReader(System.in));

        double countEven = 0; // even number count
        double countOdd = 0; // odd number count
        double countOther = 0; // other characters or symbols count
        double oddByEven = 0; // ratio of odd to even

        // Following code is for all cases where input is not a gzip file

        if (dis == null) { // Not DataInputStream
            try { // IO exception
                int line = br.read(); // char wise reading of input
                if (line == -1) {

                    // EmptyFileException custom created
                    throw new EmptyFileException("File is Empty");
                }
                String a[] = new String[1000];
                int input = 0;
                try { // EmptyFileException
                    for (int index = 0; index < line; index++) {

                        while ((line != -1)) {

                            try { // NumberFormatException
                                a[index] = (char) line + "";  //char wise

                                // break condition for stdin since there is no eof
                                if (a[index].equals("x")) {
                                    break;
                                } else {
                                    input = Integer.parseInt(a[index]); // char to int

                                    if ((input % 2) == 0) {  // checking for even number
                                        //System.out.println(j);
                                        countEven += 1;
                                    } else if ((input % 2) != 0) { // odd number
                                        //System.out.println(j);
                                        countOdd += 1;
                                    }
                                }
                            } catch (NumberFormatException ne) { // if input is string
                                input = 0; // ignore string and move on
                                countOther += 1;
                            }
                            line = br.read();
                        }
                    }
                    try { // Arithmetic Exception
                        oddByEven = countOdd / countEven;
                        if (countOdd == 0) {
                            throw new ArithmeticException("div by 0");
                        }
                    } catch (ArithmeticException ae) {
                        System.err.println("odd/even = " + "NaN");
                    }

                    // Required output is printed here

                    System.out.println("even = " + (int) countEven);
                    System.out.println("odd = " + (int) countOdd);
                    System.out.println("odd/even = " + oddByEven);

                    try { // NoNumbersException
                        if ((countEven + countOdd) == 0) {

                            //NoNumbersException custom created
                            throw new NoNumbersException("No Numbers in file");
                        }
                    } catch (NoNumbersException nn) {
                        System.err.println(nn);
                        System.err.println(nn.getMessage());
                    }
                } catch (EmptyFileException ee) {
                    System.err.println(ee);
                    System.err.println(ee.getMessage());
                }
            } catch (IOException io) {
                System.err.println(io.getMessage());
            }
            catch (NullPointerException np) {
                System.err.println("Please check input file path/existence!");
            }

            try { // IO Exception
                br.close();
            } catch (IOException io) {
                System.err.println(io.getMessage());
            }
            catch (NullPointerException np) {
                System.err.println("Please check input file path/existence!");
            }
        }

        else { // This means input is a gzip file

            try { // IO exception
                int line = dis.read(); //char wise
                if (line == -1) {

                    // EmptyFileException custom created
                    throw new EmptyFileException("File is Empty");
                }
                String a[] = new String[1000];
                int input = 0;
                try { // EmptyFileException
                    for (int index = 0; index < line; index++) {

                        while ((line != -1)) {
                            //String[] separate = line.split(" ");
                            try { // NumberFormatException
                                a[index] = (char) line + "";  // char wise

                                // break condition for stdin
                                if (a[index].equals("x")) {
                                    break;
                                } else {
                                    input = Integer.parseInt(a[index]); // char to int

                                    if ((input % 2) == 0) {  // checking for even number
                                        countEven += 1;

                                    } else if ((input % 2) != 0) { // odd number
                                        countOdd += 1;
                                    }
                                }
                            } catch (NumberFormatException ne) { // if input is string
                                input = 0; // ignore string and move on
                                countOther += 1;
                            }
                            line = dis.read(); // keep reading
                        }
                    }
                    try { // Arithmetic Exception
                        oddByEven = countOdd / countEven;

                        // If odd is zero, handle arithmetic exception

                        if (countOdd == 0) {
                            throw new ArithmeticException("div by 0");
                        }
                    } catch (ArithmeticException ae) {
                        System.err.println("odd/even = " + "NaN");
                    }

                    // Required output is printed here

                    System.out.println("even = " + (int) countEven);
                    System.out.println("odd = " + (int) countOdd);
                    System.out.println("odd/even = " + oddByEven);

                    try { // NoNumbersException
                        if ((countEven + countOdd) == 0) {

                            //NoNumbersException custom created
                            throw new NoNumbersException("No Numbers in file");
                        }
                    } catch (NoNumbersException nn) {
                        System.err.println(nn);
                        System.err.println(nn.getMessage());
                    }
                } catch (EmptyFileException ee) {
                    System.err.println(ee);
                    System.err.println(ee.getMessage());
                }
            } catch (IOException io) {
                System.err.println(io.getMessage());
            }

            try { // IO Exception
                dis.close();
            } catch (IOException io) {
                System.err.println(io.getMessage());
            }
        }

        long endTime = System.nanoTime(); // end track of time

        //print time in nanoseconds
        System.out.println("Time taken was: "+(endTime - beginTime) + "ns");

        //print time in seconds for better comprehension
        System.out.println("Time taken was: "+(endTime - beginTime)/1000000000 + "s");
    }
}