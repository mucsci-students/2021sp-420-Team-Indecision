package team.indecision;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

public class AttributesTest {
/*
	@Test
	public void testSingleAddAttribute() throws IOException {
        Class classTest = new Class("class");
        classTest.addAttribute("attribute");
        
        UML.addClass("class");
        
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		
        UML.addAttribute("class", "attribute");

        assertTrue(UML.getClasses().get("class").equals(classTest));
		assertEquals("You have created a new attribute named: attribute", outContent.toString().trim()); //Test that the class is deleted prints the proper message to the console.
		
		System.setOut(System.out); // resetting the system.setOut to default
        }
	
	@Test
	public void testSingleDeleteAttribute() throws IOException {
        Class classTest = new Class("class");
        
        UML.addClass("class");
        UML.addAttribute("class", "attribute");
        
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
        UML.deleteAttribute("class", "attribute");

        assertTrue(UML.getClasses().get("class").equals(classTest));
		assertEquals("You have deleted the attribute named: attribute", outContent.toString().trim()); 
		System.setOut(System.out);
        }
	
	@Test
	public void testSingleRenameAttribute() throws IOException {
        Class classTest = new Class("class");
        classTest.addAttribute("attributeRename");
        
        UML.addClass("class");
        UML.addAttribute("class", "attribute");
        
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
        UML.renameAttribute("class", "attribute", "attributeRename");
        

        assertTrue(UML.getClasses().get("class").equals(classTest));
		assertEquals("You have renamed attribute to attributeRename", outContent.toString().trim()); 
		System.setOut(System.out);
		}

	@Test
	public void testAddAttributeAlreadyExists() throws IOException {
		UML.addClass("class");
		UML.addAttribute("class", "a");
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); 
		
		UML.addAttribute("class", "a");
		
		assertEquals("Error: That attribute already exists.", outContent.toString().trim()); 
		System.setOut(System.out);
	}
	
	@Test
	public void testAddAttributeClassDoesNotExist() throws IOException {
		UML.addClass("class");
		UML.addAttribute("class", "a");
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		
		UML.addAttribute("classNotexitst", "a");
		
		assertEquals("Error: That class does not exist.", outContent.toString().trim()); 
		System.setOut(System.out); 
	}
	
	@Test
	public void testDeleteAttributeAlreadyExists() throws IOException {
		UML.addClass("class");
		UML.addAttribute("class", "a");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		
		System.setOut(new PrintStream(outContent)); 
		
		UML.deleteAttribute("class", "b");
		
		assertEquals("Error: Attribute does not exist.", outContent.toString().trim()); 
		System.setOut(System.out); 
	}
	
	@Test
	public void testDeleteAttributeClassDoesNotExist() throws IOException {
		UML.addClass("class");
		UML.addAttribute("class", "a");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		
		System.setOut(new PrintStream(outContent)); 
		
		UML.deleteAttribute("classNotexitst", "a");
		
		assertEquals("Error: That class does not exist.", outContent.toString().trim()); 
		System.setOut(System.out); 
	}
	
	@Test
	public void testRenameAttributeAlreadyExists() throws IOException {
		UML.addClass("class");
		UML.addAttribute("class", "a");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		
		System.setOut(new PrintStream(outContent)); 
		
		UML.renameAttribute("class", "b", "a");
		
		
		assertEquals("Error: Conflicting attribute names.", outContent.toString().trim()); 
		System.setOut(System.out); 
	}
	@Test
	public void testRenameAttributeClassDoesNotExist() throws IOException {
		UML.addClass("class");
		UML.addAttribute("class", "a");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		
		System.setOut(new PrintStream(outContent)); 
		
		UML.renameAttribute("class1", "b", "a");
		
		
		assertEquals("That class does not exist.", outContent.toString().trim()); 
		System.setOut(System.out); 
	}
	
	
	
	@Test
	public void testMultipleAddAttribute() throws IOException {
        Class test = new Class("class1");
        test.addAttribute("a");
        test.addAttribute("b");
        test.addAttribute("c");
        test.addAttribute("d");

		UML.addClass("class1");
		UML.addAttribute("class1", "a");
		UML.addAttribute("class1", "b");
		UML.addAttribute("class1", "c");
		UML.addAttribute("class1", "d");
		
        assertTrue(UML.getClasses().get("class1").equals(test));

	}
	
	@Test
	public void testMultipleAddAttributeWithMultipleClasses() throws IOException {
        Class class1 = new Class("class1");
        class1.addAttribute("a");
        class1.addAttribute("b");
        class1.addAttribute("c");
        class1.addAttribute("d");

        Class class2 = new Class("class2");
        class2.addAttribute("e");
        class2.addAttribute("f");
        class2.addAttribute("g");
        class2.addAttribute("h");
		
        
		UML.addClass("class1");
		UML.addAttribute("class1", "a");
		UML.addAttribute("class1", "b");
		UML.addAttribute("class1", "c");
		UML.addAttribute("class1", "d");
		
		UML.addClass("class2");
		UML.addAttribute("class2", "e");
		UML.addAttribute("class2", "f");
		UML.addAttribute("class2", "g");
		UML.addAttribute("class2", "h");
		
        assertTrue(UML.getClasses().get("class1").equals(class1) && UML.getClasses().get("class2").equals(class2));
	}
	
*/	
}
