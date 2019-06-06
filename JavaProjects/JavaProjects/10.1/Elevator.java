/**
 * Elevator.java
 *
 * Version: 1.0
 *
 * Revisions: 1
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import static java.lang.String.*;

/***
 * Elevator Thread
 */
public class Elevator extends Thread{

    private static int currentFloor = 0;
    private int fromFloor;
    private int toFloor;
    private static String direction = "up";
    private String Name;
    static int count = 0;

    /***
     *
     * @param Name name of the person entering the elvator
     * @param direction direction in which the person wants to go
     * @param fromFloor floor at which the person is standing
     * @param toFloor floor at which the person wants to go
     */
    public Elevator(String Name, String direction, int fromFloor, int toFloor){
        this.Name = Name;
        this.direction = direction;
        this.fromFloor = fromFloor;
        this.toFloor = toFloor;
        count += 1 ;
    }

    /***
     * Run the thread
     */
    public void run() {
        synchronized (direction) {
            System.out.println( "The elevator is currently on Floor No: " + currentFloor  );
            moveToFloor( fromFloor );
            System.out.println( Name + " Enter the elevator" );
            moveToFloor( toFloor );
            System.out.println( Name + " Leave the elevator" );
        }
    }

    /**
     * move to a particular floor from
     * @param toFloor
     */
    private synchronized void moveToFloor(int toFloor) {
        synchronized (valueOf( currentFloor )) {
            if (toFloor != currentFloor) {
                if (toFloor > currentFloor) moveUpwards( toFloor );
                else moveDownwards( toFloor );
            }
        }
    }

    /***
     * take the elevator upwards
     * @param toFloor floor to be reached
     */
    private void moveUpwards(int toFloor) {
        System.out.println( "The elevator is going up" );
        notifyAll();
        while (currentFloor < toFloor) {
            currentFloor += 1;
            System.out.println( currentFloor );
        }
        System.out.println( "The elevator has reached" );
        try {
            wait(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /***
     * take the elevator downwards
     * @param toFloor floor to be reached
     */
    private synchronized void moveDownwards(int toFloor) {
        System.out.println( "The elevator is going Down" );
        notifyAll();
        while (currentFloor > toFloor) {
            currentFloor -= 1;
            System.out.println( currentFloor );
        }
        System.out.println( "The elevator has reached" );
        try {
            wait(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * main function takes input from the user and at a time can allow exactly 3 persons to enter the elevator
     * @param args
     */
    public static void main(String[] args){

        int threads = Runtime.getRuntime().availableProcessors();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newCachedThreadPool(threadFactory);
        String[] Name = new String[5];
        String[] Direction = new String[5];
        int[] fromFloor = new int[5];
        int[] toFloor = new int[5];

        Scanner scanner = new Scanner(System.in);
        try {
//            while (scanner.hasNext()) {
                for(int index = 0; index < 3; index++) {
                    String[] person = scanner.nextLine().split( " " );
                    Name[index] = getName( person );
                    Direction[index] = getDirection( person );
                    fromFloor[index] = getFromFloor( person );
                    toFloor[index] = getToFloor( person );
                }
//            }
            for(int index = 0; index < 3; index++) {
                if (checkIsCorrect( Direction[index], fromFloor[index], toFloor[index] )) {
                    threadPoolExecutor.execute( new Elevator( Name[index], Direction[index],
                            fromFloor[index], toFloor[index] ) );
                } else
                    throw new WrongDirectionOrFloor( "Not in correct Format" );
            }
        }catch (InCorrectDirectionException | WrongDirectionOrFloor | NANException e) {
            e.printStackTrace();
        }
        scanner.close();
    }

    /**
     * check if the direction indicated by the person is correct as per the floor numbers provided
     * @param direction direction the person wants to go
     * @param fromFloor floor at which the person enters the elevator
     * @param toFloor floor at which the person enters the elevator
     * @return boolean value
     * @throws WrongDirectionOrFloor
     */
    private static boolean checkIsCorrect(String direction, int fromFloor, int toFloor) throws WrongDirectionOrFloor{
        if(direction.equalsIgnoreCase( "up" )){
            if(fromFloor > toFloor)
                return false;
        }
        else if(direction.equalsIgnoreCase( "down" )){
            if(toFloor > fromFloor)
                return false;
        }
        return  true;
    }

    /***
     * get the floor number from which the person wants to board.
     * @param person an array in which data for a person is stored
     * @return
     */
    private static String getName(String[] person) {
        String Name = person[0];
        return Name;
    }

    /***
     * get the direction in which the person wants to go
     * @param person an array in which data for a person is stored
     * @return direction
     * @throws InCorrectDirectionException
     */
    private static String getDirection(String[] person) throws InCorrectDirectionException {
        String Direction = person[1];

        if(Direction.equalsIgnoreCase( "up" ) || Direction.equalsIgnoreCase( "down" ))
            return Direction;
        else
            throw new InCorrectDirectionException("Direction can only be either UP or DOWN");
    }

    /***
     * get the floor number from which the person wants to board.
     * @param person an array in which data for a person is stored
     * @return floor number
     * @throws NANException
     */
    private static int getFromFloor(String[] person) throws NANException {
        try{
            return Integer.parseInt( person[2] );
        }catch(NumberFormatException nfe)
        {
            throw new NANException("Value should be numeric and between 0-10");
        }
    }

    /***
     * get the floor number to which the person wants to go.
     * @param person an array in which data for a person is stored
     * @return floor number
     * @throws NANException
     */
    private static int getToFloor(String[] person) throws NANException {
        try {
            return Integer.parseInt( person[3] );
        }
        catch(NumberFormatException nfe) {
            throw new NANException("Value should be numeric and between 0-10");
        }
    }

}

/**
 * Custom Exception for command for direction
 */
class InCorrectDirectionException extends Exception{
    public InCorrectDirectionException(){ }

    public InCorrectDirectionException(String s){
        super(s);
    }
}

/***
 * Not a number Exception
 */
class NANException extends Exception{
    public NANException(){ }

    public NANException(String s){
        super(s);
    }
}

/***
 * Custom Exception for Incorrect Direction
 */
class WrongDirectionOrFloor extends Exception{
    public WrongDirectionOrFloor(){ }

    public WrongDirectionOrFloor(String s){
        super(s);
    }
}