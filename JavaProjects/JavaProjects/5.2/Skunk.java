/**
 * Skunk.java
 * Child class of Omnivore.
 */

class Skunk extends Omnivore {

    /**
     * Constructor
     * Setting name of the animal and HomeType
     * @param       name        initialize name of the animal
     **/

    public Skunk(String name) {
        setName(name);
        super.Home = "Burrow.";
        super.Loc = "Fur Zone";
    }
}