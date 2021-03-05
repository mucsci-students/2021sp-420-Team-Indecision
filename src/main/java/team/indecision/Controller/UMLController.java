package team.indecision.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import team.indecision.Model.Classes;

public class UMLController {
	private Classes model;

	
	
	public UMLController(Classes classes) throws IOException {
		model = classes;

	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String choice = br.readLine();
		
		String seperator = " ";
		String[] parsedChoice = StringUtils.split(choice, seperator);
		
		
		boolean x = true;


		while (x) {
			// add

			if (parsedChoice.length == 3 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("class"))) {
				model.addClassCLI(parsedChoice[2]);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("field"))) {
				model.addFieldCLI(parsedChoice[2], parsedChoice[3]);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("method"))) {
				String parameters = br.readLine();
				String sep = ",";
				String[] parsedParameters = StringUtils.split(parameters, sep);
				List<String> parametersList = Arrays.asList(parsedParameters);
				classes.addMethodCLI(parsedChoice[2], parsedChoice[3], parametersList);
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
				String parameters = br.readLine();
				String sep = ",";
				String[] parsedParameters = StringUtils.split(parameters, sep);
				List<String> parametersList = Arrays.asList(parsedParameters);
				classes.deleteMethodCLI(parsedChoice[2], parsedChoice[3], parametersList);
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
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("method"))) {
				String parameters = br.readLine();
				String sep = ",";
				String[] parsedParameters = StringUtils.split(parameters, sep);
				List<String> parametersList = Arrays.asList(parsedParameters);
				String methodNameNew = br.readLine();
				model.editMethodNameCLI(parsedChoice[2], parsedChoice[3], parametersList, methodNameNew);
			}
			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel"))) {
				String parameters = br.readLine();
				String sep = ",";
				String[] parsedParameters = StringUtils.split(parameters, sep);
				List<String> parametersList = Arrays.asList(parsedParameters);
				String parametersNew = br.readLine();
				String[] parsedParametersNew = StringUtils.split(parametersNew, sep);
				List<String> parametersListNew = Arrays.asList(parsedParametersNew);
				model.editMethodParametersCLI(parsedChoice[2], parsedChoice[3], parametersList, parametersListNew);
			}
			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel")) && (parsedChoice[2].equals("dest"))) {
				classes.editRelationshipDestinationCLI(parsedChoice[3], parsedChoice[4], parsedChoice[5]);
			}
			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel")) && (parsedChoice[2].equals("type"))) {
				classes.editRelationshipTypeCLI(parsedChoice[3], parsedChoice[4], parsedChoice[5]);
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
			choice = br.readLine();
			parsedChoice = StringUtils.split(choice, seperator);
			}
	}
	
}