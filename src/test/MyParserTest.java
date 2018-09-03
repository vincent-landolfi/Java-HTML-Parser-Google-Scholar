package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import driver.CitationsGetter;
import driver.MyParser;

public class MyParserTest {
	MyParser parse;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	/**
	 * Make a new parser, as well as set the output stream so we can see what
	 * the methods output
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		parse = new MyParser();
		System.setOut(new PrintStream(outContent));
	}

	/**
	 * Test the get html command with a small html file, just make sure the
	 * string is correct
	 */
	@Test
	public void testGetHTML() {
		// new string
		String html = "";
		try {
			// try to parse created file
			html = parse.getHTML("nomatches.html");
			// if error
		} catch (Exception e) {
			// print the stack trace
			e.printStackTrace();
		}
		// see if it reads the code
		assertEquals(html, "<span> no matches here </span>");
	}

	/*
	 * Tests the print info method in my parser using sample1,sample2 as the
	 * parameters
	 */
	@Test
	public void testPrintInfoWithSamples() {
		// set parameters
		String[] args = { "sample1.html,sample2.html" };
		// call the method
		MyParser.printInfo(args);
		// set the output string to what we want
		String output;
		// sample1
		output = "-----------------------------------------" 
		+ "------------------------------\n";
		output += "1. Name of Author:\n";
		output += "\tOla Spjuth\n";
		output += "2. Number of All Citations:\n";
		output += "\t437\n";
		output += "3. Number of i10-index after 2009:\n";
		output += "\t12\n";
		output += "4. Title of the first 3 publications:\n";
		output += "\t1-   Bioclipse: an open source workbench " 
		+ "for chemo-and bioinformatics\n";
		output += "\t2-   The LCB data warehouse\n";
		output += "\t3-   XMPP for cloud computing in bioinformatics supporting " 
		+ "discovery and invocation"
				+ " of asynchronous web services\n";
		output += "5. Total paper citation (first 5 papers):\n";
		output += "\t239\n";
		output += "6. Total Co-Authors:\n";
		output += "\t15\n";
		output += "-----------------------------------------"
		+ "------------------------------\n";
		// sample2
		output += "1. Name of Author:\n";
		output += "\tYan Xu\n";
		output += "2. Number of All Citations:\n";
		output += "\t263\n";
		output += "3. Number of i10-index after 2009:\n";
		output += "\t9\n";
		output += "4. Title of the first 3 publications:\n";
		output += "\t1-   Face-tracking as an augmented input in video games:"
				+ " enhancing presence, role-playing and control\n";
		output += "\t2-   Art of defense: a collaborative handheld " 
				+ "augmented reality board game\n";
		output += "\t3-   Sociable killers: understanding social relationships in"
				+ " an online first-person shooter game\n";
		output += "5. Total paper citation (first 5 papers):\n";
		output += "\t158\n";
		output += "6. Total Co-Authors:\n";
		output += "\t14\n";
		output += "\n";
		output += "-----------------------------------------" 
		+ "------------------------------\n";
		// co-authors from both
		output += "7. Co-Author list sorted (Total: 29)\n";
		output += "Abigail Sellen\n";
		output += "Adam Ameur\n";
		output += "Andrew D Miller\n";
		output += "Antony John Williams\n";
		output += "Blair MacIntyre\n";
		output += "Christoph Steinbeck\n";
		output += "Deepak Jagdish\n";
		output += "E.D. Mynatt\n";
		output += "Egon Willighagen\n";
		output += "Elsa Eiriksdottir\n";
		output += "Erika Shehan Poole\n";
		output += "Greg Turk\n";
		output += "Iulian Radu\n";
		output += "Janna Hastings\n";
		output += "John Stasko\n";
		output += "Jonathan Alvarsson\n";
		output += "Komorowski Jan\n";
		output += "Kurt Luther\n";
		output += "Nina Jeliazkova\n";
		output += "Noel M. O'Boyle\n";
		output += "Rajarshi Guha\n";
		output += "Sam Adams\n";
		output += "Samuel Lampa\n";
		output += "Sean Ekins\n";
		output += "Thore Graepel\n";
		output += "Valentin Georgiev\n";
		output += "Xiang Cao\n";
		output += "Youn-ah Kang\n";
		output += "gilleain torrance\n";
		output += "\n";
		// see if we get the wanted output
		assertEquals(output, outContent.toString());
	}

