
/*
 * Zoo.java
 *
 * Version: 2.0
 *
 * Revisions: 0
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */

import java.util.Random;
import java.util.Scanner;

/**
 * ZooLife.java
 * This class encompasses all the animals, plants and fish in the zoo
 */

abstract class ZooLife {

    String Loc;

    /**
     * @return      Loc    Location of the being at the Zoo
     */

    String whereAreYou() {
        return Loc;
    }

    /**
     * @return       Species      get Species of the animal
     */

    String getSpecies() {
        return getClass().getName();
    }
}