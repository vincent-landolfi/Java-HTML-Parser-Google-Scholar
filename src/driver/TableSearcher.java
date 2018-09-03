package driver;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableSearcher extends DataGetter {
	/**
	 * Uses the super class to set the url string
	 * 
	 * @param URL
	 *            A URL or file name
	 */
	public TableSearcher(String URL) {
		super(URL);
	}

	/**
	 * Prints either the wanted citation or i10 value from the table in the top
	 * of the html file
	 * 
	 * @param value.
	 *            Either "i10" or "citations" so the method knows which number
	 *            to get
	 */
	public void printValueInTable(String value) {
		try {
			// new boolean to tell us if we found a match
			boolean found = false;
			// make a new parser
			MyParser googleScholarParser = new MyParser();
			// get the stirng of the raw html file
			String rawHTMLString = googleScholarParser.getHTML(this.URL);
			// regex that matches all values in the table
			String reForAuthorExtraction = "<td class=\"cit-borderleft cit-data\">" + "(\\d*)</td>";
			// new pattern object that compiles with my regex
			Pattern patternObject = Pattern.compile(reForAuthorExtraction);
			// new matcher to match the regex and the html string
			Matcher matcherObject = patternObject.matcher(rawHTMLString);
			// if we're looking for the i10 value
			if (value.equals("i10")) {
				// new arraylist to hold the values in the table
				ArrayList<String> tableValues = new ArrayList<String>();
				// if no matches
				// while there are more matches
				while (matcherObject.find()) {
					// say we found something
					found = true;
					// add the found value to the array list
					tableValues.add(matcherObject.group(1));
				}
				// if we can't find anything
				if (!found) {
					// just print 0 matches
					System.out.println("0");
				}
				// check if we found anything
				if (tableValues.size() > 0) {
					// print the last value in the table
					System.out.println(tableValues.get(tableValues.size() - 1));
				}
				// if we're looking for the citation value
			} else if (value.equals("citations")) {
				// just look at the first value that matches
				for (int i = 0; matcherObject.find() && i < 1; i++) {
					// we found something
					found = true;
					// print it out
					System.out.println(matcherObject.group(1));
				}
				// if we can't find anything
				if (!found) {
					// just print 0 matches
					System.out.println("0");
				}
				// if its not a preset search value its invalid
			} else {
				// print a small error message
				System.out.println("Invalid parameter");
			}
			// catch any exception
		} catch (Exception e) {
			// error message
			System.out.println("malformed URL or cannot open connection to " + "given URL");
		}
	}
}
