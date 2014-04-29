package com.conorgriffin.googlecodejam;

import java.text.DecimalFormat;

/**
 * This class solves the Cookie Clicker Alpha problem from Google Code Jam 2014 (see link below)
 * 
 * @see <a href="https://code.google.com/codejam/contest/2974486/dashboard#s=p1">
 * https://code.google.com/codejam/contest/2974486/dashboard#s=p1
 * </a>
 * 
 * @author Conor Griffin
 * 
 */
public class CookieClickerAlpha extends GoogleCodeJam {
    
    public CookieClickerAlpha() {
        // Input files beginning with 'B'
        this.inputFileName = "B-small-attempt.in.txt";
        this.competitionYear = "2014";
        this.roundName = "Qualification";
    }
    
    /**
     * For each case, check whether it's faster to create 'X' cookies with the current
     * number of factories or with one more factory.  Based on which is faster, either
     * output the best time with current factories or add another one and iterate
     * through the calculation again.
     * 
     * For each case:
     *      c = cost of a new factory in cookies
     *      f = increase in production rate for each additional factory
     *      x = number of cookies you need to produce
     */
    @Override
    protected String solve() {
        DecimalFormat df = new DecimalFormat("#.#######");

        double c = scanner.nextDouble();
        double f = scanner.nextDouble();
        double x = scanner.nextDouble();
        double initialRate = 2.0;
        double totalTime = 0.0;
        
        double currentProductionRate = initialRate;
        double bestTimeWithExistingFactories = x/currentProductionRate;
        double bestTimeWithAdditionalFactory = c/currentProductionRate + x/(currentProductionRate + f);
        
        while(bestTimeWithExistingFactories > bestTimeWithAdditionalFactory) {
            // If it's slower to make 'X' cookies with the current factories then we must
            // add another factory, increasing the currentProductionRate accordingly and
            // adding the time to create this factory to the totalTime required then
            // recalculate the bestTimeWithExistingFactories and bestTimeWithAdditionalFactory
            // variables in order to compare which is faster for the next iteration of the loop
            totalTime += c/currentProductionRate;
            currentProductionRate += f;
            bestTimeWithExistingFactories = x/currentProductionRate;
            bestTimeWithAdditionalFactory = c/currentProductionRate + x/(currentProductionRate + f);
        }
        
        totalTime += x/currentProductionRate;
        
        return(df.format(totalTime)); 
    }

    public static void main(String[] args) {
        GoogleCodeJam gcj = new CookieClickerAlpha();
        gcj.run();
    }
    
}
