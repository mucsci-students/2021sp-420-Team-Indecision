package team.indecision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.SortedMap;
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
		int selection = Integer.parseInt(br.readLine()); //Store user menu selection. Still needs validation etc..
		boolean x = true;
		while (x) {
			if (selection == 1) {
				addClass();	
			}
			else if (selection == 9) {
				listClasses();
			}
			else if (selection == 11) {
				   save();
			}
			else if (selection == 12) {
				   load();
			}
			else if (selection == 14) {
				  break;
			}
			else {
				System.out.println("Please enter a valid selection: ");
			}
			System.out.print("Choose another menu item: ");
			selection = Integer.parseInt(br.readLine());
		} 
	}
	
	/** Adds a Class object to the classes SortedMap field. Still needs work, junit tests etc...
	 */
	public static void addClass() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the Class Name:");
		String name = br.readLine();
		Class c = new Class(name);
		classes.put(name, c);
		System.out.println("You have created a new class named: " + name);
	}
	
	/** Lists the Class objects stored in the classes SortedMap field. Still needs work, junit tests etc...
	 */
	public static void listClasses() {
			classes.forEach((key,value) -> System.out.println(value.getName() + " Attributes: " + value.printAttributes() ));
	}
	
	/** Saves the classes SortedMap to a specified .json file. Still needs work, junit tests etc...
	 */
	public static void save() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("File name: ");
		String name = br.readLine();
		if (name.endsWith(".json")) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.writeValue(Paths.get(name).toFile(), classes);
			} catch (Exception ex) {
				System.out.println("Not a valid file name.");
			}	
		}
		else {
			System.out.println("Not a valid file type.");
		}
	}
	
	/** Loads a specified valid .json file into the classes SortedMap. Still needs work, junit tests etc...
	 */
	public static void load() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("File name: ");
			String name = br.readLine();
			ObjectMapper objectMapper = new ObjectMapper();
			classes = objectMapper.readValue(Paths.get(name).toFile(), new TypeReference<SortedMap<String, Class>>() {});
			classes.forEach((key,value) -> System.out.println(value.getName() + " Attributes: " + value.printAttributes() ));
		} catch (Exception ex) {
			System.out.println("Not valid json or file does not exist.");
		}
	}
	
}
