package com.nosharing.prog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Naomi on 9/16/16.
 */
public class ProcessRecordsNoSharingFib implements Runnable{
    private Map<String, Map<String,Integer>> map = new HashMap<String, Map<String,Integer>>();
    private final List<String> records;

    public ProcessRecordsNoSharingFib(List<String> fileRecords) {
        records = fileRecords;
    }

    public Map<String, Map<String, Integer>> getMap() {
        return map;
    }

    public void setMap(Map<String, Map<String, Integer>> map) {
        this.map = map;
    }

    //This method aggregates the station ID and Tmax(sum and count) values into a static Hashmap which is shared across
    // all the objects created in the threads. Hence this works as a shared memory architecture where all threads update
    // the same HashMap
    public void run(){
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
                    //Calling the fibonacci function
                    RecordAggregatorNoSharing.fibonacci(17);
                } else {
                    Map<String, Integer> tmaxValues1= new HashMap<String,Integer>();
                    tmaxValues1.put("SUM", Integer.parseInt(recordArray[3]));
                    tmaxValues1.put("COUNT", 1);
                    map.put(recordArray[0], tmaxValues1);
                }

            }
        }
    }

}
