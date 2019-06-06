/**
 * Herbivore.java
 *
 * This class is extended by the herbivorous animals in the zoo.
 * It contains the method to send all the herbivores to graze if
 * they are hungry
 *
 */

abstract class Herbivore extends Animal {
    void huntOrGraze() {
        System.out.println(name + " is going to Graze.");
    }

    // Setting the hunger status here

    Boolean isHungry(){
        return true;
    }
}