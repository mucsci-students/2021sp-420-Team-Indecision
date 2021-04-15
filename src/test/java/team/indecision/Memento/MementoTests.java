package team.indecision.Memento;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import team.indecision.Command.*;
import team.indecision.Model.*;

public class MementoTests {

	@Test
	public void Memento() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddFieldCommand f = new AddFieldCommand(model, "test", "f");
		f.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "Composition");
		r.execute();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");	
		AddMethodCommand d = new AddMethodCommand(model, "test", "meth", p);
		d.execute();
		
		Memento m = new Memento(model);
		assertFalse(model.getClasses().equals(model.getBackup()));
		m.restore();
		assertTrue(model.getClasses().equals(model.getBackup()));
	}
	
	@Test
	public void History() {
		Classes model = new Classes();
		AddClassCommand c = new AddClassCommand(model,"test");
		c.execute();
		AddClassCommand c1 = new AddClassCommand(model,"test1");
		c1.execute();
		AddFieldCommand f = new AddFieldCommand(model, "test", "f");
		f.execute();
		AddRelationshipCommand r = new AddRelationshipCommand(model, "test", "test1", "Composition");
		r.execute();
		List <String> p = new ArrayList<String>();
		p.add("String 1");
		p.add("String 2");	
		AddMethodCommand d = new AddMethodCommand(model, "test", "meth", p);
		d.execute();
		
		History h = new History();
		AddClassCommand c2 = new AddClassCommand(model,"testUndo");
		c2.execute();
		assertEquals(true,model.getClasses().containsKey("testUndo"));
		h.pushUndo(c2,new Memento(model));
		h.undo();
		assertEquals(false,model.getClasses().containsKey("testUndo"));
		Command c3 = h.redo();
		c3.execute();
		assertEquals(true,model.getClasses().containsKey("testUndo"));
	}

}
