package team.indecision.Command;

import java.io.File;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import team.indecision.Model.Classes;

public class SaveJSONCommand implements Command {

	Classes model;
	File file;
	boolean stateChange;
	
	public SaveJSONCommand (Classes modelP, File fileP) {
		model = modelP;
		file = fileP;
		stateChange = false;
	}
	
	@Override
	public String execute() {
		String response;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(file, model.getClasses());
			response = "";
		} catch (Exception ex) {
			response = "Program went bye-bye :(";
		}	
		return response;
	}

	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
