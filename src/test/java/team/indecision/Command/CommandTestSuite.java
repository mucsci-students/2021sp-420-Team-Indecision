package team.indecision.Command;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import team.indecision.Model.Classes;

public class CommandTestSuite {
	
	@Test
	public void addClassCommand() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		String response = c.execute();
		assertEquals("You have created a new class named: test", response);
		assertTrue(model.getClasses().containsKey("test"));
	}
	
	@Test
	public void addClassCommandNonLetter() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"2test");
		String response = c.execute();
		assertEquals("The first letter must be a java letter.", response);
		assertFalse(model.getClasses().containsKey("2test"));
	}
	
	@Test
	public void addClassCommandDuplicate() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"test");
		String response = c1.execute();
		assertEquals("The class test already exists.", response);
		assertTrue(model.getClasses().containsKey("test"));
		assertTrue(model.getClasses().size() == 1);
	}
	
	@Test
	public void deleteClassCommand() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		DeleteClassCommand d = new DeleteClassCommand(model,"test");
		String response = d.execute();
		assertEquals("The class test has been deleted.", response);
		assertFalse(model.getClasses().containsKey("test"));
	}
	
	@Test
	public void deleteClassCommandRelationships() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "Composition");
		r.execute();
		DeleteClassCommand d = new DeleteClassCommand(model,"test1");
		String response = d.execute();
		assertEquals("The class test1 has been deleted.", response);
		assertFalse(model.getClasses().containsKey("test1"));
		assertFalse(model.getClasses().get("test").containsRelationship("test1"));
	}
	
	@Test
	public void deleteClassCommandClassDoesNotExist() {
		Classes model = new Classes();
		DeleteClassCommand d = new DeleteClassCommand(model,"test");
		String response = d.execute();
		assertEquals("The class test does not exist.", response);
		assertFalse(model.getClasses().containsKey("test"));
	}

	@Test
	public void editClassNameCommand() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		EditClassNameCommand e = new EditClassNameCommand(model,"test","t");
		String response = e.execute();
		assertEquals("You have renamed the class test to t", response);
		assertTrue(model.getClasses().containsKey("t"));
		assertFalse(model.getClasses().containsKey("test"));
	}
	
	@Test
	public void editClassNameCommandClassDoesNotExist() {
		Classes model = new Classes();
		EditClassNameCommand e = new EditClassNameCommand(model,"test","t");
		String response = e.execute();
		assertEquals("The class test does not exist.", response);
		assertFalse(model.getClasses().containsKey("test"));
	}
	
	@Test
	public void editClassNameCommandClassRenameAlreadyExists() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"t");
		c1.execute();
		EditClassNameCommand e = new EditClassNameCommand(model,"test","t");
		String response = e.execute();
		assertEquals("The new class name t already exists.", response);
		assertTrue(model.getClasses().containsKey("t"));
		assertTrue(model.getClasses().containsKey("test"));
	}
	
	@Test
	public void addFieldCommand() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddFieldCommand f = new AddFieldCommand(model, "test", "f");
		String response = f.execute();
		assertEquals("The field f has been added to the class test.", response);
		assertTrue(model.getClasses().get("test").containsField("f"));
	}
	
	@Test
	public void addFieldCommandClassDoesNotExist() {
		Classes model = new Classes();
		AddFieldCommand f = new AddFieldCommand(model, "test", "f");
		String response = f.execute();
		assertEquals("The class test does not exist.", response);
		assertFalse(model.getClasses().containsKey("test"));
	}
	
	@Test
	public void addFieldCommandDuplicate() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddFieldCommand f1 = new AddFieldCommand(model, "test", "f");
		f1.execute();
		AddFieldCommand f = new AddFieldCommand(model, "test", "f");
		String response = f.execute();
		assertEquals("The field f already exists with the class test.", response);
		assertTrue(model.getClasses().get("test").containsField("f"));
	}

	@Test
	public void deleteFieldCommand() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddFieldCommand f = new AddFieldCommand(model, "test", "f");
		f.execute();
		DeleteFieldCommand f1 = new DeleteFieldCommand(model, "test", "f");
		String response = f1.execute();
		assertEquals("The field f has been deleted from class test.", response);
		assertFalse(model.getClasses().get("test").containsField("f"));
	}
	
	@Test
	public void deleteFieldCommandClassDoesNotExist() {
		Classes model = new Classes();
		DeleteFieldCommand f1 = new DeleteFieldCommand(model, "test", "f");
		String response = f1.execute();
		assertEquals("The class test does not exist.", response);
		assertFalse(model.getClasses().containsKey("test"));
	}
	
	@Test
	public void deleteFieldCommandFieldDoesNotExist() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		DeleteFieldCommand f1 = new DeleteFieldCommand(model, "test", "f");
		String response = f1.execute();
		assertEquals("The field f does not exist with the class test.", response);
		assertFalse(model.getClasses().get("test").containsField("f"));
	}
	
	@Test
	public void editFieldNameCommand() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddFieldCommand f1 = new AddFieldCommand(model, "test", "f");
		f1.execute();
		EditFieldNameCommand f = new EditFieldNameCommand(model, "test", "f", "f1");
		String response = f.execute();
		assertEquals("The field f has been renamed to f1.", response);
		assertFalse(model.getClasses().get("test").containsField("f"));
		assertTrue(model.getClasses().get("test").containsField("f1"));
	}
	
	@Test
	public void editFieldNameCommandClassDoesNotExist() {
		Classes model = new Classes();
		AddFieldCommand f1 = new AddFieldCommand(model, "test", "f");
		f1.execute();
		EditFieldNameCommand f = new EditFieldNameCommand(model, "test", "f", "f1");
		String response = f.execute();
		assertEquals("The class test does not exist.", response);
		assertFalse(model.getClasses().containsKey("test"));
	}
	
	@Test
	public void editFieldNameCommandFieldDoesNotExist() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		EditFieldNameCommand f = new EditFieldNameCommand(model, "test", "f", "f1");
		String response = f.execute();
		assertEquals("The field f does not exist with the class test.", response);
		assertFalse(model.getClasses().get("test").containsField("f"));
	}
	
	@Test
	public void editFieldNameCommandFieldRenameAlreadyExists() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddFieldCommand f1 = new AddFieldCommand(model, "test", "f");
		f1.execute();
		AddFieldCommand f2 = new AddFieldCommand(model, "test", "f1");
		f2.execute();
		EditFieldNameCommand f = new EditFieldNameCommand(model, "test", "f", "f1");
		String response = f.execute();
		assertEquals("The field f1 already exists with the class test.", response);
		assertTrue(model.getClasses().get("test").containsField("f"));
		assertTrue(model.getClasses().get("test").containsField("f1"));
	}
	
	@Test
    public void addRelationshipCommand() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "Composition");
		String response = r.execute();
		assertEquals("The test has created a new relationship with class: test1 of type Composition", response);
		assertTrue(model.getClasses().get("test").containsRelationship("test1"));
    }
	
	@Test
    public void addRelationshipCommandInvalidRelationshipType() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "l");
		String response = r.execute();
		assertEquals("The relationship type must be one of the following: Aggregation, Composition, Inheritance or Realization", response);
		assertFalse(model.getClasses().get("test").containsRelationship("test1"));
    }
	
	@Test
    public void addRelationshipCommandClassDoesNotExist() {
		Classes model = new Classes();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "Composition");
		String response = r.execute();
		assertEquals("The class test does not exist.", response);
		assertFalse(model.getClasses().containsKey("test"));
    }
	
	@Test
    public void addRelationshipCommandDestiniationClassDoesNotExist() {
		Classes model = new Classes();
		AddClassCommand c1 = new AddClassCommand(model,"test");
		c1.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "Composition");
		String response = r.execute();
		assertEquals("The test1 class does not exist.", response);
		assertFalse(model.getClasses().containsKey("test1"));
    }
	
	@Test
    public void addRelationshipCommandRelationshipAlreadyExists() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "Composition");
		r.execute();
		AddRelationshipCommand r1 = new AddRelationshipCommand(model, "test", "test1", "Aggregation");
		String response = r1.execute();
		assertEquals("A relationship with this class test already exists.", response);
		assertTrue(model.getClasses().get("test").containsRelationship("test1"));
    }
	
	@Test
    public void deleteRelationshipCommand() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "Composition");
		r.execute();
		DeleteRelationshipCommand r1 = new DeleteRelationshipCommand(model, "test", "test1");
		String response = r1.execute();
		assertEquals("You have deleted a relationship with class: test1", response);
		assertFalse(model.getClasses().get("test").containsRelationship("test1"));
    }
	
	@Test
    public void deleteRelationshipCommandClassDoesNotExist() {
		Classes model = new Classes();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "Composition");
		r.execute();
		DeleteRelationshipCommand r1 = new DeleteRelationshipCommand(model, "test", "test1");
		String response = r1.execute();
		assertEquals("The class test does not exist.", response);
		assertFalse(model.getClasses().containsKey("test"));
    }
	
	@Test
    public void deleteRelationshipCommandRelationshipDoesNotExist() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		DeleteRelationshipCommand r1 = new DeleteRelationshipCommand(model, "test", "test1");
		String response = r1.execute();
		assertEquals("A relationship with " + "test1" + " does not exist.", response);
		assertFalse(model.getClasses().get("test").containsRelationship("test1"));
    }
	
	@Test
    public void editRelationshipDestiniationCommand() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddClassCommand c2 = new AddClassCommand(model,"test2");
		c2.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "Composition");
		r.execute();
		EditRelationshipDestinationCommand d = new EditRelationshipDestinationCommand(model, "test", "test1", "test2");
		String response = d.execute();
		assertEquals("The relationship test1 has been changed to test2.", response);
		assertTrue(model.getClasses().get("test").containsRelationship("test2"));
		assertFalse(model.getClasses().get("test").containsRelationship("test1"));
    }
	
	@Test
    public void editRelationshipDestiniationCommandClassDoesNotExist() {
		Classes model = new Classes();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddClassCommand c2 = new AddClassCommand(model,"test2");
		c2.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "Composition");
		r.execute();
		EditRelationshipDestinationCommand d = new EditRelationshipDestinationCommand(model, "test", "test1", "test2");
		String response = d.execute();
		assertEquals("The class test does not exist.", response);
		assertFalse(model.getClasses().containsKey("test"));
    }
	
	@Test
    public void editRelationshipDestiniationCommandRelationshipDoesNotExist() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddClassCommand c2 = new AddClassCommand(model,"test2");
		c2.execute();
		EditRelationshipDestinationCommand d = new EditRelationshipDestinationCommand(model, "test", "test1", "test2");
		String response = d.execute();
		assertEquals("The relationship test1 does not exist with the class test.", response);
		assertFalse(model.getClasses().get("test").containsRelationship("test1"));
    }

	@Test
    public void editRelationshipDestiniationCommandRelationshipAlreadyExists() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddClassCommand c2 = new AddClassCommand(model,"test2");
		c2.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "Composition");
		r.execute();
		AddRelationshipCommand r1 = new AddRelationshipCommand(model, "test", "test2", "Composition");
		r1.execute();
		EditRelationshipDestinationCommand d = new EditRelationshipDestinationCommand(model, "test", "test1", "test2");
		String response = d.execute();
		assertEquals("The relationship test2 already exists with the class test.", response);
		assertTrue(model.getClasses().get("test").containsRelationship("test2"));
    }
	
	@Test
    public void editRelationshipTypeCommand() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "Composition");
		r.execute();
		EditRelationshipTypeCommand d = new EditRelationshipTypeCommand(model, "test", "test1", "Aggregation");
		String response = d.execute();
		assertEquals("The relationship test1 type has been changed to Aggregation.", response);
		assertTrue(model.getClasses().get("test").getRelationship("test1").getType().equals("Aggregation"));
    }
	
	@Test
    public void editRelationshipTypeCommandClassDoesNotExist() {
		Classes model = new Classes();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "Composition");
		r.execute();
		EditRelationshipTypeCommand d = new EditRelationshipTypeCommand(model, "test", "test1", "Aggregation");
		String response = d.execute();
		assertEquals("The class test does not exist.", response);
		assertFalse(model.getClasses().containsKey("test"));
    }
	
	@Test
    public void editRelationshipTypeCommandRelationshipDoesNotExist() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		EditRelationshipTypeCommand d = new EditRelationshipTypeCommand(model, "test", "test1", "Aggregation");
		String response = d.execute();
		assertEquals("The relationship test1 does not exist with the class test.", response);
		assertFalse(model.getClasses().get("test").containsRelationship("test1"));
    }
	
	@Test
    public void addMethodCommand() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");	
		AddMethodCommand d = new AddMethodCommand(model, "test", "meth", p);
		String response = d.execute();
		assertEquals("The method meth has been added to the class test.", response);
		assertTrue(model.getClasses().get("test").containsMethod("meth", p));
    }
	
	@Test
    public void addMethodCommandClassDoesNotExist() {
		Classes model = new Classes();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");	
		AddMethodCommand d = new AddMethodCommand(model, "test", "meth", p);
		String response = d.execute();
		assertEquals("The class test does not exist.", response);
		assertFalse(model.getClasses().containsKey("test"));
    }
	
	@Test
    public void addMethodCommandMethodAlreadyExists() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");
		AddMethodCommand d1 = new AddMethodCommand(model, "test", "meth", p);
		d1.execute();
		AddMethodCommand d = new AddMethodCommand(model, "test", "meth", p);
		String response = d.execute();
		assertEquals("The method meth already exists with the class test with those parameters.", response);
		assertTrue(model.getClasses().get("test").containsMethod("meth", p));
    }
	
	@Test
    public void deleteMethodCommand() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");	
		AddMethodCommand a = new AddMethodCommand(model, "test", "meth", p);
		a.execute();
		DeleteMethodCommand d = new DeleteMethodCommand(model, "test", "meth", p);
		String response = d.execute();
		assertEquals("The method meth has been deleted from class test.", response);
		assertFalse(model.getClasses().get("test").containsMethod("meth", p));
    }
	
	@Test
    public void deleteMethodCommandClassDoesNotExist() {
		Classes model = new Classes();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");	
		AddMethodCommand a = new AddMethodCommand(model, "test", "meth", p);
		a.execute();
		DeleteMethodCommand d = new DeleteMethodCommand(model, "test", "meth", p);
		String response = d.execute();
		assertEquals("The class test does not exist.", response);
		assertFalse(model.getClasses().containsKey("test"));
    }
	
	@Test
    public void deleteMethodCommandMethodDoesNotExist() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");	
		DeleteMethodCommand d = new DeleteMethodCommand(model, "test", "meth", p);
		String response = d.execute();
		assertEquals("The method meth does not exist with the class test.", response);
		assertFalse(model.getClasses().get("test").containsMethod("meth", p));
    }
	
	@Test
    public void editMethodNameCommand() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");	
		AddMethodCommand d = new AddMethodCommand(model, "test", "meth", p);
		d.execute();
		EditMethodNameCommand e = new EditMethodNameCommand(model, "test", "meth", p, "meth1");
		String response = e.execute();
		assertEquals("The method meth has been changed to meth1.", response);
		assertTrue(model.getClasses().get("test").containsMethod("meth1", p));
		assertFalse(model.getClasses().get("test").containsMethod("meth", p));
    }
	
	@Test
    public void editMethodNameCommandClassDoesNotExist() {
		Classes model = new Classes();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");	
		AddMethodCommand d = new AddMethodCommand(model, "test", "meth", p);
		d.execute();
		EditMethodNameCommand e = new EditMethodNameCommand(model, "test", "meth", p, "meth1");
		String response = e.execute();
		assertEquals("The class test does not exist.", response);
		assertFalse(model.getClasses().containsKey("test"));
    }
	
	@Test
    public void editMethodNameCommandMethodDoesNotExist() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");
		EditMethodNameCommand e = new EditMethodNameCommand(model, "test", "meth", p, "meth1");
		String response = e.execute();
		assertEquals("The method meth does not exist with the class test.", response);
		assertFalse(model.getClasses().get("test").containsMethod("meth", p));
    }
	
	@Test
    public void editMethodNameCommandMethodNameAlreadyExists() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");
		AddMethodCommand d = new AddMethodCommand(model, "test", "meth", p);
		d.execute();
		AddMethodCommand d1 = new AddMethodCommand(model, "test", "meth1", p);
		d1.execute();
		EditMethodNameCommand e = new EditMethodNameCommand(model, "test", "meth", p, "meth1");
		String response = e.execute();
		assertEquals("The method meth1 already exists with the class test with those parameters.", response);
		assertTrue(model.getClasses().get("test").containsMethod("meth", p));
		assertTrue(model.getClasses().get("test").containsMethod("meth1", p));
    }
	
	@Test
    public void editMethodParametersCommand() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");
		List <String> pn = new ArrayList<String>();
		pn.add("1");
		pn.add("2");
		AddMethodCommand d = new AddMethodCommand(model, "test", "meth", p);
		d.execute();
		EditMethodParametersCommand e = new EditMethodParametersCommand(model, "test", "meth", p, pn);
		String response = e.execute();
		assertEquals("The method parameters of meth has been changed.", response);
		assertFalse(model.getClasses().get("test").containsMethod("meth", p));
		assertTrue(model.getClasses().get("test").containsMethod("meth", pn));
    }
	
	@Test
    public void editMethodParametersCommandClassDoesNotExist() {
		Classes model = new Classes();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");
		List <String> pn = new ArrayList<String>();
		pn.add("1");
		pn.add("2");
		AddMethodCommand d = new AddMethodCommand(model, "test", "meth", p);
		d.execute();
		EditMethodParametersCommand e = new EditMethodParametersCommand(model, "test", "meth", p, pn);
		String response = e.execute();
		assertEquals("The class test does not exist.", response);
		assertFalse(model.getClasses().containsKey("test"));
    }
	
	@Test
    public void editMethodParametersCommandMethodAlreadyExists() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");
		List <String> pn = new ArrayList<String>();
		pn.add("1");
		pn.add("2");
		AddMethodCommand d = new AddMethodCommand(model, "test", "meth", p);
		d.execute();
		AddMethodCommand d1 = new AddMethodCommand(model, "test", "meth", pn);
		d1.execute();
		EditMethodParametersCommand e = new EditMethodParametersCommand(model, "test", "meth", p, pn);
		String response = e.execute();
		assertEquals("The method meth already exists with the class test with those parameters.", response);
		assertTrue(model.getClasses().get("test").containsMethod("meth", p));
		assertTrue(model.getClasses().get("test").containsMethod("meth", pn));
    }
	
	@Test
    public void editMethodParametersCommandMethodDoesNotExist() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");
		List <String> pn = new ArrayList<String>();
		pn.add("1");
		pn.add("2");
		EditMethodParametersCommand e = new EditMethodParametersCommand(model, "test", "meth", p, pn);
		String response = e.execute();
		assertEquals("The method meth does not exist with the class test.", response);
		assertFalse(model.getClasses().get("test").containsMethod("meth", p));
    }
	
}
