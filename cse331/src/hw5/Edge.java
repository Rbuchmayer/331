package hw5;

/**
 * <b>Edge</b> represents an immutable, labeled, directed Edge. Edges are used
 * to connect Nodes in a Graph.
 * <p>
 * 
 * One example of an Edge might be: "Edge Label: e1, Child Node: n1"
 */

public final class Edge implements Comparable<Edge> {

	/** Holds the child Node of this Edge */
	private final Node child;

	/** Holds the label of this Edge */
	private final String label;

	/*
	 * Abstract Function: Edge, e, represents an Edge such that e.child is the
	 * child Node the Edge is pointing to, and e.label is the label associated
	 * with the Edge.
	 * 
	 * Representative Invariant: For every Edge e: e != null and e.label != null
	 * and e.child != null
	 */

	/**
	 * Constructs a new Edge
	 * 
	 * @param child
	 *            The child Node of this Edge
	 * @param label
	 *            The label of this Edge
	 * @requires child != null and label != null
	 */
	public Edge(Node child, String label) {
		this.child = child;
		this.label = label;
		checkRep();
	}

	/**
	 * Gets the child Node of this Edge
	 * 
	 * @return the child Node of the this Edge
	 */
	public Node getChild() {
		return this.child;
	}

	/**
	 * Gets the label of this Edge.
	 * 
	 * @return A String representing the label of this Edge
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Returns a String representation of this Edge
	 * 
	 * @return A String representation of this Edge including its label and
	 *         child Node
	 */
	public String toString() {
		return ("Edge Label: " + this.getLabel() + ", Child Node: " + this.getChild().getData());
	}

	/**
	 * Compares two Edges first by child Node, then alphabetically by label if
	 * the child Nodes are equal
	 * 
	 * @param e
	 *            Edge to be compared to this
	 * @requires e != null
	 * @return an int that is: greater than 0 if this Edge is "greater" than e,
	 *         less than 0 if this Edge is "less" than e, equal to 0 if both
	 *         Edge's are "equal"
	 */
	@Override
	public int compareTo(Edge e) {
		if (this.getChild().equals(e.getChild())) {
			return this.getLabel().compareTo(e.getLabel());
		}
		return this.getChild().compareTo(e.getChild());
	}

	/**
	 * Tests if o and this Edge are equal. Two Edges are equal if they have the
	 * same child Node and have the same label.
	 * 
	 * @param o
	 *            Object to be tested equal to this
	 * @requires o != null
	 * @return true if o is an Edge that refers to the same child node and has
	 *         the same label as this Edge
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Edge) {
			Edge e = (Edge) o;
			return this.getChild().equals(e.getChild()) && this.getLabel().equals(e.getLabel());
		}
		return false;
	}

	/**
	 * Returns the hashCode of this Edge
	 * 
	 * @return an int representing the hashCode of this Edge
	 */
	@Override
	public int hashCode() {
		return this.getLabel().length();
	}

	// Private method to check if the Rep Invariant is held
	private void checkRep() {
		assert (this != null) : "this Edge cannot be null";
		assert (this.label != null) : "this Edge's label cannot be null";
		assert (this.child != null) : "this Edge's child cannot be null";
	}
}
