/**
 * PolarBear.java
 * Child class of Carnivore.
 */

class PolarBear extends Carnivore {

    /**
     * Constructor
     * Setting name of the animal and HomeType
     * @param       name        initialize name of the animal
     **/

    public PolarBear(String name) {
        setName(name);
        super.Home = "Bi polar Home.";
        super.Loc = "Exotic Zone";
    }
}