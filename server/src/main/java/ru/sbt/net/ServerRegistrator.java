package ru.sbt.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRegistrator {


    public static void listen(String host, int port, Object impl) throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);
        HandlerServer handler = new HandlerServer();
        while (true) {
            Socket accept = serverSocket.accept();

            ObjectInputStream objectInputStream = new ObjectInputStream(accept.getInputStream());
            Method method = handler.getMethod(objectInputStream, impl);
            Object[] object = handler.read(objectInputStream);
            Object o = handler.getObject(impl, method, object);


            ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
            handler.sendResult(o, objectOutputStream);

            objectInputStream.close();

        }


    }


    public static void main(String[] args) throws IOException {
        ServerRegistrator.listen("localhost", 5000, new CalculatorImpl());
    }


}