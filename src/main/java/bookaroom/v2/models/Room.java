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

    public Room(String name, double price, ArrayList<String> descriptionAll) {
        this.name = name;
        this.price = price;
        description = new ArrayList<>();
        description.addAll(descriptionAll);
    }



    public ArrayList<String> getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }    

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Room) {
            Room r = (Room) obj;
            return r.toString().equals(this.toString());
        }
        return false;
    }
    
    
    public String getPrint() {
        return  name + "       "
                + "Price: " + price +".-" + "       "
                + "Description: " + Arrays.toString(description.toArray());
                }
    @Override
    public String toString() {
        return "Room{"
                + "\nName=" + name
                + "\nPrice=" + price
                + "\nDescription=" + Arrays.toString(description.toArray()) + '}';
    }   
}
