import java.util.Random;
import java.util.*;

/*
 * RITBank.java
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
 * BankAccount.java
 */
abstract class BankAccount {
    String Name;
    String AccountNumber;
    String AccountType;
    String BalanceAmount;
    /**
     * Default Constructor
     **/
    public BankAccount() {
    }

    /**
     * Default Constructor
     * @param       AccountNumber        set AccountNumber of the BankAccount
     **/
    public BankAccount(String AccountNumber){
        setAccountNumber(AccountNumber);
        setAccountType(AccountType);
        setBalanceAmount(BalanceAmount);
    }

    /**
     * Default Constructor
     * @param       AccountNumber        set AccountNumber of the BankAccount
     **/
    public BankAccount(String Name, String AccountNumber, String AccountType, String BalanceAmount){
        setName(Name);
        setAccountNumber(AccountNumber);
        setAccountType(AccountType);
        setBalanceAmount(BalanceAmount);
    }

    /**
     * Default Constructor
     /* @param       AccountNumber        actual set AccountNumber happens here
     **/
    void setName(String Name){
        this.Name = Name;
    }

    String getName(){
        return Name;
    }

    /**
     * Default Constructor
     * @return       AccountNumber        get AccountNumber of the BankAccount
     **/
    String getAccountNumber() {
        return AccountNumber;
    }

    /**
     * Default Constructor
     * @param       AccountNumber        actual set AccountNumber happens here
     **/
    void setAccountNumber(String AccountNumber){
        this.AccountNumber = AccountNumber;
    }

    /**
     * Default Constructor
     * @param       AccountType        actual set AccountNumber happens here
     **/
    void setAccountType(String AccountType){
        this.AccountType = AccountType;
    }
    /**
     * Default Constructor
     * @param       BalanceAmount        actual set AccountNumber happens here
     **/
    void setBalanceAmount(String BalanceAmount){
        this.BalanceAmount = BalanceAmount;
    }

    abstract String getBalanceAmount();
    /**
     * @return      atHome      Boolean value (True or False) indicating the BankAccount is at home or not
     */
    /**
     * Default Constructor
     * @return       AccountType        get AccountType of the BankAccount
     **/
    abstract String getAccountType();

    /**
     * @return      atHome      Boolean value (True or False) indicating the BankAccount is at home or not
     */
}

/*
 * AssetAccount.java
 */
abstract class AssetAccount extends BankAccount {

    abstract String getAccountType();
}

/*
 * LiabilityAccount.java
 */
abstract class LiabilityAccount extends BankAccount {

    abstract String getAccountType();
}

/*
 * Credit.java
 */
class Credit extends LiabilityAccount {

    String AccountNumber;
    String AccountType;
    String BalanceAmount;

    public Credit () {
    }

    public Credit(String AccountNumber) {
        setAccountNumber(AccountNumber);
    }
    /**
     * Default Constructor
     * @param       AccountNumber        set AccountNumber of the BankAccount
     **/
    public Credit(String Name, String AccountNumber, String AccountType, String BalanceAmount){
        setName(Name);
        setAccountNumber(AccountNumber);
        setAccountType(AccountType);
        setBalanceAmount(BalanceAmount);
    }

    String getAccountNumber() {
        return AccountNumber;
    }
    String getBalanceAmount() {
        return BalanceAmount;
    }

    String getName() {
        return Name;
    }

    void setName(String Name) {
        this.Name = Name;
    }

        /**
     * Default Constructor
     * @param       AccountNumber        actual set AccountNumber happens here
     **/
    void setAccountNumber(String AccountNumber){
        this.AccountNumber = AccountNumber;
    }

    /**
     * Default Constructor
     * @param       AccountType        actual set AccountNumber happens here
     **/
    void setAccountType(String AccountType){
        this.AccountType = AccountType;
    }
    /**
     * Default Constructor
     * @param       BalanceAmount        actual set AccountNumber happens here
     **/
    void setBalanceAmount(String BalanceAmount){
        this.BalanceAmount = BalanceAmount;
    }

    //String AccountType  = "Credit"
    String getAccountType() {
        return this.AccountType;
    }

}



/*
 * Saving.java
 */
class Saving extends AssetAccount {

    String Name;
    String AccountNumber;
    String AccountType;
    String BalanceAmount;

    public Saving () {
    }

    public Saving(String AccountNumber) {
        setAccountNumber(AccountNumber);
    }

    /**
     * Default Constructor
     * @param       AccountNumber        set AccountNumber of the BankAccount
     **/
    public Saving(String Name, String AccountNumber, String AccountType, String BalanceAmount){
        setName(Name);
        setAccountNumber(AccountNumber);
        setAccountType(AccountType);
        setBalanceAmount(BalanceAmount);
    }

    String getAccountNumber() {
        return AccountNumber;
    }

    String getBalanceAmount() {
        return BalanceAmount;
    }

    String getName() {
        return Name;
    }

    void setName(String Name) {
        this.Name = Name;
    }

