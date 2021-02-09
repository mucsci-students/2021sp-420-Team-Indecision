package team.indecision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import java.util.SortedMap;
import java.util.Scanner;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/** A text-based REPL program for creating UML models.
 * @author Connor Nissley
 * @version 1.0
 * @since 1.0
 */
public class UML {

	// Stores the Class objects for the UML.
	private static SortedMap<String, Class> classes = new TreeMap<String, Class>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		//Print out of the available menu options for the REPL.
		System.out.println("UML Tool");
		System.out.println("1. Add Class");
		System.out.println("2. Delete Class");
		System.out.println("3. Rename Class");
		System.out.println("4. Add Attribute");
		System.out.println("5. Delete Attribute");
		System.out.println("6. Rename Attribute");
		System.out.println("7. Add Relationship");
		System.out.println("8. Delete Relationship");
		System.out.println("9. List Classes");
		System.out.println("10. List Class");
		System.out.println("11. Save");
		System.out.println("12. Load");
		System.out.println("13. Help");
		System.out.println("14. Exit");
		
		System.out.print("Choose one of the above menu items: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String choice = br.readLine();
		
		String seperator = " ";
		String[] parsedChoice = StringUtils.split(choice, seperator);
		
		boolean x = true;

		while (x) {
			if (parsedChoice[0].equals("add") && (parsedChoice[1].equals("class"))) {
				addClass(parsedChoice[2]);
			}
			else if (parsedChoice[0].equals("list") && (parsedChoice[1].equals("classes"))) {
				listClasses();
			}
			else if (parsedChoice[0].equals("quit")) {
				   break;
			}
			else if (parsedChoice[0].equals("save")) {
				   save(parsedChoice[1]);
			}
			else if (parsedChoice[0].equals("load")) {
				   load(parsedChoice[1]);
			}
			else if (parsedChoice[0].equals("list") && parsedChoice[1].equals("relationships")) {
					printRelationships();
			}
			else if (parsedChoice[0].equals("add") && parsedChoice[1].equals("relationship")) {
					createRelationship(parsedChoice[2], parsedChoice[3]);
			}
			else if (parsedChoice[0].equals("delete") && parsedChoice[1].equals("relationship")) {
					removeRelationship(parsedChoice[3]);
			}
			else if (parsedChoice[0].equals("list") && parsedChoice[1].equals("class")) {
					listClass(parsedChoice[2]);
			}
			else {
				System.out.println("Please enter a valid selection: ");			
			}
			System.out.print("Choose another menu item: ");
			choice = br.readLine();
			parsedChoice = StringUtils.split(choice, seperator);
		}

	}
	
	
	/** Adds a Class object to the classes SortedMap field. Still needs work, junit tests etc...
	 */
	public static void addClass(String name) {
		Class c = new Class(name);
		classes.put(name, c);
		System.out.println("You have created a new class named: " + name);
	}
	
	/** Lists the class.
	 * 
	 * @param className This represents the class we are going to list.
	 */
	public static String listClass(String className) 
	{
		String result = "";
		if (classes.containsKey(className)) {
			Class c = classes.get(className);
			System.out.println(c.toString());
			result = c.toString();
		}
		else {
			System.out.println("This class does not exist.");
		}
		return result;
	}
	
	/** Lists the Class objects stored in the classes SortedMap field. Still needs work, junit tests etc...
	 */
	public static void listClasses()
	{
			classes.forEach((key,value) -> System.out.println(value.getName() + " Attributes: " + value.printAttributes() ));
	}
	
	public static void printRelationships() {
		classes.forEach((key,value) -> System.out.println(value.listRelationships()));
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
	
	public static void createRelationship(String name, String type) {
		
		((Class) classes).addRelationship(name, type);
	}
	
	public static void removeRelationship(String name) {
		((Class) classes).deleteRelationship(name);
	}
	

	
	

}
