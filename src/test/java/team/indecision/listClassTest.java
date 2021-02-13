package team.indecision;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

public class listClassTest {

	@Test
	public void testListClass() {
		String name = "nameTest";
		Set<String> attributes = new HashSet<String>();
		attributes.add("attribute1");
		SortedMap<String, String> relationships = new TreeMap<String, String>();
		relationships.put("class1","type1");
		Class c = new Class (name, attributes, relationships);
		UML.addClass(name);
		UML.addAttribute(name, "attribute1");
		UML.addRelationship(name, "class1", "type1");
		UML.listClass(name);
		
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		UML.listClass(name);
		System.setOut(System.out); // resetting the system.setOut to default
		assertEquals(c.toString(),outContent.toString().trim());
	}

}
