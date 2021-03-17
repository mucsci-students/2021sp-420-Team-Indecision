package team.indecision.Memento;

import java.util.SortedMap;
import team.indecision.Model.Class;
import team.indecision.Model.Classes;

public class Memento {
	private Classes model;
	private SortedMap<String, Class> backup;
	
	public Memento(Classes modelP) {
		model = modelP;
		backup = modelP.getBackup();
	}
	
	public void restore() {
		model.setClasses(backup);
	}
}
