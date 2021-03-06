package view;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// This class creates the main JFrame for the GUI

public class Frame extends JFrame {
	
	Grid grid; //Grid that displays the visualization
	
	public Frame(int width, int height) {
		
		//Create the frame
		this.setSize(width, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Pathfinding Visualization");
		
		//Create the grid
		grid = new Grid(width, height);
		grid.setPreferredSize(new Dimension(width, height));
		this.add(grid);
		
		//Make the frame visible
		this.pack();
		this.setVisible(true);
		
		//Set up mouse listener to draw and remove borders
		//Remember to add a normal mouse adapter
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				if (SwingUtilities.isLeftMouseButton(e)) {
					grid.drawBorder(x, y);
					repaint();
				}
				else if (SwingUtilities.isRightMouseButton(e)) {
					grid.removeBorder(x, y);
					repaint();
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {}
		});
			
		//Set up key listener to listen for placing start and end nodes
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
		
				if (e.getKeyCode() == KeyEvent.VK_S) {
					int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
					int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
					grid.removeBorder(x, y); //This doesn't work visually
					grid.setStart(x, y);
					repaint();
				}
				else if (e.getKeyCode() == KeyEvent.VK_E) {
					int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
					int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
					grid.removeBorder(x, y); //This doesn't work visually
					grid.setEnd(x, y);
					repaint();
				}
				else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					grid.startVisualization();
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
	}
	
}
