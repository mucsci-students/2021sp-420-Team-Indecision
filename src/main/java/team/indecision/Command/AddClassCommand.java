package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

/** This class represents the Add Class command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class AddClassCommand implements Command {
	//Stores the model that the command will be executed against.
	private Classes model;
	//Stores the desired class name to be used when the class is created.
	private String className;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	private boolean stateChange;
	
	/** Constructs an Add Class command with the desired model and class name.
	 * @param nameP The class name.
	 * @param modelP The model.
	 */
	public AddClassCommand(Classes modelP, String classNameP) {
		model = modelP;
		className = classNameP;
		stateChange = false;
	}
	
	/** Executes the command. This command adds a new class to the model. The class must be unique and the first letter of the name must be a java char.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		Class c = new Class(className); 
		if (!model.getClasses().containsKey(className)) 
		{
			char first = className.charAt(0);
			if (Character.isLetter(first)) {
				model.getClasses().put(className, c);
				response = "You have created a new class named: " + className;
				stateChange = true;
			}
			else {
				response = "The first letter must be a java letter.";
			}
		}
		else 
		{
			response = "The class " + className + " already exists.";
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