        /**
     * Default Constructor
     * @param       AccountNumber        actual set AccountNumber happens here
     **/
    void setAccountNumber(String AccountNumber){
        this.AccountNumber = AccountNumber;
    }

    /**
     * Default Constructor
     * @param       AccountType        actual set AccountNumber happens here
     **/
    void setAccountType(String AccountType){
        this.AccountType = AccountType;
    }
    /**
     * Default Constructor
     * @param       BalanceAmount        actual set AccountNumber happens here
     **/
    void setBalanceAmount(String BalanceAmount){
        this.BalanceAmount = BalanceAmount;
    }

    //String AccountType  = "Saving"
    String getAccountType() {
        return this.AccountType;
    }
}

/*
 * Checking.java
 */
class Checking extends AssetAccount {

    String Name;
    String AccountNumber;
    String AccountType;
    String BalanceAmount;

    public Checking () {
    }

    public Checking(String AccountNumber) {
        setAccountNumber(AccountNumber);
    }

    /**
     * Default Constructor
     * @param       AccountNumber        set AccountNumber of the BankAccount
     **/
    public Checking(String Name, String AccountNumber, String AccountType, String BalanceAmount){
        setName(Name);
        setAccountNumber(AccountNumber);
        setAccountType(AccountType);
        setBalanceAmount(BalanceAmount);
    }

    String getAccountNumber() {
        return AccountNumber;
    }

    String getBalanceAmount() {
        return BalanceAmount;
    }

    String getName() {
        return Name;
    }

    void setName(String Name) {
        this.Name = Name;
    }

        /**
     * Default Constructor
     * @param       AccountNumber        actual set AccountNumber happens here
     **/
    void setAccountNumber(String AccountNumber){
        this.AccountNumber = AccountNumber;
    }

    /**
     * Default Constructor
     * @param       AccountType        actual set AccountNumber happens here
     **/
    void setAccountType(String AccountType){
        this.AccountType = AccountType;
    }
    /**
     * Default Constructor
     * @param       BalanceAmount        actual set AccountNumber happens here
     **/
    void setBalanceAmount(String BalanceAmount){
        this.BalanceAmount = BalanceAmount;
    }

    //String AccountType  = "Checking"
    String getAccountType() {
        return this.AccountType;
    }
}


public class RITBank {

    static BankAccount[] BankAccounts = new BankAccount[20];

    static Double AssetInterestRate = 1.5;
    static Double LiabilityInterestRate = 2.5;

    static int generateAccountNumber() {
        Random rand = new Random();
        // Generate random integers in range 0 to 999
        int rand_int1 = rand.nextInt(100000000);
        return rand_int1;
    }

    static void open(int count) {
        String Name="";
        String AccountNumber="";
        String AccountType = "";
        String BalanceAmount="";
        System.out.println("What type of account\n" +
                "     0 - for savings\n" +
                "     1 - for checking\n" +
                "     2 - for credit card?>");
        Scanner sc1 = new Scanner(System.in);
        int AccountTypeNo=0;

        if ( sc1.hasNext() ) {
            AccountTypeNo = sc1.nextInt();
        }

        if( AccountTypeNo == 0 ) {
            AccountType = "SAVING";
        }
        else if (AccountTypeNo == 1) {
            AccountType = "CHECKING";
        }
        else if (AccountTypeNo == 1) {
            AccountType = "CREDITCARD";
        }
            System.out.println("What is the customer’s Name?>");
            Scanner sc2 = new Scanner( System.in );
            if ( sc2.hasNext() ) {
                Name = sc2.next();
            }

            System.out.println( "How much to deposit?>\n" );
            Scanner sc3 = new Scanner( System.in );
            if ( sc3.hasNext() ) {
                BalanceAmount = sc3.next();
            }

            AccountNumber = Integer.toString(generateAccountNumber());
            BankAccounts[count] = new Saving(Name, AccountNumber, AccountType, BalanceAmount);
    }
    static void credit() {
            String AccountNumber="";
            String CreditAmount="";
            String BalanceAmount="";
            System.out.println("What is the account number?>");
            Scanner sc1 = new Scanner(System.in);
            if ( sc1.hasNext() ) {
                AccountNumber = sc1.next();
            }
            System.out.println("How much?>");
            Scanner sc2 = new Scanner(System.in);
            if(sc2.hasNext()) {
                CreditAmount = sc2.next();
            }
            int index=0;
            while (index<BankAccounts.length) {
                if (AccountNumber.equals(BankAccounts[index].getAccountNumber())) {
                    BalanceAmount = BankAccounts[index].getBalanceAmount();
                    System.out.println(BalanceAmount);
                    double BalanceAmountDouble = Double.parseDouble(BalanceAmount);
                    double CreditAmountDouble = Double.parseDouble(CreditAmount);
                    BalanceAmountDouble += CreditAmountDouble;
                    BalanceAmount = Double.toString(BalanceAmountDouble);
                    BankAccounts[index].setBalanceAmount(BalanceAmount);
                    System.out.println(BalanceAmount);
                    break;
                 }
                 index++;
            }
    }
        static void debit() {
            String AccountNumber="";
            String DebitAmount="";
            String BalanceAmount="";
            System.out.println("What is the account number?>");
            Scanner sc1 = new Scanner(System.in);
            if ( sc1.hasNext() ) {
                AccountNumber = sc1.next();
            }
            System.out.println("How much?>");
            Scanner sc2 = new Scanner(System.in);
            if(sc2.hasNext()) {
                DebitAmount = sc2.next();
            }
            int index=0;
            while (index<BankAccounts.length) {
                if (AccountNumber.equals(BankAccounts[index].getAccountNumber())) {
                    BalanceAmount = BankAccounts[index].getBalanceAmount();
                    System.out.println(BalanceAmount);
                    double BalanceAmountDouble = Double.parseDouble(BalanceAmount);
                    double DebitAmountDouble = Double.parseDouble(DebitAmount);
                    BalanceAmountDouble -= DebitAmountDouble;
                    BalanceAmount = Double.toString(BalanceAmountDouble);
                    BankAccounts[index].setBalanceAmount(BalanceAmount);
                    System.out.println(BalanceAmount);
                    break;
                 }
                 index++;
            }
    }

