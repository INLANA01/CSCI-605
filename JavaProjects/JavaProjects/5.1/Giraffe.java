/**
 * Giraffe.java
 * Child class of Herbivore.
 */

class Giraffe extends Herbivore {

    /**
     * Constructor
     * Setting name of the animal and HomeType
     * @param       name        initialize name of the animal
     */

    public Giraffe(String name) {
        setName(name);
        super.Home = "Woods.";
    }
}

