package oesk.algorithms;


import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Silnia implements Calculateable {

    private long startTime, stopTime;

    @Override
    public double calculateByIteration(int value){
        double wynik=1;
        for (long i = 1; i<=value;i++){
            wynik *= i;
        }
        return wynik;
    }

    @Override
    public long getNanoTimeIteration(int value){
        startTime = System.nanoTime();
        calculateByIteration(value);
        stopTime = System.nanoTime();
        return stopTime - startTime;
    }

    @Override
    public ArrayList<Long> getNanoTimeIteration(int value, int repeatTimes){
        ArrayList<Long> list = new ArrayList<>(repeatTimes);
        for (int i = 0; i < repeatTimes; i++) {
            list.add(getNanoTimeIteration(value));
        }
        return list;
    }

    @Override
    public LinkedHashMap<Integer, Long> getCalculateByIterationTime(int startValue, int stopValue){
        LinkedHashMap<Integer, Long> map = new LinkedHashMap<>(stopValue);
        while(startValue<=stopValue){
            map.put(startValue, getNanoTimeIteration(startValue));
            startValue++;
        }
        return map;
    }

    @Override
    public double calculateByRecursion(int value) {
        if (value<2) return value;
        return value* calculateByRecursion(value-1);
    }

    @Override
    public long getNanoTimeRecursion(int value) {
        startTime = System.nanoTime();
        calculateByRecursion(value);
        stopTime = System.nanoTime();
        return stopTime - startTime;
    }

    @Override
    public ArrayList<Long> getNanoTimeRecursion(int value, int repeatTimes) {
        ArrayList<Long> list = new ArrayList<>(repeatTimes);
        for (int i = 0; i < repeatTimes; i++) {
            list.add(getNanoTimeRecursion(value));
        }
        return list;
    }

    @Override
    public LinkedHashMap<Integer, Long> getCalculateByRecursionTime(int startValue, int stopValue) {
        LinkedHashMap<Integer, Long> map = new LinkedHashMap<>(stopValue);
        while(startValue<=stopValue){
            map.put(startValue, getNanoTimeRecursion(startValue));
            startValue++;
        }
        return map;
    }
}
