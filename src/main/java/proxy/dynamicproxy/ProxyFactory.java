package proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理实现
 * 代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用动态代理

 */
public class ProxyFactory {
    private Object target;
    ProxyFactory(Object target){
        this.target = target;

    }
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before invoke");
                Object obj = method.invoke(target,args);
                System.out.println("after invoke");
                return obj;
            }
        });
    }
}
