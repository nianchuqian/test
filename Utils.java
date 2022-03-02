package util;

import user.Customer;
import user.Manage;
import user.User;
import vehicle.Car;
import vehicle.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static com.sun.deploy.trace.Trace.flush;

public class Utils {
    public static Scanner scan = new Scanner(System.in);

    public static List init(String path) {
        List list = new ArrayList();
        ObjectInputStream ois = null;
        BufferedReader in = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(path));
            in = new BufferedReader(new FileReader(path));
            String v;
            while ((v = in.readLine()) != null) {
                list.add(ois.readObject());
                flush();
            }

        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static void fileOut(String path, List list) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path));
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                oos.writeObject(iterator.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int readInt(int a, int b) {
        int x = 0, flag = 0;
        while (true) {
            String n = scan.next();
            try {
                x = Integer.parseInt(n);
            } catch (Exception e) {
                flag = 1;
                System.out.println("没有输入数字，请重新输入" + "\n" + "如果想要退出请输入-1.");
            }
            if (x <= a && x >= b) break;
            else if (x == -1) {
                System.out.println("本次选择服务结束，欢迎下次使用。");
                break;
            } else if (flag == 0) {
                System.out.println("输入选项不存在,请重新输入" + "\n" + "如果想要退出请输入-1.");
            }
        }
        return x;
    }

    public static void ChooseMenu() {
        System.out.println("请选择您想操作的车辆类型：");
        System.out.print("1、轿车\t\t2、客车\t\t3、货车");
    }

    public static double init1(String path) {
        double x = 0;
        FileReader fr = null;
        try {
            fr = new FileReader(new File("Turnover.txt"));
            char[] cbuf = new char[64];
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                String str = new String(cbuf, 0, len);
                x = Double.parseDouble(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return x;
    }

    public static void fileOut1(String path, double x) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File("Turnover.txt"));
            fw.write(String.valueOf(x));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Vehicle getV() {
        System.out.print("请输入车牌号：");
        String vehiceled = scan.next();
        System.out.print("请输入车的品牌：");
        String brand = scan.next();
        System.out.print("请输入日租金：");
        double perRent = scan.nextDouble();
        return new Car(vehiceled, brand, perRent, null);
    }

    public static User menu() {
        int x = 0;
        User u = null;
        int f = 0;
        while (true) {
            System.out.println("1、管理员登录");
            System.out.println("2、用户登录");
            System.out.println("3、管理员注册");
            System.out.println("4、用户注册");
            System.out.println("5、退出");
            System.out.print("请选择使用方式：");
            x = Utils.readInt(5, 1);
            if (x == -1) {
                break;
            }
            if (x == 5) {
                System.out.println("感谢您对本系统的支持，欢迎下次使用。");
                break;
            }
            switch (x) {
                case 1:
                    List list1 = init("Manage.txt");
                    if (list1.isEmpty()) {
                        System.out.println("亲，本系统还没有注册过管理员呢，快去注册成为第一个管理员吧！！！");
                        System.out.println("正在为您跳转到主菜单...");
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    System.out.print("请输入用户名：");
                    String name = scan.next();
                    System.out.print("请输入密码：");
                    String password = scan.next();
                    for (int i = 0; i < list1.size(); i = i + 2) {
                        if ((name + "\r").equals(list1.get(i)) && (password + "\r").equals(list1.get(i + 1))) {
                            u = new Manage(name, password);
                            break;
                        }
                    }
                    if (u != null) {
                        f = 1;
                        System.out.println("登陆成功！！！");
                        System.out.println("正在加载管理员菜单，请稍后...");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else {
                        System.out.println("用户名或密码输入错误，即将返回主菜单...");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }

                case 2:
                    List list2 = init("Customer.txt");
                    if (list2.isEmpty()) {
                        System.out.println("亲，本系统还没有注册顾客呢，快去注册成为第一个顾客吧！！！");
                        System.out.println("正在为您跳转到主菜单...");
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    System.out.print("请输入用户名：");
                    name = scan.next();
                    System.out.print("请输入密码：");
                    password = scan.next();
                    for (int i = 0; i < list2.size(); i = i + 2) {
                        if ((name + "\r").equals(list2.get(i)) && (password + "\r").equals(list2.get(i + 1))) {
                            u = new Customer(name, password);
                            break;
                        }
                    }
                    if (u != null) {
                        f = 1;
                        System.out.println("登陆成功！！！");
                        System.out.println("正在加载用户菜单，请稍后...");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else {
                        System.out.println("用户名或密码输入错误，即将返回主菜单...");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                case 3:
                    list1 = init("Manage.txt");
                    int flag = 0;
                    System.out.println("请输入注册信息：");
                    while (true) {
                        int flag1 = 0;
                        System.out.print("请输入用户名(放弃注册请输入-1)：");
                        name = scan.next();
                        for (int i = 0; i < list1.size(); i = i + 2) {
                            if ((name + "\r").equals(list1.get(i))) {
                                System.out.println("该用户已存在，请重新输入用户名：");
                                flag1 = 1;
                                break;
                            }
                        }
                        if ("-1".equals(name)) {
                            System.out.println("管理员注册失败，即将返回主菜单...");
                            flag = 1;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        if (flag1 == 0) break;
                    }
                    if (flag == 0) {
                        System.out.print("请输入密码：");
                        password = scan.next();
                        if ("-1".equals(password)) {
                            System.out.println("用户注册失败，即将返回主菜单...");
                            flag = 1;
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        list1.add(name + "\r");
                        list1.add(password + "\r");
                        fileOut("Manage.txt", list1);
                        System.out.println("管理员注册成功，您可以选择登陆啦！");
                        break;
                    }

                    break;
                case 4:
                    flag = 0;
                    list2 = init("Customer.txt");
                    while (true) {
                        int flag1 = 0;
                        System.out.print("请输入用户名(放弃注册请输入-1)：");
                        name = Utils.scan.next();
                        for (int i = 0; i < list2.size(); i = i + 2) {
                            if ((name + "\r").equals(list2.get(i))) {
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
                        list2.add(name + "\r");
                        list2.add(password + "\r");
                        Utils.fileOut("Customer.txt", list2);
                        System.out.println("用户注册成功，您可以选择登陆啦！");
                    }
            }
            if (f == 1) break;
        }
        return u;
    }
}