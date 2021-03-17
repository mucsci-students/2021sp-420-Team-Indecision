package team.indecision.Model;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.SortedMap;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

public class ClassesTest {
/*
	@Test
	public void addClassTest() {
		Class c = new Class("test");
		Classes classes = new Classes();	
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.addClassCLI("test");
		assertEquals("You have created a new class named: test", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	
	@Test
	public void addClassTestNonLetter() {
		Class c = new Class("test");
		Classes classes = new Classes();	
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.addClassCLI("2test");
		assertEquals("The first letter must be a java letter.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test") == null);
	}
	
	@Test
	public void addClassTestDuplicate() {
		Class c = new Class("test");
		Classes classes = new Classes();
		classes.addClassCLI("test");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.addClassCLI("test");
		assertEquals("The class test already exists.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	
	@Test
	public void deleteClassTest() {
		Classes classes = new Classes();
		classes.addClassCLI("test");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.deleteClassCLI("test");
		assertEquals("The class test has been deleted.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test") == null);
	}
	
	@Test
	public void deleteClassTestAndRelationships() {
		Classes classes = new Classes();
		classes.addClassCLI("test");
		classes.addClassCLI("test1");
		classes.addRelationshipCLI("test1", "test", "Composition");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.deleteClassCLI("test");
		assertEquals("The class test has been deleted.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test") == null);
		assertTrue(m.get("test1").getRelationship("test") == null);
	}
	
	@Test
	public void deleteClassTestClassDoesNotExist() {
		Classes classes = new Classes();
		classes.addClassCLI("test");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.deleteClassCLI("test1");
		assertEquals("The class test1 does not exist.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
	}
	
	@Test
	public void renameClassTest() {
		Class c = new Class("test1");
		Classes classes = new Classes();
		classes.addClassCLI("test");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.renameClassCLI("test", "test1");
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
		classes.renameClassCLI("test", "test1");
		assertEquals("The class test does not exist.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test") == null);
	}
	
	@Test
	public void renameClassTestClassRenameAlreadyExists() {
		Classes classes = new Classes();
		classes.addClassCLI("test");
		classes.addClassCLI("test1");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.renameClassCLI("test", "test1");
		assertEquals("The new class name test1 already exists.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
	}
	
	@Test
	public void addFieldTest() {
		Class c = new Class("test");
		c.addField("f");
		Classes classes = new Classes();
		classes.addClassCLI("test");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.addFieldCLI("test","f");
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
		classes.addFieldCLI("test","f");
		assertEquals("The class test does not exist.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
	}
	
	@Test
	public void addFieldDuplicateTest() {
		Class c = new Class("test");
		c.addField("f");
		Classes classes = new Classes();
		classes.addClassCLI("test");
		classes.addFieldCLI("test","f");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.addFieldCLI("test","f");
		assertEquals("The field f already exists with the class test.", outContent.toString().trim()); 
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	
	@Test
	public void deleteFieldTest() {
		Class c = new Class("test");
		Classes classes = new Classes();
		classes.addClassCLI("test");
		classes.addFieldCLI("test","f");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.deleteFieldCLI("test", "f");
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
		classes.deleteFieldCLI("test","f");
		assertEquals("The class test does not exist.", outContent.toString().trim()); 
		System.setOut(System.out); // resetting the system.setOut to default
	}
	
	@Test
	public void deleteFieldNoExistingFieldTest() {
		Classes classes = new Classes();
		classes.addClassCLI("test");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.deleteFieldCLI("test","f");
		assertEquals("The field f does not exist with the class test.", outContent.toString().trim()); 
		System.setOut(System.out); // resetting the system.setOut to default
	}
	
	@Test
	public void editFieldTest() {
		Class c = new Class("test");
		c.addField("f1");
		Classes classes = new Classes();
		classes.addClassCLI("test");
		classes.addFieldCLI("test", "f");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.editFieldCLI("test","f","f1");
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
		classes.editFieldCLI("test","f","f1");
		assertEquals("The class test does not exist.", outContent.toString().trim()); 
		System.setOut(System.out); // resetting the system.setOut to default
	}
	
	@Test
	public void editFieldTestFieldNameDoesNotExist() {
		Class c = new Class("test");
		c.addField("f1");
		Classes classes = new Classes();
		classes.addClassCLI("test");
		classes.addFieldCLI("test", "f1");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.editFieldCLI("test","f2","f");
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
		classes.addClassCLI("test");
		classes.addFieldCLI("test", "f");
		classes.addFieldCLI("test", "f1");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.editFieldCLI("test","f","f1");
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
        classes.addClassCLI("test");
        classes.addClassCLI("r");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.addRelationshipCLI("test","r","Composition" );
        assertEquals("The test has created a new relationship with class: r of type Composition", outContent.toString().trim());
        System.setOut(System.out); // resetting the system.setOut to default
        SortedMap <String,Class> m = classes.getClasses();
        assertTrue(m.get("test").equals(c));

    }
	
	@Test
    public void addRelationshipTestInvalidRelationshipType() {
        Class c = new Class("test");
        Classes classes = new Classes();
        classes.addClassCLI("test");
        classes.addClassCLI("r");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.addRelationshipCLI("test","r","r1" );
        assertEquals("The relationship type must be one of the following: Aggregation, Composition, Inheritance or Realization", outContent.toString().trim());
        System.setOut(System.out); // resetting the system.setOut to default
        SortedMap <String,Class> m = classes.getClasses();
        assertTrue(m.get("test").equals(c));
    }
	
	@Test
    public void testAddRelationshipWhenClassDoesNotExist() {
        Classes classes = new Classes();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.addRelationshipCLI("abcde", "class1", "type1");
        assertEquals("The class abcde does not exist.", outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	
	@Test
    public void testAddRelationshipWhenDestiniationClassDoesNotExist() {
        Classes classes = new Classes();
        classes.addClassCLI("test");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.addRelationshipCLI("test", "class1", "type1");
        assertEquals("The class1 class does not exist.", outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
        SortedMap <String,Class> m = classes.getClasses();
        assertTrue(m.get("class1") == null);
    }
	
	@Test
    public void testAddRelationshipWhenRelationShipAlreadyExists() {
        Class c = new Class("test");
        c.addRelationship("r", "r1");
        Classes classes = new Classes();
        classes.addClassCLI("test");
        classes.addClassCLI("r");
        classes.addRelationshipCLI("test","r","Composition");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.addRelationshipCLI("test","r", "Composition");
        assertEquals("A relationship with this class test already exists.", outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
        SortedMap <String,Class> m = classes.getClasses();
        assertTrue(m.get("test").equals(c));
    }
	@Test
    public void deleteRelationshipTest() {
        Class c = new Class("test");
        Classes classes = new Classes();
        classes.addClassCLI("test");
        classes.addClassCLI("r");
        classes.addRelationshipCLI("test","r", "Composition");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.deleteRelationshipCLI("test", "r");
        assertEquals("You have deleted a relationship with class: r", outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
        SortedMap <String,Class> m = classes.getClasses();
        assertTrue(m.get("test").equals(c));
    }
	
	@Test
    public void testDeleteRelationshipWhenClassDoesNotExist() {
        Classes classes = new Classes();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.deleteRelationshipCLI("test","r");
        assertEquals("The class test does not exist.", outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	
	@Test
    public void testDeleteRelationshipWhenRelationshipDoesNotExist() {
        Classes classes = new Classes();
        classes.addClassCLI("test");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.deleteRelationshipCLI("test","r");
        assertEquals("A relationship with " + "r" + " does not exist.", outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	
	@Test
	public void editRelationshipDestination() {
		Class c = new Class("test");
		Classes classes = new Classes();
		classes.addClassCLI("test");
		classes.addClassCLI("test2");
		classes.addClassCLI("test3");
		c.addRelationship("test3", "Composition");
		classes.addRelationshipCLI("test", "test2", "Composition");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.editRelationshipDestinationCLI("test","test2","test3");
		assertEquals("The relationship test2 has been changed to test3.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	@Test
    public void testEditRelationshipDestinationWhenRelationshipDoesNotExist() {
        Classes classes = new Classes();
        classes.addClassCLI("test");
        classes.addClassCLI("test2");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.editRelationshipDestinationCLI("test", "test1", "test2");
        assertEquals(("The relationship test1 does not exist with the class test."), outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	@Test
    public void testEditRelationshipDestinationWhenClassDoesNotExist() {
        Classes classes = new Classes();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.editRelationshipDestinationCLI("test", "test1", "test2");
        assertEquals(("The class test does not exist."), outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	@Test
    public void testEditRelationshipDestinationWhenRelationshipAlreadyExists() {
        Classes classes = new Classes();
        classes.addClassCLI("test");
        classes.addClassCLI("test2");
        classes.addRelationshipCLI("test", "test2", "Composition");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.editRelationshipDestinationCLI("test", "test2", "test2");
        assertEquals(("The relationship test2 already exists with the class test."), outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	@Test
	public void editRelationshipType() {
		Class c = new Class("test");
		Classes classes = new Classes();
		classes.addClassCLI("test");
		classes.addClassCLI("test2");
		c.addRelationship("test2", "Composition");
		classes.addRelationshipCLI("test", "test2", "Composition");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.editRelationshipTypeCLI("test", "test2", "Aggregation");
		assertEquals("The relationship test2 type has been changed to Aggregation.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	@Test
    public void testEditRelationshipTypeWhenRelationshipDoesNotExist() {
        Classes classes = new Classes();
        classes.addClassCLI("test");
        classes.addClassCLI("test2");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.editRelationshipTypeCLI("test", "test2", "type");
        assertEquals(("The relationship test2 does not exist with the class test."), outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	@Test
    public void testEditRelationshipTypeWhenClassDoesNotExist() {
        Classes classes = new Classes();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.editRelationshipTypeCLI("test", "test1", "type");
        assertEquals(("The class test does not exist."), outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }
	@Test
    public void addMethodsTest() {
        Class c = new Class("test");
        c.addMethod("md", null);
        Classes classes = new Classes();
        classes.addClassCLI("test");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.addMethodCLI("test","md", null);
        assertEquals("The method md has been added to the class test.", outContent.toString().trim());
        System.setOut(System.out); // resetting the system.setOut to default
        SortedMap <String,Class> m = classes.getClasses();
        assertTrue(m.get("test").equals(c));

    }
	@Test
	public void addMethodNoExistingClassTest() {
		Classes classes = new Classes();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.addMethodCLI("test","m", null);
		assertEquals("The class test does not exist.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
	}
	
	@Test
	public void addMethodDuplicateTest() {
		Class c = new Class("test");
		ArrayList<String> lst = new ArrayList<>();
		c.addMethod("md", null);
		Classes classes = new Classes();
		classes.addClassCLI("test");
		classes.addMethodCLI("test","md", lst);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.addMethodCLI("test","md", lst);
		assertEquals("The method md already exists with the class test with those parameters.", outContent.toString().trim()); 
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	
	@Test
    public void deleteMethodTest() {
        Class c = new Class("test");
        ArrayList<String> lst = new ArrayList<>();
        Classes classes = new Classes();
        classes.addClassCLI("test");
        c.addMethod("testMethod", lst);
        classes.addMethodCLI("test", "testMethod", lst);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.deleteMethodCLI("test", "testMethod", lst);
        c.deleteMethod("testMethod", lst);
        assertEquals("The method testMethod has been deleted from class test.", outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
        SortedMap <String,Class> m = classes.getClasses();
        assertTrue(m.get("test").equals(c));
    }

    @Test
    public void deleteMethodNoExistingClassTest() {
        Classes classes = new Classes();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.deleteMethodCLI("test","testMethod", null);
        assertEquals("The class test does not exist.", outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
    }

    @Test
    public void deleteMethodWhenMethodDoesNotExist() {
        Class c = new Class("test");
        Classes classes = new Classes();
        classes.addClassCLI("test");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); //capturing console output.
        classes.deleteMethodCLI("test", "testMethod", null);
        c.deleteMethod("testMethod", null);
        assertEquals("The method testMethod does not exist with the class test.", outContent.toString().trim()); 
        System.setOut(System.out); // resetting the system.setOut to default
        SortedMap <String,Class> m = classes.getClasses();
        assertTrue(m.get("test").equals(c));
    }

	@Test
	public void editMethodNameTest() {
	
			Class c = new Class("test");
			ArrayList<String> lst = new ArrayList<>();
			c.addMethod("md1", lst);
			Classes classes = new Classes();
			classes.addClassCLI("test");
			classes.addMethodCLI("test", "md", lst);
			ByteArrayOutputStream outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent)); //capturing console output.
			classes.editMethodNameCLI("test","md", lst, "md1");
			assertEquals("The method md has been changed to md1.", outContent.toString().trim());
			System.setOut(System.out); // resetting the system.setOut to default
			SortedMap <String,Class> m = classes.getClasses();
			assertTrue(m.get("test").equals(c));
	}
	 @Test
	public void editMethodNoExistingClass() {
		Classes classes = new Classes();
		ArrayList<String> lst = new ArrayList<>();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.editMethodNameCLI("test","m", lst,"m1");
		assertEquals("The class test does not exist.", outContent.toString().trim()); 
		System.setOut(System.out); // resetting the system.setOut to default
	} 
	
	@Test
	public void editMethodTestMethodNameDoesNotExist() {
		Class c = new Class("test");
		ArrayList<String> lst = new ArrayList<>();
		c.addMethod("m1", lst);
		Classes classes = new Classes();
		classes.addClassCLI("test");
		classes.addMethodCLI("test", "m1",lst);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.editMethodNameCLI("test","m2", lst, "m1");
		assertEquals("The method m1 already exists with the class test with those parameters.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c));
	}
	
	 @Test
	public void editMethodNameMethodNameAlreadyExists() {
		Class c = new Class("test");
		ArrayList<String> lst = new ArrayList<>();
		c.addMethod("md",lst);
		c.addMethod("md1",lst);
		Classes classes = new Classes();
		classes.addClassCLI("test");
		classes.addMethodCLI("test", "md",lst);
		classes.addMethodCLI("test", "md1", lst);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); //capturing console output.
		classes.editMethodNameCLI("test","md",lst, "md1");
		assertEquals("The method md1 already exists with the class test with those parameters.", outContent.toString().trim());
		System.setOut(System.out); // resetting the system.setOut to default
		SortedMap <String,Class> m = classes.getClasses();
		assertTrue(m.get("test").equals(c)); 
	} 
	
	 @Test
	public void editMethodParametersTest() {
	     Class c = new Class("test");
	     List <String> lst = new ArrayList<String>();
	     List <String> lstTwo = new ArrayList<String>();
	     lstTwo.add("testParameter");
	     c.addMethod("testMethod", lstTwo);
	     Classes classes = new Classes();
	     classes.addClassCLI("test");
	     classes.addMethodCLI("test", "testMethod", lst);
	     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent)); //capturing console output.
	     classes.editMethodParametersCLI("test","testMethod", lst, lstTwo);
	     assertEquals("The method parameters of testMethod has been changed.", outContent.toString().trim());
	     System.setOut(System.out); // resetting the system.setOut to default
	     SortedMap <String,Class> m = classes.getClasses();
	     assertTrue(m.get("test").equals(c));
	}

      @Test
    public void testEditMethodParametersWhenParametersAlreadyExistWithThatMethodAndClass() {
	     Classes classes = new Classes();
	     classes.addClassCLI("test");
	     List <String> lst = new ArrayList<String>();
	     classes.addMethodCLI("test", "testMethod", lst);
	     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent)); //capturing console output.
	     classes.editMethodParametersCLI("test", "testMethod", lst, lst);
	     assertEquals(("The method testMethod already exists with the class test with those parameters."), outContent.toString().trim()); 
	     System.setOut(System.out); // resetting the system.setOut to default
	}
	    
	 @Test
    public void testEditMethodParametersWhenMethodDoesNotExistWithClass() {
	     Classes classes = new Classes();
	     List <String> lst = new ArrayList<String>();
	     List <String> lstTwo = new ArrayList<String>();
	     classes.addClassCLI("test");
	     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent)); //capturing console output.
	     classes.editMethodParametersCLI("test", "testMethod", lst, lstTwo);
	     assertEquals("The method testMethod does not exist with the class test.", outContent.toString().trim());
	     System.setOut(System.out); // resetting the system.setOut to default
   }

     @Test
	public void testEditMethodParametersWhenClassDoesNotExist() {
	     Classes classes = new Classes();
	     List <String> lst = new ArrayList<String>();
	     List <String> lstTwo = new ArrayList<String>();
	     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent)); //capturing console output.
	     classes.editMethodParametersCLI("test", "testMethod", lst, lstTwo);
	     assertEquals("The class test does not exist.", outContent.toString().trim());
	     System.setOut(System.out); // resetting the system.setOut to default
   }
*/	
}