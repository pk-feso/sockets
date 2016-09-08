package ru.sbt.net.home;

import ru.sbt.net.CalculatorImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRegistrator {

    public void listen(int port, Object impl) throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);
        HandlerThreadPool handlerThreadPool = new HandlerThreadPool();
        while (true) {
            Socket accept = serverSocket.accept();
            handlerThreadPool.execute(accept, impl);
        }
    }



    public static void main(String[] args) throws IOException {
        new ServerRegistrator().listen(5000, new CalculatorImpl());
    }


}