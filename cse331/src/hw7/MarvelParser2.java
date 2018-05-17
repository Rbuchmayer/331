package hw7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MarvelParser2 {

	/*
	 * MarvelParser2 does not have an AF, RI, or checkRep() because all its
	 * methods are static, and we will not create MarvelParser2 objects
	 */

	/**
	 * Reads and parses the Marvel (tsv) data file 
	 * 
	 * @param file
	 *            correct path to the file to parse
	 * @param counts
	 *            map to add relationships to
	 * @modifies counts
	 * @effects adds characters to counts that are mapped to other characters
	 *          and the number of books they share
	 * 
	 * @throws Exception
	 *             if the file is not well-formed: each line contains exactly
	 *             two tokens separated by a tab, or else starting with a #
	 *             symbol to indicate a comment line.
	 */
	public static void parseData(String file, Map<String, HashMap<String, Integer>> counts) throws Exception {

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));

			// initialize map to keep track of books and characters
			HashMap<String, ArrayList<String>> bookMap = new HashMap<String, ArrayList<String>>();

			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				// Ignore comment lines.
				if (inputLine.startsWith("#")) {
					continue;
				}

				// Parse the data, stripping out quotation marks and throwing
				// an exception for malformed lines.
				inputLine = inputLine.replace("\"", "");
				String[] tokens = inputLine.split("\t");
				if (tokens.length != 2) {
					throw new IllegalArgumentException("Line should contain exactly one tab: " + inputLine);
				}

				String character = tokens[0];
				String book = tokens[1];

				// add character to counts map if not already in
				if (!counts.containsKey(character)) {
					counts.put(character, new HashMap<String, Integer>());
				}

				// add book and character to bookMap map if not already in
				if (!bookMap.containsKey(book)) {
					ArrayList<String> chars = new ArrayList<String>();
					chars.add(character);
					bookMap.put(book, chars);
				} else {

					// book is not in bookList map, so update bookMap
					ArrayList<String> chars = bookMap.get(book);
					if (!chars.contains(character)) {
						HashMap<String, Integer> subMap = counts.get(character);
						for (String c1 : chars) {
							if (!subMap.containsKey(c1)) {
								subMap.put(c1, 1);
							} else {
								subMap.put(c1, subMap.get(c1) + 1);
							}

							// update other character
							HashMap<String, Integer> count = counts.get(c1);
							if (!count.containsKey(character)) {
								count.put(character, 1);
							} else {
								count.put(character, count.get(character) + 1);

							}
						}
						// update bookMap
						chars.add(character);
						bookMap.put(book, chars);
					}
				}
			}
		} catch (IOException e) {
			System.err.println(e.toString());
			e.printStackTrace(System.err);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

}
