import user.User;
import util.Utils;

public class Main {
    public static void main(String[] args) {
        System.out.println("****租好车，选腾飞****");
        System.out.println("**欢迎光临腾飞租车行**");
        System.out.println("您可以选择以下操作方式：");
             User c= Utils.menu();
             if(c!=null)
             c.menu();
             else{
                 System.out.println("");
             }

    }
}
