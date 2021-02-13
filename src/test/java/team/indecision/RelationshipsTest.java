package team.indecision;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

public class RelationshipsTest {

	@Test
	public void testAddRelationship() {
        String name = "nameTest";
        Set<String> attributes = new HashSet<String>();
        attributes.add("attribute1");
        attributes.add("attribute2");
        attributes.add("attribute3");
        SortedMap<String, String> relationships = new TreeMap<String, String>();
        relationships.put("class1","type1");
        Class c = new Class (name, attributes, relationships);
        UML.addClass(name);
        UML.addRelationship("nameTest", "class1", "type1");
        assertEquals(c.getRelationships(),UML.getClasses().get("nameTest").getRelationships());
    }

}
