package dev.flyingChicken;
import dev.flyingChicken.display.Display;
import dev.flyingChicken.graphics.Assets;
import dev.flyingChicken.input.KeyManager;
import dev.flyingChicken.states.GameState;
import dev.flyingChicken.states.State;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
	
	private Display display;
	private int width,height;
	private String title;
	
	private Thread thread;
	private boolean running=false;

	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState;
	
	//Input
	private KeyManager keyManager;
	
	public Game(String title,int width,int height)
	{
		this.width=width;
		this.height=height;
		this.title=title;
		keyManager = new KeyManager();
	}
	
	public void init()
	{
		display=new Display(title,width,height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		gameState=new GameState(this);
		State.setState(gameState);
		//State.setState(menuState);
	}

	
	public void tick()
	{
		keyManager.tick();
		
		if(State.getState() != null)
		{
			State.getState().tick();
		}
	}
	public void render()
	{
		bs=display.getCanvas().getBufferStrategy();
		if(bs==null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g=bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		//Start-Draw
		if(State.getState() != null)
		{
			State.getState().render(g);
		}
		/*g.setColor(Color.red);
		g.fillRect(5, 5, 100, 100);
		g.setColor(Color.yellow);
		g.fillRect(10, 10, 20, 20);
		g.drawImage(test, 200, 0, null);
		g.drawImage(test2, 210, 10, null);*/
		/*g.drawImage(Assets.coin, 50, 50, null);
		g.drawImage(Assets.cat, 150, 500, null);
		g.drawImage(Assets.chicken, 280, 50, null);
		g.drawImage(Assets.egg, 50, 150, null);*/
		//End
		bs.show();
		g.dispose();
	}
	public void run()
	{
		init();
		
		int fps=60;
		double timePerTick=1000000000/fps;
		double delta=0;
		long now;
		long lastTime=System.nanoTime();
		long timer=0;
		int ticks=0;
				
		while(running)
		{
			
			now=System.nanoTime();
			delta+=(now-lastTime)/timePerTick;
			timer+=(now-lastTime);
			lastTime=now;
			
			if(delta>=1)
			{
				
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer>=1000000000)
			{
				System.out.println("Ticks and Frames" + ticks);
				ticks=0;
				timer=0;
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager()
	{
		return keyManager;
	}
	
	public synchronized void start()
	{
		if(running)
		{
			return;
		}
		running=true;
		thread=new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running)
		{
			return;
		}
		running=false;
		try 
		{
			thread.join();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
}
