package hw7;

import java.util.*;
import hw5.Graph;
import hw5.Edge;

public class MarvelPaths2 {

	/*
	 * MarvelPaths2 does not have an AF, RI, or checkRep() because all its
	 * methods are static, and we will not create MarvelPath2 objects
	 */

	/**
	 * Returns a weighted Graph built from a given file
	 * 
	 * @param file
	 *            path to the file to build the Graph from
	 * @requires file is a correct path to the file and is in the correct .tsv
	 *           format.
	 * @return A weighted Graph<String, Double> built from file, null if file is
	 *         null
	 */
	public static Graph<String, Double> makeGraph(String file) throws Exception {
		if (file == null) {
			return null;
		}
		// parse the file and initialize Graph to return
		Map<String, HashMap<String, Integer>> counts = new HashMap<String, HashMap<String, Integer>>();
		MarvelParser2.parseData(file, counts);
		Graph<String, Double> ret = new Graph<String, Double>();

		// add nodes to Graph
		for (String node : counts.keySet()) {
			ret.addNode(node);
		}

		// Go through parsed data and add weighted Edges
		for (String n1 : counts.keySet()) {
			HashMap<String, Integer> subMap = counts.get(n1);
			for (String n2 : subMap.keySet()) {
				int x = subMap.get(n2);
				if (!n1.equals(n2)) {
					ret.addEdge(n1, new Edge<String, Double>(n2, 1.000 / x));
					ret.addEdge(n2, new Edge<String, Double>(n1, 1.000 / x));
				}
			}
		}
		return ret;
	}

	/**
	 * Returns the shortest weighted path from start to dest
	 * 
	 * @param g
	 *            Graph to look for path in
	 * @param start
	 *            Node to start at
	 * @param dest
	 *            Node to finish at
	 * @requires g != null, start != null, dest != null, start and dest are in
	 *           the graph
	 * @return A list of Edges representing the shortest weighted path from
	 *         start to dest, null if there is no path.
	 */
	public static <N> List<Edge<N, Double>> findPath(Graph<N, Double> g, N start, N dest) {

		if (g == null || start == null || dest == null) {
			throw new IllegalArgumentException("One of the arguments is null");
		}
		// Inner class to compare by lowest cost
		class compareEdges implements Comparator<ArrayList<Edge<N, Double>>> {
			@Override
			public int compare(ArrayList<Edge<N, Double>> o1, ArrayList<Edge<N, Double>> o2) {
				Edge<N, Double> e1 = o1.get(o1.size() - 1);
				Edge<N, Double> e2 = o2.get(o2.size() - 1);
				if (!(e1.getLabel().equals(e2.getLabel()))) {
					return e1.getLabel().compareTo(e2.getLabel());
				}

				return o1.size() - o2.size();
			}
		}

		// initialize working and finished collections
		Queue<ArrayList<Edge<N, Double>>> active = new PriorityQueue<ArrayList<Edge<N, Double>>>(5, new compareEdges());
		Set<N> finished = new HashSet<N>();
		ArrayList<Edge<N, Double>> first = new ArrayList<Edge<N, Double>>();

		// add path from start to itself
		first.add(new Edge<N, Double>(start, 0.0));
		active.add(first);

		while (!active.isEmpty()) {
			// check if algorithm is finished
			ArrayList<Edge<N, Double>> minPath = active.poll();
			N minDest = minPath.get(minPath.size() - 1).getChild();

			if (minDest.equals(dest)) {
				return minPath;
			}
			if (finished.contains(minDest)) {
				continue;
			}
			Set<Edge<N, Double>> children = g.getOutgoingEdges(minDest);
			// search next child Node
			for (Edge<N, Double> e : children) {
				if (!finished.contains(e.getChild())) {
					// calculate weighted cost
					double cost = minPath.get(minPath.size() - 1).getLabel() + e.getLabel();
					ArrayList<Edge<N, Double>> newPath = new ArrayList<Edge<N, Double>>(minPath);
					newPath.add(new Edge<N, Double>(e.getChild(), cost));
					active.add(newPath);
				}
			}
			finished.add(minDest);
		}
		// return null if no path
		return null;

	}

}
