package team.indecision;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.SortedMap;
import org.junit.Test;

public class ClassesTest {

	@Test
	public void addClassTest() {
		Class c = new Class("test");
		Classes classes = new Classes();	
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.addClass("test");
		assertEquals("You have created a new class named: test", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	
	@Test
	public void addClassTestDuplicate() {
		Class c = new Class("test");
		Classes classes = new Classes();
		classes.addClass("test");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.addClass("test");
		assertEquals("The class test already exists.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	
	@Test
	public void deleteClassTest() {
		Class c = new Class("test");
		Classes classes = new Classes();
		classes.addClass("test");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.deleteClass("test");
		assertEquals("The class test has been deleted.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test") == null);
	}
	
	@Test
	public void deleteClassTestClassDoesNotExist() {
		Class c = new Class("test");
		Classes classes = new Classes();
		classes.addClass("test");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.deleteClass("test1");
		assertEquals("The class test1 does not exist.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
	}
	
	@Test
	public void renameClassTest() {
		Class c = new Class("test1");
		Classes classes = new Classes();
		classes.addClass("test");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.renameClass("test", "test1");
		assertEquals("You have renamed the class test to test1", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test") == null);
		assertTrue(m.get("test1").equals(c));
	}
	
	@Test
	public void renameClassTestNoClassExists() {
		Classes classes = new Classes();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.renameClass("test", "test1");
		assertEquals("The class test does not exist.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test") == null);
	}
	
	@Test
	public void renameClassTestClassRenameAlreadyExists() {
		Classes classes = new Classes();
		classes.addClass("test");
		classes.addClass("test1");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.renameClass("test", "test1");
		assertEquals("The new class name test1 already exists.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
	}
	
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
	
	@Test
    public void addRelationshipTest() {
        Class c = new Class("test");
        c.addRelationship("r","r1");
        Classes classes = new Classes();
        classes.addClass("test");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.addRelationship("test","r","r1" );
        assertEquals("The test has created a new relationship with class: r of type r1", outContent.toString().trim());
        System.setOut(System.out); // resetting the system.setOut to default
        SortedMap <String,Class> m = classes.getClasses();
        assertTrue(m.get("test").equals(c));

    }
	@Test
    public void testAddRelationshipWhenClassDoesNotExist() {
        Classes classes = new Classes();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.addRelationship("abcde", "class1", "type1");
        assertEquals("The class abcde does not exist.", outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	@Test
    public void testAddRelationshipWhenRelationShipAlreadyExists() {
        Class c = new Class("test");
        c.addRelationship("r", "r1");
        Classes classes = new Classes();
        classes.addClass("test");
        classes.addRelationship("test","r","r1");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.addRelationship("test","r", "r1");
        assertEquals("A relationship with this class test already exists.", outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
        SortedMap <String,Class> m = classes.getClasses();
        assertTrue(m.get("test").equals(c));
    }
	@Test
    public void deleteRelationshipTest() {
        Class c = new Class("test");
        Classes classes = new Classes();
        classes.addClass("test");
        classes.addRelationship("test","r", "r1");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.deleteRelationship("test", "r", "r1");
        assertEquals("You have deleted a relationship with class: testofrwith relationship typer1", outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
        SortedMap <String,Class> m = classes.getClasses();
        assertTrue(m.get("test").equals(c));
    }
	
	@Test
    public void testDeleteRelationshipWhenClassDoesNotExist() {
        Classes classes = new Classes();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.deleteRelationship("test","r", "r1");
        assertEquals("The class test does not exist.", outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	
	@Test
    public void testDeleteRelationshipWhenRelationshipDoesNotExist() {
        Classes classes = new Classes();
        classes.addClass("test");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.deleteRelationship("test","r", "r1");
        assertEquals("A relationship with " + "r" + " does not exist.", outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	
	@Test
	public void editRelationshipDestination() {
		Class c = new Class("test");
		Class c1 = new Class ("test2");
		Class c2 = new Class ("test3");
		Classes classes = new Classes();
		classes.addClass("test");
		classes.addClass("test2");
		classes.addClass("test3");
		c.addRelationship("test3", "type1");
		classes.addRelationship("test", "test2", "type1");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.editRelationshipDestination("test","test2","test3");
		assertEquals("The relationship destination test2 has been set to test3.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	@Test
    public void testEditRelationshipDestinationWhenRelationshipDoesNotExist() {
        Classes classes = new Classes();
        classes.addClass("test");
        classes.addClass("test2");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.editRelationshipDestination("test", "test1", "test2");
        assertEquals(("The destination test1 does not exist."), outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	@Test
    public void testEditRelationshipDestinationWhenClassDoesNotExist() {
        Classes classes = new Classes();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.editRelationshipDestination("test", "test1", "test2");
        assertEquals(("The class test does not exist."), outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	@Test
    public void testEditRelationshipDestinationWhenRelationshipAlreadyExists() {
        Classes classes = new Classes();
        classes.addClass("test");
        classes.addClass("test2");
        classes.addRelationship("test", "test2", "type");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.editRelationshipDestination("test", "test2", "test2");
        assertEquals(("The new destination test2 already exists."), outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	@Test
	public void editRelationshipType() {
		Class c = new Class("test");
		Class c2 = new Class("test2");
		Classes classes = new Classes();
		classes.addClass("test");
		classes.addClass("test2");
		c.addRelationship("test2", "type1");
		classes.addRelationship("test", "test2", "type");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.editRelationshipType("test", "test2", "type1");
		assertEquals("The Relationship test2 type has been renamed to type1.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	@Test
    public void testEditRelationshipTypeWhenRelationshipDoesNotExist() {
        Classes classes = new Classes();
        classes.addClass("test");
        classes.addClass("test2");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.editRelationshipType("test", "test2", "type");
        assertEquals(("The relationship test2 does not exist with the class test."), outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	@Test
    public void testEditRelationshipTypeWhenClassDoesNotExist() {
        Classes classes = new Classes();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.editRelationshipType("test", "test1", "type");
        assertEquals(("The class test does not exist."), outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	
	
}
