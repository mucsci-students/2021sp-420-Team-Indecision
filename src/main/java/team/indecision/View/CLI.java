package team.indecision.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.jline.builtins.Completers;
import org.jline.builtins.Completers.RegexCompleter;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.ParsedLine;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import team.indecision.Model.*;
import team.indecision.Model.Class;



/** A text-based REPL program for creating UML models.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class CLI {
	
	private class TabCompleter implements Completer { //gives us dynamic access to the completer through the setCompleter
		RegexCompleter c;

		public  TabCompleter() {
			Map<String, Completer> comp = new HashMap<>();
			comp.put("C1", new StringsCompleter("add class"));
			comp.put("C2", new StringsCompleter("add field"));
			comp.put("C21", new StringsCompleter("f1", "f2"));
			comp.put("C3", new StringsCompleter("add rel"));
			comp.put("C31", new StringsCompleter(""));
			comp.put("C32", new StringsCompleter(""));
			comp.put("C33", new StringsCompleter("Composition", "Aggregation"));
			
			c = new Completers.RegexCompleter("C1 | C2 C21 | C3 C31 C32 C33", comp::get);
			
		}
		
	    @Override
	    public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
	        c.complete(reader, line, candidates);
	    }

	    public void setCompleter(Classes model) {
	    	List<String> classes = new ArrayList<String>();
			List<String> fields = new ArrayList<String>();
			List<String> methods = new ArrayList<String>();
			List<String> relationships = new ArrayList<String>();
	    	
			for (SortedMap.Entry<String, Class> entry : model.getClasses().entrySet()) {
		        Class c = entry.getValue();
		        classes.add(c.getName());
		        if (!c.getFields().isEmpty()) {
		        	Iterator<Field> it = c.getFields().iterator();
			   		 while (it.hasNext()) {
			   			Field f = it.next();
			   			fields.add(f.getName());
			   		 }
		        }
		        if (!c.getMethods().isEmpty()) {
		        	Iterator<Method> it = c.getMethods().iterator();
			   		 while (it.hasNext()) {
			   			Method m = it.next();
			   			methods.add(m.toString());
			   		 }
		        }
				if (!c.getRelationships().isEmpty()) {
					Iterator<Relationship> it = c.getRelationships().iterator();
			   		 while (it.hasNext()) {
			   			Relationship r = it.next();
			   			relationships.add(r.getDestination());
			   		 }
				}
		    }
	    	
	    	Map<String, Completer> comp = new HashMap<>();
			comp.put("C1", new StringsCompleter("add class"));
			
			comp.put("C2", new StringsCompleter("add field"));
			comp.put("C21", new StringsCompleter(classes));
			
			comp.put("C3", new StringsCompleter("add rel"));
			comp.put("C31", new StringsCompleter(classes));
			comp.put("C32", new StringsCompleter(classes));
			comp.put("C33", new StringsCompleter("Composition", "Aggregation"));
			
			comp.put("C4", new StringsCompleter("add method"));
			comp.put("C41", new StringsCompleter(classes));
			
			comp.put("C5", new StringsCompleter("delete class"));
			comp.put("C51", new StringsCompleter(classes));
			
			comp.put("C6", new StringsCompleter("delete field"));
			comp.put("C61", new StringsCompleter(classes));
			comp.put("C62", new StringsCompleter(fields));
			
			comp.put("C7", new StringsCompleter("delete method"));
			comp.put("C71", new StringsCompleter(classes));
			comp.put("C72", new StringsCompleter(methods));
			
			comp.put("C8", new StringsCompleter("delete rel"));
			comp.put("C81", new StringsCompleter(classes));
			comp.put("C82", new StringsCompleter(relationships));
			
			comp.put("C9", new StringsCompleter("edit class"));
			comp.put("C91", new StringsCompleter(classes));
			
			comp.put("C10", new StringsCompleter("edit field name"));
			comp.put("C101", new StringsCompleter(classes));
			comp.put("C102", new StringsCompleter(fields));
			
			comp.put("C11", new StringsCompleter("edit field type"));
			comp.put("C111", new StringsCompleter(classes));
			comp.put("C112", new StringsCompleter(fields));
			
			comp.put("C12", new StringsCompleter("edit field type"));
			comp.put("C121", new StringsCompleter(classes));
			comp.put("C122", new StringsCompleter(fields));
			
			comp.put("C13", new StringsCompleter("edit rel dest"));
			comp.put("C131", new StringsCompleter(classes));
			comp.put("C132", new StringsCompleter(relationships));
			
			comp.put("C14", new StringsCompleter("edit rel type"));
			comp.put("C141", new StringsCompleter(classes));
			comp.put("C142", new StringsCompleter(relationships));

			comp.put("C15", new StringsCompleter("list classes"));
			
			comp.put("C16", new StringsCompleter("list class"));
			comp.put("C161", new StringsCompleter(classes));
			
			comp.put("C17", new StringsCompleter("list rel"));
			
			comp.put("C18", new StringsCompleter("save"));
			
			comp.put("C19", new StringsCompleter("load"));
			
			comp.put("C20", new StringsCompleter("undo"));
			
			comp.put("C210", new StringsCompleter("redo"));
			
			comp.put("C220", new StringsCompleter("help"));
			
			comp.put("C230", new StringsCompleter("exit"));
			
			
			
			c = new Completers.RegexCompleter("C1 | C2 C21 | C3 C31 C32 C33 | C4 C41 | C5 C51 | C6 C61 C62 | C7 C71 C72 | C8 C81 C82 | C9 C91 | C10 C101 C102 | C11 C111 C112 | C12 C121 C122 | C13 C131 C132 | C14 C141 C142 | C15 | C16 C161 | C17 | C18 | C19 | C20 | C210 | C220 | C230", comp::get);
	    }
	}

	private LineReader lr;
	private TabCompleter completer; //custom completer
	
	public CLI() {
		try {
			Terminal terminal = TerminalBuilder.terminal();
			completer = new TabCompleter();
			lr = LineReaderBuilder.builder().terminal(terminal).completer(completer).build(); //create the line reader pass in compltere and commands for completer
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TabCompleter getCompleter() {
		return completer;
	}
	
	public void setCompleter(Classes model) { //sets a new completer with new commands
		completer.setCompleter(model);
	}
	
	public void update(String response) {
		System.out.println(response);
	}
	
    public String prompt() {
        return lr.readLine("UML => ");
    }
}