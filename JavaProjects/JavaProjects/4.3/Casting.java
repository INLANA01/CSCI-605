/*
 * Casting.java
 *
 * Version: 1.0
 *
 * Revisions: 0
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */

interface B
{
    int a = 0;
    void methodB();

}

interface BB
{
    int aa = 1;
    void methodBB();
}

class A
{
    int a = 2;

    void parentMethod()
    {
        System.out.println("parentMethod");
        System.out.println("a in class A is " + a);
    }
}

class AA extends A implements B, BB
{
    int a = 3;

    void childMethod()
    {
        System.out.println("childMethod");
        System.out.println("a in class AA is " + a);

    }


    public void methodB()
    {
        System.out.println("In methodB");
    }

    public void methodBB()
    {
        System.out.println("In methodBB");
    }
}

public class Casting
{

    public static void main(String[] args)
    {

        // Here we are creating an instance of class A
        A a = new A();

        A b = new AA();


        // Why is this the only method we can call?
        // Because a is an instance of parent class A which does
        // not have access to child methods. Only the vice versa is possible.
        a.parentMethod();

        // Why doesn't this work?
        // Cannot call using class name. Has to be replaced with 'a.a' to be 
        // able to print int value as 2 defined in parent class A.
        // System.out.println(A.a);

        // Here we are creating an instance of class AA
        AA aa = new AA();

        // Class AA doesn't define a parentMethod, how can we call one?
        // Using child object we are calling parent method which it has
        // inherited
        aa.parentMethod();

        // How could we override this method?
        // Using child reference to call int a in child method is
        // overriding int a in parentMethod
        aa.childMethod();

        // Which class does this variable refer to?
        // Child reference calling 'a' so it is referring to
        // int 3 which is defined in child class (AA)
        System.out.println(aa.a);

        // Which class does this variable refer to?
        // This refers to parent class A through casting so it is
        // printing variable int 2 defined in class A.
        System.out.println(((A) aa).a);

        // What forces us to define these methods in the AA class?
        // A class that implements an interface must implements all the
        // methods in interface. Class AA is defining methods of B and BB
        // since it is implementing interfaces B and BB.
        aa.methodB();
        aa.methodBB();

        // Here we are creating an instance of class AA but what is different
        // about this reference?
        // This gives the string representation of the new object which is
        // the class name @ hashcode -> same as a.toString()
        a = new AA();

        // Why do we need to cast this?
        // a is an instance of parent class A and parent cannot be used to
        // call child methods in AA without casting.
        ((AA) a).childMethod();

        // Which class does this variable refer to?
        // a is instance of class A so refers to variable of class A.
        System.out.println(a.a);

        // Which class does this variable refer to?
        // This variable refers to class AA so variable a refers to
        // int 3 defined under class AA.
        System.out.println((((AA)a).a));

        // call methodB and methodBB using the variable a
        // How can we access these variables from the interfaces?
        // Directly access interface variables using interface name
        System.out.println(B.a); // 0
        System.out.println(BB.aa); // 1

        ((AA) b).childMethod();

    }
}