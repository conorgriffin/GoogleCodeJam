package com.conorgriffin.googlecodejam;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * This class solves the Cookie Clicker Alpha problem from Google Code Jam 2014 (see link below)
 * 
 * @see <a href="https://code.google.com/codejam/contest/2974486/dashboard#s=p1">https://code.google.com/codejam/contest/2974486/dashboard#s=p1</a>
 * 
 * @author Conor Griffin
 * 
 */
public class CookieClickerAlpha extends GoogleCodeJam {

	// Input files beginning with 'B'
	private static final String INPUT_FILE_NAME = "B-large-attempt.in";
	private static final String COMPETITION_YEAR = "2014";
	
	public static void main(String[] args) {
		CookieClickerAlpha cca = new CookieClickerAlpha();
		try {
			cca.run();
		} catch (IOException ioe) {
			System.out.println(ioe.getLocalizedMessage());
			ioe.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * For each case, check whether it's faster to create 'X' cookies with the current
	 * number of factories or with one more factory.  Based on which is faster, either
	 * output the best time with current factories or add another one and iterate
	 * through the calculation again.
	 */
	@Override
	protected void processInput() throws IOException {
		
		DecimalFormat df = new DecimalFormat("#.#######");

		int caseCount = scanner.nextInt();

		for(int i = 0; i < caseCount; i++) {
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
			
			printResults(i, df.format(totalTime)); 
		}
	}

	@Override
	protected String getInputFileName() {
		return COMPETITION_YEAR + File.separator + INPUT_FILE_NAME;
	}
	
}
