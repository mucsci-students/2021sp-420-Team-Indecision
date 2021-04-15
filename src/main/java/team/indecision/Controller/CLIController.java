package team.indecision.Controller;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import team.indecision.Command.*;
import team.indecision.Memento.History;
import team.indecision.Memento.Memento;
import team.indecision.Model.Classes;
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
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("field"))) {
				String response = executeCommand(new AddFieldCommand(model, parsedChoice[2], parsedChoice[3]));
				view.update(response);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("method"))) {
				String parameters = view.prompt();
				String sep = ",";
				String[] parsedParameters = StringUtils.split(parameters, sep);
				List<String> parametersList = Arrays.asList(parsedParameters);
				String response = executeCommand(new AddMethodCommand(model, parsedChoice[2], parsedChoice[3], parametersList));
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
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("method"))) {
				String parameters = view.prompt();
				String sep = ",";
				String[] parsedParameters = StringUtils.split(parameters, sep);
				List<String> parametersList = Arrays.asList(parsedParameters);
				String response = executeCommand(new DeleteMethodCommand(model, parsedChoice[2], parsedChoice[3], parametersList));
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
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("field"))) {
				String response = executeCommand(new EditFieldNameCommand(model, parsedChoice[2], parsedChoice[3], parsedChoice[4]));
				view.update(response);
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("method")) && (parsedChoice[2].equals("name"))) {
				String parameters = view.prompt();
				String sep = ",";
				String[] parsedParameters = StringUtils.split(parameters, sep);
				List<String> parametersList = Arrays.asList(parsedParameters);
				String methodNameNew = view.prompt();
				sep = " ";
				String[] parsedMethodNameNew = StringUtils.split(methodNameNew, sep);
				String response = executeCommand(new EditMethodNameCommand(model, parsedChoice[3], parsedChoice[4], parametersList, parsedMethodNameNew[0]));
				view.update(response);
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("method")) && (parsedChoice[2].equals("parameters"))) {
				String parameters = view.prompt();
				String sep = ",";
				String[] parsedParameters = StringUtils.split(parameters, sep);
				List<String> parametersList = Arrays.asList(parsedParameters);
				String parametersNew = view.prompt();
				String[] parsedParametersNew = StringUtils.split(parametersNew, sep);
				List<String> parametersListNew = Arrays.asList(parsedParametersNew);
				String response = executeCommand(new EditMethodParametersCommand(model, parsedChoice[3], parsedChoice[4], parametersList, parametersListNew));
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
		}
		return response;
	}
	
	/** Undoes the most recent command if their is a command to undo by converting to the backup state in the model.
	 */
	private String undo() {
		String response = "You can no longer undo.";
		if (!history.isEmptyUndo()) {
			history.undo();
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