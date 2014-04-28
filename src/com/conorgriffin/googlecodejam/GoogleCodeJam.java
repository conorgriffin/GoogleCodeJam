package com.conorgriffin.googlecodejam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * This abstract class handles the files for input/output for Google Code Jam problems
 * The subclasses will perform the problem-specific logic, this abstract class removes 
 * the need to put logic in each subclass to handle IO and formatting of the standard 
 * output.
 * 
 * @author Conor Griffin
 * 
 */
public abstract class GoogleCodeJam {
	
	private BufferedReader reader;
	private PrintWriter writer;
	protected Scanner scanner;
	protected String INPUT_FILE_NAME;
	protected String COMPETITION_YEAR;
	protected String ROUND_NAME;
	
	/**
	 * Returns the name of the input file specified by the constant INPUT_FILE_NAME in
	 * the subclass
	 * 
	 * @return The name of the input file
	 */
	protected String getInputFileName() {
		return COMPETITION_YEAR + File.separator + ROUND_NAME + File.separator + INPUT_FILE_NAME;
	}
	
	/**
	 * Subclasses will override this to perform problem-specific logic.
	 */
	protected abstract String solve();
	
	/**
	 * Fetch the input files and generate and write to the output files specified by the subclass
	 */
	protected void run() {
		reader = null;
		writer = null;
		String file = getInputFileName();
		
		try {
			reader = new BufferedReader(new FileReader("inputs" + File.separator + file));
			scanner = new Scanner(reader);
			String[] tokens = file.split("\\.(?=[^\\.]+.txt$)");
			writer = new PrintWriter("outputs" + File.separator + tokens[0] + ".out.txt", "UTF-8");
			
			// run the solve() method in the subclass for each case
			int caseCount = scanner.nextInt();
			for(int i = 1; i <= caseCount; i++) {
				printResults(i, solve());
			}
			scanner.close();
			writer.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("The file \"" + file + "\" does not exist");
			fnfe.printStackTrace();
			System.exit(1);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported encoding specified: UTF-8");
			e.printStackTrace();
			System.exit(1);
		} catch (SecurityException se){
			System.out.println("Security Exception:" + se.getLocalizedMessage());
			se.printStackTrace();
			System.exit(1);
		} finally {
			if(reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					System.out.println("Close of BufferedReader failed with IOException:" + e.getLocalizedMessage());
					e.printStackTrace();
					System.exit(1);
				}
		}
		
	}
	
	/**
	 * Prints the result for each test case to the console and also to the output file
	 * specified by the subclass.
	 * 
	 * @param n			The test case number
	 * @param result	The result for test case n
	 */
	protected void printResults(int n, String result) {
		System.out.println("Case #" + n + ": " + result);
		writer.println("Case #" + n + ": " + result);
	}

}
