package team.indecision;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.junit.Test;

public class ClassTest {
	
	@Test
	public void testNameConstructorAndGetName() {
		Class c = new Class ("nameTest");
		assertEquals("nameTest", c.getName());
	}

	@Test
	public void testFullConstructorAndGets() {
		String name = "nameTest";
		Set<String> attributes = new HashSet<String>();
		attributes.add("attribute1");
		attributes.add("attribute2");
		attributes.add("attribute3");
		SortedMap<String, String> relationships = new TreeMap<String, String>();
		relationships.put("class1","type1");
		relationships.put("class2","type2");
		relationships.put("class3","type3");
		Class c = new Class (name, attributes, relationships);
		assertEquals(name, c.getName());
		assertEquals(attributes, c.getAttributes());
		assertEquals(relationships, c.getRelationships());
	}
	
	@Test
	public void testSets() {
		String name = "nameTest";
		Set<String> attributes = new HashSet<String>();
		attributes.add("attribute1");
		attributes.add("attribute2");
		attributes.add("attribute3");
		SortedMap<String, String> relationships = new TreeMap<String, String>();
		relationships.put("class1","type1");
		relationships.put("class2","type2");
		relationships.put("class3","type3");
		Class c = new Class ();
		c.setName(name);
		c.setAttributes(attributes);
		c.setRelationships(relationships);
		assertEquals(name, c.getName());
		assertEquals(attributes, c.getAttributes());
		assertEquals(relationships, c.getRelationships());
	}
	
	@Test
	public void testAddAttribute() {
		Class c = new Class ();
		c.addAttribute("attribute1");
		c.getAttributes().contains("attribute1");
		assertEquals(true, c.getAttributes().contains("attribute1"));
	}
	
	@Test
	public void testPrintAttributes() {
		Set<String> attributes = new HashSet<String>();
		attributes.add("attribute1");
		attributes.add("attribute2");
		attributes.add("attribute3");
		Class c = new Class ();
		c.setAttributes(attributes);
		assertEquals(attributes.toString(), c.printAttributes());
	}
	
	@Test
	public void testAddRelationship() {
		SortedMap<String, String> relationships = new TreeMap<String, String>();
		relationships.put("class1","type1");
		relationships.put("class2","type2");
		relationships.put("class3","type3");
		Class c = new Class ();
		c.addRelationship("class1","type1");
		c.addRelationship("class2","type2");
		c.addRelationship("class3","type3");
		assertEquals(c.getRelationships().toString(), relationships.toString());
	}
	
	@Test
	public void testPrintRelationships() {
		SortedMap<String, String> relationships = new TreeMap<String, String>();
		relationships.put("class1","type1");
		relationships.put("class2","type2");
		relationships.put("class3","type3");
		Class c = new Class ();
		c.addRelationship("class1","type1");
		c.addRelationship("class2","type2");
		c.addRelationship("class3","type3");
		assertEquals(relationships.toString(), c.printRelationships());
	}
	
	@Test
	public void testToString() {
		String test = "test [attr2, attr1] {}";
		Class c = new Class ("test");
		c.addAttribute("attr1");
		c.addAttribute("attr2");
		assertEquals(c.toString(), test);
	}
	
	@Test
	public void testEquals() {
		String name = "nameTest";
		Set<String> attributes = new HashSet<String>();
		attributes.add("attribute1");
		attributes.add("attribute2");
		attributes.add("attribute3");
		SortedMap<String, String> relationships = new TreeMap<String, String>();
		relationships.put("class1","type1");
		relationships.put("class2","type2");
		relationships.put("class3","type3");
		Class a = new Class (name, attributes, relationships);
		Class b = new Class (name, attributes, relationships);
		String name1 = "nameTest";
		Set<String> attributes1 = new HashSet<String>();
		attributes.add("attribute1");
		attributes.add("attribute2");
		attributes.add("attribute3");
		SortedMap<String, String> relationships1 = new TreeMap<String, String>();
		relationships.put("class1","type1");
		relationships.put("class2","type2");
		relationships.put("class3","type3");
		Class c = new Class (name1, attributes1, relationships1);
		assertTrue(a.equals(a));
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
	}
	
}