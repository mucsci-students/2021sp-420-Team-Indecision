package team.indecision.Memento;

import java.util.Stack;

import team.indecision.Command.Command;

/** This class represents a History object that allows for undo and redo operations.
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama Getachew 
 * @version 1.0
 * @since 1.0
 */
public class History {
	
	//Stores a command and memento pair on a stack so that undo can be performed.
	Stack<Pair> undoHistory = new Stack<Pair>();
	//Stores commands on a stack so that redo can be performed.
	Stack<Command> redoHistory = new Stack<Command>();
	
	// This class represents a pair: a memento and a command.
	private class Pair {
		//Stores a command object.
		Command command;
		//Stores a memnto object.
		Memento memento;
		
		Pair(Command c, Memento m) {
			command = c;
			memento = m;
		}
		
		/** Gets the command.
		 * @return A command object.
		 */
		private Command getCommand() {
            return command;
        }
	
		/** Gets the memento.
		 * @return A memento object.
		 */
        private Memento getMemento() {
            return memento;
        }
	}
	
	/** Pushes a new pair onto the undoHistory stack.
	 */
	public void pushUndo(Command c, Memento m) {
		undoHistory.add(new Pair(c,m));
	}
	
	/** Pops a pair from the undoHistory stack.
	 * @return A Pair that contains a command and a memento that was last executed.
	 */
	public Pair popUndo() {
		return undoHistory.pop();
	}
	
	/** Pushes a new command onto the redoHistory stack.
	 */
	public void pushRedo(Command c) {
		redoHistory.add(c);
	}
	
	/** Pops a command from the redoHistory stack.
	 * @return A Pair that contains a command that was last executed.
	 */
	public Command popRedo() {
		return redoHistory.pop();
	}
	
	/** Checks if the stack is empty.
	 * @return A boolean true if empty false if not.
	 */
	public boolean isEmptyUndo() {
		return undoHistory.isEmpty();
	}
	
	/** Checks if the stack is empty.
	 * @return A boolean true if empty false if not.
	 */
	public boolean isEmptyRedo() {
		return redoHistory.isEmpty();
	}
	
	/** Undoes the most recent command by restoring the backup state and pushes the command onto the redo stack.
	 */
	public void undo() {
		Pair p = this.popUndo();
		Memento m = p.getMemento();
		
		Command c = p.getCommand();
		this.pushRedo(c);
		
		m.restore();
	}
	
	/** Returns the most recent command executed.
	 * @return A command that was most recently run and changed the state.
	 */
	public Command redo() {
		Command c = this.popRedo();
		return c;
	}
	
}
