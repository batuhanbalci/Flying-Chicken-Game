package dev.flyingChicken.states;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JOptionPane;

import dev.flyingChicken.Game;
import dev.flyingChicken.entities.*;

public class GameState extends State{

	private Chicken chicken;
	private Target[] leftTarget=new Target[1000];
	private Target[] rightTarget=new Target[1000];
	private Cat cat;
	private Egg egg;
	private Score scoreLabel;
	private Background background;
	
	public GameState(Game game)
	{
		super(game);
		chicken=new Chicken(game,280,50,50,50);
		cat=null;
		egg=null;
		scoreLabel=new Score(game,300,300,200,200);
		background=new Background(game,0,0,600,1536);
	}
	
	Random rand = new Random();
	Random rand2 = new Random();
	Random randSize = new Random();
	Random randSize2 = new Random();
	private int counterLeft=0;
	private int counterRight=0;
	private int score=0;
	private int level=1;
	private boolean eggState=false;
	
	@Override
	public void tick() {
		
		if(score>30 && level==1)
		{
			String message = "Bir üst level'a geçebilirsin. Skorun : "+score;
			JOptionPane.showMessageDialog(null, message, "Tebrikler !!!",JOptionPane.WARNING_MESSAGE);
			level++;
			if(cat!=null)
			{
				cat.setSpeed(20);
			}
		}
		else if(score>70 && level==2)
		{
			String message = "Bir üst level'a geçebilirsin. Skorun : "+score;
			JOptionPane.showMessageDialog(null, message, "Tebrikler !!!",JOptionPane.WARNING_MESSAGE);
			level++;
			if(cat!=null)
			{
				cat.setSpeed(30);
			}
		}
		
		
		chicken.tick();	

		if(game.getKeyManager().leftArrow||game.getKeyManager().rightArrow)
		{
			eggState=true;
		}
		
		if(eggState==true)
		{
			if(egg==null)
			{
				egg=new Egg(game,chicken.getX(),chicken.getY(),20,20);
			}
			else
			{
				egg.tick();
				if(egg.getX()<-30||egg.getX()>600)
				{
					eggState=false;
					egg=null;
				}
			}
		}
		
		//Coin
		int n = rand.nextInt(180) + 1;
		int n2 = rand2.nextInt(180) +1;
		int x = randSize.nextInt(46) +30;
		int x2 = randSize2.nextInt(46)+30;
		
		if(n==20)
		{
			if(counterLeft==0)
			{
				leftTarget[counterLeft]=new Target(game,10,650,x,x,egg);
				counterLeft++;
			}
			else
			{
				if(leftTarget[counterLeft-1].getY()>600)
				{
					leftTarget[counterLeft]=new Target(game,10,leftTarget[counterLeft-1].getY()+leftTarget[counterLeft-1].getHeight(),x,x,egg);
				}
				else
				{
					leftTarget[counterLeft]=new Target(game,10,650+leftTarget[counterLeft-1].getHeight(),x,x,egg);
				}
				counterLeft++;
			}
		}
		
		if(n2==20) //Right
		{
			if(counterRight==0)
			{
				rightTarget[counterRight]=new Target(game,520,650,x2,x2,egg);
				counterRight++;
			}
			else
			{
				if(rightTarget[counterRight-1].getY()>600)
				{
					rightTarget[counterRight]=new Target(game,520,rightTarget[counterRight-1].getY()+rightTarget[counterRight-1].getHeight(),x2,x2,egg);
				}
				else
				{
					rightTarget[counterRight]=new Target(game,520,650+rightTarget[counterRight-1].getHeight(),x2,x2,egg);
				}
				counterRight++;
			}
		}
		for(int i=0;i<counterLeft;i++)
		{					
			leftTarget[i].tick();
			
			if(egg!=null)
			{
				if (leftTarget[i].getX() < egg.getX()+20 && leftTarget[i].getX()+leftTarget[i].getWidth() > egg.getX() &&
						leftTarget[i].getY()+leftTarget[i].getWidth() > egg.getY() && leftTarget[i].getY() < egg.getY()+20 ) 
				{
					if(leftTarget[i].getWidth()>=30&&leftTarget[i].getWidth()<=45)
					{
						score+=3;
					}
					else if(leftTarget[i].getWidth()>=46&&leftTarget[i].getWidth()<=60)
					{
						score+=2;
					}
					else
					{
						score++;
					}
					System.out.println(score);
					scoreLabel.setScore(score,level);
					leftTarget[i].deleteTarget();
					for(int j=i;j<counterLeft-1;j++)
					{
						leftTarget[j]=leftTarget[j+1];
					}
				}
			}
		}
		for(int i=0;i<counterRight;i++)
		{
			rightTarget[i].tick();
			
			if(egg!=null)
			{
				if (rightTarget[i].getX() < egg.getX()+20 && rightTarget[i].getX()+rightTarget[i].getWidth() > egg.getX() &&
						rightTarget[i].getY()+rightTarget[i].getWidth() > egg.getY() && rightTarget[i].getY() < egg.getY()+20 ) 
				{
					if(rightTarget[i].getWidth()>=30&&rightTarget[i].getWidth()<=45)
					{
						score+=3;
					}
					else if(rightTarget[i].getWidth()>=46&&rightTarget[i].getWidth()<=60)
					{
						score+=2;
					}
					else
					{
						score++;
					}
					System.out.println(score);
					scoreLabel.setScore(score,level);
					rightTarget[i].deleteTarget();
					for(int j=i;j<counterRight-1;j++)
					{
						rightTarget[j]=rightTarget[j+1];
					}
				}
			}
		}
		
		//Cat
		int  c = rand2.nextInt(30) + 1;
		if(c==20)
		{
			if(cat==null)
			{
				cat=new Cat(200,550,50,50);
			}
			else
			{
				
				if(chicken.isIntersect(cat))
				{			
					String message = "Kediye yakalandýn, oyun bitti !!! Skorun : "+score;
					JOptionPane.showMessageDialog(null, message, "Yakalandýn !!!",JOptionPane.WARNING_MESSAGE);
					game.stop();
				}
				cat.tick();
			}
		}
		background.tick();
		
	}

	@Override
	public void render(Graphics g) 
	{
		background.render(g);
		
		scoreLabel.render(g);
		chicken.render(g);
		
		if(egg!=null)
		{
			egg.render(g);
		}

		//Coin
		for(int i=0;i<counterLeft;i++)
		{
			leftTarget[i].render(g);
		}
		for(int i=0;i<counterRight;i++)
		{
			rightTarget[i].render(g);
		}
		
		//Cat
		if(cat!=null)
		{
			cat.render(g);
		}
	}
}
