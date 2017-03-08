package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel{
	
	private inputProcessor inputs = new inputProcessor();
	private final int OFFSET = 10;
	
	public MainPanel() {
		mouseInput mouseListen = new mouseInput();
		keyboardInput keyListen = new keyboardInput();
		this.addMouseListener(mouseListen); this.addKeyListener(keyListen);
	}
	
	private class mouseInput implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (inputs.getState() == 1) {
				inputs.addPoint(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
	}
	
	private class keyboardInput implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_C) inputs.changeState();
		}
		
	}

	private class inputProcessor {
		
		private int state = 0;
		
		public void changeState() {
			if (state == 0) state = 1;
			else state = 0;
		}
		
		public int getState() {
			return state;
		}
		
		public void addPoint(int x, int y) {
			
		}
		
	}
}
