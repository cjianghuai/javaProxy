package proxy.cblib;

import proxy.dao.Animal;

/**
 * gblib 动态代理测试类
 */
public class TestApp {
    public static void main(String[] args) {
        Animal pig = new Animal();
        Animal proxy = (Animal) new CglibProxyFactory(pig).getProxyInstacne();
        proxy.getName("pig");
    }
}
