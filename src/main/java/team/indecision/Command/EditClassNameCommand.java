package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

/** This class represents the Edit Class Name command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class EditClassNameCommand implements Command{

	//Stores the model that the command will be executed against.
	Classes model;
	//Stores the desired class name of the class to be edited.
	String className;
	//Stores the desired new class name.
	String newClassName;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs an Edit Class Name command with the desired model, class name, and new class name.
	 * @param classNameP The class name.
	 * @param newClassNameP The new class name.
	 * @param modelP The model.
	 */
	public EditClassNameCommand(Classes modelP, String classNameP, String newClassNameP) {
		model = modelP;
		className = classNameP;
		newClassName = newClassNameP;
		stateChange = false;
	}
	
	/** Executes the command. This command edits the class name of an existing class in the model. The class must exist and the new class name must be unique.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className) && !model.getClasses().containsKey(newClassName))
		{
			Class c = model.getClasses().get(className);
			c.setName(newClassName);
			model.getClasses().remove(className, c); //We need to change the key value to do this we have to remove the mapping and then re-add it with then new name.
			model.getClasses().put(newClassName, c);
			response = "You have renamed the class " + className + " to " + newClassName;
			stateChange = true;
		}
		else 
		{
			if (!model.getClasses().containsKey(className))
			{
				response = "The class " + className + " does not exist.";
			}
			else 
			{
				response = "The new class name " + newClassName + " already exists.";
			} 	
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
