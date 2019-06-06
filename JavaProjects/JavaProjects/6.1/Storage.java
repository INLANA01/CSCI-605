/*
 * Storage.java
 *
 * Version: 2.0
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

class Storage<E> {
    /**
     * Test that the Storage class is able to Remove Elements
     **/
    public static boolean testRemove(){
        Storage<String> aStorage = new Storage<String>();
        System.out.println("Test Remove Method");
        System.out.println();
        System.out.println("Add Elements :");
        aStorage.add("A");
        aStorage.element();
        aStorage.add("B");
        aStorage.element();
        aStorage.add("C");
        aStorage.element();
        System.out.println( "Size Before Remove: "+aStorage.size() );
        System.out.println();
        System.out.println( "Remove Elements :" );
        aStorage.remove();
        aStorage.element();
        aStorage.remove();
        aStorage.element();
        aStorage.remove();
        System.out.println( aStorage.element() );
        System.out.println( "Size After Remove: "+aStorage.size() );
        System.out.println( "--------------------------------------------" );
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
        System.out.println( "Test Clear: " );
        System.out.println(  );
        Storage<String> aStorage = new Storage<String>();
        aStorage.add("A");
        aStorage.add("B");
        aStorage.add("C");
        aStorage.add("AR");
        aStorage.add("AE");
        aStorage.add("Z");
        System.out.println( "Elements in the set :" );
        aStorage.element();
        aStorage.clear();
        System.out.println(  );
        System.out.println( "Elements in the set after using the method 'clear()' :" );
        System.out.println( aStorage.element() );
        System.out.println( "--------------------------------------------" );
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
        System.out.println( "Test Add"  );
        System.out.println(  );
        String[] addToStorage = {"A","B","C","AR","AE","Z","B","A"};
        String[] ExpectedStorage = {"A","B","C","AR","AE","Z"};
        System.out.println( "Elements to add : ");
        for(int index=0; index<addToStorage.length; index++){
            System.out.print( addToStorage[index] + "->" );
        }
        System.out.println(  );
        System.out.println(  );
        System.out.println( "Expected Elements in Storage :");
        for(int index=0; index<ExpectedStorage.length; index++){
            System.out.print( ExpectedStorage[index] + "->" );
        }
        System.out.println(  );
        System.out.println(  );
        Storage<String> aStorage = new Storage<String>();
        aStorage.add("A");
        aStorage.add("B");
        aStorage.add("C");
        aStorage.add("AR");
        aStorage.add("AE");
        aStorage.add("Z");
        aStorage.add("B");
        System.out.println("Elements added to the Storage (using add() method) :");
        aStorage.element();
        System.out.println( "--------------------------------------------" );
        if(aStorage.size() == ExpectedStorage.length) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Test that the Storage class use Union Operation
     **/
    public static boolean testAddAll(){
        int sizeA = 0;
        int sizeB = 0;
        int sizeAuB=0;
        System.out.println( "Test Add All" );
        System.out.println();
        Storage<String> SetA = new Storage<String>();
        Storage<String> SetB = new Storage<String>();
        System.out.println( "Set A" );
        SetA.add("A");
        SetA.add("B");
        SetA.element();
        sizeA = SetA.size();
        System.out.println("A Size: " + SetA.size());
        System.out.println();
        System.out.println( "Set B" );
        SetB.add("C");
        SetB.add("D");
        SetB.element();
        sizeB = SetB.size();
        System.out.println("B Size: " + SetB.size());
        System.out.println();
        System.out.println( "Set A after A u B" );
        SetA.addAll(SetB);
        SetA.element();
        sizeAuB =SetA.size();
        System.out.println("A Size: " + SetA.size());
        System.out.println( "--------------------------------------------" );
        //SetA.contains("B");
        if(sizeAuB == (sizeA + sizeB)){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Test that we can check if  Storage class conatains a specific Element
     **/
    public static boolean testContains(){
        System.out.println( "Test Contains" );
        System.out.println();
        Storage<String> aStorage = new Storage<String>();
        aStorage.add("A");
        aStorage.add("B");
        aStorage.add("C");
        aStorage.add("AR");
        aStorage.add("AE");
        System.out.println( "Elements in the set :" );
        aStorage.element();
        System.out.println(  );
        System.out.println( "The Set contains the Element 'A': " + aStorage.contains( "A" ));
        System.out.println( "The Set contains the Element 'Z': " + aStorage.contains( "Z" ));
        System.out.println( "--------------------------------------------" );
        if(aStorage.contains( "A" )){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Test that the Storage class use Intersection Operation
     **/
    public static boolean testRemoveAll(){
        System.out.println( "Test Remove All" );
        System.out.println(  );
        Storage<String> aStorage = new Storage<String>();
        aStorage.add("A");
        aStorage.add("B");
        aStorage.add("C");
        aStorage.add("AR");
        aStorage.add("AE");
        System.out.println("Elements in the Storage are: ");
        aStorage.element();
        String[] toRemove = {"AR","AE"};
        String[] expectedAfterRemoval = {"A","B","C"};
        System.out.println(  );
        System.out.println(  );
        System.out.println( "Elements to Remove are: " );
        for(int index=0; index<toRemove.length; index++){
            System.out.print( toRemove[index] + "->" );
        }
        System.out.println(  );
        System.out.println(  );
        System.out.println( "Expected Storage after Removal (using RemoveAll())"  );
        for(int index=0; index<expectedAfterRemoval.length; index++){
            System.out.print( expectedAfterRemoval[index] + "->" );
        }
        aStorage.removeAll(toRemove);
        System.out.println(  );
        System.out.println(  );
        System.out.println( "Storage after Removal : " );
        aStorage.element();
        System.out.println( "--------------------------------------------" );
        if(aStorage.size() == expectedAfterRemoval.length){
            return true;
        }
        else{
            return false;
        }
    }

    public static void exampleOfHowToUseIt( Storage<String> aStorage)   {
        aStorage = new Storage<String>();
        Storage<String> bStorage = new Storage<String>();
        aStorage.add("a");
        System.out.println("aStorage: " + aStorage );
        bStorage.add("b");
        if ( ! aStorage.addAll(aStorage) )
            System.out.println("You can not add yourself to yourself.");
        aStorage.addAll(bStorage);
        System.out.println("aStorage: " + aStorage );
    }

    /**
     * Test Methood
     * @param aStorage
     */
    public static void test(Storage<String> aStorage)   {
        if ( ! testRemove() )
            System.err.println("testRemove failed");
        if ( ! testContains() )
            System.err.println("testContains failed");
        if ( ! testAdd() )
            System.err.println("testAdd failed");
        if ( ! testAddAll() )
            System.err.println("testAddAll failed");
        if ( ! testClear() )
            System.err.println("testClear failed");
        if ( ! testRemoveAll() )
            System.err.println("testRemove failed");
    }

    public static void main(String args[] ) {
        test(new Storage<String>());
        exampleOfHowToUseIt(new Storage<String>());
    }

    public Node<E> head = null;
    public Node<E> tail = null;

    /**
     * Test that the Storage class is able to Clear rhe storage
     **/
    public boolean add(E e) {
        if(this.head == null){
            this.head = new Node<>(e, null);
            this.tail = this.head;
        }
        else if(! contains( e )){
            addLast(e);
        }
        else{
            return false;
        }
        return true;
    }

    /**
     * Appends the specified element to the end of this list.
     **/
    void add(int index, E element) {
        Node<E> currNode = head;
        Node<E> newNode = new Node<>(element, null);
        if(index ==0){
            currNode=head;
            newNode.next = currNode;
            head = newNode;
        } else {
            while (index >= 0) {
                //PrevNode = CurrNode;
                currNode = currNode.next;
                index--;
            }
            currNode.next = newNode;
        }
    }

    /**
     * Inserts the specified element at the beginning of this list.
     **/
    public void addFirst(E e){
        if(this.head != null) {
            Node<E> currNode = new Node<>( e, null );
            currNode.next = this.head;
            this.head = currNode;
        }
        else{
            this.head = new Node<>(e, null);
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
            currNode = currNode.next;
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
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     * @return all elements in the list
     **/
    public E element() {

        Node<E> newNode = head;
        if(head == null){
            return null;
        }
        while(newNode.next != null) {
            System.out.print(newNode.value + "->");
            newNode = newNode.next;
        }
        System.out.println(newNode.value + "->");
        return newNode.value;
    }

    /**
     * Returns the number of elements in this list.
     * @return count as size
     **/
    public int size() {
        int count =0;
        if(this.head != null ) {
            //count = 1;
            Node<E> currNode = head;
            while ((currNode != null)) { ///was != null ///this.tail
                currNode = currNode.next;
                count++;
            }
        }

        return count;
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     * @return result as Element
     **/

    public E remove() {
        int count = 0;
        E result = null;
        Node<E> currNode = head;
        if(size()==1){
            //this.head = this.tail;
            this.head = null;
            this.tail = null;
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
     * check if the storage conatins a specific element
     * @param e
     * @return flag as true or false
     */
    boolean contains(E e){
        E result = null;
        Node<E> currNode = head;
        while(currNode.next != null){
            if(currNode.value.equals(e)) {
                return true;
            }else{
                currNode=currNode.next;
            }
        }
        return false;
    }

    /**
     * Perform union operation
     * @param bStorage
     * @return flag as true or false
     */
    public boolean addAll(Storage<E> bStorage) {
        Node<E> tempNode = bStorage.head;
//        Node<E> currNode = head;
        if(tempNode == head){
            return false;
        }
        while(tempNode.next != null){
            if(! contains( tempNode.value )){
                add(tempNode.value);
            }
            tempNode = tempNode.next;
        }
        if(! contains( tempNode.value )){
            add(tempNode.value);
        }
        return true;
    }

    /**
     * Remove a specific element from the Storage
     * @param e
     * @return flag as true or false
     */
    boolean remove(E e) {
        boolean flag = false;
        Node<E> currNode = this.head;
        Node<E> prevNode = null;
        if(head == null){
            return false;
        }
        else if(size() == 1){
            if(contains( e )){
                remove();
                return true;
            }
            else{
                return false;
            }
        }
        else{
            while(currNode != null){
                if(currNode.value.equals( e )){
                    prevNode.next = currNode.next;
                    flag = true;
                    if(currNode==this.tail){
                        this.tail=prevNode;
                    }

                }
                    prevNode=currNode;
                    currNode=currNode.next;

            }
        }
    return flag;
    }

    /**
     * To remove all Elements from a given array
      * @param element
     * @return flag as true or false
     */
    public boolean removeAll(E[] element){
     boolean flag = false;
        for(E e:element){
            flag |= remove(e);
        }
        return flag;
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
