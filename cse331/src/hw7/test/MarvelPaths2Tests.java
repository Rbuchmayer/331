package hw7.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import hw5.Graph;
import hw7.MarvelPaths2;

public class MarvelPaths2Tests {

	// makeGraph Tests
	@Test
	public void testMakeGraphNull() throws Exception {
		assertEquals(MarvelPaths2.makeGraph(null), null);
		MarvelPaths2.makeGraph("src/hw7/data/complexGraph.tsv");
		MarvelPaths2.makeGraph("src/hw7/data/emptyGraph.tsv");
	}

	// findPath Tests
	@Test
	public void testFindPathNoPath() throws Exception {
		Graph<String, Double> g = MarvelPaths2.makeGraph("src/hw7/data/complexGraph.tsv");
		assertEquals(MarvelPaths2.findPath(g, "A", "H"), null);
	}

	// exception Tests
	@Test(expected = IllegalArgumentException.class)
	public void testFindPathBadCharacters() throws Exception {
		Graph<String, Double> g = MarvelPaths2.makeGraph("src/hw7/data/complexGraph.tsv");
		MarvelPaths2.findPath(g, "Ryan", "Grossman-the-Youngest-of-them-all");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMakeGraphMalformed() throws Exception {
		MarvelPaths2.makeGraph("src/hw7/data/malformedGraph.tsv");
	}
}
