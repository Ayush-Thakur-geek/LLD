package multithreading;

public class WaitExample {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread t1 = new Thread(() -> {
            sharedResource.waitEx();
        }, "billu");
        Thread t2 = new Thread(() -> {
            sharedResource.waitEx();
        }, "bablu");
        Thread t3 = new Thread(() -> {
            sharedResource.waitEx();
        }, "ramu");
        Thread t4 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " sleeping...");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " awake");
                sharedResource.notifyEx();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "champu");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class SharedResource {
    static int calls = 0;
    public synchronized void waitEx() {
        try {
            calls++;
            if (calls == 1) {
                System.out.println(Thread.currentThread().getName() + " waiting...");
                wait();
                System.out.println(Thread.currentThread().getName() + " resumed");
            } else {
                System.out.println(Thread.currentThread().getName() + " accessing the method");
            }
        } catch (InterruptedException e) {}

    }

    public synchronized void notifyEx() {
        System.out.println(Thread.currentThread().getName() + " notifying");
        notify();
    }
}
