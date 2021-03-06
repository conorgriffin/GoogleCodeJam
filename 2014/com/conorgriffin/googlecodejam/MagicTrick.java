package com.conorgriffin.googlecodejam;

import java.util.ArrayList;
import java.util.List;

/**
 * This class solves the Magic Trick problem from Google Code Jam 2014 (see link below)
 * 
 * @see <a href="https://code.google.com/codejam/contest/2974486/dashboard#s=p0">
 * https://code.google.com/codejam/contest/2974486/dashboard#s=p0
 * </a>
 * 
 * @author Conor Griffin
 * 
 */
public class MagicTrick extends GoogleCodeJam {
    
    public MagicTrick() {
        // Input files beginning with 'A'
        this.inputFileName = "A-small-attempt.in.txt";
        this.competitionYear = "2014";
        this.roundName = "Qualification";
    }
    
    /**
     * Check the number of possible results based on the player's selected rows.
     * If there's only 1 possible result, then output the card number.  If there's
     * more than 1 possible result then output 'Bad magician!' as he obviously
     * screwed up!  If there's no possible results then output 'Volunteer cheated!'
     * since they lied about the card being in both rows.
     * 
     * @return String   A string containing the result for this case
     */
    @Override
    protected String solve() {     
        int firstGuess = scanner.nextInt();
        List<Integer> firstRow = fetchLine(firstGuess);
        
        int secondGuess = scanner.nextInt();
        List<Integer> secondRow = fetchLine(secondGuess);
        
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

    /**
     * Skip through the input using the to fetch the player's selected row.
     * Then skip the remainder of the rows for this turn to point at the input 
     * line containing the next row selection
     * 
     * @return  A list containing the card numbers in the selected row of cards
     * @param   The player's selected row
     */
    private List<Integer> fetchLine(int num) {
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
    
    public static void main(String[] args) {
        GoogleCodeJam gcj = new MagicTrick();
        gcj.run();
    }

}
