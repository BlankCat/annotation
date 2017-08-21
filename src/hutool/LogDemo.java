package hutool;

import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.lang.Console;
import com.xiaoleilu.hutool.lang.Dict;
import com.xiaoleilu.hutool.util.NetUtil;

import java.util.Date;

/**
 * Created by has on 2017/7/7.
 */
public class LogDemo {
    public static void main(String[] args) {
//        String result1= HttpUtil.get("https://www.baidu.com");
//        System.out.println(result1);

//        //是否为有效的端口
//        boolean result= NetUtil.isValidPort(6379);
//        System.out.println(result);
//
//        String[] a = {"abc", "bcd", "def"};
//        Console.log(a);
        Dict dict = Dict.create()
                .set("key1", 1)//int
                .set("key2", 1000L)//long
                .set("key3", DateTime.now());//Date
        Long v2 = dict.getLong("key2");//1000
        Date v3= dict.getDate("key3");
        System.out.println(v3);

    }
}
