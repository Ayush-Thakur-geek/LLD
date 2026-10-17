package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolEx {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            cachedThreadPool.execute(() -> {
                simulateWebRequest();
                System.out.println(Thread.currentThread().getName() + " completed I/O");
            });
        }
        cachedThreadPool.shutdown();
    }

    public static void simulateWebRequest() {
        try {
            System.out.println(Thread.currentThread().getName() + " simulating");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
