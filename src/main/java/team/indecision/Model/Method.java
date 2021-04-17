package team.indecision.Model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/** Represents a Method in the UML model.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew
 * @version 1.0
 * @since 1.0
 */
public class Method implements Comparable<Method>, Serializable{

	private static final long serialVersionUID = 1L;
	//Stores the return type.
	private String returnType;
	//Stores the method name.
	private String name;
	//Stores the methods parameter list.
	private SortedSet<Parameter> parameters = new TreeSet<Parameter>();
	
	/** Constructs an uninitialized instance of the object.
	 * 
	 */
	public Method() {
		
	}
	
	/** Constructs a method with a specified name.
	 * @param nameP A String that represents the method name.
	 */
	public Method(String nameP) {
		name = nameP;
	}
	
	/** Constructs a method with a specified name and parameters.
	 * @param nameP A String that represents the method name.
	 */
	public Method(String returnTypeP, String nameP, SortedSet<Parameter> parametersP) {
		name = nameP;
		returnType = returnTypeP;
		parameters = parametersP;
	}
	
	/** Gets the method's name.
	 * @return A String representing the method name.
	 */
	public String getName() {
		return name;
	}
	
	/** Sets the method's name.
	 * @param newName A String containing the method's name.
	 */
	public void setName(String newName) {
		name =  newName;
	}
	
	/** Gets the method's type.
	 * @return A String representing the method name.
	 */
	public String getType() {
		return returnType;
	}
	
	/** Sets the method's name.
	 * @param newName A String containing the method's name.
	 */
	public void setType(String newType) {
		returnType =  newType;
	}
	
	/** Gets the method's parameters.
	 * @return A List representing the method name.
	 */
	public SortedSet<Parameter> getParameters() {
		return parameters;
	}
	
	/** Sets the method's name.
	 * @param newParameters A String containing the method's name.
	 */
	public void setParameters(SortedSet<Parameter> newParameters) {
		parameters =  newParameters;
	}
	
	/** Add a Parameter
	 * @param newParameters A String containing the method's name.
	 */
	public void addParameter(String type, String name) {
		Parameter p = new Parameter(type, name);
		parameters.add(p);
	}
	
	/** Delete a Parameter
	 * @param newParameters A String containing the method's name.
	 */
	public void deleteParameter(String name) {
		Parameter p = getParameter(name);
		parameters.remove(p);
	}
	
	/** Gets a specified parameter from the method parameters set.
	 * @param name A String containing the parameter name.
	 * @return A Parameter from the parameters set returns null if it does not exist in the set.
	 */
	public Parameter getParameter(String name) {
		Iterator<Parameter> it = parameters.iterator();
		 Parameter p = null;
		 while (it.hasNext()) {
			 p = it.next();
			 if (p.getName().equals(name)) {
				 break;
			 }
			 p = null;
		 }
		 return p;
	}
	
	/** Checks if the specified parameter is in the set.
	 * @param name A String containing the class parameter name.
	 * @return Returns true if the method exists.
	 */
	public boolean containsParameter(String name) {
		boolean result = false;
		if (getParameter(name) != null) {
			result = true;
		}
		return result;
	}
	
	/** Represents this class as a String.
	 * @return A String containing this class.
	 */
	public String toString() {
		return returnType + " " + name + " " + parameters.toString();
	}
	
	/** Compares two class objects for equality.
	 * @param classObject A Class that will be compared to this class. 
	 * @return A boolean if the classes equal each other false if not.
	 */
	public boolean equals(Method classObject) {
        boolean result = false;
        if (classObject.getName().equals(this.getName()) && classObject.getParameters().equals(this.getParameters())) {
            result = true;
        }
        return result;
    }

	/** Compares two class objects for sorting purposes.
	 * @param o A Method that will be compared to this class. 
	 * @return An int that represents the sort order.
	 */
	@Override
	public int compareTo(Method o) {
		int nameDiff = this.name.compareTo(o.getName());
		if (nameDiff != 0) {
			return nameDiff;
		}
		if (parameters.equals(o.getParameters())) {
			return 0;
		}
		return -1;
	}
	
}
