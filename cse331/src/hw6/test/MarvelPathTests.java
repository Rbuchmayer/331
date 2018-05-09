package hw6.test;

import hw6.*;
import hw6.MarvelParser.MalformedDataException;
import hw5.*;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

public final class MarvelPathTests {

	@Before
	public void testAssertsEnabled() {
		CheckAsserts.checkAssertsEnabled();
	}

	// makeGraphNull Tests
	@Test
	public void testMakeGraphNull() throws MalformedDataException  {
		assertEquals(MarvelPaths.makeGraph(null), null);
	}

	// findPath Tests
	@Test(expected = IllegalArgumentException.class)
	public void testFindPathNullGraph() throws MalformedDataException {
		MarvelPaths.findPath(null, "", "");
	}

	// exception  Tests
	@Test(expected = IllegalArgumentException.class)
	public void testFindPathBadCharacters() throws MalformedDataException {
		Graph g = MarvelPaths.makeGraph("src/hw6/data/staffSuperheroes.tsv");
		MarvelPaths.findPath(g, "Ryan", "Grossman-the-Youngest-of-them-all");
	}
	
	@Test(expected = MalformedDataException.class)
	public void testMakeGraphMalformed() throws MalformedDataException {
		 MarvelPaths.makeGraph("src/hw6/data/malformedGraph.tsv");
	}
}