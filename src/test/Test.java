package test;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by has on 2017/7/6.
 */
public class Test {
    @A(aId = "a1", aName = "a1")
    @B(bId = "b1", bName = "b1")
    private String id;

    @A(aId = "a2", aName = "a2")
    @B(bId = "b2", bName = "b2")
    private String name;

    private static void test() {
        Field[] declaredFields = Test.class.getDeclaredFields();
        for (Field field : declaredFields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }
        }
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        test();
    }
}