           static void close() {
            String AccountNumber="";
            System.out.println("What is the account number?>");
            Scanner sc1 = new Scanner(System.in);
            if ( sc1.hasNext() ) {
                AccountNumber = sc1.next();
            }
            int index=0;
            while (index<BankAccounts.length) {
                if (AccountNumber.equals(BankAccounts[index].getAccountNumber())) {
                    BankAccounts[index].setAccountNumber("");
                    BankAccounts[index].setName("");
                    BankAccounts[index].setAccountType("");
                    BankAccounts[index].setBalanceAmount("");
                    break;
                 }
                 index++;
            }
    }
 

    static void summary(){        
        String Name= "";
        String AccountNumber="";
        String AccountType;
        String BalanceAmount="";
        for (int index = 0; index < BankAccounts.length; index++) {
            try{
            Name = BankAccounts[index].getName();
            AccountNumber = BankAccounts[index].getAccountNumber();
            AccountType = BankAccounts[index].getAccountType();
            BalanceAmount = BankAccounts[index].getBalanceAmount();
            System.out.println("Bank Summary ");
            System.out.println("Account number : " + AccountNumber);// 1594686078
            System.out.println("Account type : " + AccountType); //SAVINGS
            System.out.println("Customer name : " + Name); // Bob
            System.out.println("Account balance : " + BalanceAmount); //$300.00
        }
         catch(NullPointerException e) 
        { 
            continue;
            //System.out.print("NullPointerException Caught"); 
        }
        }
    }

    static void passSomeTime(){
        String Name= "";
        String AccountNumber="";
        String AccountType;
        String BalanceAmount="";
        System.out.println("How Much?");
        int time = 1;
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()){
            time = sc.nextInt();
        }

        for (int index = 0; index < BankAccounts.length; index++) {
            try{
            Name = BankAccounts[index].getName();
            AccountNumber = BankAccounts[index].getAccountNumber();
            AccountType = BankAccounts[index].getAccountType();
            BalanceAmount = BankAccounts[index].getBalanceAmount();
            double BalanceAmountDouble = Double.parseDouble(BalanceAmount);
            Double Amount = BalanceAmountDouble * time * AssetInterestRate;
            BalanceAmountDouble += (Amount)/100;
            BalanceAmount = Double.toString(BalanceAmountDouble);
            BankAccounts[index].setBalanceAmount(BalanceAmount);
            System.out.println(Name + " earned $"+ (Amount/100) +" in interest in "
                + time +" months. Account Balance is now $" + BalanceAmount);
            //System.out.println(BalanceAmount);
        }
         catch(NullPointerException e) 
        { 
            continue;
            //System.out.print("NullPointerException Caught"); 
        }
        }   
    }

    public static void main(String args[]) {
        int count=0;
        System.out.println("Enter one of the following commands");
        System.out.println("time - pass certain amount of time");
        System.out.println("open - open a new account");
        System.out.println("close - close an account");
        System.out.println("credit - credit an account");
        System.out.println("debit - debit an account");
        System.out.println("summary - display current bank accounts");
        System.out.println("exit - exit program");
        System.out.println();
        System.out.println("What do you want to do?");
        String nextKeyword = "";
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            if (sc.hasNext()) {
                nextKeyword = sc.next();
            }
            //sc.close();
            if (nextKeyword.equals("open")) {
                open(count++);
                //BankAccounts[0] = new Credit(AccountNumber);
            } else if (nextKeyword.equals("time")) {
                passSomeTime();
            } else if (nextKeyword.equals("exit")) {
                System.exit(0);
            } else if (nextKeyword.equals("credit")) {
                credit();

            } else if (nextKeyword.equals("debit")) {
                debit();

            } else if (nextKeyword.equals("close")) {
                close();
            }else if (nextKeyword.equals("summary")) {
                summary();
            }
        }
    }
}