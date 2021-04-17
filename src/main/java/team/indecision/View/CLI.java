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
			comp.put("C31", new StringsCompleter(""));
			comp.put("C32", new StringsCompleter("Composition", "Aggregation"));
			
			c = new Completers.RegexCompleter("C1 | C2 C21 | C3 C31 C32", comp::get);
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