package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

/** This class represents the Add Relationship command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class AddRelationshipCommand implements Command {

	//Stores the model that the command will be executed against.
	Classes model;
	//Stores the desired class name for the relationship to be created in.
	String className;
	//Stores the desired relationshipDestination to be used when the relationship is created.
	String relationshipDestination;
	//Stores the desired relationshipType to be used when the relationship is created.
	String relationshipType;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs an Add Relationship command with the desired model, class name, relationship name, and relationship type.
	 * @param classNameP The class name.
	 * @param relationshipDestinationP The relationship name.
	 * @param relationshipTypeP the relationship type.
	 * @param modelP The model.
	 */
	public AddRelationshipCommand (Classes modelP, String classNameP, String relationshipDestinationP, String relationshipTypeP) {
		model = modelP;
		className = classNameP;
		relationshipDestination = relationshipDestinationP;
		relationshipType = relationshipTypeP;
		stateChange = false;
	}
	
	/** Executes the command. This command adds a new relationship to a class. The class must exist and the relationship must be unique and the class that the relationship will be with must also exist.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if (!c.containsRelationship(relationshipDestination)) {
				if (model.getClasses().containsKey(relationshipDestination)) {
					if (relationshipType.equals("Aggregation") || relationshipType.equals("Composition") || relationshipType.equals("Inheritance") || relationshipType.equals("Realization")) {
						c.addRelationship(relationshipDestination, relationshipType);
						response = "The " + className +" has created a new relationship with class: " + relationshipDestination + " of type " + relationshipType;
						stateChange = true;
					}
					else {
						response = "The relationship type must be one of the following: Aggregation, Composition, Inheritance or Realization";
					}
				}
				else {
					response = "The " + relationshipDestination + " class does not exist.";
				}
			}
			else {
				response = "A relationship with this class " + className + " already exists.";
			}
		}
		else {
			response = "The class " +  className + " does not exist.";
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
