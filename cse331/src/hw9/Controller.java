package hw9;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import hw8.*;

/**
 * Controller represents the Control of the Campus Pathfinder tool. It deals
 * with interactions with the user.
 *
 */
public class Controller extends JPanel {
	
	/*
	 * Controller does not have a rep invariant, AF, or checkRep() because it
	 * does not represent an ADT.
	 */
	
	private static final long serialVersionUID = 1L;
	private CampusPaths cp;
	private View view;
	private TreeSet<String> buildings;
	private JLabel start;
	private JLabel dest;
	private JLabel dist;
	private JComboBox<String> startBuildingMenu;
	private JComboBox<String> destBuildingMenu;

	/**
	 * Constructs a Controller object with a CampusPaths and View object
	 * 
	 * @param cp
	 *            The campusPaths to use
	 * @param view
	 *            The view to use
	 * @requires cp != null and view != null
	 */
	public Controller(CampusPaths cp, View view) {
		if (cp == null || view == null) {
			throw new IllegalArgumentException("Neither argument can be null!");
		}

		// set/initialize fields
		this.cp = cp;
		this.view = view;
		buildings = new TreeSet<String>();
		buildings.addAll(this.cp.getBuildings());
		start = new JLabel("Starting building (Green): ");
		dest = new JLabel("Ending building (Blue): ");
		startBuildingMenu = new JComboBox<String>(buildings.toArray(new String[0]));
		destBuildingMenu = new JComboBox<String>(buildings.toArray(new String[0]));
		dist = new JLabel("  0 feet");

		// initialize Panels and Buttons
		JPanel select = new JPanel(new GridBagLayout());
		JPanel buttons = new JPanel(new GridLayout(2, 2));
		JPanel distPanel = new JPanel(new GridLayout(2, 2));
		JButton findPath = new JButton("Find Path");
		JButton reset = new JButton("Reset");
		JLabel distLabel = new JLabel("  Total distance:");

		// add components to frame
		findPath.addActionListener(new Update());
		reset.addActionListener(new Update());
		select.add(start);
		select.add(startBuildingMenu);
		select.add(dest);
		select.add(destBuildingMenu);
		buttons.add(findPath);
		buttons.add(reset);
		this.add(select);
		this.add(buttons);
		distPanel.add(distLabel);
		distPanel.add(dist);
		this.add(distPanel);

		// set background color
		distPanel.setBackground(Color.WHITE);
		buttons.setBackground(Color.WHITE);
		select.setBackground(Color.WHITE);
	}

	/**
	 * Updates the state of the GUI
	 *
	 */
	private class Update implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// get user input
			String ans = e.getActionCommand();

			if (ans.equals("Reset")) {
				// reset to original state
				startBuildingMenu.setSelectedItem("Bagley Hall (East Entrance)");
				destBuildingMenu.setSelectedItem("Bagley Hall (East Entrance)");
				dist.setText("  0 feet");
				view.reset();
			} else {
				// display distance
				String start = (String) startBuildingMenu.getSelectedItem();
				String dest = (String) destBuildingMenu.getSelectedItem();
				double d = view.getDistance(start, dest);
				dist.setText(String.format("  %.0f feet", d));
			}
		}
	}
}
