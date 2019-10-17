package proxy.dynamicproxy;

import proxy.dao.BMW;
import proxy.dao.ICarDao;
import proxy.dao.IUserDao;
import proxy.dao.UserDao;

/**
 * 静态代理测试类
 */
public class TestApp {
    public static void main(String[] args) {
//        IUserDao target = new UserDao();
//        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
//        System.out.println(proxy.getClass());
//        proxy.save();

        ICarDao carTarget = new BMW();
        ICarDao carProxy = (ICarDao) new ProxyFactory(carTarget).getProxyInstance();
        System.out.println(carProxy.getClass());
        carProxy.run();
        carProxy.getColor("BLACK");
    }

}
