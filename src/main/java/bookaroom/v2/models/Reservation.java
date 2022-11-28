package bookaroom.v2.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Team BookARoom
 */

public class Reservation {
    
    private int number;
    private String name;
    private double totalPrice;
    private List<LocalDate> range;

    public Reservation(int number, String name, double totalPrice, List<LocalDate> range) {
        this.number = number;
        this.name = name;
        this.totalPrice = totalPrice;
        this.range = range;
    }
    
    public int getNumber() {
        return number;
    }
    
    public List<LocalDate> getRange() {
        return range;
    }

    public String getName() {
        return name;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setRange(List<LocalDate> range) {
        this.range = range;
    }
}