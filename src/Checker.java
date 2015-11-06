/**
 * Checker.java  6/25/2008
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
 * Top level abstract class for a 2D Sudoku checker.
 * A <code>Checker</code>is a functor which contains a
 * <code>check</code> method which performs a 2D Sudoku check
 * by iterating all the rows, columns, or boxes in the
 * <code>grid</code>.  For each of these locations, it performs
 * an <code>innerCheck</code> of the column, row, or box
 * starting at that location.
 */
public abstract class Checker
{
	/** The Grid being checked */
	Grid<Number> grid;
	/**
	 * Constructs a checker
	 */
	public Checker(Grid<Number> gr)
	{
		grid=gr;
	}

	/**
	 * Gets the grid.
	 * @return the grid being checked
	 */
	public Grid<Number> getGrid()
	{
		return grid;
	}

	/**
	 * Performs a 2D Sudoku check by iterating the grid in
	 * the outerViewCode direction.  For each location in the
	 * outer iteration, it performs an innerCheck based on the
	 * innerViewCode direction.
	 * @param outerViewCode the <code>GridView</code> direction of the
	 *        outer iteration (one of ROW_VIEW, COL_VIEW, or BOXES_VIEW)
	 * @param innerViewCode the <code>GridView</code> direction of the
	 *        inner iteration (one of ROW_VIEW, COL_VIEW, BOX_VIEW)
	 * @return true if all checks pass; false otherwise
	 */
	public boolean check(int outerViewCode, int innerViewCode)
	{
		GridView gridview=new GridView(grid, new Location(0,0), outerViewCode);
		Iterator<Location> iterator=gridview.iterator();
		while(iterator.hasNext()){
			if(innerCheck(iterator.next(), innerViewCode)!=true){
				return false;
			}
		}
		return true;
	}

	/**
	 * Performs an inner Sudoku check by iterating
	 * <code>grid</code> in the innerViewCode direction.
	 * @param loc the <code>Location</code>
	 *            where the inner interation starts
	 * @param innerViewCode the <code>GridView</code> direction of the
	 *            inner iteration (one of ROW_VIEW, COL_VIEW, BOX_VIEW)
	 * @return true if check passes; false otherwise
	 */
	public abstract boolean innerCheck(Location loc, int innerViewCode);
}