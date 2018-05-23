package hw8;

/**
 * A Location represents the x and y coordinates of an immutable location. An
 * example of a location may be (24.213, 43.553), where 24.213 is the x
 * coordinate and 43.553 is the y coordinate.
 */

public final class Location implements Comparable<Location> {

	// holds the x coordinate
	private final Double x;

	// holds the y coordinate
	private final Double y;

	/*
	 * Representative Invariant: x != null and y != null
	 * 
	 * Abstract Function: A Location represents a point (pair of coordinate
	 * points) p such that: p.x is the x coordinate and p.y is the y coordinate
	 * of the location.
	 */

	/**
	 * Constructs a new Location Object
	 * 
	 * @param x
	 *            the x coordinate of the Location
	 * @param y
	 *            the y coordinate of the Location
	 * @requires x != null and y != null
	 */
	public Location(double x, double y) {
		this.x = x;
		this.y = y;
		checkRep();
	}

	/**
	 * Gets the x coordinate of this Location
	 * 
	 * @return the x value of this Location
	 */
	public double getX() {
		checkRep();
		return this.x;
	}

	/**
	 * Gets the y coordinate of this Location
	 * 
	 * @return the y value of this Location
	 */
	public double getY() {
		checkRep();
		return this.y;
	}

	/**
	 * Compares two Locations for equality
	 * 
	 * @param o
	 *            Object to test equal to this @ requires o != null
	 * @return true if the two Locations are equal (same coordinates), false
	 *         otherwise
	 */
	@Override
	public boolean equals(Object o) {
		checkRep();
		if (!(o instanceof Location)) {
			return false;
		}
		Location l = (Location) o;
		return l.x.equals(this.x) && l.y.equals(this.y);
	}

	/**
	 * Returns the hash code of this Location
	 * 
	 * @return the hash code of this Location
	 */
	@Override
	public int hashCode() {
		checkRep();
		return (int) (this.getX() + this.getY());
	}

	/**
	 * Compares two Locations by x coordinate first, then y coordinate.
	 * 
	 * @param other
	 *            other location to compare to
	 * @requires other != null
	 * @return an int: greater than 0 if this is greater than other, less than
	 *         zero if this is less than other, or zero if both Locations are
	 *         equal.
	 */
	@Override
	public int compareTo(Location other) {
		if (this.x > other.x) {
			return 1;
		}
		if (other.x > this.x) {
			return -1;
		}
		if (this.y > other.y) {
			return 1;
		}
		if (other.y > this.y) {
			return -1;
		}
		return 0;
	}

	// makes sure the rep invariant holds
	private void checkRep() {
		assert (this != null) : "This Location cannot be null!";
		assert (this.x != null) : "The x coordinate cannot be null!";
		assert (this.y != null) : "The y coordinate cannot be null!";
	}
}
