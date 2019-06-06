
/**
 * Aardvark.java
 * Child class of Omnivore.
 */

class Aardvark extends Omnivore {

    /**
     * Constructor
     * Setting name of the animal and HomeType
     * @param       name        initialize name of the animal
     **/

    public Aardvark(String name) {
        setName(name);
        super.Home = "Hole.";
    }
}
