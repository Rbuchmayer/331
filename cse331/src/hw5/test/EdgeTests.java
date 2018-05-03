package hw5.test;

import hw5.Edge;
import hw5.Node;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

public final class EdgeTests {

	// Some basic Edges
	private Edge e1 = new Edge("n1", "e1");
	private Edge e2 = new Edge("n2", "e2");
	private Edge e3 = new Edge("n3", "e3");
	private Edge e4 = new Edge("n4", "e4");
	private Edge e5 = new Edge("n1", "e5");
	private Edge e6 = new Edge("n2", "e2");

	@Before
	public void testAssertsEnabled() {
		CheckAsserts.checkAssertsEnabled();
	}

	// Constructor Tests
	@Test
	public void testConstructor() {
		new Edge("n1", "e1");
		new Edge("n2", "e1");
		new Edge("n1", "e2");
		new Edge("n4", "e1");
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
		assertEquals(e1.toString(), "Edge Label: e1, Child Node: n1");
		assertEquals(e2.toString(), "Edge Label: e2, Child Node: n2");
		assertNotEquals(e3.toString(), "Edge Label: e2, Child Node: n2");
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
		assertSame(e4.hashCode(), e4.hashCode());
		assertSame(e5.hashCode(), e4.hashCode());
	}
}
