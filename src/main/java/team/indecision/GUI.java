package team.indecision;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JMenuItem addClassItem;
	private static Classes controller = new Classes();
	
	public GUI(Classes controller) {
		GUI.controller = controller;
		
		frame = new JFrame("guiUML");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1280, 720));
		

		JMenuBar menuBar = new JMenuBar();

		////////////////////////////JMenueBar Classes//////////////////////////////////////

		JMenu classMenu = new JMenu("Class");
		menuBar.add(classMenu);
		
		addClassItem = new JMenuItem("Add Class");
		addClassItem.addActionListener(addClassListener());
		classMenu.add(addClassItem);

		addClassItem = new JMenuItem("Delete Class");
		addClassItem.addActionListener(deleteClassListener());
		classMenu.add(addClassItem);

		addClassItem = new JMenuItem("Rename Class");
		addClassItem.addActionListener(renameClassListener());
		classMenu.add(addClassItem);
		
		////////////////////////////JMenueBar Fields//////////////////////////////////////
		JMenu fieldMenu = new JMenu("Field");
		menuBar.add(fieldMenu);
		
		addClassItem = new JMenuItem("Add Feild");
		addClassItem.addActionListener(addFieldListener());
		fieldMenu.add(addClassItem);

		addClassItem = new JMenuItem("Delete Feild");
		addClassItem.addActionListener(deleteFieldListener());
		fieldMenu.add(addClassItem);

		addClassItem = new JMenuItem("Rename Feild");
		addClassItem.addActionListener(renameFieldListener());
		fieldMenu.add(addClassItem);


		////////////////////////////JMenueBar Relationships//////////////////////////////////////
		JMenu relationshipMenu = new JMenu("Relationships");
		menuBar.add(relationshipMenu);
		
		addClassItem = new JMenuItem("Add Relationship");
		addClassItem.addActionListener(addRelationshipListener());
		relationshipMenu.add(addClassItem);

		addClassItem = new JMenuItem("Delete Relationship");
		addClassItem.addActionListener(deleteRelationshipListener());
		relationshipMenu.add(addClassItem);

		addClassItem = new JMenuItem("Edit Relationship Destination");
		addClassItem.addActionListener(editRelationshipDestination());
		relationshipMenu.add(addClassItem);

		addClassItem = new JMenuItem("Edit Relationship Type");
		addClassItem.addActionListener(editRelationshipType());
		relationshipMenu.add(addClassItem);

		frame.setJMenuBar(menuBar);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}
	




	////////////////////////////Class Action Listeners//////////////////////////////////////
	public ActionListener addClassListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newClassName = promptInput("Enter the new class name: ");
				controller.addClassGUI(frame, newClassName);
				resetJFrame();
				refreshJFrame();
			}
		};
		

	}
	
	public ActionListener deleteClassListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String deletedClass = promptInput("Enter the class name to be deleted: ");
				controller.deleteClass(deletedClass);
				resetJFrame();
				refreshJFrame();
			}
		};
	}
	public ActionListener renameClassListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String original = promptInput("Enter the class you want to rename: ");
				String newName = promptInput("Enter the new name of the class: ");

				controller.renameClass(original, newName);

				resetJFrame();
				refreshJFrame();	
			}
		};
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////Feild Action Listeners//////////////////////////////////////
	public ActionListener addFieldListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the field will be added to: ");
				String fieldname = promptInput("Enter new field name: ");
				controller.addField(className,fieldname);
				
				resetJFrame();
				refreshJFrame();
			}
		};
	}
	public ActionListener deleteFieldListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the field will be added to: ");
				String deletedField = promptInput("Enter the field name to be deleted: ");
				controller.addField(className, deletedField);
				resetJFrame();
				refreshJFrame();
			}
		};
	}
	public ActionListener renameFieldListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the field will be added to: ");
				String original = promptInput("Enter the field you want to rename: ");
				String newName = promptInput("Enter the new name of the field: ");

				controller.editField(className, original, newName);

				resetJFrame();
				refreshJFrame();	
			}
		};
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////Relationship Action Listeners//////////////////////////////////////
	public ActionListener addRelationshipListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the relationship will be added to: ");
				String relationshipName = promptInput("Enter new relationship destination: ");
				String relationshipType = promptInput("Enter new relationship type: ");
				controller.addRelationship(className, relationshipName, relationshipType);
				resetJFrame();
				refreshJFrame();
			}
		};
	}
	public ActionListener deleteRelationshipListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the relationship will be removed from: ");
				String relationshipName = promptInput("Enter relationship destination: ");
				String relationshipType = promptInput("Enter relationship type: ");
				controller.deleteRelationship(className, relationshipName, relationshipType);
				resetJFrame();
				refreshJFrame();
			}
		};
	}
	public ActionListener editRelationshipDestination() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the relationship will be renamed from: ");
				String relationshipOldName = promptInput("Enter old relationship destination: ");
				String relationshipNewName = promptInput("Enter new relationship destination: ");
				controller.editRelationshipDestination(className, relationshipOldName, relationshipNewName);
				resetJFrame();
				refreshJFrame();	
			}
		};
	}
	public ActionListener editRelationshipType() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the relationship will be renamed from: ");
				String relationshipOldName = promptInput("Enter relationship destination: ");
				String relationshipNewType = promptInput("Enter new relationship type: ");
				controller.editRelationshipDestination(className, relationshipOldName, relationshipNewType);
				resetJFrame();
				refreshJFrame();	
			}
		};
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////

	
	public String promptInput(String message) {
		// Prompt the user for input and return the input
		return JOptionPane.showInputDialog(frame, message);
	}
	
	public void resetJFrame() {
		removeAll();
		revalidate();
		repaint();
	}
	
	public void refreshJFrame() {
		for (SortedMap.Entry<String, Class> entry : GUI.controller.getClasses().entrySet()) {
			JPanel temp = new JPanel();
			JLabel lbl = new JLabel(entry.getValue().toString());
			temp.add(lbl);
			add(lbl);
			frame.pack();
	    }
	}
}
