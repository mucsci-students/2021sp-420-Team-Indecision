package team.indecision.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
			// This will need further work to implement adding a list of parameters.
//			else if (parsedChoice.length == 4 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("method"))) {
//				classes.addMethod(parsedChoice[2], parsedChoice[3]);
//			}
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
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("method"))) {
//				classes.deleteMethod(parsedChoice[2], parsedChoice[3], parsedChoice[4]);
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
		// 	This will need further work to implement adding a list of parameters.
//			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("method"))) {
//				classes.editMethodName(parsedChoice[2], parsedChoice[3], parsedChoice[4], parsedChoice[5]);
//			}
			// This will need further work to implement adding a list of parameters.
//			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel"))) {
//			classes.editMethodType(parsedChoice[2], parsedChoice[3], parsedChoice[4], parsedChoice[5]);
//			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel"))) {
				classes.editRelationshipDestinationCLI(parsedChoice[2], parsedChoice[3], parsedChoice[4]);
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("method"))) {
				classes.editRelationshipTypeCLI(parsedChoice[2], parsedChoice[3], parsedChoice[4]);
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