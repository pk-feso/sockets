package ru.sbt.net;

import java.lang.reflect.Method;

public class ServerRegistrator {
    public static void listen(String host, int port, Object impl) {
        String methodName = null;
        Object[] args = null;

    }

    public static void main(String[] args) {
        ServerRegistrator.listen("localhost", 5000, new CalculatorImpl());
    }
}