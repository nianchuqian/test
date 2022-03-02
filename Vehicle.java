package vehicle;

import util.Utils;

import java.io.Serializable;

public abstract class Vehicle implements Serializable {
    public static final long serialVersionUID=-6537058438086621115L;
    private String brand;
    private String vehicleld;
    private double perRent;
    private int days;
    private float calRent;
    public Vehicle() {
    }

    public Vehicle(String vehicleld, String brand, double perRent) {

        this.vehicleld = vehicleld;
        this.brand = brand;
        this.perRent = perRent;
    }

    public float getCalRent() {
        return calRent;
    }

    public void setCalRent(float calRent) {
        this.calRent = calRent;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVehicleld() {
        return vehicleld;
    }

    public void setVehicleld(String vehicleld) {
        this.vehicleld = vehicleld;
    }

    public double getPerRent() {
        return perRent;
    }

    public void dayChoose() {
        System.out.print("请输入您想要租赁的天数：");
        int x= Utils.readInt(1000000,0);
        setDays(x);
    }
    public abstract float calRent();
}
