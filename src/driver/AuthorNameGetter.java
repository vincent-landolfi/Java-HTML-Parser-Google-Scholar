package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorNameGetter extends DataGetter {
	/**
	 * Uses the super class to set the url string
	 * 
	 * @param URL
	 *            A URL or file name
	 */
	public AuthorNameGetter(String URL) {
		// make the string the given URL
		super(URL);
	}

	/**
	 * Prints the name of the author at the given URL or file
	 */
	public void printAuthorName() {
		try {
			// boolean if we found a match
			boolean found = false;
			// new parser
			MyParser googleScholarParser = new MyParser();
			// get the html string from the url
			String rawHTMLString = googleScholarParser.getHTML(this.URL);
			// regex for finding author name
			String reForAuthorExtraction = "<span id=\"cit-name-display\" "
					+ "class=\"cit-in-place-nohover\">(.*?)</span>";
			// compile pattern to look for regex
			Pattern patternObject = Pattern.compile(reForAuthorExtraction);
			// make a match to find strings matching the regex
			Matcher matcherObject = patternObject.matcher(rawHTMLString);
			// while there are matches
			while (matcherObject.find()) {
				// we found something
				found = true;
				// print out the first group
				System.out.println(matcherObject.group(1));
			}
			// if nothing was found
			if (!found) {
				// print not found
				System.out.println("No author found");
			}
			// if theres an error
		} catch (Exception e) {
			// not a good URL
			System.out.println("malformed URL or cannot open connection to "
			+ "given URL");
		}
	}
}
