package gui;

import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import gamePlay.Game;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public MainFrame(DesignPanel designPanel) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(designPanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.revalidate();
	}
	
	public void showPanel() {
		this.setVisible(true);
	}
	
	

}
