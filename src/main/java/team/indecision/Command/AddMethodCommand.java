package team.indecision.Command;

import java.util.List;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class AddMethodCommand implements Command{

	Classes model;
	String className;
	String methodName;
	List<String> parameters;
	boolean stateChange;
	
	public AddMethodCommand (Classes modelP, String classNameP, String methodNameP, List<String> parametersP) {
		model = modelP;
		className = classNameP;
		methodName = methodNameP;
		parameters = parametersP;
		stateChange = false;
	}
	
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if (!c.containsMethod(methodName, parameters)) {
				c.addMethod(methodName, parameters);
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
	
	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
