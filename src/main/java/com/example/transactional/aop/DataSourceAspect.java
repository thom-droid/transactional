package com.example.transactional.aop;

import com.mysql.cj.jdbc.ConnectionImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.sql.Connection;

@Component
@Aspect
public class DataSourceAspect {

    @Around("target(javax.sql.DataSource)")
    public Object logConnection(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String methodName = proceedingJoinPoint.getSignature().getName();
        System.out.println(methodName + " is now invoked");

        Object result = proceedingJoinPoint.proceed(); // connectionImpl

        if (result instanceof Connection) {
            ConnectionInvocationHandler connectionInvocationHandler = new ConnectionInvocationHandler((Connection) result);
            return Proxy.newProxyInstance(ConnectionImpl.class.getClassLoader(), new Class[]{Connection.class}, connectionInvocationHandler);
        } else {
            return result;
        }

    }

}
