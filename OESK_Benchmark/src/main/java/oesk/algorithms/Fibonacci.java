package oesk.algorithms;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Fibonacci implements Calculateable {

    @Override
    public double calculateByIteration(int n) {
        if( (n == 0) || (n == 1) ) return n;
        double a, b;
        a = 1d; b = 1d;
        double temp;
        for (int i = 0; i < n-2; i++) {
            temp = a;
            a = b;
            b = temp;
            b+=a;
        }
        return b;
    }

    @Override
    public double calculateByRecursion(int value) {
        if(value == 0) return 0;
        if(value == 1) return 1;
        return calculateByRecursion(value-1)+ calculateByRecursion(value-2);
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
