/*
 * Zoo.java
 *
 * Version: 2.0
 *
 * Revisions: 0
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */

import java.util.Random;
import java.util.Scanner;

    /**
     * The Animal array is declared and initialized in this class along with the
     * printSpecies, printName, printHomeStatus, printLoc methods
     *
     * The Marine array and Plant array are also declared and initialized along
     * with the method to display their location
     */
    public class Zoo {
        static Animal[] animals = {new Tiger("Katya"), new Lion("Shera"),
                new Giraffe("Zack"), new Gazelle("Bambi"),
                new Skunk("Smelton"), new Aardvark("Saul"),
                new PolarBear("Snowden"), new Ferret("Maddy"),
                new Alpaca("Jessica"), new Camel("Humpty")};

        static Marine[] school = {new fish()};

        static Plant[] flora = {new palmTree()};

        public static void printSpecies(Animal thisOne) {
            System.out.println("I am a " + thisOne.getSpecies());
        }

        public static void printName(Animal thisOne) {
            System.out.println("My name " + thisOne.getName());
        }

        public static void printHomeStatus(Animal thisOne) {
            System.out.println("I am" + (thisOne.amIHome() ? " " : " not ") + "home.");
            if (thisOne.amIHome())
                System.out.println(thisOne.areYouHome());
        }

        public static void printLoc(Animal thisOne) {
            System.out.println("My location is " + thisOne.whereAreYou());
        }

        public static void printLoc1(Marine thisOne) {
            System.out.println("The fish are in the " + thisOne.whereAreYou() + "\n");
        }

        public static void printLoc2(Plant thisOne) {
            System.out.println("The palm tree is in the " + thisOne.whereAreYou() + "\n");
        }

        /**
         * In this main method the serial numbers for each species is generated
         * and the name, species, home status are displayed
         */

        public static void main(String args[]) {

            Class[] classes;
            int SerialIDs[] = new int[animals.length];

            // Generate serial number for the school of gold fish which contains
            // serial ID and ends with number of fish in the school

            String fishID;
            int fishNum = 30;
            Random schoolID = new Random();
            fishID = schoolID.nextInt(10000) + "-" + Integer.toString(fishNum);
            System.out.println("There are " + fishNum + " gold fish in the school ");
            System.out.println("Serial ID of the school of goldfish is " + fishID + "\n");

            String[] names = new String[100];

            for (int index = 0; index < animals.length; index++) {

                // Print name of the animal

                printName(animals[index]);

                // Print species of the animal

                printSpecies(animals[index]);

                // Generate serial numbers for all animals

                Random rand = new Random();
                SerialIDs[index] = rand.nextInt(1000000);
                System.out.println("My Serial ID is : " + SerialIDs[index]);

                // Display whether the animal is at home

                printHomeStatus(animals[index]);

                // Ask the animals to go home

                animals[index].goHome();

                // Display whether the animal has gone home

                printHomeStatus(animals[index]);

                // Display location of the animals

                printLoc(animals[index]);

                names[index] = animals[index].getName();

                // if I am a tiger, giraffe, alpaca ,gazelle, polar bear and
                // ask if I am hungry and send me to hunt or graze (eating)

                if ((animals[index].getSpecies().equals("Giraffe")) ||
                        (animals[index].getSpecies().equals("Alpaca")) ||
                        (animals[index].getSpecies().equals("Gazelle")) ||
                        (animals[index].getSpecies().equals("Tiger")) ||
                        (animals[index].getSpecies().equals("Polar Bear"))) {
                    if (animals[index].isHungry()) {
                        animals[index].huntOrGraze();
                        System.out.println("Yes I am Hungry");
                    }
                }
                System.out.println();
                System.out.println("------------------------------------------");
                System.out.println();
            }

            printLoc1(school[0]);

            printLoc2(flora[0]);

            // Move the animals/plants from one locality to another in the zoo
            // based on user response

            String response = "yes";
            String entity;
            String newLoc;

            move:
            while ( response.equals("yes")) {

            Scanner sc1 = new Scanner(System.in);
            System.out.println("Do you want to move anyone? (yes/no) ");
            response = sc1.next();

            if (response .equals("no")) {
                break move;
            }
            
                System.out.println("Whom do you want to move? ");
                for (int num = 0; num < animals.length; num++) {
                    System.out.println(names[num]);
                }
                System.out.println("Gold fishes" + "\n" + "Palm tree");
                Scanner sc2 = new Scanner(System.in);
                entity = sc2.nextLine(); // entity which is to be moved
                System.out.println("Which zone do you want to move it to? ");
                System.out.println(" Fur Zone ; Exotic Zone ; Wild Zone; " +
                        "Safari Zone; Aqua Zone; Flora Zone ");
                Scanner sc3 = new Scanner(System.in);
                newLoc = sc3.nextLine();
                for (int num = 0; num < animals.length; num++) {
                    if (entity.equals(names[num])) {

                        // If entity is already in the same location then
                        // prompt user for a different location for relocation

                        while (animals[num].Loc .equals(newLoc)) {
                            System.out.println(entity + " is already there!");
                            System.out.println("Choose another location. ");
                            Scanner sc4 = new Scanner(System.in);
                            newLoc = sc4.nextLine();
                        }

                        // Print out new location and success response

                        System.out.println(entity + " has been moved to " + newLoc + "!");
                    }
                }
                System.out.println(" ");
            }
            {
                System.out.println(" Have a great day at our Zoo !");
            }
        }
    }