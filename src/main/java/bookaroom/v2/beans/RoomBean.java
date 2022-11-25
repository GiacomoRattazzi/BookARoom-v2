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
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
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
    
    
    private double totalPrice = 0;
    private List<LocalDate> range = null;
    private List<LocalDate> betweenRange;
    private Boolean boo =true;
    private LocalDate test1;
    private Boolean roomEmpty = true;
    private String temp1;
    private String temp2;
    


    public void addDatesToBooking() {
        User user = LoginBean.getUserLoggedIn();
        user.getBooking().addBookedRoomAndDates(roomName, datesbooked);
        //empty values
        //this.roomName = "";
        }

    public void setRoomDates(String roomanddates) {
        this.datesbooked = roomanddates;
    }
    
    public void addTotalToBooking() {
        User user = LoginBean.getUserLoggedIn();
        user.getBooking().addTotal(roomName.toString(), getDatesBetween().toString());
    }
  
    public void addRoomToBooking() {
        User user = LoginBean.getUserLoggedIn();
        try {
            Room r = findRoomByNameInTheHotel(roomName);
            //user.getBooking().addRoom(r);
            //user.getBooking().addBookedRoomAndDates(roomName, getDatesBetween().toString());
            user.getBooking().addTotal(roomName.toString(), getDatesBetween().toString());
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        // empty values
        //this.roomName = "";
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
    
    
    //NEW FROM TEST BEAN
    
    public void click() {
        if (roomEmpty == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "This date is already booked: " + temp2 ));
        }else {
            PrimeFaces.current().ajax().update("form:display");
            PrimeFaces.current().executeScript("PF('dlg').show()");
        }
    }
 
    public HashMap<String, ArrayList<LocalDate>> getBookedDates() {
        return MockDatabase.getInstance().getBookedDates();
    }
    
    public String getResult(){
        return roomEmpty.toString();
    }
    public String getResult2(){
        return temp1;
    }
    
    public String getResult3(){
        return temp2;
    }
    
    
    
    public void dateFor() {
        roomEmpty = true;
        for (LocalDate tempBooked : getDatesBetween()) {
            test1 = tempBooked;
            
            ArrayList<LocalDate> test2;
            for (HashMap.Entry<String, ArrayList<LocalDate>> a : getBookedDates().entrySet()) {
                if (a.getKey().equals(roomName)) {
                    test2 = a.getValue();
                    
                    for (LocalDate v : test2){
                        temp1 = test1.toString();
                        temp2 = v.toString();
                        if (test1.toString().equals(v.toString()) == true) {
                            roomEmpty = false;
                            break;
                    }
                    } 
            }
            }}
        
    }
    
    public void addDatesBooked() {
        MockDatabase.getInstance().setDates(getDatesBetween(), roomName);
        
    }
  
    public List<LocalDate> getRange() {
        return range;
    }
 
    public void setRange(List<LocalDate> range) {
        this.range = range;
    }
    
    public LocalDate getToday() {
        return LocalDate.now();
    }
    

    public List<LocalDate> getDatesBetween() { 
        if(range == null) {
            return null;
        } else {
            long numOfDaysBetween = ChronoUnit.DAYS.between(range.get(0), range.get(1)); 
            return IntStream.iterate(0, i -> i + 1)
              .limit(numOfDaysBetween)
              .mapToObj(i -> range.get(0).plusDays(i))
              .collect(Collectors.toList());
                
        }
    }
   
    
    public void setDatesBetween(List<LocalDate> datesBetween) {
        this.betweenRange = betweenRange;
    }
    
    //NEW PRICE
    private double findRoomPrice() {
        for (Room r : MockDatabase.getInstance().getRooms()) {
            if (r.getName().equals(roomName)) {
                return r.getPrice();
            }
        }return 0;
         }
    
    public double getTotalPrice() {
        if (range!=null){
            long diffDays = ChronoUnit.DAYS.between(range.get(0), range.get(1));
            totalPrice = findRoomPrice()*diffDays;
                return totalPrice;
        }else{
            return 0;
        }
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void finish() {
        dateFor(); 
        if (roomEmpty==false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "This date is already booked: " + temp2 ));
        }else{
            addDatesBooked();
            
            PrimeFaces.current().ajax().update("form:display");
            PrimeFaces.current().executeScript("PF('dlg').show()");
            addTotalToBooking();
        }
        }
    

    
    //Maybe not needed 
    /*
    public void setToday(LocalDate today) {
        this.today = today;
    }
*/
    
}


