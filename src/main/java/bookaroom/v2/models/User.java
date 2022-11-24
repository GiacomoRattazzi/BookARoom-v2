package bookaroom.v2.models;

import bookaroom.v2.exceptions.InvalidCreditCardDateException;
import java.time.YearMonth;
import java.util.Date;

/*
 * @author Team BookARoom
 */

public class User {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private int password;
    private int CCnumber;
    private int CCcode;
    private Date CCexpirationdate;
    private Booking booking;

    public User(String username, String firstName, String lastName, String email, String password, String CCnumber, String CCcode, Date CCexpirationdate) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password.hashCode();
        this.CCnumber = CCnumber.hashCode();
        this.CCcode = CCcode.hashCode();
        this.CCexpirationdate = CCexpirationdate;
        this.booking = new Booking();
    }
    
    public void resetFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Booking getBooking() {
        return booking;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
    
    public int getCCnumber() {
        return CCnumber;
    }
    
    public int getCCcode() {
        return CCcode;
    }
    
    public Date getCCexpirationdate() {
        return CCexpirationdate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCCnumber(int CCnumber) {
        this.CCnumber = CCnumber;
    }
    
    public void setCCcode(int CCcode) {
         this.CCcode = CCcode;
    }
    
    public void setCCexpirationdate(Date CCexpirationdate) {
        this.CCexpirationdate = CCexpirationdate;
    }        

    public void setPassword(String password) {
        this.password = password.hashCode();
    }

    public boolean isPasswordCorrect(String password) {
        return password.hashCode() == this.password;
    }
    
    @Override
    public boolean equals(Object obj) {
        return ((User) obj).getUsername().equals(this.username);
    }

    @Override
    public String toString() {
        return "Username: " + this.username
                + "\nFirst name: " + this.firstName
                + "\nLast name: " + this.lastName
                + "\nEmail: " + this.email
                + "\nCredit Card information:\n   - number: ****************" + "\n   - code: ***" + "\n   - expiration date: " + this.CCexpirationdate
                + "\n" + this.booking.toString();
    }
    public void completeBooking()throws InvalidCreditCardDateException {
        booking.emptyBooking();
        throw new InvalidCreditCardDateException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}