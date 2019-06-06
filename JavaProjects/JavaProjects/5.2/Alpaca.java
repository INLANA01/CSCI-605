/**
 * Alpaca.java
 * Child class of Herbivore.
 */

class Alpaca extends Herbivore {

    /**
     * Constructor
     * Setting name of the animal and HomeType
     * @param       name        initialize name of the animal
     */

    public Alpaca(String name) {
        setName(name);
        super.Home = "farm.";
        super.Loc = "Exotic Zone";
    }
}