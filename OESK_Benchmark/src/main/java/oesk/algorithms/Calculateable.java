package oesk.algorithms;


import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface Calculateable {
    double calculateByIteration(int value);
    double calculateByRecursion(int value);

    long getNanoTimeIteration(int value);
    ArrayList<Long> getNanoTimeIteration(int value, int repeatTimes);
    LinkedHashMap<Integer, Long> getCalculateByIterationTime(int startValue, int stopValue);

    long getNanoTimeRecursion(int value);
    ArrayList<Long> getNanoTimeRecursion(int value, int repeatTimes);
    LinkedHashMap<Integer, Long> getCalculateByRecursionTime(int startValue, int stopValue);
}
