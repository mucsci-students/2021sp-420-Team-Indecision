package team.indecision;

import team.indecision.Controller.CLIController;
import team.indecision.Controller.GUIController;
import team.indecision.Model.Classes;
import team.indecision.View.*;

/** This class contains main and runs the application.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public final class IndecisionApp {

	/** Constructs the view and model objects and passes them to the controllers.
	 * @param args If the program is ran with the -cli argument then the cli application is created and run if no argument is passed then the gui application is run.
	 */
	public static void main(String[] args) {
		Classes model = new Classes();
		
		if((args.length == 1) && (args[0].equals("-cli"))) {
			CLI view = new CLI();
			@SuppressWarnings("unused")
			CLIController controller = new CLIController(model, view);
		}else if (args.length == 0){
			GUI view = new GUI();
			@SuppressWarnings("unused")
			GUIController controller = new GUIController(model, view);
		}else {
			System.out.println("Invalid Input. Valid inputs are: -cli");
		}
	}
}
