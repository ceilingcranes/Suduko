/**
 * SudokuWorld.java  01/27/07
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

import info.gridworld.world.World;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public class SudokuWorld extends World<Number>
{
	/** The game */
	private SudokuGame game;

	/**
	 * Constructs the world.
	 */
	public SudokuWorld()
	{
		super(new BoundedGrid<Number>(9, 9));

		game = null;  // no game yet
		setMessage("Create a new puzzle.");
		System.setProperty("info.gridworld.gui.tooltips", "hide");
		System.setProperty("info.gridworld.gui.frametitle",
			"Sudoku - puzzles from http://www.websudoku.com");
	}

	/**
	 * Gets a new puzzle, creates a new game with it,
	 * displays the message to the player, and shows the grid.
	 * @param difficulty the difficulty of the game.  Legal
	 *        values are Puzzle.EASY, Puzzle.MEDIUM, Puzzle.HARD,
	 *        and Puzzle.EVIL.
	 * @param puzzleLoc the location of the puzzle.  Legal
	 *        values are Puzzle.INTERNET and Puzzle.FILE.
	 */
	public void newPuzzle(int difficulty, int puzzleLoc)
	{
		Puzzle puzzle = new Puzzle(difficulty, puzzleLoc);
		game = new SudokuGame(getGrid(), puzzle);
		setMessage(
			"First choose a cell, then (1) type a number to enter or" +
			"\n(2) press Del key to remove, or (3) press c to cheat.");

		show();
	}

	/**
	 * Solves the same if a puzzle has been loaded;
	 * returns otherwise.
	 */
	public void solve()
	{
		if (game == null)
			return;
		game.solve();
		show();
	}

	/**
	 * Clears the board if a puzzle has been loaded;
	 * returns otherwise.
	 */
	public void clear()
	{
		if (game == null)
			return;
		game.clearBoard();
		show();
	}

    /**
     * This method is called when the user clicks on a location in the
     * WorldFrame.
     *
     * @param loc the grid location that the user selected
     * @return true because the world consumed the click.
     */
	public boolean locationClicked(Location loc)
	{
		return true; // click handled - GUI has nothing to do.
	}

    /**
     * This method is called when a key was pressed.
     * @param loc the selected location in the grid
     *        at the time the key was pressed
     * @return true if the world consumed the key press
     *         (ENTER, DELETE, c, C, or 1-9);
     *         false otherwise (the GUI should consume it.)
     */
	public boolean keyPressed(String description, Location loc)
	{
		if ("ENTER".equals(description))
			return true;

		if ("DELETE".equals(description))
		{
			if (game == null)
				return true;
			game.clearLocation(loc);
			return true;
		}

		if (description.length() == 1 )
		{
			if (game == null)
				return true;
			Number current = getGrid().get(loc);
			if (current == null || current.getType() != Number.ORIGINAL)
			{
				char input = description.charAt(0);
				if ('1' <= input && input <= '9')
				{
					int value = input - '0';
					game.makePlay(value, loc);
					show();
					return true;
				}
				else if (input == 'c' || input == 'C')
				{
					game.cheat(loc);
					return true;
				}
			}
		}
		return false;
	}
}
