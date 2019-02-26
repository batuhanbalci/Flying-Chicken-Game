package dev.flyingChicken;
import dev.flyingChicken.Game;

public class Launcher {
	public static void main(String[] args)
	{
		Game game=new Game("Flying Chicken",600,650);
		game.start();
	}
}
