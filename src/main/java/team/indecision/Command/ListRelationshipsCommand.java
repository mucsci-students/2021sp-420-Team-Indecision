package team.indecision.Command;

import java.util.SortedMap;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class ListRelationshipsCommand implements Command {

	Classes model;
	
	public ListRelationshipsCommand (Classes modelP) {
		model = modelP;
	}
	
	@Override
	public String execute() {
		String response = "";
		int i = 0;
		for (SortedMap.Entry<String, Class> entry : model.getClasses().entrySet()) {
	        Class c = entry.getValue();
	        if (!c.getRelationships().isEmpty()) {
	        	if ((model.getClasses().size() -1) == i)
		        {
		        	response += c.getName() + c.printRelationships();
		        }
		        else {
		        	response += c.getName() + c.printRelationships() + System.lineSeparator();
		        }
	        }
	        i++;
	    }
		return response;
	}

}
