/**
 * GridBoxIterator.java  6/25/2008
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 * Copyright(c) 2008 Robert Glen Martin
 * (http://martin.apluscomputerscience.com/)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

/**
 * A <code>GridRowIterator</code> iterates a box in a <code>Grid</code>
 */
public class GridBoxIterator extends GridIterator
{
	/**
	 * Constructs a <code>GridBoxIterator</code> object.
	 * @param grid the <code>Grid</code> to be iterated
	 * @param loc the first <code>Location</code> in the iteration
	 */
    public GridBoxIterator(Grid<Number> grid, Location loc)
    {
    	super(grid, loc);
    }

	/**
	 * Gets the location after the next one in box order.
	 * @return the next location after the next one in box order.
	 */
    public Location nextLocation()
    {
    	Location l=getLocation();
    	if(getIterationCount()%3!=0){
    		return new Location(l.getRow()+1, l.getCol());
    	}
    	else 
    		return new Location(l.getRow()-1, l.getCol()-2);
    }
}