package ru.sbt.net.home;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ClientInvocationHandler implements InvocationHandler {
    private final String host;
    private final int port;

    public ClientInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {

        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        try(Socket socket = new Socket(host, port)) {

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            writeParams(method, args, objectOutputStream);
            objectOutputStream.flush();

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = getObject(objectInputStream);

            return returnObject(object);
        }

    }

    private Object getObject(ObjectInputStream objectInputStream) throws IOException {
        Object object;
        try {
            object = objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found", e);
        }
        return object;
    }

    private Object returnObject(Object object) throws Exception {
        if (object instanceof RuntimeException) {
            Exception exception = (Exception) object;
            while (exception.getCause() != null) {
                exception = (Exception) exception.getCause();
            }
            throw exception.getClass().getConstructor(String.class).newInstance(exception.getMessage());
        } else {
            return object;
        }
    }

    private void writeParams(Method method, Object[] args, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(method.getName());
        objectOutputStream.writeObject(method.getParameterTypes());
        objectOutputStream.writeObject(args);
    }

}