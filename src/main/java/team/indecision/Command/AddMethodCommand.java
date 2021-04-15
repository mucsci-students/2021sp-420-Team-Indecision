package team.indecision.Command;

import java.util.List;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class AddMethodCommand implements Command{

	Classes model;
	String className;
	String methodName;
	String parameterType;
	List<String> parameters;
	boolean stateChange;
	String returnType;
	
	public AddMethodCommand (Classes modelP, String classNameP, String returnTypeP, String methodNameP, String parameterTypeP, List<String> parametersP) {
		model = modelP;
		className = classNameP;
		methodName = methodNameP;
		parameters = parametersP;
		stateChange = false;
		returnType = returnTypeP;
		parameterType = parameterTypeP;
	}
	
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if (!c.containsMethod(returnType,methodName,parameterType, parameters)) {
				c.addMethod(returnType,methodName,parameterType, parameters);
				response = returnType + " " + methodName + " has been added to " + className + ".";
				stateChange = true;
			}
			else {
				response = returnType + " " + methodName + " already exists with the class " + className + " with those parameters.";
			}
		}
		else {
			response = "The class "  +  className + " does not exist.";
		}
		return response;
	}
	
	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}