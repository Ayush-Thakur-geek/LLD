package multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {
    public final Queue<Integer> queue;
    private final int capacity;
    public ProducerConsumer(Queue<Integer> queue) {
        this.queue = queue;
        this.capacity = 5;
    }
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                if (this.queue.size() == this.capacity) {
                    System.out.println("Queue is full");
                    wait();
                }
                System.out.println("producer produced value: " + value);
                this.queue.add(value++);
                notifyAll();
            }
            Thread.sleep(10);
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (this.queue.size() == 0) {
                    System.out.println("Queue is empty");
                    wait();
                }
                System.out.println("consumer consumed value: " + this.queue.poll());
                notifyAll();
            }
            Thread.sleep(1000);
        }
    }
}

class Main {
    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer(new LinkedList<>());
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            try {
                producerConsumer.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                producerConsumer.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }
}
