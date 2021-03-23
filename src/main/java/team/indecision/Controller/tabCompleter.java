package team.indecision.Controller;
import team.indecision.Model.*;
import team.indecision.Model.Class;

import java.util.ArrayList;
import java.util.Arrays;

import org.jline.reader.Completer;
//aggregates completers together
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.NullCompleter;
import org.jline.reader.impl.completer.StringsCompleter;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

  public class tabCompleter {
    private AggregateCompleter completer;
    //null completer must be used because when argument completer has run out of all completers, the last completer is used by defgult. 
    public tabCompleter() {
    	
    	 StringsCompleter classFieldMethodRelationship = new StringsCompleter("class", "field", "method", "rel");
    	 StringsCompleter editThird = new StringsCompleter("name", "parameters", "dest", "type");
    	 StringsCompleter listSecond = new StringsCompleter("classes", "class", "rel");
    	 
    	 this.completer = new AggregateCompleter(
    			 new AggregateCompleter(addCommandsOne()),
    			 new ArgumentCompleter(
                 new StringsCompleter("add"),
                 classFieldMethodRelationship,
                 new NullCompleter()
                 ),
    			 new AggregateCompleter(
    	    			 new ArgumentCompleter(
    	                 new StringsCompleter("delete"),
    	                 classFieldMethodRelationship,
    	                 new NullCompleter()
    	                 )),
    	    	new AggregateCompleter(
    	    	    	new ArgumentCompleter(
    	    	        new StringsCompleter("edit"),
    	    	        classFieldMethodRelationship,
    	    	        editThird,
    	    	        new NullCompleter()
    	    	        )),
    	    	new AggregateCompleter(
    	    	    	new ArgumentCompleter(
    	    	        new StringsCompleter("list"),
    	    	        listSecond,
    	    	        new NullCompleter()
    	    	        ))
    			 );
    }
    /*
    public AggregateCompleter userCreatedCompleter(Classes Classes) {
    	ArrayList<Completer> getCompleters = new ArrayList<Completer>();
    	ArrayList<String> getClassNames = new ArrayList<>();
    	ArrayList<String> getFields = new ArrayList<>();
    	ArrayList<String> getMethods = new ArrayList<>();
    	//add completers based off lists
    	for(int x = 0; x < Classes.getClasses().size(); ++x) {
    		Class returnClass = Classes.getClasses().get(x);
    		String className = returnClass.getName();
    		getClassNames.add(className);
    		//add all field names to completer
    		for(Field retrieveF : returnClass.getFields()) {
    			getFields.add(retrieveF.getName());
    			getCompleters.add (
    					new ArgumentCompleter(
    							new StringsCompleter("edit", "delete"),
    							new StringsCompleter("field"),
    							new StringsCompleter(className),
    							new StringsCompleter(retrieveF.getName()),
    							new NullCompleter()));
    		}
    		//add all method names to completer
    		for(Method retrieveM : returnClass.getMethods()) {
    			getMethods.add(retrieveM.getName());
    			getCompleters.add (
    					new ArgumentCompleter(
    							new StringsCompleter("edit", "delete"),
    							new StringsCompleter("method"),
    							//new StringsCompleter(className),
    							new StringsCompleter(retrieveM.getName()),
    							new NullCompleter()));
    	}
    	
    	
    	
    	
    	
    	
    }
    	return completer;
    }*/
    
    
    private ArrayList<Completer> addCommandsOne()
    { 
        ArrayList<String> commandsOne = new ArrayList<>(Arrays.asList("save", "load", "help", "undo", "redo", "exit"));
        ArrayList<Completer> completers = new ArrayList<Completer>();
        //add commands to string completer
        for(String getCommandsOne : commandsOne) {
            //this.completer = new AggregateCompleter(new ArgumentCompleter(new StringsCompleter(getCommandsOne),new NullCompleter()));
        	completers.add(new ArgumentCompleter(new StringsCompleter(getCommandsOne), new NullCompleter()));
        }
        return completers;
    } 
    

}
