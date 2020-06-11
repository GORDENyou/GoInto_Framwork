package p.gordenyou.gointoframwork.into_retrofit.myretrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import okhttp3.HttpUrl;
import p.gordenyou.gointoframwork.into_retrofit.annotation.Field;
import p.gordenyou.gointoframwork.into_retrofit.annotation.GET;
import p.gordenyou.gointoframwork.into_retrofit.annotation.POST;
import p.gordenyou.gointoframwork.into_retrofit.annotation.Query;

public class ServiceMethod {
    private final String relativeUrl;
    private final HttpUrl baseUrl;
    private final String httpMethod;
    private final boolean hasBody;
    private final ParameterHandler[] parameterHandler;

    public ServiceMethod(Builder builder) {
        baseUrl = builder.myRetrofit.baseUrl;
        // todo 这里的 callFactory 是用来干什么的？
//        callFactory = builder.myRetrofit.callFactory;

        httpMethod = builder.httpMethod;
        relativeUrl = builder.relativeUrl;
        hasBody = builder.hasBody;
        parameterHandler = builder.parameterHandler;

        //如果是有请求体,创建一个okhttp的请求体对象
        if (hasBody) {
            // todo formBuild 做什么?
//            formBuild = new FormBody.Builder();
        }
    }

    public void addQueryParameter(String key, String value) {
        //todo
    }

    public void addFiledParameter(String key, String value) {
        //todo
    }

    public Object invoke(Object[] args) {
        //todo
        return null;
    }

    public static class Builder {

        private final Annotation[] methodAnnotations;

        // 有多个参数， 而且一个参数有多个注解。所以这里是二维数组。
        private final Annotation[][] parameterAnnotations;
        private final MyRetrofit myRetrofit;

        private String httpMethod;
        private String relativeUrl;
        private boolean hasBody;
        private ParameterHandler[] parameterHandler;

        public Builder(MyRetrofit myRetrofit, Method method) {
            // 这里需要持有 MyRetrofit 的一个对象，方便我们获取他的 Url 。
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

                // 处理每一个注解
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
