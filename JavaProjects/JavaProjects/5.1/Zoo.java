/**
 * The Animal array is declared and initialized in this class along with the
 * printSpecies, printName, printHomeStatus methods.
 */

class Zoo {
    static Animal[] animals = {new Tiger("Katya"), new Lion("Shera"),
            new Giraffe("Zack"), new Gazelle("Bambi"),
            new Skunk("Smelton"), new Aardvark("Saul"),
            new PolarBear("Snowden"), new Ferret("Maddy"),
            new Alpaca("Jessica"), new Camel("Humpty")};

    public static void printSpecies(Animal thisOne) {
        System.out.println("I am a " + thisOne.getSpecies());
    }

    public static void printName(Animal thisOne) {
        System.out.println("My name " + thisOne.getName());
    }

    public static void printHomeStatus(Animal thisOne) {
        System.out.println("I am" + (thisOne.amIHome() ? " " : " not ") + "home now.");
        if (thisOne.amIHome())
            System.out.println(thisOne.areYouHome());
    }

    /**
     * In this method the printSpecies, printName and printHomeStatus methods
     * are called and these characteristics are displayed of each animal
     * in the animals array.
     */

    public static void main(String args[]) {

        Class[] classes;
        for (int index = 0; index < animals.length; index++) {
            printSpecies(animals[index]);
            printName(animals[index]);
            printHomeStatus(animals[index]);
            animals[index].goHome();
            printHomeStatus(animals[index]);

            // if I am a tiger, giraffe, alpaca ,gazelle, polar bear and
            // ask if I am hungry and send me to hunt or graze

            if ((animals[index].getSpecies().equals("Giraffe")) ||
                    (animals[index].getSpecies().equals("Alpaca")) ||
                    (animals[index].getSpecies().equals("Gazelle")) ||
                    (animals[index].getSpecies().equals("Tiger")) ||
                    (animals[index].getSpecies().equals("PolarBear"))){
                if  (animals[index].isHungry()) {
                    animals[index].huntOrGraze();
                    System.out.println("Yes I am Hungry.");
                }
            }
            System.out.println();
        }
    }
}