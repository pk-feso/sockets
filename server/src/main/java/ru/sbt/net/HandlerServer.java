package ru.sbt.net;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class HandlerServer {



    public Method getMethod(ObjectInputStream objectInputStream, Object impl)  {
        try {
            String name = (String) objectInputStream.readObject();
            Class<?>[] classes = (Class<?>[]) objectInputStream.readObject();
            return impl.getClass().getMethod(name, classes);
        } catch (NoSuchMethodException | ClassNotFoundException | IOException e) {
            throw new RuntimeException("Error during invoke method", e);
        }
    }

    Object processObject(Object impl, Method method, Object[] object) {
        Object o;
        try {
            o = method.invoke(impl, object);
        } catch (Exception e) {
            o = new RuntimeException(e);
        }
        return o;
    }

    Object[] getArgs(Socket accept) {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(accept.getInputStream())) {
            return (Object[]) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
           throw new RuntimeException("Error during get arguments", e);
        }
    }

    public void sendResult(Object o, ObjectOutputStream outputStream) {
        try {
            outputStream.writeObject(o);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Error during send result", e);
        }



    }




    public Object[] read(ObjectInputStream objectInputStream) {
        try {
            return (Object[]) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error during get arguments", e);
        }
    }

    Object getObject(Object impl, Method method, Object[] object) {
        Object o;
        try {
            o = method.invoke(impl, object);
        } catch (Exception e) {
            o = new RuntimeException(e);
        }
        return o;
    }
}
