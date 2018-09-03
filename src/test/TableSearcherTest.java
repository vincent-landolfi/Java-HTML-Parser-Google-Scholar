package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import driver.CitationsGetter;
import driver.TableSearcher;

public class TableSearcherTest {
	
	TableSearcher search;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	/**
	 * Set the output stream so we can see the output
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	}
	/**
	 * Test the print value in table method with the citation cell
	 * from sample 1
	 */
	@Test
	public void testPrintNumCitationsSample1() {
		// new search table with sample1
		search = new TableSearcher("sample1.html");
		// looking for citations value
		search.printValueInTable("citations");
		// see if we have correct value
		assertEquals(outContent.toString(),"437\n");
	}
	/**
	 * Test the print value in table method with the i10 cell
	 * from sample 1
	 */
	@Test
	public void testPrintI10IndexSample1() {
		// new search table with sample1
		search = new TableSearcher("sample1.html");
		// looking for i10 value
		search.printValueInTable("i10");
		// see if we have correct value
		assertEquals(outContent.toString(),"12\n");
	}
	/**
	 * Test the print value in table method with the citation cell
	 * from sample 2
	 */
	@Test
	public void testPrintNumCitationsSample2() {
		// new search table with sample2
		search = new TableSearcher("sample2.html");
		// looking for citations value
		search.printValueInTable("citations");
		// see if we have correct value
		assertEquals(outContent.toString(),"263\n");
	}
	/**
	 * Test the print value in table method with the i10 cell
	 * from sample 2
	 */
	@Test
	public void testPrintI10IndexSample2() {
		// new search table with sample2
		search = new TableSearcher("sample2.html");
		// looking for i10 value
		search.printValueInTable("i10");
		// see if we have correct value
		assertEquals(outContent.toString(),"9\n");
	}
	/**
	 * Test the print value in table method with the citations cell
	 * from supplied test file
	 */
	@Test
	public void testPrintNumCitationsTestFile() {
		// new search table with test file
		search = new TableSearcher("rs.html");
		// looking for citations value
		search.printValueInTable("citations");
		// see if we have correct value
		assertEquals(outContent.toString(),"33\n");
	}
	/**
	 * Test the print value in table method with the i10 cell
	 * from supplied test file
	 */
	@Test
	public void testPrintI10IndexTestFile() {
		// new search table with supplied test file
		search = new TableSearcher("rs.html");
		// looking for i10 value
		search.printValueInTable("i10");
		// see if we have the correct value
		assertEquals(outContent.toString(),"1\n");
	}
	/**
	 * Test the print value in table method with the citations cell
	 * from created test file with matches
	 */
	@Test
	public void testPrintNumCitationsMatchingRegex() {
		// new search table with matches test file
		search = new TableSearcher("matches.html");
		// looking for citation value
		search.printValueInTable("citations");
		// see if we have correct value
		assertEquals(outContent.toString(),"99\n");
	}
	/**
	 * Test the print value in table method with the i10 cell
	 * from created test file with matches
	 */
	@Test
	public void testPrintI10IndexMatchingRegex() {
		// new search table with matching test file
		search = new TableSearcher("matches.html");
		// looking for i10 value
		search.printValueInTable("i10");
		// see if we have the correct value
		assertEquals(outContent.toString(),"66\n");
	}
	/**
	 * Test the print value in table method with the invalid cell
	 * from any file
	 */
	@Test
	public void testPrintInvalid() {
		// new search table with random file
		search = new TableSearcher("rs.html");
		// invalid parameter
		search.printValueInTable("nomatch");
		// see if we get correct output
		assertEquals(outContent.toString(),"Invalid parameter\n");
	}
	/**
	 * Test the print value in table method with the citations cell
	 * from created test file with no matches
	 */
	@Test
	public void testPrintNumCitationsNoMatch() {
		// new search table with no match file
		search = new TableSearcher("nomatches.html");
		// looking for citations value
		search.printValueInTable("citations");
		// see if we print 0
		assertEquals(outContent.toString(),"0\n");
	}
	/**
	 * Test the print value in table method with the i10 cell
	 * from created test file with no matches
	 */
	@Test
	public void testPrintI10IndexNoMatch() {
		// new search table with no match file
		search = new TableSearcher("nomatches.html");
		// looking for i10 value
		search.printValueInTable("i10");
		// see if we print 0
		assertEquals(outContent.toString(),"0\n");
	}
}
