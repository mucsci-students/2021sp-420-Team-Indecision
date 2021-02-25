package team.indecision;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	
	private JMenuItem addClassItem;
	private static Classes controller = new Classes();
	
	
	public GUI(Classes controller) {
		GUI.controller = controller;
		
		frame = new JFrame("guiUML");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 500));
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Class");
		menuBar.add(menu);
		
		addClassItem = new JMenuItem("Add Class");
		addClassItem.addActionListener(addClassListener());
		menu.add(addClassItem);

		addClassItem = new JMenuItem("Delete Class");
		addClassItem.addActionListener(deleteClassListener());
		menu.add(addClassItem);

		addClassItem = new JMenuItem("Rename Class");
		addClassItem.addActionListener(renameClassListener());
		menu.add(addClassItem);
		
		frame.setJMenuBar(menuBar);
		
		
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	public ActionListener addClassListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newClassName = promptInput("Enter the new class name: ");
				controller.addClass(newClassName);
				
				resetJFrame();
				refreshJFrame();
			}
		};
	}
	public ActionListener deleteClassListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String deletedClass = promptInput("Enter the class name to be deleted: ");
				controller.deleteClass(deletedClass);
				resetJFrame();
				refreshJFrame();
			}
		};
	}
	public ActionListener renameClassListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String original = promptInput("Enter the class you want to rename: ");
				String newName = promptInput("Enter the new name of the class: ");

				controller.renameClass(original, newName);
				
				resetJFrame();
				refreshJFrame();
				
				
			}
		};
	}
	public String promptInput(String message) {
		// Prompt the user for input and return the input
		return JOptionPane.showInputDialog(frame, message);
	}
	
	public void resetJFrame() {
		removeAll();
		revalidate();
		repaint();
	}
	
	public void refreshJFrame() {
		for (SortedMap.Entry<String, Class> entry : GUI.controller.getClasses().entrySet()) {
			JPanel temp = new JPanel();
			JLabel lbl = new JLabel(entry.getValue().toString());
			temp.add(lbl);
			add(lbl);
			frame.pack();
	    }
		
			
	}
		
	
	public static void main(String[] args) {
		new GUI(controller);
	}
}
