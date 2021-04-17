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
	public void Parameter() {
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
		Method m = new Method("test");
		m.addParameter("int", "p1");
		assertEquals("test",m.getName());
		assertEquals("p1",m.getParameter("p1").getName());
		assertEquals("int",m.getParameter("p1").getType());
		m.setName("test1");
		SortedSet<Parameter> parameters = new TreeSet<Parameter>();
		Parameter p = new Parameter("string", "p2");
		parameters.add(p);
		m.setParameters(parameters);
		m.setType("int");
		assertEquals("test1",m.getName());
		assertEquals(parameters,m.getParameters());
		assertEquals("int test1 [string p2]", m.toString());
		
		Method m1 = new Method("test1");
		m1.setParameters(parameters);
		assertTrue(m.equals(m1));
		m1.setName("t1");
		assertFalse(m.equals(m1));
	}
	
	@Test
	public void Class() {
		Class c = new Class("test");
		assertEquals("test",c.getName());
		SortedSet<Parameter> p1 = new TreeSet<Parameter>();
		Method m = new Method("type","method",p1);
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
		Method m1 = new Method("type","method1",p1);
		
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
		SortedSet<Parameter> p2 = new TreeSet<Parameter>();
		p2.add(new Parameter("int", "p1"));
		Method m3 = new Method("int","m3", p2);
		c1.addMethod("int","m3", p2);
		//Method m3 = c1.addMethodParameter(c1.getMethod("m3",p2),"int","p1");

		assertEquals(true,c1.containsMethod(m3.getName(),m3.getParameters()));
		
		c1.addRelationship("rel3", "agg");
		assertEquals(true,c1.containsRelationship("rel3"));
		
		c1.deleteField("f3");
		c1.deleteMethod(m3.getName(),m3.getParameters());
		c1.deleteRelationship("rel3");
		assertEquals(false,c1.containsField("f3"));
		assertEquals(false,c1.containsMethod(m3.getName(),m3.getParameters()));
		assertEquals(false,c1.containsRelationship("rel3"));
		
		assertEquals("[int f1, int f2]",c1.printFields());
		assertEquals("[type method [], type method1 []]",c1.printMethods());
		assertEquals("[rel agg, rel1 agg]",c1.printRelationships());
		assertEquals("test2 [int f1, int f2] [type method [], type method1 []] [rel agg, rel1 agg]",c1.toString());
		
		Class c2 = new Class("test2",c1.getFields(),c1.getMethods(),c1.getRelationships(), 0, 0);;
		assertTrue(c1.equals(c2));
		
	}
	
	@Test
	public void Classes() {
		Class c = new Class("test");
		SortedSet<Parameter> p1 = new TreeSet<Parameter>();
		p1.add(new Parameter("int", "a"));
		Method m = new Method("int","method",p1);
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
