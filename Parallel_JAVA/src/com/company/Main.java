package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;


/*
* Create By Thongchai S.
* ref. http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/
* */
class Parallel {

    public static void main(String[] argv) throws ExecutionException, InterruptedException {

        /*
        * @newFixedThreadPool(int nThreads) //เหมาะสำหรับงานหนัก ๆ ของ CPU
        * @newWorkStealingPool(int parallelism) //สร้าง thread แบบ dynamic
        * @newSingleThreadExecutor() //ใช้ 1 thread ประมวลผลแบบ sequential
        * */
        ExecutorService executor = Executors.newWorkStealingPool();

        /*
        * List of Process
        *
        * */
        List<Callable<Object>> callables = Arrays.asList(
                callable("task4", 10),
                callable("task5", 10),
                callable2("task2",10),
                callable2("task3", 50000));

        List<Future<Object>> result=  executor.invokeAll(callables);
        /*
        * @awaitTerminationAfterShutdown
        * Wait Process complete
        * */
        awaitTerminationAfterShutdown(executor);

        /*
        * Get Data By process
        * */
        for(Future<Object> future : result){
            if(future.isDone()){
                System.out.println("isDone"+" "+future.get());
            }
        }
    }
   static Callable<Object> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            System.out.println(result);
            return result;
        };
    }
    static Callable<Object> callable2(String result,int power) {
        return () -> {
            Long value=0L;

            for(int i=0;i<15;i++){
                value+=i;
//                System.out.println(result+" "+value);
            }
//            Long value=pow(1,power);
            System.out.println(result+" finnish: "+value);
           return value;

        };
    }
    public static Long pow( int a, int n) {
        if ( n == 0 ) {
            return 1L;
        }
        return ( a * pow(a,n-1));
    }

    public static void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}