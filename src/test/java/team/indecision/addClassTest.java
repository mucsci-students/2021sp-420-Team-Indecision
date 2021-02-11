package team.indecision;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class addClassTest {

	@Test
	public void testAddClass() {
		Class c = new Class("test");
		UML.addClass("test");
		assertTrue(c.equals(UML.getClasses().get("test"))); //Testing that we are adding a class correctly.
		UML.addClass("test"); 
		assertTrue(UML.getClasses().size() == 1 && c.equals(UML.getClasses().get("test"))); //Testing that when we add a duplicate class we not adding it again.);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
	
		UML.addClass("test");
		assertEquals("The class test already exists.", outContent.toString().trim()); //Test that the class is deleted prints the proper message to the console.
		System.setOut(System.out); // resetting the system.setOut to default
	}

}
