package com.sequential.prog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Naomi on 9/16/16.
 */
public class ProcessRecordsSeqFib {
    private static Map<String, Map<String,Integer>> map = new HashMap<String, Map<String,Integer>>();

    public Map<String, Map<String, Integer>> getMap() {
        return map;
    }

    public void setMap(Map<String, Map<String, Integer>> map) {
        this.map = map;
    }

    //This method aggregates the station ID and Tmax(sum and count) values into a static Hashmap which is shared across
    // all the objects created in the threads. Hence this works as a shared memory architecture where all threads update
    // the same HashMap
    public void processRecordsForSeq(List<String> records){
        int sum = 0;
        Map<String, Integer> tmaxValues = new HashMap<String,Integer>();
        for (String line: records) {
            if (line.contains("TMAX")){
                String[] recordArray = line.split(",");
                if (map.containsKey(recordArray[0])){
                   tmaxValues = map.get(recordArray[0]);
                    sum = tmaxValues.get("SUM")+Integer.parseInt(recordArray[3]);
                    tmaxValues.put("SUM", sum );
                    tmaxValues.put("COUNT", tmaxValues.get("COUNT")+1);
                    //calling Fibonacci function
                    fibonacci(17);
                } else {
                    Map<String, Integer> tmaxValues1= new HashMap<String,Integer>();
                    tmaxValues1.put("SUM", Integer.parseInt(recordArray[3]));
                    tmaxValues1.put("COUNT", 1);
                    map.put(recordArray[0], tmaxValues1);
                }
            }
        }
        findAverage(map);
    }
    // This method calculates the average of all Tmax per station IDs obtained from the final Hashmap
    private Map<String,Double> findAverage(Map<String, Map<String, Integer>> map) {
        Map<String,Double> finalMap = new HashMap<String, Double>();
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()){
            Integer sum = entry.getValue().get("SUM");
            Integer count = entry.getValue().get("COUNT");
            Double average = (double) sum/count;
            finalMap.put(entry.getKey(), average);
        }
        return finalMap;
    }

    //Fibonacci method
    private static long fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n-1) + fibonacci(n-2);
    }
}
