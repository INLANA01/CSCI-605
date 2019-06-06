/**
 * Carnivore.java
 *
 * This class is extended by the carnivorous animals in the zoo.
 * It contains the method to send all the carnivores to hunt if
 * they are hungry
 *
 */

abstract class Carnivore extends Animal {
    void huntOrGraze() {
        System.out.println(name + " is going to Hunt.");
    }

    // Setting the hunger status here

    Boolean isHungry(){
        return true;
    }
}