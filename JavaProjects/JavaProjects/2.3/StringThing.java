/** 
 * StringThing.java 
 * 
 * Version:   1.0
 *     
 * Revisions: 0  
 *
 * AUTHORS: ADITYA LANDGE, RUPA KARUMANCHI   
 *
 * (a) Explain the output --> comments added before respective output line
 *
 * (b) How many strings will be generated in the lines marked 
 *     (a) ... j) and 1. ... 6?
 *	   Total no. of strings generated: 17 
 *     { a123, 123, c. 12323, d. 1233123, e. 126123, f. 1213123, g. 369123, 
 *     h. 41123, i. 120123, aa, xyz, x, xyz, xyz, 123, 123, 123 }
 *
 * (c) When is the earliest moment the garbage collector can free up memory for 
 *     each of the lines marked a) ... j) and 1. ... 6?
 *     All the strings created from a) ... (j) are eligible for GC
 *     immediately after their respective print statement is executed since
 *     there is no reference variable which can be used to point to the strings.
 *     (There is no reference varible such as 'aString' assigned to them).
 *     Strings created during 1. ... 6 are eligible for GC once the method is
 *     executed since they are local to that particular method only. 
 *	   
 */


class StringThing {
  public static void method(String id, String literal, String aNewString)	{
	System.out.println(id + ".	" + (literal == aNewString ));
  }
  public static void main( String args[] ) {
	String aString = "123";
	String bString = "123";
	String cString = "1" + "23";
	int number = 3;

	// false: "a" + "123" results in string concatenation. 
	// So we are comparing a123 with aString (123) hence it is false.
	System.out.println("a.  " +   "123" == aString );	

	// false: aString is a constant placed in the string constant pool.
	// "12" + number results in a runtime operation and is placed in the heap. 
	System.out.println("b.	" +   ( "12" + number == aString ) ); 

	// c. 12323: Multiplication takes precedence and will be evaluated.
	// Rest of the expression will be concatenated since it begins with "c. "
	System.out.println("c.	" +   aString  + 1 * 23 ); 

	// d. 1233123: By string concatenation since it begins with "string" "+"" .
	System.out.println("d.  " +   123 + number + aString  ); 

    // e. 126123: Expression in parenthesis will be evaluated first (123 + 3)
    // followed by concatenation.
	System.out.println("e.	" +   ( 123 + number ) + aString   ); 

	// f. 1213123: Expression in parenthesis will be evaluated first
    // followed by concatenation.
	System.out.println("f.	" +   ( 123 - 2 + "" +  number + aString )  ); 

	// g. 369123: Multiplication takes precedence and will be evaluated.
	// Rest of the expression will be concatenated.
	System.out.println("g.	" +   123 * number + aString  ); 

	// h. 41123: Division takes precedence and will be evaluated.
	// Rest of the expression will be concatenated.
	System.out.println("h.	" +   123 / number + aString  ); 

	// i. 120123: Expression in parenthesis will be evaluated first
    // followed by concatenation.
	System.out.println("i.	" +   ( 123 - number )  + aString  ); 

	// j. true: aString was declared as a string constant "123"
	System.out.println("j.	" +     ( "123" == aString )   ); 

	// g. true: String concatenation. Does not create duplicates.
	System.out.println("g.	" +     ( "a" + "a" == "aa"  )   ); 

	// h. true: cString was declared as "12" + "3" which is "123"
	System.out.println("h.	" +     ( "123" == cString )   ); 

	// false: String concatenation occurs from left to right (1.x !== x)
	System.out.println("1." + "x" == "x"); 
 
    // true: String concatenation. Does not create duplicates.
	method("1", "xyz", "x" + "yz"); 

	// false: new keyword creates a new object hence it will not be same as xyz
	method("2", "xyz", new String("x") + "yz" ); //false

	// true: String concatenation. Does not create duplicates.
	method("3", "xyz", "x" + "y" +"z"); 

	// true: 123 == 123 (multiplication takes precedence)
	method("4", "1" + "2" + "3",     "1" + 2 * 1 + 3); 

	// true: 123 == 123 (String concatenation takes precedence)
	method("5", "1" + "2" + "3",     "1" + 2 + 3); 

    // true: 123 == 123 (parenthesis will be evaluated)
    // followed by concatenation.
	method("6", "1" + "2" + "3",     "1" + (3 - 1)  + 3); 
  }
}