package hw9;

import hw8.*;

/**
 * CampusPathsMain is used to run the full Campus Pathfinder application.
 *
 */

public class CampusPathsMain {

	/*
	 * CampusPathsMain does not have a Rep invariant, AF, or checkRep() because
	 * it is not an ADT.
	 */

	// Combines the model, view, and controller to run the final Campus
	// Pathfinder.
	public static void main(String[] args) throws Exception {

		// return a GUI using the UW campus data
		CampusPaths cp = new CampusPaths("src/hw8/data/campus_buildings.dat", "src/hw8/data/campus_paths.dat");
		new GUI(cp);
	}
}
