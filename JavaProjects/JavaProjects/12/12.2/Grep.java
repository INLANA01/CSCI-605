/*
 * Grep.java
 *
 * Version: 2.0
 *
 * Revisions: 0
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */

import java.util.Scanner;
import java.io.*;
import java.util.function.ToIntBiFunction;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;
import java.util.*;

@FunctionalInterface
interface functional_l {
    String bMethod(File aFile);
}

public class Grep {
    static String aString = "";
    static int count=0;
    static int wordCount = 0;
    static String values = "";
    static String FileNames ="";

    /**
     * Actual operation for c command
     * @param regEx regex pattern to match
     * @param aString String to compare
     * @return count
     */
    public static int testc(String regEx, String aString) {
        ToIntBiFunction<String, String> i = (String r, String a) -> {
            if (Pattern.matches(regEx, aString)) {
                count++;
            }
            return count;
        };
        return i.applyAsInt(regEx, aString);
    }

    /**
     * Actual operation for w command
     * @param regEx regex pattern to match
     * @param aString String to compare
     * @param comment comment
     * @return boolean true or false
     */
    public static boolean testw(String regEx, String aString, String comment ) {
        return Pattern.matches(regEx, aString);
    }

    /**
     * Main Function
     * @param args
     */
    public static void main(String[] args) throws IOException {
        String[] commands = new String[15];
        String[] fileNames = new String[5];
        String [] input = new String[5];
        String [] allInput = new String[50];
        String pattern;
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNext("grep")) {

            int commandIndex = 0;
            int fileIndex = 0;
            int inputIndex = 0;

            // grep -w -c one input.txt - input2.txt -  --> array of a[]
            String[] a = scanner.nextLine().split(" ");

            // Runnable r = () -> {
            for(int index=0; index < a.length; index++ ){

                //pattern for command (eg. -l/-w/-c)
                if (Pattern.matches("-[a-zA-Z]", a[index])) {
                    commands[commandIndex] = a[index];
                    commandIndex++;

                    //pattern for input.txt
                } else if (Pattern.matches(".*txt", a[index])) {
                    fileNames[fileIndex] = a[index];
                    fileIndex++;

                    //pattern for - (stdin)
                } else if (Pattern.matches("-", a[index])) {
                    // scanner
                    input[inputIndex] = a[index];
                    inputIndex++;
                }
            }
            // };
            // r.run();
            pattern = a[commandIndex +1];  // word or string
            grep(pattern, commands, fileNames, input);
        }
    }

    /**
     * Functionality of GREP command
     * @param pattern pattern from the user
     * @param commands commands to perform
     * @param fileNames file on which the operation to be performed
     */

    public static void grep(String pattern, String[] commands, String[] fileNames, String[] input) throws IOException {
        int c = 0;
        boolean cFlag = false;
        boolean wFlag = false;
        boolean lFlag = false;
        boolean qFlag = false;

        String w = "";
        String l = "";

        try {
            int fileIndex = 0;
            int inputIndex = 0;
            BufferedReader read;

            while (inputIndex < input.length) {
                if (input[inputIndex] != null) {

                    // read = new BufferedReader(new InputStreamReader(System.in));

                    System.out.println("reading stream ...");
                    Scanner sc = new Scanner(System.in);

                    while ((aString = sc.nextLine()) != null) {
                        if (aString.equals("end"))
                            break;

                        int index = 0;
                        while (index < commands.length) {
                            try {
                                if (commands[index] != null) {
                                    if (commands[index].equals("-w")) {
                                        w = w(pattern); // passed to lambda
                                        wFlag = true;
                                    }
                                    if (commands[index].equals("-c")) {
                                        c = c(pattern, aString); // passed to lambda
                                        cFlag = true;
                                    }
                                    if (commands[index].equals("-l")) {
                                        System.out.println("No file");
                                        lFlag = true;
                                        break;
                                    }
                                    if (commands[index].equals("-q")) {
                                        qFlag = true;
                                        break;
                                    }
                                    index++;
                                } else if (commands[index] == null) {
                                    break;
                                }
                            } catch (Exception e) {
                                index++;
                            }
                        }
                    }

                    if (wFlag && cFlag) {
                        System.out.println(wordCount);
                    } else if (wFlag) {
                        System.out.println(w);
                    } else if (lFlag && cFlag) {
                        System.out.println(fileNames[fileIndex]);
                    } else if (cFlag) {
                        System.out.println(c);
                    } else if (lFlag) {
                        System.out.println(fileNames[fileIndex]);
                    }
                    inputIndex++;
                    if (input[inputIndex] == null) {
                        break;
                    }
                    count = 0;
                    wordCount = 0;
                }
            }

            while(fileIndex < fileNames.length) {

                if (fileNames[fileIndex] != null) {

                    File file = new File(fileNames[fileIndex]);

                    read = new BufferedReader(new FileReader(file));

                    while ((aString = read.readLine()) != null) {

                        int index = 0;
                        while (index < commands.length) {
                            try {
                                if (commands[index] != null) {
                                    if (commands[index].equals("-w")) {
                                        w = w(pattern); // passed to lambda
                                        wFlag = true;
                                    }
                                    if (commands[index].equals("-c")) {
                                        c = c(pattern, aString); // passed to lambda
                                        cFlag = true;
                                    }
                                    if (commands[index].equals("-l")) {
                                        l = l(file).toString(); // passed to lambda
                                        lFlag = true;
                                        break;
                                    }
                                    if (commands[index].equals("-q")) {
                                        qFlag = true;
                                        break;
                                    }
                                    index++;
                                } else if (commands[index] == null) {
                                    w = w(pattern); // passed to lambda
                                    wFlag = true;
                                    break;
                                }
                            } catch (Exception e) {
                                index++;
                            }
                        }
                    }

                    if (wFlag && cFlag) {
                        System.out.println(wordCount);
                    } else if (wFlag) {
                        System.out.println(w);
                    } else if (lFlag && cFlag) {
                        System.out.println(fileNames[fileIndex]);
                    } else if (cFlag) {
                        System.out.println(c);
                    } else if (lFlag) {
                        System.out.println(fileNames[fileIndex]);
                    }
                    fileIndex++;
                }

                if (fileNames[fileIndex] == null) {
                    break;
                }
                count = 0;wordCount=0;
            }

        } catch (FileNotFoundException e) {}


        if (qFlag) {
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                if (scanner.next().equals("$?")) {
                    if (count > 0) {
                        System.out.println(0);
                    } else {
                        System.out.println(1);
                    }
                }
            }
        }
    }

    /***
     * Perform -w operation
     * @param pattern pattern to check
     * @return values
     */
    public static String w(String pattern) {
        if(testw(pattern + "|"+pattern+" .*", aString, "A String" )){
            values += aString + "\n";
            wordCount++;
        }
        return values;
    }

    /**
     * Perform -c operation
     * @param pattern pattern to check
     * @param aString String on which operations are performed
     * @return values
     */
    public static int c(String pattern, String aString) {
        ToIntBiFunction<String, String> i = (String p, String a) -> testc(".*" + pattern + ".*", aString);
        return i.applyAsInt(pattern, aString);
    }

    /**
     * @param file Name of the file
     * @return Name of the file
     */
    public static String l(File file) {
        functional_l i = (File f) -> file.toString();
        return i.bMethod(file);
    }
}

