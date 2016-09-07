package ru.sbt.net;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ClientInvocationHandler implements InvocationHandler {
    private final String host;
    private final int port;

    public ClientInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}