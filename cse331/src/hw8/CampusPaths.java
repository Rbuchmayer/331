package hw8;

import java.util.*;

import hw5.*;
import hw7.*;

/**
 * CampusPaths stores information regarding the UW campus and finds shortest
 * paths
 *
 */

public class CampusPaths {

	/*
	 * Representative Invariant: abbToFull != null, fullToAbb != null,
	 * campusGraph != null, locations != null, no key or value in any map is
	 * null, and no node or edge in the Graph is null.
	 * 
	 * Abstraction Function: A CampusPaths object represents a CampusGraph cp
	 * such that: cp.building_locations = this.locations, cp.building_names =
	 * this.abbToFull, and cp.campusMap = this.campusGraph.
	 * 
	 */

	// debug flag for checkRep()
	private static final boolean DEBUG = false;

	// map to hold abbreviated names mapped to full names
	private Map<String, String> abbToFull;

	// map to hold full names mapped to abbreviated names
	private Map<String, String> fullToAbb;

	// Graph to represent the campus
	private Graph<Location, Double> campusGraph;

	// map to hold buildings mapped to their locations
	private Map<String, Location> locations;

	/**
	 * Constructs a new CampusPaths Object given buildings and paths
	 * 
	 * @param names
	 *            file containing the building names data
	 * @param paths
	 *            file containing the path data
	 * 
	 * @requires Both file paths lead to the correct file, and both files are
	 *           well formed. In a well formed file, each line contains the
	 *           correct number of tokens separated by tabs, or else starting
	 *           with a # symbol to indicate a comment line.
	 * @throws IllegalArgumentException
	 *             if either argument is null
	 */
	public CampusPaths(String names, String paths) throws Exception {
		if (names == null || paths == null) {
			throw new IllegalArgumentException("Neither names or paths can be null!");
		}

		// initialize and parse data
		this.abbToFull = new HashMap<String, String>();
		this.campusGraph = new Graph<Location, Double>();
		this.locations = new HashMap<String, Location>();
		this.fullToAbb = new HashMap<String, String>();
		checkRep();
		CampusParser.parseData(names, abbToFull, locations, fullToAbb);
		CampusParser.makeGraph(paths, campusGraph);
	}

	/**
	 * Finds the shortest path between two buildings
	 * 
	 * @param start
	 *            Location of building to start at
	 * @param end
	 *            Location of building to end at
	 * @require start != null, end != null, and start/end are UW building
	 *          Locations.
	 * @return The shortest path from start to dest, or null if no path
	 * @throws IllegalArgumentException
	 *             if either argument is null
	 */
	public Map<Location, Double> findPath(Location start, Location dest) {
		checkRep();
		if (start == null || dest == null) {
			throw new IllegalArgumentException("One of the arguments is null!");
		}

		// find shortest path
		List<Edge<Location, Double>> path = MarvelPaths2.findPath(campusGraph, start, dest);

		// if there is no path
		if (path == null) {
			return null;
		}

		path.remove(0);

		// store the path in a LinkedHashMap to return
		Map<Location, Double> ret = new LinkedHashMap<Location, Double>();
		for (Edge<Location, Double> e : path) {
			ret.put(e.getChild(), e.getLabel());
		}
		checkRep();
		return ret;
	}

	/**
	 * Prints the buildings in the UW campus alphabetically in abbreviated form
	 * followed by full form.
	 */
	public void printBuildings() {
		Map<String, String> sorted = new TreeMap<String, String>();
		sorted.putAll(this.abbToFull);
		System.out.println("Buildings:");
		for (String b : sorted.keySet()) {
			System.out.println("\t" + b + ": " + sorted.get(b));
		}
		checkRep();
	}

	/**
	 * Returns a Set of all the buildings
	 * 
	 * @return A Set containing all the full names of the UW buildings
	 */
	public Set<String> getBuildings() {
		Map<String, String> buildings1 = new HashMap<String, String>();
		buildings1.putAll(this.fullToAbb);
		Set<String> buildings = new HashSet<String>();
		buildings.addAll(buildings1.keySet());
		return buildings;
	}

	/**
	 * Returns the full name of a UW building given the abbreviated name.
	 * 
	 * @param abbreviated
	 *            Abbreviated name of building.
	 * @requires abbreviated != null
	 * @return Full name of the building.
	 * @throws IllegalArgumentException
	 *             if the building is not at UW
	 */
	public String getFullBuildingName(String abbreviated) {
		if (!abbToFull.containsKey(abbreviated)) {
			throw new IllegalArgumentException("building is not at UW!");
		}
		checkRep();
		return this.abbToFull.get(abbreviated);
	}

	/**
	 * Returns the abbreviated name of a UW building
	 * 
	 * @param fullName
	 *            The full name of the building
	 * @return The abbreviated name of the building
	 * @require fullName != null
	 * @throws IllegalArgumentException
	 *             if the building is not at UW
	 */
	public String getAbbreviatedName(String fullName) {
		if (!fullToAbb.containsKey(fullName)) {
			throw new IllegalArgumentException("building is not at UW!");
		}
		checkRep();
		return this.fullToAbb.get(fullName);
	}

	/**
	 * Returns the Location of a UW building
	 * 
	 * @param building
	 *            The building to get the Location of
	 * @return The Location of the UW building
	 * @requires building != null and building is in the UW data set.
	 * 
	 */
	public Location getLocation(String building) {
		checkRep();
		return this.locations.get(building);
	}

	// to make sure rep invariant holds
	private void checkRep() {
		assert (abbToFull != null) : "abbToFull is null!";
		assert (fullToAbb != null) : "fullToAbb is null!";
		assert (locations != null) : "locations is null!";
		assert (campusGraph != null) : "campusGraph is null!";

		// go through all data structures and check for null
		if (DEBUG) {
			for (String s : abbToFull.keySet()) {
				assert (s != null);
				assert (abbToFull.get(s) != null);
			}
			for (String s : fullToAbb.keySet()) {
				assert (s != null);
				assert (fullToAbb.get(s) != null);
			}
			for (String s : locations.keySet()) {
				assert (s != null);
				assert (locations.get(s) != null);
			}
			Set<Location> locs = campusGraph.getNodes();
			for (Location l : locs) {
				assert (l != null);
				Set<Edge<Location, Double>> edges = campusGraph.getOutgoingEdges(l);
				for (Edge<Location, Double> e : edges) {
					assert (e != null);
				}
			}
		}
	}
}
