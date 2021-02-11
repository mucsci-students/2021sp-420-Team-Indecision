package team.indecision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.SortedMap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/** A text-based REPL program for creating UML models.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public final class UML {

	// Stores the Class objects for the UML.
	private static SortedMap<String, Class> classes = new TreeMap<String, Class>();
	
	public static SortedMap<String, Class> getClasses() {
		return classes;
	}

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

			if (parsedChoice.length == 3 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("class"))) {
				addClass(parsedChoice[2]);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("attr"))) {
				addAttribute(parsedChoice[2], parsedChoice[3]);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("rel"))) {
//				addRelationship(parsedChoice[2], parsedChoice[3]);
			}
			// delete
			else if (parsedChoice.length == 3 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("class"))) {
				deleteClass(parsedChoice[2]);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("attr"))) {
//				deleteAttribute(parsedChoice[2], parsedChoice[3]);
			}
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("delete") && (parsedChoice[1].equals("rel"))) {
//				deleteRelationship(parsedChoice[2], parsedChoice[3]);
			}
			// rename
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("rename") && (parsedChoice[1].equals("class"))) {
				renameClass(parsedChoice[2], parsedChoice[3]);
			}
			else if (parsedChoice.length == 5 && parsedChoice[0].equals("rename") && (parsedChoice[1].equals("attr"))) {
//				renameAttribute(parsedChoice[2], parsedChoice[3], parsedChoice[4]);
			}
			// list
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("classes"))) {
				listClasses();
			}
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("class"))) {
//				listClass(parsedChoice[0]);
			}
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("rel"))) {
//				listRelationships();
			}
			// save / load
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("save")) {
				   save(parsedChoice[1]);
			}
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("load")) {
				   load(parsedChoice[1]);
			}
      //misc
			else if (parsedChoice.length == 1 && parsedChoice[0].equals("help")) {
				   help();
			}
			else if (parsedChoice.length == 1 && parsedChoice[0].equals("exit")){
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
	
	/** Adds a class to to the classes field. If the class name already exists it does not add the class and instead displays a message.
	 * @param name A string that represents the class name.
	 */
	public static void addClass(String name) {
		Class c = new Class(name); 
		if (classes.put(name, c) == null) //.put will return null if there is no mapping for the key.
		{
			System.out.println("You have created a new class named: " + name);
		}
		else 
		{
			System.out.println("The class " + name + " already exists.");
		} 
	}
	
	/** Deletes a class from the classes field. If the class does not exists it does not delete the class and instead displays a message.
	 * @param name A string that represents the class name.
	 */
	public static void deleteClass(String name) {
		if (classes.remove(name) != null) //.remove will return null if there was no mapping for the key.
		{
			System.out.println("The class " + name + " has been deleted.");
		}
		else 
		{
			System.out.println("The class " + name + " does not exist.");
		}
	}
	
	/** Renames a class from the classes field. If the class name does not exist or the newClassName exists it does not rename the class and instead displays a message.
	 * @param className A string that represents the class name.
	 * @param newClassName A string that represents the new class name.
	 */
	public static void renameClass(String className, String newClassName) {
		if (classes.containsKey(className) && !classes.containsKey(newClassName))
		{
			Class c = classes.get(className);
			c.setName(newClassName);
			classes.remove(className, c); //We need to change the key value to do this we have to remove the mapping and then re-add it with then new name.
			classes.put(newClassName, c);
			System.out.println("You have renamed the class " + className + " to " + newClassName);
		}
		else 
		{
			if (!classes.containsKey(className))
			{
				System.out.println("The class " + className + " does not exist.");
			}
			else 
			{
				System.out.println("The new class name " + newClassName + " already exists.");
			} 	
		}
	}
	/** Adds an attribute to the attribute field. If the class does not exist or the attribute already exists it prints an error.
	 * @param className A string that represents the class name.
	 * @param attrName A string that represents the new attributes name.
	 */
	public static void addAttribute(String className, String attrName) throws IOException {	
		if(classes.containsKey(className)) {
			Class c = classes.get(className);
			if(c.addAttribute(attrName)) {
				System.out.println("You have created a new attribute named: " + attrName);
			}
			else {
				System.out.println("Error: That attribute already exists.");
			}
		}
		else {
			System.out.println("Error: That class does not exist.");
		}
	}
	
	/** Deletes an attribute from the attribute field. If the class does not exist or the attribute does not exist it prints an error.
	 * @param className A string that represents the class name.
	 * @param attrName A string that represents the attribute being deleted.
	 */
	public static void deleteAttribute(String className, String attrName) throws IOException {	
		if(classes.containsKey(className)) {
			Class c = classes.get(className);
			Set<String> attr = c.getAttributes();
			if(attr.contains(attrName)) {
				attr.remove(attrName);
				System.out.println("You have deleted the attribute named: " + attrName);
			}	
			else {
				System.out.println("Error: Attribute does not exist.");
			}
		}
		else {
			System.out.println("Error: That class does not exist.");
		}
	}
	
	/** Renames an attribute from the attribute field. If the class does not exist or the attribute does not exist it prints an error.
	 * @param className A string that represents the class name.
	 * @param attrName A string that represents the attribute being renamed.
	 * @param newAttrName A string that represents the new attribute name.
	 */
	public static void renameAttribute(String className, String oldAttrName, String newAttrName) throws IOException {	
		if(classes.containsKey(className)) {
			Class c = classes.get(className);
			Set<String> attr = c.getAttributes();
			if(attr.contains(oldAttrName) && !(attr.contains(newAttrName))) {
				attr.remove(oldAttrName);
				attr.add(newAttrName);	
				System.out.println("You have renamed " + oldAttrName + " to " + newAttrName);
			}	
			else {
				System.out.println("Error: Conflicting attribute names.");
			}
		}
		else {
			System.out.println("That class does not exist.");
		}
	}
	
	/** Lists the Class objects stored in the classes SortedMap field. Still needs work, junit tests etc...
	 */
	public static void listClasses()
	{
			classes.forEach((key,value) -> System.out.println(value.toString()));
	}
	
	/** Saves the classes SortedMap to a specified .json file. Still needs work, junit tests etc...
	 */
	public static void save(String name) {
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
			listClasses();
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
		System.out.println("delete class class_name - deletes given class");
		System.out.println("delete attr class_name attr_name - deletes given attribute in specified class");
		System.out.println("delete rel class1 class2 - deletes the relationship between given classes\n");
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
