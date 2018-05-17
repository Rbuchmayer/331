package hw5.test;

import hw5.Edge;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

public final class EdgeTests {

	// Some basic Edges
	private Edge<String, String> e1 = new Edge<String, String>("n1", "e1");
	private Edge<String, String> e2 = new Edge<String, String>("n2", "e2");
	private Edge<String, String> e3 = new Edge<String, String>("n3", "e3");
	private Edge<String, String> e4 = new Edge<String, String>("n4", "e4");
	private Edge<String, String> e5 = new Edge<String, String>("n1", "e5");
	private Edge<String, String> e6 = new Edge<String, String>("n2", "e2");

	@Before
	public void testAssertsEnabled() {
		CheckAsserts.checkAssertsEnabled();
	}

	// Constructor Tests
	@Test
	public void testConstructor() {
		new Edge<String, String>("n1", "e1");
		new Edge<String, String>("n2", "e1");
		new Edge<String, String>("n1", "e2");
		new Edge<String, String>("n4", "e1");
	}

	// getChild Tests
	@Test
	public void testGetChild() {
		assertEquals(e1.getChild(), "n1");
		assertEquals(e2.getChild(), "n2");
		assertNotEquals(e3.getChild(), "n4");
		assertEquals(e5.getChild(), e1.getChild());
	}

	// setLabel Tests
	@Test
	public void testGetLabel() {
		assertEquals(e1.getLabel(), "e1");
		assertEquals(e2.getLabel(), "e2");
		assertNotEquals(e1.getLabel(), e3.getLabel());
	}

	// toString Tests
	@Test
	public void testToString() {
		assertEquals(e1.toString(), "e1 pointing to n1");
		assertEquals(e2.toString(), "e2 pointing to n2");
		assertNotEquals(e3.toString(), "e2 pointing to n2");
	}

	// compareTo Tests
	@Test
	public void testCompareToEqual() {
		assertSame(e6.compareTo(e2), 0);
		assertSame(e4.compareTo(e4), 0);
	}

	@Test
	public void testCompareToUnequal() {
		assertTrue(e6.compareTo(e1) > 0);
		assertTrue(e5.compareTo(e2) < 0);
	}

	// Equals Tests
	@Test
	public void testEqualsWhenNotEqual() {
		assertNotEquals(e1, e4);
		assertNotEquals(e3, e6);
	}

	@Test
	public void testEqualsWhenEqual() {
		assertEquals(e4, e4);
		assertEquals(e6, e2);
	}

	// hashCode Tests
	@Test
	public void testHashCodeOnEqualEdges() {
		assertEquals(e4.hashCode(), e4.hashCode());
		assertEquals(e6.hashCode(), e2.hashCode());
	}
}
