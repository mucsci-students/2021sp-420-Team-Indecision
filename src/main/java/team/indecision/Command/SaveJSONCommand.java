package team.indecision.Command;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
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
        try {
                File f = new File(fileName);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(f, model.getClasses());
                response = "Your data has been saved to a JSON file in your program's root directory.";
        } catch (Exception ex) {
            response = "Not a valid path or filename.";
        }
        return response;
    }

	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
