package oesk.algorithms;


public class Potegowanie {

    public double calculateIteration(int n, int b) {
        long w = 1;

        while(n>0)
        {
            if (n%2 == 1) //jesli bit jest = 1
                w *= b;

            b*= b;
            n/=2; //skrócenie o jeden bit
        }
        return w;
    }

    public double calculateRecursion(double n, double a) {
        if(n==0)
            return 1;

        if(n%2 == 1) //gdy n jest nieparzyste
            return a * calculateRecursion(a, n - 1);

        //żeby dwa razy nie wchodzić w tą samą rekurencję
        double w = calculateRecursion(a, n/2);
        return w * w;
    }
}
