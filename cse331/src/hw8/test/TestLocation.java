package hw8.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hw8.*;

public class TestLocation {

	Location negative;
	Location zero;
	Location positive;
	private static final double epsilon = 0.0001;

	@Before
	public void makeLocations() {
		negative = new Location(-211.55, -4.33);
		zero = new Location(0.00, 0.00);
		positive = new Location(51.33, 1.12);
	}

	// Constructor Tests
	@Test
	public void constructLocation() {
		new Location(10.0, 5.0);
		new Location(0.0, 0.0);
		new Location(-21.6, -2112.544);
	}

	// getter Tests
	@Test
	public void testGetX() {
		assertEquals(negative.getX(), -211.55, epsilon);
		assertEquals(zero.getX(), 0.00, epsilon);
		assertEquals(positive.getX(), 51.33, epsilon);
	}

	@Test
	public void testGetY() {
		assertEquals(negative.getY(), -4.33, epsilon);
		assertEquals(zero.getY(), 0.00, epsilon);
		assertEquals(positive.getY(), 1.12, epsilon);
	}

	// Equals Tests
	@Test
	public void testEqualsWhenEqual() {
		assertTrue(negative.equals(new Location(-211.55, -4.33)));
		assertTrue(positive.equals(positive));
	}

	@Test
	public void testEqualsWhenNotEqual() {
		assertFalse(negative.equals(new Location(-211.6, -4.3)));
		assertFalse(positive.equals(zero));
	}

	// Hash code Tests
	@Test
	public void testHashCode() {
		assertEquals(negative.hashCode(), negative.hashCode());
		Location l = new Location(51.33, 1.12);
		assertEquals(positive.hashCode(), l.hashCode());
		assertNotEquals(zero.hashCode(), negative.hashCode());
	}

	// CompareTo
	@Test
	public void testCompareTo() {
		assertTrue(positive.compareTo(zero) > 0);
		assertTrue(negative.compareTo(positive) < 0);
		assertTrue(positive.compareTo(positive) == 0);
	}
}
