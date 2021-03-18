package team.indecision.Controller;
import java.util.SortedSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import team.indecision.View.GUI;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;
import team.indecision.Model.Field;
import team.indecision.Model.Method;
import team.indecision.Model.Relationship;
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

    //////////////////////////// Class ActionListeners//////////////////////////////////////

    /** When the add class button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener addClassListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newClassName = promptInput("Enter the new class name.");
                if (newClassName != null) {
                    model.addClassGUI(gui.frame, newClassName);
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
                String deletedClass = promptClassDropDown("Enter the class name to be deleted.");
                if (deletedClass != null) {
                    model.deleteClassGUI(gui.frame, deletedClass);
                    refreshJFrame();
                }
            }
        };
    }

    /** When the rename class button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener renameClassListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String original = promptClassDropDown("Enter the class you want to rename.");
                if (original != null) {
                    String newName = promptInput("Enter the new name of the class.");
                    if (newName != null) {
                        model.renameClassGUI(gui.frame, original, newName);
                        refreshJFrame();
                    }
                }
            }
        };
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////// Field ActionListeners///////////////////////////////////////////////

    /** When the add field button is pushed this function is called to get info from the user.
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
                        model.addFieldGUI(gui.frame, className, fieldName);
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
                String className = promptClassDropDown("Enter the class name where the field will be deleted.");
                if (className != null) {
                    Field deletedField = promptFieldDropDown(className, "Enter the field name to be deleted.");
                    if (deletedField != null) {
                        model.deleteFieldGUI(gui.frame, className, deletedField.getName());
                        refreshJFrame();
                    }
                }
            }
        };
    }

    /** When the rename field button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener renameFieldListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Enter the class name where the field will be added renamed.");
                if (className != null) {
                    Field original = promptFieldDropDown(className, "Enter the field you want to rename.");
                    if (original != null) {
                        String newName = promptInput("Enter the new name of the field.");
                        if (newName != null) {
                            model.editFieldGUI(gui.frame, className, original.getName(), newName);
                            refreshJFrame();
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
                String className = promptClassDropDown("Enter the class name where the relationship will be added.");
                if (className != null) {
                    String relationshipName = promptClassDropDown("Enter relationship destination.");
                    if (relationshipName != null) {
                        String relationshipType = promptRelationshipTypeDropDown("Enter new relationship type.");
                        if (relationshipType != null) {
                            model.addRelationshipGUI(gui.frame, className, relationshipName, relationshipType);
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
                String className = promptClassDropDown("Enter the class name where the relationship will be deleted.");
                if (className != null) {
                    Relationship relationshipName = promptRelationshipDropDown(className,
                            "Choose the relationship to be deleted.");
                    if (relationshipName != null) {
                        model.deleteRelationshipGUI(gui.frame, className, relationshipName.getDestination());
                        refreshJFrame();
                    }
                }
            }
        };
    }

    /** When the edit relationship destination button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener editRelationshipDestination() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown(
                        "Enter the class name where the relationship will have its destination changed.");
                if (className != null) {
                    Relationship oldRelationship = promptRelationshipDropDown(className,
                            "Choose the relationship you want to change.");
                    if (oldRelationship != null) {
                        String newRelationshipDestination = promptClassDropDown("Enter new relationship destination.");
                        if (newRelationshipDestination != null) {
                            model.editRelationshipDestinationGUI(gui.frame, className, oldRelationship.getDestination(),
                                    newRelationshipDestination);
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
    public ActionListener editRelationshipType() {
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
                            model.editRelationshipTypeGUI(gui.frame, className, relationship.getDestination(),
                                    relationshipNewType);
                            refreshJFrame();
                        }
                    }
                }
            }
        };
    }

    //////////////////////////////////////Method ActionListeners////////////////////////////////////////////

    /** When the add method button is pushed this function is called to get info from the user.
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
                            model.addMethodGUI(gui.frame, className, methodName, parameters);
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
                String className = promptClassDropDown("Enter the class name where the method will be deleted.");
                if (className != null) {
                    Method methodAndParams = prompMethodDropDown(className, "Choose method to delete");
                    if (methodAndParams != null) {
                        model.deleteMethodGUI(gui.frame, className, methodAndParams.getName(),
                                methodAndParams.getParameters());
                        refreshJFrame();
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
                String className = promptClassDropDown("Enter the class name where the methods name will be changed.");
                if (className != null) {
                    Method methodToChange = prompMethodDropDown(className,
                            "Choose the method you want to change the name of.");
                    if (methodToChange != null) {
                        String methodNewName = promptInput("Enter new method name.");
                        if (methodNewName != null) {
                            model.editMethodNameGUI(gui.frame, className, methodToChange.getName(),
                                    methodToChange.getParameters(), methodNewName);
                            refreshJFrame();
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
                String className = promptClassDropDown(
                        "Enter the class name where the methods parameters will be changed.");
                if (className != null) {
                    Method methodToChange = prompMethodDropDown(className,
                            "Select the method you want to change parametes of.");
                    if (methodToChange != null) {
                        List<String> newParameters = promptMultipleInput("Enter new method parameters.");
                        if (newParameters != null) {
                            model.editMethodParametersGUI(gui.frame, className, methodToChange.getName(),
                                    methodToChange.getParameters(), newParameters);
                            refreshJFrame();
                            refreshJFrame();
                        }
                    }
                }
            }
        };
    }

    //////////////////////////// Save and load ActionListeners///////////////////////////////////////

    /**
     * When the save button is pushed this function is called to get info from the
     * user and save the JSON file.
     * 
     * @return An ActionListener is sent back to the GUI so the data is passed back.
     */
    public ActionListener saveActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = promptInput("Enter file name to save.");
                if (fileName != null) {
                    model.saveJSON(fileName);
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
    public ActionListener loadActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = promptInput("Enter file name to load.");
                if (fileName != null) {
                    model.loadJSON(fileName);
                    refreshJFrame();
                }
            }
        };
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// Getting Info From user///////////////////////////////////

    /** Gets single input from the user.
     * @param message is the question the user will be prompted with to input data.
     * @return The string the user input. 
	 */
    public String promptInput(String message) {
        return JOptionPane.showInputDialog(gui.frame, message);
    }

    /** Gets multiple input from the user.
     * @param message is the question the user will be prompted with to input data.
     * @return The list string the user input. 
	 */
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
    /** Gets multiple input from the user.
     * @param message is the question the user will be prompted with to input data.
     * @return The class name the user input. 
	 */
    public String promptClassDropDown(String message) {
        if (model.getClasses().isEmpty()) {
            JOptionPane.showMessageDialog(gui.frame, "There are no classes added.");
            return null;
        } else {
            List<String> optionsList = new ArrayList<String>();
            for (SortedMap.Entry<String, Class> entry : model.getClasses().entrySet()) {
                optionsList.add(entry.getValue().getName());
            }
            Object[] classesArray = optionsList.toArray();
            Object classChosen = JOptionPane.showInputDialog(gui.frame, message, message, JOptionPane.QUESTION_MESSAGE,
                    null, classesArray, classesArray[0]);
            return classChosen.toString();
        }
    }

    /** Gets multiple input from the user.
     * @param className is the class the field is in.
     * @param message is the question the user will be prompted with to input data.
     * @return a field object with the updated information. 
	 */
    public Field promptFieldDropDown(String className, String message) {
        if (model.getClasses().get(className).getFields().isEmpty()) {
            JOptionPane.showMessageDialog(gui.frame, "There are no fields added.");
            return null;
        } else {
            SortedSet<Field> fields = model.getClasses().get(className).getFields();
            Object[] fieldsArray = fields.toArray();
            Object field = JOptionPane.showInputDialog(gui.frame, message, message, JOptionPane.QUESTION_MESSAGE, null,
                    fieldsArray, fieldsArray[0]);
            return (Field) field;
        }
    }

    /** Gets the relationship type the user wants for the relationship.
     * @param message is the question the user will be prompted with to input data.
     * @return The class name the user input. 
	 */
    public String promptRelationshipTypeDropDown(String message) {
        String[] optionsArray = { "Aggregation", "Composition", "Inheritance", "Realization" };
        return (String) JOptionPane.showInputDialog(gui.frame, message, message, JOptionPane.QUESTION_MESSAGE, null,
                optionsArray, optionsArray[0]);
    }

    /** Gets the relationshp the user wants to change.
     * @param className is the class the relationship is in.
     * @param message is the question the user will be prompted with to input data.
     * @return a relationship object with the updated information. 
	 */
    public Relationship promptRelationshipDropDown(String className, String message) {
        if (model.getClasses().get(className).getRelationships().isEmpty()) {
            JOptionPane.showMessageDialog(gui.frame, "There are no relationships added.");
            return null;
        } else {
        SortedSet<Relationship> relationships = model.getClasses().get(className).getRelationships();
        Object[] relationshipArray = relationships.toArray();
        Object relationship = JOptionPane.showInputDialog(gui.frame, message, message, JOptionPane.QUESTION_MESSAGE,
                null, relationshipArray, relationshipArray[0]);
        return (Relationship) relationship;
        }
    }

    /** Gets the method the user wants to change.
     * @param className is the class the method is in.
     * @param message is the question the user will be prompted with to input data.
     * @return a method object with the updated information. 
	 */
    public Method prompMethodDropDown(String className, String message) {
        if (model.getClasses().get(className).getMethods().isEmpty()) {
            JOptionPane.showMessageDialog(gui.frame, "There are no methods added.");
            return null;
        } else {
        SortedSet<Method> methods = model.getClasses().get(className).getMethods();
        Object[] methodsArray = methods.toArray();
        Object method = JOptionPane.showInputDialog(gui.frame, message, message, JOptionPane.QUESTION_MESSAGE, null,
                methodsArray, methodsArray[0]);
        return (Method) method;
        }
    }
    /** Removes all the elements from the frame and adds them back with updated data. This allows the frame to refresh so the most updated content is shown. 
	 */
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
