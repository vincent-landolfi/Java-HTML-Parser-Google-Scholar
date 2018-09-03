package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import driver.CoAuthorGetter;

public class CoAuthorGetterTest {
	CoAuthorGetter CoAuth;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	/**
	 * Set the output stream so we can save what was outputted and make a new
	 * GetCoAuthors object
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		CoAuth = new CoAuthorGetter();
	}

	/**
	 * See if the populate list command populates the arraylist in the object
	 * correctly
	 */
	@Test
	public void testSample1PopulateList() {
		// populate with co authors from sample1.html
		CoAuth.populateCoAuthorsList("sample1.html");
		// new arraylist
		ArrayList<String> test = new ArrayList<String>();
		// add all the names that should be there
		test.add("Egon Willighagen");
		test.add("Jonathan Alvarsson");
		test.add("Christoph Steinbeck");
		test.add("Nina Jeliazkova");
		test.add("Rajarshi Guha");
		test.add("Sam Adams");
		test.add("Janna Hastings");
		test.add("Samuel Lampa");
		test.add("Valentin Georgiev");
		test.add("Adam Ameur");
		test.add("Komorowski Jan");
		test.add("gilleain torrance");
		test.add("Antony John Williams");
		test.add("Noel M. O'Boyle");
		test.add("Sean Ekins");
		// see if the arraylist in the object and our test list match
		assertEquals(CoAuth.getCoAuthors(), test);
	}

	/**
	 * Test the function to print the number of co-authors with sample1.html
	 */
	@Test
	public void testPrintNumCoAuthorsSample1() {
		// populate the list
		CoAuth.populateCoAuthorsList("sample1.html");
		// print the number of co authors
		CoAuth.printNumCoAuthors();
		// see if the number was right
		assertTrue(outContent.toString().equals("15"));
	}

	/**
	 * Test the populate list method with sample2.html
	 */
	@Test
	public void testSample2PopulateList() {
		// populate the list with co authors from sample2
		CoAuth.populateCoAuthorsList("sample2.html");
		// make new arraylist
		ArrayList<String> test = new ArrayList<String>();
		// add all the necessary names
		test.add("Blair MacIntyre");
		test.add("E.D. Mynatt");
		test.add("Erika Shehan Poole");
		test.add("Andrew D Miller");
		test.add("Elsa Eiriksdottir");
		test.add("Iulian Radu");
		test.add("Abigail Sellen");
		test.add("Xiang Cao");
		test.add("Thore Graepel");
		test.add("John Stasko");
		test.add("Youn-ah Kang");
		test.add("Kurt Luther");
		test.add("Deepak Jagdish");
		test.add("Greg Turk");
		// see if object arraylist and test list match
		assertEquals(CoAuth.getCoAuthors(), test);
	}

	/**
	 * Test the print number of co authors command with sample2
	 */
	@Test
	public void testPrintNumCoAuthorsSample2() {
		// populate the list
		CoAuth.populateCoAuthorsList("sample2.html");
		// print the number of co authors
		CoAuth.printNumCoAuthors();
		// see if it was right
		assertTrue(outContent.toString().equals("14"));
	}

	/**
	 * Test the print num of co authors method with the supplied test file
	 */
	@Test
	public void testPrintNumCoAuthorsTestFile() {
		// populate the list
		CoAuth.populateCoAuthorsList("rs.html");
		// print the number of co authors
		CoAuth.printNumCoAuthors();
		// see if its right
		assertTrue(outContent.toString().equals("0"));
	}

