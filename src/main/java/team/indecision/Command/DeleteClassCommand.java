package team.indecision.Command;

import java.util.SortedMap;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;

/** This class represents the Delete Class command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class DeleteClassCommand implements Command {

	//Stores the model that the command will be executed against.
	Classes model;
	//Stores the desired class name of the class to be deleted from the model.
	String className;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs a Delete Class command with the desired model and class name.
	 * @param classNameP The class name.
	 * @param modelP The model.
	 */
	public DeleteClassCommand(Classes modelP, String classNameP) {
		model = modelP;
		className = classNameP;
		stateChange = false;
	}
	
	/** Executes the command. This command deletes a class from the model. The class must exist.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className))
		{	
			model.getClasses().remove(className);
			for (SortedMap.Entry<String, Class> entry : model.getClasses().entrySet()) {
		        Class c = entry.getValue();
		        if (c.containsRelationship(className)) {
		        	c.deleteRelationship(className);
		        }
		    }
			response = "The class " + className + " has been deleted.";
			stateChange = true;
		}
		else 
		{
			response = "The class " + className + " does not exist.";
		}
		return response;
	}
	
	/** Gets the stateChange field.
	 * @return A boolean that represents whether or not the state haas changed.
	 */
	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
