import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;


public class Flag extends Actor
{

	private boolean isMine = false;
	
	public Flag(boolean mine)
	{
		super();
		isMine = mine;
		setColor(Color.RED);
	}
	
	public boolean isMine()
	{
		return isMine;
	}
	
	public void act()
	{

	}
}
