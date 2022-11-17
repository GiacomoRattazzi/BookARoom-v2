package bookaroom.v2.models;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * @author team BookARoom
 */

public class Room {

    private String name;
    private double price;
    private ArrayList<String> description;
    private ArrayList<String> datesbooked;

    public Room(String name, double price, ArrayList<String> descriptionAll,ArrayList<String> datesbookedList) {
        this.name = name;
        this.price = price;
        description = new ArrayList<>();
        description.addAll(descriptionAll);
        this.datesbooked = new ArrayList<>(); // this is normaly useless because can not be use
        this.datesbooked.addAll(datesbookedList); // this is normaly useless because can not be use
    }

    public Room(String room_1, int i, ArrayList<String> arrayList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
// Totally useless ------------------------------------------
    public ArrayList<String> getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    
    
    public ArrayList<String> getDates() {
        return datesbooked;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public void setDates(ArrayList<String> datesbooked) {
        this.datesbooked = datesbooked;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Room) {
            Room r = (Room) obj;
            return r.toString().equals(this.toString());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Room{"
                + "\nName=" + name
                + "\nPrice=" + price
                + "\nDescription=" + Arrays.toString(description.toArray()) + '}';
    }   
}
