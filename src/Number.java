/**
 * Number.java  01/27/07
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
import java.awt.Color;

public class Number
{
	/** Original value for <code>type</code> */
	public static final int ORIGINAL = 1;
	/** Player value for <code>type</code> */
	public static final int PLAYER = 2;
	/** Cheat value for <code>type</code> */
	public static final int CHEAT = 3;

	/** Value for this number (0-9).<br>
	 *       0 is a special value meanging no value */
	private int value;

	/** The location for this number */
	private Location location;

	/** The type of this object.  Possible values are
	 *        Number.ORIGINAL, Number.PLAYER, and Number.CHEAT. */
	private int type;

	/**
	 * Constructs a number.
	 * @param val the value for this number (0-9).
	 *        0 is a special value meanging that this
	 *        cell has not had a value chosen.
	 * @param loc the location for this number.
	 * @param t the type of this object.  Possible values are
	 *        Number.ORIGINAL, Number.PLAYER, and Number.CHEAT.
	 */
	public Number(int val, Location loc, int t)
	{
		value = val;
		location = loc;
		type = t;
	}

	/**
	 * Gets the text to be displayed in the grid.
	 * @return the text string.
	 */
	public String getText()
	{
		if (value != 0)
			return "" + value;
		else
			return "";
	}

	/**
	 * Gets the value of the number.
	 * @return the value.
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * Gets the type of the number.
	 * @return the type.
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * Gets the color used to display the number in the grid.
	 * The color depends on the type:<br>
	 *     ORIGINAL values are colored BLACK<br>
	 *     PLAYER   values are colored BLUE<br>
	 *     CHEAT    values are colored RED
	 * @return the color for the text
	 */
	public Color getTextColor()
	{
		if (type == ORIGINAL)
			return Color.BLACK;
		else if (type == PLAYER)
			return Color.BLUE;
		else
			return Color.RED;
	}

	/**
	 * Gets the background color for the number.  This color
	 * is based on a alternating 3x3 boxes.  Boxes are colored
	 * GRAY or WHITE with the upper left box being WHITE.
	 * @return the background color
	 */
	public Color getColor()
	{
		int locNumber = location.getRow() / 3 + location.getCol() / 3;
		if (locNumber % 2 == 1)
			return Color.GRAY;
		else
			return Color.WHITE;
	}
}
