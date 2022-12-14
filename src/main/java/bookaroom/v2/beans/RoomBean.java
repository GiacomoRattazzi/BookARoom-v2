package bookaroom.v2.beans;

import bookaroom.v2.exceptions.DoesNotExistException;
import bookaroom.v2.models.Room;
import bookaroom.v2.database.MockDatabase;
import bookaroom.v2.models.Reservation;
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
    
    private int resNbr = 0;
    private int RemoveResNbr = 0;
    private String DelRoomName = "";
    private List<LocalDate> DelDates = null;
    private List<LocalDate> DelDatesRange = null;
    

    private boolean checkRoomExists() {
        for (Room r : MockDatabase.getInstance().getRooms()) {
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

    public ArrayList<Room> getRooms() {
        return MockDatabase.getInstance().getRooms();
    }
    

    public String getRoomName() {
        return roomName;
    }
    
  
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    
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
    
    public ArrayList<String> getBookedDatesString(){
        ArrayList<String> temp = new ArrayList<String>();
        for (LocalDate x : MockDatabase.getInstance().getBookedDates().get(roomName)){
            temp.add(x.toString());
        }
        return temp;
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
            resNbr += 1;
            addResToRes();
            
            PrimeFaces.current().ajax().update("form:display");
            PrimeFaces.current().executeScript("PF('dlg').show()");
            
        }
        }
    //TEST OTHER
   
    
    public void addResToRes() {
        User user = LoginBean.getUserLoggedIn();
        Reservation r = new Reservation(resNbr, roomName, getTotalPrice(), range);
        user.getBooking().addReservation(r);
        
    }
    
    public int getResNbr(){
        return RemoveResNbr;
    }
    
    public void setResNbr(int Rnbr) {
        RemoveResNbr = Rnbr;
    }
    
    public String getDelRoomName(){
        return DelRoomName;
    }
    
    public void setDelRoomName(String delR) {
        DelRoomName = delR;
    }
    
    
    public void removeDatesBooked() {
        MockDatabase.getInstance().removeDates(getDatesBetween(), roomName);   
    }
    
    
    public void removeRoomFromBooking() {
        User user = LoginBean.getUserLoggedIn();
        try {
            if (doesRoomExistInBooking(user, RemoveResNbr)) {
                setResDatesByNumberInBooking(user, RemoveResNbr);
                MockDatabase.getInstance().removeDates(getRangeFromBooking(), findResNameByNumberInBooking(user, RemoveResNbr));
                user.getBooking().removeReservation(findResByNumberInBooking(user, RemoveResNbr));
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        // empty values
        this.RemoveResNbr = 0;
    }
    
    private boolean doesRoomExistInBooking(User user, int resnbr) {
        for (Reservation r : user.getBooking().getReservations()) {
            if (r.getNumber() == resnbr) {
                return true;
            }
        }
        return false;
    }
    
    private Reservation findResByNumberInBooking(User user, int resnbr) throws DoesNotExistException {
        for (Reservation r : user.getBooking().getReservations()) {
            if (r.getNumber() == resnbr) {
                return r;
            }
        }
        throw new DoesNotExistException("Reservation " + resnbr + " does not exist.");
    }
    
    private String findResNameByNumberInBooking(User user, int resnbr) throws DoesNotExistException {
        for (Reservation r : user.getBooking().getReservations()) {
            if (r.getNumber() == resnbr) {
                return r.getName();
            }
        }
        throw new DoesNotExistException("Reservation " + resnbr + " does not exist.");
    }
    
    public void setResDatesByNumberInBooking(User user, int resnbr) {
        for (Reservation r : user.getBooking().getReservations()) {
            if (r.getNumber() == resnbr) {
                DelDates = r.getRange();
                System.out.println(DelDates);
               
            }
        }
    }
    
    private List<LocalDate> getRangeFromBooking(){
        if(DelDates == null) {
            return null;
        } else {
            long numOfDaysBetween = ChronoUnit.DAYS.between(DelDates.get(0), DelDates.get(1)); 
            return IntStream.iterate(0, i -> i + 1)
              .limit(numOfDaysBetween)
              .mapToObj(i -> DelDates.get(0).plusDays(i))
              .collect(Collectors.toList());
                
        }
    }
    
    public void deleteDatesBooked(List<LocalDate> list) {
        MockDatabase.getInstance().removeDates(getDatesBetween(), roomName);   
    }
    
    public void testingremove(String roomName){
        MockDatabase.getInstance().testRemove();
    }
    
     
            
    //Maybe not needed 
    /*
    public void setToday(LocalDate today) {
        this.today = today;
    }
*/
    
}


