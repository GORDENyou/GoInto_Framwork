package p.gordenyou.gointoframwork.annotation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;

import java.lang.reflect.Field;
import java.util.Arrays;

public class InjectIntentUtils {
    public static void injectIntent(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();

        Bundle extras = activity.getIntent().getExtras();
        if (extras == null) { //　java 中少不了判空。
            return;
        }

        Field[] fields = cls.getDeclaredFields(); // 我们获取其中的所有成员变量。

        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectIntent.class)) {  // 判断是否被自定义注解修饰
                InjectIntent annotation = field.getAnnotation(InjectIntent.class);

                String intentName = annotation.Value().isEmpty() ? field.getName() : annotation.Value();

                // todo 这里需要通用化我们的注解，所以我们需要所有的类型的值都可以获取
                // 首先需要判断是否存在这个键（key）
                if (extras.containsKey(intentName)) {
                    Object object = extras.get(intentName);

                    Class<?> componentType = field.getType().getComponentType();
                    // todo Parcelable数组类型不能直接设置，其他的都可以.
                    if (field.getType().isArray() && Parcel.class.isAssignableFrom(componentType)) {

                        Object[] objs = (Object[]) object;
                        //创建对应类型的数组并由objs拷贝

                        object = Arrays.copyOf(objs, objs.length, (Class<? extends Object[]>) field.getType());
                    }

                    field.setAccessible(true);
                    try {
                        field.set(activity, object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
