package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class ListClassCommand implements Command {

	Classes model;
	String className;
	boolean stateChange;
	
	public ListClassCommand (Classes modelP, String classNameP) {
		model = modelP;
		className = classNameP;
		stateChange = false;
	}
	
	@Override
	public String execute() {
		String response;
        if(model.getClasses().containsKey(className)) {
            Class c = model.getClasses().get(className);
            response = c.toString();
        }
        else {
            response = "The class " + className + " does not exist";
        }
		return response;
	}
	
	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
