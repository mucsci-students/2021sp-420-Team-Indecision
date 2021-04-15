package team.indecision.Command;

import java.util.List;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;
import team.indecision.Model.Method;

public class EditMethodNameCommand implements Command {

	Classes model;
	String className;
	String methodName;
	List<String> parameters;
	String newMethodName;
	boolean stateChange;
	String returnType;
	String parameterType;
	
	public EditMethodNameCommand (Classes modelP, String classNameP, String returnTypeP, String methodNameP, String parameterTypeP, List<String> parametersP, String newMethodNameP) {
		model = modelP;
		className = classNameP;
		methodName = methodNameP;
		parameters = parametersP;
		newMethodName = newMethodNameP;
		stateChange = false;
		returnType = returnTypeP;
		parameterType = parameterTypeP;
	}
	
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if(c.containsMethod(returnType, methodName, parameterType, parameters) && !c.containsMethod(returnType, newMethodName,parameterType, parameters)) {
				Method m = c.getMethod(returnType, methodName, parameterType, parameters);
				m.setName(newMethodName);
				response = "The method " + methodName + " has been changed to " + newMethodName + ".";
				stateChange = true;
			}
			else {
				if (c.containsMethod(returnType, newMethodName,parameterType, parameters)) {
					response = "The method " +  newMethodName + " already exists with the class " + className + " with those parameters.";
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
	
	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}