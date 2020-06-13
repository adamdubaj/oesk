package oesk.algorithms;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Sumowanie implements Calculateable {

    public double calculateIteration(int n) {
        double wynik=0d;
        for(long i=1; i<=n; i++){
            wynik = wynik + i;
        }
        return wynik;
    }

    private long calculateRecursion(long n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        return n+calculateRecursion(n-1);
    }

    @Override
    public double calculateByIteration(int value) {
        return 0;
    }

    @Override
    public double calculateByRecursion(int value) {
        return 0;
    }

    @Override
    public long getNanoTimeIteration(int value) {
        return 0;
    }

    @Override
    public ArrayList<Long> getNanoTimeIteration(int value, int repeatTimes) {
        return null;
    }

    @Override
    public LinkedHashMap<Integer, Long> getCalculateByIterationTime(int startValue, int stopValue) {
        return null;
    }

    @Override
    public long getNanoTimeRecursion(int value) {
        return 0;
    }

    @Override
    public ArrayList<Long> getNanoTimeRecursion(int value, int repeatTimes) {
        return null;
    }

    @Override
    public LinkedHashMap<Integer, Long> getCalculateByRecursionTime(int startValue, int stopValue) {
        return null;
    }
}
