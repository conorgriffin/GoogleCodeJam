package com.conorgriffin.googlecodejam;

import java.io.File;
import java.io.IOException;

/**
 * @see <a href="https://code.google.com/codejam/contest/2974486/dashboard#s=p2">https://code.google.com/codejam/contest/2974486/dashboard#s=p2</a>
 * 
 * @author Conor Griffin
 * 
 */

public class MineSweeperMaster extends GoogleCodeJam {

	// Input files beginning with 'C'
	private static final String INPUT_FILE_NAME = "C-small-attempt.in";
	private static final String COMPETITION_YEAR = "2014";

	public static void main(String[] args) {
		MineSweeperMaster msm = new MineSweeperMaster();
		try {
			msm.run();
		} catch (IOException ioe) {
			System.out.println(ioe.getLocalizedMessage());
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	protected void processInput() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getInputFileName() {
		return COMPETITION_YEAR + File.separator + INPUT_FILE_NAME;
	}
	
}
