package com.shiv.exception.multithreading;
import java.util.concurrent.CountDownLatch;

public class CountdownLatchWithOnEveryDecrement {
    public static void main(String[] args) {
    }
}

class Worker implements Runnable {
    private final int id;
    private final CountDownLatch latch;

    public Worker(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            // Simulating some work being done by the thread
            System.out.println("Worker " + id + " is working..."+Thread.currentThread().getName());
            Thread.sleep((int) (2* 1000)); // Simulate work
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Decrement the latch count when the work is done
            System.out.println("Worker " + id + " finished work.");
            latch.countDown();  // Decrement latch count
        }
    }
}
