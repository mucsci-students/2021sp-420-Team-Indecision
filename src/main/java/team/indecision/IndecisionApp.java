package team.indecision;

import java.io.IOException;
import java.io.InputStreamReader;
import team.indecision.Controller.CLIController;
import team.indecision.Controller.GUIController;
import team.indecision.Model.Classes;
import team.indecision.View.*;

public final class IndecisionApp {

	public static void main(String[] args) {
		
		Classes model = new Classes();
		
		if((args.length == 1) && (args[0].equals("-cli"))) {
			CLI view = new CLI(new InputStreamReader(System.in));
			CLIController controller = new CLIController(model, view);
		}else if (args.length == 0){
			GUI view = new GUI();
			GUIController controller = new GUIController(model, view);
		}else {
			System.out.println("Invalid Input.");
		}
	}
}
