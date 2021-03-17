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
    private GUI view;
    
    public GUIController(Classes modelP, GUI viewP) {
        model = modelP;
        view = viewP;
    }
    //////////////////////////// Class Action Listeners////////////////////////////////////// 

    /** When the add class button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener addClassListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Will re-implement when we do the Command design method.
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
            	// Will re-implement when we do the Command design method.
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
            	// Will re-implement when we do the Command design method.
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
            	// Will re-implement when we do the Command design method.
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
            	// Will re-implement when we do the Command design method.
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
            	// Will re-implement when we do the Command design method.
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
            	// Will re-implement when we do the Command design method.
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
            	// Will re-implement when we do the Command design method.
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
            	// Will re-implement when we do the Command design method.
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
            	// Will re-implement when we do the Command design method.
            }
        };
    }
    //////////////////////////// Method ActionListeners////////////////////////////////////

    /** When the add method button is pushed this function is called to get info from the user.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener addMethodListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Will re-implement when we do the Command design method.
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
            	// Will re-implement when we do the Command design method.
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
                	// Will re-implement when we do the Command design method.
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
                	// Will re-implement when we do the Command design method.
                }
            }
        };
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////// Save and load ActionListeners///////////////////////////////////////

    /** When the save button is pushed this function is called to get info from the user and save the JSON file.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener saveActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = promptInput("Enter file name to save.");
                if (fileName != null) {
                	// Will re-implement when we do the Command design method.
                }
            }
        };
    }
    /** When the load button is pushed this function is called to get info from the user and load the JSON file into the environment.
     * @return An ActionListener is sent back to the GUI so the data is passed back.
	 */
    public ActionListener loadActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Will re-implement when we do the Command design method.
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