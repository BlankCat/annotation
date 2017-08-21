package Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * Created by has on 2017/7/6.
 */
public class ClassUtil {
    /**
     * 获取成员函数信息
     * @param obj
     */
    public static void printClassMethodMessage(Object obj){
        //获取类的信息  获取类的类类型
        Class c = obj.getClass();
        //获取类的名称
        System.out.println("类的名称是：" + c.getName());
        /**
         * Methods类，方法对象
         * 一个成员方法就是一个Method对象
         * getMethods()方法是获取所有public的函数，包括父类继承而来
         * getDeclaredMethods()获取的是所有该类自己声明的方法，不问访问权限
         */
//    Method [] ms = c.getMethods();
        Method [] ms = c.getDeclaredMethods();
        for (Method m : ms) {
            //得到方法返回值的类类型
            Class returnType = m.getReturnType();
            System.out.print(returnType.getName() + " ");
            //得到方法名称
            System.out.print(m.getName() + "(");
            //获取参数类型(得到的是参数列表的类型的类类型)
            Class[] paramTypes = m.getParameterTypes();
            for (Class paramType : paramTypes) {
                System.out.print(paramType.getName()+",");
            }
            System.out.println(")");
        }
    }

    /**
     * 获取成员变量信息
     * @param obj
     */
    public static void printClassFieldMessage(Object obj) {
        Class c = obj.getClass();
        /**
         * 成员变量也是对象
         * Field类封装了关于成员变量的操作
         * getFields()获取的是所有的public的成员变量信息
         * getDeclaredFields()获取的是该类自己声明的成员变量信息
         */
//    Field [] fs = c.getFields();
        Field[] fs = c.getDeclaredFields();
        for (Field f : fs) {
            //得到成员变量的类型的类类型
            Class fieldType = f.getType();
            String typeName = fieldType.getName();
            //得到成员变量的名称
            String fieldName = f.getName();
            System.out.println(typeName+" "+fieldName);
        }
    }

    /**
     * 获取构造函数信息
     * @param obj
     */
    public static void printConMessage(Object obj){
        Class c = obj.getClass();
        /**
         * 构造函数也是对象
         * java.lang.Constructor封装了构造函数的信息
         * getConstructors()获取所有的public构造函数
         * getDeclaredConstructors()获取所有的构造函数
         */
//    Constructor[] cs = c.getConstructors();
        Constructor[] cs = c.getDeclaredConstructors();
        for (Constructor constructor : cs) {
            System.out.print(constructor.getName()+"(");
            //获取构造函数的参数列表,得到的是参数列表的类类型
            Class[] paramTypes = constructor.getParameterTypes();
            for (Class paramType : paramTypes) {
                System.out.print(paramType.getName()+",");
            }
            System.out.println(")");
        }
    }
}
