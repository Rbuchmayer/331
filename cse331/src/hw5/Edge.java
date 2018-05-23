package hw5;

/**
 * <b>Edge</b> represents a labeled, directed Edge. Edges are used to connect
 * Nodes in a Graph. Note that if mutable objects are used in an Edge, the
 * Edge's behavior is unspecified if the object is changed.
 * <p>
 * 
 * One example of an Edge might be: "e1 pointing to n1"
 * 
 */

public final class Edge<N, L>  {

	/*
	 * Generic type N represents the type of the child Node of this Edge, and
	 * generic type L represents the type of the label of this Edge
	 */

	// Holds the child Node of this Edge
	private final N child;

	// Holds the label of this Edge
	private final L label;

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
	public Edge(N child, L label) {
		this.child = child;
		this.label = label;
		checkRep();
	}

	/**
	 * Gets the data of this Edge's child Node.
	 * 
	 * @return the child of this Edge
	 */
	public N getChild() {
		checkRep();
		return this.child;
	}

	/**
	 * Gets the label of this Edge.
	 * 
	 * @return A data of the label of this Edge
	 */
	public L getLabel() {
		checkRep();
		return this.label;
	}

	/**
	 * Returns a String representation of this Edge
	 * 
	 * @return A String representation of this Edge including its label and
	 *         child Node
	 */
	public String toString() {
		checkRep();
		return (this.getLabel() + " pointing to " + this.getChild());
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
		checkRep();
		if (o instanceof Edge<?, ?>) {
			Edge<?, ?> e = (Edge<?, ?>) o;
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
		checkRep();
		return label.hashCode() + child.hashCode();
	}


	// Private method to check if the Rep Invariant is held
	private void checkRep() {
		assert (this != null) : "this Edge cannot be null";
		assert (this.label != null) : "this Edge's label cannot be null";
		assert (this.child != null) : "this Edge's child cannot be null";
	}


}
