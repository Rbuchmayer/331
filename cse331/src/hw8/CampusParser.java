package hw8;

import java.io.*;
import java.util.*;

import hw5.*;

/**
 * Parser utility to parse the UW campus data
 */
public class CampusParser {

	/**
	 * Reads and parses the UW campus buildings file. Each line in the file
	 * contains two string tokens followed by two double tokens, all separated
	 * by a tab.
	 * 
	 * @param buildings
	 *            correct path to file containing the data
	 * @param abbToFull
	 *            Map to add mappings from abbreviated name to full name
	 * @param locations
	 *            Map to add mappings from abbreviated name to Location
	 * @param fullToAbb
	 *            Map to add mappings from full name to abbreviated name
	 * @requires buildings != null, abbToFull != null, locations != null, and
	 *           fullToAbb != null.
	 * @modifies abbToFull, locations, and fullToAbb
	 * @effects fills abbToFull with mappings from abbreviated name to full
	 *          name.
	 * @effects fills fullToAbb with mappings from full name to abbreviated
	 *          name.
	 * @effects fills locations with mappings from abbreviated name to its
	 *          Location.
	 * @throws Exception
	 *             if the file is not well formed. In a well formed file, each
	 *             line contains exactly four tokens separated by a tab, or else
	 *             starting with a # symbol to indicate a comment line.
	 */
	public static void parseData(String buildings, Map<String, String> abbToFull, Map<String, Location> locations,
			Map<String, String> fullToAbb) throws Exception {

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(buildings));
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {

				if (inputLine.startsWith("#")) {
					continue;
				}
				inputLine = inputLine.replace("\"", "");
				String[] tokens = inputLine.split("\t");
				if (tokens.length != 4) {
					throw new Exception("Line should contain one tab between " + "each pair of tokens: " + inputLine);
				}

				// get the tokens
				String abbreviated = tokens[0];
				String name = tokens[1];
				double x_coor = Double.parseDouble(tokens[2]);
				double y_coor = Double.parseDouble(tokens[3]);

				// add the name mappings to the maps
				abbToFull.put(abbreviated, name);
				fullToAbb.put(name, abbreviated);

				// add coordinates to locations
				locations.put(abbreviated, new Location(x_coor, y_coor));

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

	/**
	 * Reads and parses the UW campus paths file. Each entry contains a
	 * coordinate followed by tab indented lines containing an end point, colon,
	 * and distance to that end point.
	 * 
	 * @param paths
	 *            correct path to the file containing the UW campus paths
	 * @param campusGraph
	 *            Graph to fill with parsed data
	 * @requires paths != null and campusGraph != null
	 * @modifies campusGraph
	 * @effects fills campusGraph with building Locations connected by distances
	 *          (doubles).
	 * @throws Exception
	 *             if the file is not well formed. In a well formed file, each
	 *             coordinate should be followed by tab indented lines
	 *             containing an end coordinate, colon, space, and distance.
	 */
	public static void makeGraph(String paths, Graph<Location, Double> campusGraph) throws Exception {

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(paths));
			String inputLine;
			Location loc = null;
			while ((inputLine = reader.readLine()) != null) {

				if (inputLine.startsWith("#")) {
					continue;
				}

				// trim input
				inputLine = inputLine.replace("\"", "");
				inputLine = inputLine.replace("\t", "");
				String[] tokens = inputLine.split(": ");

				// get the tokens
				String[] coordinates = tokens[0].split(",");
				double x = Double.parseDouble(coordinates[0]);
				double y = Double.parseDouble(coordinates[1]);
				Location l = new Location(x, y);

				// add the coordinate to the Graph
				if (tokens.length == 1) {
					loc = l;
					if (!campusGraph.contains(l)) {
						campusGraph.addNode(l);
					}
				}

				// add other coordinate to the Graph
				else if (tokens.length == 2) {
					if (loc == null) {
						throw new Exception("File is not well-formed!");
					}
					if (!campusGraph.contains(l)) {
						campusGraph.addNode(l);
					}
					double distance = Double.parseDouble(tokens[1]);
					campusGraph.addEdge(loc, new Edge<Location, Double>(l, distance));
				}

				// not well-formed
				else {
					throw new Exception("File is not well-formed!");
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
