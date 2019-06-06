
/**
 * Tiger.java
 * Child class of Carnivore.
 */

class Tiger extends Carnivore {
    /**
     * Constructor
     * Setting name of the animal and HomeType
     * @param       name        initialize name of the animal
     **/
    public Tiger(String name) {
        setName(name);
        super.Home = "forest.";
    }
}

