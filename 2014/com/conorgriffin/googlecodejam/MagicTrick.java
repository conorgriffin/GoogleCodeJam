package com.conorgriffin.googlecodejam;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class solves the Magic Trick problem from Google Code Jam 2014 (see link below)
 * 
 * @see <a href="https://code.google.com/codejam/contest/2974486/dashboard#s=p0">https://code.google.com/codejam/contest/2974486/dashboard#s=p0</a>
 * 
 * @author Conor Griffin
 * 
 */

public class MagicTrick extends GoogleCodeJam {
	
	// Input files beginning with 'A'
	private static final String INPUT_FILE_NAME = "A-small-attempt.in";
	private static final String COMPETITION_YEAR = "2014";
	
	public static void main(String[] args) {
		MagicTrick magicNumbers = new MagicTrick();
		try {
			magicNumbers.run();
		} catch (IOException ioe) {
			System.out.println(ioe.getLocalizedMessage());
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Loop through each case in the input file and extract the rows for each player's
	 * guess and calculate the result
	 */
	@Override
	protected void processInput() throws IOException {
		
		int caseCount = scanner.nextInt();
		String result = null;
		
		for(int i = 0; i < caseCount; i++) {
			int firstGuess = scanner.nextInt();
			List<Integer> firstRow = fetchLine(firstGuess);
			
			int secondGuess = scanner.nextInt();
			List<Integer> secondRow = fetchLine(secondGuess);
			
			result = getResult(firstRow, secondRow);
			
			printResults(i, result);
		}
		
		scanner.close();
	}

	/**
	 * Skip through the input using the superclass Scanner object to fetch the
	 * player's selected row.  Then skip the remainder of the rows for this turn
	 * to move point the Scanner at the input line containing the next row selection
	 * 
	 * @return	A list containing the card numbers in the selected row of cards
	 * @param	The player's selected row
	 */
	private List<Integer> fetchLine(int num) throws IOException {
		List<Integer> row = new ArrayList<Integer>();
		// skip to the line we want
		for (int i = 0; i < num; i++) {
			scanner.nextLine();
		}
		// get the line we want
		for (int i = 1; i <= 4; i++) {
			row.add(scanner.nextInt());
		}
		// skip the rest of the cards in this round
		for (int i = 0; i <= (4 - num); i++) {
			scanner.nextLine();
		}
		return row;
	}
	
	/**
	 * Check the number of possible results based on the player's selected rows
	 * If there's only 1 possible result, then output the card number.  If there's
	 * more than 1 possible result then output 'Bad magician!' as he obviously
	 * screwed up!  If there's no possible results then output 'Volunteer cheated!'
	 * since they lied about the card being in both rows.
	 * 
	 * @param firstRow	A List of Integers representing the first row of four cards
	 * @param secondRow	A List of Integers representing the second row of four cards
	 * @return String A string containing the result for this case.  This will be one
	 * of "Bad magician!", "Volunteer cheated!" or the number of the card chosen.
	 */
	private String getResult(List<Integer> firstRow, List<Integer> secondRow) {
		int count = 0;
		int cardNumber = 0;
		
		for(Integer card: firstRow) {
			if(secondRow.contains(card.intValue())) {
				cardNumber = card.intValue();
				count++;
			}
		}
		if (count > 1) {
			return "Bad magician!";
		} else if (count == 1) {
			return String.valueOf(cardNumber);
		} else {
			return "Volunteer cheated!";
		}
		
	}

	@Override
	protected String getInputFileName() {
		return COMPETITION_YEAR + File.separator + INPUT_FILE_NAME;
	}
	
}
