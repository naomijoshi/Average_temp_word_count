package com.nosharing.prog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Naomi on 9/15/16.
 */
public class Application {

    public static void main(String[] args) {
        List<String> records = ReadFile.readInputFile(args[0]);
        //NO-SHARING
        int j=0;
        //Creates an array list of time taken by each of the 10 runs of the program
        ArrayList<Long> times = new ArrayList<Long>();
        while (j<10)//10 loops of execution
        {
            Long startTime = null;
            int size = records.size();
            // FInding the no. of processors available at Runtime
            int cores = Runtime.getRuntime().availableProcessors();
            //Creating thread array with the size of no. of processors available
            Thread[] threads = new Thread[cores];
            ProcessRecordsNoSharing[] object = new ProcessRecordsNoSharing[cores];
            for (int i = 0; i < threads.length; i++) {
                // This blocks splits the input list into sublists based on how many threads are created
                // This happens dynamically and list is divided into equal sublists
                List<String> sublist;
                if (i == threads.length - 1) {
                    sublist = records.subList((i * size) / threads.length, size);
                } else
                    sublist = records.subList((i * size) / threads.length, (i + 1) * (size / threads.length));
                object[i] = new ProcessRecordsNoSharing(sublist);
                threads[i] = new Thread(object[i]);
                if(i==0){
                    //Records the start time when 1st thread starts
                    startTime = System.currentTimeMillis();
                }
                //All threads are started in the loop as they are created
                threads[i].start();
            }
            //This blocks joins all the threads so the main thread waits till each of the thread is complete
            for (int i = 0; i < threads.length; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //After each thread is complete the method to aggregate the sum and count for station ID into one single
                // HashMap
                RecordAggregatorNoSharing.aggregateResult(object[i].getMap());
            }
            //This line calculates the average Tmax for all station IDs taking the final sum and count HashMap
            RecordAggregatorNoSharing.findAverage(RecordAggregatorNoSharing.getFinalMap());
            Long endTime = System.currentTimeMillis();
            //Adds the execution time to the Array for every execution
            times.add(endTime-startTime);
            j++;
        }
        System.out.println("NORMAL");
        System.out.println("No Sharing maximum time " + Collections.max(times));
        System.out.println("No Sharing minimum time " + Collections.min(times));
        System.out.println("No Sharing average time " + times.stream().mapToDouble(a -> a).average().getAsDouble());

        //NO-SHARING FIBONACCI
        j=0;
        //Creates an array list of time taken by each of the 10 runs of the program with Fibonacci
        ArrayList<Long> timesFib = new ArrayList<Long>();
        while (j<10)//10 loops of execution
        {
            Long startTime = null;
            int size = records.size();
            // FInding the no. of processors available at Runtime
            int cores = Runtime.getRuntime().availableProcessors();
            //Creating thread array with the size of no. of processors available
            Thread[] threads = new Thread[cores];
            ProcessRecordsNoSharingFib[] object = new ProcessRecordsNoSharingFib[cores];
            for (int i = 0; i < threads.length; i++) {
                // This blocks splits the input list into sublists based on how many threads are created
                // This happens dynamically and list is divided into equal sublists
                List<String> sublist;
                if (i == threads.length - 1) {
                    sublist = records.subList((i * size) / threads.length, size);
                } else
                    sublist = records.subList((i * size) / threads.length, (i + 1) * (size / threads.length));
                object[i] = new ProcessRecordsNoSharingFib(sublist);
                threads[i] = new Thread(object[i]);
                if(i==0){
                    //Records the start time when 1st thread starts
                    startTime = System.currentTimeMillis();
                }
                //All threads are started in the loop as they are created
                threads[i].start();
            }
            //This blocks joins all the threads so the main thread waits till each of the thread is complete
            for (int i = 0; i < threads.length; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //After each thread is complete the method to aggregate the sum and count for station ID into one single
                // HashMap
                RecordAggregatorNoSharing.aggregateResult(object[i].getMap());
            }
            //This line calculates the average Tmax for all station IDs taking the final sum and count HashMap
            RecordAggregatorNoSharing.findAverage(RecordAggregatorNoSharing.getFinalMap());
            Long endTime = System.currentTimeMillis();
            //Adds the execution time to the Array for every execution with Fibonacci
            timesFib.add(endTime-startTime);
            j++;
        }
        System.out.println("FIBONACCI");
        System.out.println("No Sharing maximum time " + Collections.max(timesFib));
        System.out.println("No Sharing minimum time " + Collections.min(timesFib));
        System.out.println("No Sharing average time " + timesFib.stream().mapToDouble(a -> a).average().getAsDouble());

    }
}
