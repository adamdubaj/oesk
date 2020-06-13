package oesk.algorithms;

public class Euklides {
    public long calculateIteration(long a, long b){
        long pom;
        while(b!=0)
        {
            pom = b;
            b = a%b;
            a = pom;
        }
        return a;
    }

    public long calculateRecursion(long a, long b){
        if(b!=0) return calculateRecursion(b,a%b);

        return a;
    }
}
