package team.indecision.Command;

import java.util.SortedMap;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class DeleteClassCommand implements Command {

	Classes model;
	String className; 
	
	public DeleteClassCommand(Classes model, String className) {
		this.model = model;
		this.className = className;
	}
	
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className))
		{	
			model.getClasses().remove(className);
			for (SortedMap.Entry<String, Class> entry : model.getClasses().entrySet()) {
		        Class c = entry.getValue();
		        if (c.containsRelationship(className)) {
		        	c.deleteRelationship(className);
		        }
		    }
			response = "The class " + className + " has been deleted.";
		}
		else 
		{
			response = "The class " + className + " does not exist.";
		}
		return response;
	}
}
