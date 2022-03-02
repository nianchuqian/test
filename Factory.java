package factory;

import vehicle.Bus;
import vehicle.Car;
import vehicle.Trunk;
import vehicle.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Factory {
    private List<Vehicle> list = new ArrayList<>();

    public List<Vehicle> init1() {
        List<Vehicle> list1 = new ArrayList<>();
        list1.add(new Car("京NY28588", "宝马", 800, "宝马-X6"));
        list1.add(new Car("京CNY3284", "宝马", 600, "宝马-550i"));
        list1.add(new Car("京NT37465", "别克", 300, "别克-林荫大道"));
        list1.add(new Car("京NT96968", "别克", 600, "别克-GL8"));
        list1.add(new Bus("京6566754", "金杯", 800, 16));
        list1.add(new Bus("京8696997", "金杯", 800, 34));
        list1.add(new Bus("京9696996", "金杯", 1500, 16));
        list1.add(new Bus("京8696998", "金杯", 1500, 34));
        list1.add(new Trunk("京A587894", "一汽", 1800, 20));
        list1.add(new Trunk("京B879541", "一汽", 2800, 40));
        list1.add(new Trunk("京C459754", "解放", 1700, 20));
        list1.add(new Trunk("京D879541", "解放", 2900, 40));
        return list1;
    }

    public List<Vehicle> getList() {
        return list;
    }

    public void outit(List<Vehicle> list1) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("Vehicles.txt"));
            Iterator<Vehicle> iterator = list1.iterator();
            while (iterator.hasNext()) {
                oos.writeObject(iterator.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void init() {
        ObjectInputStream ois = null;
        BufferedReader in = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("Vehicles.txt"));
            in = new BufferedReader(new FileReader("Vehicles.txt"));
            String v;
            while ((v = in.readLine()) != null) {
                list.add((Vehicle) ois.readObject());
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
    }
}


