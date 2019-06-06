
/**
 * Omnivore.java
 *
 * This class is extended by the omnivorous animals in the zoo.
 * It contains the method to send all the omnivores to graze if
 * they are hungry
 *
 */

abstract class Omnivore extends Animal {

    // Setting the hunger status here

    Boolean isHungry(){
        return true;
    }
    void huntOrGraze() {
        System.out.println(name + " is going to Graze.");
    }
}
