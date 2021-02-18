package team.indecision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.lang3.StringUtils;


/** A text-based REPL program for creating UML models.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public final class UML {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Classes classes = new Classes();
		
		System.out.println("Welcome to Team Indecision's UML Tool");
		System.out.println("Type 'help' to see a list of commands or 'exit' to close the program.");
		System.out.print("\nChoose one of the above menu items: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String choice = br.readLine();
		
		String seperator = " ";
		String[] parsedChoice = StringUtils.split(choice, seperator);
		
		boolean x = true;

		while (x) {
			// add

			if (parsedChoice.length == 3 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("class"))) {
				classes.addClass(parsedChoice[2]);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("field"))) {
//				classes.addField(parsedChoice[2], parsedChoice[3]);
			}
			// This will need further work to implement adding a list of parameters.
//			else if (parsedChoice.length == 4 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("method"))) {
//				classes.addMethod(parsedChoice[2], parsedChoice[3]);
//			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("rel"))) {
				classes.addRelationship(parsedChoice[2], parsedChoice[3], parsedChoice[4]);
			}
			// delete
			else if (parsedChoice.length == 3 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("class"))) {
				classes.deleteClass(parsedChoice[2]);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("field"))) {
//				classes.deleteField(parsedChoice[2], parsedChoice[3]);
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("method"))) {
//				classes.deleteMethod(parsedChoice[2], parsedChoice[3], parsedChoice[4]);
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("rel"))) {
				classes.deleteRelationship(parsedChoice[2], parsedChoice[3], parsedChoice[4]);
			}
			// rename / edit
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("rename") && (parsedChoice[1].equals("class"))) {
				classes.renameClass(parsedChoice[2], parsedChoice[3]);
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("field"))) {
//				classes.editField(parsedChoice[2], parsedChoice[3], parsedChoice[4]);
			}
			// This will need further work to implement adding a list of parameters.
//			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("method"))) {
//				classes.editMethodName(parsedChoice[2], parsedChoice[3], parsedChoice[4], parsedChoice[5]);
//			}
			// This will need further work to implement adding a list of parameters.
//			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel"))) {
//				classes.editMethodType(parsedChoice[2], parsedChoice[3], parsedChoice[4], parsedChoice[5]);
//			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel"))) {
				classes.editRelationshipDestination(parsedChoice[2], parsedChoice[3], parsedChoice[4]);
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("method"))) {
				classes.editRelationshipType(parsedChoice[2], parsedChoice[3], parsedChoice[4]);
			}
			// list
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("classes"))) {
				classes.listClasses();
			}
			else if (parsedChoice.length == 3 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("class"))) {
				classes.listClass(parsedChoice[2]);
			}
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("rel"))) {
				classes.listRelationships();
			}
			// save / load
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("save")) {
				   classes.saveJSON(parsedChoice[1]);
			}
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("load")) {
				   classes.loadJSON(parsedChoice[1]);
			}
			// help and exit
			else if (parsedChoice.length == 1 && parsedChoice[0].equals("help")) {
				   classes.help();
			}
			else if (parsedChoice.length == 1 && parsedChoice[0].equals("exit")){
				   break;
			}
			else {
				System.out.println("Please enter a valid selection");			
			}
			System.out.print("Choose another menu item: ");
			choice = br.readLine();
			parsedChoice = StringUtils.split(choice, seperator);
		}

	}
	
}
