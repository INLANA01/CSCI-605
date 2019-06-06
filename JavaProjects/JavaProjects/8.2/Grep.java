/*
 * Grep.java
 *
 * Version: 1.0
 *
 * Revisions: 0
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */

import java.util.Scanner;
import java.io.File;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;

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
     * @param comment comment
     * @return count
     */
    public static int testc(String regEx, String aString, String comment) {
        if ( Pattern.matches(regEx, aString) )	{
            count++;
        }
        return count;
    }

    /**
     * Actual operation for w command
     * @param regEx regex pattern to match
     * @param aString String to compare
     * @param comment comment
     * @return boolean true or false
     */
    public static boolean testw(String regEx, String aString, String comment ) {
        if ( Pattern.matches(regEx, aString) )	{
            return true;
        }
        return false;
    }

    /**
     * Main Function
     * @param args
     */
    public static void main(String[] args) {
        String[] commands = new String[15];
        String[] fileNames = new String[5];
        String fileName;
        String pattern;
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNext("grep")){
            int commandIndex = 0;
            int fileIndex = 0;
            String[] a = scanner.nextLine().split(" ");
            for(int index=0; index < a.length; index++ ){
                if(Pattern.matches("-.*", a[index])){
                    commands[commandIndex] = a[index];
                    commandIndex++;
                }
                else if(Pattern.matches(".*txt", a[index])){
                    fileNames[fileIndex] = a[index];
                    fileIndex++;
                }
            }
            pattern = a[commandIndex+1];
            grep(pattern, commands, fileNames);
        }

    }

    /**
     * Functionality of GREP command
     * @param pattern pattern from the user
     * @param commands commands to perform
     * @param fileNames file on which the operation to be performed
     */

    public static void grep(String pattern, String[] commands, String[] fileNames ){
        int c = 0;
        boolean cFlag = false;
        boolean wFlag = false;
        boolean lFlag = false;
        boolean qFlag = false;
        boolean c2Flag = false;

        String w = "";
        String l = "";
        int c2 = 0;

        try {
            int fileIndex = 0;
            while(fileIndex < fileNames.length) {
                if (fileNames[fileIndex] != null) {
                    File file = new File( fileNames[fileIndex] );
                    Scanner sc = new Scanner( file );
                    while (sc.hasNextLine()) {
                        aString = sc.nextLine();
                        int index = 0;
                        while (index < commands.length) {
                            try {
                                if (commands[index] != null) {
                                    if (commands[index].equals( "-w" )) {
                                        w = w( pattern );
                                        wFlag = true;
                                    } if (commands[index].equals( "-c" )) {
                                        c = c( pattern, aString);
                                        cFlag = true;
                                    } if (commands[index].equals( "-l" )) {
                                        l = l( file ).toString();
                                        lFlag = true;
                                        break;
                                    } if (commands[index].equals( "-q" )) {
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
                    if (wFlag && cFlag){
                        System.out.println( wordCount );
                    } else
                    if (wFlag) {
                        System.out.println( w );
                    }
                    else if(lFlag && cFlag){
                        System.out.println( fileNames[fileIndex] );

                    }
                    else if (cFlag) {
                        System.out.println( c );
                    }
                    else
                    if (lFlag) {
                        System.out.println( fileNames[fileIndex] );
                    }

                    fileIndex++;
                }if (fileNames[fileIndex] == null) {
                    break;
                }
                count = 0;wordCount=0;

            }
        }catch(FileNotFoundException e){

        }

        if (qFlag) {
            System.out.println( );
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNext()){
                if(scanner.next().equals("$?")){
                    if(count > 0){
                        System.out.println( 0 );
                    }
                    else{
                        System.out.println( 1 );
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
    public static String w(String pattern){
        if(testw(pattern + "|"+pattern+" .*", aString, "A String" )){
            values += aString + "\n";
            wordCount++;
        }
        return values;
    }
    /***
     * Perform -c operation
     * @param pattern pattern to check
     * @param aString String on ehich operatins are performed
     * @return values
     */
    public static int c(String pattern, String aString){
        return testc( ".*"+pattern+".*", aString, "A String");
    }

    /**
     * Perform -c operation
     * @param file Name of he file
     * @return Name of he file
     */
    public static String l(File file){
        FileNames += file.toString();
        return FileNames;
    }
}