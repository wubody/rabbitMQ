package com.example.demo.entity;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: WJQ
 * @date 2020/7/15 16:50
 */
public class Test4 {
    /**
     *  新增一个代理类，代理 BeProxyTarget 类，在执行BeProxyTarget.test()方法前，执行代理类的代码,代理方法输出：System.out.println("proxy bis！");
     *  预期效果：
     *  proxy bis！
     *  start bis！
     */
    public static void main(String[] param){
        BeProxyTarget beProxyTarget = new BeProxyTarget();
        BeProxyTargetBody beProxyTargetBody = new BeProxyTargetBody(beProxyTarget);
        BeProxyTarget beProxyTarget1 = (BeProxyTarget)beProxyTargetBody.getProxyInstance();
        beProxyTarget1.test();

    }
}


class  BeProxyTarget{
    public void test(){
        System.out.println("start bis！");
    }
}

/**
 * 代理类  Cglib动态代理
 */
class BeProxyTargetBody implements MethodInterceptor {
    private Object target;
    public BeProxyTargetBody(Object target){
        this.target = target;
    }

    public Object getProxyInstance(){
        Enhancer en = new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(this);
        return en.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] arg2, MethodProxy proxy) throws Throwable {
        System.out.println("proxy bis！");
        Object returnValue = method.invoke(target, arg2);
        return returnValue;
    }
 }
