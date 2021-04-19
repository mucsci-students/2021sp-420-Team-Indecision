package team.indecision.Memento;

import java.util.SortedMap;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;

/** This class represents a Memento object that allows for undo operation.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class Memento {
	
	//Stores the model.
	private Classes model;
	//Stores the backup of the classes model that is passed in.
	private SortedMap<String, Class> backup;
	
	/** Constructs a Memento with the desired model.
	 */
	public Memento(Classes modelP) {
		model = modelP;
		backup = modelP.getBackup();
	}
	
	/** Sets the classes field of the model to the backup. This restores the model to the previous state.
	 */
	public void restore() {
		model.setClasses(backup);
	}
}