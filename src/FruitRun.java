import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by has on 2017/7/6.
 */
public class FruitRun {
    /**
     * @param args
     */
    public static void main(String[] args) {
//        Apple a=new Apple();
//        Class<?> clz = a.getClass();
//        Field[] fields = clz.getDeclaredFields();
//
//        a.getAppleProvider();
//        System.out.println(a.getAppleProvider());
        FruitInfoUtil.getFruitInfo(Apple.class);


        List<LinkedHashMap<String,Object>> list= FieldHelper.initAnnoFieldDic(Apple.class);
        System.out.println(list.get(0));
    }
}
