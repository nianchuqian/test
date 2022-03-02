package user;

import factory.Factory;
import util.Utils;
import vehicle.Bus;
import vehicle.Car;
import vehicle.Trunk;
import vehicle.Vehicle;

import java.util.List;

public class Manage extends User implements DefaultManage {
    public Manage(String name, String password) {
        super(name, password);
    }

    public void menu() {
        System.out.println("欢迎回来亲爱的" + getName());
        int x = 0;
        while (true) {
            menu1();
            x = Utils.readInt(7, 1);
            if (x == -1) {
                break;
            }
            if (x == 7) {
                System.out.println("感谢您对本系统的支持，欢迎下次使用。");
                break;
            }
            switch (x){
                case 1:
                    checkVehicle();
                    break;
                case 2:
                    boolean b=addVehicle();
                    if (b==true) System.out.println("添加车辆成功！");
                    else System.out.println("添加车辆失败！");
                    break;
                case 3:
                    boolean b1=modifyVehicle();
                    if (b1==true) System.out.println("修改车辆信息成功！");
                    else System.out.println("修改车辆信息失败！");
                    break;
                case 4:
                    boolean b2=deleteVehicle();
                    if (b2==true) System.out.println("车辆删除成功！");
                    else System.out.println("车辆删除失败！");
                    break;
                case 5:
                    checkTurnover();
                    break;
                case 6:
                    manageCustomer();
                    break;
            }
        }
    }
    private void menu1(){
        System.out.println("请问需要为您提供什么服务呢？");
        System.out.println("1、查看车辆信息");
        System.out.println("2、添加车辆");
        System.out.println("3、修改车辆信息");
        System.out.println("4、删除车辆");
        System.out.println("5、查看营业额");
        System.out.println("6、管理顾客");
        System.out.println("7、退出");
    }
    @Override
    public void checkVehicle() {
        int x;
        Utils.ChooseMenu();
        x = Utils.readInt(3, 1);
        Factory f = new Factory();
        f.init();
        List<Vehicle> list = f.getList();
        System.out.println("您想查看的车辆信息如下：");
        switch (x) {
            case 1:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof Car) {
                        System.out.println(list.get(i));
                    }
                }
                break;
            case 2:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof Bus) {
                        System.out.println(list.get(i));
                    }
                }
                break;
            case 3:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof Trunk) {
                        System.out.println(list.get(i));
                    }
                }
                break;
        }

    }

    @Override
    public boolean addVehicle() {
        Utils.ChooseMenu();
        int x=Utils.readInt(3,1);
        Factory f=new Factory();
        f.init();
        List<Vehicle> list=f.getList();
        switch (x){
            case 1:
                Car car= (Car) Utils.getV();
                System.out.print("请输入轿车型号：");
                String type=Utils.scan.next();
                car.setType(type);
                list.add(car);
                f.outit(list);
                return true;
            case 2:
                Car c= (Car) Utils.getV();
                System.out.print("请输入客车座位数（请输入10到100的整数）：");
                int seat=Utils.readInt(100,10);
                Bus bus=new Bus(c.getVehicleld(),c.getBrand(),c.getPerRent(),seat);
                list.add(bus);
                f.outit(list);
                return true;
            case 3:
                Car c1= (Car) Utils.getV();
                System.out.print("请输入货车载重量（请输入3到1000的整数）：");
                int load=Utils.readInt(1000,3);
                Trunk trunk=new Trunk(c1.getVehicleld(),c1.getBrand(),c1.getPerRent(),load);
                list.add(trunk);
                f.outit(list);
                return true;
        }
        return false;
    }

    @Override
    public boolean modifyVehicle() {
        Factory f=new Factory();
        f.init();
        List<Vehicle> list=f.getList();
        System.out.println("车库中所有的车辆信息如下：");
        for(int i=0;i<list.size();i++){
            System.out.println(i+1+"号车："+list.get(i));
        }
        System.out.print("请输入您想要修改的车辆的序号：");
        int x=Utils.readInt(list.size(),1);
        int y=0;
        while(y!=-1){
            System.out.print("请输入您想要修改的车辆的信息编号（修改完毕请输入-1）：");
            System.out.print("1、车牌号\t2、品牌\t3、日租金\t4、型号/座位数/载重量");
            y=Utils.readInt(4,1);
            switch (y){
                case 1:
                    System.out.print("请输入新的车牌号：");
                    String str=Utils.scan.next();
                    list.get(x-1).setVehicleld(str);
                    break;
                case 2:
                    System.out.print("请输入新的品牌：");
                    str=Utils.scan.next();
                    list.get(x-1).setBrand(str);
                    break;
                case 3:
                    System.out.print("请输入新的日租金：");
                    int m=Utils.readInt(10000,100);
                    list.get(x-1).setCalRent(m);
                    break;
                case 4:
                    if(list.get(x-1)instanceof Car){
                        System.out.print("请输入新的轿车型号：");
                        str=Utils.scan.next();
                        ((Car) list.get(x-1)).setType(str);
                        break;
                    }
                    if(list.get(x-1)instanceof Bus){
                        System.out.print("请输入新的客车座位数（请输入10到100的整数）：");
                        int seat=Utils.readInt(100,10);
                        ((Bus) list.get(x-1)).setSeat(seat);
                        break;
                    }
                    if(list.get(x-1)instanceof Trunk){
                        System.out.print("请输入新的货车载重量（请输入3到1000的整数）：");
                        int load=Utils.readInt(1000,3);
                        ((Trunk) list.get(x-1)).setLoad(load);
                        break;
                    }
            }
        }
        f.outit(list);
        return true;
    }

    @Override
    public boolean deleteVehicle() {
        Factory f=new Factory();
        f.init();
        List<Vehicle> list=f.getList();
        System.out.println("车库中所有的车辆信息如下：");
        for(int i=0;i<list.size();i++){
            System.out.println(i+1+"号车："+list.get(i));
        }
        System.out.print("请输入您想要删除的车辆的序号：");
        int x=Utils.readInt(list.size(),1);
        System.out.print("请确认是否删除（y/n）：");
        while(true){
            char a=Utils.scan.next().charAt(0);
            if(a=='Y'||a=='y'){
                list.remove(x-1);
                f.outit(list);
                return true;
            } else if (a == 'N' || a == 'n') {
                return false;
            }else{
                System.out.print("输入选项错误，请重新输入：");
            }
        }
    }

    @Override
    public void checkTurnover() {
        System.out.println("这段时间详细租车订单如下：");
        List list = Utils.init("Account.txt");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        System.out.println("公司账户余额为："+Utils.init1("Turnover.txt")+"元");
    }

    @Override
    public boolean manageCustomer() {
        List list=Utils.init("Customer.txt");
        int x;
        if(list.isEmpty()){
            System.out.println("还没有顾客注册本系统，您可以选添加用户（退出请输入-1）：");
            x=2;
        }
        else{
            System.out.println("所有注册用户姓名如下信息如下：");
            for(int i=0;i<list.size();i=i+2){
                System.out.println("用户"+(i/2+1)+": "+list.get(i));
            }
            System.out.print("您可以进行如下操作：1、删除用户\t2、添加用户\t-1、退出：");
            x=Utils.readInt(2,1);
        }

        if(x==1){
            System.out.print("请选择您要删除的用户（退出请输入-1）：");
            int y=Utils.readInt(list.size()/2,1);
            if(y==-1)return false;
            System.out.print("请确认是否删除（y/n）：");
            while(true){
                char a=Utils.scan.next().charAt(0);
                if(a=='Y'||a=='y'){
                    list.remove(y-1);
                    list.remove(y-1);
                    Utils.fileOut("Customer.txt",list);
                    System.out.println("删除用户成功！");
                    return true;
                } else if (a == 'N' || a == 'n') {
                    return false;
                }else{
                    System.out.print("输入选项错误，请重新输入：");
                }
            }

        }else if(x==2){
            int flag=0;
            String name;
            String password;
            while (true) {
                int flag1 = 0;
                System.out.print("请输入用户名(放弃注册请输入-1)：");
                name = Utils.scan.next();
                for (int i = 0; i < list.size(); i = i + 2) {
                    if ((name + "\r").equals(list.get(i))) {
                        System.out.println("该用户已存在，请重新输入用户名：");
                        flag1 = 1;
                        break;
                    }
                }
                if ("-1".equals(name)) {
                    System.out.println("用户注册失败，即将返回主菜单...");
                    flag = 1;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                if (flag1 == 0) break;
            }
            if (flag == 0) {
                System.out.print("请输入密码：");
                password = Utils.scan.next();
                if ("-1".equals(password)) {
                    System.out.println("用户注册失败，即将返回主菜单...");
                    flag = 1;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.add(name + "\r");
                list.add(password + "\r");
                Utils.fileOut("Customer.txt", list);
                System.out.println("用户注册成功，"+name+"可以选择登陆啦！");
            }
        }
            return true;
        }

   }
