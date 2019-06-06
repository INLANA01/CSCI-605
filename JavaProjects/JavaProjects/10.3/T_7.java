import java.util.*;

public class T_7 extends Thread    {
    static Object o = new Object();
    static int    counter = 0;
    int id;

    public T_7(int id)	{
        this.id = id;
    }

    public void run () {
        if ( ++counter == 1 ) // only once (when first thread reaches here)
            o = new Object(); // new object created when first thread enters this loop
        else
            o = new Object(); // new object created for all other threads
        // solution: remove new object creation to sync on the same object

        synchronized ( o ) { // each thread synced on a different object
            System.err.println(id + " --->" );
            System.err.println(id + " <---" );
        }
    }

    public static void main (String args []) {
        new T_7(1).start();
        new T_7(2).start();
        new T_7(3).start();
    }
}

/*

Mixed output (No sync). E.g.:-

1 --->
3 --->
2 --->
1 <---
3 <---
2 <---

2 --->
3 --->
2 <---
1 --->
1 <---
3 <---

et cetera. (Any order)

This is because every thread going into run method activates the new
object creation and if synchronize block is on object, it will not work
since object keeps changing.

solution: remove new object creation to sync on the same object. 

 */