package team.indecision.Command;

import java.util.List;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;
import team.indecision.Model.Method;

public class EditMethodParametersCommand implements Command {

	Classes model;
	String className;
	String methodName;
	String returnType;
	String parameterType;
	List<String> parameters;
	List<String> newParameters;
	boolean stateChange;
	
	public EditMethodParametersCommand (Classes modelP, String classNameP,String returnTypeP, String methodNameP,String parameterTypeP, List<String> parametersP, List<String> newParametersP) {
		model = modelP;
		className = classNameP;
		methodName = methodNameP;
		parameters = parametersP;
		newParameters = newParametersP;
		returnType = returnTypeP;
		parameterType = parameterTypeP;
		stateChange = false;
	}
	
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if(c.containsMethod(returnType, methodName, parameterType, parameters) && !c.containsMethod(returnType, methodName, parameterType, newParameters)) {
				Method m = c.getMethod(returnType, methodName, parameterType, parameters);
				m.setParameters(newParameters);
				response = "The method parameters of " + methodName + " has been changed.";
				stateChange = true;
			}
			else {
				if (c.containsMethod(returnType, methodName, parameterType, newParameters)) {
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
	
	@Override
	public boolean getStateChange() {
		return stateChange;
	}
	

}
