/** 
*(1) The program is a calculator which evaluates mathematical expressions containing 
*numbers and certain defined operators (six selected operators). 
*(2) It makes use of two main stacks - (i) a number stack to store and retrieve the 
*numbers (double data type) and (ii) an operator stack to store and retrieve the operators (string data type).
*(3)The mathematical expressions are hard coded. The performCalculation function is defined 
*which makes use of vectors for accommodating varying sizes of values and operators.
*(4)The performCalculation function contains a function ‘calculate’ which first 
*checks whether the first element in the expression is a number or an operator and ultimately 
*pushes the respective element into the respective stack (number stack / operator stack). 
*(5)If the element is an operator, a function ‘performOperator’ is defined which checks whether the operator stack is empty.
*(6)If it is not empty, a check is performed to compare whether the current operator has 
*lesser precedence than the top-most operator (peek) of the operator stack. 
*(7)If it is, the expression is evaluated under function ‘evaluate’ wherein the 
*mathematical tasks of the operators have been defined.  
*(8)The operator is called from the operator stack and a function ‘getNumber’ is defined which retrieves a number from the number stack. 
*This function is called under the ‘evaluate’ function. 
*(9)If the number stack is empty the ‘getNumber’ function 
*returns the message that there are not enough numbers.
*(10)If not the numbers on either side of the operators are 
*called and the operator performs the function it was defined to do. 
*(11)The ‘precedence’ function is defined under the performOperator function. 
*(12)The ‘precedence’ function returns the precedence order through calling the 
*index number of the string ‘operators’. 
*(13)The ‘operators’ string is created and the order of elements in the string is entered based on his/her desired precedence. 
*(14)Changing the order of elements in this string has the following ripple effect -> the precedence
*function returns the changed order of precedence of the operators and this is reflected when the ‘performOperator’ function 
*does the check of comparing precedence of current operator to the precedence of the operator in the operator stack.
*/

import java.util.Vector;
import java.util.Stack;

public class Calculator {

	// See https://docs.oracle.com/javase/10/docs/api/java/util/Stack.html
    static Stack<Double> numberStack = new Stack<Double>();
    static Stack<String> operatorStack = new Stack<String>();
	// See https://docs.oracle.com/javase/10/docs/api/java/lang/String.html
    static String operators = "/*%-+^";	

    public static void main (String args []) {
	performCalculation("2", "+", "3");  //val ops 
	performCalculation("2", "+", "3", "*", "3");
	performCalculation("2", "*", "3", "+", "3");
	performCalculation("2", "+", "3", "^", "4");
	performCalculation("2", "^", "3", "+", "4");
	performCalculation("2", "^", "3", "^", "4");
    }

    // See https://docs.oracle.com/javase/8/docs/technotes/guides/language/varargs.html
    public static void performCalculation (String ... valuesAndOperators)	{
	Vector<String> aLine = new Vector<String>(); //aLine is 2+3 */
	for ( String valuesAndOperator: valuesAndOperators )	{
		aLine.add(valuesAndOperator);
		System.out.print(valuesAndOperator + " ");
	}
	System.out.println(" =  " + calculate(aLine) );
    }
    /** drives the calculation and returns the result
     */
    public static double calculate (Vector<String> inputLine) {
	while ( inputLine.size() >= 1 )	{
		if ( operator( inputLine.firstElement() )	)
			performOperator(inputLine.firstElement());
		else
			performNumber(inputLine.firstElement());

		inputLine.removeElementAt(0);
	}
	while ( !  operatorStack.empty() )	{
		if ( numberStack.size() > 1 )
			evaluate();
		else	{
			System.out.println("dangling operand ....");
			System.out.println(numberStack);
			System.exit(1);
		
		}
	}

	return numberStack.pop();
    }

    /** perform the required operation based on precedence of the operators on the stack
     */
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
		while (! operatorStack.empty()  &&
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
			numberStack.push( left + right );  /** assigning the mathematical task to the
			 particular operator symbol */
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
		else
			System.out.println("Unknow Operator");
    }
}