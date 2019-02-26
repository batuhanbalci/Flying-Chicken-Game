package dev.flyingChicken.entities;

import java.awt.Graphics;

import dev.flyingChicken.Game;
import dev.flyingChicken.graphics.Assets;

public class Background extends Entity{

	public Background(Game game, float x, float y, int width, int height)
	{
		super(x,y,width,height);
	}

	@Override
	public void tick() {
		y-=1;
		/*if(y==-800)
		{
			y=0;
		}*/
	}

	@Override
	public void render(Graphics g) {
		
		for(int i=0;i<1000;i++)
		{
			g.drawImage(Assets.background, (int)x, (int)y+(height*i), width, height, null);
		}
		
		
		
	}

}
