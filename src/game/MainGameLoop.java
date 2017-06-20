package game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
					
					for (Pair pair : pairs) {
						Entity e1 = pair.getEntity1();
						Entity e2 = pair.getEntity2();
						if ((e1.getNode().getValue().equals("evader") && e2.getNode().getValue().equals("pursuer")) || (e1.getNode().getValue().equals("pursuer") && e2.getNode().getValue().equals("evader"))) {
							if (pair.getLineOfSight()) {// && radChecker.RadiusCheck(e1.getNode().getX(), e2.getNode().getX(), e1.getNode().getY(), e2.getNode().getY(), e1.getRadius())) {
								if (e1.getNode().getValue().equals("evader")) e1.setCapture(true);
								else e2.setCapture(true);
							}
						}
					}
					/*
					for (Evader evader : graph.getEvaders()) {
						if (evader.getNode().getVision()) evader.setCapture(true);
					}
					*/
					check = true;
					for (Evader evader : graph.getEvaders()) {
						if (check && !evader.getCapture()) check = false;
					}
					if (check) isRunning = false;
				}
				
				
        		gamePanel.repaint();			//update panel
        		if (!isRunning)
        		{								//check
        			timer.cancel();				//exit loop
        			System.out.println("game is done");
        		}
    		}
	}
}
 