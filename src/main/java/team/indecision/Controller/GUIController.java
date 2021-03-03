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

import java.util.ArrayList;
import java.util.List;

public class GUIController {

	private Classes model;
	private GUI gui;

	public GUIController() {

	}

	public GUIController(GUI guiP, Classes classes) {
		model = classes;
		gui = guiP;
	}

	//////////////////////////// Class Action
	//////////////////////////// Listeners//////////////////////////////////////

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

	//////////////////////////// Field Action
	//////////////////////////// Listeners///////////////////////////////////////////////
	public ActionListener addFieldListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the field will be added to: ");
				if (className != null) {
					String fieldName = promptInput("Enter new field name: ");
					if (fieldName != null) {
						model.addFieldGUI(gui.frame, className, fieldName);
						refreshJFrame();
					}
				}
			}
		};
	}

	public ActionListener deleteFieldListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the field will be added to: ");
				if (className != null) {
					String deletedField = promptInput("Enter the field name to be deleted: ");
					if (deletedField != null) {
						model.addFieldGUI(gui.frame, className, deletedField);
						refreshJFrame();
					}
				}
			}
		};
	}

	public ActionListener renameFieldListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the field will be added to: ");
				if (className != null) {
					String original = promptInput("Enter the field you want to rename: ");
					if (original != null) {
						String newName = promptInput("Enter the new name of the field: ");
						if (newName != null) {
							model.editFieldGUI(gui.frame, className, original, newName);
							refreshJFrame();
						}
					}
				}
			}
		};
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////////// Relationship
	//////////////////////////// ActionListeners////////////////////////////////////

	public ActionListener addRelationshipListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the relationship will be added to: ");
				if (className != null) {
					String relationshipName = promptInput("Enter new relationship destination: ");
					if (relationshipName != null) {
						String relationshipType = promptInput("Enter new relationship type: ");
						if (relationshipType != null) {
							model.addRelationshipGUI(gui.frame, className, relationshipName, relationshipType);
						}
					}
				}
			}
		};
	}

	public ActionListener deleteRelationshipListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the relationship will be removed from: ");
				if (className != null) {
					String relationshipName = promptInput("Enter relationship destination: ");
					if (relationshipName != null) {
						String relationshipType = promptInput("Enter relationship type: ");
						if (relationshipType != null) {
							model.deleteRelationshipGUI(gui.frame, className, relationshipName, relationshipType);
						}
					}
				}

			}
		};
	}

	public ActionListener editRelationshipDestination() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the relationship will be renamed from: ");
				if (className != null) {
					String relationshipOldName = promptInput("Enter old relationship destination: ");
					if (relationshipOldName != null) {
						String relationshipNewName = promptInput("Enter new relationship destination: ");
						if (relationshipNewName != null) {
							model.editRelationshipDestinationGUI(gui.frame, className, relationshipOldName,
									relationshipNewName);
						}
					}
				}

			}
		};
	}

	public ActionListener editRelationshipType() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the relationship will be renamed from: ");
				if (className != null) {
					String relationshipOldName = promptInput("Enter relationship destination: ");
					if (relationshipOldName != null) {
						String relationshipNewType = promptInput("Enter new relationship type: ");
						if (relationshipNewType != null) {
							model.editRelationshipDestinationGUI(gui.frame, className, relationshipOldName,
									relationshipNewType);
						}
					}
				}
			}
		};
	}

	//////////////////////////// Method
	//////////////////////////// ActionListeners////////////////////////////////////

	public ActionListener addMethodListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the method will be added to: ");
				if (className != null) {
					String methodName = promptInput("Enter new method name: ");
					if (methodName != null) {
						List<String> parameters = promptMultipleInput("Enter new method parameters: ");
						if (parameters != null) {
							model.addMethod(className, methodName, parameters);
						}
					}
				}
			}
		};
	}

	public ActionListener deleteMethodListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name the method will be deleted from: ");
				if (className != null) {
					String methodName = promptInput("Enter method name to delete: ");
					if (methodName != null) {
						List<String> parameters = promptMultipleInput("Enter method parameters to delete: ");
						if (parameters != null) {
							model.deleteMethod(className, methodName, parameters);
						}
					}
				}
			}
		};
	}

	public ActionListener editMethodNameListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name of the method you want to change: ");
				if (className != null) {
					String methodOldName = promptInput("Enter old method name: ");
					if (methodOldName != null) {
						String methodNewName = promptInput("Enter new method name: ");
						if (methodNewName != null) {
							List<String> parameters = promptMultipleInput("Enter method parameters: ");
							if (parameters != null) {
								model.editMethodName(className, methodOldName, parameters, methodNewName);
							}
						}
					}
				}
			}
		};
	}

	public ActionListener editMethodParametersListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = promptInput("Enter class name of the method you want to change parameters: ");
				if (className != null) {
					String methodName = promptInput("Enter method name: ");
					if (methodName != null) {
						List<String> oldParameters = promptMultipleInput("Enter old method parameters: ");
						if (oldParameters != null) {
							List<String> newParameters = promptMultipleInput("Enter new method parameters: ");
							if (newParameters != null) {
								model.editMethodParameters(className, methodName, oldParameters, newParameters);
							}
						}
					}
				}
			}
		};
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////

	public String promptInput(String message) {
		return JOptionPane.showInputDialog(gui.frame, message);
	}

	public List<String> promptMultipleInput(String message) {
		List<String> parameters = new ArrayList<String>();
		boolean bool = true;
		while (bool) {
			String parameter = JOptionPane.showInputDialog(gui.frame, message);
			if (parameter != null) {
				parameters.add(parameter);
				int confirmed = JOptionPane.showConfirmDialog(null, "Are you done entering parameters?",
						"Exit Program Message Box", JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					bool = false;
				}
			} else {
				bool = false;
			}
		}
		return parameters;
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
