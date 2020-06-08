package p.gordenyou.gointoframwork.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //定义为变量注解
@Retention(RetentionPolicy.SOURCE) // 在源代码时使用
public @interface InjectIntent {
    String Value();
}
