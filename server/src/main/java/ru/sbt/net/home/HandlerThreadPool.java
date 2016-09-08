package ru.sbt.net.home;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandlerThreadPool {

    private final ExecutorService executorService;
    private final HandlerServer handler;

    public HandlerThreadPool() {
        this.handler = new HandlerServer();
        executorService = Executors.newFixedThreadPool(10);
    }


    public synchronized void execute(Socket accept, Object impl) {
        executorService.submit(new RunnableSocket(accept, impl));
    }


    private class RunnableSocket implements Runnable {

        private final Socket socket;
        private final Object impl;

        private RunnableSocket(Socket socket, Object impl) {
            this.socket = socket;
            this.impl = impl;
        }

        @Override
        public void run() {
            try {

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Method method = handler.getMethod(objectInputStream, impl);
                Object[] object = handler.read(objectInputStream);

                Object o = handler.getObject(impl, method, object);

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                handler.sendResult(o, objectOutputStream);
            } catch (IOException e) {
                throw new RuntimeException("Error during work with stream", e);
            }


        }
    }
}
