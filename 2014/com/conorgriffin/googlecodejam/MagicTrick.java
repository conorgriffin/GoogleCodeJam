package com.conorgriffin.googlecodejam;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// https://code.google.com/codejam/contest/2974486/dashboard#s=p0
// Input files beginning with 'A'

public class MagicTrick extends GoogleCodeJam {
	
	private static final String INPUT_FILE_NAME = "A-small-attempt.in";
	private static final String COMPTETITION_YEAR = "2014";
	
	public static void main(String[] args) {
		MagicTrick magicNumbers = new MagicTrick();
		try {
			magicNumbers.run();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

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
		return COMPTETITION_YEAR + File.separator + INPUT_FILE_NAME;
	}
	
}
