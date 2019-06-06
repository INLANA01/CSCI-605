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

/**
 *
 * The abstract class Animal is the parent class for all the
 * animals in the zoo. This class contains the methods for setting
 * name, species, homes, hunger status and food activity.
 *
 */

abstract class Animal {

    String name;
    String Home;

    /**
     * Default Constructor
     */

    public Animal() {
    }

    /**
     * Constructor
     * @param       name        initialize name of the animal
     */

    public Animal(String name){
        setName(name);
    }

    /**
     * @param       name        actual set name happens here
     */

    void setName(String name){
        this.name = name;
    }

    /**
     * @return       name        get name of the animal
     */

    String getName() {
        return name;
    }

    Boolean atHome = true;

    /**
     * @return       Species      get Species of the animal
     */

    String getSpecies() {
        return getClass().getName();
    }

    /**
     * @return      atHome        Boolean value (True or False) indicating
     *                            the animal is at home or not
     */

    Boolean amIHome() {
        return atHome;
    }

    /**
     * @return      Home          Home of the animal eg: Tiger - Den
     */

    String areYouHome() {
        return "I am in my " + Home;
    }

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