package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class AddClassCommand implements Command {

	private Classes model;
	String className;
	boolean stateChange;
	
	public AddClassCommand(Classes modelP, String classNameP) {
		model = modelP;
		className = classNameP;
		stateChange = false;
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
				stateChange = true;
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

	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
