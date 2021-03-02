package team.indecision.View;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import team.indecision.Controller.GUIController;
import team.indecision.Model.Classes;

public class GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	private JMenuItem addClassItem;
	private GUIController controller;
	
	public GUI(Classes modelP) {
		
		controller = new GUIController(this, modelP);
		
		frame = new JFrame("guiUML");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1280, 720));
		

		JMenuBar menuBar = new JMenuBar();

		////////////////////////////JMenueBar Classes//////////////////////////////////////

		JMenu classMenu = new JMenu("Class");
		menuBar.add(classMenu);
		
		addClassItem = new JMenuItem("Add Class");
		addClassItem.addActionListener(controller.addClassListener());
		classMenu.add(addClassItem);

		addClassItem = new JMenuItem("Delete Class");
		addClassItem.addActionListener(controller.deleteClassListener());
		classMenu.add(addClassItem);

		addClassItem = new JMenuItem("Rename Class");
		addClassItem.addActionListener(controller.renameClassListener());
		classMenu.add(addClassItem);
		
		////////////////////////////JMenueBar Fields//////////////////////////////////////
		JMenu fieldMenu = new JMenu("Field");
		menuBar.add(fieldMenu);
		
		addClassItem = new JMenuItem("Add Feild");
		addClassItem.addActionListener(controller.addFieldListener());
		fieldMenu.add(addClassItem);

		addClassItem = new JMenuItem("Delete Feild");
		addClassItem.addActionListener(controller.deleteFieldListener());
		fieldMenu.add(addClassItem);

		addClassItem = new JMenuItem("Rename Feild");
		addClassItem.addActionListener(controller.renameFieldListener());
		fieldMenu.add(addClassItem);


		////////////////////////////JMenueBar Relationships//////////////////////////////////////
		JMenu relationshipMenu = new JMenu("Relationships");
		menuBar.add(relationshipMenu);
		
		addClassItem = new JMenuItem("Add Relationship");
		addClassItem.addActionListener(controller.addRelationshipListener());
		relationshipMenu.add(addClassItem);

		addClassItem = new JMenuItem("Delete Relationship");
		addClassItem.addActionListener(controller.deleteRelationshipListener());
		relationshipMenu.add(addClassItem);

		addClassItem = new JMenuItem("Edit Relationship Destination");
		addClassItem.addActionListener(controller.editRelationshipDestination());
		relationshipMenu.add(addClassItem);

		addClassItem = new JMenuItem("Edit Relationship Type");
		addClassItem.addActionListener(controller.editRelationshipType());
		relationshipMenu.add(addClassItem);

		frame.setJMenuBar(menuBar);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		
	}

}
