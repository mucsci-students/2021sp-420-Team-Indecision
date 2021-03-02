package team.indecision;

import java.io.IOException;

import team.indecision.*;

public final class IndecisionApp {

	public static void main(String[] args) throws IOException {
		
		Classes classes = new Classes();
		
		if((args.length == 1) && (args[0].equals("-cli"))) {
			UML uml = new UML(classes);
		}else if (args.length == 0){
			GUI gui = new GUI(classes);
		}else {
			System.out.println("Invalid Input.");
		}
	}
}