package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class DeleteRelationshipCommand implements Command {

	Classes model;
	String className;
	String relationshipDestination;
	
	public DeleteRelationshipCommand (Classes modelP, String classNameP, String relationshipDestinationP) {
		model = modelP;
		className = classNameP;
		relationshipDestination = relationshipDestinationP;
	}
	
	@Override
	public String execute() {
		String response;
        if (model.getClasses().containsKey(className)) {
            Class c = model.getClasses().get(className);
            if (c.containsRelationship(relationshipDestination)) {
            	c.deleteRelationship(relationshipDestination);
                response = "You have deleted a relationship with class: " + relationshipDestination;
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

}
