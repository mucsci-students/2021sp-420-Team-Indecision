package team.indecision;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class InterfaceTest {

	@Test
	public void listClassTest() {
		
		Class test = new Class("test");
		
		UML.addClass("test");
		
		assertEquals(UML.listClass("test"),test.toString());
	}

}
