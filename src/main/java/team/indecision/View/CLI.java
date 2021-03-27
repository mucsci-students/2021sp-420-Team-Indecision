package team.indecision.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		StringsCompleter c;

		public  TabCompleter (StringsCompleter completer) {
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
	private List<String> candidates = new ArrayList<String>(); //commands to autocomplete
	
	public CLI() {
		try {
			Terminal terminal = TerminalBuilder.terminal();
			candidates.add("add"); 
			candidates.add("delete");
			candidates.add("edit");
			candidates.add("list");
			candidates.add("save");
			candidates.add("load");
			candidates.add("undo");
			candidates.add("redo");
			candidates.add("help");
			candidates.add("exit");
			candidates.add("class");
			candidates.add("classes");
			candidates.add("rel");
			candidates.add("field");
			candidates.add("method");
			candidates.add("name");
			candidates.add("parameters");
			candidates.add("dest");
			candidates.add("type");
			completer = new TabCompleter(new StringsCompleter(candidates));
			lr = LineReaderBuilder.builder().terminal(terminal).completer(completer).build(); //create the line reader pass in compltere and commands for completer
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TabCompleter getCompleter() {
		return completer;
	}
	
	public List<String> getCandidates() {
		return candidates; 
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