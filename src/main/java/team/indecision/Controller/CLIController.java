package team.indecision.Controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import team.indecision.Command.*;
import team.indecision.Memento.History;
import team.indecision.Memento.Memento;
import team.indecision.Model.*;
import team.indecision.View.CLI;

/** This class represents the controller for the CLI application.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class CLIController {

	//Stores the model that the controller will act on.
	private Classes model;
	//Stores the view that the controller will update.
	private CLI view;
	//Stores the history object that the controller uses to perform undo and redo.
	private History history;
	
	/** Constructs a CLI controller. Listens for and parses user input and then runs commands based on the user input.
	 * @param modelP The model to be acted on
	 * @param viewP The view to be updated.
	 */
	public CLIController(Classes modelP, CLI viewP) {
		model = modelP;
		view = viewP;
		history = new History();
		
		String choice = view.prompt();
		String seperator = " ";
		String[] parsedChoice = StringUtils.split(choice, seperator);
		
		boolean x = true;
		while (x) {
			// add
			if (parsedChoice.length == 3 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("class"))) {
				String response = executeCommand(new AddClassCommand(model, parsedChoice[2]));
				view.update(response);
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("field"))) {
				String response = executeCommand(new AddFieldCommand(model, parsedChoice[2], parsedChoice[3], parsedChoice[4]));
				view.update(response);
			}
			else if (parsedChoice.length >= 5 && !(parsedChoice.length % 2 == 0) && parsedChoice[0].equals("add") && (parsedChoice[1].equals("method"))) {
				List<String> parametersList = Arrays.asList(parsedChoice);
				SortedSet<Parameter> parametersSet = new TreeSet<Parameter>();
				for(int i = 5; i < parametersList.size(); i = i + 2) {
					parametersSet.add(new Parameter(parametersList.get(i),parametersList.get(i+1)));
				}
				String response = executeCommand(new AddMethodCommand(model, parsedChoice[2], parsedChoice[3], parsedChoice[4], parametersSet));
				view.update(response);
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("rel"))) {
				String response = executeCommand(new AddRelationshipCommand(model, parsedChoice[2], parsedChoice[3], parsedChoice[4]));
				view.update(response);
			}
			// delete
			else if (parsedChoice.length == 3 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("class"))) {
				String response = executeCommand(new DeleteClassCommand(model, parsedChoice[2]));
				view.update(response);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("field"))) {
				String response = executeCommand(new DeleteFieldCommand(model, parsedChoice[2], parsedChoice[3]));
				view.update(response);
			}
			else if (parsedChoice.length >= 4 && (parsedChoice.length % 2 == 0) && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("method"))) {
				List<String> parametersList = Arrays.asList(parsedChoice);
				SortedSet<Parameter> parametersSet = new TreeSet<Parameter>();
				for(int i = 4; i < parametersList.size(); i = i + 2) {
					parametersSet.add(new Parameter(parametersList.get(i),parametersList.get(i+1)));
				}
				String response = executeCommand(new DeleteMethodCommand(model, parsedChoice[2], parsedChoice[3], parametersSet));
				view.update(response);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("rel"))) {
				String response = executeCommand(new DeleteRelationshipCommand(model, parsedChoice[2], parsedChoice[3]));
				view.update(response);
			}
			// rename / edit
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("class"))) {
				String response = executeCommand(new EditClassNameCommand(model, parsedChoice[2], parsedChoice[3]));
				view.update(response);
			}
			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("field")) &&  (parsedChoice[2].equals("name"))) {
				String response = executeCommand(new EditFieldNameCommand(model, parsedChoice[3], parsedChoice[4], parsedChoice[5]));
				view.update(response);
			}
			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("field")) &&  (parsedChoice[2].equals("type"))) {
				String response = executeCommand(new EditFieldTypeCommand(model, parsedChoice[3], parsedChoice[4], parsedChoice[5]));
				view.update(response);
			}
			else if (parsedChoice.length >= 5 && !(parsedChoice.length % 2 == 0) && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("method")) && (parsedChoice[2].equals("name"))) {
				List<String> parametersList = Arrays.asList(parsedChoice);
				SortedSet<Parameter> parametersSet = new TreeSet<Parameter>();
				String newMethodName = "";
				for(int i = 5; i < parametersList.size(); i = i + 2) {
					if (parametersList.get(i).equals("/")) {
						for(i++; i < parametersList.size(); i = i + 2) {
							   newMethodName = parametersList.get(i);
							}
					} 
					else {
						parametersSet.add(new Parameter(parametersList.get(i),parametersList.get(i+1)));
					}
				}
				String response = executeCommand(new EditMethodNameCommand(model, parsedChoice[3], parsedChoice[4], parametersSet, newMethodName));
				view.update(response);
			}
			else if (parsedChoice.length >= 5 && !(parsedChoice.length % 2 == 0) && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("method")) && (parsedChoice[2].equals("type"))) {
				List<String> parametersList = Arrays.asList(parsedChoice);
				SortedSet<Parameter> parametersSet = new TreeSet<Parameter>();
				String newMethodType = "";
				for(int i = 5; i < parametersList.size(); i = i + 2) {
					if (parametersList.get(i).equals("/")) {
						for(i++; i < parametersList.size(); i = i + 2) {
							newMethodType = parametersList.get(i);
							}
					} 
					else {
						parametersSet.add(new Parameter(parametersList.get(i),parametersList.get(i+1)));
					}
				}
				String response = executeCommand(new EditMethodTypeCommand(model, parsedChoice[3], parsedChoice[4], parametersSet, newMethodType));
				view.update(response);
			}
			else if (parsedChoice.length >= 5 && (parsedChoice.length % 2 == 0) && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("method")) && (parsedChoice[2].equals("parameters"))) {
				List<String> parametersList = Arrays.asList(parsedChoice);
				SortedSet<Parameter> parametersSet = new TreeSet<Parameter>();
				SortedSet<Parameter> parametersSetNew = new TreeSet<Parameter>();
				for(int i = 5; i < parametersList.size(); i = i + 2) {
					if (parametersList.get(i).equals("/")) {
						for(i++; i < parametersList.size(); i = i + 2) {
							   parametersSetNew.add(new Parameter(parametersList.get(i),parametersList.get(i+1)));
							}
					} 
					else {
						parametersSet.add(new Parameter(parametersList.get(i),parametersList.get(i+1)));
					}
				}
				String response = executeCommand(new EditMethodParametersCommand(model, parsedChoice[3], parsedChoice[4], parametersSet, parametersSetNew));
				view.update(response);
			}
			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel")) && (parsedChoice[2].equals("dest"))) {
				String response = executeCommand(new EditRelationshipDestinationCommand(model, parsedChoice[3], parsedChoice[4], parsedChoice[5]));
				view.update(response);
			}
			else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel")) && (parsedChoice[2].equals("type"))) {
				String response = executeCommand(new EditRelationshipTypeCommand(model, parsedChoice[3], parsedChoice[4], parsedChoice[5]));
				view.update(response);
			}
			// list
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("classes"))) {
				String response = executeCommand(new ListClassesCommand(model));
				view.update(response);
			}
			else if (parsedChoice.length == 3 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("class"))) {
				String response = executeCommand(new ListClassCommand(model, parsedChoice[2]));
				view.update(response);
			}
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("rel"))) {
				String response = executeCommand(new ListRelationshipsCommand(model));
				view.update(response);
			}
			// save / load
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("save")) {
				String response = executeCommand(new SaveJSONCommand(model, parsedChoice[1]));
				view.update(response);
			}
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("load")) {
				String response = executeCommand(new LoadJSONCommand(model, parsedChoice[1]));
				view.update(response);
			}
			// help and exit
			else if (parsedChoice.length == 1 && parsedChoice[0].equals("help")) {
				String response = executeCommand(new HelpCommand());
				view.update(response);
			}
			else if (parsedChoice.length == 1 && parsedChoice[0].equals("undo")) {
				String response = this.undo();
				view.update(response);
			}
			else if (parsedChoice.length == 1 && parsedChoice[0].equals("redo")) {
				String response = this.redo();
				view.update(response);
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
	
	/** Executes a command object. This method also creates a deep copy and saves it to the backup field of the model and pushes the Command and a Memento onto the History stack.
	 * @return A string that represents the outcome of the execution.
	 */
	private String executeCommand(Command command) {
		Classes deepCopy = (Classes) org.apache.commons.lang.SerializationUtils.clone(model);
		model.setBackup(deepCopy.getClasses());
		String response = command.execute();
		if (command.getStateChange()) {
			history.pushUndo(command, new Memento(model));
			view.setCompleter(model);
		}
		return response;
	}
	
	/** Undoes the most recent command if their is a command to undo by converting to the backup state in the model.
	 */
	private String undo() {
		String response = "You can no longer undo.";
		if (!history.isEmptyUndo()) {
			history.undo();
			view.setCompleter(model);
			response = "The last action has been undone.";
		}
		return response;
	}
	
	/** Redoes the most recent command if their is a command to redo.
	 */
	private String redo() {
		String response = "You can no longer redo.";
		if (!history.isEmptyRedo()) {
			Command c = history.redo();
			response = executeCommand(c);
		}
		return response;
	}
	
}