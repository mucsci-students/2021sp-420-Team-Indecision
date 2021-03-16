package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;
import team.indecision.Model.Relationship;

public class EditRelationshipTypeCommand implements Command {
	
	Classes model;
	String className;
	String relationshipDestination;
	String newRelationshipType;
	
	public EditRelationshipTypeCommand (Classes modelP, String classNameP, String relationshipDestinationP, String newRelationshipTypeP) {
		model = modelP;
		className = classNameP;
		relationshipDestination = relationshipDestinationP;
		newRelationshipType = newRelationshipTypeP;
	}
	
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			Class c = model.getClasses().get(className);
			if(c.containsRelationship(relationshipDestination)) {
				Relationship r = c.getRelationship(relationshipDestination);
				if (newRelationshipType.equals("Aggregation") || newRelationshipType.equals("Composition") || newRelationshipType.equals("Inheritance") || newRelationshipType.equals("Realization")) {
					r.setType(newRelationshipType);
					System.out.println("The relationship " + relationshipDestination + " type has been changed to " + newRelationshipType + ".");
					response = "The relationship " + relationshipDestination + " type has been changed to " + newRelationshipType + ".";
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
}