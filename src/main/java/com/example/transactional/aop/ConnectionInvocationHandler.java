package com.example.transactional.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

public class ConnectionInvocationHandler implements InvocationHandler {

    private final Connection connection;

    public ConnectionInvocationHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().contains("commit") || method.getName().contains("close")) {
            System.out.println(method.getName() + " is invoked");
        }

        return method.invoke(connection, args);

    }
}
