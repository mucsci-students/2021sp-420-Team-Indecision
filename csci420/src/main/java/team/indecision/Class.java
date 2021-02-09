package team.indecision;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/** Represents a Class in the UML model.
 * @author Connor Nissley
 * @version 1.0
 * @since 1.0
 */
public class Class {
	
	//Stores the class name.
	private String name;
	//Stores the attributes for the class.
	private Set<String> attributes = new HashSet<String>();
	//Stores the relationships for the class.
	private SortedMap<String, String> relationships = new TreeMap<String, String>();
	
	/** Constructs an uninitialized instance of the object.
	 * 
	 */
	public Class() {
		
	}
	
	/** Constructs a class with a specified name.
	 * @param nameP The class name.
	 */
	public Class(String nameP) {
		name = nameP;
	}
	
	/** Constructs a class with a specified name, attributes, and relationships.
	 * @param nameP The class name.
	 * @param attributesP The class name.
	 * @param relationshipsP The class name.
	 */
	public Class(String nameP, Set<String> attributesP, SortedMap<String, String> relationshipsP) {
		name = nameP;
		attributes = attributesP;
		relationships = relationshipsP;
	}
	
	/** Gets the class's name.
	 * @return A String representing the class name.
	 */
	public String getName() {
		return name;
	}
	
	/** Sets the class's name.
	 * @param newName A String containing the class name.
	 */
	public void setName(String newName) {
		name = newName;
	}
	
	/** Gets the class's attributes.
	 * @return A Set that stores the class attributes.
	 */
	public Set<String> getAttributes() {
		return attributes;
	}
	
	/** Sets the class's attributes.
	 * @param newAttributes A Set of Strings containing the class attributes.
	 */
	public void setAttributes(Set<String> newAttributes) {
		attributes = newAttributes;
	}
	
	/** Adds a new attribute to the class.
	 * @param newAttribute A Strings containing the class attribute.
	 */
	public void addAttribute(String newAttribute) {
		attributes.add(newAttribute);
	}
	
	/** Prints the attributes for this class.
	 * @return A String containing the class's attributes.
	 */
	public String printAttributes() {
		/*Probably should be updated/formatted differently.*/
		return attributes.toString();
	}
	
	/** Gets the class's relationships.
	 * @return A SortedMap that stores the class relationships.
	 */
	public SortedMap<String, String> getRelationships() {
		return relationships;
	}
	
	/** Sets the class's relationships.
	 * @param newRelationship A SortedMap of a (key) String that represents the name of the relationship and a (value) String that represents the type of the relationship.
	 */
	public void setRelationships(SortedMap<String, String> newRelationship) {
		relationships = newRelationship;
	}
	
	/** Adds a new relationship to the class.
	 * @param name A String that represents the name of the relationship.
	 * @param type A Strings that represents the type of the relationship.
	 */
	public void addRelationship(String name, String type) {
		relationships.put(name, type);
	}
	
	/** Prints the relationships for this class.
	 * @return A String containing the class's relationships.
	 */
	public String listRelationships() {
		return relationships.toString();
	}
	
	public void deleteRelationship(String name) {
		relationships.remove(name);
	}
	
	public void listSingleClass (){
		getName();
		printAttributes();
		listRelationships();
	}
	
	/** Represents this class as a String.
	 * @return A String containing this class.
	 */
	public String toString() {
        String result = this.getName() + " " + this.printAttributes() + " " + this.listRelationships(); 
        return result;
    }
	
}
