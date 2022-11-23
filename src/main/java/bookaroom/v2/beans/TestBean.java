package bookaroom.v2.beans;

import bookaroom.v2.exceptions.DoesNotExistException;
import bookaroom.v2.models.Room;
import bookaroom.v2.database.MockDatabase;
import bookaroom.v2.models.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Date;
import java.util.Calendar;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
/**
@author Team BookARoom
 */

@Named
@SessionScoped
public class TestBean implements Serializable {
 
    private List<LocalDate> range = null;
    private List<LocalDate> betweenRange;
    //Maybe not needed: private LocalDate today;
    private Boolean boo =true;
    private LocalDate test1;
    private Boolean roomEmpty = true;
    private String temp1;
    private String temp2;

    
    public void click() {
        if (roomEmpty == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "This date is already booked: " + temp2 ));
        }   else {
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
                if (a.getKey().equals("Room 1")) {
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
    


    
    //Maybe not needed 
    /*
    public void setToday(LocalDate today) {
        this.today = today;
    }
*/
    
}




