package team.indecision.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.util.SortedMap;
import java.util.SortedSet;
import java.awt.event.MouseListener;


import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import team.indecision.View.GUI;
import team.indecision.Command.*;
import team.indecision.Memento.History;
import team.indecision.Memento.Memento;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;
import team.indecision.Model.Method;
import team.indecision.Model.Relationship;
import team.indecision.Model.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIController extends JPanel implements  MouseListener, MouseMotionListener{
    private Classes model;
    private GUI view;
    private History history;
    private int lastX;
    private int lastY;

    private HashMap<String, JPanel> customJPanels = new HashMap<String, JPanel>();

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
            history.pushUndo(command, new Memento(model));
        }
        return response;
    }

    private String undo() {
        String response = "You can no longer undo.";
        if (!history.isEmptyUndo()) {
            history.undo();
            response = "The last command that changed the state has been undone.";
        }
        return response;
    }

    private String redo() {
        String response = "You can no longer redo.";
        if (!history.isEmptyRedo()) {
            Command c = history.redo();
            response = executeCommand(c);
        }
        return response;
    }

    //////////////////////////// Class Action
    //////////////////////////// Listeners//////////////////////////////////////

    /**
     * When the add class button is pushed this function is called to get info from
     * the user.
     * 
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

    /**
     * When the delete class button is pushed this function is called to get info
     * from the user.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener deleteClassListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deletedClass = promptClassDropDown("Enter the class name to be deleted.");
                if (deletedClass != null) {
                    String response = executeCommand(new DeleteClassCommand(model, deletedClass));
                    JOptionPane.showMessageDialog(view.frame, response);
                    refreshJFrame();
                }
            }
        };
    }

    /**
     * When the rename class button is pushed this function is called to get info
     * from the user.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener renameClassListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String original = promptClassDropDown("Enter the class you want to rename.");
                if (original != null) {
                    String newClassName = promptInput("Enter the new name of the class.");
                    if (newClassName != null) {
                        String response = executeCommand(new EditClassNameCommand(model, original, newClassName));
                        JOptionPane.showMessageDialog(view.frame, response);
                        refreshJFrame();
                    }
                }
            }
        };
    }

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

    //////////////////////////// Field Action
    //////////////////////////// Listeners///////////////////////////////////////////////

    /**
     * When the add field button is pushed this function is called to get info from
     * the user.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener addFieldListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Enter the class name where the field will be added.");
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

    /**
     * When the delete field button is pushed this function is called to get info
     * from the user.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener deleteFieldListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Enter the class name where the field will be deleted.");
                if (className != null) {
                    Field deletedField = promptFieldDropDown(className, "Enter the field name to be deleted.");
                    if (deletedField != null) {
                        String response = executeCommand(
                                new DeleteFieldCommand(model, className, deletedField.getName()));
                        JOptionPane.showMessageDialog(view.frame, response);
                        refreshJFrame();
                    }
                }
            }
        };
    }

    /**
     * When the rename field button is pushed this function is called to get info
     * from the user.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener editFieldNameListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Enter the class name where the field will be added renamed.");
                if (className != null) {
                    Field original = promptFieldDropDown(className, "Enter the field you want to rename.");
                    if (original != null) {
                        String newName = promptInput("Enter the new name of the field.");
                        if (newName != null) {
                            String response = executeCommand(
                                    new EditFieldNameCommand(model, className, original.getName(), newName));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                    }
                }
            }
        };
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////// Method
    //////////////////////////// ActionListeners////////////////////////////////////

    /**
     * When the add method button is pushed this function is called to get info from
     * the user.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener addMethodListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Enter the class name where the method will be added.");
                if (className != null) {
                    String methodName = promptInput("Enter new method name.");
                    if (methodName != null) {
                        List<String> parameters = promptMultipleInput("Enter new method parameters.");
                        if (parameters != null) {
                            String response = executeCommand(
                                    new AddMethodCommand(model, className, methodName, parameters));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                    }
                }
            }
        };
    }

    /**
     * When the delete method button is pushed this function is called to get info
     * from the user.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener deleteMethodListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Enter the class name where the method will be deleted.");
                if (className != null) {
                    Method methodAndParams = prompMethodDropDown(className, "Choose method to delete");
                    if (methodAndParams != null) {
                        String response = executeCommand(new DeleteMethodCommand(model, className,
                                methodAndParams.getName(), methodAndParams.getParameters()));
                        JOptionPane.showMessageDialog(view.frame, response);
                        refreshJFrame();
                    }
                }
            }
        };
    }

    /**
     * When the edit method name button is pushed this function is called to get
     * info from the user.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener editMethodNameListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Enter the class name where the methods name will be changed.");
                if (className != null) {
                    Method methodToChange = prompMethodDropDown(className,
                            "Choose the method you want to change the name of.");
                    if (methodToChange != null) {
                        String methodNewName = promptInput("Enter new method name.");
                        if (methodNewName != null) {
                            String response = executeCommand(new EditMethodNameCommand(model, className,
                                    methodToChange.getName(), methodToChange.getParameters(), methodNewName));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                    }
                }
            }
        };
    }

    /**
     * When the edit method parameters button is pushed this function is called to
     * get info from the user.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener editMethodParametersListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown(
                        "Enter the class name where the methods parameters will be changed.");
                if (className != null) {
                    Method methodToChange = prompMethodDropDown(className,
                            "Select the method you want to change parametes of.");
                    if (methodToChange != null) {
                        List<String> newParameters = promptMultipleInput("Enter new method parameters.");
                        if (newParameters != null) {
                            String response = executeCommand(new EditMethodParametersCommand(model, className,
                                    methodToChange.getName(), methodToChange.getParameters(), newParameters));
                            JOptionPane.showMessageDialog(view.frame, response);
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

    /**
     * When the add relationship button is pushed this function is called to get
     * info from the user.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener addRelationshipListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Enter the class name where the relationship will be added.");
                if (className != null) {
                    String relationshipName = promptClassDropDown("Enter relationship destination.");
                    if (relationshipName != null) {
                        String relationshipType = promptRelationshipTypeDropDown("Enter new relationship type.");
                        if (relationshipType != null) {
                            String response = executeCommand(
                                    new AddRelationshipCommand(model, className, relationshipName, relationshipType));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                    }
                }
            }
        };
    }

    /**
     * When the delete relationship button is pushed this function is called to get
     * info from the user.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener deleteRelationshipListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Enter the class name where the relationship will be deleted.");
                if (className != null) {
                    Relationship relationship = promptRelationshipDropDown(className,
                            "Choose the relationship to be deleted.");
                    if (relationship != null) {
                        String response = executeCommand(
                                new DeleteRelationshipCommand(model, className, relationship.getDestination()));
                        JOptionPane.showMessageDialog(view.frame, response);
                        refreshJFrame();
                    }

                }
            }
        };
    }

    /**
     * When the edit relationship destination button is pushed this function is
     * called to get info from the user.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener editRelationshipDestinationListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptInput(
                        "Enter the class name where the relationship will have its destination changed.");
                if (className != null) {
                    String relationshipDestination = promptInput("Enter old relationship destination.");
                    if (relationshipDestination != null) {
                        String newRelationshipDestination = promptInput("Enter new relationship destination.");
                        if (newRelationshipDestination != null) {
                            String response = executeCommand(new EditRelationshipDestinationCommand(model, className,
                                    relationshipDestination, newRelationshipDestination));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                    }
                }
            }
        };
    }

    /**
     * When the edit relationship type button is pushed this function is called to
     * get info from the user.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener editRelationshipTypeListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown(
                        "Enter the class name where the relationship will have its type changed.");
                if (className != null) {
                    Relationship relationship = promptRelationshipDropDown(className,
                            "Choose the relationship you want to change.");
                    if (relationship != null) {
                        String relationshipNewType = promptRelationshipTypeDropDown("Enter new relationship type.");
                        if (relationshipNewType != null) {
                            String response = executeCommand(new EditRelationshipTypeCommand(model, className,
                                    relationship.getDestination(), relationshipNewType));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                    }
                }
            }
        };
    }

    //////////////////////////// Save and load
    //////////////////////////// ActionListeners///////////////////////////////////////

    /**
     * When the save button is pushed this function is called to get info from the
     * user and save the JSON file.
     * 
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

    /**
     * When the load button is pushed this function is called to get info from the
     * user and load the JSON file into the environment.
     * 
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

    //////////////////////////// Undo and Redo
    //////////////////////////// ActionListeners///////////////////////////////////////

    /**
     * When the save button is pushed this function is called to get info from the
     * user and save the JSON file.
     * 
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

    /**
     * When the load button is pushed this function is called to get info from the
     * user and load the JSON file into the environment.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener redoListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = redo();
                JOptionPane.showMessageDialog(view.frame, response);
                refreshJFrame();
            }
        };
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Gets single input from the user.
     * 
     * @param message is the question the user will be prompted with to input data.
     * @return The string the user inputed.
     */
    public String promptInput(String message) {
        return JOptionPane.showInputDialog(view.frame, message);
    }

    /**
     * Gets multiple input from the user.
     * 
     * @param message is the question the user will be prompted with to input data.
     * @return The list string the user input.
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

    /**
     * Gets multiple input from the user.
     * 
     * @param message is the question the user will be prompted with to input data.
     * @return The class name the user input.
     */
    public String promptClassDropDown(String message) {
        if (model.getClasses().isEmpty()) {
            JOptionPane.showMessageDialog(view.frame, "There are no classes added.");
            return null;
        } else {
            List<String> optionsList = new ArrayList<String>();
            for (SortedMap.Entry<String, Class> entry : model.getClasses().entrySet()) {
                optionsList.add(entry.getValue().getName());
            }
            Object[] classesArray = optionsList.toArray();
            Object classChosen = JOptionPane.showInputDialog(view.frame, message, message, JOptionPane.QUESTION_MESSAGE,
                    null, classesArray, classesArray[0]);
            if (classChosen != null) {
                return classChosen.toString();
            } else {
                return null;
            }
        }
    }

    /**
     * Gets multiple input from the user.
     * 
     * @param className is the class the field is in.
     * @param message   is the question the user will be prompted with to input
     *                  data.
     * @return a field object with the updated information.
     */
    public Field promptFieldDropDown(String className, String message) {
        if (model.getClasses().get(className).getFields().isEmpty()) {
            JOptionPane.showMessageDialog(view.frame, "There are no fields added.");
            return null;
        } else {
            SortedSet<Field> fields = model.getClasses().get(className).getFields();
            Object[] fieldsArray = fields.toArray();
            Object field = JOptionPane.showInputDialog(view.frame, message, message, JOptionPane.QUESTION_MESSAGE, null,
                    fieldsArray, fieldsArray[0]);
            if (field != null) {
                return (Field) field;
            } else {
                return null;
            }
        }
    }

    /**
     * Gets the relationship type the user wants for the relationship.
     * 
     * @param message is the question the user will be prompted with to input data.
     * @return The class name the user input.
     */
    public String promptRelationshipTypeDropDown(String message) {
        String[] optionsArray = { "Aggregation", "Composition", "Inheritance", "Realization" };
        return (String) JOptionPane.showInputDialog(view.frame, message, message, JOptionPane.QUESTION_MESSAGE, null,
                optionsArray, optionsArray[0]);
    }

    /**
     * Gets the relationshp the user wants to change.
     * 
     * @param className is the class the relationship is in.
     * @param message   is the question the user will be prompted with to input
     *                  data.
     * @return a relationship object with the updated information.
     */
    public Relationship promptRelationshipDropDown(String className, String message) {
        if (model.getClasses().get(className).getRelationships().isEmpty()) {
            JOptionPane.showMessageDialog(view.frame, "There are no relationships added.");
            return null;
        } else {
            SortedSet<Relationship> relationships = model.getClasses().get(className).getRelationships();
            Object[] relationshipArray = relationships.toArray();
            Object relationship = JOptionPane.showInputDialog(view.frame, message, message,
                    JOptionPane.QUESTION_MESSAGE, null, relationshipArray, relationshipArray[0]);
            if (relationship != null) {
                return (Relationship) relationship;
            } else {
                return null;
            }
        }
    }

    /**
     * Gets the method the user wants to change.
     * 
     * @param className is the class the method is in.
     * @param message   is the question the user will be prompted with to input
     *                  data.
     * @return a method object with the updated information.
     */
    public Method prompMethodDropDown(String className, String message) {
        if (model.getClasses().get(className).getMethods().isEmpty()) {
            JOptionPane.showMessageDialog(view.frame, "There are no methods added.");
            return null;
        } else {
            SortedSet<Method> methods = model.getClasses().get(className).getMethods();
            Object[] methodsArray = methods.toArray();
            Object method = JOptionPane.showInputDialog(view.frame, message, message, JOptionPane.QUESTION_MESSAGE,
                    null, methodsArray, methodsArray[0]);
            
            if (method != null) {
                return (Method) method;
            } else {
                return null;
            }
            
        }
    }

    /**
     * Removes all the elements from the frame and adds them back with updated data.
     * This allows the frame to refresh so the most updated content is shown.
     */
    public void refreshJFrame1() {
        view.removeAll();
        view.revalidate();
        view.repaint();
        for (SortedMap.Entry<String, Class> entry : model.getClasses().entrySet()) {
            Border emptyborder = BorderFactory.createEmptyBorder(10,10,10,10);
            JPanel temp = new JPanel();
            temp.setLayout(null);
            JLabel lbl = new JLabel(entry.getValue().toStringGUI());
            lbl.setBorder(emptyborder);
            lbl.setBackground(Color.LIGHT_GRAY);
            lbl.setOpaque(true);
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            temp.add(lbl);
            view.add(lbl);
            view.frame.pack();
        }
    }
    public void refreshJFrame() {

        // keep track of these panels 


        for (SortedMap.Entry<String, Class> entry : model.getClasses().entrySet()) {
            Border emptyborder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

            JPanel panel = new JPanel();
            panel.setSize(new Dimension(100, 100));
            JLabel label = new JLabel();

            label.setText(entry.getValue().toStringGUI());
            label.setBorder(emptyborder);
            label.setBackground(Color.LIGHT_GRAY);
            label.setOpaque(true);
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            panel.add(label);
            
            //panel.setLocation(150, 20);
            panel.addMouseListener(this);
            panel.addMouseMotionListener(this);
            customJPanels.put(entry.getValue().getName(), panel);

        }
        placeJPanles();
    }
    public void placeJPanles(){
        view.removeAll();
        view.revalidate();
        for(Map.Entry<String, JPanel> entry : customJPanels.entrySet()) {
            JPanel panel = entry.getValue();
            view.add(panel); 

        }
        view.revalidate();
        view.repaint();

    }

	@Override
	public void mouseDragged(MouseEvent e) {
		// Get the source of the mouse event
		Object source = e.getSource();

		// Check if press is a left click
		if(SwingUtilities.isLeftMouseButton(e)) {
			// Loop through GUI classes and check if it triggered the event
			for(Map.Entry<String, JPanel> entry : customJPanels.entrySet()) {
				JPanel panel = entry.getValue();
				if(source == panel) {
					// Set location of GUIClass
					int newX = e.getLocationOnScreen().x - lastX;
					int newY = e.getLocationOnScreen().y - lastY;
					panel.setLocation(newX, newY);
					
					lastX = e.getLocationOnScreen().x - panel.getX();
					lastY = e.getLocationOnScreen().y - panel.getY();
	
					// If the user is dragging a class then repaint in case the class has a relationship
                    view.frame.revalidate();
                    view.frame.repaint();				
					break;
				}
			}
		}
	}

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
        if(SwingUtilities.isLeftMouseButton(e)) {
			for(Map.Entry<String, JPanel> entry : customJPanels.entrySet()) {
				JPanel panel = entry.getValue(); 
				
				if(source == panel) {
					lastX = e.getLocationOnScreen().x - panel.getX();
					lastY = e.getLocationOnScreen().y - panel.getY();	
    			}
			}
		}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


}