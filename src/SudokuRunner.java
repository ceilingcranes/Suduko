/**
 * SudokuRunner.java  01/27/07
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

/**
 * Application to run GridWorld Sudou game.
 */
public class SudokuRunner
{
	/**
	 * Creates and shows a <code>SudokuWorld</code>.
	 */
	public static void main(String[] args)
	{
        SudokuWorld world = new SudokuWorld();
        world.show();
	}
}
