package ru.sbt.net;

import java.lang.reflect.Method;

/*
* Client:
* createProxy
*     send method name + args to server
 *    receive return value from server and return it
* */

/*
* server:
* listen host + port
* read methodName + args
* invoke method via reflection
* send return value to client
*
* */

public class ServerRegistrator {
    public static void listen(String host, int port, Object impl) {
        String methodName = null;
        Object[] args = null;

    }

    public static void main(String[] args) {
        ServerRegistrator.listen("localhost", 5000, new CalculatorImpl());
    }
}