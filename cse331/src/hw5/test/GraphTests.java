package hw5.test;

import hw5.Graph;
import hw5.Node;
import hw5.Edge;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public final class GraphTests {

	// Some basic Nodes
	private String n1 = "n1";
	private String n2 = "n2";
	private String n3 = "n3";
	private String n4 = "n4";
	private String n5 = "n5";
	private String n6 = "n6";
	private String n7 = "n7";
	private String n8 = "n8";
	private String n9 = "n9";
	private String n10 = "n10";
	private String n11 = "n11";
	private String n12 = "n12";
	private String n13 = "n13";
	private String n14 = "n14";

	// Some basic Graphs
	Graph EMPTY;
	Graph g1;
	Graph g2;
	Graph g3;
	Graph g4;
	Graph g5;
	Graph g6;
	Graph g7;

	@Before
	public void makeGraphs() {
		// Empty Graph
		EMPTY = new Graph();

		// One Node Graph
		g1 = new Graph();
		g1.addNode(n1);

		// One Node, one Edge Graph
		g2 = new Graph();
		g2.addNode(n2);
		g2.addEdge(n2, new Edge(n2, "e1"));

		// Two Nodes, two Edge Graph
		g3 = new Graph();
		g3.addNode(n3);
		g3.addNode(n4);
		g3.addEdge(n3, new Edge(n4, "e2"));

		// Two Nodes, no Edge Graph
		g4 = new Graph();
		g4.addNode(n5);
		g4.addNode(n6);

		// Two Nodes, two opposite Edges Graph
		g5 = new Graph();
		g5.addNode(n7);
		g5.addNode(n8);
		g5.addEdge(n7, new Edge(n8, "e3"));
		g5.addEdge(n8, new Edge(n7, "e4"));

		// Two Nodes, two same direction Edges Graph
		g6 = new Graph();
		g6.addNode(n9);
		g6.addNode(n10);
		g6.addEdge(n9, new Edge(n10, "e5"));
		g6.addEdge(n9, new Edge(n10, "e6"));

		// Complex Graph with 3 Nodes and five Edges
		g7 = new Graph();
		g7.addNode(n11);
		g7.addNode(n12);
		g7.addNode(n13);
		g7.addEdge(n11, new Edge(n11, "e7"));
		g7.addEdge(n11, new Edge(n12, "e8"));
		g7.addEdge(n12, new Edge(n13, "e9"));
		g7.addEdge(n13, new Edge(n11, "e10"));
		g7.addEdge(n13, new Edge(n12, "e11"));
	}

	@Before
	public void testAssertsEnabled() {
		CheckAsserts.checkAssertsEnabled();
	}

	// Constructor Tests
	@Test
	public void testConstructor() {
		new Graph();
	}

	// Size Tests
	@Test
	public void testSizeEmpty() {
		assertSame(EMPTY.size(), 0);
	}

	@Test
	public void testSizeNonEmpty() {
		assertSame(g1.size(), 1);
		assertSame(g2.size(), 1);
		assertSame(g3.size(), 2);
		assertSame(g4.size(), 2);
		assertSame(g5.size(), 2);
		assertSame(g6.size(), 2);
		assertSame(g7.size(), 3);
	}

	// totalEdges Tests
	@Test
	public void testTotalEdgesWhenNoEdges() {
		assertSame(g1.totalEdges(), 0);
		assertSame(g4.totalEdges(), 0);
		assertSame(EMPTY.totalEdges(), 0);
	}

	@Test
	public void testTotalEdgesWithEdges() {
		assertSame(g7.totalEdges(), 5);
		assertSame(g2.totalEdges(), 1);
		assertSame(g6.totalEdges(), 2);
		assertSame(g5.totalEdges(), 2);
	}

	// isEmpty Tests
	@Test
	public void testIsEmptyWhenEmpty() {
		assertTrue(EMPTY.isEmpty());
	}

	@Test
	public void testIsEmptyWhenNotEmpty() {
		assertFalse(g1.isEmpty());
		assertFalse(g2.isEmpty());
		assertFalse(g3.isEmpty());
		assertFalse(g4.isEmpty());
		assertFalse(g5.isEmpty());
		assertFalse(g6.isEmpty());
		assertFalse(g7.isEmpty());
	}

	// Contains Tests
	@Test
	public void testContainsWhenTrue() {
		assertTrue(g1.contains(n1));
		assertTrue(g2.contains(n2));
		assertTrue(g3.contains(n3));
		assertTrue(g3.contains(n4));
		assertTrue(g4.contains(n5));
		assertTrue(g4.contains(n6));
		assertTrue(g5.contains(n7));
		assertTrue(g5.contains(n8));
		assertTrue(g6.contains(n9));
		assertTrue(g6.contains(n10));
		assertTrue(g7.contains(n11));
		assertTrue(g7.contains(n12));
		assertTrue(g7.contains(n13));
	}

	@Test
	public void testContainsWhenFalse() {
		assertFalse(EMPTY.contains(n1));
		assertFalse(g6.contains(n3));
		assertFalse(g3.contains(n2));
		assertFalse(g7.contains(n8));
		assertFalse(g1.contains(n2));
	}

	// toString Tests
	@Test
	public void testToStringEmpty() {
		assertTrue(EMPTY.toString().equals("{}"));
	}

	@Test
	public void testToStringOneNode() {
		assertEquals(g1.toString(), "{n1=[]}");
		assertEquals(g2.toString(), "{n2=[e1 pointing to n2]}");
	}

	@Test
	public void testToStringMultiNode() {
		assertEquals(g6.toString(),
				"{n9=[e5 pointing to n10, " + "e6 pointing to n10], n10=[]}");

		System.out.println(g7.toString());
		assertEquals(g7.toString(),
				"{n13=[e10 pointing to n11, e11 pointing to n12], "
						+ "n12=[e9 pointing to n13], n11=[e7 pointing to n11, e8 pointing to n12]}");
	}

	// Clear Tests
	@Test
	public void testClear() {
		EMPTY.clear();
		g7.clear();
		assertTrue(EMPTY.isEmpty());
		assertTrue(g7.isEmpty());
	}

	// addNode Tests
	@Test
	public void testAddNodeToEmpty() {
		EMPTY.addNode(n14);
		assertFalse(EMPTY.isEmpty());
		assertTrue(EMPTY.contains(n14));
	}

	@Test
	public void testAddNodeToNonEmpty() {
		g1.addNode(n11);
		assertTrue(g1.contains(n11));
		g5.addNode(n10);
		assertTrue(g5.contains(n10));
		g7.addNode(n9);
		assertTrue(g7.contains(n9));
	}

	@Test
	public void testAddDuplicateNode() {
		g7.addNode(n13);
		assertSame(g7.totalEdges(), 3);
		assertTrue(g7.contains(n13));
		assertTrue(g7.getOutgoingEdges(n13).isEmpty());
	}

	// removeNode Tests
	@Test
	public void testRemoveNodeFromEmpty() {
		EMPTY.removeNode(n3);
		assertTrue(EMPTY.isEmpty());
	}

	@Test
	public void testRemoveNodeFromOneNodeGraph() {
		g1.removeNode(n1);
		assertFalse(g1.contains(n1));
		assertTrue(g1.isEmpty());
	}

	@Test
	public void testRemoveNodeFromMultiNodeGraph() {
		g7.removeNode(n13);
		assertSame(g7.totalEdges(), 2);
		assertFalse(g7.contains(n13));
		assertSame(g7.size(), 2);
		g7.removeNode(n12);
		assertSame(g7.totalEdges(), 1);
		assertSame(g7.size(), 1);
		assertSame(g7.getOutgoingEdges(n11).size(), 1);
		g3.removeNode(n4);
		assertFalse(g7.contains(n4));
		assertSame(g3.size(), 1);
		g6.removeNode(n10);
		assertTrue(g6.getOutgoingEdges(n9).isEmpty());
		assertFalse(g6.contains(n10));
	}

	// addEdge Tests
	@Test
	public void testAddEdgeToSingleNodeGraph() {
		assertSame(g1.totalEdges(), 0);
		g1.addEdge(n1, new Edge(n1, "e1"));
		assertSame(g1.totalEdges(), 1);
		g2.addEdge(n2, new Edge(n2, "e2"));
		assertSame(g2.totalEdges(), 2);
	}

	@Test
	public void testAddEdgeToMultiNodeGraph() {
		g4.addEdge(n5, new Edge(n6, "e3"));
		assertSame(g4.totalEdges(), 1);
		g4.addEdge(n6, new Edge(n5, "e4"));
		assertSame(g4.totalEdges(), 2);
		g7.addEdge(n12, new Edge(n11, "e5"));
		assertSame(g7.totalEdges(), 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddEdgeException() {
		g1.addEdge(n1, new Edge(n2, "e3"));
		g7.addEdge(n12, new Edge(n8, "e9"));
	}

	// removeEdge Tests
	@Test
	public void testRemoveEdgeFromSingleNodeGraph() {
		g2.removeEdge(n2, n2, "e1");
		assertSame(g2.totalEdges(), 0);
		g1.removeEdge(n1, n1, "e1");
		assertSame(g1.totalEdges(), 0);
	}

	@Test
	public void testRemoveEdgeFromMultiNodeGraph() {
		g5.removeEdge(n8, n7, "e4");
		assertSame(g5.totalEdges(), 1);
		assertTrue(g5.getOutgoingEdges(n8).isEmpty());
		g6.removeEdge(n9, n10, "e5");
		assertTrue(g6.adjacent(n9, n10));
		assertSame(g6.totalEdges(), 1);
		g6.addEdge(n9, new Edge(n10, "edge"));
		g6.addEdge(n9, new Edge(n10, "edge"));
		assertSame(g6.totalEdges(), 2);
		g6.removeEdge(n9, n10, "edge");
		assertSame(g6.totalEdges(), 1);
		assertTrue(g7.adjacent(n11, n12));
		g7.removeEdge(n11, n12, "e8");
		assertFalse(g7.adjacent(n11, n12));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveEdgeException() {
		g1.removeEdge(n1, n2, "e3");
		g7.removeEdge(n3, n3, "e2");
	}

	// Adjacent Tests
	@Test
	public void testAdjacentFalse() {
		assertFalse(g4.adjacent(n5, n6));
		assertFalse(g1.adjacent(n1, n1));
	}

	@Test
	public void testAdjacentTrue() {
		assertTrue(g2.adjacent(n2, n2));
		assertTrue(g3.adjacent(n3, n4));
		assertTrue(g5.adjacent(n7, n8));
		assertTrue(g6.adjacent(n10, n9));
		assertTrue(g2.adjacent(n2, n2));
		assertTrue(g7.adjacent(n12, n13));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdjacentException() {
		EMPTY.adjacent(n3, n4);
		g3.adjacent(n2, n2);
		g6.adjacent(n9, n11);
	}

	// getChildren Tests
	@Test
	public void testGetChildrenWhenNoChildren() {
		assertTrue(g1.getOutgoingEdges(n1).isEmpty());
		assertTrue(g3.getOutgoingEdges(n4).isEmpty());
		assertTrue(g4.getOutgoingEdges(n5).isEmpty());
		assertTrue(g6.getOutgoingEdges(n10).isEmpty());
	}

	@Test
	public void testGetChildrenWhenChildrenPresent() {
		assertSame(g2.getOutgoingEdges(n2).size(), 1);
		assertSame(g6.getOutgoingEdges(n9).size(), 2);
		assertSame(g7.getOutgoingEdges(n11).size(), 2);
		assertSame(g7.getOutgoingEdges(n12).size(), 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetChildrenException() {
		assertSame(EMPTY.getOutgoingEdges(n1), 0);
		assertSame(g4.getOutgoingEdges(n7), 0);
	}

	// getNodes Tests
	@Test
	public void testGetNodesNoNodes() {
		assertTrue(EMPTY.getNodes().isEmpty());
	}

	@Test
	public void testGetNodesOneNode() {
		assertSame(g1.getNodes().size(), 1);
		assertSame(g2.getNodes().size(), 1);
	}

	@Test
	public void testGetNodesMultiNodes() {
		assertSame(g7.getNodes().size(), 3);
		assertSame(g4.getNodes().size(), 2);
		assertSame(g3.getNodes().size(), 2);
	}
}