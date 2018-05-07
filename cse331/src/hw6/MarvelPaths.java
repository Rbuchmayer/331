package hw6;

import hw5.*;
import hw6.MarvelParser.MalformedDataException;

import java.util.*;

public final class MarvelPaths {

	/*
	 * MarvelPaths does not have an AF, RI, or checkRep() because all its
	 * methods are static, and we will not create MarvelPath objects
	 */

	/**
	 * Returns a Graph built from a given file
	 * 
	 * @param file
	 *            correct path to the file to build graph from.
	 * @requires file is in the correct format (.tsv) and is a correct path to
	 *           the file.
	 * @throws MalformedDataException
	 *             if the file is not well-formed: each line contains exactly
	 *             two tokens separated by a tab, or else starting with a #
	 *             symbol to indicate a comment line.
	 * @return A graph built from file, null if file is null.
	 */
	public static Graph makeGraph(String file) throws MalformedDataException {
		if (file == null) {
			return null;
		}
		Graph g = new Graph();
		Set<String> characters = new HashSet<String>();
		Map<String, List<String>> books = new HashMap<String, List<String>>();
		MarvelParser.parseData(file, characters, books);
		//add all characters to graph
		for (String s : characters) {
			g.addNode(s);
		}
		//add all edges to graph
		for (String book : books.keySet()) {
			int i = 1;
			List<String> charsInBook = books.get(book);
			for (String character1 : charsInBook) {
				// sublist to avoid re-adding Edges
				List<String> sublist = charsInBook.subList(i, charsInBook.size());
				for (String character2 : sublist) {
					if (!character1.equals(character2)) {
						g.addEdge(character1, new Edge(character2, book));
						g.addEdge(character2, new Edge(character1, book));
					}
				}
				i++;
			}
		}
		return g;
	}

	/**
	 * Returns the shortest path between two Nodes in a Graph
	 * 
	 * @param g
	 *            The Graph containing the two Nodes
	 * @param start
	 *            The data of the Node at the start of the path
	 * @param dest
	 *            The data of the Node at the end of the path
	 * @throws IllegalArgumentException
	 *             if g is null or start/dest is not in g
	 * @return A list of Edges representing the shortest path from start to
	 *         dest.
	 */
	public static List<Edge> findPath(Graph g, String start, String dest) {
		if (g == null) {
			throw new IllegalArgumentException("The Graph can not be null");
		}
		if (!g.contains(start) || !g.contains(dest)) {
			throw new IllegalArgumentException("one of the characters is not in the Graph");
		}
		LinkedList<String> queue = new LinkedList<String>();
		Map<String, List<Edge>> visited = new HashMap<String, List<Edge>>();
		queue.add(start);
		visited.put(start, new ArrayList<Edge>());
		while (!queue.isEmpty()) {
			//node to explore
			String node = queue.remove();
			if (node.equals(dest)) {
				return visited.get(node);
			}
			//explore node's children
			Set<Edge> edges = new TreeSet<Edge>();
			edges.addAll(g.getOutgoingEdges(node));
			for (Edge e : edges) {
				//if not visited, append path and mark as visited 
				if (!visited.containsKey(e.getChild())) {
					List<Edge> path = visited.get(node);
					List<Edge> path_appended = new ArrayList<Edge>(path);
					path_appended.add(e);
					visited.put(e.getChild(), path_appended);
					String next = e.getChild();
					queue.add(next);
				}
			}
		}
		//return null if no path found
		return null;
	}

	//Main method to support command-line input
	public static void main(String[] args) throws Exception {
		Graph g = makeGraph("src/hw6/data/marvel.tsv");
		Scanner sc = new Scanner(System.in);
		boolean again = true;
		while (again) {
			System.out.println("Shortest path between which two Marvel characters! Enter the first character:");
			String c1 = sc.nextLine();
			System.out.println("Enter the second character:");
			String c2 = sc.nextLine();
			if (!g.contains(c1) || !g.contains(c2)) {
				System.out.println("One of your characters in not in Marvel!");
			} else {
				List<Edge> path = findPath(g, c1, c2);
				if (path == null) {
					System.out.println("no path between " + c1 + " and " + c2 + "!");
				} else {
					System.out.println("Shortest Path: " + path.toString());
				}
			}
			System.out.println("Would you like to try two more characters? (yes/no):");
			String ans = sc.next();
			sc.nextLine();
			if (ans.startsWith("n") || ans.startsWith("N")) {
				again = false;
				sc.close();
			}
		}
	}
}