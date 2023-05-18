package org.example.counter;

public class Counter implements Runnable {

    private int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public int getValue() {
        return count;
    }

    @Override
    public void run() {
        // 동기화 처리
        synchronized (this) {
            this.increment();
            // 1
            System.out.println("Value For Thread After increment " + Thread.currentThread().getName() + " " + this.getValue());
            this.decrement();
            // 0
            System.out.println("Value for Thread at last " + Thread.currentThread().getName() + " " + this.getValue());
        }
    }
}
