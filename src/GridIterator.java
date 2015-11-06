/**
 * GridIterator.java  6/25/2008
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Top level abstract grid iterator class
 */
public abstract class GridIterator implements Iterator<Location>
{
	/** The grid to be iterated */
	private Grid<Number> grid;
	/** The next location in the iteration */
	private Location nextLoc;
	/** The number of times this iterator has returned a location */
	private int interCount;

	/**
	 * Constructs a <code>GridIterator</code> object.
	 * @param grid the <code>Grid</code> to be iterated
	 * @param loc the first <code>Location</code> in the iteration
	 */
    public GridIterator(Grid<Number> gr, Location loc)
    {
    	grid=gr;
    	nextLoc=loc;
    	interCount=0;
    	
    }

	/**
	 * Determines if all 9 locations in the region have been iterated.
	 * @return true if there are more locations to iterate;
	 *         false otherwise
	 */
    public boolean hasNext()
    {
    	if(interCount<9)
    		return true;
    	return false;
    }

	/**
	 * Gets the next location
	 * @return the next location in the iteration
	 * @throws NoSuchElementException if there are no more
	 *         locations in the iteration
	 */
    public Location next()
    {
    	if(interCount>=9){
    		throw new NoSuchElementException("No More Elements");
    	}
    	interCount++;
    	Location temp=nextLoc;
    	nextLoc=nextLocation();
    	return temp;
    }

	/**
	 * The remove method is not supported.
	 * @throws UnsupportedOperationException always
	 */
    public void remove()
    {
    	throw new UnsupportedOperationException("Remove what, exactly?");
    }

	/**
	 * Gets the grid
	 * @return the grid
	 */
    public Grid<Number> getGrid()
    {
    	return grid;
    }

	/**
	 * Gets the next location in the iteration
	 * @return the next location
	 */
    public Location getLocation()
    {
    	return nextLoc;
    }

	/**
	 * Gets the number of iterations performed
	 * @return the number of iterations
	 */
    public int getIterationCount()
    {
    	return interCount;
    }

	/**
	 * Gets the location after <code>nextLoc</code> in the iteration.
	 * @return the next location after <code>nextLoc</code>
	 *         in the iteration
	 */
    public abstract Location nextLocation();
}