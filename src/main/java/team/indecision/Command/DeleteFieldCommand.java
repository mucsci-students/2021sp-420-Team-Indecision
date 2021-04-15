package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

/** This class represents the Delete Field command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class DeleteFieldCommand implements Command {

	//Stores the model that the command will be executed against.
	Classes model;
	//Stores the desired class name of the class to be deleted from the model.
	String className;
	//Stores the desired field name of the field to be deleted from the class.
	String fieldName;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs a Delete Field command with the desired model, class name and field name.
	 * @param classNameP The class name.
	 * @param fieldNameP The field name.
	 * @param modelP The model.
	 */
	public DeleteFieldCommand(Classes modelP, String classNameP, String fieldNameP) {
		model = modelP;
		className = classNameP;
		fieldName = fieldNameP;
		stateChange = false;
	}
	
	/** Executes the command. This command deletes a field from the model. The class and field must exist.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if (c.containsField(fieldName)) {
				c.deleteField(fieldName);
				response = "The field " +  fieldName + " has been deleted from class " + className + ".";
				stateChange = true;
			}
			else {
				response = "The field " +  fieldName + " does not exist with the class " + className + ".";
			}
		}
		else {
			response = "The class " +  className + " does not exist.";
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
