package team.indecision.Model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/** Represents a Class in the UML model.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class Class implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//Stores the class name.
	private String name;
	//Stores the fields for the class.
	private SortedSet<Field> fields = new TreeSet<Field>();
	//Stores the methods for the class.
	private SortedSet<Method> methods = new TreeSet<Method>();
	//Stores the relationships for the class.
	private SortedSet<Relationship> relationships = new TreeSet<Relationship>();
	//Stores GUI location data
	private int x;
	//Stores GUI location data
	private int y;
	
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
	
	/** Constructs a class with a specified name, fields, methods and relationships.
	 * @param nameP The class name.
	 * @param fieldsP The class fields name.
	 * @param methodsP The class methods name.
	 * @param relationshipsP The class relationships name.
	 * @param xP 
	 * @param yP 
	 */
	public Class(String nameP, SortedSet<Field> fieldsP, SortedSet<Method> methodsP, SortedSet<Relationship> relationshipsP, int xP, int yP) {
		name = nameP;
		fields = fieldsP;
		methods = methodsP;
		relationships = relationshipsP;
		x = xP;
		y = yP;
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
	
	/** Gets the class's fields.
	 * @return A SortedSet that stores the class fields.
	 */
	public SortedSet<Field> getFields() {
		return fields;
	}
	
	/** Sets the class's fields.
	 * @param newFeilds A SortedSet of Fields containing the class fields.
	 */
	public void setFields(SortedSet<Field> newFields) {
		fields = newFields;
	}
	
	/** Adds a new field to the class.
	 * @param newField A String containing the class field name.
	 * @return A boolean true if it is added and false if it already exists or is not added.
	 */
	public boolean addField(String type, String newField) {
		Field f = new Field (type , newField);
		return fields.add(f);
	}
	
	/** Deletes an existing field from the class.
	 * @param name A String containing the class field name.
	 * @return A boolean true if it is deleted and false if it does not exist.
	 */
	public boolean deleteField(String type, String name) {
		Field f = new Field (type, name);
		return fields.remove(f);
	}
	
	/** Gets a specified field from the class fields set.
	 * @param name A String containing the class field name.
	 * @return A Field from the fields set returns null if it does not exist in the set.
	 */
	public Field getField(String name) {
		 Iterator<Field> it = fields.iterator();
		 Field f = null;
		 while (it.hasNext()) {
			 f = it.next();
			 if (f.getName().equals(name)) {
				 break;
			 }
			 f = null;
		 }
		 return f;
	}
	
	/** Checks if the specified field is in the set.
	 * @param name A String containing the class field name.
	 * @return Returns true if the field exists.
	 */
	public boolean containsField(String name) {
		boolean result = false;
		if (getField(name) != null) {
			result = true;
		}
		return result;
	}
	
	/** Prints the fields for this class.
	 * @return A String containing the class's fields.
	 */
	public String printFields() {
		return fields.toString();
	}
	
	/** Gets the class's methods.
	 * @return A SortedSet that stores the class methods.
	 */
	public SortedSet<Method> getMethods() {
		return methods;
	}
	
	/** Sets the class's methods.
	 * @param newFeilds A SortedSet of methods containing the class methods.
	 */
	public void setMethods(SortedSet<Method> newMethods) {
		methods = newMethods;
	}
	
	/** Adds a new method to the class.
	 * @param newField A String containing the class method name.
	 * @return A boolean true if it is added and false if it already exists or is not added.
	 */
	public boolean addMethod(String newReturnType,String newMethod,String newParameterType, List<String> newParameters) {
		Method m = new Method( newReturnType, newMethod, newParameterType, newParameters);
		return methods.add(m);
	}
	
	/** Deletes an existing method from the class.
	 * @param name A String containing the class method name.
	 * @return A boolean true if it is deleted and false if it does not exist.
	 */
	public boolean deleteMethod(String returnType,String name,String ParameterType, List<String> parameters) {
		Method m = new Method(returnType, name,ParameterType,parameters);
		return methods.remove(m);
	}
	
	/** Gets a specified method from the class methods set.
	 * @param name A String containing the class method name.
	 * @return A Method from the methods set returns null if it does not exist in the set.
	 */
	public Method getMethod(String returnType, String name, String parameterType, List<String> parameters) {
		 Iterator<Method> it = methods.iterator();
		 Method m = null;
		 while (it.hasNext()) {
			 m = it.next();
			 if (m.getReturnType().equals(returnType) && m.getName().equals(name) && m.getparametersType().equals(parameterType) && m.getParameters().equals(parameters)) {
				 break;
			 }
			 m = null;
		 }
		 return m;
	}
	
	
	/** Checks if the specified method is in the set.
	 * @param name A String containing the class method name.
	 * @return Returns true if the method exists.
	 */
	public boolean containsMethod(String returnType, String name, String parameterType, List<String> parameters) {
		boolean result = false;
		if (getMethod(returnType, name, parameterType, parameters) != null) {
			result = true;
		}
		return result;
	}
	
	/** Prints the methods for this class.
	 * @return A String containing the class's methods.
	 */
	public String printMethods() {
		return methods.toString();
	}
	
	/** Gets the class's relationships.
	 * @return A SortedMap that stores the class relationships.
	 */
	public SortedSet<Relationship> getRelationships() {
		return relationships;
	}
	
	/** Sets the class's relationships.
	 * @param newRelationship A SortedSet of Relationship objects that contains the class's relationships.
	 */
	public void setRelationships(SortedSet<Relationship> newRelationships) {
		relationships = newRelationships;
	}
	
	/** Adds a new relationship to the class.
	 * @param name A String that represents the name of the relationship.
	 * @param type A String that represents the type of the relationship.
	 * @return A boolean true if it is added and false if it already exists or is not added.
	 */
	public boolean addRelationship(String destination, String type) {
		Relationship r = new Relationship (destination, type);
		return relationships.add(r);
	}
	
	/** Deletes an existing relationship from the class.
	 * @param name A String that represents the name of the relationship.
	 * @param type A String that represents the type of the relationship.
	 * @return A boolean true if it is deleted and false if it does not exist.
	 */
	public boolean deleteRelationship(String destination) {
		Relationship r = new Relationship (destination);
		return relationships.remove(r);
	}
	
	/** Gets a specified relationship from the class relationships set.
	 * @param destination A String containing the relationship destination name.
	 * @return A Relationship from the relationships set returns null if it does not exist in the set.
	 */
	public Relationship getRelationship(String destination) {
		 Iterator<Relationship> it = relationships.iterator();
		 Relationship r = null;
		 while (it.hasNext()) {
			 r = it.next();
			 if (r.getDestination().equals(destination)) {
				 break;
			 }
			 r = null;
		 }
		 return r;
	}
	
	/** Checks if the specified relationship is in the set.
	 * @param name A String containing the class relationship name.
	 * @return Returns true if the relationship exists.
	 */
	public boolean containsRelationship(String destination) {
		boolean result = false;
		if (getRelationship(destination) != null) {
			result = true;
		}
		return result;
	}
	
	/** Prints the relationships for this class.
	 * @return A String containing the class's relationships.
	 */
	public String printRelationships() {
		return relationships.toString();
	}
	
	/** Represents this class as a String.
	 * @return A String containing this class.
	 */
	public String toString() {
		String result = this.getName() + " " + this.printFields() + " " + this.printMethods() + " " + this.printRelationships(); 
        return result;
	}
	
	public String toStringGUI() {
		String result = "<html>" + this.getName() + "<hr/> Fields:" + this.printFields() + " <hr/> Methods:" + this.printMethods() + "<hr/> Relationships:" + this.printRelationships() + "</html>"; 
        return result;
	}

	
	/** Compares two class objects for equality.
	 * @param classObject A Class that will be compared to this class. 
	 * @return A boolean if the classes equal each other false if not.
	 */
	public boolean equals(Class classObject) {
        boolean result = false;
        if (classObject.getName().equals(this.getName()) && classObject.getFields().equals(this.getFields()) && classObject.getMethods().equals(this.getMethods()) && classObject.getRelationships().equals(this.getRelationships()) ) {
            result = true;
        }
        return result;
    }
	
	
	public int getXLocation() {
		return x;
	}
	
	public int getYLocation() {
		return y;
	}
	
	public void setXLocation(int xP) {
		x = xP;
	}
	
	public void setYLocation(int yP) {
		y = yP;
	}
}

