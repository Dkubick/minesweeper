import java.awt.Color;
import java.util.ArrayList;



import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;


public class Box extends Actor
{
	private boolean isMine = false;

	public Box(boolean mine)
	{
		isMine = mine;
		setColor(Color.GRAY);
	}

	public boolean isMine()
	{
		return isMine;
	}

	public void act()
	{

	}

}


