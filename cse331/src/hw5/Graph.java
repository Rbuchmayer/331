package hw5;

import java.util.*;

/**
 * <b>Graph</b> represents a mutable, directed, labeled, multigraph. Graphs are
 * made of Nodes and Edges.
 * 
 * <p>
 * Some examples of Graphs are: "{n1=[]}", "{n2=[Edge Label: e1, Child Node:
 * n2]}", and "{n3=[Edge Label: e2, Child Node: n3]}"
 */

public class Graph {

	/** Holds all the Nodes and Edges in the Graph */
	private Map<String, Set<Edge>> map;

	/*
	 * Abstraction Function: Graph, g, represents a directed, labeled,
	 * multigraph such that g = {} if the Graph is empty, {n1 = []} if the Graph
	 * contains one Node, n1, with no outgoing edges, {n1 = [e1, e2]}, n2 =
	 * [e3], n3 = [e4]} if the Graph contains nodes n1, n2, and n3, such that
	 * e1, e2, e3, and e4 are outgoing edges.
	 * 
	 * Representative Invariant: For every Graph g, g != null, g.map != null,
	 * g.nodes != null, g.edges != null, and g.nodes.contains(g.edges.children)
	 * 
	 * In other words: g, map, all Nodes, and all Edges in the Graph are not
	 * null. Also, every Edge's child Node is contained in the Graph
	 */

	private final static boolean DEBUG = true;

	/**
	 * Constructs an empty Graph
	 */
	public Graph() {
		map = new HashMap<String, Set<Edge>>();
		checkRep();
	}

	/**
	 * Returns the size of this Graph
	 * 
	 * @return an int representing the number of Nodes in this Graph
	 */
	public int size() {
		return this.map.keySet().size();
	}

	/**
	 * Returns the total number of edges in this Graph
	 * 
	 * @return An int representing the total number of Edges in this Graph
	 */
	public int totalEdges() {
		int total = 0;
		for (String s : this.map.keySet()) {
			total += this.map.get(s).size();
		}
		return total;
	}

	/**
	 * Returns true if this Graph is empty
	 * 
	 * @return true if this Graph is empty (contains no Nodes and Edges)
	 */
	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	/**
	 * Returns true if a Node that holds <var>data<var> is contained in this
	 * Graph
	 * 
	 * @param data
	 *            data to be looked for in this Graph
	 * @requires data != null
	 * @return true if a Node containing <var>data<var> is in the Graph, false
	 *         otherwise
	 */
	public boolean contains(String data) {
		return this.map.containsKey(data);
	}

	/**
	 * Returns a string representation of the Graph
	 * 
	 * @return a string representation of this Graph
	 */
	public String toString() {
		return this.map.toString();
	}

	/**
	 * Clears this Graph of all Nodes and Edges
	 * 
	 * @modifies this
	 * @effects Removes every Node and Edge from this Graph
	 */
	public void clear() {
		this.map.clear();
		checkRep();
	}

	/**
	 * Adds a Node with no Edges to this Graph. If the node is already in the
	 * Graph, all its outgoing edges will be removed.
	 * 
	 * @param data
	 *            the data of the Node to be added to this Graph
	 * @requires data != null
	 * @modifies this
	 * @effects Adds Node holding <var>data<var> with no Edges to this Graph.
	 *          Removes all its outgoing Edges if the Node is already in the
	 *          Graph.
	 */
	public void addNode(String data) {
		this.map.put(data, new HashSet<Edge>());
		checkRep();
	}

	/**
	 * Removes a the Node holding <var>data<var> from this Graph. All Edges
	 * connected to the Node will be removed. If the Node is not in the Graph,
	 * there will be no effect.
	 * 
	 * @param data
	 *            Data of the Node to be removed from this Graph
	 * @requires data != null
	 * @modifies this
	 * @effects Removes the Node holding <var>data<var> from this Graph. Removes
	 *          all Edges that the Node was the parent or child of. No effect if
	 *          the Node is not in the Graph to begin with.
	 */
	public void removeNode(String data) {
		this.map.remove(data);
		for (String s : this.map.keySet()) {
			Set<Edge> toRemove = new HashSet<Edge>();
			for (Edge e : this.map.get(s)) {
				if (e.getChild().equals(data)) {
					toRemove.add(e);
				}
			}
			this.map.get(s).removeAll(toRemove);
		}
		checkRep();
	}

