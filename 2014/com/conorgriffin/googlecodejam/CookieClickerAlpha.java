package com.conorgriffin.googlecodejam;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

// https://code.google.com/codejam/contest/2974486/dashboard#s=p1
// Input files beginning with 'B'

public class CookieClickerAlpha extends GoogleCodeJam {

	private static final String INPUT_FILE_NAME = "B-large-attempt.in";
	private static final String COMPTETITION_YEAR = "2014";
	
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
		return COMPTETITION_YEAR + File.separator + INPUT_FILE_NAME;
	}
	
}
