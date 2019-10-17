package proxy.cblib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 动态 代理：不需要接口，在内存中生成子类
 */
public class CglibProxyFactory implements MethodInterceptor {
    private Object target;
    public CglibProxyFactory(Object target){
        this.target = target;
    }

    public Object getProxyInstacne(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();

    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before invoke...");
        Object obj = method.invoke(target,args);
        System.out.println("before invoke...");
        return obj;
    }
}
