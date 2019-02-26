package dev.flyingChicken.entities;
import java.awt.Graphics;
import java.util.Random;
import dev.flyingChicken.graphics.Assets;

public class Cat extends Entity{

	public static final int DEFAULT_SPEED=10;
	public static final int DEFAULT_SIZE=50;
	protected int speed;
	private boolean catDirection=true;

	public Cat(float x, float y, int width, int height) {
		super(x, y, width, height);
		speed=DEFAULT_SPEED;
	}

	Random rand = new Random();
	
	@Override
	public void tick() 
	{
		if(catDirection==true)
		{
			y-=speed;
			if(y<=30)
			{
				catDirection=false;
			}
		}
		else
		{
			y+=speed;
			if(y>400)
			{
				catDirection=true;
			}
		}
		
		int  n = rand.nextInt(5+1+5) - 5;
		x+=n;
		if(y<=20)
		{
			
		}
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(Assets.cat, (int)x, (int)y, width, height, null);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
