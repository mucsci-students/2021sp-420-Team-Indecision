package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class AddFieldCommand implements Command {

	private Classes model;
	private String className;
	private String fieldName;
	private String type; 
	boolean stateChange;
	
	public AddFieldCommand(Classes modelP, String classNameP, String typeP, String fieldNameP) {
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
			if (!c.containsField(fieldName)) {
				c.addField(type,fieldName);
				response = "The field " + fieldName + " has been added to the class " + className + ".";
				stateChange = true;
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

	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
