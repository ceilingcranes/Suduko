/**
 * GridView.java  6/25/2008
 *
 * @author - Robert Glen Martin
 * @author - School for the Talented and Gifted
 * @author - Dallas ISD
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

import java.util.Iterator;

/**
 * Provides an iterable view of a <code>Grid</code> object.
 */
public class GridView implements Iterable<Location>
{
	/** Iterate a row */
	public static final int ROW_VIEW = 1;
	/** Iterate a column */
	public static final int COL_VIEW = 2;
	/** Iterate a box */
	public static final int BOX_VIEW = 3;
	/** Iterate the boxes (upper-left corners of each box) */
	public static final int BOXES_VIEW = 4;

	/** The <code>Grid</code> to iterate */
	private Grid<Number> grid;
	/** The first <code>Location</code> in the iteration */
	private Location loc;
	/** The chosen iteration direction */
	private int viewType;

	/**
	 * Construct a grid view
	 * @param grid the <code>Grid</code> to iterate
	 * @param loc the initial <code>Location</code> in the iteration
	 * @param viewType the region type to be iterated
	 */
    public GridView(Grid<Number> grid, Location loc, int viewType)
    {
    	this.grid = grid;
    	this.loc = loc;
    	this.viewType = viewType;
    }

	/**
	 * Gets an iterator
	 * @return an iterator based on the grid view
	 * @throws IllegalStateException invalid <code>viewType</code> value
	 */
    public Iterator<Location> iterator()
    {
    	if (viewType == ROW_VIEW)
    		return new GridRowIterator(grid, loc);
    	else if (viewType == COL_VIEW)
    		return new GridColIterator(grid, loc);
    	else if (viewType == BOX_VIEW)
    		return new GridBoxIterator(grid, loc);
    	else if (viewType == BOXES_VIEW)
    		return new GridBoxesIterator(grid, loc);
    	else
    		throw new IllegalStateException(
    			"Illegal grid view code: " + viewType);
    }
}