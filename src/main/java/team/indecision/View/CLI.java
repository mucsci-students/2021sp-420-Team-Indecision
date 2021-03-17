package team.indecision.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;


/** A text-based REPL program for creating UML models.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class CLI {

	private final BufferedReader br;
	
	public CLI(Reader reader) {
		br = new BufferedReader(reader);
	}
	
	public void update(String response) {
		System.out.println(response);
	}
	
    public String prompt() {
        try {
            System.out.print("UML => ");
            return br.readLine();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}