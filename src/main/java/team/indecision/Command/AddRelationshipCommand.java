package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class AddRelationshipCommand implements Command {

	Classes model;
	String className;
	String relationshipDestination;
	String relationshipType;
	
	public AddRelationshipCommand (Classes modelP, String classNameP, String relationshipDestinationP, String relationshipTypeP) {
		model = modelP;
		className = classNameP;
		relationshipDestination = relationshipDestinationP;
		relationshipType = relationshipTypeP;
	}
	
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

}
