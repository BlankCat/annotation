package test;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Created by has on 2017/7/6.
 */
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface B {
    String bId();
    String bName();
}
