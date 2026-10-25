package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class BarrierSynchronization {
    public static void main(String[] args) {
        BarrierSynchronizationImpl barrierSynchronization = new BarrierSynchronizationImpl(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " Entering phase 1");
                    Thread.sleep((long)(Math.random() * 1000));
                    System.out.println(Thread.currentThread().getName() + " currently at barrier");
                    barrierSynchronization.await();
                    System.out.println(Thread.currentThread().getName() + " Entering phase 2");
                    Thread.sleep((long)(Math.random() * 1000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + " was interrupted");
                }
            });
        }
        executorService.shutdown();
    }
}

class BarrierSynchronizationImpl {
    Semaphore mutex = new Semaphore(1);
    Semaphore barrier = new Semaphore(0);
    int count;
    int party;
    BarrierSynchronizationImpl(int party) {
        this.party = party;
        this.count = party;
    }

    public void await() throws InterruptedException {
        mutex.acquire();
        count--;
        if (count == 0) {
            barrier.release(party - 1);
            count = party;
            mutex.release();
        }
        else {
            mutex.release();
            barrier.acquire();
        }
    }
}
