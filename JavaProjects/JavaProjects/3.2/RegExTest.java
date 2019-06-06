/* 
 * RegEx.txt.java 
 * 
 * Version: 
 *     1 
 * 
 * Revisions: 
 *     0 
 */

/** 
 * The class consists for 19 methods in all one of which is a main method.
 * Half of the remaining methods determine if a word fulfils a particular
 * requirement using custom methods for each property.
 * And other half use regex. 
 * 
 * @author      ADITYA LANDGE 
 * @author      RUPA KARUMANCHI 
 */

import java.util.Scanner;
import java.io.File;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;

public class RegExTest {
    public static void testPattern1(String regEx, String aString,
     String comment ) {
		if ( Pattern.matches(regEx, aString) )	{
			System.out.println("RegEx Test 1:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + regEx );
			System.out.println("\t" + comment);
		}
    }

    public static void testOwn1(String aString, String comment ) {
		if ( aString.indexOf('a') >= 0 )	{
			System.out.println("Own Test 1:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + comment);
		}
	}

	public static void testPattern2(String regEx, String aString, String comment ) {
		if ( Pattern.matches(regEx, aString) )	{
			System.out.println("RegEx Test 2:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + regEx );
			System.out.println("\t" + comment);
		}
    }	

	public static void testOwn2(String aString, String comment ) {
		int index = aString.length();
		if ( String.valueOf(aString.charAt(0)).equals(String.valueOf(aString.charAt(index-1))) )	{
			System.out.println("Own Test 2:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + comment);
		}
    }

    public static void testPattern3(String regEx, String aString, String comment ) {
		if ( Pattern.matches(regEx, aString) )	{
			System.out.println("RegEx Test 3:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + regEx );
			System.out.println("\t" + comment);
		}
    }	

    public static void testOwn3(String aString, String comment ) {
		int index = aString.length()-1;
		int i=0;
		while(i<index){
			if(String.valueOf(aString.charAt(i)).equals(String.valueOf(aString.charAt(i+1)))){
			System.out.println("Own Test 3:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + comment);
			break;
			}
			i++;
		}
    }

    public static void testPattern4(String regEx, String aString, String comment ) {
		if ( Pattern.matches(regEx, aString) )	{
			System.out.println("RegEx Test 4:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + regEx );
			System.out.println("\t" + comment);
		}
    }

    public static void testOwn4(String aString, String comment ) {
		int index = aString.length()-1;
		int i=0;
		while(i<(index-1)){
			//System.out.println("i: "+aString.charAt(i));
			//System.out.print("i+2: "+aString.charAt(i+2));
			if(String.valueOf(aString.charAt(i)).equals(String.valueOf(aString.charAt(i+2)))) {	
			System.out.println("Own Test 4:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + comment);
			break;
			}
			i++;
			}
    }

    public static void testPattern5(String regEx, String aString, String comment ) {
		if ( Pattern.matches(regEx, aString) )	{
			System.out.println("RegEx Test 5:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + regEx );
			System.out.println("\t" + comment);
		}
    }

    public static void testOwn5(String aString, String comment ) {
		if ( aString.indexOf('a') >= 0 )	{
			System.out.println("Own Test 5:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + comment);
		}
	}

	    public static void testPattern6(String regEx, String aString, String comment ) {
		if ( Pattern.matches(regEx, aString) )	{
			System.out.println("RegEx Test 6:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + regEx );
			System.out.println("\t" + comment);
		}
    }

	public static void testOwn6(String aString, String comment ) {
		int index = aString.length()-1;
		int i = 0;
		int count = 0;
		while(i<index){
			if(String.valueOf(aString.charAt(i)).equals("a") || String.valueOf(aString.charAt(i)).equals("b")) {
			count++;
			}
			i++;
		}
		if(count == index) {
			System.out.println("Own Test 6:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + comment);
		}
    }

    public static void testPattern7(String regEx, String aString, String comment ) {
		if ( Pattern.matches(regEx, aString) )	{
			System.out.println("RegEx Test 7:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + regEx );
			System.out.println("\t" + comment);
		}
    }
	
	public static void testOwn7(String aString, String comment ) {
		int index = aString.length()-1;
		int i = 0;
		int count = 0;
		while(i<index){
			if(String.valueOf(aString.charAt(i)).equals("a") || String.valueOf(aString.charAt(i)).equals("b")) {
			count++;
			}
			i++;
		}
		if(count == 0) {
			System.out.println("Own Test 7:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + comment);
		}
    }

    public static void testPattern8(String regEx, String aString, String comment ) {
		if ( Pattern.matches(regEx, aString) )	{
			System.out.println("RegEx Test 8:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + regEx );
			System.out.println("\t" + comment);
		}
    }

    public static void testOwn8(String aString, String comment ) {
		if ( aString.indexOf('.') >= 0 )	{
			System.out.println("Own Test 8:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + comment);
		}
	}

	public static void testPattern9(String regEx, String aString, String comment ) {
		if ( Pattern.matches(regEx, aString) )	{
			System.out.println("RegEx Test 9:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + regEx );
			System.out.println("\t" + comment);
		}
    }

	public static void testOwn9(String aString, String comment ) {
		if ( aString.indexOf('.') >= 0 )	{
			System.out.println("Own Test 9:");
			System.out.println("\tInput line: " + aString);
			System.out.println("\t" + comment);
		}
	}

    public static void main( String[] args ) {
		String aString;
		Pattern aPattern;

		//File file = new File("C:/Users/adity/OneDrive/Desktop/Java/words");
        //Scanner sc = new Scanner(file);
        try{
        Scanner sc = new Scanner(new File("words.txt"));
        while (sc.hasNextLine()) {
	        aString = sc.next();
			//aString = "aaaaa";
			testPattern1(".*[a]+.*", aString, "string has at least one a ");
			testOwn1(aString, "string has at least one a ");
			testPattern2("/^((.))$/", aString, "Palindrome anchored at the beginning and end of line. ");
			testOwn2(aString, "Palindrome anchored at the beginning and end of line. ");
			testPattern3("[a-z]{2,}", aString, "Include a palindrome which is 2 characters long.");
			testOwn3(aString, "Include a palindrome which is 2 characters long.");
			testPattern4("(.?)(^.?)(.?)", aString, "Include a palindrome which is 3 characters long.");
			testOwn4(aString, "Include a palindrome which is 3 characters long.");
			testPattern5(".*[a]+.*", aString, "The word has at least one a in it.");
			testOwn5(aString, "The word has at least one a in it.");
			testPattern6("[^c-z]+", aString, "The word consist only of a s or b s. ");
			testOwn6(aString, "The word consist only of a s or b s.");
			testPattern7("[^a]+[^b]+", aString, "a s or  b s can not be part of the word.");
			testOwn7(aString, "a s or  b s can not be part of the word.");
			testPattern8(".*[.]+.*", aString, "The word is == ’.’.");
			testOwn8(aString, "The word is == ’.’.");
			testPattern9(".*[.]+.*", aString, "The word includes a ’.’.");
			testOwn9(aString, "The word includes a ’.’.");
			System.out.println("\n");
			}
        	sc.close(); 
        	System.out.println("\n");
	    }
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}
}
