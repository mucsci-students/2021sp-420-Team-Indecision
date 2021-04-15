package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;
import team.indecision.Model.Relationship;

/** This class represents the Edit Relationship Type command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class EditRelationshipTypeCommand implements Command {
	
	//Stores the model that the command will be executed against.
	Classes model;
	//Stores the desired class name of the class in which the relationship exists.
	String className;
	//Stores the relationship destination of the class to be edited.
	String relationshipDestination;
	//Stores the new relationship type.
	String newRelationshipType;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs an Edit Relationship Type command with the desired model, class name, destination name, and new type name.
	 * @param classNameP The class name.
	 * @param relationshipDestinationP The relationship destination name.
	 * @param newRelationshipTypeP The new relationship destination name.
	 * @param modelP The model.
	 */
	public EditRelationshipTypeCommand (Classes modelP, String classNameP, String relationshipDestinationP, String newRelationshipTypeP) {
		model = modelP;
		className = classNameP;
		relationshipDestination = relationshipDestinationP;
		newRelationshipType = newRelationshipTypeP;
		stateChange = false;
	}
	
	/** Executes the command. This command edits the relationship type of an existing relationship in a class. The class and relationship must exist and the new relationship type must be unique.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if(c.containsRelationship(relationshipDestination)) {
				Relationship r = c.getRelationship(relationshipDestination);
				if (newRelationshipType.equals("Aggregation") || newRelationshipType.equals("Composition") || newRelationshipType.equals("Inheritance") || newRelationshipType.equals("Realization")) {
					r.setType(newRelationshipType);
					response = "The relationship " + relationshipDestination + " type has been changed to " + newRelationshipType + ".";
					stateChange = true;
				}
				else {
					response = "The relationship type must be one of the following: Aggregation, Composition, Inheritance or Realization";
				}
			}
			else {
				response = "The relationship " +  relationshipDestination + " does not exist with the class " + className + ".";
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
