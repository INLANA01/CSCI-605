import java.util.*;

public class T_6 extends Thread    {
    String o = " ";
    int id;

    public T_6(int id)	{
        this.id = id;
    }

    public void run () {
        synchronized ( o ) { // All threads synced on same String object
            System.err.println(id + " --->" );
            System.err.println(id + " <---" );
        }
    }

    public static void main (String args []) {
        new T_6(1).start();
        new T_6(2).start();
        new T_6(3).start();
    }
}

/*
1 --->
1 <---
3 --->
3 <---
2 --->
2 <---

1 --->
1 <---
2 --->
2 <---
3 --->
3 <---

or any order of (11-22-33) depending on which thread starts first based on
thread scheduler

 */