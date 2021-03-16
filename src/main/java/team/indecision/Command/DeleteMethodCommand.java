package team.indecision.Command;

import java.util.List;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class DeleteMethodCommand implements Command {

	Classes model;
	String className;
	String methodName;
	List<String> parameters;
	
	public DeleteMethodCommand (Classes modelP, String classNameP, String methodNameP, List<String> parametersP) {
		model = modelP;
		className = classNameP;
		methodName = methodNameP;
		parameters = parametersP;
	}
	
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if (c.containsMethod(methodName, parameters)) {
				c.deleteMethod(methodName, parameters);
				response = "The method " +  methodName + " has been deleted from class " + className + ".";
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
}