	/**
	 * Tests the print info method with my two created html files
	 */
	@Test
	public void testPrintInfoWithMadeHTML() {
		// set parameters
		String[] args = { "matches.html,nomatches.html" };
		// call printInfo with those parameters
		MyParser.printInfo(args);
		// set the ouput string to what we want
		String output;
		// matches file
		output = "-----------------------------------------" 
		+ "------------------------------\n";
		output += "1. Name of Author:\n";
		output += "\tVincent Landolfi\n";
		output += "2. Number of All Citations:\n";
		output += "\t99\n";
		output += "3. Number of i10-index after 2009:\n";
		output += "\t66\n";
		output += "4. Title of the first 3 publications:\n";
		output += "\t1-   Hello\n";
		output += "\t2-   This\n";
		output += "\t3-   Works\n";
		output += "5. Total paper citation (first 5 papers):\n";
		output += "\t47\n";
		output += "6. Total Co-Authors:\n";
		output += "\t1\n";
		output += "------------------------------------" 
		+ "-----------------------------------\n";
		// no matches file
		output += "1. Name of Author:\n";
		output += "\tNo author found\n";
		output += "2. Number of All Citations:\n";
		output += "\t0\n";
		output += "3. Number of i10-index after 2009:\n";
		output += "\t0\n";
		output += "4. Title of the first 3 publications:\n";
		output += "\tNo publications found\n";
		output += "5. Total paper citation (first 5 papers):\n";
		output += "\t0\n";
		output += "6. Total Co-Authors:\n";
		output += "\t0\n";
		output += "\n";
		output += "--------------------------------------"
		+ "---------------------------------\n";
		output += "7. Co-Author list sorted (Total: 1)\n";
		output += "Vincent Landolfi\n";
		output += "\n";
		assertEquals(output, outContent.toString());
	}

	/**
	 * Tests the print info method with a created test file and the supplied
	 * test file
	 */
	@Test
	public void testPrintInfoWithCreatedAndSupplied() {
		// set parameters
		String[] args = { "matches.html,rs.html" };
		// call printInfo with those parameters
		MyParser.printInfo(args);
		// set the ouput string to what we want
		String output;
		// matches file
		output = "----------------------------------" 
		+ "-------------------------------------\n";
		output += "1. Name of Author:\n";
		output += "\tVincent Landolfi\n";
		output += "2. Number of All Citations:\n";
		output += "\t99\n";
		output += "3. Number of i10-index after 2009:\n";
		output += "\t66\n";
		output += "4. Title of the first 3 publications:\n";
		output += "\t1-   Hello\n";
		output += "\t2-   This\n";
		output += "\t3-   Works\n";
		output += "5. Total paper citation (first 5 papers):\n";
		output += "\t47\n";
		output += "6. Total Co-Authors:\n";
		output += "\t1\n";
		output += "-----------------------------------" 
		+ "------------------------------------\n";
		// no matches file
		output += "1. Name of Author:\n";
		output += "\trahul sawhney\n";
		output += "2. Number of All Citations:\n";
		output += "\t33\n";
		output += "3. Number of i10-index after 2009:\n";
		output += "\t1\n";
		output += "4. Title of the first 3 publications:\n";
		output += "\t1-   On fast exploration in 2D and 3D terrains" 
		+ " with multiple robots\n"
				+ "\t2-   Sonic Grid: an auditory interface " 
		+ "for the visually impaired "
				+ "to navigate GUI-based environments\n" 
		+ "\t3-   Multi robotic exploration with"
				+ " communication requirement to a " 
		+ "fixed base station\n";
		output += "5. Total paper citation (first 5 papers):\n";
		output += "\t33\n";
		output += "6. Total Co-Authors:\n";
		output += "\t0\n";
		output += "\n";
		output += "---------------------------------------------"
		+ "--------------------------\n";
		output += "7. Co-Author list sorted (Total: 1)\n";
		output += "Vincent Landolfi\n";
		output += "\n";
		assertEquals(output, outContent.toString());
	}

}
