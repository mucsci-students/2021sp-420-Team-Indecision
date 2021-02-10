package team.indecision;

import static org.junit.Assert.*;
import org.junit.Test;

public class addClassTest {

	@Test
	public void testAddClass() {
		Class c = new Class("test");
		UML.addClass("test");
		assertTrue(c.equals(UML.getClasses().get("test"))); //Testing that we are adding a class correctly.
		UML.addClass("test"); 
		assertTrue(UML.getClasses().size() == 1 && c.equals(UML.getClasses().get("test"))); //Testing that when we add a duplicate class we not adding it again.
	}

}
