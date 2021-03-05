package team.indecision;

import java.io.IOException;
import team.indecision.Model.Classes;
import team.indecision.View.*;

public final class IndecisionApp {

	public static void main(String[] args) throws IOException {
		
		Classes model = new Classes();
		
		if((args.length == 1) && (args[0].equals("-cli"))) {
			UML uml = new UML(model);
		}else if (args.length == 0){
			GUI gui = new GUI(model);
		}else {
			System.out.println("Invalid Input.");
		}
	}
}