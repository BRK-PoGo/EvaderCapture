package game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import logic.AngleChecker;
import logic.LineOfSightChecker;
import logic.Pair;
import logic.RadiusChecker;


public class MainGameLoop{
	private static int BASE_FPS=4;
	private  int fps= BASE_FPS;
	private java.util.Timer timer;
	private  GamePanel gamePanel;
	private Graph graph;
	private boolean isRunning=true;
	private int count=0;
	private LineOfSightChecker checker = new LineOfSightChecker();
	private RadiusChecker radChecker = new RadiusChecker();
	private AngleChecker angChecker = new AngleChecker();

	public MainGameLoop(GamePanel panel) {
		gamePanel=panel;
		graph = gamePanel.graph;
	
	}
	public void gameLoop()
	{
		timer = new Timer();
    	timer.schedule(new LoopyStuff(), 0, 1000 / fps); //new timer at 'fps' , the timing mechanism
	}
	class LoopyStuff extends TimerTask {
		@Override
		public void run()						//loop
			{
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
				
				
        		gamePanel.repaint();			//update panel
        		if (!isRunning) {								//check
        			timer.cancel();				//exit loop
        			System.out.println("game is done");
        		}
    		}
				
	}
}
 