/**
 * FastSort.java
 *
 * Version: 2.0
 *
 * Revisions: 0
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 **/

import java.util.Scanner;

/**
 *   Storage Interface
 **/
interface StorageI<E>  {

    public boolean add(E e);    // 2
    public E get();
    public void clear();        // 2 3
    public boolean contains(E e);
    public boolean isEmpty();
    public void sort();     // 3
    public int size();      // 2 3
    public String getClassName();
    public void display();

}
/**
 * Sorts the Array with complexity O(1)
 **/
public class FastSort{
    public static void main(String[] args) {
        String response = "";
        StorageI<String> array = new ArrayL<String>(3);
        StorageI<String> brray = new ArrayL<String>(1);
        array.add("a");
        array.add("A");
        array.add("B");
        array.add("r");
        array.add("t");
        array.add("3");
        array.add("6");
        array.add("d");
        array.add("a7a");
        array.add("y");
        array.add("i");
        array.add("tejas");
        array.add("tanvi");
        array.add("4");
        array.add("9");
        array.add("3");
        array.add("as");
        array.add("ert");
        brray.add("757");
        array.sort();
        System.out.println("A get: ");
        System.out.println(array.get());

        // User interaction captured in below code

        loop:
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("Choose an operation (type the number) ");
            System.out.println("1. Add ");
            System.out.println("2. Contains ");
            System.out.println("3. Clear ");
            System.out.println("4. Get ");
            System.out.println("5. isEmpty ");
            System.out.println("6. Sort ");
            System.out.println("7. Size ");
            System.out.println("8. Get ClassName ");
            System.out.println("Type 'exit' to exit ");
            response = input.next();

            if (response.equals("exit")) {
                break loop;

            } else if (response.equals("1")) {

                // Add an element to the storage
                {
                    Scanner input1 = new Scanner(System.in);
                    System.out.println("Add an elem : ");
                    response = input1.next();
                    array.add(response);
                    System.out.println(array.get());
                    System.out.println("Element has been added to the storage ");
                }

            } else if (response.equals("2")) {

                // Check if the storage contains an element

                Scanner input2 = new Scanner(System.in);
                System.out.println("Check if storage contains an elem : ");
                response = input2.next();
                if (array.contains(response)) {
                    System.out.println("The storage contains the element");
                } else {
                    System.out.println("The storage does not contain the element");
                }

            } else if (response.equals("3")) {

                // Clear the array

                Scanner input3 = new Scanner(System.in);
                System.out.println("Clear the array? (y/n) ");
                response = input3.next();
                if (response.equals("y")) {
                    array.clear();
                    System.out.println(array.get());
                    System.out.println("The storage is clear");
                } else {
                    System.out.println(array.get());
                }

            } else if (response.equals("4")) {

                // Get elements of the Storage

                Scanner input4 = new Scanner(System.in);
                System.out.println("Get elements? (y/n) ");
                response = input4.nextLine();
                if (response.equals("y")) {
                    System.out.println(array.get());
                }

            } else if (response.equals("5")) {

                // Check if Storage is empty

                Scanner input5 = new Scanner(System.in);
                System.out.println("Check if storage is empty? (y/n) ");
                response = input5.next();
                if (response.equals("y")) {
                    System.out.println(array.isEmpty());
                }

            } else if (response.equals("6")) {

                // Sort the storage

                Scanner input6 = new Scanner(System.in);
                System.out.println("Sort the storage? (y/n) ");
                response = input6.next();
                if (response.equals("y")) {
                    array.sort();
                    System.out.println(array.get());
                    System.out.println("The storage is already sorted");
                } else {
                    System.out.println(array.get());
                }

            } else if (response.equals("7")) {

                // Get size of the storage

                Scanner input7 = new Scanner(System.in);
                System.out.println("Get size of the storage? (y/n) ");
                response = input7.next();
                if (response.equals("y")) {
                    System.out.println(array.size());
                }

            } else if (response.equals("8")) {

                // Get class name of the storage

                Scanner input8 = new Scanner(System.in);
                System.out.println("Get class name? (y/n) ");
                response = input8.next();
                if (response.equals("y")) {
                    System.out.println(array.getClassName());
                }
            }
        } while (!response.equals("exit"));
    }
}

class ArrayL<E> implements StorageI<E> {
    Object[] Array1;
    int size = 0;
    int[] id;
    /**
     * Default constructor to create an object of length 10
     **/
    ArrayL() {
        this.Array1 = new Object[10];
        this.size = 0;
        this.id = new int[10];
    }
    
    /**
     * Parametrised constructor to create and object of customised length
     */
    
    ArrayL(int length) {
        this.Array1 = new Object[length];
        this.size = 0;
        this.id = new int[10];
    }

    /**
     * Check Storage size, and increase size if it exceeds the size of the Storage.
     */
    
    public void checkArraySize() {
        if (this.size >= size()) {
            Object[] newArray = new Object[this.Array1.length * 2];
            System.arraycopy( this.Array1, 0, newArray, 0, Array1.length );
            this.Array1 = newArray;
            int[] newId = new int[this.id.length * 2];
            System.arraycopy( this.id, 0, newId, 0, id.length );
            this.id = newId;
        }
    }

    /**
     * Add new element to the Storage
     */
    
    public boolean add(E e) {
        if (size() == 0) {
            String temp = e.toString();
            this.id[size] = temp.toLowerCase().hashCode();
            //(int) temp.charAt( 0 );
            this.Array1[size] = e;
            size++;
            return true;
        }
        else {
            String temp = e.toString();
            int newId = temp.toLowerCase().hashCode();
            int index = 0;
            while (index < size()) { 
                if(newId < id[index]) {
                    System.arraycopy( this.Array1, index, this.Array1,
                            index + 1, Array1.length-(index+1) );
                    System.arraycopy( this.id, index, this.id,
                            index + 1, id.length-(index+1) );
                    break;
                }
                index++;
            }
            checkArraySize();
            this.Array1[index] = e;
            this.id[index] = newId;
            size++;
            return true;
        }
    }
    
    /**
     * Provide size of the Storage
     */
    public int size(){
        return this.size;
    }
    
    /**
     * Empty the Storage
     */
    public void clear(){
        for( size(); size>0; size-- )
            this.Array1[size] = "";
        this.id[size] = 0;
    }

    /**
     * Display the Storage
     */

    public void display(){
        for(int i=0; i<this.size; i++) {
            System.out.println(this.Array1[i]);
        }
    }
    
    /**
     * Get values in the Storage
     */
    
    public E get(){
        String values="";
        for(int index = 0; index < size(); index++ ){
            values += Array1[index] + " ";
        }
        E result = (E)values;
        return result;

    }
    
    /** 
     * Check if the Storage contins the specific element
     */
    
    public boolean contains(E e){
        for (int index = 0; index < size(); index++) {
            if(Array1[index].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the Storage is Empty
     **/
    
    public boolean isEmpty(){
        if(size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * There is no actual need to Sort the Storage since elements
     * are inserted in sorted order
     */
    
    public void sort()      // 3
    {
        int temp;
        String temp2;
        for(int i = 0; i < size(); i++) {
            for(int j = 0; j < size(); j++) {
                if(id[i] < id[j]){
                    temp = id[i];
                    id[i] = id[j];
                    id[j] = temp;
                    temp2 = (String)Array1[i];
                    Array1[i] = Array1[j];
                    Array1[j] = (Object)temp2;
                }
            }
        }
    }
    
    /**
     * Get name of the class
     */
    
    public String getClassName(){
        return getClass().toString();
    }

}