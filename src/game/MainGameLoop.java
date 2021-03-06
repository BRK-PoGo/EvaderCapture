package game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import logic.AngleChecker;
import logic.LineOfSightChecker;
import logic.Pair;
import logic.RadiusChecker;


public class MainGameLoop{
	private static int BASE_FPS=8;
	private  int fps= BASE_FPS;
	private double multiplyer = 100;
	private java.util.Timer timer;
	private  GamePanel gamePanel;
	private Graph graph;
	private boolean isRunning=true;
	private int count=0;
	private LineOfSightChecker checker = new LineOfSightChecker();
	private RadiusChecker radChecker = new RadiusChecker();
	private AngleChecker angChecker = new AngleChecker();
	private boolean pause = false;
	private long startTime;

	public MainGameLoop(GamePanel panel) {
		gamePanel=panel;
		graph = gamePanel.graph;
	
	}
	public void gameLoop()
	{
		newTimer();
	}
	class LoopyStuff extends TimerTask {
		
		int moveCounter = 0;
		
		public void run()						//loop
			{
			if(!pause){
				boolean hasMoved = false;
				
				if(count == 100)		//counting frames, for turn handling, from 1 to 100
					count = 1;
				else
					count++;
				for(Entity ent:graph.getEntities()){//move entities
					if(((int)100/ent.getSpeed())>=((double)100/ent.getSpeed())){
						if(count%(100/ent.getSpeed())==0  ||  ((100%ent.getSpeed())!=0 && count%(100%ent.getSpeed())==0)){
							ent.move(graph);
							hasMoved = true;
						}
					}else{
						if(count%(100/ent.getSpeed())==0  &&  (count%(100/(100%ent.getSpeed()))!=0)){
							ent.move(graph);
							hasMoved = true;
						}
					}
				}
				//create algorithm

				//Set algorithm's graph to this graph

				//checks if all evaders have been captured || Written by Tom
				if (hasMoved) {
					boolean check = true;
					moveCounter++;
					ArrayList<Pair> pairs = checker.checkEntities(graph);
					Entity evaderEnt = null;
					Entity pursuerEnt = null;
					for (Pair pair : pairs) {
						if ((pair.getEntity1().getNode().getValue().equals("evader") && pair.getEntity2().getNode().getValue().equals("pursuer")) || (pair.getEntity1().getNode().getValue().equals("pursuer") && pair.getEntity2().getNode().getValue().equals("evader"))) {
							if (pair.getEntity1().getNode().getValue().equals("evader")) {
								evaderEnt = pair.getEntity1();
								pursuerEnt = pair.getEntity2();
							} else {
								pursuerEnt = pair.getEntity1();
								evaderEnt = pair.getEntity2();
							}
							if (pair.getLineOfSight() && radChecker.RadiusCheck(pursuerEnt.getNode().getX(), evaderEnt.getNode().getX(), pursuerEnt.getNode().getY(), evaderEnt.getNode().getY(), pursuerEnt.getRadius())) {
								if (pursuerEnt.getDir().equals("UP")) {
									int uI = 1;
									int uJ = 0;
									int vI = pursuerEnt.getNode().getX() - evaderEnt.getNode().getX();
									int vJ = pursuerEnt.getNode().getY() - evaderEnt.getNode().getY();
									if (angChecker.checkAngle(vI, vJ, uI, uJ, pursuerEnt.getAngle()/2)) evaderEnt.setCapture(true);
								} else if (pursuerEnt.getDir().equals("DOWN")) {
									int uI = -1;
									int uJ = 0;
									int vI = pursuerEnt.getNode().getX() - evaderEnt.getNode().getX();
									int vJ = pursuerEnt.getNode().getY() - evaderEnt.getNode().getY();
									if (angChecker.checkAngle(vI, vJ, uI, uJ, pursuerEnt.getAngle()/2)) evaderEnt.setCapture(true);
								} else if (pursuerEnt.getDir().equals("LEFT")) {
									int uI = 0;
									int uJ = 1;
									int vI = pursuerEnt.getNode().getX() - evaderEnt.getNode().getX();
									int vJ = pursuerEnt.getNode().getY() - evaderEnt.getNode().getY();
									if (angChecker.checkAngle(vI, vJ, uI, uJ, pursuerEnt.getAngle()/2)) evaderEnt.setCapture(true);
								} else if (pursuerEnt.getDir().equals("LEFT")) {
									int uI = 0;
									int uJ = -1;
									int vI = pursuerEnt.getNode().getX() - evaderEnt.getNode().getX();
									int vJ = pursuerEnt.getNode().getY() - evaderEnt.getNode().getY();
									if (angChecker.checkAngle(vI, vJ, uI, uJ, pursuerEnt.getAngle()/2)) evaderEnt.setCapture(true);
								}
							}
						}
					}
					check = true;
					for (Evader evader : graph.getEvaders()) {
						if (check && !evader.getCapture()) check = false;
					}
					if (check) isRunning = false;
				}
				
				gamePanel.setGraph(graph);
        		gamePanel.repaint();			//update panel
        		if (!isRunning) {								//check
        			double estimatedTime = (System.nanoTime() - startTime)/1e9;
        			timer.cancel();				//exit loop
        			System.out.println("game is done");
        			System.out.println("moves: " + moveCounter);
        			System.out.println("time: " + estimatedTime);
        		}
    		}else{//else if pause, avoid void loop
    			try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.err.println("ERROR, sleep unavaiable");
				}
    		}
		}	
	}
	public void setPause(boolean pause) {
		this.pause=pause;
		
	}
	public boolean isPaused() {
		return pause;
	}
	public void setFps(int fps){
		this.fps=fps;
		newTimer();
		
	}
	public int getFPS(){
		return fps;
	}
	public void resetFps() {
		fps=BASE_FPS;
		newTimer();
	}
	public void setMultiplyer(double multiplyer){
		this.multiplyer=multiplyer;
		newTimer();
		
	}
	private void newTimer() {
		timer = new Timer();
		startTime = System.nanoTime();
    	timer.schedule(new LoopyStuff(), 0, 1000 / (int)(fps*(multiplyer/100))); //new timer at 'fps' , the timing mechanism
		
	}
}
 