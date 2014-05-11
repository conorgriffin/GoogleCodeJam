package com.conorgriffin.googlecodejam;

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

    private long gcd(long p, long q) {
        if(q==0) return p;
        if(p==0) return q;
        return gcd(q, p%q);
    }
    
}
