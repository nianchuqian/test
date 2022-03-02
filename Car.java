package vehicle;

public class Car extends Vehicle {
    private String type;

    public Car() {
        super();
    }

    public Car(String vehicleld, String brand, double perRent, String type) {
        super(vehicleld, brand, perRent);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "[车牌号：" + getVehicleld() + ", 品牌：" + getBrand() + ", 型号为：" + type + ", 日租金：" + getPerRent() + "]";
    }

    @Override
    public float calRent() {
        System.out.print("您的租车费用为：");
        if (getDays() > 150) {
            setCalRent((float) (getDays() * getPerRent() * 0.7));
        } else if (getDays() > 30) {
            setCalRent((float) (getDays() * getPerRent() * 0.8));

        } else if (getDays() > 7) {
            setCalRent((float) (getDays() * getPerRent() * 0.9));

        } else {
            setCalRent((float) (getDays() * getPerRent()));

        }
        return getCalRent();
    }
}
