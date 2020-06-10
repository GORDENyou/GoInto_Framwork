package p.gordenyou.gointoframwork.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //定义为变量注解
@Retention(RetentionPolicy.RUNTIME) // 我们如果使用注解，需要在运行时都可以取到。
public @interface InjectIntent {
    String Value() default "";
}
