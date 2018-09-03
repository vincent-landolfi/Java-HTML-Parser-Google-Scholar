package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import driver.PublicationsGetter;
import driver.CitationsGetter;

public class CitationsGetterTest {

	CitationsGetter TotPaperCite;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	/**
	 * Set the output stream so we can see what is outputted
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	}

	/**
	 * Test the print total citation method with sample1
	 */
	@Test
	public void testPrintTotalCitationSample1() {
		// new citation command with sample1
		TotPaperCite = new CitationsGetter("sample1.html");
		// print the total number of citations
		TotPaperCite.printTotalPaperCitationNum();
		// see if its correct
		assertTrue(outContent.toString().equals("239\n"));
	}

	/**
	 * Test the print total citation method with sample2
	 */
	@Test
	public void testPrintTotalCitationSample2() {
		// new citation command with sample2
		TotPaperCite = new CitationsGetter("sample2.html");
		// print the total number of citations
		TotPaperCite.printTotalPaperCitationNum();
		// see if its correct
		assertTrue(outContent.toString().equals("158\n"));
	}

	/**
	 * Test the print total citations method when there are less than 5
	 * citations
	 */
	@Test
	public void testPrintTotalCitationMatchesWithLessThanFiveCitations() {
		// new object with the created test file
		TotPaperCite = new CitationsGetter("matches.html");
		// print the number of citations
		TotPaperCite.printTotalPaperCitationNum();
		// see if its correct
		assertEquals(outContent.toString(), "47\n");
	}

	/**
	 * test the print total citations method where there is not even one match
	 */
	@Test
	public void testPrintTotalCitationNoMatches() {
		// new object with no matches in the html code
		TotPaperCite = new CitationsGetter("nomatches.html");
		// print the number of citations
		TotPaperCite.printTotalPaperCitationNum();
		// see if the number is zero
		assertTrue(outContent.toString().equals("0\n"));
	}

	/*
	 * Test print total citations method with the supplied test file
	 */
	@Test
	public void testPrintTotalCitationTestFile() {
		// new object with the supplied test file
		TotPaperCite = new CitationsGetter("rs.html");
		// print the number of citations
		TotPaperCite.printTotalPaperCitationNum();
		// see if the number is right
		assertTrue(outContent.toString().equals("33\n"));
	}

}
