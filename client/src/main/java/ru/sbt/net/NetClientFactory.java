package ru.sbt.net;

import java.util.List;

public class NetClientFactory {
    private final String host;
    private final int port;

    public NetClientFactory(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public <T> T createClient(Class<T> interfaceClass) {
        return null;
    }

    public static void main(String[] args) {
        NetClientFactory factory = new NetClientFactory("localhost", 5000);
        Calculator client = factory.createClient(Calculator.class);
    }
}
