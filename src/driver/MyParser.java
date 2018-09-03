//**********************************************************
//Assignment3:
//UTORID user_name: landolf5
//
//Author: Vincent Landolfi
//
//
//Honor Code: I pledge that this program represents my own
//program code and that I have coded on my own. I received
//help from no one in designing and debugging my program.
//*********************************************************
package driver;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyParser {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		printInfo(args);
		MyParser parse = new MyParser();
	}

	/**
	 * Prints all the information that is necessary output for this assignment.
	 * 
	 * @param args.
	 *            An array of strings, the first element being a list of URLs or
	 *            filename separated by commas, and the second element being the
	 *            output file
	 */
	public static void printInfo(String[] args) {
		try {
			// if there is a file arguments
			if (args.length != 1) {
				// change output stream to the file
				System.setOut(new PrintStream(new FileOutputStream(args[1])));
			}
			// if there is an error
		} catch (Exception e) {
			// if it wasnt a problem with the non existing param
			if (args.length != 1) {
				// error message
				System.out.println("Did you change the run configuration in" + "Eclipse for argument0 and argument 1?");
			}
		}
		// split the files at the commas
		String inputFiles[] = args[0].split(",");
		// make a new GetCoAuthors command outside loop because it needs
		// to use every iterations of the loop
		CoAuthorGetter CoAuth = new CoAuthorGetter();
		// go through all the inputfiles
		for (String inputFile : inputFiles) {
			// break line
			System.out.println("------------------------------------"
					+ "-----------------------------------");
			// new GetAuthorName command given input file
			AuthorNameGetter getAuthor = new AuthorNameGetter(inputFile);
			// print the command and some spaces
			System.out.println("1. Name of Author:");
			System.out.print("\t");
			// use the printAuthorName command to get the author's name in this
			// particular inputfile
			getAuthor.printAuthorName();
			// new object to search the table at the top of the page
			TableSearcher search = new TableSearcher(inputFile);
			// print the command and some spaces
			System.out.println("2. Number of All Citations:");
			System.out.print("\t");
			// print the value of citations in the table
			search.printValueInTable("citations");
			// print the command and some spaces
			System.out.println("3. Number of i10-index after 2009:");
			System.out.print("\t");
			// print the i10 value in the table
			search.printValueInTable("i10");
			// new GetPublications command to get the title of the first three
			// publications
			PublicationsGetter publications = new PublicationsGetter(inputFile);
			// print the command
			System.out.println("4. Title of the first 3 publications:");
			// the GetFirstThreePublications command prints the first three
			// publications
			publications.getFirstThreePublications();
			// new GetTotalPaperCitation Command will get the total of the first
			// five
			// citations
			CitationsGetter Cite = new CitationsGetter(inputFile);
			// print the command and some spaces
			System.out.println("5. Total paper citation (first 5 papers):");
			System.out.print("\t");
			// prints the total citation number
			Cite.printTotalPaperCitationNum();
			// add the coAuthors on this webpage to the coAuthorsList
			CoAuth.populateCoAuthorsList(inputFile);
			// print the command and some spaces
			System.out.println("6. Total Co-Authors:");
			System.out.print("\t");
			// print the number of coAuthors on this page only
			CoAuth.printNumCoAuthors();
			// break point between web pages
			System.out.print("\n");
		}
		// newline
		System.out.println("");
		// break
		System.out.println("------------------------------------"
				+ "-----------------------------------");
		// print the command and some spaces
		System.out.print("7. Co-Author list sorted (Total: ");
		// print the total list of coAuthors
		CoAuth.printCoAuthors();
		// newline
		System.out.println("");
	}

	/**
	 * Uses the string that is either a URL or file and gets the html code from
	 * that file
	 * 
	 * @param urlString.
	 *            The URL or file to get the html code from
	 * @return returns the html code of the file in string form
	 * @throws Exception
	 *             Any possible exception that is raised
	 */
	public String getHTML(String urlString) throws Exception {
		// create object to store html source text as it is being collected
		StringBuilder html = new StringBuilder();
		// open connection to given url
		URL url = new File(urlString).toURI().toURL();
		// create BufferedReader to buffer the given url's HTML source
		BufferedReader htmlbr = new BufferedReader(new InputStreamReader(url.openStream()));
		String line;
		// read each line of HTML code and store in StringBuilder
		while ((line = htmlbr.readLine()) != null) {
			html.append(line);
		}
		htmlbr.close();
		// convert StringBuilder into a String and return it
		return html.toString();
	}
}
