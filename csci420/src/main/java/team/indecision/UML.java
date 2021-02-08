package team.indecision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
import java.util.SortedMap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/** A text-based REPL program for creating UML models.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class UML {

	// Stores the Class objects for the UML.
	private static SortedMap<String, Class> classes = new TreeMap<String, Class>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		//Print out of the available menu options for the REPL.
		System.out.println("Welcome to Team Indecision's UML Tool");
		System.out.println("Type 'help' to see a list of commands or 'exit' to close the program.");

		
		System.out.print("\nChoose one of the above menu items: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String choice = br.readLine();
		
		String seperator = " ";
		String[] parsedChoice = StringUtils.split(choice, seperator);
		
		boolean x = true;

		while (x) {
			// add
			if (parsedChoice[0].equals("add") && (parsedChoice[1].equals("class"))) {
				addClass(parsedChoice[2]);
			}
			if (parsedChoice[0].equals("add") && (parsedChoice[1].equals("attr"))) {
//				addAttribute(parsedChoice[2], parsedChoice[3]);
			}
			if (parsedChoice[0].equals("add") && (parsedChoice[1].equals("rel"))) {
//				addRelationship(parsedChoice[2], parsedChoice[3]);
			}
			// delete
			if (parsedChoice[0].equals("delete") && (parsedChoice[1].equals("class"))) {
				deleteClass(parsedChoice[2]);
			}
			if (parsedChoice[0].equals("delete") && (parsedChoice[1].equals("attr"))) {
//				deleteAttribute(parsedChoice[2], parsedChoice[3]);
			}
			if (parsedChoice[0].equals("delete") && (parsedChoice[1].equals("rel"))) {
//				deleteRelationship(parsedChoice[2], parsedChoice[3]);
			}
			// rename
			if (parsedChoice[0].equals("rename") && (parsedChoice[1].equals("class"))) {
				renameClass(parsedChoice[2], parsedChoice[3]);
			}
			if (parsedChoice[0].equals("rename") && (parsedChoice[1].equals("attr"))) {
//				renameAttribute(parsedChoice[2], parsedChoice[3], parsedChoice[4]);
			}
			// list
			else if (parsedChoice[0].equals("list") && (parsedChoice[1].equals("classes"))) {
				listClasses();
			}
			else if (parsedChoice[0].equals("list") && (parsedChoice[1].equals("class"))) {
//				listClass(parsedChoice[0]);
			}
			else if (parsedChoice[0].equals("list") && (parsedChoice[1].equals("rel"))) {
//				listRelationships();
			}
			// save / load
			else if (parsedChoice[0].equals("save")) {
				   save(parsedChoice[1]);
			}
			else if (parsedChoice[0].equals("load")) {
				   load(parsedChoice[1]);
			}
			// misc.
			else if (parsedChoice[0].equals("help")) {
				   help();
			}
			else if (parsedChoice[0].equals("exit")){
				   break;
			}

			else {
				System.out.println("Please enter a valid selection");			
			}
			System.out.print("Choose another menu item: ");
			choice = br.readLine();
			parsedChoice = StringUtils.split(choice, seperator);
		}

	}
	
	/** Adds a Class object to the classes SortedMap field. Still needs work, junit tests etc...
	 */
	public static void addClass(String name) throws IOException {
		Class c = new Class(name);
		classes.put(name, c);
		System.out.println("You have created a new class named: " + name);
	}
	
	public static void deleteClass(String name) throws IOException {
		if(classes.containsKey(name)) {
			classes.remove(name);
		}else {
			System.out.println("Invalid class name");
		}
	}
	
	public static void renameClass(String className, String newClassName) throws IOException {
		if(classes.containsKey(className)) {
			Class c = classes.get(className);
			c.setName(newClassName);
		}else {
			System.out.println("This class does not exist.");
		}
	}
	/** Lists the Class objects stored in the classes SortedMap field. Still needs work, junit tests etc...
	 */
	public static void listClasses()
	{
			classes.forEach((key,value) -> System.out.println(value.getName() + " Attributes: " + value.printAttributes() ));
	}
	
	/** Saves the classes SortedMap to a specified .json file. Still needs work, junit tests etc...
	 */
	public static void save(String name) throws IOException {
		name = name.concat(".json");
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(Paths.get(name).toFile(), classes);
		} catch (Exception ex) {
			System.out.println("Not a valid file name.");
		}
		
	}
	
	/** Loads a specified valid .json file into the classes SortedMap. Still needs work, junit tests etc...
	 */
	public static void load(String name) {
		name = name.concat(".json");
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			classes = objectMapper.readValue(Paths.get(name).toFile(), new TypeReference<SortedMap<String, Class>>() {});
			classes.forEach((key,value) -> System.out.println(value.getName() + " Attributes: " + value.printAttributes() ));
		} catch (Exception ex) {
			System.out.println("Not valid json or file does not exist.");
		}
	}
	
	public static void help() {
		System.out.println("\nADD");
		System.out.println("add class class_name - adds a class");
		System.out.println("add attr class_name attr_name - adds attribute to a desired class");
		System.out.println("add rel class1 class2 - adds a relationship between classes\n");
		System.out.println("DELETE");
		System.out.println("del class class_name - deletes given class");
		System.out.println("del attr class_name attr_name - deletes given attribute in specified class");
		System.out.println("del rel class1 class2 - deletes the relationship between given classes\n");
		System.out.println("RENAME");
		System.out.println("rename class class_name new_class_name - renames the specified class");
		System.out.println("rename attr class_name attr_name new_attr_name - renames desired attribute given its class\n");
		System.out.println("LIST");
		System.out.println("list class class_name - list single class and all its components");
		System.out.println("list classes - list all classes");
		System.out.println("list rel - lists all relationships between classes\n");
		System.out.println("SAVE/LOAD");
		System.out.println("save file_name - saves a file to x-destination with the given file name in .json format");
		System.out.println("load file_name - loads file with given file name\n");
		System.out.println("'exit'- will exit the program");
		
	}
	
}
