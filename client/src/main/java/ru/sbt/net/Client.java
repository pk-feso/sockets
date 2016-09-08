package ru.sbt.net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        while (true) {
            Calculator calculator = new NetClientFactory("localhost", 5000).createClient(Calculator.class);
            System.out.println(calculator.calculate(10, 577));
            System.out.println(calculator.multiply(30, 34));
        }
    }
}
