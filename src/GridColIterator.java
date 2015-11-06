/**
 * GridColIterator.java  6/25/2008
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
 * A <code>GridRowIterator</code> iterates a column in a <code>Grid</code>
 */
public class GridColIterator extends GridIterator
{
	/**
	 * Constructs a <code>GridColIterator</code> object.
	 * @param grid the <code>Grid</code> to be iterated
	 * @param loc the first <code>Location</code> in the iteration
	 */
    public GridColIterator(Grid<Number> grid, Location loc)
    {
    	super(grid, loc);
    }

	/**
	 * Gets the location after the next one in column order.
	 * @return the next location after the next one in column order.
	 */
    public Location nextLocation()
    {
    	return new Location(getLocation().getRow(), getLocation().getCol()+1);
    }
}