package team.indecision.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import team.indecision.Command.*;
import team.indecision.Command.AddClassCommand;
import team.indecision.Model.Classes;
import team.indecision.View.CLI;

public class CLIController {
	private Classes model;
	private CLI view;
	
	public CLIController(Classes model, CLI view) {
		this.model = model;
		this.view = view;
		
		String choice = view.prompt();
		String seperator = " ";
		String[] parsedChoice = StringUtils.split(choice, seperator);
		
		boolean x = true;
		while (x) {
			if (parsedChoice.length == 3 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("class"))) {
				String response = executeCommand(new AddClassCommand(model, parsedChoice[2]));
				view.update(response);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("field"))) {
				model.addFieldCLI(parsedChoice[2], parsedChoice[3]);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("method"))) {
				String parameters = view.prompt();
				String sep = ",";
				String[] parsedParameters = StringUtils.split(parameters, sep);
				List<String> parametersList = Arrays.asList(parsedParameters);
				model.addMethodCLI(parsedChoice[2], parsedChoice[3], parametersList);
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("rel"))) {
				model.addRelationshipCLI(parsedChoice[2], parsedChoice[3], parsedChoice[4]);
			}
			// delete
			else if (parsedChoice.length == 3 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("class"))) {
				model.deleteClassCLI(parsedChoice[2]);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("field"))) {
				model.deleteFieldCLI(parsedChoice[2], parsedChoice[3]);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("method"))) {
				String parameters = view.prompt();
				String sep = ",";
				String[] parsedParameters = StringUtils.split(parameters, sep);
				List<String> parametersList = Arrays.asList(parsedParameters);
				model.deleteMethodCLI(parsedChoice[2], parsedChoice[3], parametersList);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("rel"))) {
				model.deleteRelationshipCLI(parsedChoice[2], parsedChoice[3]);
			}
			// rename / edit
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("rename") && (parsedChoice[1].equals("class"))) {
				model.renameClassCLI(parsedChoice[2], parsedChoice[3]);
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("field"))) {
				model.editFieldCLI(parsedChoice[2], parsedChoice[3], parsedChoice[4]);
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("method")) && (parsedChoice[2].equals("name"))) {
				String parameters = view.prompt();
				String sep = ",";
				String[] parsedParameters = StringUtils.split(parameters, sep);
				List<String> parametersList = Arrays.asList(parsedParameters);
				String methodNameNew = view.prompt();
				sep = " ";
				String[] parsedMethodNameNew = StringUtils.split(methodNameNew, sep);
				model.editMethodNameCLI(parsedChoice[3], parsedChoice[4], parametersList, parsedMethodNameNew[0]);
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("method")) && (parsedChoice[2].equals("parameters"))) {
				String parameters = view.prompt();
				String sep = ",";
				String[] parsedParameters = StringUtils.split(parameters, sep);
				List<String> parametersList = Arrays.asList(parsedParameters);
				String parametersNew = view.prompt();
				String[] parsedParametersNew = StringUtils.split(parametersNew, sep);
				List<String> parametersListNew = Arrays.asList(parsedParametersNew);
				model.editMethodParametersCLI(parsedChoice[3], parsedChoice[4], parametersList, parametersListNew);
			}
			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel")) && (parsedChoice[2].equals("dest"))) {
				model.editRelationshipDestinationCLI(parsedChoice[3], parsedChoice[4], parsedChoice[5]);
			}
			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel")) && (parsedChoice[2].equals("type"))) {
				model.editRelationshipTypeCLI(parsedChoice[3], parsedChoice[4], parsedChoice[5]);
			}
			// list
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("classes"))) {
				model.listClasses();
			}
			else if (parsedChoice.length == 3 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("class"))) {
				model.listClass(parsedChoice[2]);
			}
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("rel"))) {
				model.listRelationships();
			}
			// save / load
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("save")) {
				model.saveJSON(parsedChoice[1]);
			}
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("load")) {
				model.loadJSON(parsedChoice[1]);
			}
			// help and exit
			else if (parsedChoice.length == 1 && parsedChoice[0].equals("help")) {
			   model.help();
			}
			else if (parsedChoice.length == 1 && parsedChoice[0].equals("exit")){
			   break;
			}
			else {
				System.out.println("Please enter a valid selection");			
			}
			choice = view.prompt();
			parsedChoice = StringUtils.split(choice, seperator);
			}
		
	}
	
	private String executeCommand(Command command) {
		return command.execute();
	}
	
}