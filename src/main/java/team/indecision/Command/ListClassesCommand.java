package team.indecision.Command;

import java.util.SortedMap;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class ListClassesCommand implements Command {

	Classes model;
	
	public ListClassesCommand (Classes modelP) {
		model = modelP;
	}
	
	@Override
	public String execute() {
		String response = "";
		int i = 0;
		for (SortedMap.Entry<String, Class> entry : model.getClasses().entrySet()) {
	        Class c = entry.getValue();
	        if ((model.getClasses().size() -1) == i)
	        {
	        	response += c.toString();
	        }
	        else {
	        	response += c.toString() + System.lineSeparator();
	        }
	        i++;
	    }
		return response;
	}

}
