package p.gordenyou.lib.dynamic_proxy;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import p.gordenyou.lib.dynamic_proxy.agent.SellerAgent;
import p.gordenyou.lib.dynamic_proxy.bussiness.Delivery;
import p.gordenyou.lib.dynamic_proxy.bussiness.Sell;
import p.gordenyou.lib.dynamic_proxy.staff.Allround;
import p.gordenyou.lib.dynamic_proxy.staff.Seller;
import sun.misc.ProxyGenerator;

public class Test {
    public static void main(String[] args) throws Exception {
        runProxy();

//        runAgent();

        runProxys(); // 代理多个接口
    }

    private static void runProxys() throws Exception {
        final Allround allround = new Allround();

        Object object = Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{Sell.class, Delivery.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object object, Method method, Object[] args) throws Throwable {
                return method.invoke(allround, args);
            }
        });

        Sell proxy = (Sell) object;
        proxy.sell();

        Delivery proxy2 = (Delivery) object; // 动态代理是面向接口的。
        proxy2.delivery();

        proxy(allround);
    }

    /**
     * 错误示范， 实际上 SellerAgent 是静态代理的使用。
     */
    private static void runAgent() {
        Sell seller = new Seller();
        final SellerAgent sellerAgent = new SellerAgent(seller);

        Object object = Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{Sell.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object object, Method method, Object[] args) throws Throwable {
                return method.invoke(sellerAgent, args);
            }
        });

        //实际上这里会报错：com.sun.proxy.$Proxy0 cannot be cast to p.gordenyou.lib.dynamic_proxy.agent.SellerAgent
        SellerAgent proxyAgent = (SellerAgent) object;
        proxyAgent.sell();
    }

    /**
     * 动态代理
     */
    private static void runProxy() throws Exception {
        final Seller seller = new Seller();

        Object object = Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{Sell.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object object, Method method, Object[] args) throws Throwable {
                return method.invoke(seller, args);// 返回方法的回调执行
            }
        });

        Sell sell = (Sell) object;
        sell.sell();

        proxy(seller);
    }

    /**
     * 用于生成代理类的代码。实际上这个class是在我们动态代理运行时生成的，我们平常看不到
     * @param object
     * @throws Exception
     */
    private static void proxy(Object object) throws Exception {
        String name = object.getClass().getName() + "$Proxy0";
        //生成代理指定接口的Class数据
        byte[] bytes = ProxyGenerator.generateProxyClass(name, object.getClass().getInterfaces());
        FileOutputStream fos = new FileOutputStream("lib/" + name + ".class");
        fos.write(bytes);
        fos.close();
    }
}
