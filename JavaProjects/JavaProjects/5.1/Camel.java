/**
 * Camel.java
 * Child class of Herbivore.
 */

class Camel extends Herbivore {

    /**
     * Constructor
     * Setting name of the animal and HomeType
     * @param       name        initialize name of the animal
     */

    public Camel(String name) {
        setName(name);
        super.Home = "Desert.";
    }
}