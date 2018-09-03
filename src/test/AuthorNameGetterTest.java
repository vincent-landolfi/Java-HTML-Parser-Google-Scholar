package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import driver.AuthorNameGetter;

public class AuthorNameGetterTest {
	AuthorNameGetter name;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	/*
	 * Set the output stream to a string so we can see what was printed
	 */
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	}

	/**
	 * Test getting the author name with sample1.html
	 */
	@Test
	public void testPrintSample1Name() {
		// new get author command
		name = new AuthorNameGetter("sample1.html");
		// print the name
		name.printAuthorName();
		// see if its right
		assertTrue(outContent.toString().equals("Ola Spjuth\n"));
	}

	/**
	 * Test getting the author name with sample2.html
	 */
	@Test
	public void testPrintSample2Name() {
		// new get author command
		name = new AuthorNameGetter("sample2.html");
		// print the name
		name.printAuthorName();
		// see if its right
		assertTrue(outContent.toString().equals("Yan Xu\n"));
	}

	/**
	 * Test getting the author name with a file that was made for testing, which
	 * contains a matching name
	 */
	@Test
	public void testPrintNameMatchingRegex() {
		// new get author command
		name = new AuthorNameGetter("matches.html");
		// print the author name
		name.printAuthorName();
		// see if its right
		assertTrue(outContent.toString().equals("Vincent Landolfi\n"));
	}

	/**
	 * Test getting the author name with the supplied testing file
	 */
	@Test
	public void testPrintNameTestFile() {
		// new get author command
		name = new AuthorNameGetter("rs.html");
		// print the name
		name.printAuthorName();
		// see if its right
		assertTrue(outContent.toString().equals("rahul sawhney\n"));
	}

	/**
	 * Test getting the author name with a file that was made for testing that
	 * does not contain any matches
	 */
	@Test
	public void testPrintNameNonMatchingRegex() {
		// new get author with no matches
		name = new AuthorNameGetter("nomatches.html");
		// print author name
		name.printAuthorName();
		// should say no author was found
		assertTrue(outContent.toString().equals("No author found\n"));
	}
}
