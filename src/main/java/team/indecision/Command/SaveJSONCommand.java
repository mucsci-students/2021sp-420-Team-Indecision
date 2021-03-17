package team.indecision.Command;

import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import team.indecision.Model.Classes;

public class SaveJSONCommand implements Command {

	Classes model;
	String fileName;
	boolean stateChange;
	
	public SaveJSONCommand (Classes modelP, String fileNameP) {
		model = modelP;
		fileName = fileNameP;
		stateChange = false;
	}
	
	@Override
	public String execute() {
		String response;
		fileName = fileName.concat(".json");
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(Paths.get(fileName).toFile(), model.getClasses());
			response = "Your data has been saved to a JSON file in your program's root directory.";
		} catch (Exception ex) {
			System.out.println("Not a valid file name.");
			response = "Not a valid file name.";
		}	
		return response;
	}

	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
