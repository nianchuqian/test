package user;

import factory.Factory;
import util.Utils;
import vehicle.Bus;
import vehicle.Car;
import vehicle.Trunk;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer extends User implements DefaultCustomer {
    private Vehicle v = null;

    public Customer(String name, String password) {
        super(name, password);
    }

    public void menu() {
        System.out.println("欢迎回来亲爱的" + getName());
        int x = 0;
        while (true) {
            menu1();
            x = Utils.readInt(6, 1);
            if (x == -1) {
                break;
            }
            if (x == 6) {
                System.out.println("感谢您对本系统的支持，欢迎下次使用。");
                break;
            }
            switch (x) {
                case 1:
                    checkVehicle();
                    break;
                case 2:
                    Rent();
                    break;
                case 3:
                    changeVehicle();
                    break;
                case 4:
                    perMoney();
                    break;
                case 5:
                    boolean b = modifyPassword();
                    if (b == true) {
                        System.out.println("密码修改成功！");
                    } else {
                        System.out.println("密码修改失败！");
                    }
            }

        }
    }

    private void menu1() {
        System.out.println("请问需要为您提供什么服务呢？");
        System.out.println("1、查看车辆信息");
        System.out.println("2、租车");
        System.out.println("3、更换车辆");
        System.out.println("4、付款");
        System.out.println("5、修改密码");
        System.out.println("6、退出");
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
    public void Rent() {
        List<Vehicle> list1 = new ArrayList<>();
        Factory f = new Factory();
        f.init();
        List<Vehicle> list = f.getList();
        Utils.ChooseMenu();
        int x = Utils.readInt(3, 1);
        switch (x) {
            case 1:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof Car) {
                        list1.add(list.get(i));
                    }
                }
                break;
            case 2:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof Bus) {
                        list1.add(list.get(i));
                    }
                }
                break;
            case 3:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof Trunk) {
                        list1.add(list.get(i));
                    }
                }
                break;
        }
        System.out.println("您想租赁的车辆信息如下：");
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(i + 1 + "号车：" + list1.get(i));
        }
        System.out.println("请输入您想租赁的车的编号：");
        int m = Utils.readInt(list1.size(), 1);
        if (m != -1) {
            list1.get(m - 1).dayChoose();
            v = list1.get(m - 1);
            System.out.println(list1.get(m - 1).calRent());
            System.out.println("亲，您已选择好车辆，别忘了支付哦！");
        } else {
            System.out.println("已退出选车系统...\r请选择下一步操作");
            return;
        }
    }

    @Override
    public void changeVehicle() {
        if (v == null) {
            System.out.println("亲，您还没有选择车辆哦，即将为您调转到选车界面...");
        } else {
            System.out.println("亲，是我们的车辆有什么问题吗？欢迎反馈哦" + "\n" + "即将为您跳转到换车界面...");
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Rent();
    }

    @Override
    public void perMoney() {
        if (v == null) {
            System.out.println("您还没有选择车辆，请进行选择。");
        } else {
            List list = new ArrayList();
            double x;
            list = Utils.init("Account.txt");
            x = Utils.init1("Turnover.txt");
            setTurnover(x);
            System.out.println(v.calRent());
            setTurnover(getTurnover() + v.getCalRent());

            list.add(new String("外租车信息如下：" + v + "，  租车人：" + getName() + ",   租车日期：" + new Date() + "，  租金为：" + v.getCalRent() + ",   截止目前公司账户余额为：" + getTurnover()) + "\r");
            Utils.fileOut1("Turnover.txt", getTurnover());
            Utils.fileOut("Account.txt", list);
            System.out.println("恭喜您租车成功，车辆信息已录入" + "\n" + "道路千万条，安全第一条，祝您用车愉快。");
        }

    }

    @Override
    public boolean modifyPassword() {
        List list = Utils.init("Customer.txt");
        int x = 0;
        String old = null;
        for (int i = 0; i < list.size(); i = i + 2) {
            if ((getName() + "\r").equals(list.get(i))) {
                old = (String) list.get(i + 1);
                x = i;
                list.remove(i+1);
                break;
            }
        }
        System.out.print("请输入原来的密码：");
        String password = Utils.scan.next();
        while (!old.equals(password + "\r")) {
            System.out.print("原来的密码输入错误，请重新输入（若放弃修改请输入-1）：");
            password = Utils.scan.next();
            if ("-1".equals(password)) {
                return false;
            }
        }

        System.out.print("请输入新密码：");
        password = Utils.scan.next();
        list.add(x + 1, password + "\r");
        Utils.fileOut("Customer.txt", list);
        return true;
    }
}
