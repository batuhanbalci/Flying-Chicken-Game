package dev.flyingChicken.input;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean [] keys;
	public boolean left,right,leftArrow,rightArrow;
	
	public KeyManager()
	{
		keys=new boolean[256];
	}
	
	public void tick()
	{
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		leftArrow = keys[KeyEvent.VK_LEFT];
		rightArrow = keys[KeyEvent.VK_RIGHT];
	}
	
	public void render()
	{
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		keys[e.getKeyCode()]=true;
		System.out.print("bastýn");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		keys[e.getKeyCode()]=false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
