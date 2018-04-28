package hw5;

/**
 * <b>Node</b> represents an immutable Node. Nodes store data and are used to
 * build Graphs.
 * 
 * <p>
 * Some examples of Nodes are: "Node Data: n1" and "Node Data: node"
 */

public final class Node implements Comparable<Node> {

	/** Holds the data of this Node */
	private final String data;

	/*
	 * Abstraction Function: Node, n, represents a Node such that this.data
	 * represents the data held by the Node
	 * 
	 * Representative Invariant: For every Node n, n != null and n.data != null
	 */

	/**
	 * Constructs a new Node
	 * 
	 * @param data
	 *            The data to be held by the Node
	 * @requires data != null
	 */
	public Node(String data) {
		this.data = data;
		checkRep();
	}

	/**
	 * Gets the data of the Node
	 * 
	 * @return A String representing the data held by the Node
	 */
	public String getData() {
		return this.data;
	}

	/**
	 * Returns a String Representation of the Node
	 * 
	 * @return a String representing the Node
	 */
	public String toString() {
		return ("Node Data: " + this.getData());
	}

	/**
	 * Compares two Nodes by their data alphabetically
	 * 
	 * @param n
	 *            Node to be compared to this Node
	 * @requires n != null
	 * @return an int that is: greater than 0 if this Node's data is
	 *         alphabetically before n's, less than 0 if this Node's data is
	 *         alphabetically after n's, equal to 0 if both Nodes have the same
	 *         data
	 */
	@Override
	public int compareTo(Node n) {
		return this.getData().compareTo(n.getData());
	}

	/**
	 * Tests if two Nodes are equal. Two Nodes are equal if they have the same
	 * data value
	 * 
	 * @param o
	 *            Object to be tested equal to this Node
	 * @requires o != null
	 * @return true if o is a Node who's data is equal to this Node's data
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Node) {
			Node n = (Node) o;
			return this.getData().equals(n.getData());
		}
		return false;
	}

	/**
	 * Returns the hashCode of this Node
	 * 
	 * @return an int representing the hashCode of this Node
	 */
	@Override
	public int hashCode() {
		return this.getData().length();
	}

	// Private method to check if the Rep Invariant is held
	private void checkRep() {
		assert (this != null) : "this cannot be null";
		assert (this.data != null) : "this.data cannot be null";
	}
}
