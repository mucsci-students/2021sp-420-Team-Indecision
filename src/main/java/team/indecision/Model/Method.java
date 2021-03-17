package team.indecision.Model;

import java.util.ArrayList;
import java.util.List;

/** Represents a Method in the UML model.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew
 * @version 1.0
 * @since 1.0
 */
public class Method implements Comparable<Method>{
	
	// stores the method name.
	private String name;
	// stores the methods parameter list.
	private List<String> parameters = new ArrayList<String>();
	
	/** Constructs an uninitialized instance of the object.
	 * 
	 */
	public Method() {
		
	}
	
	public Method (Method that) {
		this(that.getName(), new ArrayList<String>(that.getParameters()));
	}
	
	/** Constructs a method with a specified name.
	 * @param nameP A String that represents the method name.
	 */
	public Method(String nameP, List<String> parametersP) {
		name = nameP;
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
	
	/** Gets the method's parameters.
	 * @return A List representing the method name.
	 */
	public List<String> getParameters() {
		return parameters;
	}
	
	/** Sets the method's name.
	 * @param newParameters A String containing the method's name.
	 */
	public void setParameters(List<String> newParameters) {
		parameters =  newParameters;
	}
	
	/** Represents this class as a String.
	 * @return A String containing this class.
	 */
	public String toString() {
		return name + " " + parameters.toString();
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
		int name = this.name.compareTo(o.getName()); 
		return name;
	}
	
}
