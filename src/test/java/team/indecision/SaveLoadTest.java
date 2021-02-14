package team.indecision;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.io.FileUtils;

import org.junit.Test;

public class SaveLoadTest {

	
	@Test
	public void testSave() throws IOException {
		UML.addClass("test");
		UML.addAttribute("test", "attr");
		UML.addAttribute("test", "attr1");
		UML.addRelationship("test", "rel", "type");
		UML.addRelationship("test", "rel1", "type");
		UML.addClass("test1");
		UML.addClass("test2");
		UML.addRelationship("test2", "rel", "type");
		UML.addAttribute("test2", "attr");
		UML.save("classes");
		UML.listClasses();
		File classes = Paths.get("classes").toFile();
		File classesTest = Paths.get("classesTest").toFile();
		assertTrue(FileUtils.contentEquals(classes,classesTest));
		// json is valid. It was checked using https://jsonlint.com.
	}
	
	@Test
	public void testLoad() {
		
		String name = "test";
		Set<String> attributes = new HashSet<String>();
		attributes.add("attr");
		attributes.add("attr1");
		SortedMap<String, String> relationships = new TreeMap<String, String>();
		relationships.put("rel","type");
		relationships.put("rel1","type");
		Class c = new Class (name, attributes, relationships);
		//System.out.println(c.toString());
		
		String name1 = "test1";
		Class c1 = new Class (name1);
		//System.out.println(c1.toString());
		
		String name2 = "test2";
		Set<String> attributes2 = new HashSet<String>();
		attributes2.add("attr");
		SortedMap<String, String> relationships2 = new TreeMap<String, String>();
		relationships2.put("rel","type");
		Class c2 = new Class (name2, attributes2, relationships2);
		//System.out.println(c2.toString());
		
		SortedMap<String, Class> classes = new TreeMap<String, Class>();
		classes.put(name, c);
		classes.put(name1, c1);
		classes.put(name2, c2);
		
		File classesTest = Paths.get("classesTest").toFile();
		System.out.println(classesTest.getPath());
		UML.load(classesTest);
		
		/*
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		UML.load(classesTest);
		assertEquals("test [attr, attr1] {rel=type, rel1=type}\r\n" + 
				"test1 [] {}\r\n" + 
				"test2 [attr] {rel=type}", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		*/
		
	}
	
	@Test
	public void testLoadWhenFileDoesNotExist() {
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		UML.load("classesasdhc[");
		assertEquals("Not valid json or file does not exist.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		
	}

}
