package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import driver.PublicationsGetter;

public class PublicationsGetterTest {
	PublicationsGetter GetPub;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	/**
	 * Set the output stream to outcontent so we can see what was
	 * outputted
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	}
	/**
	 * Test printing the first three publications with sample1
	 */
	@Test
	public void testPrintFirst3Sample1() {
		// new getpulications object
		GetPub = new PublicationsGetter("sample1.html");
		// get the first three pubs printed
		GetPub.getFirstThreePublications();
		// see if they were printed properly
		assertTrue(outContent.toString()
				.equals("\t1-   Bioclipse: an open source workbench for "
						+ "chemo-and bioinformatics\n"
						+ "\t2-   The LCB data warehouse\n"
						+ "\t3-   XMPP for cloud computing in bioinformatics supporting "
						+ "discovery and invocation of asynchronous web services\n"));
	}
	/**
	 * Test printing the first three publications with sample2
	 */
	@Test
	public void testPrintFirst3Sample2() {
		// new getpublications object
		GetPub = new PublicationsGetter("sample2.html");
		// print the first three publications
		GetPub.getFirstThreePublications();
		// see if they were printed properly
		assertTrue(outContent.toString()
				.equals("\t1-   Face-tracking as an augmented input in video "
						+ "games: enhancing presence, role-playing and control\n"
						+ "\t2-   Art of defense: a collaborative handheld "
						+ "augmented reality board game\n"
						+ "\t3-   Sociable killers: understanding social relationships "
						+ "in an online first-person shooter game\n"));
	}
	/**
	 * Test printing the first three publications where not even
	 * one publication matches
	 */
	@Test
	public void testPrintFirst3NoMatches() {
		// new getpublication object with no matches in html file
		GetPub = new PublicationsGetter("nomatches.html");
		// get the publications
		GetPub.getFirstThreePublications();
		// make sure it tells that no publications were found
		assertTrue(outContent.toString()
				.equals("\tNo publications found\n"));
	}
	/**
	 * Test getting the first three publications with the supplied
	 * testing file
	 */
	@Test
	public void testPrintFirst3TestFile() {
		// new getpublications object with test file
		GetPub = new PublicationsGetter("rs.html");
		// print the first three publications
		GetPub.getFirstThreePublications();
		// make sure they were printed correctly
		assertTrue(outContent.toString()
				.equals("\t1-   On fast exploration in 2D and 3D terrains "
						+ "with multiple robots\n"
						+ "\t2-   Sonic Grid: an auditory interface for the visually "
						+ "impaired to navigate GUI-based environments\n"
						+ "\t3-   Multi robotic exploration with communication requirement"
						+ " to a fixed base station\n"));
	}
	/**
	 * Test print the first three publications on a specialized test
	 * file that contains three matches
	 */
	@Test
	public void testPrintFirst3Matches() {
		// new getpublications object using the matches test file
		GetPub = new PublicationsGetter("matches.html");
		// print the first three publications
		GetPub.getFirstThreePublications();
		// see if printed properly
		assertTrue(outContent.toString()
				.equals("\t1-   Hello\n"
						+ "\t2-   This\n"
						+ "\t3-   Works\n"));
	}

}
