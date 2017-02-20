package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.List;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import gamePlay.Game;
import updateable.Updateable;
import watchable.Watchable;

public class DesignPanel extends JPanel implements Watchable {

	private static final long serialVersionUID = 1L;
	DrawDesignPanel drawPanel = new DrawDesignPanel();
	private static final int D_W = 500;
    private static final int D_H = 500; 
    ArrayList<Updateable> updateables = new ArrayList<>();
	
	public DesignPanel(Game game) {
		updateables.add(game);
		MouseMover mouse = new MouseMover();
		this.addMouseListener(mouse);
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paints the cube component
        drawPanel.draw(g);
    }
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(D_W, D_H); // sets the dimensions
    }
	
	@Override
	public void update(Color[][] grid) {
		drawPanel.setGrid(grid);
		repaint();
	}
	
	private class MouseMover implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			int x = (int)(arg0.getX()-10)/40;
			int y = (int)(arg0.getY()-10)/40;
			for (Updateable up : updateables) {
				up.update(x, y);
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}
	}
}
