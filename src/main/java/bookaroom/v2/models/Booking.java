package bookaroom.v2.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Team BookARoom
 */



public class Booking {

    private ArrayList<Room> rooms;
    private List<LocalDate> datesbookedLists;
    private String arrivalday;
    private String departureday;
    private HashMap<String, List<LocalDate>> maps;
    private ArrayList<String> roomsbookedTest;

    public Booking() {
        this.rooms = new ArrayList<>();
        this.datesbookedLists = new ArrayList<>();
        this.maps = new HashMap<>();
        this.roomsbookedTest = new ArrayList<String>();
        
        //BCDelete: this.balance = 0.0;
    }
    
    public void addBookedRoomAndDates(String roomName, String roomAndBookedDates){
        roomsbookedTest.add(roomName +": " + roomAndBookedDates);
    }
    
    public void addRoom(Room room) {
        rooms.add(room);
    }
    
    public void addDatesBookedList(List<LocalDate> datesbookedList){
        datesbookedLists.addAll(datesbookedList);
    }
    
    
    public void removeRoom(Room room) {
        rooms.remove(room);
    }
    
    public ArrayList<Room> getRooms() {
        return rooms;
    }
    
    public List<LocalDate> getDatesBooked() {
        return datesbookedLists;
    }
    
    public HashMap<String, List<LocalDate>> getBookedRoomAndDates() {
        return maps;
    }
    
    public String getArrivalDateBooking() {
        return arrivalday;
    }
    
    public String getDepartureDateBooking() {
        return departureday;
    }
    public void emptyBooking() {
        rooms.clear();
        roomsbookedTest.clear();
    }
    
    //PRICE
    public double GetRoomPrice(Room room) {
        return room.getPrice();
    }
    //ENDPRICE

    @Override
    public String toString() {
        return "Booking: " + Arrays.toString(rooms.toArray()) + "\n" + "Booked Dates:" +roomsbookedTest;
                
    }
   
}
