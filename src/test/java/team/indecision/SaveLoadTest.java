package team.indecision;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class SaveLoadTest {

	@Test
	public void testSave() throws IOException {
		UML.addClass("test");
		UML.addAttribute("test", "attr");
		UML.addAttribute("test", "attr1");
		UML.addRelationship("test", "rel", "type");
		UML.addRelationship("test", "rel1", "type");
		UML.addClass("test1");
		UML.addClass("test2");
		UML.addRelationship("test2", "rel", "type");
		UML.addAttribute("test2", "attr");
		UML.save("classes");
		UML.listClasses();
		File classes = Paths.get("classes").toFile();
		File classesTest = Paths.get("classesTest").toFile();
		assertTrue(FileUtils.contentEquals(classes,classesTest));
		// json is valid. It was checked using https://jsonlint.com.
	}

}
