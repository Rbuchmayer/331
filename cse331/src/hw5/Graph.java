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

public class Graph<N, L> {

	/*
	 * Generic Type N represents the data of a Node, and generic type L
	 * represents the type of the labels in this Graph.
	 */

	// Holds all the Nodes and Edges in the Graph
	private Map<N, HashSet<Edge<N, L>>> map;

	/*
	 * Abstraction Function: Graph, g, represents a directed, labeled,
	 * multigraph such that g = {} if the Graph is empty, {n1 = []} if the Graph
	 * contains one Node, n1, with no outgoing edges, {n1 = [e1, e2]}, n2 =
	 * [e3], n3 = [e4]} if the Graph contains nodes n1, n2, and n3, such that
	 * e1, e2, e3, and e4 are outgoing edges.
	 * 
	 * Representative Invariant: For every Graph g, g != null, g.map != null,
	 * g.map.keyset() != null, all edges in g != null, and
	 * g.map.keyset().contains(child of all edges)
	 * 
	 * In other words: g, map, all Nodes, and all Edges in the Graph are not
	 * null. Also, every Edge's child Node is contained in the Graph
	 */

	private final static boolean DEBUG = false;

	/**
	 * Constructs an empty Graph
	 */
	public Graph() {
		map = new HashMap<N, HashSet<Edge<N, L>>>();
		checkRep();
	}

	/**
	 * Returns the size of this Graph
	 * 
	 * @return an int representing the number of Nodes in this Graph
	 */
	public int size() {
		checkRep();
		return this.map.keySet().size();
	}

	/**
	 * Returns the total number of edges in this Graph
	 * 
	 * @return An int representing the total number of Edges in this Graph
	 */
	public int totalEdges() {
		checkRep();
		int total = 0;
		for (N n : this.map.keySet()) {
			total += this.map.get(n).size();
		}
		return total;
	}

	/**
	 * Returns true if this Graph is empty
	 * 
	 * @return true if this Graph is empty (contains no Nodes and Edges)
	 */
	public boolean isEmpty() {
		checkRep();
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
	public boolean contains(N data) {
		checkRep();
		return this.map.containsKey(data);
	}

	/**
	 * Returns a string representation of the Graph
	 * 
	 * @return a string representation of this Graph
	 */
	public String toString() {
		checkRep();
		return this.map.toString();
	}

	/**
	 * Clears this Graph of all Nodes and Edges
	 * 
	 * @modifies this
	 * @effects Removes every Node and Edge from this Graph
	 */
	public void clear() {
		checkRep();
		this.map.clear();
		checkRep();
	}

	/**
	 * Adds a Node with no Edges to this Graph.
	 * 
	 * @param data
	 *            the data of the Node to be added to this Graph
	 * @requires data != null
	 * @modifies this
	 * @effects Adds Node holding <var>data<var> with no Edges to this Graph.
	 * 
	 */
	public void addNode(N data) {
		this.map.put(data, new HashSet<Edge<N, L>>());
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
	public void removeNode(N data) {
		this.map.remove(data);
		for (N n : this.map.keySet()) {
			Set<Edge<N, L>> toRemove = new HashSet<Edge<N, L>>();
			for (Edge<N, L> e : this.map.get(n)) {
				if (e.getChild().equals(data)) {
					toRemove.add(e);
				}
			}
			this.map.get(n).removeAll(toRemove);
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
	public void addEdge(N parent, Edge<N, L> edge) {
		if (!this.map.containsKey(parent) || !this.map.containsKey(edge.getChild())) {
			throw new IllegalArgumentException("One argument is not in the Graph");
		}
		Set<Edge<N, L>> edges = this.map.get(parent);
		edges.add(edge);
		checkRep();
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
	public void removeEdge(N parent, N child, N label) {
		if (!this.map.containsKey(parent) || !this.map.containsKey(child)) {
			throw new IllegalArgumentException("One argument is not in the Graph");
		}
		Set<Edge<N, L>> edges = this.map.get(parent);
		Set<Edge<N, L>> toRemove = new HashSet<Edge<N, L>>();
		for (Edge<N, L> e : edges) {
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
	public boolean adjacent(N x, N y) {
		if (!this.map.containsKey(x) || !this.map.containsKey(y)) {
			throw new IllegalArgumentException("One argument is not in the Graph");
		}
		Set<Edge<N, L>> xEdges = this.map.get(x);
		for (Edge<N, L> e : xEdges) {
			if (e.getChild().equals(y)) {
				checkRep();
				return true;
			}
		}
		Set<Edge<N, L>> yEdges = this.map.get(y);
		for (Edge<N, L> e : yEdges) {
			if (e.getChild().equals(x)) {
				checkRep();
				return true;
			}
		}
		checkRep();
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
	public Set<Edge<N, L>> getOutgoingEdges(N node) {
		if (!this.map.containsKey(node)) {
			throw new IllegalArgumentException("The node is not in the Graph");
		}
		Set<Edge<N, L>> result = new HashSet<Edge<N, L>>();
		result.addAll(this.map.get(node));
		checkRep();
		return result;
	}

	/**
	 * Returns a set of all the Nodes in this Graph
	 * 
	 * @return a Set that represent the data of every Node in this Graph
	 */
	public Set<N> getNodes() {
		Set<N> result = new TreeSet<N>();
		result.addAll(this.map.keySet());
		checkRep();
		return result;
	}

	// Private method that checks if the Rep Invariant is holding
	private void checkRep() {
		assert (this != null) : "this cannot be null";
		assert (this.map != null) : "this.map cannot be null";
		if (DEBUG) {
			Set<N> keys = this.map.keySet();
			for (N k : keys) {
				assert (k != null) : "Null Node Found";
				for (Edge<N, L> e : this.map.get(k)) {
					assert (e != null) : "Null Edge Found";
					assert (this.map.containsKey(e.getChild())) : "Child of an Edge not in Graph";
				}
			}
		}
	}
}
