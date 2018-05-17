package hw7.test;

import java.io.*;
import java.util.*;

import hw5.Edge;
import hw5.Graph;
import hw7.MarvelPaths2;

/**
 * This class implements a testing driver which reads test scripts from files
 * for your graph ADT and improved MarvelPaths application using Dijkstra's
 * algorithm.
 **/
public class HW7TestDriver {

	public static void main(String args[]) {
		try {
			if (args.length > 1) {
				printUsage();
				return;
			}

			HW7TestDriver td;

			if (args.length == 0) {
				td = new HW7TestDriver(new InputStreamReader(System.in), new OutputStreamWriter(System.out));
			} else {

				String fileName = args[0];
				File tests = new File(fileName);

				if (tests.exists() || tests.canRead()) {
					td = new HW7TestDriver(new FileReader(tests), new OutputStreamWriter(System.out));
				} else {
					System.err.println("Cannot read from " + tests.toString());
					printUsage();
					return;
				}
			}

			td.runTests();

		} catch (IOException e) {
			System.err.println(e.toString());
			e.printStackTrace(System.err);
		}
	}

	private static void printUsage() {
		System.err.println("Usage:");
		System.err.println("to read from a file: java hw5.test.HW5TestDriver <name of input script>");
		System.err.println("to read from standard in: java hw5.test.HW5TestDriver");
	}

	/** String -> Graph: maps the names of graphs to the actual graph **/
	private final Map<String, Graph<String, Double>> graphs = new HashMap<String, Graph<String, Double>>();
	private final PrintWriter output;
	private final BufferedReader input;

	/**
	 * @requires r != null && w != null
	 *
	 * @effects Creates a new HW5TestDriver which reads command from <tt>r</tt>
	 *          and writes results to <tt>w</tt>.
	 **/

	public HW7TestDriver(Reader r, Writer w) {
		input = new BufferedReader(r);
		output = new PrintWriter(w);
	}

	public void runTests() throws IOException {
		String inputLine;
		while ((inputLine = input.readLine()) != null) {
			if ((inputLine.trim().length() == 0) || (inputLine.charAt(0) == '#')) {
				// echo blank and comment lines
				output.println(inputLine);
			} else {
				// separate the input line on white space
				StringTokenizer st = new StringTokenizer(inputLine);
				if (st.hasMoreTokens()) {
					String command = st.nextToken();

					List<String> arguments = new ArrayList<String>();
					while (st.hasMoreTokens()) {
						arguments.add(st.nextToken());
					}

					executeCommand(command, arguments);
				}
			}
			output.flush();
		}
	}

	private void executeCommand(String command, List<String> arguments) {
		try {
			if (command.equals("CreateGraph")) {
				createGraph(arguments);
			} else if (command.equals("AddNode")) {
				addNode(arguments);
			} else if (command.equals("AddEdge")) {
				addEdge(arguments);
			} else if (command.equals("ListNodes")) {
				listNodes(arguments);
			} else if (command.equals("ListChildren")) {
				listChildren(arguments);
			} else if (command.equals("LoadGraph")) {
				loadGraph(arguments);
			} else if (command.equals("FindPath")) {
				findPath(arguments);
			} else {
				output.println("Unrecognized command: " + command);
			}
		} catch (Exception e) {
			output.println("Exception: " + e.toString());
		}
	}

	private void createGraph(List<String> arguments) {
		if (arguments.size() != 1) {
			throw new CommandException("Bad arguments to CreateGraph: " + arguments);
		}

		String graphName = arguments.get(0);
		createGraph(graphName);
	}

	private void createGraph(String graphName) {
		Graph<String, Double> g = new Graph<String, Double>();

		graphs.put(graphName, g);
		output.println("created graph " + graphName);
	}

	private void addNode(List<String> arguments) {
		if (arguments.size() != 2) {
			throw new CommandException("Bad arguments to addNode: " + arguments);
		}

		String graphName = arguments.get(0);
		String nodeName = arguments.get(1);

		addNode(graphName, nodeName);
	}

	private void addNode(String graphName, String nodeName) {
		Graph<String, Double> g = graphs.get(graphName);
		g.addNode(nodeName);
		output.println("added node " + nodeName + " to " + graphName);
	}

