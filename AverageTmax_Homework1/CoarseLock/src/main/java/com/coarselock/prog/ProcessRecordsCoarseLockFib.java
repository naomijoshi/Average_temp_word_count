package com.coarselock.prog;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Naomi on 9/18/16.
 */
public class ProcessRecordsCoarseLockFib implements Runnable{
    private static Map<String, Map<String,Integer>> map = Collections.synchronizedMap(new HashMap<String, Map<String,Integer>>());
    private final List<String> records;

private static Map<String,Double> finalMap = new HashMap<String, Double>();
    //CReating a static synchronized Map so that multiple threads do not update it at the same time
    public ProcessRecordsCoarseLockFib(List<String> fileRecords) {
            records = fileRecords;
    }

    public static Map<String, Map<String, Integer>> getMap() {
        return map;
    }

    //This method aggregates the station ID and Tmax(sum and count) values into a static Hashmap which is shared across
    // all the objects created in the threads. Hence this works as a shared memory architecture where all threads update
    // the same HashMap
    // This is also a synchronized block so objects inside it are locked by thread which is processing it
    synchronized public void run(){

        int sum = 0;
        Map<String, Integer> tmaxValues;
        for (String line: records) {
            if (line.contains("TMAX")){
                String[] recordArray = line.split(",");
                if (map.containsKey(recordArray[0])){
                    tmaxValues = map.get(recordArray[0]);
                    sum = tmaxValues.get("SUM")+Integer.parseInt(recordArray[3]);
                    tmaxValues.put("SUM", sum );
                    tmaxValues.put("COUNT", tmaxValues.get("COUNT")+1);
                    fibonacci(17);
                } else {
                    Map<String, Integer> tmaxValues1= new HashMap<String,Integer>();
                    tmaxValues1.put("SUM", Integer.parseInt(recordArray[3]));
                    tmaxValues1.put("COUNT", 1);
                    map.put(recordArray[0], tmaxValues1);
                }
            }
        }
    }
    // This method calculates the average of all Tmax per station IDs obtained from the final Hashmap
     public static void findAverage(Map<String, Map<String, Integer>> map) {
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()){
            Integer sum = entry.getValue().get("SUM");
            Integer count = entry.getValue().get("COUNT");
            Double average = (double) sum/count;
            finalMap.put(entry.getKey(), average);
        }
    }

    private static long fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n-1) + fibonacci(n-2);
    }
}
