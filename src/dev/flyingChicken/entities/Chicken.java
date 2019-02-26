package dev.flyingChicken.entities;
import java.awt.Graphics;
import dev.flyingChicken.Game;
import dev.flyingChicken.graphics.Assets;

public class Chicken extends Entity{
	
	public static final int MAX_LEFT=50;
	public static final int MAX_RIGHT=500;
	
	private Game game;
	private int maxLeft,maxRight;
	
	public Chicken(Game game, float x, float y, int width, int height )
	{
		super(x,y,width,height);
		this.game=game;
		maxLeft=MAX_LEFT;
		maxRight=MAX_RIGHT;
	}

	public boolean isIntersect(Cat cat)
	{
		if (x < cat.getX()+50 && x+50 > cat.getX() && y+50 > cat.getY() && y < cat.getY()+50 ) 
		{
			width=0;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public void tick()
	{
		if(game.getKeyManager().left)
		{
			if(x>=maxLeft+3)
			{
				x-=3;
			}
		}
		if(game.getKeyManager().right)
		{
			if(x<=maxRight-3)
			{
				x+=3;
			}
		}
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(Assets.chicken, (int)x, (int)y, width, height, null);
	}
}
