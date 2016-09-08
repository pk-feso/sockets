package ru.sbt.net.home;

import ru.sbt.net.Calculator;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException, InterruptedException {
        final Calculator calculator = new NetClientFactory("localhost", 5000).createClient(Calculator.class);
        for (int i = 0; i < 2000; i++) {
            executorService.submit(() -> {
                System.out.println(calculator.calculate((int) (Math.random() * 20), (int) (Math.random() * 100)));
            });
        }
        executorService.shutdown();

    }





}
