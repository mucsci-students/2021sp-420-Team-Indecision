package team.indecision.Command;

import java.util.SortedSet;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;
import team.indecision.Model.Method;
import team.indecision.Model.Parameter;

public class EditMethodTypeCommand implements Command{

	
	//Stores the model that the command will be executed against.
		Classes model;
		//Stores the desired class name of the class in which the method exists.
		String className;
		//Stores the desired method name of the method to be edited.
		String methodName;
		//Stores the desired method parameters of the method to be edited.
		SortedSet<Parameter> parameters;
		//Stores the new method name.
		String newMethodType;
		//Stores whether or not the state of the model has changed. True if the state has changed false if not.
		boolean stateChange;
		
		/** Constructs an Edit Method Name command with the desired model, class name, method name, method parameters, and new method name.
		 * @param classNameP The class name.
		 * @param methodNameP The field name.
		 * @param parametersP The method parameters.
		 * @param newMethodNameP The new method name.
		 * @param modelP The model.
		 */
		public EditMethodTypeCommand (Classes modelP, String classNameP, String methodNameP, SortedSet<Parameter> parametersP, String newMethodTypeP) {
			model = modelP;
			className = classNameP;
			methodName = methodNameP;
			parameters = parametersP;
			newMethodType = newMethodTypeP;
			stateChange = false;
		}
		
		/** Executes the command. This command edits the method name of an existing method in a class. The class and method must exist and the new method name must be unique.
		 * @return A string that represents the outcome of the execution.
		 */
		@Override
		public String execute() {
			String response;
			if (model.getClasses().containsKey(className)) {
				Class c = model.getClasses().get(className);
				if(c.containsMethod(methodName, parameters)) {
					Method m = c.getMethod(methodName, parameters);
					m.setType(newMethodType);
					response = "The method type" + " has been changed to " + newMethodType + ".";
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
		 * @return A boolean that represents whether or not the state has changed.
		 */
		@Override
		public boolean getStateChange() {
			return stateChange;
		}
}
