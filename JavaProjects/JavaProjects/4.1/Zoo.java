
/*
 * Zoo.java
 *
 * Version: 1.0
 *
 * Revisions: 0
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */

/*
 * Animal.java
 */
abstract class Animal {

    String name;

    /**
     * Default Constructor
     **/

    public Animal() {
    }

    /**
     * Constructor
     * @param       name        initialize name of the animal
     **/

    public Animal(String name){
        setName(name);
    }

    /**
     * @param       name        actual set name happens here
     **/

    void setName(String name){
        this.name = name;
    }

    /**
     * @return       name        get name of the animal
     **/

    String getName() {
        return name;
    }

    //String Hungry;
    Boolean atHome = true;

    /**
     * @return       Species      get Species of the animal
     **/

    abstract String getSpecies();

    /**
     * @return      atHome        Boolean value (True or False) indicating
     *                            the animal is at home or not
     */

    abstract Boolean amIHome();

    /**
     * @return      Home          Home of the animal eg: Tiger - Den
     */

    abstract String areYouHome();

    /**
     * @return      True/False    return Boolean value if the animal
     *                            is Hungry or not
     */

    abstract Boolean isHungry();

    /**
     * Send the animal to Graze (if Herbivore) or Hunt (if Carnivore)
     */

    abstract void huntOrGraze();

    /**
     * send the animal to home by changing the Boolean value for atHome.
     */

    void goHome() {
        if(this.atHome) {
            this.atHome = false;
        }
        else {
            this.atHome = true;
        }
    }
}

/*
 * Herbivore.java
 */
abstract class Herbivore extends Animal {

    abstract String getSpecies();
    abstract String areYouHome();
    abstract Boolean amIHome();

    void huntOrGraze() {
        System.out.println(name + " is going to Graze");
    }
}

/*
 * Carnivore.java
 */
abstract class Carnivore extends Animal {

    abstract String getSpecies();
    abstract String areYouHome();
    abstract Boolean amIHome();

    void huntOrGraze() {
        System.out.println(name + " is going to Hunt");
    }
}

/*
 * Tiger.java
 */
class Tiger extends Carnivore {

    String name;

    public Tiger () {
    }

    public Tiger(String name) {
        setName(name);
    }

    void setName(String name){
        this.name = name;
    }

    String getName() {
        return name;
    }

    String Species  = "Tiger";
    String Home = "Den";
    Boolean atHome = false;

    void goHome() {
        if(this.atHome) {
            this.atHome = false;
        }
        else {
            this.atHome = true;
        }

    }

    String getSpecies() {
        return Species;
    }

    String areYouHome() {
        return "I am in my " + Home;
    }


    void huntOrGraze() {
        System.out.println(name + " is going to Hunt");
    }

    Boolean amIHome() {
        return atHome;
    }

    Boolean isHungry(){
        return true;
    }

}

/*
 * Lion.java
 */
class Lion extends Carnivore {

    String name;

    public Lion () {
    }

    public Lion(String name) {
        setName(name);
    }

    void setName(String name){
        this.name = name;
    }

    String getName() {
        return name;
    }

    String Species  = "Lion";
    String Home = "Cane";
    Boolean atHome = true;

    String getSpecies() {
        return Species;
    }

    String areYouHome() {
        return "I am in my " + Home;
    }

    Boolean amIHome() {
        return atHome;
    }

    void huntOrGraze() {
        System.out.println(name + " is going to Hunt");
    }

    Boolean isHungry() {
        return true;
    }

    void goHome() {
        if(this.atHome) {
            this.atHome = false;
        }
        else {
            this.atHome = true;
        }
    }

}

/*
 * Giraffe.java
 */
class Giraffe extends Herbivore {

    String name;

    public Giraffe () {
    }

    public Giraffe(String name) {
        setName(name);
    }
    void setName(String name){
        this.name = name;
    }
    String getName() {
        return name;
    }

    String Species  = "Giraffe";
    String Home = "Woods";
    Boolean atHome = false;

    String getSpecies() {
        return Species;
    }

    String areYouHome() {
        return "I am in my " + Home;
    }

    Boolean amIHome() {
        return atHome;
    }

    Boolean isHungry(){
        return false;
    }

    void goHome() {
        if(this.atHome) {
            this.atHome = false;
        }
        else {
            this.atHome = true;
        }
    }

    void huntOrGraze() {
        System.out.println(name + " is going to Graze");
    }
}

/*
 * Gazelle.java
 */
class Gazelle extends Herbivore {

    String name;

    public Gazelle () {
    }

    public Gazelle(String name) {
        setName(name);
    }

    void setName(String name){
        this.name = name;
    }

    String getName() {
        return name;
    }

    String Species  = "Gazelle";
    String Home = "Woods";
    Boolean atHome = false;

    String getSpecies() {
        return Species;
    }


    String areYouHome() {
        return "I am in my " + Home;
    }

    Boolean amIHome() {
        return atHome;
    }
    Boolean isHungry(){
        return true;
    }
    void goHome() {
        if(this.atHome) {
            this.atHome = false;
        }
        else {
            this.atHome = true;
        }
    }
    void huntOrGraze() {
        System.out.println(name + " is going to Graze");
    }
}


/**
 * The Animal array is declared and initialized in this class along with the
 * printSpecies, printName, printHomeStatus methods
 */

public class Zoo {
    static Animal[] animals = {new Tiger("Katya"), new Lion("Shera"),
            new Giraffe("Zack"), new Gazelle("Bambi")};

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


    public static void main(String args[]) {

        Class[] classes;
        for (int index = 0; index < animals.length; index++) {
            printSpecies(animals[index]);
            printName(animals[index]);
            printHomeStatus(animals[index]);
            animals[index].goHome();
            printHomeStatus(animals[index]);

            // if I am a tiger send me to hunt and ask if I am hungry - implement 1
            if (animals[index].getSpecies().equals("Tiger")) {
                if(animals[index].isHungry()) {
                    animals[index].huntOrGraze();
                    System.out.println("Yes I am Hungry");
                }
            }

            // if I am a gazelle send me to graze and ask if I am hungry - implement 2
            if (animals[index].getSpecies().equals("Gazelle")) {
                if(animals[index].isHungry()) {
                    animals[index].huntOrGraze();
                    System.out.println("Yes I am Hungry");
                }
            }

            //Test Scenario
            // if I am a Giraffe send me to graze and ask if I am hungry
            if (animals[index].getSpecies().equals("Giraffe")) {
                if(animals[index].isHungry()) {
                    animals[index].huntOrGraze();
                    System.out.println("Yes I am Hungry");
                }
            }
            System.out.println();
        }
    }
}