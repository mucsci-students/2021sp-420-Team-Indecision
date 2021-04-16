package team.indecision.Command;

import java.io.File;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import team.indecision.Model.Classes;

/** This class represents the Save JSON command.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class SaveJSONCommand implements Command {

	//Stores the model that the command will be executed against.
	Classes model;
	//Stores the file name of the file that will be loaded.
	String fileName;
	//Stores the file to be loaded this will be null if the cli controller is calling this command
	File file;
	//Stores whether or not the state of the model has changed. True if the state has changed false if not.
	boolean stateChange;
	
	/** Constructs a Save JSON command with the desired model and file.
	 */
	public SaveJSONCommand (Classes modelP, File fileP) {
		model = modelP;
		fileName = null;
		file = fileP;
		stateChange = false;
	}
	
	/** Constructs a Save JSON command with the desired model and file name.
	 */
	public SaveJSONCommand (Classes modelP, String fileNameP) {
		model = modelP;
		fileName = fileNameP;
		file = null;
		stateChange = false;
	}
	
	/** Executes the command. This command saves JSON to a file with the desired name.
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
    public String execute() {
        String response;
        try {
        	if (file == null ) {
        		File f = new File(fileName);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(f, model.getClasses());
        	}
        	else {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(file, model.getClasses());
        	}
                
                response = "Your data has been saved to a JSON file.";
        } catch (Exception ex) {
            response = "Not a valid path or filename.";
        }
        return response;
    }

	/** Gets the stateChange field.
	 * @return A boolean that represents whether or not the state has changed.
	 */
	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
