package p.gordenyou.gointoframwork.annotation;

import android.app.Activity;

import java.lang.reflect.Field;

public class InjectIntentUtils {
    public static void injectIntent(Activity activity) {
        Class cls = activity.getClass();

        Field[] fields = cls.getDeclaredFields(); // 我们获取其中的所有成员变量。

        for (Field field : fields) {
            if(field.isAnnotationPresent(InjectIntent.class)){
                String intentName = field.getAnnotation(InjectIntent.class).Value();
                field.setAccessible(true);
                try {
                    field.set(activity, activity.getIntent().getStringExtra(intentName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
