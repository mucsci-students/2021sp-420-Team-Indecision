package team.indecision.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class GUI extends JPanel{



	// Add mouse listeners here or in controller 
	
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	private JMenuBar menuBar;
	
	public GUI() {
		
		frame = new JFrame("UML - Team Indecision");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1280, 720));
		setLayout(null); 


		try {
         	UIManager.setLookAndFeel(
            	UIManager.getSystemLookAndFeelClassName());
    	} 
   	 	catch (Exception e) {
			System.out.println("Look and feel not set on this system.");
		}

		menuBar = new JMenuBar();
		
		////////////////////////////JMenueBar Classes//////////////////////////////////////

		JMenu classMenu = new JMenu("Class");
		menuBar.add(classMenu);
		
		JMenuItem addClassItem = new JMenuItem("Add Class");
		classMenu.add(addClassItem);

		JMenuItem deleteClassItem = new JMenuItem("Delete Class");
		classMenu.add(deleteClassItem);

		JMenuItem editClassNameItem = new JMenuItem("Edit Class Name");
		classMenu.add(editClassNameItem);
		
		////////////////////////////JMenueBar Fields///////////////////////////////////////
		
		JMenu fieldMenu = new JMenu("Field");
		menuBar.add(fieldMenu);
		
		JMenuItem addFieldItem = new JMenuItem("Add Field");
		fieldMenu.add(addFieldItem);

		JMenuItem deleteFieldItem = new JMenuItem("Delete Field");
		fieldMenu.add(deleteFieldItem);

		JMenuItem editFieldNameItem = new JMenuItem("Edit Field Name");
		fieldMenu.add(editFieldNameItem);

		////////////////////////////JMenueBar Methods//////////////////////////////////////
		
		JMenu methodMenu = new JMenu("Method");
		menuBar.add(methodMenu);
		
		JMenuItem addMethodItem = new JMenuItem("Add Method");
		methodMenu.add(addMethodItem);

		JMenuItem deleteMethodItem = new JMenuItem("Delete Method");
		methodMenu.add(deleteMethodItem);

		JMenuItem editMethodNameItem = new JMenuItem("Edit Method Name");
		methodMenu.add(editMethodNameItem);

		JMenuItem editMethodParametersItem = new JMenuItem("Edit Method Parameters");
		methodMenu.add(editMethodParametersItem);
			
		////////////////////////////JMenueBar Relationships////////////////////////////////
		
		JMenu relationshipMenu = new JMenu("Relationship");
		menuBar.add(relationshipMenu);
		
		JMenuItem addRelationshipItem = new JMenuItem("Add Relationship");
		relationshipMenu.add(addRelationshipItem);
		
		JMenuItem deleteRelationshipItem = new JMenuItem("Delete Relationship");
		relationshipMenu.add(deleteRelationshipItem);
		
		JMenuItem editRelationshipDestinationItem = new JMenuItem("Edit Relationship Destination");
		relationshipMenu.add(editRelationshipDestinationItem);
		
		JMenuItem editRelationshipTypeItem = new JMenuItem("Edit Relationship Type");
		relationshipMenu.add(editRelationshipTypeItem);
		
		////////////////////////////JMenueBar Save and Load////////////////////////////////
		
		JMenu saveAndLoadMenu = new JMenu("Save and Load");
		menuBar.add(saveAndLoadMenu);
				
		JMenuItem saveItem = new JMenuItem("Save");
		saveAndLoadMenu.add(saveItem);
				
		JMenuItem loadItem = new JMenuItem("Load");
		saveAndLoadMenu.add(loadItem);
		
		////////////////////////////JMenueBar Save and Load//////////////////////////////////////
		JMenu undoAndRedoMenu = new JMenu("Undo and Redo");
		menuBar.add(undoAndRedoMenu);
		
		JMenuItem undoItem = new JMenuItem("Undo");
		undoAndRedoMenu.add(undoItem);
		
		JMenuItem redoItem = new JMenuItem("Redo");
		undoAndRedoMenu.add(redoItem);
				
		frame.setJMenuBar(menuBar);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void addActionListener (ActionListener listener, int menuPosition, int menuItemPosition) {
		
		menuBar.getMenu(menuPosition).getItem(menuItemPosition).addActionListener(listener);
	}
}
