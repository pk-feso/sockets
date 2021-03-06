package ru.sbt.net.home;

import ru.sbt.net.Calculator;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException, InterruptedException {

        for (int i = 0; i < 2000; i++) {
            executorService.submit(() -> {
                Calculator calculator = new NetClientFactory("localhost", 5000).createClient(Calculator.class);
                System.out.println(calculator.calculate(20, 100));
            });
        }
        executorService.shutdown();

    }


}
