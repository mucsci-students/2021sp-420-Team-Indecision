package team.indecision.Command;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class EditClassNameCommand implements Command{

	Classes model;
	String className;
	String newClassName;
	boolean stateChange;
	
	public EditClassNameCommand(Classes modelP, String classNameP, String newClassNameP) {
		model = modelP;
		className = classNameP;
		newClassName = newClassNameP;
		stateChange = false;
	}
	
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className) && !model.getClasses().containsKey(newClassName))
		{
			Class c = model.getClasses().get(className);
			c.setName(newClassName);
			model.getClasses().remove(className, c); //We need to change the key value to do this we have to remove the mapping and then re-add it with then new name.
			model.getClasses().put(newClassName, c);
			response = "You have renamed the class " + className + " to " + newClassName;
			stateChange = true;
		}
		else 
		{
			if (!model.getClasses().containsKey(className))
			{
				response = "The class " + className + " does not exist.";
			}
			else 
			{
				response = "The new class name " + newClassName + " already exists.";
			} 	
		}
		return response;
	}
	
	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
