package user;

import java.io.Serializable;

public class User implements Serializable {
    public static final long serialVersionUID=695085794287349228L;
    private String name;
    private String password;
    private double  turnover;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void menu() {

    }
}
