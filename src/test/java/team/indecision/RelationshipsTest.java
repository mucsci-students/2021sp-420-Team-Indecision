package team.indecision;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

public class RelationshipsTest {

	@Test
	public void testAddRelationship() {
        String name = "nameTest";
        Set<String> attributes = new HashSet<String>();
        attributes.add("attribute1");
        attributes.add("attribute2");
        attributes.add("attribute3");
        SortedMap<String, String> relationships = new TreeMap<String, String>();
        relationships.put("class1","type1");
        Class c = new Class (name, attributes, relationships);
        UML.addClass(name);
        UML.addRelationship("nameTest", "class1", "type1");
        assertEquals(c.getRelationships(),UML.getClasses().get("nameTest").getRelationships());  
    }
	
	@Test
	public void testAddRelationshipWhenClassDoesNotExist() {	
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		UML.addRelationship("asdjhc", "class1", "type1");
		assertEquals("The class asdjhc does not exist.", outContent.toString().trim()); 
		System.setOut(System.out); // resetting the system.setOut to default
    }
	 
	@Test
	public void testAddRelationshipWhenRelationShipAlreadyExists() {
        String name = "nameTest";
        Set<String> attributes = new HashSet<String>();
        attributes.add("attribute1");
        attributes.add("attribute2");
        attributes.add("attribute3");
        SortedMap<String, String> relationships = new TreeMap<String, String>();
        relationships.put("class1","type1");
        UML.addClass(name);
        UML.addRelationship("nameTest", "class1", "type1");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		UML.addRelationship("nameTest", "class1", "type1");
		assertEquals("A relationship with this class nameTest already exists.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
    }
		 
	/*
	@Test
	public void testDeleteRelationship() {
        String name = "nameTest";
        Set<String> attributes = new HashSet<String>();
        attributes.add("attribute1");
        attributes.add("attribute2");
        attributes.add("attribute3");
        SortedMap<String, String> relationships = new TreeMap<String, String>();
        relationships.put("class1","type1");
        Class c = new Class (name, attributes, relationships);
        UML.addClass(name);
        UML.addRelationship("nameTest", "class1", "type1");
        assertEquals(c.getRelationships(),UML.getClasses().get("nameTest").getRelationships());
    }
    */
}
