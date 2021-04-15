package team.indecision.Command;

import java.util.List;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;

/** This class represents the Delete Method command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class DeleteMethodCommand implements Command {

	//Stores the model that the command will be executed against.
	Classes model;
	//Stores the desired class name of the class to be deleted from the model.
	String className;
	//Stores the desired method name of the method to be deleted from the class.
	String methodName;
	//Stores the desired method parameters of the method to be deleted form the class.
	List<String> parameters;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs a Delete Method command with the desired model, class name, method name, and parameters.
	 * @param classNameP The class name.
	 * @param methodNameP The method name.
	 * @param paramtersP The parameters.
	 * @param modelP The model.
	 */
	public DeleteMethodCommand (Classes modelP, String classNameP, String methodNameP, List<String> parametersP) {
		
		//Stores the model that the command will be executed against.
		model = modelP;
		//Stores the desired class name of the class to be deleted from the model.
		className = classNameP;
		//Stores the desired method name of the method to be deleted from the class.
		methodName = methodNameP;
		//Stores the desired method parameters of the method to be deleted from the class.
		parameters = parametersP;
		//Stores whether or not the state of the model has changed. True if the state has changed false if not.
		stateChange = false;
	}
	
	/** Executes the command. This command deletes a method from the model. The class and method with the specified parameters must exist.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if (c.containsMethod(methodName, parameters)) {
				c.deleteMethod(methodName, parameters);
				response = "The method " +  methodName + " has been deleted from class " + className + ".";
				stateChange = true;
			}
			else {
				response = "The method " +  methodName + " does not exist with the class " + className + ".";
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
