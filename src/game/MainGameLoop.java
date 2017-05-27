package game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import logic.LineOfSightChecker;
import logic.Pair;


public class MainGameLoop{
	private static int BASE_FPS=4;
	private  int fps= BASE_FPS;
	private java.util.Timer timer;
	private  GamePanel gamePanel;
	private Graph graph;
	private boolean isRunning=true;
	private int count=0;
	private LineOfSightChecker checker = new LineOfSightChecker();

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
				boolean check = true;
				if(count == 100)		//counting frames, for turn handling, from 1 to 100
					count = 1;
				else
					count++;
				for(Entity ent:graph.getEntities()){//move entities
					if(((int)100/ent.getSpeed())>=((double)100/ent.getSpeed())){
						if(count%(100/ent.getSpeed())==0  ||  ((100%ent.getSpeed())!=0 && count%(100%ent.getSpeed())==0)){
							ent.move();
							hasMoved = true;
						}
					}else{
						if(count%(100/ent.getSpeed())==0  &&  (count%(100/(100%ent.getSpeed()))!=0)){
							ent.move();
							hasMoved = true;
						}
					}
				}
				
				//checks if all evaders have been captured ||
				if (hasMoved) {
					System.out.println("NEW MOVES HAVE BEGUN");
					ArrayList<Pair> pairs = checker.checkEntities(graph);
					
					for (Pair pair : pairs) {
						String entity1 = pair.getEntity1().getNode().getValue();
						String entity2 = pair.getEntity2().getNode().getValue();
						System.out.println(pair.getLineOfSight());
						if(((entity1.equals("evader") && entity2.equals("pursuer")) || (entity2.equals("evader") && entity1.equals("pursuer"))) && pair.getLineOfSight()) {
							if (entity1.equals("evader")) pair.getEntity1().setCapture(true);
							else pair.getEntity2().setCapture(true);
						}
					}
					check = true;
					for (Evader evader : graph.getEvaders()) {
						if (check && !evader.getCapture()) check = false;
					}
				}
				
				if (check) isRunning = false;
        		gamePanel.repaint();			//update panel
        		if (!isRunning)
        		{								//check
        			timer.cancel();				//exit loop
        			System.out.println("game is done");
        		}
    		}
	}
}
 