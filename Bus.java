package vehicle;

public class Bus extends Vehicle{
    private int seat;
    public Bus() {
        super();
    }
    public Bus(String vehicleld, String brand, double perRent,int seat){
        super(vehicleld,brand,perRent);
        this.seat = seat;
    }
    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    @Override
    public String toString() {
        return "[车牌号：" + getVehicleld() + ", 品牌：" +getBrand()+", 座位数为："+seat +"坐, 日租金：" + getPerRent()+"]";
    }
    @Override
    public float calRent() {
        System.out.print("您的租车费用为：");
        if (getDays() >= 150) {
            setCalRent ((float) (getDays() * getPerRent() * 0.6));
        } else if (getDays() >= 30) {
            setCalRent ((float) (getDays() * getPerRent() * 0.7));

        } else if (getDays() >= 7) {
            setCalRent ((float) (getDays() * getPerRent() * 0.8));

        } else if(getDays()>=3) {
            setCalRent ((float) (getDays() * getPerRent() * 0.9));

        }
        else
            setCalRent ((float) (getDays() * getPerRent()));
        return getCalRent();
    }
}
