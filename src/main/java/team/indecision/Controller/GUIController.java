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
import java.util.TreeSet;
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
import team.indecision.Model.Parameter;
import team.indecision.Model.Relationship;
import team.indecision.Model.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
 
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/** This class represents the controller for the GUI application.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class GUIController extends JPanel implements  MouseListener, MouseMotionListener{
   
	private static final long serialVersionUID = 1L;
	//Stores the model that the controller will act on.
	private Classes model;
	//Stores the view that the controller will update.
    private GUI view;
  //Stores the history object that the controller uses to perform undo and redo.
    private History history;
    private int previousX;
    private int previousY;

    private HashMap<String, JPanel> customJPanels = new HashMap<String, JPanel>();

    /** Constructs an uninitialized controller.
	 */
    public GUIController() {

    }

    public GUIController(Classes modelP, GUI viewP) {
        model = modelP;
        view = viewP;
        history = new History();

        view.addActionListener(this.saveJSONListener(), 0, 0);
        view.addActionListener(this.loadJSONListener(), 0, 1);
        view.addActionListener(this.imageExportListener(), 0, 2);
        view.addActionListener(this.addClassListener(), 1, 0);
        view.addActionListener(this.deleteClassListener(), 1, 1);
        view.addActionListener(this.editClassNameListener(), 1, 2);
        view.addActionListener(this.addFieldListener(), 2, 0);
        view.addActionListener(this.deleteFieldListener(), 2, 1);
        view.addActionListener(this.editFieldNameListener(), 2, 2);
        view.addActionListener(this.addMethodListener(), 3, 0);
        view.addActionListener(this.deleteMethodListener(), 3, 1);
        view.addActionListener(this.editMethodNameListener(), 3, 2);
        view.addActionListener(this.editMethodParametersListener(), 3, 3);
        view.addActionListener(this.addRelationshipListener(), 4, 0);
        view.addActionListener(this.deleteRelationshipListener(), 4, 1);
        view.addActionListener(this.editRelationshipDestinationListener(), 4, 2);
        view.addActionListener(this.editRelationshipTypeListener(), 4, 3);
        view.addActionListener(this.undoListener(), 5, 0);
        view.addActionListener(this.redoListener(), 5, 1);

        view.setController(this);

    }


    public Classes getModel(){
        return model;
    }

    public HashMap<String, JPanel> getCustomJPanels () {
        return customJPanels;
    }


    /** Executes a command object. This method also creates a deep copy and saves it to the backup field of the model and pushes the Command and a Memento onto the History stack.
	 * @return A string that represents the outcome of the execution.
	 */

    private String executeCommand(Command command) {
        Classes deepCopy = (Classes) org.apache.commons.lang.SerializationUtils.clone(model);
        model.setBackup(deepCopy.getClasses());
        String response = command.execute();
        if (command.getStateChange()) {
            history.pushUndo(command, new Memento(model));
        }
        return response;
    }

    /** Undoes the most recent command if their is a command to undo by converting to the backup state in the model.
	 */
    private String undo() {
        String response = "You can no longer undo.";
        if (!history.isEmptyUndo()) {
            history.undo();
            response = "The last command that changed the state has been undone.";
        }
        return response;
    }

    /** Redoes the most recent command if their is a command to redo.
	 */
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
                else {
                    JOptionPane.showMessageDialog(view.frame,  "No class was added.", "Error",  JOptionPane.ERROR_MESSAGE);
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
                String deletedClass = promptClassDropDown("Select the class to be deleted.");
                if (deletedClass != null) {
                    String response = executeCommand(new DeleteClassCommand(model, deletedClass));
                    JOptionPane.showMessageDialog(view.frame, response);
                    refreshJFrame();
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);
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

    public ActionListener editClassNameListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptInput("Select the class you want to rename.");
                if (className != null) {
                    String newClassName = promptInput("Enter the new name of the class.");
                    if (newClassName != null) {
                        String response = executeCommand(new EditClassNameCommand(model, className, newClassName));
                        JOptionPane.showMessageDialog(view.frame, response);
                        refreshJFrame();
                    }
                    else {
                        JOptionPane.showMessageDialog(view.frame, "No new class name was selected.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);
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
                String className = promptClassDropDown("Select the class where the field will be added.");
                if (className != null) {
                	String fieldType = promptInput("Enter new field type.");
                	if (fieldType != null) {
                		String fieldName = promptInput("Enter new field name.");
                        if (fieldName != null) {
                            String response = executeCommand(new AddFieldCommand(model, className, fieldType, fieldName));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                        else {
                        	JOptionPane.showMessageDialog(view.frame, "No field name was entered.", "Error", JOptionPane.ERROR_MESSAGE); 
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(view.frame, "No field type was entered.", "Error", JOptionPane.ERROR_MESSAGE);                    }
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);                
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
                String className = promptClassDropDown("Select the class where the field will be deleted.");
                if (className != null) {
                    Field deletedField = promptFieldDropDown(className, "Select the field to be deleted.");
                    if (deletedField != null) {
                        String response = executeCommand(new DeleteFieldCommand(model, className, deletedField.getName()));
                        JOptionPane.showMessageDialog(view.frame, response);
                        refreshJFrame();
                    }
                    else {
                        JOptionPane.showMessageDialog(view.frame, "No field was selected.", "Error", JOptionPane.ERROR_MESSAGE);                    }
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);                }

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
                String className = promptClassDropDown("Select the class where the field will be added renamed.");
                if (className != null) {
                    Field original = promptFieldDropDown(className, "Select the field you want to rename.");
                    if (original != null) {
                        String newName = promptInput("Enter the new name of the field.");
                        if (newName != null) {
                            String response = executeCommand(
                                    new EditFieldNameCommand(model, className, original.getName(), newName));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                        else {
                            JOptionPane.showMessageDialog(view.frame, "No field name was entered.", "Error", JOptionPane.ERROR_MESSAGE);                        }
                    }
                    else { 
                        JOptionPane.showMessageDialog(view.frame, "No field was selected.", "Error", JOptionPane.ERROR_MESSAGE);                    }
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);                }
            }
        };
    }
    
    /**
     * When the retype field button is pushed this function is called to get info
     * from the user.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener editFieldTypeListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Select the class where the field will hav its type changed.");
                if (className != null) {
                    Field original = promptFieldDropDown(className, "Select the field you want to change the type of.");
                    if (original != null) {
                        String newType = promptInput("Enter the new type of the field.");
                        if (newType != null) {
                            String response = executeCommand(
                                    new EditFieldTypeCommand(model, className, original.getName(), newType));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                        else {
                            JOptionPane.showMessageDialog(view.frame, "No field type was entered.", "Error", JOptionPane.ERROR_MESSAGE);                        }
                    }
                    else { 
                        JOptionPane.showMessageDialog(view.frame, "No field was selected.", "Error", JOptionPane.ERROR_MESSAGE);                    }
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);                }
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
                String className = promptClassDropDown("Select the class where the method will be added.");
                if (className != null) {
                	String methodReturnType = promptInput("Enter new method return type.");
                	if (methodReturnType != null) {
                		String methodName = promptInput("Enter new method name.");
                        if (methodName != null) {
                            SortedSet<Parameter> parameters = promptMultipleInputParameters("Enter new method parameters type first then name seperated by a space and new parameters seperated with commas.");
                            if (parameters != null) {
                                String response = executeCommand(
                                        new AddMethodCommand(model,className, methodReturnType, methodName, parameters));
                                JOptionPane.showMessageDialog(view.frame, response);
                                refreshJFrame();
                            }
                            else {
                                JOptionPane.showMessageDialog(view.frame, "No parameters were entered or the parameter format was incorrect.", "Error", JOptionPane.ERROR_MESSAGE);                        }
                        }
                        else {
                            JOptionPane.showMessageDialog(view.frame, "No method was entered.", "Error", JOptionPane.ERROR_MESSAGE);                    
                            }
                	}
                	else {
                		JOptionPane.showMessageDialog(view.frame, "No return type was entered.", "Error", JOptionPane.ERROR_MESSAGE);
                	} 
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);                
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
                String className = promptClassDropDown("Select the class where the method will be deleted.");
                if (className != null) {
                    Method methodAndParams = prompMethodDropDown(className, "Choose the method to delete");
                    if (methodAndParams != null) {
                        String response = executeCommand(new DeleteMethodCommand(model, className,
                                methodAndParams.getName(), methodAndParams.getParameters()));
                        JOptionPane.showMessageDialog(view.frame, response);
                        refreshJFrame();
                    }
                    else {
                        JOptionPane.showMessageDialog(view.frame, "No method was selected.", "Error", JOptionPane.ERROR_MESSAGE);                    }
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);                }
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
                String className = promptClassDropDown("Select the class with the method you want to change.");
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
                        else {
                            JOptionPane.showMessageDialog(view.frame, "No new method name was entered.", "Error", JOptionPane.ERROR_MESSAGE);                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(view.frame, "No method was selected.", "Error", JOptionPane.ERROR_MESSAGE);                    }
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);                }
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
                        "Select the class name where the methods parameters will be changed.");
                if (className != null) {
                    Method methodToChange = prompMethodDropDown(className,
                            "Select the method you want to change parametes of.");
                    if (methodToChange != null) {
                        SortedSet<Parameter> newParameters = promptMultipleInputParameters("Enter new method parameters type first then name seperated by a space and new parameters seperated with commas.");
                        if (newParameters != null) {
                            String response = executeCommand(new EditMethodParametersCommand(model, className,
                                    methodToChange.getName(), methodToChange.getParameters(), newParameters));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                        else {
                            JOptionPane.showMessageDialog(view.frame, "No parameters were entered.", "Error", JOptionPane.ERROR_MESSAGE);                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(view.frame, "No method was selected.", "Error", JOptionPane.ERROR_MESSAGE);                    }
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);
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
    public ActionListener editMethodTypeListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Select the class with the method you want to change.");
                if (className != null) {
                    Method methodToChange = prompMethodDropDown(className,
                            "Choose the method you want to change the type of.");
                    if (methodToChange != null) {
                        String methodNewReturnType = promptInput("Enter new method return type.");
                        if (methodNewReturnType != null) {
                            String response = executeCommand(new EditMethodTypeCommand(model, className,
                                    methodToChange.getName(), methodToChange.getParameters(), methodNewReturnType));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                        else {
                            JOptionPane.showMessageDialog(view.frame, "No new method type was entered.", "Error", JOptionPane.ERROR_MESSAGE);                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(view.frame, "No method was selected.", "Error", JOptionPane.ERROR_MESSAGE);                    }
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);                }
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
                String className = promptClassDropDown("Select the class where the relationship will be added.");
                if (className != null) {
                    String relationshipName = promptClassDropDown("Select the relationship destination.");
                    if (relationshipName != null) {
                        String relationshipType = promptRelationshipTypeDropDown("Select the new relationship type.");
                        if (relationshipType != null) {
                            String response = executeCommand(
                                    new AddRelationshipCommand(model, className, relationshipName, relationshipType));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                        else {
                            JOptionPane.showMessageDialog(view.frame, "No relatioship type was selected.", "Error", JOptionPane.ERROR_MESSAGE);                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(view.frame, "No relationship destination was selected.", "Error", JOptionPane.ERROR_MESSAGE);                    }
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);                }
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
                    else {
                        JOptionPane.showMessageDialog(view.frame, "No relationship was selected.", "Error", JOptionPane.ERROR_MESSAGE);                    }
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);                }
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
                String className = promptClassDropDown(
                        "Select the class where the relationship will have its destination changed.");
                if (className != null) {
                    Relationship relationshipDestination = promptRelationshipDropDown(className, "Select relationship to change its destination.");
                    if (relationshipDestination != null) {
                        String newRelationshipDestination = promptClassDropDown("Select new relationship destination.");
                        if (newRelationshipDestination != null) {
                            String response = executeCommand(new EditRelationshipDestinationCommand(model, className,
                                    relationshipDestination.getDestination(), newRelationshipDestination));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                        else {
                            JOptionPane.showMessageDialog(view.frame, "No new relationship destination was selected.", "Error", JOptionPane.ERROR_MESSAGE);                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(view.frame, "No relationship was selected.", "Error", JOptionPane.ERROR_MESSAGE);                    }
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);                }
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
                        "Select the class where the relationship will have its type changed.");
                if (className != null) {
                    Relationship relationship = promptRelationshipDropDown(className,
                            "Select the relationship you want to change.");
                    if (relationship != null) {
                        String relationshipNewType = promptRelationshipTypeDropDown("Select new relationship type.");
                        if (relationshipNewType != null) {
                            String response = executeCommand(new EditRelationshipTypeCommand(model, className,
                                    relationship.getDestination(), relationshipNewType));
                            JOptionPane.showMessageDialog(view.frame, response);
                            refreshJFrame();
                        }
                        else {
                            JOptionPane.showMessageDialog(view.frame, "No relationship type was selected.", "Error", JOptionPane.ERROR_MESSAGE);                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(view.frame, "No relationship was selected.", "Error", JOptionPane.ERROR_MESSAGE);                    }
                }
                else {
                    JOptionPane.showMessageDialog(view.frame, "No class was selected.", "Error", JOptionPane.ERROR_MESSAGE);                }
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
             	JFileChooser fileChooser = new JFileChooser();
            	fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            	int result = fileChooser.showOpenDialog(view.frame);
            	if (result == JFileChooser.APPROVE_OPTION) {
            	    File selectedFile = fileChooser.getSelectedFile();
            	    String response = executeCommand(new SaveJSONCommand(model, selectedFile));
            	    if(response.equals("Your data has been saved to a JSON file.")) {
            	    	refreshJFrame();
            	    } 
            	    else {
            	    	JOptionPane.showMessageDialog(view.frame, response, "Error", JOptionPane.ERROR_MESSAGE);
            	    }
            	}
            	else {
            		JOptionPane.showMessageDialog(view.frame, "No selection made.", "Error", JOptionPane.ERROR_MESSAGE);
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
            	JFileChooser fileChooser = new JFileChooser();
            	fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            	int result = fileChooser.showOpenDialog(view.frame);
            	if (result == JFileChooser.APPROVE_OPTION) {
            	    File selectedFile = fileChooser.getSelectedFile();
            	    String response = executeCommand(new LoadJSONCommand(model, selectedFile));
            	    if(response.equals("Your data has been loaded from a JSON file.")) {
            	    	refreshJFrame();
            	    } 
            	    else {
            	    	JOptionPane.showMessageDialog(view.frame, response, "Error", JOptionPane.ERROR_MESSAGE);
            	    }
            	}
            	else {
            		JOptionPane.showMessageDialog(view.frame, "No selection made.", "Error", JOptionPane.ERROR_MESSAGE);
            	}
            }
        };
    }
    
    public ActionListener imageExportListener() {
    	JFrame f = view.frame;
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	BufferedImage img = getScreenShot(
                        f.getContentPane() );
                      JOptionPane.showMessageDialog(
                        null,
                        new JLabel(
                          new ImageIcon(
                            img.getScaledInstance(
                              img.getWidth(null)/2,
                              img.getHeight(null)/2,
                              Image.SCALE_SMOOTH )
                            )));
                      try {
                          // write the image as a PNG
                          ImageIO.write(
                            img,
                            "png",
                            new File("screenshot.png"));
                        } catch(Exception e1) {
                          e1.printStackTrace();
                        }
                
            }
        };
    }

    public static BufferedImage getScreenShot(
    	    Component component) {

    	    BufferedImage image = new BufferedImage(
    	      component.getWidth(),
    	      component.getHeight(),
    	      BufferedImage.TYPE_INT_RGB
    	      );
    	    // call the Component's paint method, using
    	    // the Graphics object of the image.
    	    component.paint( image.getGraphics() ); // alternately use .printAll(..)
    	    return image;
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

        
        String input = JOptionPane.showInputDialog(view.frame, message);
        if(input.equals(null)){
            return null;
        }
        else {
        input.replaceAll("\\s", "");
        String[] token = input.split(",");

        parameters.addAll(Arrays.asList(token));
        return parameters;

        }
    }
    
    /**
     * Gets multiple input from the user.
     * 
     * @param message is the question the user will be prompted with to input data.
     * @return The list string the user input.
     */
    public SortedSet<Parameter> promptMultipleInputParameters(String message) {
        SortedSet<Parameter> parameters = new TreeSet<Parameter>();
        
        String input = JOptionPane.showInputDialog(view.frame, message);
        if(input.equals(null)){
            return null;
        }
        else {
	        input.replaceAll("\\s", "");
	        String[] token = input.split(",");
	        
	        for(int i = 0; i < token.length; i++) {
				String[] token2 = token[i].split(" ");
				if (token2.length == 2) {
					parameters.add(new Parameter(token2[0], token2[1]));
				}
				else {
					return null;
				}
			}	
	 
	        return parameters;
        }        
    }

    /**
     * Gets multiple input from the user.
     * 
     * @param message is the question the user will be prompted with to input data.
     * @return The class name the user input.
     */
    public String promptClassDropDown(String message) {
        if (model.getClasses().isEmpty()) {
            //JOptionPane.showMessageDialog(view.frame, "There are no classes added.");
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

    public void refreshJFrame() {
        if(!(customJPanels.isEmpty())) {
            customJPanels.clear();
        }
        for (SortedMap.Entry<String, Class> entry : model.getClasses().entrySet()) {
            Border emptyborder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

            JPanel panel = new JPanel();
            //panel.setSize(new Dimension(100, 100));
            //panel.setBounds(200, 200, 200, 200);
            JLabel label = new JLabel();

            panel.setOpaque(true);

            label.setText(entry.getValue().toStringGUI());
            label.setBorder(emptyborder);
            label.setBackground(Color.LIGHT_GRAY);
            //panel.setBackground(Color.GREEN);
            label.setOpaque(true);
            panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            panel.setLayout(new GridBagLayout());

            panel.setName(entry.getValue().getName() + "Panel");

            label.setName(entry.getValue().getName() + "Label");

            panel.add(label);

            panel.addMouseListener(this);
            panel.addMouseMotionListener(this);
            customJPanels.put(entry.getValue().getName(), panel);

            view.frame.pack();
            //System.out.println(label.getBounds().toString());

        }
        placeJPanels();
    }
    public void placeJPanels(){
        view.removeAll();
        view.repaint();
        for(Map.Entry<String, JPanel> entry : customJPanels.entrySet()) {
            JPanel panel = entry.getValue();
            //System.out.println(panel.getAccessibleContext().toString());
            //System.out.println(panel.getComponent(0));

            panel.setBounds(model.getClasses().get(entry.getKey()).getXLocation(), model.getClasses().get(entry.getKey()).getYLocation(), 200,  200);

            view.add(panel); 
        }
        view.revalidate();
        view.repaint();


    }

	@Override
	public void mouseDragged(MouseEvent e) {
		Object source = e.getSource();

		if(SwingUtilities.isLeftMouseButton(e)) {
			for(Map.Entry<String, JPanel> entry : customJPanels.entrySet()) {
				JPanel panel = entry.getValue();
				if(source == panel) {
					int newX = e.getLocationOnScreen().x - previousX;
					int newY = e.getLocationOnScreen().y - previousY;

					panel.setLocation(newX, newY);
					
					previousX = e.getLocationOnScreen().x - panel.getX();
					previousY = e.getLocationOnScreen().y - panel.getY();
                    model.getClasses().get(entry.getKey()).setXLocation(panel.getX());
                    model.getClasses().get(entry.getKey()).setYLocation(panel.getY());

                    view.revalidate();
                    view.repaint();				
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
					previousX = e.getLocationOnScreen().x - panel.getX();
					previousY = e.getLocationOnScreen().y - panel.getY();	
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