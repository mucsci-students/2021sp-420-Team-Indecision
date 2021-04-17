package team.indecision.Command;

import java.util.List;
import java.util.SortedSet;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;
import team.indecision.Model.Parameter;

/** This class represents the Add Method command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class AddMethodCommand implements Command{

	//Stores the model that the command will be executed against.
	Classes model;
	//Stores the desired return type for the method.
	String returnType;
	//Stores the desired class name for the method.
	String className;
	//Stores the desired method name to be used when the method is created.
	String methodName;
	//Stores the desired method parameters to be used when the method is created.
	SortedSet<Parameter> parameters;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs an Add Method command with the desired model, class name, method name, and method parameters.
	 * @param classNameP The class name.
	 * @param fieldNameP The method name.
	 * @param parametersP the method parameters.
	 * @param modelP The model.
	 */
	public AddMethodCommand (Classes modelP, String classNameP, String returnTypeP,  String methodNameP, SortedSet<Parameter> parametersP) {
		model = modelP;
		className = classNameP;
		methodName = methodNameP;
		parameters = parametersP;
		returnType = returnTypeP;
		stateChange = false;
	}
	
	/** Executes the command. This command adds a new method to class. The class must exist and the method parameters must be unique for the method name.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if (!c.containsMethod(methodName, parameters)) {
				c.addMethod(returnType, methodName, parameters);
				response = "The method " + methodName + " has been added to the class " + className + ".";
				stateChange = true;
			}
			else {
				response = "The method " + methodName + " already exists with the class " + className + " with those parameters.";
			}
		}
		else {
			response = "The class "  +  className + " does not exist.";
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
