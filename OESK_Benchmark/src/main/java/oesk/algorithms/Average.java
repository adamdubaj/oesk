package oesk.algorithms;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Average {
    private int listSize = 0;
    private Double summary = 0d;

    public double getAverageValue(ArrayList<BigDecimal> list){
        listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            summary += list.get(i).doubleValue();
        }
        return summary /listSize;
    }

}
