package team.indecision;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.SortedMap;
import org.junit.Test;

public class ClassesTest {

	@Test
	public void addFieldTest() {
		Class c = new Class("test");
		c.addField("f");
		Classes classes = new Classes();
		classes.addClass("test");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.addField("test","f");
		assertEquals("The field f has been added to the class test.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	
	@Test
	public void addFieldNoExistingClassTest() {
		Classes classes = new Classes();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.addField("test","f");
		assertEquals("The class test does not exist.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
	}
	
	@Test
	public void addFieldDuplicateTest() {
		Class c = new Class("test");
		c.addField("f");
		Classes classes = new Classes();
		classes.addClass("test");
		classes.addField("test","f");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.addField("test","f");
		assertEquals("The field f already exists with the class test.", outContent.toString().trim()); 
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	
	@Test
	public void deleteFieldTest() {
		Class c = new Class("test");
		Classes classes = new Classes();
		classes.addClass("test");
		classes.addField("test","f");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.deleteField("test", "f");
		assertEquals("The field f has been deleted from class test.", outContent.toString().trim()); 
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	
	@Test
	public void deleteFieldNoExistingClassTest() {
		Classes classes = new Classes();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.deleteField("test","f");
		assertEquals("The class test does not exist.", outContent.toString().trim()); 
		System.setOut(System.out); // resetting the system.setOut to default
	}
	
	@Test
	public void deleteFieldNoExistingFieldTest() {
		Classes classes = new Classes();
		classes.addClass("test");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.deleteField("test","f");
		assertEquals("The field f does not exist with the class test.", outContent.toString().trim()); 
		System.setOut(System.out); // resetting the system.setOut to default
	}
	
	@Test
	public void editFieldTest() {
		Class c = new Class("test");
		c.addField("f1");
		Classes classes = new Classes();
		classes.addClass("test");
		classes.addField("test", "f");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.editField("test","f","f1");
		assertEquals("The field f has been renamed to f1.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	
	@Test
	public void editFieldTestNoExistingClass() {
		Classes classes = new Classes();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.editField("test","f","f1");
		assertEquals("The class test does not exist.", outContent.toString().trim()); 
		System.setOut(System.out); // resetting the system.setOut to default
	}
	
	@Test
	public void editFieldTestFieldNameDoesNotExist() {
		Class c = new Class("test");
		c.addField("f1");
		Classes classes = new Classes();
		classes.addClass("test");
		classes.addField("test", "f1");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.editField("test","f2","f");
		assertEquals("The field f2 does not exist with the class test.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	
	@Test
	public void editFieldTestNewFieldNameAlreadyExists() {
		Class c = new Class("test");
		c.addField("f");
		c.addField("f1");
		Classes classes = new Classes();
		classes.addClass("test");
		classes.addField("test", "f");
		classes.addField("test", "f1");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.editField("test","f","f1");
		assertEquals("The field f1 already exists with the class test.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}

}
