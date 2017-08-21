package annotation;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * Created by has on 2017/7/6.
 */
public class ReflectionDemoTest {
    public static void main(String[] args) {
        //创建类对象,使用泛型修饰避免强转
        Class cla=ReflectionDemo.class;
        //获取全部公共域的方法
        Field[] field=cla.getFields();
        for(Field f:field){
            System.out.println("获取全部公共域的方法:"+f.toString());
        }
        //获取指定的某公共域的方法
        try {
            Field field1=cla.getField("pub_field");
            System.out.println("获取指定的公共域的方法:"+field1.toString());
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //获取全部域的方法(含私有域),Declare
        Field[] field2=cla.getDeclaredFields();
        for(Field f:field2){
            System.out.println("获取全部域的方法(含私有域):"+f.toString());
        }
        //获取指定域的方法(含私有域)
        try {
            Field field3=cla.getDeclaredField("pri_field");
            System.out.println("获取指定域的方法(含私有域):"+field3.toString());
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //获取全部公共方法的方法(含父类)
        Method[] method=cla.getMethods();
        for(Method m:method){
            System.out.println("获取全部公共方法的方法:"+m.toString());
        }
        try {
            //获取指定无参方法
            //第二个参数表示参数类型，参数NUll表示无参数方法
            Method method1=cla.getMethod("ReflectMethod", null);
            System.out.println("获取无参公共方法的方法:"+method1.toString());
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //获取该类全部方法的方法(不含父类)
        Method[] method2=cla.getDeclaredMethods();
        for(Method m:method2){
            System.out.println("获取全部方法的方法(含父类):"+m.toString());
        }

        //获取该类指定方法的方法(不含父类)
        //第一个参数为方法名,第二个参数为方法返回类型，NULL则为无参方法
        try {
            Method method3=cla.getDeclaredMethod("ReflectMethod",int.class);
            System.out.println("获取该类指定方法的方法(不含父类):"+method3);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //获取公有构造方法
        //获取无参公有构造方法
        try {
            Constructor cs=cla.getConstructor(null);
            System.out.println("获取无参构造方法:"+cs);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //获取有参公有构造方法
        //如多个参数的构造方法，则构造方法为多个，注意顺序
        try {
            Constructor cs=cla.getConstructor(String.class);
            System.out.println("获取有参构造方法:"+cs);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //获取全部构造方法

        try {
            Constructor[] cs=cla.getDeclaredConstructors();
            for(Constructor c:cs){
                System.out.println("获取全部构造方法:"+c);
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //获取包名
        Package pack=cla.getPackage();
        System.out.println("获取当前包的名称:"+pack);

        //获取注释
        Annotation[] ann=cla.getAnnotations();
        for(Annotation an:ann){
            System.out.println(an.toString());
        }
//获取父类
        Class supercla=cla.getSuperclass();
        System.out.println(supercla);
        //获取内部类
        Class[] innercla=cla.getDeclaredClasses();
        for(Class cl:innercla){
            System.out.println(cl);

        }

    }
}
