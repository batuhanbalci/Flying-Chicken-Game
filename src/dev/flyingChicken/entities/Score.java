package dev.flyingChicken.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import dev.flyingChicken.Game;

public class Score extends Entity {
	
	private int score;
	private int level=1;
	
	public Score(Game game, float x, float y, int width, int height)
	{
		super(x,y,width,height);
	}
	
	@Override
	public void tick() 
	{

	}
	
	public void setScore(int score,int level)
	{
		this.score=score;
		this.level=level;
	}

	@Override
	public void render(Graphics g) 
	{
	    if(g instanceof Graphics2D) //Score ve level
	    {
	    	Graphics2D g2d = (Graphics2D)g;
	    	int alpha = 127; // 50% transparent
	    	Color bgColour = new Color(90, 90, 90, alpha);
	    	Color fontColour = new Color(255, 0, 0);
	    	
	    	Rectangle2D rect = new Rectangle2D.Double(0, 600, 600, 60);
	    	g2d.setColor(bgColour);
	    	g2d.fill(rect);   	

	        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	        g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,50));
	        g2d.setColor(fontColour);
	        g2d.drawString(String.valueOf(score), 290, 640);

	        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	        g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,20));
	        g2d.drawString("Level: "+String.valueOf(level), 90, 630);
	    }
	}

}
