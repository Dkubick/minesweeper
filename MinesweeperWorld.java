import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class MinesweeperWorld extends ActorWorld
{

	private boolean gameOver = false;
	private boolean flagMode = false;
	
	private int numMines = 0;

	public MinesweeperWorld(int size) //Constructor
	{
		super(new BoundedGrid<Actor>(size,size));
		for(int x =0; x< size; x++) //Fills grid with Box objects.
		{
			for(int y=0; y <size; y++)
			{
				int random = (int) (Math.random()*10); //Randomly decides whether Box is a mine or not.

				if (random == 0)
				{
					add(new Location(x,y), new Box(true));
					numMines++;
					
				}
				else
				{
					add(new Location(x,y), new Box(false));
					
				}

			}
		}
		setMessage("Flag Mode: " + flagMode);
	}
	
	public boolean locationClicked(Location loc) //What to do if a location is clicked.
	{
		Grid<Actor> gr = getGrid();
		Actor temp = gr.get(loc);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		if(flagMode == false)
		{
			if(temp instanceof Box)
			{
				check(loc);
			}
		}
		else if (flagMode == true)
		{
			if(getGrid().get(loc) instanceof Box)
			{
				flag(loc);
			}
			else if(getGrid().get(loc) instanceof Flag)
			{
				unflag(loc);
			}
		}
		
		

		return true; 
	}
	
	
	
	public boolean keyPressed(String description, Location loc)
	{
		if(description.equals("F"))
		{
			flagMode = !flagMode;
			setMessage("Flag Mode: " + flagMode);
		}
		return true;
	}
	
	public void flag(Location loc)
	{
		
		Box currentBox = (Box) getGrid().get(loc);
	
		
		if(getGrid().get(loc) instanceof Box && getGrid().get(loc).getColor().equals(Color.GRAY))
		{
			add(loc, new Flag(currentBox.isMine()));
		}
	}
	
	public void unflag(Location loc)
	{
		Flag currentFlag = (Flag) getGrid().get(loc);
		
		add(loc, new Box(currentFlag.isMine()));
	}
	

	public void check(Location loc)
	{
		if(!gameOver && getGrid().get(loc) instanceof Box) //Makes sure game is not over and player is clicking on an empty box.
		{
			Box currentBox = (Box) getGrid().get(loc);
			if(currentBox.isMine())
			{
				//Replaces checked location with a mine.
				getGrid().put(loc, new Mine());

				//Checks rest of grid for mines.
				ArrayList<Location> allLocs = getGrid().getOccupiedLocations();
				for(Location x: allLocs)
				{
					if(getGrid().get(x) instanceof Box)
					{
						Box tempBox = (Box) getGrid().get(x);
						if (tempBox.isMine())
						{
							getGrid().put(tempBox.getLocation(),new Mine());
						}
					}
				}

				//Prevents more clicking if player hits mine.
				gameOver = true;
			}

			else
			{
				if(getGrid().get(loc) instanceof Box)
				{

					ArrayList<Location> neighbors = getGrid().getValidAdjacentLocations(loc);
					int count = 0;
					for(Location location: neighbors)
					{
						Actor temp = getGrid().get(location);
						if(temp instanceof Box)
						{
							if(((Box) temp).isMine())
							{
								count++;
							}
						}
						if(temp instanceof Flag)
						{
							if(((Flag) temp).isMine())
							{
								count++;
							}
						}
					}

					if(count == 0)
					{
						currentBox.setColor(Color.WHITE);

						
						for(Actor neighbor : getGrid().getNeighbors(loc))
						{
							if(neighbor instanceof Box && neighbor.getColor().equals(Color.GRAY))
							{
								check(neighbor.getLocation());
							}
							
							
						}
						
					}
					else if(count == 1)
					{
						getGrid().put(currentBox.getLocation(), new One());
					}
					else if(count == 2)
					{
						getGrid().put(currentBox.getLocation(), new Two());
					}
					else if(count == 3)
					{
						getGrid().put(currentBox.getLocation(), new Three());
					}
					else if(count == 4)
					{
						getGrid().put(currentBox.getLocation(), new Four());
					}
					else if(count == 5)
					{
						getGrid().put(currentBox.getLocation(), new Five());
					}
					else if(count == 6)
					{
						getGrid().put(currentBox.getLocation(), new Six());
					}
					else if(count == 7)
					{
						getGrid().put(currentBox.getLocation(), new Seven());
					}
					else if(count == 8)
					{
						getGrid().put(currentBox.getLocation(), new Eight());
					}
				}
			}
		}
	}
}
