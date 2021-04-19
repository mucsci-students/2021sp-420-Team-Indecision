package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

/** This class represents the Delete Relationship command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class DeleteRelationshipCommand implements Command {

	//Stores the model that the command will be executed against.
	Classes model;
	//Stores the desired class name of the class to be deleted from the model.
	String className;
	//Stores the desired relationship destination to be deleted from the class.
	String relationshipDestination;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs a Delete Relationship command with the desired model, class name, relationship destination name.
	 * @param classNameP The class name.
	 * @param relationshipDestinationP The relationship destination name.
	 * @param modelP The model.
	 */
	public DeleteRelationshipCommand (Classes modelP, String classNameP, String relationshipDestinationP) {
		model = modelP;
		className = classNameP;
		relationshipDestination = relationshipDestinationP;
		stateChange = false;
	}
	
	/** Executes the command. This command deletes a relationship from the model. The class and relationship must exist.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
        if (model.getClasses().containsKey(className)) {
            Class c = model.getClasses().get(className);
            if (c.containsRelationship(relationshipDestination)) {
            	c.deleteRelationship(relationshipDestination);
                response = "You have deleted a relationship with class: " + relationshipDestination;
                stateChange = true;
            }
            else {
                response = "A relationship with " + relationshipDestination + " does not exist.";
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
