package dev.flyingChicken.graphics;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width=50,height=50;
	
	public static BufferedImage coin,chicken, cat, egg, background;
	
	public static void init()
	{
		SpriteSheet sheet=new SpriteSheet(ImageLoader.loadImage("/textures/texture.png"));
		
		coin=sheet.crop(0, 0, width, height);
		egg=sheet.crop(width, 0, width, height);
		cat=sheet.crop(width*2, 0, width, height);
		chicken=sheet.crop(width*3, 0, width, height);
		background=sheet.crop(width*4, 0, 600, 1536);
	}
}
