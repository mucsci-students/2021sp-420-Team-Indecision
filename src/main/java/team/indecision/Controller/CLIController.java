package team.indecision.Controller;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import team.indecision.Model.Classes;
import team.indecision.View.CLI;

public class CLIController {
	private Classes model;
	private CLI view;
	
	public CLIController(Classes modelP, CLI viewP) {
		model = modelP;
		view = viewP;
		
		String choice = view.prompt();
		String seperator = " ";
		String[] parsedChoice = StringUtils.split(choice, seperator);
		
		boolean x = true;
		while (x) {
			// add
			if (parsedChoice.length == 3 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("class"))) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("field"))) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("method"))) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("rel"))) {
				// Will re-implement when we do the Command design method.
			}
			// delete
			else if (parsedChoice.length == 3 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("class"))) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("field"))) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("method"))) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("rel"))) {
				// Will re-implement when we do the Command design method.
			}
			// rename / edit
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("class"))) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("field"))) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("method")) && (parsedChoice[2].equals("name"))) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("method")) && (parsedChoice[2].equals("parameters"))) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel")) && (parsedChoice[2].equals("dest"))) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel")) && (parsedChoice[2].equals("type"))) {
				// Will re-implement when we do the Command design method.
			}
			// list
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("classes"))) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 3 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("class"))) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("rel"))) {
				// Will re-implement when we do the Command design method.
			}
			// save / load
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("save")) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("load")) {
				// Will re-implement when we do the Command design method.
			}
			// help and exit
			else if (parsedChoice.length == 1 && parsedChoice[0].equals("help")) {
				// Will re-implement when we do the Command design method.
			}
			else if (parsedChoice.length == 1 && parsedChoice[0].equals("undo")) {
				// Will re-implement when we do the Command design method.
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
}