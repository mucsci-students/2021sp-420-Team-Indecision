package team.indecision.Command;

import java.io.File;
import java.nio.file.Paths;
import java.util.SortedMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class LoadJSONCommand implements Command {

	Classes model;
	File file;
	boolean stateChange;
	
	public LoadJSONCommand (Classes modelP, File fileP) {
		model = modelP;
		file = fileP;
		stateChange = false;
	}
	
	@Override
	public String execute() {
		String response;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			SortedMap<String, Class> classes = objectMapper.readValue(file, new TypeReference<SortedMap<String, Class>>() {});
			model.setClasses(classes);
			response = "";
			stateChange = true;
		} catch (Exception ex) {
			response = "Not a valid file type (.json).";
		}
		return response;
	}

	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
