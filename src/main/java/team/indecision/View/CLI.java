package team.indecision.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


/** A text-based REPL program for creating UML models.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class CLI {
	
	private class TabCompleter implements Completer { //gives us dynamic access to the completer through the setCompleter
		RegexCompleter c;

		public  TabCompleter (RegexCompleter completer) {
			
			Map<String, Completer> comp = new HashMap<>();
			comp.put("C1", new StringsCompleter("add class"));
			comp.put("C11", new StringsCompleter("t1", "t2"));
			comp.put("C2", new StringsCompleter("add field"));
			comp.put("C21", new StringsCompleter("f1", "f2"));
			comp.put("C22", new StringsCompleter("arg21", "arg22", "arg23"));			
			
			RegexCompleter completer = new Completers.RegexCompleter("C1 C11 | C2 C21", comp::get);
			
			c = completer;
		}
		
	    @Override
	    public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
	        c.complete(reader, line, candidates);
	    }

	    public void setCompleter(StringsCompleter delegate) {
	        this.c = delegate;
	    }
	}

	private LineReader lr;
	private TabCompleter completer; //custom completer
	
	public CLI() {
		try {
			Terminal terminal = TerminalBuilder.terminal();
			
			completer = new TabCompleter(new StringsCompleter(candidates));
			lr = LineReaderBuilder.builder().terminal(terminal).completer(completer).build(); //create the line reader pass in compltere and commands for completer
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TabCompleter getCompleter() {
		return completer;
	}
	
	public void setCompleter(List<String> candidates) { //sets a new completer with new commands
		completer.setCompleter(new StringsCompleter(candidates));
	}
	
	public void update(String response) {
		System.out.println(response);
	}
	
    public String prompt() {
        return lr.readLine("UML => ");
    }
}