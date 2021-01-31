package team.indecision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.SortedMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UML {
	static SortedMap<String, Class> classes = new TreeMap<String, Class>();

	public static void main(String[] args) throws NumberFormatException, IOException {
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
		int selection = Integer.parseInt(br.readLine());
		boolean x = true;
		while (x) {
			if (selection == 1) {
				addClass();	
			}
			else if (selection == 4) {
				listClasses();
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
	
	public static void addClass() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the Class Name:");
		String name = br.readLine();
		Class c = new Class(name);
		classes.put(name, c);
		System.out.println("You have created a new class named: " + name);
	}
	
	public static void listClasses() {
			classes.forEach((key,value) -> System.out.println(value.getName()));
	}
	
	public static void save() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("File name: ");
			String name = br.readLine();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(Paths.get(name).toFile(), classes);
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
	
	public static void load() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("File name: ");
			String name = br.readLine();
			ObjectMapper objectMapper = new ObjectMapper();
			classes = objectMapper.readValue(Paths.get(name).toFile(), new TypeReference<SortedMap<String, Class>>() {});
			classes.forEach((key,value) -> System.out.println(value.getName()));
		} catch (Exception ex) {
			System.out.println("Not valid json or file does not exist.");
		}
	}
	
	/* IN PROGRESS 
	public static void addAttribute() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Choose class: ");
		String cname = br.readLine();
		System.out.println("Enter a new Attribute:");
		String aname = br.readLine();
	}
	*/
	
}
