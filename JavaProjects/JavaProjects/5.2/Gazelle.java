/**
 * Gazelle.java
 * Child class of Herbivore.
 */

class Gazelle extends Herbivore {

    /**
     * Constructor
     * Setting name of the animal and HomeType
     * @param       name        initialize name of the animal
     */

    public Gazelle(String name) {
        setName(name);
        super.Home = "open plain.";
        super.Loc = "Safari Zone";
    }
}