		package hw8;

import java.util.*;

/**
 * CampusPathsMain is the controller and view. It allows users to interact with
 * the UW building data.
 *
 */

public class CampusPathsMain {

	// main method to allow user interaction
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			CampusPaths cp = new CampusPaths("src/hw8/data/campus_buildings.dat", "src/hw8/data/campus_paths.dat");

			String menu = "Menu:" + "\n" + "\t" + "r to find a route" + "\n" + "\t" + "b to see a list of all buildings"
					+ "\n" + "\t" + "q to quit" + "\n";

			System.out.println(menu);
			System.out.print("Enter an option ('m' to see the menu): ");

			while (true) {
				String ans = sc.nextLine();

				// echo empty lines and comments
				if (ans.startsWith("#") || ans.length() == 0) {
					System.out.println(ans);
					continue;
				}

				if (ans.equals("b")) {
					cp.printBuildings();
					System.out.println();
				} else if (ans.equals("m")) {
					System.out.println(menu);
				} else if (ans.equals("r")) {
					System.out.print("Abbreviated name of starting building: ");
					String start = sc.nextLine();
					System.out.print("Abbreviated name of ending building: ");
					String end = sc.nextLine();
					printPath(cp, start, end);
				} else if (ans.equals("q")) {
					sc.close();
					return;
				} else {
					System.out.println("Unknown option");
					System.out.println();
				}
				System.out.print("Enter an option ('m' to see the menu): ");
			}
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Prints the path from the start of a building to the end
	 * 
	 * @param cp
	 *            The CampusPaths to use
	 * @param start
	 *            the building to start at
	 * @param dest
	 *            the building to end at
	 * @throws IllegarlArgumentException
	 *             if start, dest, or cp is null.
	 */
	public static void printPath(CampusPaths cp, String start, String dest) {
		if (cp == null || start == null || dest == null) {
			throw new IllegalArgumentException("One of the arguments is null!");
		}

		Location startLoc = cp.getLocation(start);
		Location destLoc = cp.getLocation(dest);

		// check for unknowns
		if (startLoc == null && destLoc == null) {
			System.out.println("Unknown building: " + start);
			System.out.println("Unknown building: " + dest);
		} else if (startLoc == null) {
			System.out.println("Unknown building: " + start);
		} else if (destLoc == null) {
			System.out.println("Unknown building: " + dest);
		} else {

			// find path and calculate distance
			double x = startLoc.getX();
			double y = startLoc.getY();
			double totalDistance = 0.0;

			String startName = cp.getFullBuildingName(start);
			String destName = cp.getFullBuildingName(dest);

			Map<Location, Double> path = cp.findPath(startLoc, destLoc);
			String ret = "Path from " + startName + " to " + destName + ":\n";
			for (Location l : path.keySet()) {
				double destX = l.getX();
				double destY = l.getY();
				double dist = path.get(l).doubleValue();
				double angle = Math.atan2(destY - y, destX - x);
				String dir = direction(angle);
				ret += String.format("\t" + "Walk %.0f feet %s to (%.0f, %.0f)" + "\n", (dist - totalDistance), dir, destX,
						destY);
				x = destX;
				y = destY;
				totalDistance = dist;

			}
			ret += String.format("Total distance: %.0f feet", totalDistance);
			System.out.println(ret);
		}
		System.out.println();
	}

	/**
	 * Returns the direction of an angle in radians
	 * 
	 * @param angle
	 *            angle to find direction of
	 * @requires angle != null
	 * @return A String representing the direction of the angle (N, NW, W, SW,
	 *         S, SE, E, NE).
	 */
	private static String direction(double angle) {

		// constants
		final double piOverEight = Math.PI / 8.0;
		final double threePiOverEight = 3.0 * piOverEight;
		final double fivePiOverEight = 5.0 * piOverEight;
		final double sevenPiOverEight = 7.0 * piOverEight;
		double epsilon = 0.0000001;

		final String EAST = "E";
		final String NORTH_EAST = "NE";
		final String NORTH = "N";
		final String NORTH_WEST = "NW";
		final String WEST = "W";
		final String SOUTH_WEST = "SW";
		final String SOUTH = "S";
		final String SOUTH_EAST = "SE";

		// look up direction
		if (angle > fivePiOverEight && angle < sevenPiOverEight) {
			return SOUTH_WEST;
		}
		if (Math.abs(angle - threePiOverEight) < epsilon || Math.abs(angle - fivePiOverEight) < epsilon
				|| (angle > threePiOverEight && angle < fivePiOverEight)) {
			return SOUTH;
		}
		if (angle > piOverEight && angle < threePiOverEight) {
			return SOUTH_EAST;
		}
		if (angle > (-1.0 * threePiOverEight) && angle < (-1.0 * piOverEight)) {
			return NORTH_EAST;
		}
		if (angle > 0 && angle < piOverEight || (angle > (-1.0 * piOverEight) && angle < 0) || Math.abs(angle) < epsilon
				|| Math.abs(angle - piOverEight) < epsilon || Math.abs(angle - (-1.0 * piOverEight)) < epsilon) {
			return EAST;
		}
		if (Math.abs(angle - (-1.0 * threePiOverEight)) < epsilon
				|| Math.abs(angle - (-1.0 * fivePiOverEight)) < epsilon
				|| (angle > (-1.0 * fivePiOverEight) && angle < (-1.0 * threePiOverEight))) {
			return NORTH;
		}
		if (angle > (-1.0 * sevenPiOverEight) && angle < (-1.0 * fivePiOverEight)) {
			return NORTH_WEST;
		}
		return WEST;
	}
}
