package team.indecision;

import java.util.HashSet;
import java.util.Set;

public class Class {
	 String name;
	 Set<String> attributes = new HashSet<String>();
	
	public Class() {
		
	}
	
	public Class(String namep) {
		name = namep;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public Set<String> getAttributes() {
		return attributes;
	}
	
	public void setAttributes(Set<String> newAttributes) {
		attributes = newAttributes;
	}
	
	public void addAttribute(String newAttribute) {
		attributes.add(newAttribute);
	}
}
