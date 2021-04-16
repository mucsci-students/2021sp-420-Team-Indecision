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
		return "ADD" + System.lineSeparator() + "add class class_name - adds a class " + System.lineSeparator() + "add field class_name field_name - adds attribute to a desired class " + System.lineSeparator() + "add method class_name method_name parameter_list(comma separated)- adds a method to a desired class " + System.lineSeparator() + "add rel class1 class2 relationship_type - adds a relationship between classes the relationship type must be one of the following Aggregation, Composition, Inheritance or Realization " + System.lineSeparator() + "DELETE " + System.lineSeparator() + "delete class class_name - deletes given class " + System.lineSeparator() + "delete field class_name attr_name - deletes given field in specified class " + System.lineSeparator() + "delete method class_name parameter_list(comma separated)- deletes given method in specified class " + System.lineSeparator() + "delete rel class1 class2 - deletes the relationship between given classes " + System.lineSeparator() + "RENAME/EDIT " + System.lineSeparator() + "edit class class_name new_class_name - renames the specified class " + System.lineSeparator() + "edit field class_name curr_field new_field_name - renames desired field given its class " + System.lineSeparator() + "edit method name class_name curr_method parameter_list new_method_name - renames desired method given its class " + System.lineSeparator() + "edit method parameters class_name  method_name paramter_list new_parameter_list- renames desired attribute given its class " + System.lineSeparator() + "LIST " + System.lineSeparator() + "list class class_name - list single class and all its components " + System.lineSeparator() + "list classes - list all classes " + System.lineSeparator() + "list rel - lists all relationships between classes " + System.lineSeparator() + "SAVE/LOAD " + System.lineSeparator() + "save file_name - saves a file to x-destination with the given file name in .json format " + System.lineSeparator() + "load file_name - loads file with given file name " + System.lineSeparator() + "exit- will exit the program ";
	}
	
	/** Gets the stateChange field.
	 * @return A boolean that represents whether or not the state has changed.
	 */
	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
