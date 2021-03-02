package team.indecision.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import team.indecision.View.GUI;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class GUIController {

	private Classes model;
	private GUI gui;
	
	public GUIController() {

	}
	
	public GUIController(GUI guiP, Classes classes) {
		model = classes;
		gui = guiP;
	}
	
	////////////////////////////Class Action Listeners//////////////////////////////////////
	
	public ActionListener addClassListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newClassName = promptInput("Enter the new class name: ");
				if (newClassName != null) {
					model.addClassGUI(gui.frame, newClassName);
					refreshJFrame();
				}
			}
		};
	}
	
	public ActionListener deleteClassListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String deletedClass = promptInput("Enter the class name to be deleted: ");
				if (deletedClass != null) {
					model.deleteClassGUI(gui.frame, deletedClass);
					refreshJFrame();
				}
			}
		};
	}
	
	public ActionListener renameClassListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String original = promptInput("Enter the class you want to rename: ");
				if (original != null) {
					String newName = promptInput("Enter the new name of the class: ");
					if (newName != null) {
						model.renameClassGUI(gui.frame, original, newName);
						refreshJFrame();
					}
				}
			}
		};
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////

	
	////////////////////////////Field Action Listeners///////////////////////////////////////////////
	public ActionListener addFieldListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the field will be added to: ");
				String fieldname = promptInput("Enter new field name: ");
				model.addFieldGUI(gui.frame, className,fieldname);
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
				model.addFieldGUI(gui.frame, className, deletedField);
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
				model.editFieldGUI(gui.frame, className, original, newName);
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
				model.addRelationshipGUI(gui.frame, className, relationshipName, relationshipType);
				

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
				model.deleteRelationshipGUI(gui.frame, className, relationshipName, relationshipType);

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
				model.editRelationshipDestinationGUI(gui.frame, className, relationshipOldName, relationshipNewName);
	
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
				model.editRelationshipDestinationGUI(gui.frame, className, relationshipOldName, relationshipNewType);

			}
		};
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////

	
	public String promptInput(String message) {
		// Prompt the user for input and return the input
		return JOptionPane.showInputDialog(gui.frame, message);
	}
	
	public void refreshJFrame() {
		gui.removeAll();
		gui.revalidate();
		gui.repaint();
		for (SortedMap.Entry<String, Class> entry : model.getClasses().entrySet()) {
			JPanel temp = new JPanel();
			JLabel lbl = new JLabel(entry.getValue().toString());
			temp.add(lbl);
			gui.add(lbl);
			gui.frame.pack();
	    }
	}
	
}
