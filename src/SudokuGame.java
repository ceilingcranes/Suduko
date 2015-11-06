/**
 * SudokuGame.java  01/27/07
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

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import javax.swing.JOptionPane;

/**
 * A <code>SodokuGame</code> allows a player to play a single Sudoku game.
 */
public class SudokuGame
{
	/** The grid for the game which is always filled with numbers */
	private Grid<Number> grid;

	/** The puzzle to be solved */
	private Puzzle puzzle;
	private SumChecker sumChecker;
	private DuplicateChecker dupChecker;
    /**
     * Constructor which initializes the game.<BR>
     * Initializes the instance varaibles and fills in the grid
     * with Number objects.
     * @param g the grid to be used
     * @param p the puzzle to be used
     */
	public SudokuGame(Grid<Number> g, Puzzle p)
	{
		grid = g;
		puzzle = p;
		sumChecker=new SumChecker(grid);
		dupChecker=new DuplicateChecker(grid);
		for (int r = 0; r < 9; r++)
			for (int c = 0; c < 9; c++)
			{
				Location loc = new Location(r, c);
				int value = puzzle.getOriginalValue(loc);
				if (value > 0)
					grid.put(loc, new Number(value, loc, Number.ORIGINAL));
				else
					grid.put(loc, new Number(0, loc, Number.PLAYER));
			}
	}

    /**
     * Cheats for a specific location.
     * If the Number object a <code>loc</code> is not part of the
     * original puzzle then replace it with an appropriate
     * cheating value.
     * @param loc the location
     */
	public void cheat(Location loc)
	{
		Number number = grid.get(loc);
		if (number == null ||
			number.getType() == Number.ORIGINAL)
			return;
		int value = puzzle.getSolutionValue(loc);
		if (number.getValue() == value)
			return;
		grid.put(loc, new Number(value, loc, Number.CHEAT));
	}

	/**
	 * Solves the puzzle by cheating for each location.
	 */
	public void solve()
	{
		for (Location loc : grid.getOccupiedLocations())
			cheat(loc);
	}

	/**
	 * Clears the cell at location <code>loc</code> by
	 * placing a Player's <code>Number</code> object
	 * with value 0 at that location.
	 * @param loc the location to be cleared
	 */
	public void clearLocation(Location loc)
	{
		Number current = grid.get(loc);
		if (current != null &&
			current.getType() != Number.ORIGINAL)
				grid.put(loc, new Number(0, loc, Number.PLAYER));
	}

	/**
	 * Clears by board by calling clearLocation
	 * for each cell on the board.
	 */
	public void clearBoard()
	{
		for (Location loc : grid.getOccupiedLocations())
			clearLocation(loc);
	}

	/**
	 * Plays <code>value</code> at location
	 * <code>loc</code> if it does not conflict with
	 * another number on the board.  Otherwise it pops
	 * up an error message and restores the board to
	 * its original state.<br>
	 * Also, if this play solves the puzzle, a appropriate
	 * pop up is displayed.
	 * @param value the number to be "played".
	 * @param loc the location to be "played".
	 */
	public void makePlay(int value, Location loc)
	{
		Number oldNumber = grid.get(loc);
		if (oldNumber == null)
			return;
		grid.put(loc, new Number(value, loc, Number.PLAYER));
		if (! boardIsOK())
		{
			JOptionPane.showMessageDialog(null,
				"Illegal move.", "Illegal Move Error",
				JOptionPane.ERROR_MESSAGE);
			grid.put(loc, oldNumber);
		}
		else if (boardIsSolved())
			JOptionPane.showMessageDialog(null,
				"Congratulations", "You win!",
				JOptionPane.INFORMATION_MESSAGE );
	}

	/**
	 * Checks the board for conflicts.
	 * @return true if there are no conflicts;
	 *         false otherwise.
	 */
	public boolean boardIsOK()
	{
		return check(dupChecker);
	}

	/**
	 * Checks to see if the puzzle is solved.
	 * (All rows, columns, and boxes add to 45).
	 * @return true if the puzzle is solved;
	 *         false otherwise.
	 */
	public boolean boardIsSolved()
	{
		return check(sumChecker);
	}
	
	/**
	 * Checks to see if all the rows in the puzzle are solved (add to 45).
	 * @return true if all rows are solved;
	 *         false otherwise;
	 */
	
	public boolean check(Checker checker){
		return checker.check(GridView.COL_VIEW, GridView.ROW_VIEW)&&
				checker.check(GridView.ROW_VIEW, GridView.COL_VIEW)&&
				checker.check(GridView.BOXES_VIEW, GridView.BOX_VIEW);
	}
}