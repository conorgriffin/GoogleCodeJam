package com.conorgriffin.googlecodejam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/*
 * This abstract class handles the files for input/output for Google Code Jam problems
 * The subclasses will perform the problem-specific logic, this abstract class just 
 * removes the need to put logic in each subclass to handle IO and formatting of the 
 * standard output.
 */
public abstract class GoogleCodeJam {
	
	private BufferedReader reader;
	private PrintWriter writer;
	protected Scanner scanner;

	protected abstract String getInputFileName();
	
	protected abstract void processInput() throws IOException;
	
	protected void run() throws IOException {
		reader = null;
		writer = null;
		String file = getInputFileName();
		try {
			reader = new BufferedReader(new FileReader("inputs" + File.separator + file));
			scanner = new Scanner(reader);
			String[] tokens = file.split("\\.(?=[^\\.]+$)");
			writer = new PrintWriter("outputs" + File.separator + tokens[0] + ".out", "UTF-8");
		} catch (FileNotFoundException fnfe) {
			System.out.println("The file \"" + file + "\" does not exist");
			fnfe.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported encoding specified: UTF-8");
			e.printStackTrace();
		}

		processInput();
		
		writer.close();
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println("An IO Exception occurred");
			e.printStackTrace();
		}

	}
		
	protected void printResults(int n, String result) {
		System.out.println("Case #" + (n+1) + ": " + result);
		writer.println("Case #" + (n+1) + ": " + result);
	}

}
