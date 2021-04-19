package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

/** This class represents the Add Field command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class AddFieldCommand implements Command {
	
	//Stores the model that the command will be executed against.
	private Classes model;
	//Stores the desired class name for the field to be created in.
	private String className;
	//Stores the desired field type to be used when the field is created.
	private String fieldType;
	//Stores the desired field name to be used when the field is created.
	private String fieldName;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs an Add Field command with the desired model, class name and field name.
	 * @param classNameP The class name.
	 * @param fieldNameP The field name.
	 * @param modelP The model.
	 */
	public AddFieldCommand(Classes modelP, String classNameP, String fieldTypeP, String fieldNameP) {
		model = modelP;
		className = classNameP;
		fieldType = fieldTypeP;
		fieldName = fieldNameP;
		stateChange = false;
	}
	
	/** Executes the command. This command adds a new field to class. The class must exist and the field must be unique.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if (!c.containsField(fieldName)) {
				c.addField(fieldType, fieldName);
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

	/** Gets the stateChange field.
	 * @return A boolean that represents whether or not the state haas changed.
	 */
	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
