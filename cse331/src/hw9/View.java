package hw9;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import hw8.*;

/**
 * View represents the View of the Campus Pathfinder tool. It helps display
 * items to the user.
 *
 */

public class View extends JComponent {

	/*
	 * View does not have a rep invariant, AF, or checkRep() becuase it does not
	 * represent an ADT.
	 */
	
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private CampusPaths cp;
	private ArrayList<Location> points;
	private Map<Location, Double> path;
	private BufferedImage img;
	private double xRatio;
	private double yRatio;

	/**
	 * Constructs a Veiw object
	 * 
	 * @param cp
	 *            The CampusPaths to use in the View
	 * @requires cp != null
	 * @throws IOException
	 *             if there is an issue with the image file.
	 */
	public View(CampusPaths cp) throws IOException {

		if (cp == null) {
			throw new IllegalArgumentException("cp cannot be null!");
		}

		// set/initialize fields
		width = 1000;
		height = 750;
		this.cp = cp;
		path = null;
		points = null;
		File file = new File("src/hw8/data/campus_map.jpg");
		img = ImageIO.read(file);
		yRatio = ((double) height) / img.getHeight();
		xRatio = ((double) width) / img.getWidth();
		
		// set image size
		this.setPreferredSize(new Dimension(width, height));
	}

	/**
	 * Links Graphics and this View
	 * 
	 * @param g
	 *            The Graphics to use
	 * @requires g != null
	 * @modifies this
	 * @effects displays buildings, paths, and resizes the image for the user.
	 */
	@Override
	public void paintComponent(Graphics g) {
		if (g == null) {
			throw new IllegalArgumentException("g cannot be null!");
		}

		// call parent class and cast to Graphics 2D
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// set fields
		width = this.getWidth();
		height = this.getHeight();
		xRatio = ((double) width) / img.getWidth();
		yRatio = ((double) height) / img.getHeight();

		// paint campus map
		g2d.drawImage(img, 0, 0, width, height, 0, 0, img.getWidth(), img.getHeight(), null);
		
		if (path != null) {
			points = new ArrayList<Location>();
			points.addAll(path.keySet());

			// find and paint start point
			int x = (int) Math.round(points.get(0).getX() * xRatio);
			int y = (int) Math.round(points.get(0).getY() * yRatio);
			g2d.setColor(new Color(20, 210, 20, 220));
			g2d.fillOval(x - 8, y - 8, 16, 16);

			// paint the path
			g2d.setColor(Color.RED);
			for (Location l : points) {
				int xDest = (int) Math.round(l.getX() * xRatio);
				int yDest = (int) Math.round(l.getY() * yRatio);
				g2d.drawLine(x, y, xDest, yDest);

				x = xDest;
				y = yDest;
			}

			// paint end point
			g2d.setColor(new Color(20, 20, 200, 200));
			g2d.fillOval(x - 8, y - 8, 16, 16);
		}
	}

	/**
	 * Resets the GUI to its original state
	 */
	public void reset() {
		path = null;
		repaint();
	}

	/**
	 * Gets the distance between two buildings
	 * 
	 * @param startBuilding
	 *            Building to start at
	 * @param destBuilding
	 *            building to end at
	 * @requires both buildings are not null and are valid buildings.
	 * @return The distance between the two buildings
	 */
	public double getDistance(String startBuilding, String destBuilding) {

		// get the location of the two buildings
		String abbStart = cp.getAbbreviatedName(startBuilding);
		String abbDest = cp.getAbbreviatedName(destBuilding);
		Location start = cp.getLocation(abbStart);
		Location dest = cp.getLocation(abbDest);

		double distance;
		if (start.equals(dest)) {

			// buildings are the same, so 0 distance
			path = new HashMap<Location, Double>();
			path.put(start, 0.0);
			points = new ArrayList<Location>(path.keySet());
			distance = 0.0;
		} else {

			// find the path and get distance
			path = cp.findPath(start, dest);
			points = new ArrayList<Location>(path.keySet());
			Location l = points.get(points.size() - 1);
			distance = path.get(l);
		}
		repaint();
		return distance;
	}
}
