package multithreading;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class ExecutorServiceEx {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        Callable<String> task1 = () -> {
//            Thread.sleep(5000);
//            return "hello";
//        };
//        Callable<String> task2 = () -> {
//            Thread.sleep(1000);
//            return "world";
//        };
//        List<Future<String>> futures = executorService.invokeAll(List.of(task1, task2));
//        for (Future<String> future : futures) {
//            if (future.isDone()) System.out.println(future.get());
//        }

        Supplier<String> supplier = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello";
        };

        Supplier<String> supplier1 = () -> {
            return "world";
        };
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(supplier, executorService).exceptionally(ex -> {
            System.out.println("Exception caught in supplyAsync " + ex.getMessage());
            return ex.getMessage();
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(supplier1, executorService);

        for (int i = 0; i < 2; i++) {
            if (i == 0) f1.thenAccept(System.out::println);
            else f2.thenAccept(System.out::println);
        }

        executorService.shutdown();
    }
}
