package hw8.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hw8.*;

public class TestCampusPaths {

	// constants
	CampusPaths UW;
	Location KANE;
	Location CSE;
	CampusPaths empty;
	CampusPaths twoBuildings;
	CampusPaths multiBuildings;

	@Before
	public void Initialize() throws Exception  {
		KANE = new Location(1874.338, 1212.4713);
		CSE = new Location(2259.7112, 1715.5273);
		UW = new CampusPaths("src/hw8/data/campus_buildings.dat", "src/hw8/data/campus_paths.dat");
		empty = new CampusPaths("src/hw8/data/empty.dat", "src/hw8/data/empty.dat");
		twoBuildings = new CampusPaths("src/hw8/data/twoBuildings.dat", "src/hw8/data/onePath.dat");
		multiBuildings = new CampusPaths("src/hw8/data/multiBuildings.dat", "src/hw8/data/multiPaths.dat");
	}

	// Constructor Tests
	@Test(expected = IllegalArgumentException.class)
	public void testNullArguments() throws Exception {
		new CampusPaths(null, "src/hw8/data/campus_paths.dat");
	}

	// FindPath Tests
	@Test(expected = IllegalArgumentException.class)
	public void testFindPathException() {
		assertEquals(UW.findPath(CSE, null), null);
	}

	@Test
	public void FindPathCorrect() {
		UW.findPath(CSE, KANE);
		UW.findPath(KANE, CSE);
	}

	// getter Tests
	@Test
	public void testGetFullBuildingName() {
		assertEquals(UW.getFullBuildingName("CSE"), "Paul G. Allen Center for Computer Science & Engineering");
		assertEquals(UW.getFullBuildingName("KNE"), "Kane Hall (North Entrance)");
		assertEquals(twoBuildings.getFullBuildingName("CLK"), "Clarke Residence");
		assertEquals(multiBuildings.getFullBuildingName("C"), "Building C");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetFullBuildingNameException() {
		UW.getFullBuildingName("cse");
	}

	@Test
	public void testGetAbbreviatedName() {
		assertEquals(UW.getAbbreviatedName("Paul G. Allen Center for Computer Science & Engineering"), "CSE");
		assertEquals(UW.getAbbreviatedName("Kane Hall (North Entrance)"), "KNE");
		assertEquals(twoBuildings.getAbbreviatedName("Smith Residence"), "SMTH");
		assertEquals(multiBuildings.getAbbreviatedName("Building D"), "D");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetAbbreviatedNameException() {
		UW.getAbbreviatedName("Paul Allen Center for Computer Science & Engineering");
	}

	@Test
	public void testGetLocation() {
		assertEquals(UW.getLocation("CSE"), new Location(2259.7112, 1715.5273));
		assertEquals(UW.getLocation("KNE"), new Location(1876.6109, 1165.2467));
		assertEquals(twoBuildings.getLocation("SMTH"), new Location(31.55, 0.11));
		assertEquals(multiBuildings.getLocation("D"), new Location(2.54, -1.98));
	}

}
