package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class AddClassCommand implements Command {

	Classes model;
	String className; 
	
	public AddClassCommand(Classes model, String className) {
		this.model = model;
		this.className = className;
	}
	
	@Override
	public String execute() {
		String response;
		Class c = new Class(className); 
		if (!model.getClasses().containsKey(className)) 
		{
			char first = className.charAt(0);
			if (Character.isLetter(first)) {
				model.getClasses().put(className, c);
				response = "You have created a new class named: " + className;
			}
			else {
				response = "The first letter must be a java letter.";
			}
		}
		else 
		{
			response = "The class " + className + " already exists.";
		}
		return response; 
	}

}
