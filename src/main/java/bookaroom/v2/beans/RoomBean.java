package bookaroom.v2.beans;

import bookaroom.v2.exceptions.DoesNotExistException;
import bookaroom.v2.models.Room;
import bookaroom.v2.database.MockDatabase;
import bookaroom.v2.models.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
	
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
/**
@author Team BookARoom
 */
@Named(value = "roomBean")
@SessionScoped

public class RoomBean implements Serializable{

    private String roomName = "";
    private String dayArrival = "";
    private String dayDeparture ="";
    private String datesbooked = "";
    private HashMap<String, List<LocalDate>> Map;


    public void addDatesToBooking() {
        User user = LoginBean.getUserLoggedIn();
        user.getBooking().addBookedRoomAndDates(roomName, datesbooked);
        //empty values
        this.roomName = "";
        }

    public void setRoomDates(String roomanddates) {
        this.datesbooked = roomanddates;
    }
  
    public void addRoomToBooking() {
        User user = LoginBean.getUserLoggedIn();
        try {
            Room r = findRoomByNameInTheHotel(roomName);
            user.getBooking().addRoom(r);
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        // empty values
        this.roomName = "";
    }
    

    private boolean checkRoomExists() {
        for (Room r : MockDatabase.getInstance().getRooms()) {
            if (r.getName().equals(roomName)) {
                return true;
            }
        }
        return false;
    }
  
    public void removeRoomFromBooking() {
        User user = LoginBean.getUserLoggedIn();
        try {
            if (doesRoomExistInBooking(user, roomName)) {
                user.getBooking().removeRoom(findRoomByNameInBooking(user, roomName));
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        // empty values
        this.roomName = "";
    }
    
// TODO put this in form of an exception
    private boolean doesRoomExistInBooking(User user, String roonName) {
        for (Room r : user.getBooking().getRooms()) {
            if (r.getName().equals(roomName)) {
                return true;
            }
        }
        return false;
    }

    private Room findRoomByNameInTheHotel(String roomName) throws DoesNotExistException {
        for (Room r : MockDatabase.getInstance().getRooms()) {
            if (r.getName().equals(roomName)) {
                return r;
            }
        }
        throw new DoesNotExistException(roomName + " does not exist.");
    }

    private Room findRoomByNameInBooking(User user, String roomName) throws DoesNotExistException {
        for (Room r : user.getBooking().getRooms()) {
            if (r.getName().equals(roomName)) {
                return r;
            }
        }
        throw new DoesNotExistException("Room " + roomName + " does not exist or is not booked.");
    }

    public ArrayList<Room> getRooms() {
        return MockDatabase.getInstance().getRooms();
    }
    
     public String getTesting() {
        return "ah";
    }


    //TEST
    public HashMap<String, ArrayList<LocalDate>> getBookedDates() {
        return MockDatabase.getInstance().getBookedDates();
    }
    //END TEST

    public String getRoomName() {
        return roomName;
    }
    
      public String getRoomDayArrival() {
        return dayArrival;
    }
    
    public String getRoomDayDeparture() {
        return dayDeparture;
    }
    
    public HashMap<String, List<LocalDate>> getBookRoomAndDates(String BookedRoomName, List<LocalDate> BookedRoomDates) {
        HashMap<String, List<LocalDate>> Hmap = new HashMap<>();
        Hmap.put(BookedRoomName,BookedRoomDates);
        return Hmap;
    }
    
    public void setBookRoomAndDates(HashMap<String, List<LocalDate>> Map) {
        this.Map = Map;
    }
    
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    
    public void setRoomDayArrival(String dayArrival) {
        this.dayArrival = dayArrival;
    }
    
    public void setRoomDayDeparture(String dayDeparture) {
        this.dayDeparture = dayDeparture;
    }
    /*
    public static void setRoomDayDates(List<LocalDate> datesbooked) {
        RoomController.datesbooked = datesbooked;
    }
*/
    
    //GET PRICE
    public double getRoomPriceTest(String roomName) {
        User user = LoginBean.getUserLoggedIn();
        double p = 0;
        try {
            Room r = findRoomByNameInTheHotel(roomName);
            p = user.getBooking().GetRoomPrice(r);
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
    
    

}
