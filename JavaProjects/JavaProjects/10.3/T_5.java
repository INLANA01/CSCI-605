import java.util.*;

public class T_5 extends Thread    {
    static Object o = new Object(); // if not static mixed output
    static int    counter = 0;
    int id;

    public T_5(int id)	{
        this.id = id;
    }
    public void run () {
        if ( ++counter == 1 ) // counter is now 1 -- when first thread comes here
            // new obj created whenever below statement is reached but this will
            
            // only be reached the first time (counter is 0 and increments to 1 only once)
        {
        try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
        
            o = new Object();
        }

        synchronized ( o ) { 
            System.err.println(id + " --->" );
            // try {
            //     Thread.sleep(1000);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
            System.err.println(id + " <---" );
        }
    }

    public static void main (String args []) {
        new T_5(1).start();
        new T_5(2).start();
        new T_5(3).start();
    }
}

/*

Synchronized on Object. However, in the run method, when the first time a thread
reaches the if statement, a new object will be created. This changes the instance 
for this thread. For the remaining two threads, the synch will work since they 
will be synchronized on same static object. So the second and third threads
will be synchronized but the first thread will be synchronized on a new object.
We can see this more clearly if we add a 'Thread.sleep()' in between the two
print statements in the synchronized block.

So our possible outputs are :-

1 --->
1 <---
3 --->
3 <---
2 --->
2 <---

OR

2 --->
1 --->
2 <---
1 <---
3 --->
3 <---

OR

1 --->
1 <---
2 --->
2 <---
3 --->
3 <---


or any order of 123 but where second and third threads are synched but
the first one is not synched with the other two.

If we want all the three threads to be synched we should remove
new object creation in run method.

 */