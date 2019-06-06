/** 
 * Calculator.java 
 * 
 * Version:   2.0
 *     
 * Revisions: 0  
 *
 * AUTHORS: ADITYA LANDGE, RUPA KARUMANCHI   
 *	   
 */

import java.util.Vector;
import java.util.Stack;

public class Calculator {

    // See https://docs.oracle.com/javase/10/docs/api/java/util/Stack.html
    static Stack<Double> numberStack = new Stack<Double>();
    static Stack<String> operatorStack = new Stack<String>();
    // See https://docs.oracle.com/javase/10/docs/api/java/lang/String.html
    static String operators = "^/*%-+{[(}])";
    static int countRoundBrackets=0;
    static int countParanthesis=0;
    static int countSquareBrackets=0;

    public static void main (String args []) {
        performCalculation("2","*","{", "9", "+","(","2", "*" , "2" ,")","}");  //val ops
        performCalculation("2", "*", "(" ,"3" ,"+" ,"5",")"  );
        performCalculation("2", "+", "[", "(", "3", "-", "6" ,")", "/", "5", "]");
        performCalculation("1", "*", "{", "2", "+", "3", "-", "[", "1", "*", "(", "2", "-", "1", ")",
                                            "]", "+", "3", "}");
        performCalculation("2", "^", "3", "+", "4");
        performCalculation("2", "^", "3", "^", "4");
        //performCalculation("1", "-", "(", "2", "+", "[", "+", "2", "-", ")" ,"-" ,"3" ,"]");*/
        performCalculation("(", "2", "+" ,"3", ")", "^" ,"4");

    }

    // See https://docs.oracle.com/javase/8/docs/technotes/guides/language/varargs.html
    public static void performCalculation (String ... valuesAndOperators)   {
        Vector<String> aLine = new Vector<String>(); //aLine is 2+3 */
        for ( String valuesAndOperator: valuesAndOperators )    {
            aLine.add(valuesAndOperator);
            System.out.print(valuesAndOperator + " ");
        }

        System.out.println(" =  " + calculate(aLine) );
    }
    /** drives the calculation and returns the result
     */
    public static double calculate (Vector<String> inputLine) {
        while ( inputLine.size() >= 1 ) {
            if ( operator( inputLine.firstElement() )   )
                performOperator(inputLine.firstElement());
            else
                performNumber(inputLine.firstElement());

            inputLine.removeElementAt(0);
        }
        while ( !  (operatorStack.empty())) {
            if ( numberStack.size() > 1 )
                evaluate();
            else    {
                if(! (operatorStack.peek().equals("(") || 
                	operatorStack.peek().equals("["))) {
                    System.out.println("dangling operand ....");
                    System.out.println(numberStack);
                    System.exit(1);
                }
                else{
                    //System.out.print(numberStack);
                    break;
                }

            }
        }

        return numberStack.pop();
    }

    /** perform the required operation based on precedence of the operators 
    on the stack*/
    public static boolean operator (String op) {
        return ( operators.indexOf(op) >= 0 );
    }

    /** deteremence a precedence level for the operator
     */
    public static int precedence (String op) {
        return operators.indexOf(op);  //setting precendence base on index*/
    }

    /** perform the required operation based on precedence on the stack
     */
    public static void performOperator (String op) {
        while (! (operatorStack.empty() || operatorStack.peek().equals("(") ||
         operatorStack.peek().equals("[")
                || operatorStack.peek().equals("{"))  &&
                (  precedence(op) < precedence(operatorStack.peek() ) )
        )
            evaluate();
        operatorStack.push(op);
    }

    /** pushes the number on the number stack
     */
    public static void performNumber (String number) {
        numberStack.push(Double.valueOf(number));
    }

    /** get the number of the stack, if a number is available, else RIP
     */
    public static double  getNumber () {
        if ( numberStack.empty() ){
            System.out.println("not enough numbers ...");
            System.exit(2);
        }
        return numberStack.pop();
    }

    /** perform the required operation based on the operator in the stack
     */
    public static void evaluate () {
        String currentOp = operatorStack.pop();
        double right = getNumber();
        double left = getNumber();
        if ( currentOp.equals("+") )
        /** assigning the mathematical task to the
         particular operator symbol */
            numberStack.push( left + right );  
        else if ( currentOp.equals("-") )
            numberStack.push( left - right );
        else if ( currentOp.equals("*") )
            numberStack.push( left * right );
        else if ( currentOp.equals("%") )
            numberStack.push( left % right );
        else if ( currentOp.equals("/") )
            numberStack.push( left / right );
        else if ( currentOp.equals("^") )
            numberStack.push( Math.pow(left , right ) );

        //Mathematical task for round brackets
        else if ( currentOp.equals(")") ) {
            numberStack.push(left);
            numberStack.push(right);
            countRoundBrackets++;
        }
        else if ( currentOp.equals("(") ){
            if(countRoundBrackets!=0) {
                numberStack.push(left);
                numberStack.push(right);
            }
            else{
                    while (!(operatorStack.empty() && 
                    	operatorStack.peek().equals("("))) {
                        System.out.println("here()");
                        evaluate();
                    }
                    numberStack.push(left);
                    numberStack.push(right);
                }
        }
        //Mathematical Tasks for square brackets
        else if ( currentOp.equals("]") ) {
            numberStack.push(left);
            numberStack.push(right);
            countSquareBrackets++;
        }
        else if ( currentOp.equals("[") ){
            if(countSquareBrackets!=0) {
                numberStack.push(left);
                numberStack.push(right);
            }
            else{
                while (!(operatorStack.empty() && 
                	operatorStack.peek().equals("["))) {
                    System.out.println("here[]");
                    evaluate();
                }
                numberStack.push(left);
                numberStack.push(right);
            }
        }
        //Mathematical task for Curly Braces
        else if ( currentOp.equals("}") ) {
            numberStack.push(left);
            numberStack.push(right);
            countParanthesis++;
        }
        else if ( currentOp.equals("{") ){
            if(countParanthesis!=0) {
                numberStack.push(left);
                numberStack.push(right);
            }
            else{
                while (!(operatorStack.empty() && 
                	operatorStack.peek().equals("{"))) {
                    System.out.println("here{}");
                    evaluate();
                }
                numberStack.push(left);
                numberStack.push(right);
            }
        }
        //Else Condition
        else
            System.out.println("Unknow Operator");
    }
}