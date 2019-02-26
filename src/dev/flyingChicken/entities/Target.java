package dev.flyingChicken.entities;
import java.awt.Graphics;

import dev.flyingChicken.Game;
import dev.flyingChicken.graphics.Assets;

public class Target extends Entity{
	
	public Target(Game game, float x, float y, int width, int height, Egg egg)
	{
		super(x,y,width,height);
	}
	
	public void deleteTarget()
	{
		width=0;
	}

	@Override
	public void tick() 
	{
		y-=1;
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(Assets.coin, (int)x, (int)y, width, height, null);
	}
}
