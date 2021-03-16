package team.indecision.Command;

import java.nio.file.Paths;
import java.util.SortedMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class LoadJSONCommand implements Command {

	Classes model;
	String fileName;
	
	public LoadJSONCommand (Classes modelP, String fileNameP) {
		model = modelP;
		fileName = fileNameP;
	}
	
	@Override
	public String execute() {
		String response;
		fileName = fileName.concat(".json");
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			SortedMap<String, Class> classes = objectMapper.readValue(Paths.get(fileName).toFile(), new TypeReference<SortedMap<String, Class>>() {});
			model.setClasses(classes);
			response = "Your data has been loaded from a JSON file in your program's root directory";
		} catch (Exception ex) {
			response = "Not a valid json file or the file does not exist.";
		}
		return response;
	}

}
