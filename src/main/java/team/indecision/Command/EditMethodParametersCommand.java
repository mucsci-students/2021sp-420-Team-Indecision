package team.indecision.Command;

import java.util.SortedSet;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;
import team.indecision.Model.Method;
import team.indecision.Model.Parameter;

/** This class represents the Edit Method Parameters command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class EditMethodParametersCommand implements Command {
	
	//Stores the model that the command will be executed against.
	Classes model;
	//Stores the desired class name of the class in which the method exists.
	String className;
	//Stores the desired method name of the method to be edited.
	String methodName;
	//Stores the desired method parameters of the method to be edited.
	SortedSet<Parameter> parameters;
	//Stores the new method parameters.
	SortedSet<Parameter> newParameters;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs an Edit Method Parameters command with the desired model, class name, method name, method parameters, and new method parameters.
	 * @param classNameP The class name.
	 * @param methodNameP The method name.
	 * @param parametersP The method parameters.
	 * @param newParametersP The new parameters.
	 * @param modelP The model.
	 */
	public EditMethodParametersCommand (Classes modelP, String classNameP, String methodNameP, SortedSet<Parameter> parametersP, SortedSet<Parameter> newParametersP) {
		model = modelP;
		className = classNameP;
		methodName = methodNameP;
		parameters = parametersP;
		newParameters = newParametersP;
		stateChange = false;
	}
	
	/** Executes the command. This command edits the method parameters of an existing method in a class. The class and method must exist and the new method parameters must be unique.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if(c.containsMethod(methodName, parameters) && !c.containsMethod(methodName, newParameters)) {
				Method m = c.getMethod(methodName, parameters);
				m.setParameters(newParameters);
				response = "The method parameters of " + methodName + " has been changed.";
				stateChange = true;
			}
			else {
				if (c.containsMethod(methodName, newParameters)) {
					response = "The method " +  methodName + " already exists with the class " + className + " with those parameters.";
				}
				else {
					response = "The method " +  methodName + " does not exist with the class " + className + ".";
				}
			}
		}
		else {
			response = "The class " +  className + " does not exist.";
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
