package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CitationsGetter extends DataGetter {
	/**
	 * Uses the super class to set the url string
	 * 
	 * @param URL
	 *            A URL or file name
	 */
	public CitationsGetter(String URL) {
		super(URL);
	}

	/**
	 * Prints the number of the first five citations added together at the given
	 * URL or file
	 */
	public void printTotalPaperCitationNum() {
		try {
			// make a new parser
			MyParser googleScholarParser = new MyParser();
			// get the stirng of the raw html file
			String rawHTMLString = googleScholarParser.getHTML(this.URL);
			// regex that matches all values in the table
			String reForAuthorExtraction = "<td id=\"col-citedby\"><a class=\"cit-dark-link\" href=\"(.*?)\">(\\d*)</a></td>";
			// new pattern object that compiles with my regex
			Pattern patternObject = Pattern.compile(reForAuthorExtraction);
			// new matcher to match the regex and the html string
			Matcher matcherObject = patternObject.matcher(rawHTMLString);
			// make an int to add the citations
			int cite = 0;
			// while there are strings that match the regex
			for (int i = 0; matcherObject.find() && i < 5; i++) {
				// add the citation number matching the regex
				cite += Integer.parseInt(matcherObject.group(2));
			}
			System.out.println(Integer.valueOf(cite));
			// catch any exception
		} catch (Exception e) {
			// error message
			System.out.println("malformed URL or cannot open connection to " + "given URL");
		}
	}
}