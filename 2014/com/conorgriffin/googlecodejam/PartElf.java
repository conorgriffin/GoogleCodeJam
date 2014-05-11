package com.conorgriffin.googlecodejam;

/**
 * This class solves the Part Elf problem from Google Code Jam 2014 (see link below)
 * 
 * @see <a href="https://code.google.com/codejam/contest/3004486/dashboard#s=p0">
 * https://code.google.com/codejam/contest/3004486/dashboard#s=p0
 * </a>
 * 
 * @author Conor Griffin
 * 
 */
public class PartElf extends GoogleCodeJam {

    public PartElf() {
        // Input files beginning with 'A'
        this.inputFileName = "A-large-practice.in.txt";
        this.competitionYear = "2014";
        this.roundName = "1C";
    }

    public static void main(String[] args) {
        GoogleCodeJam gcj = new PartElf();
        gcj.run();
    }
    
    /**
     * For each case, find the greatest common divisor to reduce the fraction
     * to it's lowest terms.  Then see if the base is a power of 2.  If it's not
     * a power of 2 then print "impossible".  If the base is a power of 2, then
     * count how many times you can double p before it's bigger than q.  This
     * will indicate the number of generations you must go back to find a 
     * descendant who is 1/1 Elf.
     */
    @Override
    protected String solve() {
                            
        String inputCase = scanner.nextLine();
        String[] tokens = inputCase.split("/");

        long p = Long.parseLong(tokens[0]);
        long q = Long.parseLong(tokens[1]);
        
        // get greatest common divisor
        long gcd = gcd(p, q);
        
        p /= gcd;
        q /= gcd;
        
        // see if q is a power of 2
        long tempQ = q;
        while(tempQ%2==0) {
            tempQ /= 2;
        }
        
        if(tempQ == 1) {
            int result = 0;
            while(p < q) {
                p *= 2;
                result++;
            }
            return String.valueOf(result);
        }
                
        return "impossible";
    }

    /**
     * A basic method to calculate the greatest common divisor of two longs p and q
     * @param p the dividend
     * @param q the divisor
     * @return the greatest common divisor of p and q
     */
    private long gcd(long p, long q) {
        // base case
        if(p == 0 || q == 0) return p+q;
        // recursive call
        return gcd(q, p%q);
    }
    
}
