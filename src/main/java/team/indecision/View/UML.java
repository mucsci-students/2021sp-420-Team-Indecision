package team.indecision.View;

import team.indecision.Model.Classes;

import java.io.IOException;

import team.indecision.Controller.UMLController;

/** A text-based REPL program for creating UML models.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class UML {

	private UMLController controller;
	
	public UML(Classes modelP) throws IOException {
		System.out.println("Welcome to Team Indecision's UML Tool");
		System.out.println("Type 'help' to see a list of commands or 'exit' to close the program.");
		
		controller = new UMLController(modelP);
		
	}

	

}
