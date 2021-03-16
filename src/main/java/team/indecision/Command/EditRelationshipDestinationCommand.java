package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;
import team.indecision.Model.Relationship;

public class EditRelationshipDestinationCommand implements Command {

	Classes model;
	String className;
	String relationshipDestination;
	String newRelationshipDestination;
	
	public EditRelationshipDestinationCommand (Classes modelP, String classNameP, String relationshipDestinationP, String newRelationshipDestinationP) {
		model = modelP;
		className = classNameP;
		relationshipDestination = relationshipDestinationP;
		newRelationshipDestination = newRelationshipDestinationP;
	}
	
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if(c.containsRelationship(relationshipDestination) && !c.containsRelationship(newRelationshipDestination)) {
				Relationship r = c.getRelationship(relationshipDestination);
				r.setDestination(newRelationshipDestination);
				response = "The relationship " + relationshipDestination + " has been changed to " + newRelationshipDestination + ".";
			}
			else {
				if (c.containsRelationship(newRelationshipDestination)) {
					response = "The relationship " +  newRelationshipDestination + " already exists with the class " + className + ".";
				}
				else {
					response = "The relationship " +  relationshipDestination + " does not exist with the class " + className + ".";
				}
			}
		}
		else {
			response = "The class " +  className + " does not exist.";
		}
		return response;
	}
}
