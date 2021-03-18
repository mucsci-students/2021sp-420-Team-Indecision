package team.indecision.Model;

import java.io.Serializable;

/** Represents a Relationship in the UML model.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class Relationship implements Comparable<Relationship>, Serializable {
	
	private static final long serialVersionUID = 1L;
	// stores the relationship destination
	private String destination;
	// stores the relationship type
	private String type;
	
	/** Constructs an uninitialized instance of the object.
	 * 
	 */
	public Relationship() {
		
	}
	
	/** Constructs a relationship with a specified destination and type.
	 * @param destinationP A String that represents destination name.
	 * @param typeP A String that represents type name.
	 */
	public Relationship(String destinationP, String typeP) {
		destination = destinationP;
		type = typeP;
	}
	
	
	public Relationship(String destinationP) {
		destination = destinationP;
	}
	
	/** Gets the relationship's destination.
	 * @return A String representing the destination name.
	 */
	public String getDestination() {
		return destination;
	}
	
	/** Sets the relationship's destination.
	 * @param newDestination A String containing the destination name.
	 */
	public void setDestination(String newDestination) {
		destination = newDestination;
	}
	
	/** Gets the relationship's type.
	 * @return A String representing the type name.
	 */
	public String getType() {
		return type;
	}
	
	/** Sets the relationship's type.
	 * @param newType A String containing the type name.
	 */
	public void setType(String newType) {
		type = newType;
	}
	
	/** Represents this class as a String.
	 * @return A String containing this class.
	 */
	public String toString() {
		return destination + " " + type; 
	}
	
	/** Compares two class objects for equality.
	 * @param classObject A Class that will be compared to this class. 
	 * @return A boolean if the classes equal each other false if not.
	 */
	public boolean equals(Relationship classObject) {
        boolean result = false;
        if (classObject.getDestination().equals(this.getDestination()) && classObject.getType().equals(this.getType())) {
            result = true;
        }
        return result;
    }
	
	/** Compares two class objects for sorting purposes.
	 * @param o A Relationship that will be compared to this class. 
	 * @return An int that represents the sort order.
	 */
	@Override
	public int compareTo(Relationship o) {
		int destination = this.destination.compareTo(o.getDestination()); 
		return destination;
	}
	
}
