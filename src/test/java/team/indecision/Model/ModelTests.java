package team.indecision.Model;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

public class ModelTests {

	@Test
	public void Relationship() {
		Relationship r = new Relationship("test","composition");
		assertEquals("test",r.getDestination());
		assertEquals("composition",r.getType());
		r.setDestination("test1");
		r.setType("agg");
		assertEquals("test1",r.getDestination());
		assertEquals("agg",r.getType());
		assertEquals("test1 agg", r.toString());
		Relationship r1 = new Relationship("test1","agg");
		assertTrue(r.equals(r1));
		r1.setDestination("test2");
		assertFalse(r.equals(r1));
	}
	
	@Test
	public void Field() {
		Field f = new Field("string","test");
		assertEquals("test",f.getName());
		assertEquals("string",f.getType());
		f.setName("test1");
		f.setType("int");
		assertEquals("test1",f.getName());
		assertEquals("int",f.getType());
		assertEquals("int test1", f.toString());
		Field f1 = new Field("int","test1");
		assertTrue(f.equals(f1));
		f1.setName("test2");
		assertFalse(f.equals(f1));
	}
	
	@Test
	public void Method() {
		List<String> p = new ArrayList<String>();
		p.add("x");
		p.add("y");
		List<String> p1 = new ArrayList<String>();
		p1.add("a");
		p1.add("z");
		Method m = new Method("test",p);
		assertEquals("test",m.getName());
		assertEquals(p,m.getParameters());
		m.setName("test1");
		m.setParameters(p1);
		assertEquals("test1",m.getName());
		assertEquals(p1,m.getParameters());
		assertEquals("test1 [a, z]", m.toString());
		Method m1 = new Method("test",p);
		assertFalse(m.equals(m1));
		m1.setName("test1");
		m1.setParameters(p1);
		assertTrue(m.equals(m1));
	}
	
	@Test
	public void Class() {
		Class c = new Class("test");
		assertEquals("test",c.getName());
		List<String> p1 = new ArrayList<String>();
		p1.add("a");
		p1.add("z");
		Method m = new Method("method",p1);
		SortedSet<Method> methods = new TreeSet<Method>();
		methods.add(m);
		Field f = new Field("int","f1");
		SortedSet<Field> fields = new TreeSet<Field>();
		fields.add(f);
		Relationship r = new Relationship("rel","agg");
		SortedSet<Relationship> relationships = new TreeSet<Relationship>();
		relationships.add(r);
		
		Class c1 = new Class("test1",fields,methods,relationships, 0 , 0);
		assertTrue(c1.getFields().equals(fields));
		assertTrue(c1.getMethods().equals(methods));
		assertTrue(c1.getRelationships().equals(relationships));
		assertTrue(c1.getName().equals("test1"));
		
		Field f1 = new Field("int","f2");
		Relationship r1 = new Relationship("rel1","agg");
		Method m1 = new Method("method1",p1);
		
		fields.add(f1);
		methods.add(m1);
		relationships.add(r1);
		
		c1.setFields(fields);
		c1.setMethods(methods);
		c1.setName("test2");
		c1.setRelationships(relationships);
		
		assertTrue(c1.getFields().equals(fields));
		assertTrue(c1.getMethods().equals(methods));
		assertTrue(c1.getRelationships().equals(relationships));
		assertTrue(c1.getName().equals("test2"));
		
		assertTrue(c1.getField("f2").equals(f1));
		assertTrue(c1.getMethod("method1",p1).equals(m1));
		assertTrue(c1.getRelationship("rel1").equals(r1));
		
		assertEquals(false,c1.containsField("f4"));
		assertEquals(false,c1.containsMethod("f4",p1));
		assertEquals(false,c1.containsRelationship("test"));
		assertEquals(true,c1.containsField("f2"));
		assertEquals(true,c1.containsMethod("method1",p1));
		assertEquals(true,c1.containsRelationship("rel1"));
		
		c1.addField("int","f3");
		assertEquals(true,c1.containsField("f3"));
		c1.addMethod("m3", p1);
		assertEquals(true,c1.containsMethod("m3",p1));
		c1.addRelationship("rel3", "agg");
		assertEquals(true,c1.containsRelationship("rel3"));
		
		c1.deleteField("f3");
		c1.deleteMethod("m3", p1);
		c1.deleteRelationship("rel3");
		assertEquals(false,c1.containsField("f3"));
		assertEquals(false,c1.containsMethod("m3",p1));
		assertEquals(false,c1.containsRelationship("rel3"));
		
		assertEquals("[int f1, int f2]",c1.printFields());
		assertEquals("[method [a, z], method1 [a, z]]",c1.printMethods());
		assertEquals("[rel agg, rel1 agg]",c1.printRelationships());
		assertEquals("test2 [int f1, int f2] [method [a, z], method1 [a, z]] [rel agg, rel1 agg]",c1.toString());
		
		Class c2 = new Class("test2",c1.getFields(),c1.getMethods(),c1.getRelationships(), 0, 0);;
		assertTrue(c1.equals(c2));
		
	}
	
	@Test
	public void Classes() {
		Class c = new Class("test");
		List<String> p1 = new ArrayList<String>();
		p1.add("a");
		p1.add("z");
		Method m = new Method("method",p1);
		SortedSet<Method> methods = new TreeSet<Method>();
		methods.add(m);
		Field f = new Field("int","f1");
		SortedSet<Field> fields = new TreeSet<Field>();
		fields.add(f);
		Relationship r = new Relationship("rel","agg");
		SortedSet<Relationship> relationships = new TreeSet<Relationship>();
		relationships.add(r);
		Class c1 = new Class("test1",fields,methods,relationships, 0 , 0);
		
		SortedMap<String, Class> classes = new TreeMap<String, Class>();
		classes.put("test", c);
		classes.put("test1", c1);
		
		Classes model = new Classes(classes);
		assertEquals(classes,model.getClasses());
		
		Classes model1 = new Classes();
		model1.setClasses(classes);
		assertEquals(classes, model1.getClasses());
		
		Classes model2 = new Classes (classes,classes);
		assertEquals(classes,model2.getBackup());
		
		Classes model3 = new Classes();
		model3.setBackup(classes);
		assertEquals(classes, model3.getBackup());
	}
	
}
