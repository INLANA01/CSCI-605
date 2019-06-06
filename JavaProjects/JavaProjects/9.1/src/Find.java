/*
 * Find.java
 *
 * Version: 1.0
 *
 * Revisions: 0
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */

// Terminal : Find --printFile --directory A --printDate --printLength

import java.util.Scanner;
import java.io.File;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

/**
 * NoArgsException is a customized exception created for
 * throwing an exception when the user does not give any
 * arguments for performing on the file/directory
 */

class NoArgsException extends RuntimeException {
    String s;
    public NoArgsException(String s) {

    }
}

/**
 * This class is a simplified version of find command. It
 * implements a parts of the find utility which recursively
 * descends the directory tree for each folder listed
 */

public class Find {

    /**
     * In this method the user given arguments are taken as input
     * using Scanner and the input is parsed and stored into
     * respective array for further use
     */

    public static void main(String[] args) {
        String[] commands = new String[15];
        String[] fileNames = new String[5];
        String pattern;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext("Find")) { // recognize find command
            int commandIndex = 0;
            int fileIndex = 0;
            String dir = "";

            // Split each command so we can store them separately
            String[] eachCommand = scanner.nextLine().split(" ");

            for (int index = 0; index < eachCommand.length; index++) {

                // storing all other commands
                if (Pattern.matches("^--.*", eachCommand[index])) {
                    commands[commandIndex] = eachCommand[index];
                    commandIndex++;

                // storing file name
                } else if (Pattern.matches(".*txt", eachCommand[index])) {
                    fileNames[fileIndex] = eachCommand[index];
                    fileIndex++;

                // if command is neither Find nor starts with --it is directory
                } else if (!eachCommand[index].equals("Find")) {
                    dir = eachCommand[index];
                }
            }
            find(commands, dir, commandIndex); // calling find function
        }
    }

    /**
     * In this method the user given arguments such as print length, print
     * file names, print date of file, print size of file in bytes are
     * performed for each respective case.
     */

    public static void getFiles(String name, boolean fileFlag, boolean fileDate, boolean fileLength) {
        try {
            // Creating a file object with the given input
            File test = new File(name);
            // Checking the validity/presence of the file in the directory
            if (!test.exists()) {
                throw new FileNotFoundException("File Not Found" + test);
            }
            File[] arr2 = test.listFiles(); // listing all files

            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd YYYY HH:mm:ss");

            if (fileFlag && fileDate && fileLength) {

                for (File fileum : arr2) {

                    // list all files if its a file
                    if (fileum.isFile()) {
                        System.out.println(":" + fileum.getParentFile().getName()
                                + ":" + fileum.getName() + " "
                                + sdf.format(fileum.lastModified())
                                + " " + "EDT" + " " + fileum.length());

                        // If it is directory list files within that directory
                    } else if (fileum.isDirectory()) {
                       getFiles(fileum.getAbsolutePath(),true,true,true);
                    }
                }

            } else if (fileFlag && fileDate) {
                for (File fileum : arr2) {
                    if (fileum.isFile()) {
                        System.out.println(":" + fileum.getParentFile().getName()
                                + ":" + fileum.getName() + " " +
                                sdf.format(fileum.lastModified()) + " " + "EDT");
                    } else if (fileum.isDirectory()) {
                        getFiles(fileum.getAbsolutePath(),true,true,false);

                        }
                    }

            } else if (fileFlag && fileLength) {

                for (File fileum : arr2) {
                    if (fileum.isFile()) {
                        System.out.println(":" + fileum.getParentFile().getName()
                         + ":" + fileum.getName() + " " + fileum.length());
                    } else if (fileum.isDirectory()) {
                        getFiles(fileum.getAbsolutePath(),true,false,true);
                    }
                }
             } else if (fileDate && fileLength) {

                for (File fileum : arr2) {
                    if (fileum.isFile()) {
                        System.out.println(sdf.format(fileum.lastModified())
                                + " " + fileum.length());
                    } else if (fileum.isDirectory()) {
                        getFiles(fileum.getAbsolutePath(),false,true,true);

                    }
                }
            } else if (fileFlag) {

                for (File fileum : arr2) {
                    if (fileum.isFile()) {
                        System.out.println( ":" + fileum.getParentFile().getName()
                                + ":" + fileum.getName());
                    } else if (fileum.isDirectory()) {
                        getFiles(fileum.getAbsolutePath(),true,false,false);
                    }
                }
            }
            else if (fileDate) {

                for (File fileum : arr2) {
                    if (fileum.isFile()) {
                        System.out.println(sdf.format(fileum.lastModified()));
                    } else if (fileum.isDirectory()) {
                        getFiles(fileum.getAbsolutePath(),false,true,false);
                    }
                }
            } else if (fileLength) {

                for (File fileum : arr2) {
                    if (fileum.isFile()) {
                        System.out.println(fileum.length());
                    } else if (fileum.isDirectory()) {
                        getFiles(fileum.getAbsolutePath(),false,false,true);
                    }
                }
            }
        } catch(FileNotFoundException fe) {
            System.err.println(fe.getMessage());
        }
    }

    /**
     * In this method the user given commands are converted into flags
     * for each case and passed on to the above method to perform each
     * respective task based on the flags assigned. Exceptions are
     * thrown when there is no arguments/commands given or when the file
     * or directory on which to perform operations is not found
     */

    public static void find(String[] commands, String dir, int commandIndex) {

        int numCommands = 0;
        boolean fileFlag = false;
        boolean fileDate = false;
        boolean fileLength = false;

        try {
            if (commandIndex == 0) {
                throw new NoArgsException("no");
            }
        } catch (NoArgsException no) {
            System.err.println("NoArgsException: No arguments given to perform");
        }

        while (numCommands <= (commandIndex + 1)) {

            try {
                if (commands[numCommands] != null) {
                    if (commands[numCommands].equals("--printFile")) {
                        fileFlag = true;
                    }
                    if ((commands[numCommands].equals("--printLength") ||
                            commands[numCommands].equals("--printLength=true"))) {
                        fileLength = true;
                    }
                    if (commands[numCommands].equals("--printDate")) {
                        fileDate = true;
                    }
                    numCommands++;
                } else if (commands[numCommands] == null) { // end of commands
                    break;
                }
            } catch (Exception e) {
                numCommands++;
            }
        }

        try {
            // Creating a file object with the given input
            File file2 = new File(dir);

            // Checking the validity/presence of the file in the directory
            if (!file2.exists()) {
                throw new FileNotFoundException("File Not Found");
            }

            File[] arr = file2.listFiles(); // listing all files

            if (fileFlag && fileDate && fileLength) {

                getFiles(file2.getAbsolutePath(),true,true,true);

            } else if (fileFlag && fileDate) {

                getFiles(file2.getAbsolutePath(),true,true,false);

            } else if (fileFlag && fileLength) {

                getFiles(file2.getAbsolutePath(),true,false,true);

            } else if (fileDate && fileLength) {

                getFiles(file2.getAbsolutePath(),false,true,true);

            } else if (fileFlag) {

                getFiles(file2.getAbsolutePath(),true,false,false);

            } else if (fileDate) {

                getFiles(file2.getAbsolutePath(),false,true,false);

            } else if (fileLength) {

                getFiles(file2.getAbsolutePath(),false,false,true);

            }
        } catch (FileNotFoundException fe) {
            System.err.println("Please check presence of file/directory");
        }
    }
}
