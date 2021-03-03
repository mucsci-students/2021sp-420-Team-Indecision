package team.indecision;

import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/** Represents a collection of Class objects in the UML model.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class Classes {
	
	// stores a SortedMap of class objects.
	private SortedMap<String, Class> classes = new TreeMap<String, Class>();
	
	/** Constructs an uninitialized instance of the object.
	 * 
	 */
	public Classes () {
		
	}
	
	/** Constructs a classes map with a specified name.
	 * @param classesP The classes map name.
	 */
	public Classes (SortedMap<String, Class> classesP) {
		classes = classesP;
	}
	
	/** Gets the classes's map.
	 * @return A Map that stores all class objects.
	 */
	public SortedMap<String, Class> getClasses() {
		return classes;
	}
	
	/** Sets the classes's map.
	 * @param newClasses A Map that stores all class objects.
	 */
	public void setClasses(SortedMap<String, Class> newClasses) {
		classes = newClasses;
	}
	
	////////////////////////////INTERFACE//////////////////////////////////////
	
	/** Prints each Class object in the map.
	 */
	public void listClasses() {
		classes.forEach((key,value) -> System.out.println(value.toString()));
	}

	
	/** Prints each Class object in the map.
	 * @param className A String that represents the class that will be listed.
	 */
	public void listClass(String className) {
        if(classes.containsKey(className)) {
            Class c = classes.get(className);
            System.out.println(c.toString());
        }
        else {
            System.out.println("The class " + className + " does not exist");
        }
    }
	
	/** Prints all the relationships for the objects in the map.
	 */
	public void listRelationships() {
		classes.forEach((key,value) -> System.out.println(value.getName() + ": "+ value.printRelationships()));
	}
	
	public void help() {
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
	
	///////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////CLASS/////////////////////////////////////////
	
	/** Adds a class to to the classes field. If the class name already exists it does not add the class and instead displays a message.
	 * @param name A String that represents the class name.
	 */
	public void addClass(String name) {
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
	 * @param name A String that represents the class name.
	 */
	public void deleteClass(String name) {
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
	 * @param className A String that represents the class name.
	 * @param newClassName A String that represents the new class name.
	 */
	public void renameClass(String className, String newClassName) {
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
	
	///////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////FIELDS/////////////////////////////////////////
	
	/** Adds a field to the specified class in the classes map. If the class does not exist or the field already exists it prints an error.
	 * @param className A String that represents the class name.
	 * @param fieldName A String representing the name of the field.
	 */
	public void addField(String className, String fieldName) {
		if (classes.containsKey(className)) {
			Class c = classes.get(className);
			if (c.addField(fieldName)) {
				System.out.println("The field " + fieldName + " has been added to the class " + className + ".");
			}
			else {
				System.out.println("The field " + fieldName + " already exists with the class " + className + ".");
			}
		}
		else {
			System.out.println("The class "  +  className + " does not exist.");
		}
	}
	
	/** Deletes a field from the specified class in the classes map. If the class does not exist or the field already exists it prints an error.
	 * @param className A String that represents the class name.
	 * @param fieldName A String representing the name of the field.
	 */
	public void deleteField(String className, String fieldName) {
		if (classes.containsKey(className)) {
			Class c = classes.get(className);
			if (c.deleteField(fieldName)) {
				System.out.println("The field " +  fieldName + " has been deleted from class " + className + ".");
			}
			else {
				System.out.println("The field " +  fieldName + " does not exist with the class " + className + ".");
			}
		}
		else {
			System.out.println("The class " +  className + " does not exist.");
		}
	}
	
	/** Edits a current field for the specified class in the classes map. If the class does not exist or the field does not exist or if the newField name already exists it prints an error.
	 * @param className A String that represents the class name.
	 * @param fieldName A String representing the name of the field.
	 * @param newFieldName A String representing the new name of the field.
	 */
	public void editField(String className, String fieldName, String newFieldName) {
		if (classes.containsKey(className)) {
			Class c = classes.get(className);
			Field f = c.getField(fieldName);
			Field fn = c.getField(newFieldName);
			if(f != null && fn == null) {
				f.setName(newFieldName);
				System.out.println("The field " + fieldName + " has been renamed to " + newFieldName + ".");
			}
			else {
				if (fn != null) {
					System.out.println("The field " +  newFieldName + " already exists with the class " + className + ".");
				}
				else {
					System.out.println("The field " +  fieldName + " does not exist with the class " + className + ".");
				}
			}
		}
		else {
			System.out.println("The class " +  className + " does not exist.");
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////METHODS////////////////////////////////////////
	/** Adds a method to the specified class in the classes map. If the class does not exist or the method already exists it prints an error. 
	 * @param className A String that represents the class name.
	 * @param methodName A String that represents the method name.
	 * @param parameters A List of strings containing the parameters.
	 */
	public void addMethod(String className, String methodName, List<String> parameters) {
		//Needs to be implemented.
	}
	
	/** Deletes a method from the specified class in the classes map. If the class does not exist or the field already exists or if the new method name with the same parameters already exists it prints an error.
	 * @param className A String that represents the class name.
	 * @param methodName A String that represents the method name.
	 * @param parameters A List of strings containing the parameters.
	 */
	public void deleteMethod(String className, String methodName, List<String> parameters) {
		//Needs to be implemented.
	}
	
	/** Edits a methods name from the specified class in the classes map. If the class does not exist or the field already exists or if the new method name with the same parameters already exists it prints an error.
	 * @param className A String that represents the class name.
	 * @param methodName A String that represents the method name.
	 * @param parameters A List of strings containing the parameters.
	 * @param newMethodName A String that represents the new method name.
	 */
	public void editMethodName(String className, String methodName, List<String> parameters, String newMethodName) {
		//Needs to be implemented.
	}
	
	/** Edits a methods parameters from the specified class in the classes map. If the class does not exist or the field already exists or if the new method name with the same parameters already exists it prints an error.
	 * @param className A String that represents the class name.
	 * @param methodName A String that represents the method name.
	 * @param parameters A List of Strings containing the parameters.
	 * @param newParameters A List of Strings that represents the new methods name.
	 */
	public void editMethodParameters(String className, String methodName, List<String> parameters, List<String> newMethodParameters) {
		//Needs to be implemented.
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////RELATIONSHIPS//////////////////////////////////
	
	/** Adds a relationship to the specified class in the classes map. If the class does not exist or the relationship already exists it prints an error.
	 * @param className A String that represents the class name.
	 * @param relationshipDestination A string that represents the relationship destination class.
	 * @param relationshipType A string that represents the relationship type name.
	 */
	public void addRelationship(String className, String relationshipDestination, String relationshipType) {
		if (classes.containsKey(className)) {
			Class c = classes.get(className);
			if (c.addRelationship(relationshipDestination, relationshipType)) {
				System.out.println("The " + className +" has created a new relationship with class: " + relationshipDestination + " of type " + relationshipType);
			}
			else {
				System.out.println("A relationship with this class " + className + " already exists.");
			}
		}
		else {
			System.out.println("The class "  +  className + " does not exist.");
		}
	}
	
	/** Deletes a relationship from the specified class in the classes map. If the class does not exist or the relationship does not exist it prints an error.
	 * @param className A String that represents the class name.
	 * @param relationshipDestination A String that represents the relationship destination class.
	 * @param relationshipType A String that represents the relationship type name.
	 */
	public void deleteRelationship(String className, String relationshipDestination, String relationshipType) {
        if (classes.containsKey(className)) {
            Class c = classes.get(className);
            if (c.deleteRelationship(relationshipDestination, relationshipType)) {
                System.out.println("You have deleted a relationship with class: " + className + "of" + relationshipDestination + "with relationship type" + relationshipType);
            }
            else {
                System.out.println("A relationship with " + relationshipDestination + " does not exist.");
            }
        }
        else {
            System.out.println("The class " +  className + " does not exist.");
        }
    }
	/** Edits a current relationship for the specified class in the classes map. If the class does not exist or the relationshipDestination does not exist or if the newRelationshipDestination name already exists it prints an error.
	 * @param className A String that represents the class name.
	 * @param relationshipDestination A String representing the destination of the relationship.
	 * @param newRelationshipDestination A String representing the new destination of the relationship.
	 */
	public void editRelationshipDestination(String className, String relationshipDestination, String newRelationshipDestination) {
		//Needs to be implemented.
		if(classes.containsKey(className)) {
			Class c = classes.get(className);
			Relationship r = c.getRelationship(relationshipDestination);
			if(c.getRelationship(newRelationshipDestination) != null) {
				System.out.println("The new destination " +  newRelationshipDestination + " already exists.");
			}
			
			else if(r != null) {
				r.setDestination(newRelationshipDestination);
				System.out.println("The relationship destination " + relationshipDestination + " has been set to " + newRelationshipDestination + ".");
			}
			else {
				System.out.println("The destination " +  relationshipDestination + " does not exist.");
			}
	
		}
		else {
			System.out.println("The class " +  className + " does not exist.");
		}
	}
	
	/** Edits a current relationship's type for the specified class in the classes map. If the class does not exist or the relationshipDestination does not exist it prints an error.
	 * @param className A String that represents the class name.
	 * @param relationshipDestination A String representing the destination of the relationship.
	 * @param newRelationshipType A String representing the new type of the relationship.
	 */
	public void editRelationshipType(String className, String relationshipDestination, String newRelationshipType) {
		if (classes.containsKey(className)) {
            Class c = classes.get(className);
            Relationship r = c.getRelationship(relationshipDestination);
            Relationship rn = c.getRelationship(newRelationshipType);
            if(r != null && rn == null) {
                r.setType(newRelationshipType);
                System.out.println("The Relationship " + relationshipDestination + " type has been renamed to " + newRelationshipType + ".");
            }
            else {
                System.out.println("The relationship " +  relationshipDestination + " does not exist with the class " + className + ".");
            }
        }
        else {
            System.out.println("The class " +  className + " does not exist.");
        }
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////SAVE AND LOAD//////////////////////////////////
	
	/** Saves the classes SortedMap to a specified .json file. The file is saved to the program root dir.
	 * @param fileName A String that represents the class name.
	 */
	public void saveJSON(String fileName) {
		fileName = fileName.concat(".json");
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(Paths.get(fileName).toFile(), classes);
		} catch (Exception ex) {
			System.out.println("Not a valid file name.");
		}
		
	}
	
	/** Loads a specified valid .json file into the classes SortedMap.
	 * @param fileName A String that represents the class name.
	 */
	public void loadJSON(String fileName) {
		fileName = fileName.concat(".json");
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			classes = objectMapper.readValue(Paths.get(fileName).toFile(), new TypeReference<SortedMap<String, Class>>() {});
			this.listClasses();
		} catch (Exception ex) {
			System.out.println("Not a valid json file or the file does not exist.");
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
}
