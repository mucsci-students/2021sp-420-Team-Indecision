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
import org.jline.reader.LineReader.Option;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.ParsedLine;
import org.jline.reader.impl.DefaultParser;
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
			   			methods.add(m.toStringTabCompleter());
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
			comp.put("C0", new StringsCompleter("add"));
			comp.put("C1", new StringsCompleter("delete"));
			comp.put("C2", new StringsCompleter("edit"));
			comp.put("C3", new StringsCompleter("list"));
			
			comp.put("C4", new StringsCompleter("class"));
			comp.put("C5", new StringsCompleter("field"));
			comp.put("C6", new StringsCompleter("method"));
			comp.put("C7", new StringsCompleter("rel"));
			comp.put("C8", new StringsCompleter("Composition", "Aggregation", "Inheritance", "Realization"));
			
			comp.put("C9", new StringsCompleter(classes));
			comp.put("C10", new StringsCompleter(fields));
			comp.put("C11", new StringsCompleter(methods));
			comp.put("C12", new StringsCompleter(relationships));
			
			comp.put("C13", new StringsCompleter("name"));
			comp.put("C14", new StringsCompleter("type"));
			comp.put("C15", new StringsCompleter("dest"));
			comp.put("C16", new StringsCompleter("parameters"));
			comp.put("C17", new StringsCompleter("classes"));
			
			comp.put("C20", new StringsCompleter("save"));
			comp.put("C21", new StringsCompleter("load"));
			comp.put("C22", new StringsCompleter("redo"));
			comp.put("C23", new StringsCompleter("undo"));
			comp.put("C24", new StringsCompleter("help"));
			comp.put("C25", new StringsCompleter("exit"));
			
			c = new Completers.RegexCompleter("C20 | C21 | C22 | C23 | C24 | C25 | C0 C4 | C0 C5 C9 | C0 C6 C9 | C0 C7 C9 C9 | C1 C4 C9 | C1 C5 C9 C10 | C1 C6 C9 C11 | C1 C7 C9 C12 | C2 C4 C9 | C2 C5 C13 C9 C10 | C2 C5 C14 C9 C10 | C2 C6 C13 C9 C11 | C2 C6 C14 C9 C11 | C2 C6 C16 C9 C11 | C2 C7 C15 C9 C12 | C2 C7 C14 C9 C12 | C3 C17 | C3 C4 C9 | C3 C7", comp::get);
			
	    }
	}

	private LineReader lr;
	private TabCompleter completer; //custom completer
	
	public CLI() {
		try {
			Terminal terminal = TerminalBuilder.terminal();
			completer = new TabCompleter();
			lr = LineReaderBuilder.builder().terminal(terminal).completer(completer).build();
			DefaultParser dp = (DefaultParser) lr.getParser();
			dp.setEscapeChars(new char[]{});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TabCompleter getCompleter() {
		return completer;
	}
	
	public void setCompleter(Classes model) {
		completer.setCompleter(model);
	}
	
	public void update(String response) {
		System.out.println(response);
	}
	
    public String prompt() {
        return lr.readLine("UML => ");
    }
}