package com.sequential.prog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Naomi on 9/15/16.
 */
public class Application {

    public static void main(String[] args) {
        List<String> records = ReadFile.readInputFile(args[0]);
        // Sequential run
        ProcessRecordsSeq pr = new ProcessRecordsSeq();
        //Creates an array list of time taken by each of the 10 runs of the program
        ArrayList<Long> times = new ArrayList<Long>();
        //10 loops of execution
        for (int i=0;i<10;i++){
            // Record start time
            Long startTime = System.currentTimeMillis();
            pr.processRecordsForSeq(records);
            Long endTime = System.currentTimeMillis();
            // Add the time taken by each execution to an array list
            times.add(endTime - startTime);

        }
        System.out.println("NORMAL");
        System.out.println("Sequential maximum time " + Collections.max(times));
        System.out.println("Sequential minimum time " + Collections.min(times));
        System.out.println("Sequential average time " + times.stream().mapToDouble(a -> a).average().getAsDouble());

        // Sequential run - Fibonacci
        ProcessRecordsSeqFib prf = new ProcessRecordsSeqFib();
        //Creates an array list of time taken by each of the 10 runs of the program with Fibonacci
        ArrayList<Long> timesFib = new ArrayList<Long>();
        //10 loops of execution
        for (int i=0;i<10;i++){
            //Record the start time of each execution
            Long startTime = System.currentTimeMillis();
            prf.processRecordsForSeq(records);
            Long endTime = System.currentTimeMillis();
            // Add the time taken by each execution to an array list
            timesFib.add(endTime - startTime);

        }
        System.out.println("FIBONACCI");
        System.out.println("Sequential maximum time " + Collections.max(timesFib));
        System.out.println("Sequential minimum time " + Collections.min(timesFib));
        System.out.println("Sequential average time " + timesFib.stream().mapToDouble(a -> a).average().getAsDouble());

    }
}
