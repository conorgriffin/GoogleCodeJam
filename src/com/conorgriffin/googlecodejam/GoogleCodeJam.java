package com.conorgriffin.googlecodejam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * This abstract class handles the files for input/output for Google Code Jam problems
 * The subclasses will perform the problem-specific logic, this abstract class removes 
 * the need to put logic in each subclass to handle IO and formatting of the output.
 * 
 * @author Conor Griffin
 * 
 */
public abstract class GoogleCodeJam {
    
    protected Scanner scanner;
    protected String INPUT_FILE_NAME;
    protected String COMPETITION_YEAR;
    protected String ROUND_NAME;
    protected Path file;
    
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
        String file = getInputFileName();
        String[] tokens = file.split("\\.(?=[^\\.]+.txt$)");
        
        try (
            BufferedReader reader = new BufferedReader(new FileReader("inputs" + File.separator + file));
            PrintWriter writer = new PrintWriter("outputs" + File.separator + tokens[0] + ".out.txt", "UTF-8");
        ) {
            scanner = new Scanner(reader);
            // run the solve() method in the subclass for each case
            int caseCount = scanner.nextInt();
            for(int i = 1; i <= caseCount; i++) {
                printResults(i, solve(), writer);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println(ioe.getMessage());
            System.exit(1);
        }
    }
    
    /**
     * Print the result for each test case to the console and also to the output file
     * specified by the subclass.
     * 
     * @param n         The test case number
     * @param result    The result for test case n
     */
    protected void printResults(int n, String result, PrintWriter writer) {
        System.out.println("Case #" + n + ": " + result);
        writer.println("Case #" + n + ": " + result);
    }

}
