package team.indecision.Model;

/** Represents a Field in the UML model.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class Field implements Comparable<Field>{
	private String name;
	
	/** Constructs an uninitialized instance of the object.
	 * 
	 */
	public Field() {
		
	}
	
	/** Constructs a field with a specified name.
	 * @param nameP A String that represents the field name.
	 */
	public Field(String nameP) {
		name = nameP;
	}
	
	/** Gets the field's name.
	 * @return A String representing the field name.
	 */
	public String getName() {
		return name;
	}
	
	/** Sets the field's name.
	 * @param newDestination A String containing the field's name.
	 */
	public void setName(String newName) {
		name =  newName;
	}

	/** Represents this class as a String.
	 * @return A String containing this class.
	 */
	public String toString() {
		return name;
	}
	
	/** Compares two class objects for equality.
	 * @param classObject A Class that will be compared to this class. 
	 * @return A boolean if the classes equal each other false if not.
	 */
	public boolean equals(Method classObject) {
        boolean result = false;
        if (classObject.getName().equals(this.getName())) {
            result = true;
        }
        return result;
    }
	
	/** Compares two class objects for sorting purposes.
	 * @param o A Field that will be compared to this class. 
	 * @return An int that represents the sort order.
	 */
	@Override
	public int compareTo(Field o) {
		int name = this.name.compareTo(o.getName()); 
		return name;
	}
}