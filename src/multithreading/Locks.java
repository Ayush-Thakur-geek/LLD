package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Locks {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTypes lock1 = new ReentrantLockTypes();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 5; i++) {
//            executorService.submit(() -> {
//                lock1.increment();
//            });
//        }
//        Thread.sleep(2000);
//        for (int i = 0; i < 5; i++) {
//            executorService.submit(() -> {
//                lock1.readLock();
//            });
//        }

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                lock1.writeValue("xyz", 0);
            });
        }
        executorService.shutdown();

    }
}

class ReentrantLockTypes {
    private ReentrantLock lock = new ReentrantLock();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private int count;

    public void increment() {
        lock.lock();
        try {
            System.out.println("got the lock");
            count++;
            System.out.println("value incremented to: " + count);
        } finally {
            System.out.println("unlocked");
            lock.unlock();
        }
    }

    public void readLock() {
        readWriteLock.readLock().lock();
        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " reading the count");
            System.out.println(count);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Thread " + Thread.currentThread().getName() + " done reading");
            readWriteLock.readLock().unlock();
        }
    }

    private int logValue = 0;

    // Simulate processing work using a dummy computation loop.
    private void simulateWork() {
        long sum = 0;
        for (int i = 0; i < 500000; i++) {
            sum += i;
        }
        // (The computed sum is discarded; its purpose is solely to consume CPU time.)
    }

    // Write operation: exclusively updates the shared logValue.
    public void writeValue(String taskName, int newValue) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(taskName + " (write): Acquired write lock.");
            simulateWork();
            logValue = newValue;
            System.out.println(taskName + " (write): Updated logValue to " + logValue);
        } finally {
            System.out.println(taskName + " (write): Released write lock.");
            readWriteLock.writeLock().unlock();
        }
    }
}