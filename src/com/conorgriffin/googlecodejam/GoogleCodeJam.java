package com.conorgriffin.googlecodejam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
    protected String inputFileName;
    protected String competitionYear;
    protected String roundName;
    private static final String FILE_ENCODING = "UTF-8";
    
    /**
     * Subclasses will override this to perform problem-specific logic.
     */
    protected abstract String solve();
    
    /**
     * Fetch the input files and generate and write to the output files specified by the subclass
     */
    protected void run() {
        String file = competitionYear + File.separator + roundName + File.separator + inputFileName;
        String[] tokens = file.split("\\.(?=[^\\.]+.txt$)");
        
        try (
            BufferedReader reader = new BufferedReader(new FileReader("inputs" + File.separator + file));
            PrintWriter writer = new PrintWriter("outputs" + File.separator + tokens[0] + ".out.txt", FILE_ENCODING);
        ) {
            scanner = new Scanner(reader);
            // run the solve() method in the subclass for each case
            int caseCount = Integer.parseInt(scanner.nextLine());
            for(int i = 1; i <= caseCount; i++) {
                String solution = solve();
                writer.println("Case #" + i + ": " + solution);
                System.out.println("Case #" + i + ": " + solution);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println(ioe.getMessage());
            System.exit(1);
        }
    }
}