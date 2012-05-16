import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.world.World;

import java.awt.Color;
import java.util.ArrayList;

public class MinesweeperRunner 
{
    public static void main(String[] args)
    {
    	int gridSize = 10;
    	
    	ActorWorld world = new MinesweeperWorld(gridSize);
    	
		
		world.show();
        
    }

    
	
}