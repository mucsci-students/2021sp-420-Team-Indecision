package team.indecision.Controller;

import java.util.SortedSet;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedMap;

import javax.swing.JComboBox;
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
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

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
                String newClassName = promptInput("Enter the new class name.");
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
                String deletedClass = promptClassDropDown("Enter the class name to be deleted.");
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
    //////////////////////////// Field Action
    //////////////////////////// Listeners///////////////////////////////////////////////
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

    public ActionListener deleteFieldListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Enter the class name where the field will be deleted.");
                if (className != null) {
                    String deletedField = promptFieldDropDown(className, "Enter the field name to be deleted.");
                    if (deletedField != null) {
                        model.deleteFieldGUI(gui.frame, className, deletedField);
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
                String className = promptClassDropDown("Enter the class name where the field will be added renamed.");
                if (className != null) {
                    String original = promptFieldDropDown(className, "Enter the field you want to rename.");
                    if (original != null) {
                        String newName = promptInput("Enter the new name of the field.");
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

    public ActionListener deleteRelationshipListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Enter the class name where the relationship will be deleted.");
                if (className != null) {
                    String relationshipName = promptClassDropDown("Enter relationship destination.");
                    if (relationshipName != null) {
                        model.deleteRelationshipGUI(gui.frame, className, relationshipName);
                        refreshJFrame();
                    }
                }
            }
        };
    }

    public ActionListener editRelationshipDestination() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown(
                        "Enter the class name where the relationship will have its destination changed.");
                if (className != null) {
                    String relationshipOldName = promptClassDropDown("Enter old relationship destination.");
                    if (relationshipOldName != null) {
                        String relationshipNewName = promptClassDropDown("Enter new relationship destination.");
                        if (relationshipNewName != null) {
                            model.editRelationshipDestinationGUI(gui.frame, className, relationshipOldName,
                                    relationshipNewName);
                            refreshJFrame();
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
                String className = promptClassDropDown(
                        "Enter the class name where the relationship will have its type changed.");
                if (className != null) {
                    String relationshipOldName = promptClassDropDown("Enter relationship destination.");
                    if (relationshipOldName != null) {
                        String relationshipNewType = promptRelationshipTypeDropDown("Enter new relationship type.");
                        if (relationshipNewType != null) {
                            model.editRelationshipTypeGUI(gui.frame, className, relationshipOldName,
                                    relationshipNewType);
                            refreshJFrame();
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

    public ActionListener deleteMethodListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Enter the class name where the method will be deleted.");
                if (className != null) {
                    Classes methodAndParams = prompMethodDropDown(className, "Enter method to delete");
                    if (methodAndParams != null) {
                        SortedSet<Method> methods = methodAndParams.getClasses().get(className).getMethods();
                        Iterator<Method> it = methods.iterator();
                        Method m = null;
                        m = it.next();
                        String methodName = m.getName();
                        List<String> parameters = m.getParameters();
                        model.deleteMethodGUI(gui.frame, className, methodName, parameters);
                        refreshJFrame();
                    }
                }
            }
        };
    }

    public ActionListener editMethodNameListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = promptClassDropDown("Enter the class name where the methods name will be changed.");
                if (className != null) {
                    Classes methodToChange = prompMethodDropDown(className,
                            "Select method you want to change the name of");
                    if (methodToChange != null) {
                        String methodNewName = promptInput("Enter new method name.");
                        if (methodNewName != null) {
                            SortedSet<Method> methods = methodToChange.getClasses().get(className).getMethods();
                            Iterator<Method> it = methods.iterator();
                            Method m = null;
                            m = it.next();
                            String methodName = m.getName();
                            List<String> parameters = m.getParameters();
                            model.editMethodNameGUI(gui.frame, className, methodName, parameters, methodNewName);
                            refreshJFrame();
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
                String className = promptClassDropDown(
                        "Enter the class name where the methods parameters will be changed.");
                if (className != null) {
                    Classes methodToChange = prompMethodDropDown(className,
                            "Select the method you want to change parametes of.");
                    if (methodToChange != null) {
                        List<String> newParameters = promptMultipleInput("Enter new method parameters.");
                        if (newParameters != null) {
                            SortedSet<Method> methods = methodToChange.getClasses().get(className).getMethods();
                            Iterator<Method> it = methods.iterator();
                            Method m = null;
                            m = it.next();
                            String methodName = m.getName();
                            List<String> parameters = m.getParameters();
                            model.editMethodParametersGUI(gui.frame, className, methodName, parameters, newParameters);
                            refreshJFrame();
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

    public String promptClassDropDown(String message) {
        List<String> optionsList = new ArrayList<String>();
        for (SortedMap.Entry<String, Class> entry : model.getClasses().entrySet()) {
            optionsList.add(entry.getValue().getName());
        }
        String[] optionsArray = new String[optionsList.size()];
        for (int i = 0; i < optionsList.size(); i++)
            optionsArray[i] = optionsList.get(i);
        return (String) JOptionPane.showInputDialog(gui.frame, message, message, JOptionPane.QUESTION_MESSAGE, null,
                optionsArray, optionsArray[0]);
    }

    public String promptFieldDropDown(String className, String message) {
        SortedSet<Field> fields = model.getClasses().get(className).getFields();

        String fieldString = fields.toString();
        String string1 = fieldString.replace("[", "");
        String string2 = string1.replace("]", "");
        String string3 = string2.replace(",", "");
        String[] fieldsArray = string3.split(" ");

        return (String) JOptionPane.showInputDialog(gui.frame, message, message, JOptionPane.QUESTION_MESSAGE, null,
                fieldsArray, fieldsArray[0]);
    }

    public String promptRelationshipTypeDropDown(String message) {
        String[] optionsArray = { "Aggregation", "Composition", "Inheritance", "Realization" };
        return (String) JOptionPane.showInputDialog(gui.frame, message, message, JOptionPane.QUESTION_MESSAGE, null,
                optionsArray, optionsArray[0]);
    }

    public Classes prompMethodDropDown(String className, String message) {



        // Something here needs to change. Bugs with methods with more than one spaces 



        SortedSet<Method> methods = model.getClasses().get(className).getMethods();

        String[] methodsArray = new String[methods.size()];

        Iterator<Method> it = methods.iterator();
        Method m = null;
        int i = 0;
        while (it.hasNext()) {
            m = it.next();

            String methodAndParam = m.getName() + " " + m.getParameters().toString();
            methodsArray[i] = methodAndParam;
            i++;
            m = null;
        }
        System.out.println();
        System.out.println();
        System.out.println(methods.toArray());
        System.out.println();
        System.out.println();


        String methodAndParamChosen = (String) JOptionPane.showInputDialog(gui.frame, message, message,
                JOptionPane.QUESTION_MESSAGE, null, methodsArray, methodsArray[0]);
        // System.out.println(methodAndParamChosen);

        String methodChosen = methodAndParamChosen.split(" ")[0];

        String parameterString = methodAndParamChosen.split("\\[")[1];
        String string2 = parameterString.replace("]", "");
        String string3 = string2.replace(" ", "");
        String str[] = string3.split(",");
        List<String> al = new ArrayList<String>();
        al = Arrays.asList(str);
        System.out.println(al.toString());

        Classes newModel = new Classes();
        newModel.addClassCLI(className);
        newModel.addMethodCLI(className, methodChosen, al);

        return newModel;
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
