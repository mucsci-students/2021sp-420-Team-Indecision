package team.indecision.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import team.indecision.View.GUI;
import team.indecision.Command.*;
import team.indecision.Memento.History;
import team.indecision.Memento.Memento;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;
import java.util.ArrayList;
import java.util.List;

public class GUIController {
	private Classes model;
    private GUI view;
    private History history;
    
    public GUIController() {
    	
    }
    
    public GUIController(Classes modelP, GUI viewP) {
        model = modelP;
        view = viewP;
        history = new History();
        
        view.addActionListener(this.addClassListener(), 0, 0);
        view.addActionListener(this.deleteClassListener(), 0, 1);
        view.addActionListener(this.editClassNameListener(), 0, 2);
        view.addActionListener(this.addFieldListener(), 1, 0);
        view.addActionListener(this.deleteFieldListener(), 1, 1);
        view.addActionListener(this.editFieldNameListener(), 1, 2);
        view.addActionListener(this.addMethodListener(), 2, 0);
        view.addActionListener(this.deleteMethodListener(), 2, 1);
        view.addActionListener(this.editMethodNameListener(), 2, 2);
        view.addActionListener(this.editMethodParametersListener(), 2, 3);
        view.addActionListener(this.addRelationshipListener(), 3, 0);
        view.addActionListener(this.deleteRelationshipListener(), 3, 1);
        view.addActionListener(this.editRelationshipDestinationListener(), 3, 2);
        view.addActionListener(this.editRelationshipTypeListener(), 3, 3);
        view.addActionListener(this.saveJSONListener(), 4, 0);
        view.addActionListener(this.loadJSONListener(), 4, 1);
        view.addActionListener(this.undoListener(), 5, 0);
        view.addActionListener(this.redoListener(), 5, 1);
    }
    
    private String executeCommand(Command command) {
		Classes deepCopy = (Classes) org.apache.commons.lang.SerializationUtils.clone(model);
		model.setBackup(deepCopy.getClasses());
		String response = command.execute();
		if (command.getStateChange()) {
			history.push(command, new Memento(model));
		}
		return response;
	}
    
    private String undo() {
		String response = "You can no longer undo.";
		if (!history.isEmpty()) {
			history.undo();
			response = "The last command that changed the state has been undone.";
		}
		return response;
	}
    //////////////////////////// Class Action Listeners////////////////////////////////////// 

