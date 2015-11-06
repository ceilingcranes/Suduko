/**
 * DuplicateChecker.java  6/25/2008
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

import java.util.Iterator;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A checker that checks if a row, column, or box
 * has no duplicate values.
 */
public class DuplicateChecker extends Checker
{
	/**
	 * Constructs a <code>DuplicateChecker</code> object.
	 * @param grid the <code>Grid</code> object to be checked.
	 */
    public DuplicateChecker(Grid<Number> grid)
    {
    	super(grid);
    }

	/**
	 * Performs an inner Sudoku check for duplicate values
	 * by iterating <code>grid</code> in the innerViewCode direction.
	 * @param start the <code>Location</code>
	 *            where the inner interation starts
	 * @param innerViewCode the <code>GridView</code> direction of the
	 *            inner iteration (one of ROW_VIEW, COL_VIEW, BOX_VIEW)
	 * @return true if check passes; false otherwise
	 */
    public boolean innerCheck(Location start, int innerViewCode)
    {
    	int[] tester=new int[9];
    	GridView gridView=new GridView(getGrid(), start, innerViewCode);
    	Iterator<Location> it=gridView.iterator();
		while(it.hasNext()){
			tester[getGrid().get(it.next()).getValue()]++;
		}
		for(int k=0; k<10; k++){
			if(tester[k]>=2)
				return false;
		}
		return true;
    }
}