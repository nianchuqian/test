package vehicle;

public class Trunk extends Vehicle{
    private int load;
    public Trunk() {
        super();
    }
    public Trunk(String vehicleld, String brand, double perRent,int load){
        super(vehicleld,brand,perRent);
        this.load = load;
    }
    public int getLoad() {
        return load;
    }
    public void setLoad(int load) {
        this.load = load;
    }
    @Override
    public String toString() {
        return "[车牌号：" + getVehicleld() + ", 品牌：" +getBrand()+", 载重量为："+load +"吨, 日租金：" + getPerRent()+"]";
    }
    @Override
    public float calRent() {
        System.out.print("您的租车费用为：");
        if (getDays() >= 150) {
            setCalRent((float) (getDays() * getPerRent() * 0.6));

        } else if (getDays() >= 30) {
            setCalRent((float) (getDays() * getPerRent() * 0.7));
        } else if (getDays() >= 7) {
            setCalRent((float) (getDays() * getPerRent() * 0.8));
        } else if(getDays()>=3) {
            setCalRent((float) (getDays() * getPerRent() * 0.9));
        }
        else
            setCalRent((float) (getDays() * getPerRent()));
        return getCalRent();
    }
}