    /** When the add class button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener addClassListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newClassName = promptInput("Enter the new class name.");
                if (newClassName != null) {
                    String response = executeCommand(new AddClassCommand(model, newClassName));
                    JOptionPane.showMessageDialog(view.frame, response);
                    refreshJFrame();
                }
            }
        };
    }
    
    /** When the delete class button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener deleteClassListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deletedClass = promptInput("Enter the class name to be deleted.");
                if (deletedClass != null) {
                	String response = executeCommand(new DeleteClassCommand(model, deletedClass));
                    JOptionPane.showMessageDialog(view.frame, response);
                    refreshJFrame();
                }
            }
        };
    }
    
    /** When the rename class button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener editClassNameListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptInput("Enter the class you want to rename.");
                if (className != null) {
                    String newClassName = promptInput("Enter the new name of the class.");
                    if (newClassName != null) {
                    	String response = executeCommand(new EditClassNameCommand(model, className, newClassName));
                        JOptionPane.showMessageDialog(view.frame, response);
                        refreshJFrame();
                    }
                }
            }
        };
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////

    
    //////////////////////////// Field Action Listeners/////////////////////////////////////////////// 

    /** When the add field button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener addFieldListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptInput("Enter the class name where the field will be added.");
                if (className != null) {
                    String fieldName = promptInput("Enter new field name.");
                    if (fieldName != null) {
                        String response = executeCommand(new AddFieldCommand(model, className, fieldName));
                        JOptionPane.showMessageDialog(view.frame, response);
                        refreshJFrame();
                    }
                }
            }
        };
    }
    
    /** When the delete field button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener deleteFieldListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptInput("Enter the class name where the field will be deleted.");
                if (className != null) {
                    String fieldName = promptInput("Enter the field name to be deleted.");
                    if (fieldName != null) {
                        String response = executeCommand(new DeleteFieldCommand(model, className, fieldName));
                        JOptionPane.showMessageDialog(view.frame, response);
                        refreshJFrame();
                    }
                }
            }
        };
    }
    
    /** When the rename field button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener editFieldNameListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptInput("Enter the class name where the field will be added renamed.");
                if (className != null) {
                    String fieldName = promptInput("Enter the field you want to rename.");
                    if (fieldName != null) {
                        String newFieldName = promptInput("Enter the new name of the field.");
                        if (newFieldName != null) {
                            String response = executeCommand(new EditFieldNameCommand(model, className, fieldName, newFieldName));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                    }
                }
            }
        };
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    //////////////////////////// Method ActionListeners////////////////////////////////////

    /** When the add method button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener addMethodListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptInput("Enter the class name where the method will be added.");
                if (className != null) {
                    String methodName = promptInput("Enter new method name.");
                    if (methodName != null) {
                        List<String> parameters = promptMultipleInput("Enter a parameter.");
                        if (parameters != null) {
                            String response = executeCommand(new AddMethodCommand(model, className, methodName, parameters));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                    }
                }
            }
        };
    }
    
    /** When the delete method button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener deleteMethodListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptInput("Enter the class name where the method will be deleted.");
                if (className != null) {
                    String methodName = promptInput("Enter method name to delete.");
                    if (methodName != null) {
                        List<String> parameters = promptMultipleInput("Enter a parameter.");
                        if (parameters != null) {
                            String response = executeCommand(new DeleteMethodCommand(model, className, methodName, parameters));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                    }
                }
            }
        };
    }
    
    /** When the edit method name button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener editMethodNameListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptInput("Enter the class name where the methods name will be changed.");
                if (className != null) {
                    String methodName = promptInput("Enter old method name.");
                    if (methodName != null) {
                    	List<String> parameters = promptMultipleInput("Enter a parameter.");
                        if (parameters != null) {
                        	String newMethodName = promptInput("Enter new method name.");
                            if (newMethodName != null) {
                                String response = executeCommand(new EditMethodNameCommand(model, className, methodName, parameters, newMethodName));
                                JOptionPane.showMessageDialog(view.frame, response);
                                refreshJFrame();
                            }
                        }
                    }
                }
            }
        };
    }
    
    /** When the edit method parameters button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener editMethodParametersListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptInput("Enter the class name where the methods parameters will be changed.");
                if (className != null) {
                    String methodName = promptInput("Enter method name.");
                    if (methodName != null) {
                        List<String> parameters = promptMultipleInput("Enter a parameter.");
                        if (parameters != null) {
                            List<String> newParameters = promptMultipleInput("Enter the new parameter.");
                            if (newParameters != null) {
                                String response = executeCommand(new EditMethodParametersCommand(model, className, methodName, parameters, newParameters));
                                JOptionPane.showMessageDialog(view.frame, response);
                                refreshJFrame();
                            }
                        }
                    }
                }
            }
        };
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////

    
    //////////////////////////// Relationship ActionListeners////////////////////////////////////

    /** When the add relationship button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener addRelationshipListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptInput("Enter the class name where the relationship will be added.");
                if (className != null) {
                    String relationshipName = promptInput("Enter new relationship destination.");
                    if (relationshipName != null) {
                        String relationshipType = promptInput("Enter new relationship type.");
                        if (relationshipType != null) {
                            String response = executeCommand(new AddRelationshipCommand(model, className, relationshipName, relationshipType));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                    }
                }
            }
        };
    }

    /** When the delete relationship button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener deleteRelationshipListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptInput("Enter the class name where the relationship will be deleted.");
                if (className != null) {
                    String relationshipName = promptInput("Enter relationship destination.");
                    if (relationshipName != null) {
                    	String response = executeCommand(new DeleteRelationshipCommand(model, className, relationshipName));
                        JOptionPane.showMessageDialog(view.frame, response);
                    	refreshJFrame();
                    }
                }
            }
        };
    }
    
    /** When the edit relationship destination button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener editRelationshipDestinationListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptInput("Enter the class name where the relationship will have its destination changed.");
                if (className != null) {
                    String relationshipDestination = promptInput("Enter old relationship destination.");
                    if (relationshipDestination != null) {
                        String newRelationshipDestination = promptInput("Enter new relationship destination.");
                        if (newRelationshipDestination != null) {
                        	String response = executeCommand(new EditRelationshipDestinationCommand(model, className, relationshipDestination, newRelationshipDestination));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                    }
                }
            }
        };
    }

    /** When the edit relationship type button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener editRelationshipTypeListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptInput("Enter the class name where the relationship will have its type changed.");
                if (className != null) {
                    String relationshipDestination = promptInput("Enter relationship destination.");
                    if (relationshipDestination != null) {
                        String relationshipType = promptInput("Enter new relationship type.");
                        if (relationshipType != null) {
                        	String response = executeCommand(new EditRelationshipTypeCommand(model, className, relationshipDestination, relationshipType));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                    }
                }
            }
        };
    }

    //////////////////////////// Save and load ActionListeners///////////////////////////////////////

    /** When the save button is pushed this function is called to get info from the user and save the JSON file.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener saveJSONListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = promptInput("Enter file name to save.");
                if (fileName != null) {
                	String response = executeCommand(new SaveJSONCommand(model, fileName));
                    JOptionPane.showMessageDialog(view.frame, response);
                }
            }
        };
    }
    /** When the load button is pushed this function is called to get info from the user and load the JSON file into the environment.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener loadJSONListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = promptInput("Enter file name to load.");
                if (fileName != null) {
                	String response = executeCommand(new LoadJSONCommand(model, fileName));
                    JOptionPane.showMessageDialog(view.frame, response);
                    refreshJFrame();
                }
            }
        };
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////// Save and load ActionListeners///////////////////////////////////////

    /** When the save button is pushed this function is called to get info from the user and save the JSON file.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener undoListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String response = undo();
                JOptionPane.showMessageDialog(view.frame, response);
                refreshJFrame();
            }
        };
    }
    /** When the load button is pushed this function is called to get info from the user and load the JSON file into the environment.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener redoListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        };
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    

    /** Gets single input from the user.
     * @param message is the question the user will be prompted with to input data.
     * @return The string the user inputed. 
	 */
    public String promptInput(String message) {
        return JOptionPane.showInputDialog(view.frame, message);
    }
    /** Gets multiple input from the user.
     * @param message is the question the user will be prompted with to input data.
     * @return The list string the user inputed. 
	 */
    public List<String> promptMultipleInput(String message) {
        List<String> parameters = new ArrayList<String>();
        boolean bool = true;
        while (bool) {
            String parameter = JOptionPane.showInputDialog(view.frame, message);
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
    /** Removes all the elements from the frame and adds them back with updated data. This allows the frame to refresh so the most updated content is shown. 
	 */
    public void refreshJFrame() {
    	view.removeAll();
    	view.revalidate();
    	view.repaint();
        for (SortedMap.Entry<String, Class> entry : model.getClasses().entrySet()) {
            JPanel temp = new JPanel();
            JLabel lbl = new JLabel(entry.getValue().toString());
            temp.add(lbl);
            view.add(lbl);
            view.frame.pack();
        }
    }
    
}