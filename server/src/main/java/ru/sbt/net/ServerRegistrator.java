package ru.sbt.net;

public class ServerRegistrator {
    public static void listen(String host, int port, Object impl) {

    }

    public static void main(String[] args) {
        ServerRegistrator.listen("localhost", 5000, new CalculatorImpl());
    }
}