	private void addEdge(List<String> arguments) {
		if (arguments.size() != 4) {
			throw new CommandException("Bad arguments to addEdge: " + arguments);
		}

		String graphName = arguments.get(0);
		String parentName = arguments.get(1);
		String childName = arguments.get(2);
		double edgeLabel;

		try {
			edgeLabel = Double.parseDouble(arguments.get(3));
		} catch (Exception e) {
			throw new CommandException("Last argument is not a double");
		}

		addEdge(graphName, parentName, childName, edgeLabel);
	}

	private void addEdge(String graphName, String parentName, String childName, Double edgeLabel) {

		Graph<String, Double> g = graphs.get(graphName);
		Edge<String, Double> e = new Edge<String, Double>(childName, edgeLabel);

		g.addEdge(parentName, e);
		String cost = String.format("%.3f", e.getLabel());
		output.println("added edge " + cost + " from " + parentName + " to " + childName + " in " + graphName);
	}

	private void listNodes(List<String> arguments) {
		if (arguments.size() != 1) {
			throw new CommandException("Bad arguments to listNodes: " + arguments);
		}

		String graphName = arguments.get(0);
		listNodes(graphName);
	}

	private void listNodes(String graphName) {

		Graph<String, Double> g = graphs.get(graphName);
		Set<String> nodes = g.getNodes();
		output.print(graphName + " contains:");
		for (String n : nodes) {
			output.print(" " + n);
		}
		output.println();
	}

	private void listChildren(List<String> arguments) {
		if (arguments.size() != 2) {
			throw new CommandException("Bad arguments to listChildren: " + arguments);
		}

		String graphName = arguments.get(0);
		String parentName = arguments.get(1);
		listChildren(graphName, parentName);
	}

	private void listChildren(String graphName, String parentName) {

		Graph<String, Double> g = graphs.get(graphName);
		Set<Edge<String, Double>> edges = g.getOutgoingEdges(parentName);
		output.print("the children of " + parentName + " in " + graphName + " are:");
		for (Edge<String, Double> e : edges) {
			String cost = String.format("%.3f", e.getLabel());
			output.print(" " + e.getChild() + "(" + cost + ")");
		}
		output.println();
	}

	private void loadGraph(List<String> arguments) throws Exception {
		if (arguments.size() != 2) {
			throw new CommandException("Bad arguments to loadGraph: " + arguments);
		}
		String graphName = arguments.get(0);
		String filename = arguments.get(1);
		loadGraph(graphName, filename);
	}

	private void loadGraph(String graphName, String filename) throws Exception {
		filename = "src/hw7/data/" + filename;
		graphs.put(graphName, MarvelPaths2.makeGraph(filename));
		output.println("loaded graph " + graphName);
	}

	private void findPath(List<String> arguments) {
		if (arguments.size() != 3) {
			throw new CommandException("Bad arguments to findPath: " + arguments);
		}
		String graphName = arguments.get(0);
		String node1 = arguments.get(1);
		String node2 = arguments.get(2);
		node1 = node1.replace('_', ' ');
		node2 = node2.replace('_', ' ');

		findPath(graphName, node1, node2);
	}

	private void findPath(String graphName, String node1Name, String node2Name) {
		Graph<String, Double> g = graphs.get(graphName);
		if ((!g.contains(node1Name)) && (!g.contains(node2Name))) {
			output.println("unknown character " + node1Name);
			output.println("unknown character " + node2Name);
		} else if (!(g.contains(node1Name))) {
			output.println("unknown character " + node1Name);
		} else if (!(g.contains(node2Name))) {
			output.println("unknown character " + node2Name);
		} else {
			String result = "path from " + node1Name + " to " + node2Name + ":";
			List<Edge<String, Double>> path = MarvelPaths2.findPath(g, node1Name, node2Name);

			if (path == null) {
				result += "\n" + "no path found";
			} else {
				String node1 = node1Name;
				double cost = 0.0;
				path = path.subList(1, path.size());
				for (Edge<String, Double> edge : path) {
					result += "\n" + node1 + " to " + edge.getChild() + " with weight "
							+ String.format("%.3f", (edge.getLabel() - cost));
					cost = edge.getLabel();
					node1 = edge.getChild();
				}
				result += "\n" + "total cost: " + String.format("%.3f", cost);
			}

			output.println(result);
		}
	}

	/**
	 * This exception results when the input file cannot be parsed properly
	 **/
	static class CommandException extends RuntimeException {

		public CommandException() {
			super();
		}

		public CommandException(String s) {
			super(s);
		}

		public static final long serialVersionUID = 3495;
	}
}
