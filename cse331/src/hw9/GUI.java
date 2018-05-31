package hw9;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.*;
import hw8.*;
import java.awt.Color;

/**
 * GUI represents the Graphical User Interface of the Campus Pathfinder
 * application. It is the view and controller.
 *
 */

public class GUI {

	/*
	 * GUI does not have a rep invariant, AF, or checkRep() because it is not an
	 * ADT.
	 */

	// CampusPaths to use
	private CampusPaths cp;

	// JFrame to use
	private JFrame frame;

	/**
	 * Constructs a GUI object using a CampusPaths object
	 * 
	 * @param cp
	 *            The CampusPaths object to use in the GUI
	 * @requires cp != null
	 * @throws IOException
	 *             if there is an issue with the image file in the view.
	 */
	public GUI(CampusPaths cp) throws IOException {
		if (cp == null) {
			throw new IllegalArgumentException("cp cannot be null!");
		}

		// initialize fields and create a View and Controller
		this.cp = cp;
		View view = new View(this.cp);
		Controller controller = new Controller(this.cp, view);
		frame = new JFrame("UW Campus Pathfinder");

		// add control and view to frame
		frame.add(controller);
		frame.add(view);

		// set traits of the controller and frame
		controller.setPreferredSize(new Dimension(1200, 100));
		controller.setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		frame.setSize(1024, 768);

		// make frame visible
		frame.pack();
		frame.setVisible(true);

	}
}
