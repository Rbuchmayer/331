package hw8.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import hw5.*;
import hw8.*;

public class TestCampusParser {

	// constants
	String UW_Buildings;
	String UW_Paths;
	String twoBuildings;
	String multiBuildings;
	String empty;
	String malformed;
	Location KANE;
	Location CSE;
	Map<String, String> abbToFull;
	Map<String, String> fullToAbb;
	Map<String, Location> locations;
	Graph<Location, Double> g;

	@Before
	public void Initialize() throws Exception {
		KANE = new Location(1874.338, 1212.4713);
		CSE = new Location(2259.7112, 1715.5273);
		UW_Buildings = "src/hw8/data/campus_buildings.dat";
		UW_Paths = "src/hw8/data/campus_paths.dat";
		empty = "src/hw8/data/empty.dat";
		twoBuildings = "src/hw8/data/twoBuildings.dat";
		multiBuildings = "src/hw8/data/multiBuildings.dat";
		malformed = "src/hw8/data/malformedBuildings.dat";
		abbToFull = new HashMap<String, String>();
		fullToAbb = new HashMap<String, String>();
		locations = new HashMap<String, Location>();
	}

	// parseData Tests
	@Test(expected = Exception.class)
	public void testParseDataException() throws Exception {
		CampusParser.parseData(malformed, abbToFull, locations, fullToAbb);
	}

	@Test
	public void testParseDataNames() throws Exception {
		CampusParser.parseData(UW_Buildings, abbToFull, locations, fullToAbb);
		assertTrue(abbToFull.containsKey("KNE"));
		assertTrue(abbToFull.containsKey("ROB"));
		assertEquals(fullToAbb.get("Intramural Activities Building"), "IMA");
		assertEquals(fullToAbb.get("Roberts Hall"), "ROB");
	}

	@Test
	public void testParseDataLocations() throws Exception {
		CampusParser.parseData(multiBuildings, abbToFull, locations, fullToAbb);
		assertEquals(locations.get("A"), new Location(0.00, 0.00));
		assertEquals(locations.get("C"), new Location(-2.12, -4.67));
	}

	// makeGraph Tests
	@Test(expected = IllegalArgumentException.class)
	public void testMakeGraphException() throws Exception {
		CampusParser.makeGraph(malformed, g);
	}
	
}
