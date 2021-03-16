package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;
import team.indecision.Model.Field;

public class EditFieldNameCommand implements Command {

	Classes model;
	String className;
	String fieldName;
	String newFieldName;
	
	public EditFieldNameCommand (Classes modelP, String classNameP, String fieldNameP, String newFieldNameP) {
		model = modelP;
		className = classNameP;
		fieldName = fieldNameP;
		newFieldName = newFieldNameP;
	}
	
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if(c.containsField(fieldName) && !c.containsField(newFieldName)) {
				Field f = c.getField(fieldName);
				f.setName(newFieldName);
				response = "The field " + fieldName + " has been renamed to " + newFieldName + ".";
			}
			else {
				if (c.containsField(newFieldName)) {
					response = "The field " +  newFieldName + " already exists with the class " + className + ".";
				}
				else {
					response = "The field " +  fieldName + " does not exist with the class " + className + ".";
				}
			}
		}
		else {
			response = "The class " +  className + " does not exist.";
		}
		return response;
	}
}
