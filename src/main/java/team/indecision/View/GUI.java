package team.indecision.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Polygon;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Map.Entry;

import javax.management.relation.Relation;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import team.indecision.Controller.GUIController;
import team.indecision.Model.Classes;
import team.indecision.Model.Relationship;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.*;

public class GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	private JMenuBar menuBar;
	private GUIController controller;

	public void setController(GUIController controllerP) {
		controller = controllerP;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		List<Component> componentList = getAllComponents(this);

		for (int i = 0; i < componentList.size(); i = i + 2) {
			Dimension d = new Dimension(100, 100);
			componentList.get(i).setSize(componentList.get(i + 1).getWidth(), componentList.get(i + 1).getHeight());
			// System.out.println(componentList.get(i).getX());

			revalidate();
			repaint();
		}

		for (Entry<String, team.indecision.Model.Class> entry : controller.getModel().getClasses().entrySet()) {

			if (entry.getValue().getRelationships() != null) {

				SortedSet<Relationship> relationships = entry.getValue().getRelationships();
				Iterator<Relationship> it = relationships.iterator();
				Relationship r = null;
				while (it.hasNext()) {
					r = it.next();
					int entryX = entry.getValue().getXLocation();
					int entryY = entry.getValue().getYLocation();
					int destinationX = controller.getModel().getClasses().get(r.getDestination()).getXLocation();
					int destinationY = controller.getModel().getClasses().get(r.getDestination()).getYLocation();

					JPanel panel = null;
					JPanel panelDestination = null;
					for (int i = 0; i < componentList.size(); i++) {
						if (componentList.get(i).getName().equals(r.getDestination() + "Panel")) {
							panelDestination = (JPanel) componentList.get(i);
						}
						if (componentList.get(i).getName().equals(entry.getValue().getName() + "Panel")) {
							panel = (JPanel) componentList.get(i);
						}
					}

					if (panel != null && panelDestination != null) {						
						if (r.getType().equals("Aggregation")){
							
							if(entry.getValue().getName().equals(r.getDestination())){
								// Main Line
								g2d.setStroke(new BasicStroke(5));
								int xLinePoly[] = {entryX + panel.getWidth(), entryX + 40 + panel.getWidth(), entryX + 40 + panel.getWidth(), entryX + panel.getWidth() + 25};
								int yLinePoly[] = {entryY + panel.getHeight() / 2, entryY + panel.getHeight() / 2, entryY + 5, entryY + 5};
								g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);


								int xTrianglePoly[] = {entryX + panel.getWidth() + 5, entryX + panel.getWidth() + 10 + 5, entryX + panel.getWidth() + 20 + 5, entryX + panel.getWidth() + 10 + 5};
								int yTrianglePoly[] = {destinationY + 5, destinationY - 5 + 5, destinationY + 5, destinationY + 5 + 5};
								Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly, xTrianglePoly.length);
								g2d.drawPolygon(triangle);
								g2d.setColor(new Color(255, 255, 255));
								g2d.fillPolygon(triangle);
								g2d.setColor(new Color(0, 0, 0));
							}
							
							else {
							
								// Main Line
								g2d.setStroke(new BasicStroke(5));
								int xLinePoly[] = {entryX + panel.getWidth(), entryX + 40 + panel.getWidth(), destinationX - 36, destinationX - 26 };
								int yLinePoly[] = {entryY + panel.getHeight() / 2, entryY + panel.getHeight() / 2, destinationY +  panelDestination.getHeight() / 2, destinationY +  panelDestination.getHeight() / 2 };
								g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

								// Arrow Point 
								int xTrianglePoly[] = {destinationX - 5, destinationX - 10 - 5, destinationX - 20 - 5, destinationX - 10 - 5};
								int yTrianglePoly[] = {destinationY + panelDestination.getHeight() / 2, destinationY - 5 + panelDestination.getHeight() / 2, destinationY + panelDestination.getHeight() / 2, destinationY + 5 + panelDestination.getHeight() / 2 };
								Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly, xTrianglePoly.length);
								g2d.drawPolygon(triangle);
								g2d.setColor(new Color(255, 255, 255));
								g2d.fillPolygon(triangle);
								g2d.setColor(new Color(0, 0, 0));
							}

							}
							
						else if (r.getType().equals("Composition")){

							if(entry.getValue().getName().equals(r.getDestination())){
								// Main Line
								g2d.setStroke(new BasicStroke(5));
								int xLinePoly[] = {entryX + panel.getWidth(), entryX + 40 + panel.getWidth(), entryX + 40 + panel.getWidth(), entryX + panel.getWidth() + 25};
								int yLinePoly[] = {entryY + panel.getHeight() / 2, entryY + panel.getHeight() / 2, entryY + 5, entryY + 5};
								g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);


								int xTrianglePoly[] = {entryX + panel.getWidth() + 5, entryX + panel.getWidth() + 10 + 5, entryX + panel.getWidth() + 20 + 5, entryX + panel.getWidth() + 10 + 5};
								int yTrianglePoly[] = {destinationY + 5, destinationY - 5 + 5, destinationY + 5, destinationY + 5 + 5};
								Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly, xTrianglePoly.length);
								g2d.drawPolygon(triangle);
								g2d.fillPolygon(triangle);
							}
							
							else {
							// Main Line
							g2d.setStroke(new BasicStroke(5));
							int xLinePoly[] = {entryX + panel.getWidth(), entryX + 40 + panel.getWidth(), destinationX - 36, destinationX - 26 };
							int yLinePoly[] = {entryY + panel.getHeight() / 2, entryY + panel.getHeight() / 2, destinationY +  panelDestination.getHeight() / 2, destinationY +  panelDestination.getHeight() / 2 };
							g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

							// Arrow Point 
							int xTrianglePoly[] = { destinationX - 5, destinationX - 10 - 5, destinationX - 20 - 5, destinationX - 10 - 5};
							int yTrianglePoly[] = { destinationY + panelDestination.getHeight() / 2, destinationY - 5 + panelDestination.getHeight() / 2, destinationY + panelDestination.getHeight() / 2, destinationY + 5 + panelDestination.getHeight() / 2 };
							Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly, xTrianglePoly.length);
							g2d.drawPolygon(triangle);
							g2d.fillPolygon(triangle);							
						}
					}
						else if (r.getType().equals("Inheritance")){

							if(entry.getValue().getName().equals(r.getDestination())){
								// Main Line
								g2d.setStroke(new BasicStroke(5));
								int xLinePoly[] = {entryX + panel.getWidth(), entryX + 40 + panel.getWidth(), entryX + 40 + panel.getWidth(), entryX + panel.getWidth() + 20};
								int yLinePoly[] = {entryY + panel.getHeight() / 2, entryY + panel.getHeight() / 2, entryY + 10, entryY + 10};
								g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);


								int xTrianglePoly[] = {entryX + panel.getWidth() + 5, entryX + panel.getWidth() + 10 + 5, entryX + panel.getWidth() + 5 + 10};
								int yTrianglePoly[] = {destinationY + 10, destinationY - 5 + 10, destinationY + 10 + 5};
								Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly, xTrianglePoly.length);
								g2d.drawPolygon(triangle);
								g2d.setColor(new Color(255, 255, 255));
								g2d.fillPolygon(triangle);
								g2d.setColor(new Color(0, 0, 0));
							}
							
							else {
								// Main Line
								g2d.setStroke(new BasicStroke(5));
								int xLinePoly[] = {entryX + panel.getWidth(), entryX + 40 + panel.getWidth(), destinationX - 30, destinationX - 10 };
								int yLinePoly[] = {entryY + panel.getHeight() / 2, entryY + panel.getHeight() / 2, destinationY +  panelDestination.getHeight() / 2, destinationY +  panelDestination.getHeight() / 2 };
								g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

								// Arrow Point 
								int xTrianglePoly[] = { destinationX - 5, destinationX - 10 - 5, destinationX - 10 - 5};
								int yTrianglePoly[] = { destinationY + panelDestination.getHeight() / 2, destinationY - 5 + panelDestination.getHeight() / 2, destinationY + 5 + panelDestination.getHeight() / 2 };
								Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly, xTrianglePoly.length);
								g2d.drawPolygon(triangle);
								g2d.setColor(new Color(255, 255, 255));
								g2d.fillPolygon(triangle);
								g2d.setColor(new Color(0, 0, 0));
							}
						}
						else if (r.getType().equals("Realization")){
							if(entry.getValue().getName().equals(r.getDestination())){
								// Main Line
								g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
								int xLinePoly[] = {entryX + panel.getWidth(), entryX + 40 + panel.getWidth(), entryX + 40 + panel.getWidth(), entryX + panel.getWidth() + 20};
								int yLinePoly[] = {entryY + panel.getHeight() / 2, entryY + panel.getHeight() / 2, entryY + 10, entryY + 10};
								g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);


								int xTrianglePoly[] = {entryX + panel.getWidth() + 5, entryX + panel.getWidth() + 10 + 5, entryX + panel.getWidth() + 5 + 10};
								int yTrianglePoly[] = {destinationY + 10, destinationY - 5 + 10, destinationY + 10 + 5};
								Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly, xTrianglePoly.length);
								g2d.setStroke(new BasicStroke(5));

								g2d.drawPolygon(triangle);
								g2d.setColor(new Color(255, 255, 255));
								g2d.fillPolygon(triangle);
								g2d.setColor(new Color(0, 0, 0));
							}
							
							else {
								// Main Line
								g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
								int xLinePoly[] = {entryX + panel.getWidth(), entryX + 40 + panel.getWidth(), destinationX - 30, destinationX - 10 };
								int yLinePoly[] = {entryY + panel.getHeight() / 2, entryY + panel.getHeight() / 2, destinationY +  panelDestination.getHeight() / 2, destinationY +  panelDestination.getHeight() / 2 };
								g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);
								// Arrow Point 
								int xTrianglePoly[] = { destinationX - 5, destinationX - 10 - 5, destinationX - 10 - 5};
								int yTrianglePoly[] = { destinationY + panelDestination.getHeight() / 2, destinationY - 5 + panelDestination.getHeight() / 2, destinationY + 5 + panelDestination.getHeight() / 2 };
								Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly, xTrianglePoly.length);
								g2d.setStroke(new BasicStroke(5));

								g2d.drawPolygon(triangle);
								g2d.setColor(new Color(255, 255, 255));
								g2d.fillPolygon(triangle);
								g2d.setColor(new Color(0, 0, 0));
							}
						}
						
					}
				}

			}
		}

	}

	public static List<Component> getAllComponents(final Container c) {
		Component[] comps = c.getComponents();
		List<Component> compList = new ArrayList<Component>();
		for (Component comp : comps) {
			compList.add(comp);
			if (comp instanceof Container)
				compList.addAll(getAllComponents((Container) comp));
		}
		return compList;
	}

	public GUI(Classes modelP) {
		// controller = controllerP;
		// List<Component> list = getAllComponents(this);
		// System.out.println(list.toString());

		frame = new JFrame("UML - Team Indecision");
		setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1280, 720));

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and feel not set on this system.");
		}

		menuBar = new JMenuBar();

		//////////////////////////// JMenueBar
		//////////////////////////// Classes//////////////////////////////////////

		JMenu classMenu = new JMenu("Class");
		menuBar.add(classMenu);

		JMenuItem addClassItem = new JMenuItem("Add Class");
		classMenu.add(addClassItem);

		JMenuItem deleteClassItem = new JMenuItem("Delete Class");
		classMenu.add(deleteClassItem);

		JMenuItem editClassNameItem = new JMenuItem("Edit Class Name");
		classMenu.add(editClassNameItem);

		//////////////////////////// JMenueBar
		//////////////////////////// Fields///////////////////////////////////////

		JMenu fieldMenu = new JMenu("Field");
		menuBar.add(fieldMenu);

		JMenuItem addFieldItem = new JMenuItem("Add Field");
		fieldMenu.add(addFieldItem);

		JMenuItem deleteFieldItem = new JMenuItem("Delete Field");
		fieldMenu.add(deleteFieldItem);

		JMenuItem editFieldNameItem = new JMenuItem("Edit Field Name");
		fieldMenu.add(editFieldNameItem);
		
		JMenuItem editFieldTypeItem = new JMenuItem("Edit Field Type");
		fieldMenu.add(editFieldTypeItem);

		//////////////////////////// JMenueBar
		//////////////////////////// Methods//////////////////////////////////////

		JMenu methodMenu = new JMenu("Method");
		menuBar.add(methodMenu);

		JMenuItem addMethodItem = new JMenuItem("Add Method");
		methodMenu.add(addMethodItem);

		JMenuItem deleteMethodItem = new JMenuItem("Delete Method");
		methodMenu.add(deleteMethodItem);

		JMenuItem editMethodNameItem = new JMenuItem("Edit Method Name");
		methodMenu.add(editMethodNameItem);

		JMenuItem editMethodParametersItem = new JMenuItem("Edit Method Parameters");
		methodMenu.add(editMethodParametersItem);

		//////////////////////////// JMenueBar
		//////////////////////////// Relationships////////////////////////////////

		JMenu relationshipMenu = new JMenu("Relationship");
		menuBar.add(relationshipMenu);

		JMenuItem addRelationshipItem = new JMenuItem("Add Relationship");
		relationshipMenu.add(addRelationshipItem);

		JMenuItem deleteRelationshipItem = new JMenuItem("Delete Relationship");
		relationshipMenu.add(deleteRelationshipItem);

		JMenuItem editRelationshipDestinationItem = new JMenuItem("Edit Relationship Destination");
		relationshipMenu.add(editRelationshipDestinationItem);

		JMenuItem editRelationshipTypeItem = new JMenuItem("Edit Relationship Type");
		relationshipMenu.add(editRelationshipTypeItem);

		//////////////////////////// JMenueBar Save and
		//////////////////////////// Load////////////////////////////////

		JMenu saveAndLoadMenu = new JMenu("Save and Load");
		menuBar.add(saveAndLoadMenu);

		JMenuItem saveItem = new JMenuItem("Save");
		saveAndLoadMenu.add(saveItem);

		JMenuItem loadItem = new JMenuItem("Load");
		saveAndLoadMenu.add(loadItem);

		//////////////////////////// JMenueBar Save and
		//////////////////////////// Load//////////////////////////////////////
		JMenu undoAndRedoMenu = new JMenu("Undo and Redo");
		menuBar.add(undoAndRedoMenu);

		JMenuItem undoItem = new JMenuItem("Undo");
		undoAndRedoMenu.add(undoItem);

		JMenuItem redoItem = new JMenuItem("Redo");
		undoAndRedoMenu.add(redoItem);

		frame.setJMenuBar(menuBar);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}

	public void addActionListener(ActionListener listener, int menuPosition, int menuItemPosition) {

		menuBar.getMenu(menuPosition).getItem(menuItemPosition).addActionListener(listener);
	}
}
