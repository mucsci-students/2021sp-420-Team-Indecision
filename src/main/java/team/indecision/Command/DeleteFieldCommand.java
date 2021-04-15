package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class DeleteFieldCommand implements Command {

	Classes model;
	String className;
	String fieldName;
	String type;
	boolean stateChange;
	
	
	public DeleteFieldCommand(Classes modelP, String classNameP, String typeP, String fieldNameP) {
		model = modelP;
		className = classNameP;
		fieldName = fieldNameP;
		type = typeP;
		stateChange = false;
	}
	
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if (c.containsField(fieldName)) {
				c.deleteField(type,fieldName);
				response = "The field " +  fieldName + " has been deleted from class " + className + ".";
				stateChange = true;
			}
			else {
				response = "The field " +  fieldName + " does not exist with the class " + className + ".";
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
