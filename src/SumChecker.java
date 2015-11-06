/**
 * SumChecker.java  6/25/2008
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

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A checker that checks if a row, column, or box adds to 45.
 */
public class SumChecker extends Checker
{
	/**
	 * Constructs a <code>SumChecker</code> object.
	 * @param grid the <code>Grid</code> object to be checked.
	 */
    public SumChecker(Grid<Number> grid)
    {
    	super(grid);
    }

	/**
	 * Performs an inner Sudoku check for correct sums (45) of values
	 * by iterating <code>grid</code> in the innerViewCode direction.
	 * @param start the <code>Location</code>
	 *            where the inner interation starts
	 * @param innerViewCode the <code>GridView</code> direction of the
	 *            inner iteration (one of ROW_VIEW, COL_VIEW, BOX_VIEW)
	 * @return true if check passes; false otherwise
	 */
    public boolean innerCheck(Location start, int innerViewCode)
    {
    	GridView gridview=new GridView(getGrid(), start, innerViewCode);
    	int sum = 0;
		for(Location loc: gridview){
			sum+=getGrid().get(loc).getValue();
		}
		return sum==45;
	
    }
}