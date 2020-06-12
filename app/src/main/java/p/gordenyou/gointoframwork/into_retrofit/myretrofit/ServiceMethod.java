package p.gordenyou.gointoframwork.into_retrofit.myretrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import p.gordenyou.gointoframwork.into_retrofit.annotation.Field;
import p.gordenyou.gointoframwork.into_retrofit.annotation.GET;
import p.gordenyou.gointoframwork.into_retrofit.annotation.POST;
import p.gordenyou.gointoframwork.into_retrofit.annotation.Query;

public class ServiceMethod {
    private final HttpUrl baseUrl;
    private final String httpMethod;
    private final String relativeUrl;
    private final boolean hasBody;
    private final ParameterHandler[] parameterHandler;

    HttpUrl.Builder urlBuilder;
    FormBody.Builder bodyBuilder;
    private Call.Factory callFactory;

    public ServiceMethod(Builder builder) {
        baseUrl = builder.myRetrofit.baseUrl;

        httpMethod = builder.httpMethod;
        relativeUrl = builder.relativeUrl;
        hasBody = builder.hasBody;
        parameterHandler = builder.parameterHandler;
        callFactory = builder.myRetrofit.callFactory;

        //如果是有请求体,创建一个okhttp的请求体对象
        if (hasBody) {
            //  bodyBuilder 做什么? 构建 POST 请求的请求体
            bodyBuilder = new FormBody.Builder();
        }
    }

    // 将 key 和 value 拼接到 url 中。
    public void addQueryParameter(String key, String value) {
        if (urlBuilder == null) {
            urlBuilder = baseUrl.newBuilder(relativeUrl);
        }
        urlBuilder.addQueryParameter(key, value);
    }

    // 将 key 和 value 放入请求体
    public void addFiledParameter(String key, String value) {
        bodyBuilder.add(key, value);
    }

    public Object invoke(Object[] args) {
        /*
        处理请求的地址与参数
         */
        for (int i = 0; i < parameterHandler.length; i++) {
            ParameterHandler handler = parameterHandler[i];
            // 我们会调用中的方法最终传递了请求的 value
            handler.apply(this, args[i].toString());
        }

        /*
        接下来拼接最终的 url
         */
        HttpUrl url;
        if(urlBuilder == null){
            urlBuilder = baseUrl.newBuilder(relativeUrl);
        }
        url = urlBuilder.build();

        /*
        构建请求体
         */
        FormBody formBody = null;
        if(bodyBuilder != null){
            formBody = bodyBuilder.build();
        }

        /*
        最终的请求的构建
         */
        Request request = new Request.Builder().url(url).method(httpMethod, formBody).build();
        return callFactory.newCall(request);
    }

    public static class Builder {

        private final Annotation[] methodAnnotations;

        // 有多个参数， 而且一个参数有多个注解。所以这里是二维数组。
        private final Annotation[][] parameterAnnotations;
        private final MyRetrofit myRetrofit;

        private String httpMethod;
        private String relativeUrl; // 请求的具体“方法”
        private boolean hasBody;
        private ParameterHandler[] parameterHandler;

        public Builder(MyRetrofit myRetrofit, Method method) {
            // 这里需要持有 MyRetrofit 的一个对象，方便我们获取他的 baseUrl 。
            this.myRetrofit = myRetrofit;

            // 获取方法上所有的注解
            methodAnnotations = method.getAnnotations();

            // 获取方法参数上所有的注解
            parameterAnnotations = method.getParameterAnnotations();
        }

        public ServiceMethod build() {
            /*
              我们目前只处理方法上 Post 和 Get 注解
             */
            for (Annotation methodAnnotation : methodAnnotations) {
                if (methodAnnotation instanceof POST) {
                    // 记录当前请求方式
                    this.httpMethod = "POST";
                    // 记录请求 URL 的 Path
                    this.relativeUrl = ((POST) methodAnnotation).value();
                    // 记录是否有请求体
                    this.hasBody = true;
                }
                if (methodAnnotation instanceof GET) {
                    this.httpMethod = "GET";
                    this.relativeUrl = ((GET) methodAnnotation).value();
                    this.hasBody = false;
                }
            }


            /*
             * 接下来解析参数上的所有注解
             */
            int parameterLength = parameterAnnotations.length;
            parameterHandler = new ParameterHandler[parameterLength];

            for (int i = 0; i < parameterLength; i++) {
                // 获取一个参数上所有的注解
                Annotation[] annotations = parameterAnnotations[i];

                // 处理每一个注解，可以看出有不同的处理方式。
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Field) {
                        String value = ((Field) annotation).value();
                        // todo 这里还不知道这里的 parameterHandler 有什么作用。
                        parameterHandler[i] = new ParameterHandler.FiledParameterHandler(value);
                    }
                    if (annotation instanceof Query) {
                        String value = ((Query) annotation).value();
                        parameterHandler[i] = new ParameterHandler.QueryParameterHandler(value);
                    }
                }
            }

            return new ServiceMethod(this);
        }
    }
}
