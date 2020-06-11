package p.gordenyou.gointoframwork.into_retrofit.myretrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.HttpUrl;

/**
 * 我们完成这个类，我们需要做什么？
 * 1.首先使用构建者模式，方便用户构建自己的调用接口
 * 2.分别实现我们在方法上的注解、形参上的注解。我们参考 retrofit 调用函数的步骤，一步步实现！
 */
public class MyRetrofit {
    // 我们为了防止其它地方再次修改，所以使用 final 修饰
    final HttpUrl baseUrl;

    // 三、我们定义了一个 ConcurrentHashMap 缓存我们已经解析过的方法。
    private final Map<Method, ServiceMethod> serviceMethodCache = new ConcurrentHashMap<>();

    //默认只能在我们自己的包中调用，MyRetrofitActivity 中已测试，不可以直接调用。
    MyRetrofit(HttpUrl baseUrl) {
        this.baseUrl = baseUrl;
    }

    // ################ 中文标记：retrofitApi = retrofit.create(RetrofitApi.class);

    // 一、实现 crete() 方法，这个方法返回的是动态代理的实现类
    public <T> T create(Class<T> service){
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 当我们回调到接口定义的函数时，我们需要处理注解所代表的含义。
                // 我们需要用使用函数上的注解定义我们请求的方式：Get 还是 Post请求。
                // 我们需要用参数上的注解获取我们的参数。

                // 我目前没有想法，看看老师的实现：
                // 二、我们统一将方法交给 loadServiceMethod() 方法处理
                ServiceMethod serviceMethod = loadServiceMethod(method);
                //args:
                return serviceMethod.invoke(args);
            }
        });
    }

    private ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod serviceMethod = serviceMethodCache.get(method);
        if(method != null) return serviceMethod;
        // 类似于单例模式的 DLC 方式。第一次检验是减少再次创建的时间消耗，第二次检验是防止多进程产生多次创建。
        synchronized (serviceMethodCache){
            serviceMethod = serviceMethodCache.get(method);
            if(serviceMethod == null){
                serviceMethod = new ServiceMethod.Builder(this, method).build();
                serviceMethodCache.put(method, serviceMethod);
            }
        }
        return null;
    }


    // ################ 数字标记：Retrofit retrofit = new Retrofit.Builder().baseUrl("https://restapi.amap.com").build();

    // 1.首先调用了 Builder(), 这个应该是 Builder 构造函数
    public final static class Builder {
        private HttpUrl baseUrl;


        // 2.接下来是 Builder#baseUrl() 方法
        public Builder baseUrl(String baseUrl){
            Objects.requireNonNull(baseUrl, "baseUrl == null");
            this.baseUrl = HttpUrl.get(baseUrl);
            return this;
        }

        // 3.接下来是 Builder#build() 方法， 它最终返回的是我们的 MyRetrofit
        public MyRetrofit build(){
            return new MyRetrofit(baseUrl);
        }
    }
}
