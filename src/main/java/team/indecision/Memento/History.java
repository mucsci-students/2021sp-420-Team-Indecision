package team.indecision.Memento;

import java.util.Stack;

import team.indecision.Command.Command;

public class History {
	Stack<Pair> undoHistory = new Stack<Pair>();
	Stack<Command> redoHistory = new Stack<Command>();
	
	private class Pair {
		Command command;
		Memento memento;
		
		Pair(Command c, Memento m) {
			command = c;
			memento = m;
		}
		
		private Command getCommand() {
            return command;
        }

        private Memento getMemento() {
            return memento;
        }
	}
	
	public void pushUndo(Command c, Memento m) {
		undoHistory.add(new Pair(c,m));
	}
	
	public Pair popUndo() {
		return undoHistory.pop();
	}
	
	public void pushRedo(Command c) {
		redoHistory.add(c);
	}
	
	public Command popRedo() {
		return redoHistory.pop();
	}
	
	public boolean isEmptyUndo() {
		return undoHistory.isEmpty();
	}
	
	public boolean isEmptyRedo() {
		return redoHistory.isEmpty();
	}
	
	public void undo() {
		Pair p = this.popUndo();
		Memento m = p.getMemento();
		
		Command c = p.getCommand();
		this.pushRedo(c);
		
		m.restore();
	}
	
	public Command redo() {
		Command c = this.popRedo();
		return c;
	}
	
}
