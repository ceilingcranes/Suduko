/**
 * Puzzle.java  01/27/07
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

import java.io.IOException;
import java.util.Scanner;
import java.net.URL;
import java.io.PrintStream;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * Represents a single Sudoku puzzle.
 */
public class Puzzle
{
	/** Display names for the 4 difficulty levels */
	public static final String[] DIFFICULTY_NAMES = {
		"", "Easy", "Medium", "Hard", "Evil"};

	/** int constant for the easy difficulty level */
	public static final int EASY = 1;
	/** int constant for the medium difficulty level */
	public static final int MEDIUM = 2;
	/** int constant for the hard difficulty level */
	public static final int HARD = 3;
	/** int constant for the evil difficulty level */
	public static final int EVIL = 4;

	/** int constant for the internet puzzle source */
	public static final int INTERNET = 10;
	/** int constant for the file puzzle source */
	public static final int FILE = 11;

	/** String value for the CR LF character sequence */
	private static final String CRLF = "" + (char)13 + (char)10;

	/** Difficulty for this puzzle */
	private int difficulty;
	/** Original values for this puzzle */
	private String original;
	/** Solution values for this puzzle */
	private String solution;

	/**
	 * Constructs a puzzle using a game from either the
	 * Internet (http://show.websudoku.com) or the
	 * Games folder (previously downloaded games).
	 * @param level the difficulty of the game.  Legal
	 *        values are Puzzle.EASY, Puzzle.MEDIUM, Puzzle.HARD,
	 *        and Puzzle.EVIL.
	 * @param location the location of the puzzle.  Legal
	 *        values are Puzzle.INTERNET and Puzzle.FILE.
	 * @throws IllegalArgumentException when either the
	 *         <code>level</code> or <code>location</code>
	 *         are not in range.
	 * @throws Error when the retrived puzzle format is incorrect.
	 */
	public Puzzle(int level, int location)
	{
		if (level < 1 || level > 4)
			throw new IllegalArgumentException(
				"level not int the range [1..4]");

		if (location < 10 || location > 11)
			throw new IllegalArgumentException(
				"location not INTERNET or FILE");

		difficulty = level;

		if (location == INTERNET)
			getPuzzleFromInternet();
		else
			getPuzzleFromFile();

		if (solution.length() != 81)
			throw new Error("Solution string length incorrect");
		if (original.length() != 81)
			throw new Error("Original string length incorrect");
	}

	/**
	 * Gets a puzzle from the Internet (http://show.websudoku.com).
	 * @throws MalformedURLException - should never happen.
	 * @throws Error when websudoku can't be accessed.
	 */
	private void getPuzzleFromInternet()
	{
		URL sudoku = null;
		try
		{
			sudoku = new URL("http://show.websudoku.com/?level=" +
					         difficulty);
		}
		catch (java.net.MalformedURLException e)
		{
			System.exit(1);	// Should never happen.
		}

        Scanner in = null;
        try
        {
           	in = new Scanner(sudoku.openStream());
        }
        catch (IOException e)
        {
        	throw new Error("Error accessing websudoku.com");
        }

        String cheat = in.findWithinHorizon(
        	"var\\s*cheat='\\d+", 0);
        solution = cheat.substring(11);

        in.findWithinHorizon("<TABLE", 0);
        in.findWithinHorizon("<TABLE", 0);
        in.findWithinHorizon("<TABLE", 0);

		original = "";
        for (int i = 0; i < 81; i++)
        {
        	String td = in.findWithinHorizon(
        		"<TD.*?</TD", 0);
        	int index = td.indexOf("VALUE=");
        	char value;
        	if (index < 0)
        		value = '0';
        	else
        		value = td.charAt(index + 7);
        	original += value;
        }
	}

	/**
	 * Gets a puzzle from the Games folder
	 * (previously downloaded from http://show.websudoku.com).
	 * @throws Error when file can't be accessed - should not happen.
	 */
	private void getPuzzleFromFile()
	{
		JFileChooser chooser =
			new JFileChooser(".\\Games");
        FileFilter filter = new FileFilter()
        {
            public boolean accept(File file)
            {
            	String fileName = file.getName();
            	int index = fileName.lastIndexOf(".");
            	if (index < 0)
            		return false;
            	String ext = fileName.substring(index + 1);
            	return ext.equals(
            		DIFFICULTY_NAMES[difficulty] + "Sudoku");
            }
            public String getDescription()
            {
            	return DIFFICULTY_NAMES[difficulty] +
            		" Sudoku Game Files";
        	}
        };
        chooser.setFileFilter(filter);
        File file = null;
        do
        {
        	chooser.showOpenDialog(null);
       		file = chooser.getSelectedFile();
        }
        while (file == null);

		Scanner in = null;
		try
		{
        	in = new Scanner(file);
		}
		catch (FileNotFoundException e)
		{
			throw new Error("File Not Found");
		}
        solution = in.nextLine();
        original = in.nextLine();
	}

	/**
	 * Gets the original puzzle value at
	 * location <code>loc</code>.
	 * @param loc the location of the value
	 * @return the puzzle value
	 */
	public int getOriginalValue(Location loc)
	{
		return original.charAt(loc.getRow() * 9 + loc.getCol()) - '0';
	}

	/**
	 * Gets the solution puzzle value at
	 * location <code>loc</code>.
	 * @param loc the location of the solution value
	 * @return the puzzle solution value
	 */
	public int getSolutionValue(Location loc)
	{
		return solution.charAt(loc.getRow() * 9 + loc.getCol()) - '0';
	}


	/**
	 * Downloads games into the Games folder for offline play.
	 * @param args not used
	 * @throws Error when websudoku can't be accessed.
	 */
	public static void main(String[] args)
	{
		final int NUM_PUZZLES_OF_EACH_TYPE = 5;

		for (int d = 1; d < DIFFICULTY_NAMES.length; d++)
			for (int i = 0; i < NUM_PUZZLES_OF_EACH_TYPE; i++)
			{
				Puzzle p = new Puzzle(d, INTERNET);
				File outFile = new File(
					".\\Games\\Game" + i +
					"." +
					DIFFICULTY_NAMES[d] + "Sudoku");
				PrintStream out = null;
				try
				{
					out = new PrintStream(outFile);
				}
				catch (FileNotFoundException e)
				{
					throw new Error("File Not Found");
				}

				out.println(p.solution);
				out.println(p.original);
				out.close();
			}
	}
}
