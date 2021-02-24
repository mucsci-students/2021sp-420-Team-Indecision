package team.indecision;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private static Classes controller;
	private ArrayList<String> classes;
	
	public GUI(Classes controller) {
		this.controller = controller;
		
		frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 500));
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Class");
		menuBar.add(menu);
		
		addClassItem = new JMenuItem("Add Class");
		addClassItem.addActionListener(addClassListener());
		
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
				
				classes.add(newClassName);
				
				/*
				temp.add(lbl);
				add(lbl);
				frame.pack();
				*/
			}
		};
	}
	public ActionListener deleteClassListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String deletedClass = promptInput("Enter the class name to be deleted: ");
				controller.deleteClass(deletedClass);
				
				classes.remove(deletedClass);
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
				
				JPanel tmp = new JPanel();
				JLabel lbl = new JLabel(newName);
				
				classes.remove(original);
				classes.add(newName);
			}
		};
	}
	public String promptInput(String message) {
		// Prompt the user for input and return the input
		return JOptionPane.showInputDialog(frame, message);
	}
	
	public static void main(String[] args) {
		new GUI(controller);
	}
}
