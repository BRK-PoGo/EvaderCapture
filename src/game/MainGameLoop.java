package game;

import java.util.Timer;
import java.util.TimerTask;


public class MainGameLoop{
	private static int BASE_FPS=4;
	private  int fps= BASE_FPS;
	private java.util.Timer timer;
	private  GamePanel gamePanel;
	private Graph graph;
	private boolean isRunning=true;
	private int count=0;
	

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
				if(count == 100)		//counting frames, for turn handling, from 1 to 100
					count = 1;
				else
					count++;
				for(Entity ent:graph.getEntities()){//move entities
					if(((int)100/ent.getSpeed())>=((double)100/ent.getSpeed())){
						if(count%(100/ent.getSpeed())==0  ||  ((100%ent.getSpeed())!=0 && count%(100%ent.getSpeed())==0)){
							ent.move();
						}
					}else{
						if(count%(100/ent.getSpeed())==0  &&  (count%(100/(100%ent.getSpeed()))!=0)){
							ent.move();
						}
					}
				}
        		gamePanel.repaint();			//update panel
        		if (!isRunning)
        		{								//check
        			timer.cancel();				//exit loop
        		}
    		}
	}
}
 