	@Test
	public void testPrintCoAuthors() {
		// test the print co authors command which can work
		// with multiple pages
		CoAuth.populateCoAuthorsList("sample1.html");
		CoAuth.populateCoAuthorsList("sample2.html");
		// print the co authors
		CoAuth.printCoAuthors();
		// see if the output is correct
		assertTrue(outContent.toString()
				.equals("29)\nAbigail Sellen\nAdam Ameur\nAndrew D Miller\nAntony John Williams"
						+ "\nBlair MacIntyre\nChristoph Steinbeck\nDeepak Jagdish\nE.D. Mynatt\n"
						+ "Egon Willighagen\nElsa Eiriksdottir\nErika Shehan Poole\nGreg Turk\n"
						+ "Iulian Radu\nJanna Hastings\nJohn Stasko\nJonathan Alvarsson\n"
						+ "Komorowski Jan\nKurt Luther\nNina Jeliazkova\nNoel M. O'Boyle\n"
						+ "Rajarshi Guha\nSam Adams\nSamuel Lampa\nSean Ekins\nThore Graepel\n"
						+ "Valentin Georgiev\nXiang Cao\nYoun-ah Kang\ngilleain torrance\n"));
	}

	/**
	 * Test the populate list function with a test file that contains one
	 * matching coauthor
	 */
	@Test
	public void testMatchingRegexPopulateList() {
		// populate the list
		CoAuth.populateCoAuthorsList("matches.html");
		// new arraylist
		ArrayList<String> test = new ArrayList<String>();
		// add necessary names
		test.add("Vincent Landolfi");
		// see if object list matches test list
		assertEquals(CoAuth.getCoAuthors(), test);
	}

	/**
	 * Test the populate list function with a test file that has no matching co
	 * authors
	 */
	@Test
	public void testNonMatchingRegexPopulateList() {
		// populate the list
		CoAuth.populateCoAuthorsList("nomatches.html");
		// empty arraylist
		ArrayList<String> test = new ArrayList<String>();
		// see if object list is empty
		assertEquals(CoAuth.getCoAuthors(), test);
	}

	/**
	 * Test the populate list command with the supplied testing file
	 */
	@Test
	public void testPopulateListTestFile() {
		// populate list
		CoAuth.populateCoAuthorsList("rs.html");
		// empty arraylist
		ArrayList<String> test = new ArrayList<String>();
		// See if the object's list is empty
		assertEquals(CoAuth.getCoAuthors(), test);
	}

	/**
	 * Test the print num of co authors method with a test file that has one
	 * match
	 */
	@Test
	public void testMatchingRegexPrintNumCoAuthors() {
		// populate list
		CoAuth.populateCoAuthorsList("matches.html");
		// print the number of co authors
		CoAuth.printNumCoAuthors();
		// see if we get the right output
		assertTrue(outContent.toString().equals("1"));
	}

	/**
	 * Test the print num of co authors method with a test file that does not
	 * contain any matches
	 */
	@Test
	public void testNonMatchingRegexPrintNumCoAuthors() {
		// populate the list
		CoAuth.populateCoAuthorsList("nomatches.html");
		// print the number of co authors
		CoAuth.printNumCoAuthors();
		// see if the number printed is zero
		assertTrue(outContent.toString().equals("0"));
	}

	/**
	 * Test the print co authors function with both created HTML test files,
	 * which only have one co author between them
	 */
	@Test
	public void testPrinCoAuthorsWithMadeHTMLFiles() {
		// populate list with both files
		CoAuth.populateCoAuthorsList("matches.html");
		CoAuth.populateCoAuthorsList("nomatches.html");
		// print the co authors
		CoAuth.printCoAuthors();
		// see if the number of authors and name is correct
		assertTrue(outContent.toString().equals("1)\nVincent Landolfi\n"));
	}

	/**
	 * Tests the print co authors functions with one matching test file and the
	 * supplied test file that has no matches
	 */
	@Test
	public void testPrinCoAuthorsWithTestFile() {
		// populate list with bost files
		CoAuth.populateCoAuthorsList("matches.html");
		CoAuth.populateCoAuthorsList("rs.html");
		// print the co authors
		CoAuth.printCoAuthors();
		// see if the number of authors and name is correct
		assertTrue(outContent.toString().equals("1)\nVincent Landolfi\n"));
	}

}
