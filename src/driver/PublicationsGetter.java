package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PublicationsGetter extends DataGetter {
	/**
	 * Uses the super class to set the url string
	 * 
	 * @param URL
	 *            A URL or file name
	 */
	public PublicationsGetter(String URL) {
		super(URL);
	}

	/**
	 * Prints the first three publications at the given URL or file
	 */
	public void getFirstThreePublications() {
		try {
			// boolean if we found something
			boolean found = false;
			// make a new parser
			MyParser googleScholarParser = new MyParser();
			// get the stirng of the raw html file
			String rawHTMLString = googleScholarParser.getHTML(this.URL);
			// regex that matches all values in the table
			String reForAuthorExtraction = "<a href=\"(.*?)\" class=\"cit-dark-large-link\">(.*?)</a>";
			// new pattern object that compiles with my regex
			Pattern patternObject = Pattern.compile(reForAuthorExtraction);
			// new matcher to match the regex and the html string
			Matcher matcherObject = patternObject.matcher(rawHTMLString);
			// while there are strings that match the regex
			for (int i = 1; matcherObject.find() && i < 4; i++) {
				// say we found something
				found = true;
				// print out the match which is the second group in this case
				System.out.println("\t" + Integer.toString(i) + "-   " + matcherObject.group(2));
			}
			// if nothing was found
			if (!found) {
				// say nothing was found
				System.out.println("\tNo publications found");
			}
			// catch any exception
		} catch (Exception e) {
			// error message
			System.out.println("malformed URL or cannot open connection to " + "given URL");
		}
	}
}
