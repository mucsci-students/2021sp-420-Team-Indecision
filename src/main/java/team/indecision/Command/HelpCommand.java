package team.indecision.Command;

/** This class represents the Help command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class HelpCommand implements Command {

	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs a Help command.
	 */
	public HelpCommand () {
		stateChange = false;
	}
	
	/** Executes the command. This command stores the acceptable commands in a string.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		return "ADD" + System.lineSeparator() + "add class class_name - adds a class " + System.lineSeparator() + "add field class_name field_type field_name - adds a field to the desired class " + System.lineSeparator() + "add method class_name method_return_type method_name method_parameters (method parameters must be entered in the following format: parameter_type parameter_name .....)- adds a method to a desired class " + System.lineSeparator() + "add rel class1 class2 relationship_type - adds a relationship between classes the relationship type must be one of the following Aggregation, Composition, Inheritance or Realization " + System.lineSeparator() + "DELETE " + System.lineSeparator() + "delete class class_name - deletes given class " + System.lineSeparator() + "delete field class_name field_name - deletes given field in specified class " + System.lineSeparator() + "delete method class_name method_name method_parameters (method parameters must be entered in the following format: parameter_type parameter_name .....)- deletes given method in specified class " + System.lineSeparator() + "delete rel class_name relationship_destination_name - deletes the relationship between given classes " + System.lineSeparator() + "RENAME/EDIT " + System.lineSeparator() + "edit class class_name new_class_name - renames the specified class " + System.lineSeparator() + "edit field name class_name curr_field new_field_name - renames desired field" + System.lineSeparator() + "edit field type class_name curr_field new_field_type - renames desired field type" + System.lineSeparator() + "edit method name class_name method_name method_parameters (method parameters must be entered in the following format: parameter_type parameter_name .....) / new_method_name - renames desired method" + System.lineSeparator() +"edit method type class_name method_name method_parameters (method parameters must be entered in the following format: parameter_type parameter_name .....) / new_method_type - renames desired method return type" + System.lineSeparator() + "edit method parameters class_name  method_name method_parameters (method parameters must be entered in the following format: parameter_type parameter_name .....) / new_method_parameters (method parameters must be entered in the following format: parameter_type parameter_name .....) - sets a new parameter list for the method " + System.lineSeparator() + "edit rel dest class_name destination_name new_destination_name - changes the destination of the relationship" + System.lineSeparator() + "edit rel type class_name destination_name new_destination_type - changes the type of the relationship" + System.lineSeparator() + "LIST " + System.lineSeparator() + "list class class_name - list single class and all its components " + System.lineSeparator() + "list classes - list all classes " + System.lineSeparator() + "list rel - lists all relationships between classes " + System.lineSeparator() + "SAVE/LOAD " + System.lineSeparator() + "save file_name - saves a file to x-destination with the given file name in .json format " + System.lineSeparator() + "load file_name - loads file with given file name " + System.lineSeparator() + "UNDO/REDO" + System.lineSeparator() + "undo - undoes the last action that changed the state of the model" + System.lineSeparator() + "redo - redoes the last undo" + System.lineSeparator() + "EXIT " + System.lineSeparator() +"exit - will exit the program ";
	}
	
	/** Gets the stateChange field.
	 * @return A boolean that represents whether or not the state has changed.
	 */
	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
