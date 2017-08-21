package Utils;

/**
 * Created by has on 2017/7/6.
 */
public class Apple {
    private String appleName;

    private String appleColor;

    private String appleProvider;



    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }
    public String getAppleColor() {
        return appleColor;
    }


    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }
    public String getAppleName() {
        return appleName;
    }

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }

    public void displayName(){
        System.out.println("水果的名字是：苹果");
    }
}
