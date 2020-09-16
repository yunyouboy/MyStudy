package cn.readsense.activityhookdemo.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class MyClass {

    public static void main(String[] args) throws Exception {
        //静态代理
        //Massage message = new Lucy();
        //Agent agent = new Agent(message);
        //agent.massage();

        //动态代理
        final Alvin alvin = new Alvin();

        Object o = Proxy.newProxyInstance(//ProxyClassFactory.generateProxy到native层生成代理类（内部反射产生代理类，回调InvocationHandler）
                MyClass.class.getClassLoader(),
                new Class[]{Massage.class,Wash.class},
                new InvocationHandler() {//回调
                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                        return method.invoke(alvin, objects);
                    }
                });
        Massage massage = (Massage) o;
        massage.massage();

        Wash wash = (Wash) o;
        wash.wash();
    }
}
