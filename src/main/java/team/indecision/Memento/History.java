package team.indecision.Memento;

import java.util.Stack;

import team.indecision.Command.Command;

public class History {
	Stack<Pair> history = new Stack<Pair>();
	
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
	
	public void push (Command c, Memento m) {
		history.add(new Pair(c,m));
	}
	
	public Pair pop() {
		return history.pop();
	}
	
	public void undo () {
		Pair p = this.pop();
		Memento m = p.getMemento();
		m.restore();
	}
	
	public boolean isEmpty() {
		return history.isEmpty();
	}
	
}
