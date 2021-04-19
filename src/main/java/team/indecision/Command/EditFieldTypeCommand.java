package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;
import team.indecision.Model.Field;

/** This class represents the Edit Field Type command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class EditFieldTypeCommand implements Command {
	
	//Stores the model that the command will be executed against.
	Classes model;
	//Stores the desired class name of the class in which the field exists.
	String className;
	//Stores the desired field name of the field to edited.
	String fieldName;
	//Stores the new field Type.
	String newFieldType;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs an Edit Field Type command with the desired model, class name, field name, and new field type.
	 * @param classNameP The class name.
	 * @param fieldNameP The field name.
	 * @param newFieldTypeP The new field type.
	 * @param modelP The model.
	 */
	public EditFieldTypeCommand (Classes modelP, String classNameP, String fieldNameP, String newFieldTypeP) {
		model = modelP;
		className = classNameP;
		fieldName = fieldNameP;
		newFieldType = newFieldTypeP;
		stateChange = false;
	}

	/** Executes the command. This command edits the field type of an existing field in a class. The class and field must exist.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if(c.containsField(fieldName)) {
				Field f = c.getField(fieldName);
				f.setType(newFieldType);
				response = "The field " + fieldName + " has had its type changed to " + newFieldType + ".";
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

	/** Gets the stateChange field.
	 * @return A boolean that represents whether or not the state has changed.
	 */
	@Override
	public boolean getStateChange() {
		return stateChange;
	}

}
