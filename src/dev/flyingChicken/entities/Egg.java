package dev.flyingChicken.entities;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import dev.flyingChicken.Game;
import dev.flyingChicken.graphics.Assets;

public class Egg extends Entity implements ActionListener{
	
	private Game game;
	
	public Egg(Game game, float x, float y, int width, int height)
	{
		super(x,y,width,height);
		this.game=game;
	}

	
	boolean eggDirection;
	
	@Override
	public void tick() 
	{
		Timer t = new Timer(10, this);
		
		
		if(game.getKeyManager().leftArrow)
		{	
			t.start();
			eggDirection=true;
		}
		
		if(game.getKeyManager().rightArrow)
		{
			t.start();
			eggDirection=false;
		}
		
		
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(Assets.egg, (int)x, (int)y, width, height, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(eggDirection==true)
		{
			x-=1;
			
		}	
		
		if(eggDirection==false)
		{	
			x+=1;
		}
		
	}

}
