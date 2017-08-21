/**
 * @author  zjf
 * @date 创建时间：2017年7月13日 
 * @Description 
 */
public class A {

 
  public static void main(String[] args) {
    String orgAllbelongs="|1|-10|45|145|";
    System.out.println(orgAllbelongs);
    String[] split = orgAllbelongs.split("\\|");
    System.out.println("-----------dao---------split.length="+split.length+"---");
    for (int i = 1; i < split.length; i++) {
        System.out.println("--------------------split[split.length - i]="+split[split.length - i]);
        int casOrgId = Integer.parseInt(split[split.length - i]);// 逆序
        if (1 == casOrgId || 0 > casOrgId) {
            continue;
        }
       
    }
  }
}
