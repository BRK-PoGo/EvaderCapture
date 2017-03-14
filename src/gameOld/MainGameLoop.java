package gameOld;
import java.util.Timer;
import java.util.TimerTask;


public class MainGameLoop{
	private static int BASE_FPS=5;
	private  int fps= BASE_FPS;
	private java.util.Timer timer;
	private  GamePanel gamePanel;
	private boolean isRunning=true;
	

	public MainGameLoop(GamePanel panel) {
		gamePanel=panel;
		gameLoop();
	}
	public void gameLoop()
	{
		timer = new Timer();
    	timer.schedule(new LoopyStuff(), 0, 1000 / fps); //new timer at 'fps' , the timing mechanism
	}
	class LoopyStuff extends TimerTask {
		@Override
		public void run() 		
		
												//loop
			{
        		gamePanel.update();				//update panel
        		if (!isRunning)
        		{								//check
        			timer.cancel();				//exit loop
        		}
    		}
	}
}
 