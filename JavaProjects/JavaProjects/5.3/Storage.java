/*
 * Storage.java
 *
 * Version: 1.0
 *
 * Revisions: 0
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */

/*
 * Storage<E>.java
 */
public class Storage<E> {
    /**
     * Test that the Storage class is able to add a value for particular indexes
     *
     **/
    public static boolean testAddIndex(){
        Storage<String> aStorage = new Storage<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.remove(0);
        if(aStorage.size() == 0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Test that the Storage class is able to remove a value for particular indexes
     **/
    public static boolean testRemoveIndex(){
        Storage<String> aStorage = new Storage<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.remove(0);
        if(aStorage.size() == 0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Test that the Storage class is able to Clear the storage
     **/
    public static boolean testClear(){
        Storage<String> aStorage = new Storage<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        aStorage.clear();
        if(aStorage.size() == 0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Test that the Storage class is able to Add to the storage
     **/
    public static boolean testAdd()     {
        Storage<String> aStorage = new Storage<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        for ( int index = 0; index < theStrings.length; index ++ )
            if(aStorage.size() == 0) {
                rValue &= aStorage.remove() == null;
            }
            else{
                rValue &= aStorage.remove().equals( theStrings[index] );
            }
        rValue &= aStorage.remove() == null;
        aStorage.add("c");
        return rValue;
    }
    public static void exampleOfHowToUseIt( Storage<String> aStorage)   {
        aStorage.add("a");
        aStorage.add("b");
        aStorage.add("c");
        aStorage.add(0, "0"); //How int??
        aStorage.add(aStorage.size(), "1");
        aStorage.add(aStorage.size() + 1, "2");
        System.out.println("aStorage: ");
        System.out.println(aStorage.element());
    }
    public static void test(Storage<String> aStorage)   {
        if ( ! testAdd() )
            System.err.println("testAdd failed");
        if ( ! testRemoveIndex() )
            System.err.println("testRemoveIndex failed");
        if ( ! testAddIndex() )
            System.err.println("testAddIndex failed");
        if ( ! testClear() )
            System.err.println("testClear failed");
    }
    public static void main(String args[] )     {
        test(new Storage<String>());
        exampleOfHowToUseIt(new Storage<String>());

    }
    public Node<E> head = null;
    public Node<E> tail = null;

    /**
     * Test that the Storage class is able to Clear rhe storage
     * ADD TO THE STORAGE
     **/
    public boolean add(E e) {
        if(this.head == null){
            this.head = new Node<>(e, null);
            this.tail = this.head; //ONE ELEMENT
        }
        else{
            addLast(e);
        }
        return true;
    }

    /**
     * Appends the specified element to the end of this list.
     * AT AN INDEX
     *
     **/
    void add(int index, E element) {
        Node<E> currNode = head;
        Node<E> newNode = new Node<>(element, null);
        if(index ==0){
            currNode=head;
            newNode.next = currNode;
            head = newNode;
        }else {
            while (index >= 0) {
                //PrevNode = CurrNode;
                currNode = currNode.next;
                index--; //ADDING AT AN INDEX IN BETWEEN
            }
            currNode.next = newNode;
        }

    }

    /**
     * Inserts the specified element at the beginning of this list.
     * ADD FIRST
     **/
    public void addFirst(E e){
        if(this.head != null) {
            Node<E> currNode = new Node<>( e, null );
            currNode.next = this.head;
            this.head = currNode;
        }
        else{
            this.head = new Node<>(e, null); //REASSIGN HEAD TO NNEW
            this.tail = this.head;
        }
    }

    /**
     * Appends the specified element to the end of this list.
     **/
    public void addLast(E e){
        Node<E> currNode = head;
        // loop until we find the end of the list
        while ((currNode.next != null)) {
            currNode = currNode.next; //LAST NODE LINK TO NEW NODE AND MAKE NEW NODE TAIL
        }
        // set the new node to the Object x, next will be null.
        currNode.next = new Node<>(e, null);
        this.tail = currNode;
    }

    /**
     * Removes all of the elements from this list.
     **/
    public void clear() {
        head = null;
    } //POINT NOWHERE

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     **/
    public E element() {
        Node<E> newNode = head;
        while(newNode.next != null) {
            System.out.print(newNode.value + "->");
            newNode = newNode.next;
        }
        return newNode.value;
    }

    /**
     * Returns the number of elements in this list.
     * SIZEEE
     **/
    public int size() {
        int count =0;
        if(this.head != null ) {
            //count = 1;
            Node<E> currNode = head;
            while ((currNode != this.tail)) { ///was != null
                currNode = currNode.next;
                //System.out.println(currNode.value);
                count++;
            }
        }
        //System.out.println(count);
        return count;
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     **/
    public E remove() {
        int count = 0;
        E result = null;
        Node<E> currNode = head;
        if(size()==1){
            this.head = this.tail;
        }
        else if(size()==0){
            return null;
        }
        else {
            result = head.value;
            head = head.next;
            return result;
        }
        result = currNode.value;
        currNode=null;
        return result;
    }

    /**
     * Removes the element at the specified position in this list.
     * REMOVE LINKS AND POINT BEFORE AND AFTER TO EACH OTHER
     **/
    E remove(int index) {
        Node<E> currNode = head;
        Node<E> nextNode = head;

        if(index == 0){
            currNode=head;
            head = currNode.next;
            nextNode = currNode;
        }else {
            nextNode = nextNode.next;
            while (index > 1) {
                currNode = currNode.next;
                nextNode = nextNode.next;
                index--;
            }
            currNode.next = nextNode.next;
        }
        E result = currNode.value;
        return result;
    }
}

/*
 * Storage<E>.java
 */
class Node<E> {
    public E value;
    public Node<E> next;

    public Node (E value, Node<E> next) {
        this.value = value;
        this.next = next;
    }
}