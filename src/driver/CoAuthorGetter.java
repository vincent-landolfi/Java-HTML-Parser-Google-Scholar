package driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoAuthorGetter extends DataGetter {
	private ArrayList<String> coAuthors;
	private int previousSize;

	/**
	 * Doesn't assign any URL because we need to use more than one. Instantiates
	 * an arraylist to hold all the names of the co-authors, and a previous
	 * size, to know where to start.
	 */
	public CoAuthorGetter() {
		// just have a blank string for now
		super("");
		// make an empty arraylist
		this.coAuthors = new ArrayList<String>();
		// size of the previous arraylist
		previousSize = 0;
	}

	/**
	 * Adds all the co-authors from the given page to the coauthors arraylist
	 * 
	 * @param URL.
	 *            A string that takes the URL or file to get information from.
	 */
	public void populateCoAuthorsList(String URL) {
		try {
			// set the url to the given string
			this.URL = URL;
			// new parser
			MyParser googleScholarParser = new MyParser();
			// get the html string from the url
			String rawHTMLString = googleScholarParser.getHTML(this.URL);
			// regex to find the co authors
			String reForAuthorExtraction = "<a class=\"cit-dark-link\" href=\"[^\"]*\" title=\"(.*?)\">(.*?)</a>";
			// compile a pattern to look for regexes
			Pattern patternObject = Pattern.compile(reForAuthorExtraction);
			// start a matcher so we can find matches
			Matcher matcherObject = patternObject.matcher(rawHTMLString);
			// while there are matches
			while (matcherObject.find()) {
				// add the second group to the list, which is the name
				this.coAuthors.add(matcherObject.group(2));
			}
			// if we get an error
		} catch (Exception e) {
			// not a good URL
			System.out.println("malformed URL or cannot open connection to " + "given URL");
		}

	}

	/**
	 * Prints the number of co-authors at the given URL
	 */
	public void printNumCoAuthors() {
		// print out the size of the current list of co authors
		System.out.print(Integer.toString(this.coAuthors.size() - previousSize));
		// add the current size to the previous size, so the next time we call
		// it it will be that list's size
		previousSize += this.coAuthors.size();
	}

	/**
	 * Returns back the list of co-authors
	 */
	public ArrayList<String> getCoAuthors() {
		return coAuthors;
	}

	/**
	 * Prints the names of all the co-authors from each URL that has been used
	 * with the populate co-authors list
	 */
	public void printCoAuthors() {
		// sort the list
		Collections.sort(this.coAuthors);
		// print out the size of the list
		System.out.println(Integer.toString(this.coAuthors.size()) + ")");
		// go through the list
		for (int i = 0; i < this.coAuthors.size(); i++) {
			// print out each element
			System.out.println(this.coAuthors.get(i));
		}

	}
}
