package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class AddFieldCommand implements Command {

	Classes model;
	String className;
	String fieldName;
	
	public AddFieldCommand(Classes modelP, String classNameP, String fieldNameP) {
		model = modelP;
		className = classNameP;
		fieldName = fieldNameP;
	}
	
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if (!c.containsField(fieldName)) {
				c.addField(fieldName);
				response = "The field " + fieldName + " has been added to the class " + className + ".";
			}
			else {
				response = "The field " + fieldName + " already exists with the class " + className + ".";
			}
		}
		else {
			response = "The class "  +  className + " does not exist.";
		}
		return response;
	}
}
