package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;
import team.indecision.Model.Field;

/** This class represents the Edit Field Name command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class EditFieldNameCommand implements Command {
	
	//Stores the model that the command will be executed against.
	Classes model;
	//Stores the desired class name of the class in which the field exists.
	String className;
	//Stores the desired field name of the field to edited.
	String fieldName;
	//Stores the new field name.
	String newFieldName;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs an Edit Field Name command with the desired model, class name, field name, and new field name.
	 * @param classNameP The class name.
	 * @param fieldNameP The field name.
	 * @param newFieldNameP The new field name.
	 * @param modelP The model.
	 */
	public EditFieldNameCommand (Classes modelP, String classNameP, String fieldNameP, String newFieldNameP) {
		model = modelP;
		className = classNameP;
		fieldName = fieldNameP;
		newFieldName = newFieldNameP;
		stateChange = false;
	}
	
	/** Executes the command. This command edits the field name of an existing field in a class. The class and field must exist and the new field name must be unique.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if(c.containsField(fieldName) && !c.containsField(newFieldName)) {
				Field f = c.getField(fieldName);
				f.setName(newFieldName);
				response = "The field " + fieldName + " has been renamed to " + newFieldName + ".";
				stateChange = true;
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
	
	/** Gets the stateChange field.
	 * @return A boolean that represents whether or not the state has changed.
	 */
	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
