package com.nosharing.prog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Naomi on 9/20/16.
 */
public class RecordAggregatorNoSharing {
    private static Map<String, Map<String,Integer>> finalMap = new HashMap<String, Map<String,Integer>>();

    //This method aggregates the result (no shared HashMap) of all the running threads into one single HashMap which is
    // static and hence remains the same across all the objects
    public static void aggregateResult(Map<String, Map<String, Integer>> map){
        int sum = 0;
        Map<String, Integer> tmaxValues = new HashMap<String,Integer>();
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()) {
            if (finalMap.containsKey(entry.getKey())) {
                tmaxValues = finalMap.get(entry.getKey());
                sum = tmaxValues.get("SUM") + entry.getValue().get("SUM");
                tmaxValues.put("SUM", sum);
                tmaxValues.put("COUNT", tmaxValues.get("COUNT") + entry.getValue().get("COUNT"));
            } else {
                finalMap.put(entry.getKey(), entry.getValue());
            }
        }
    }

    // This method calculates the average of all Tmax per station IDs obtained from the final Hashmap
    public static void findAverage(Map<String, Map<String, Integer>> map) {
        Map<String, Double> finalMap = new HashMap<>();
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()) {
            Integer sum = entry.getValue().get("SUM");
            Integer count = entry.getValue().get("COUNT");
            Double average = (double) sum / count;
            finalMap.put(entry.getKey(), average);
        }
    }

    public static Map<String, Map<String, Integer>> getFinalMap () {
            return finalMap;
        }

    public static void setFinalMap(Map<String, Map<String, Integer>> finalMap) {
        RecordAggregatorNoSharing.finalMap = finalMap;
    }

    // Calculates Fibonacci series for value 17
    public static long fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n-1) + fibonacci(n-2);
    }

}
