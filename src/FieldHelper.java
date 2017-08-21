import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by has on 2017/7/6.
 */
public class FieldHelper {
    /**
     * 根据实体类名获取字段名称和中文名称
     * @param
     * @return List<Map<String,Object>>
     */
    public static List<LinkedHashMap<String,Object>> initAnnoFieldDic(@SuppressWarnings("rawtypes") Class clzz){
        //用于存储字段和中文值的集合
        List<LinkedHashMap<String,Object>> fieldList = new ArrayList<>();
        //用于存储实体类字段(key)和中文名(value)
        LinkedHashMap<String,Object> valueMap = new LinkedHashMap<>();
        //获取对象中所有的Field
        Field[] fields = clzz.getDeclaredFields();
        //循环实体类字段集合,获取标注@ColumnConfig的字段
        for (Field field : fields) {
            if(field.isAnnotationPresent(ColumnConfig.class)){
                //获取字段名
                String fieldNames = clzz.getSimpleName()+"."+field.getName();
                //获取字段注解
                ColumnConfig columnConfig = field.getAnnotation(ColumnConfig.class);
                //判断是否已经获取过该code的字典数据 避免重复获取
                if(valueMap.get(columnConfig.description())==null){
                    valueMap.put(fieldNames, columnConfig.description());
                }
            }
            else if(field.isAnnotationPresent(FruitName.class)){
                String fieldNames = clzz.getSimpleName()+"."+field.getName();
                FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                //判断是否已经获取过该code的字典数据 避免重复获取
                if(valueMap.get(fruitName.value())==null){
                    valueMap.put(fieldNames, fruitName.value());
                }

            }
        }
        fieldList.add(valueMap);//将LinkedHashMap放入List集合中
        return fieldList;
    }
}
