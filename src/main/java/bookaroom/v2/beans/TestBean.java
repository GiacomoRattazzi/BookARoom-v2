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
import java.util.GregorianCalendar;
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
 
    private List<LocalDate> range;

    public void click() {
        PrimeFaces.current().ajax().update("form:display");
        PrimeFaces.current().executeScript("PF('dlg').show()");
    }
 
    public List<LocalDate> getRange() {
        return range;
    }
 
    public void setRange(List<LocalDate> range) {
        this.range = range;
    }
 
}