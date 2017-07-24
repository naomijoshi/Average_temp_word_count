package com.nolock.prog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Naomi on 9/18/16.
 */
public class Application {

    public static void main(String[] args) {
        List<String> records = ReadFile.readInputFile(args[0]);
        //No Lock run
        int j=0;
        //Creates an array list of time taken by each of the 10 runs of the program
        ArrayList<Long> times = new ArrayList<Long>();
        while (j<10) //10 loops of execution
        {
            Long startTime = null;
            int size = records.size();
            // FInding the no. of processors available at Runtime
            int cores = Runtime.getRuntime().availableProcessors();
            //Creating thread array with the size of no. of processors available
            Thread[] threads = new Thread[cores];

            for (int i = 0; i < threads.length; i++) {
                // This blocks splits the input list into sublists based on how many threads are created
                // This happens dynamically and list is divided into equal sublists
                List<String> sublist;
                if(i==threads.length - 1){
                    sublist = records.subList((i*size)/threads.length,size);
                }
                else
                    sublist = records.subList((i*size)/threads.length, (i+1)*(size/threads.length));
                threads[i] = new Thread(new ProcessRecordsNoLock(sublist));
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

            }
            //Takes the hashmap where all the station ID and sum,count is stored and calculates the average
            ProcessRecordsNoLock.findAverage(ProcessRecordsNoLock.getMap());
            Long endTime = System.currentTimeMillis();
            times.add(endTime-startTime);
            j++;
        }
        System.out.println("NORMAL");
        System.out.println("No lock maximum time " + Collections.max(times));
        System.out.println("No lock minimum time " + Collections.min(times));
        System.out.println("No lock average time " + times.stream().mapToDouble(a -> a).average().getAsDouble());

        //No Lock run - Fibonacci
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

            for (int i = 0; i < threads.length; i++) {
                // This blocks splits the input list into sublists based on how many threads are created
                // This happens dynamically and list is divided into equal sublists
                List<String> sublist;
                if(i==threads.length - 1){
                    sublist = records.subList((i*size)/threads.length,size);
                }
                else
                    sublist = records.subList((i*size)/threads.length, (i+1)*(size/threads.length));
                threads[i] = new Thread(new ProcessRecordsNoLockFib(sublist));
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

            }
            //Takes the hashmap where all the station ID and sum,count is stored and calculates the average
            ProcessRecordsNoLock.findAverage(ProcessRecordsNoLockFib.getMap());
            Long endTime = System.currentTimeMillis();
            timesFib.add(endTime-startTime);
            j++;
        }
        System.out.println("FIBONACCI");
        System.out.println("No lock maximum time " + Collections.max(timesFib));
        System.out.println("No lock minimum time " + Collections.min(timesFib));
        System.out.println("No lock average tim e " + timesFib.stream().mapToDouble(a -> a).average().getAsDouble());
    }
}
