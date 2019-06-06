/**
 * Ferret.java
 * Child class of Carnivore.
 */

class Ferret extends Carnivore {

    /**
     * Constructor
     * Setting name of the animal and HomeType
     * @param       name        initialize name of the animal
     */

    public Ferret (String name) {
        setName(name);
        super.Home = "tunnel.";
        super.Loc = "Fur Zone";
    }
}