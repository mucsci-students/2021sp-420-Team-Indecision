package team.indecision.Command;

import java.util.SortedMap;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;

/** This class represents the List Classes command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class ListClassesCommand implements Command {
	
	//Stores the model that the command will be executed against.
	Classes model;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs a List Classes command with the desired model.
	 */
	public ListClassesCommand (Classes modelP) {
		model = modelP;
		stateChange = false;
	}
	
	/** Executes the command. This command lists all the classes in the model.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response = "";
		int i = 0;
		for (SortedMap.Entry<String, Class> entry : model.getClasses().entrySet()) {
	        Class c = entry.getValue();
	        if ((model.getClasses().size() -1) == i)
	        {
	        	response += c.toString();
	        }
	        else {
	        	response += c.toString() + System.lineSeparator();
	        }
	        i++;
	        response += System.lineSeparator();
	    }
		return response;
	}

	/** Gets the stateChange field.
	 * @return A boolean that represents whether or not the state has changed.
	 */
	@Override
	public boolean getStateChange() {
		return stateChange;
	}

}
