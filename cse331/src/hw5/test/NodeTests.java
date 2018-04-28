package hw5.test;

import hw5.Node;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

public final class NodeTests {

	// Some basic Nodes
	private Node n1 = new Node("n1");
	private Node n2 = new Node("n2");
	private Node n3 = new Node("n3");
	private Node n4 = new Node("n3");

	@Before
	public void testAssertsEnabled() {
		CheckAsserts.checkAssertsEnabled();
	}

	// Constructor Tests
	@Test
	public void testConstructor() {
		new Node("");
		new Node("node");
		new Node("n0");
	}

	// getData Tests
	@Test
	public void testGetData() {
		assertEquals(n1.getData(), "n1");
		assertEquals(n2.getData(), "n2");
		assertEquals(n3.getData(), "n3");
		assertNotEquals(n1.getData(), n3.getData());
	}

	// toString Tests
	@Test
	public void testToString() {
		assertEquals(n1.toString(), "Node Data: n1");
		assertEquals(n2.toString(), "Node Data: n2");
		assertNotEquals(n3.toString(), "Node Data: n1");
	}

	// compareTo Tests
	@Test
	public void testCompareToWhenEqual() {
		assertSame(n1.compareTo(n1), 0);
		assertSame(n3.compareTo(n4), 0);
	}

	@Test
	public void testCompareToWhenNotEqual() {
		assertTrue(n1.compareTo(n2) < 0);
		assertTrue(n3.compareTo(n1) > 0);
	}

	// Equals Tests
	@Test
	public void testEqualsWhenEqual() {
		assertEquals(n3, n4);
		assertEquals(n2, n2);
	}

	@Test
	public void testEqualsWhenNotEqual() {
		assertNotEquals(n1, n4);
		assertNotEquals(n2, n3);
	}

	// hashCode Tests
	@Test
	public void testHashCodeWhenEqual() {
		assertSame(n3.hashCode(), n4.hashCode());
		assertSame(n2.hashCode(), n2.hashCode());
	}
}
