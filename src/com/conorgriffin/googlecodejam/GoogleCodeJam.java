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

	/**
	 * Returns the name of the input file specified by the constant INPUT_FILE_NAME in
	 * the subclass
	 * 
	 * @return The name of the input file
	 */
	protected abstract String getInputFileName();
	
	/**
	 * Subclasses will override this to perform problem-specific logic.
	 */
	protected abstract void processInput() throws IOException;
	
	/**
	 * Fetch the input files and generate and write to the output files specified by the subclass
	 */
	protected void run() throws IOException {
		reader = null;
		writer = null;
		String file = getInputFileName();
		
		try {
			reader = new BufferedReader(new FileReader("inputs" + File.separator + file));
			scanner = new Scanner(reader);
			String[] tokens = file.split("\\.(?=[^\\.]+$)");
			writer = new PrintWriter("outputs" + File.separator + tokens[0] + ".out", "UTF-8");
			processInput();
			writer.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("The file \"" + file + "\" does not exist");
			fnfe.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported encoding specified: UTF-8");
			e.printStackTrace();
		} finally {
			if(reader != null) reader.close();
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
		System.out.println("Case #" + (n+1) + ": " + result);
		writer.println("Case #" + (n+1) + ": " + result);
	}

}
