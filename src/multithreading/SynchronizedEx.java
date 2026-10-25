package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedEx {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
//        ClassA a = new ClassA();
//        for (int i = 0; i < 3; i++) {
//
//            executor.submit(() -> {
//                try {
//                    a.methodA();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        }
//
//        for (int i = 0; i < 3; i++) {
//            executor.submit(() -> {
//                ClassA b = new ClassA();
//                try {
//                    b.methodB();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        }
//
//        for (int i = 0; i < 3; i++) {
//            executor.submit(() -> {
//                ClassA b = new ClassA();
//                try {
//                    ClassA.methodC();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        }

        ClassA a1 = new ClassA();
        for (int i = 0; i < 3; i++) {
            executor.submit(() -> {
                try {
                    a1.methodD();
                    System.out.println(a1.count);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        System.out.println(a1.count);


        executor.shutdown();
    }
}

class ClassA {
    int count = 0;
    private final Object lock = new Object();
    public synchronized void methodA() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("methodA");
    }
    public synchronized void methodB() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("methodB");

    }
    public static synchronized void methodC() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("methodC");
    }
    public void methodD() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("methodD");
        synchronized (lock) {
            System.out.println("synchronized block");
            count++;
        }
    }
}