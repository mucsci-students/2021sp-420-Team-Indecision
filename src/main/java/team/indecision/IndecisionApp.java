package team.indecision;

import java.io.IOException;

import team.indecision.*;

public final class IndecisionApp {

	public static void main(String[] args) throws IOException {
		
		Classes classes = new Classes();
		GUI gui;
		UML uml;
		
		if((args.length == 1) && (args[0].equals("-cli"))) {
			uml = new UML(classes);
		}else if (args.length == 0){
			gui = new GUI(classes);
		}else {
			System.out.println("Invalid Input.");
		}
	}
}