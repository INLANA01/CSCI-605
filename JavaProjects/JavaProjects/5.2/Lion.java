/**
 * Lion.java
 * Child class of Carnivore.
 */

class Lion extends Carnivore {

    /**
     * Constructor
     * Setting name of the animal and HomeType
     * @param       name        initialize name of the animal
     **/

    public Lion(String name) {
        setName(name);
        super.Home = "Den.";
        super.Loc = "Wild Zone";
    }
}