	/**
	 * Adds an Edge to this Graph. No effect if an equal Edge is already in the
	 * Graph.
	 * 
	 * @param parent
	 *            Parent of the Edge to be added
	 * @param edge
	 *            Edge to be added
	 * @requires parent != null, edge != null
	 * @modifies this
	 * @effects Adds edge to this Graph if it is not already contained in this
	 *          Graph
	 * @throws IllegalArgumentException
	 *             if parent or edge.getChild() is not contained in the Graph
	 */
	public void addEdge(String parent, Edge edge) {
		if (!this.map.containsKey(parent) || !this.map.containsKey(edge.getChild())) {
			throw new IllegalArgumentException("One argument is not in the Graph");
		}
		Set<Edge> edges = this.map.get(parent);
		edges.add(edge);

	}

	/**
	 * Removes an Edge from this Graph. No effect if the Edge is not in the
	 * Graph.
	 * 
	 * @param parent
	 *            Parent of the Edge to be removed
	 * @param child
	 *            Child of the Edge to be removed
	 * @param label
	 *            Label of the Edge to be removed
	 * @requires parent != null, child != null, label != null
	 * @modifies this
	 * @effects Removes edge from this Graph
	 * @throws IllegalArgumentException
	 *             if parent or child is not contained in this Graph
	 */
	public void removeEdge(String parent, String child, String label) {
		if (!this.map.containsKey(parent) || !this.map.containsKey(child)) {
			throw new IllegalArgumentException("One argument is not in the Graph");
		}
		Set<Edge> edges = this.map.get(parent);
		Set<Edge> toRemove = new HashSet<Edge>();
		for (Edge e : edges) {
			if (e.getLabel().equals(label)) {
				toRemove.add(e);
			}
		}
		edges.removeAll(toRemove);
		checkRep();
	}

	/**
	 * Returns true if two Nodes are adjacent. Two nodes are adjacent if they
	 * are connected by an edge in either direction.
	 * 
	 * @param x
	 *            First Node's data
	 * @param y
	 *            Second Node's data
	 * @requires x != null and y != null
	 * @return true if Node holding x and Node holding y are connected by an
	 *         edge, false otherwise
	 * @throws IllegalArgumentException
	 *             if Node holding x or Node holding y is not contained in this
	 *             Graph
	 */
	public boolean adjacent(String x, String y) {
		if (!this.map.containsKey(x) || !this.map.containsKey(y)) {
			throw new IllegalArgumentException("One argument is not in the Graph");
		}
		Set<Edge> xEdges = this.map.get(x);
		for (Edge e : xEdges) {
			if (e.getChild().equals(y)) {
				return true;
			}
		}
		Set<Edge> yEdges = this.map.get(y);
		for (Edge e : yEdges) {
			if (e.getChild().equals(x)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a set of all the outgoing Edges of a Node.
	 * 
	 * @param node
	 *            Data of the Node to get outgoing Edges from
	 * @requires data != null
	 * @return a Set of type Edge that contains all the outgoing Edges of node
	 * @throws IllegalArgumentException
	 *             if node is not contained in this Graph
	 */
	public Set<Edge> getOutgoingEdges(String node) {
		if (!this.map.containsKey(node)) {
			throw new IllegalArgumentException("The node is not in the Graph");
		}
		Set<Edge> result = new TreeSet<Edge>();
		result.addAll(this.map.get(node));
		return result;
	}

	/**
	 * Returns a set of all the Nodes in this Graph
	 * 
	 * @return a Set of Strings that represent the data of every Node in this
	 *         Graph
	 */
	public Set<String> getNodes() {
		Set<String> result = new TreeSet<String>();
		result.addAll(this.map.keySet());
		return result;
	}

	// Private method that checks if the Rep Invariant is holding
	private void checkRep() {
		assert (this != null) : "this cannot be null";
		assert (this.map != null) : "this.map cannot be null";
		if (DEBUG) {
			Set<String> keys = this.map.keySet();
			for (String k : keys) {
				assert (k != null) : "Null Node Found";
				for (Edge e : this.map.get(k)) {
					assert (e != null) : "Null Edge Found";
					assert (this.map.containsKey(e.getChild())) : "Child of an Edge not in Graph";
				}
			}
		}
	}
}
