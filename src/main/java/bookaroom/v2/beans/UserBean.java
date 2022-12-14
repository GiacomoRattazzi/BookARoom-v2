
package bookaroom.v2.beans;

import bookaroom.v2.exceptions.AlreadyExistsException;
import bookaroom.v2.exceptions.DoesNotExistException;
import bookaroom.v2.exceptions.InvalidCreditCardException;
import bookaroom.v2.exceptions.InvalidCreditCardDateException;
import bookaroom.v2.models.User;
import bookaroom.v2.database.MockDatabase;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;


/*
 * @author Team BookARoom
 */

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private String username = "";
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String password = "";
    private String CCnumber = "";
    private String CCcode = "";
    private Date CCexpirationdate = null;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
    private final YearMonth CurrentTime = YearMonth.now();
    private final DateTimeFormatter Dateformatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final LocalDate CurrentTimeLong = LocalDate.now();
    private static final Scanner sc = new Scanner(System.in);
    
    
    public void createAUser() {
        try {
            if (!emailExists() && !usernameExists()) {
            MockDatabase.getInstance().addAUser(new User(username, firstName, lastName, email, password, CCnumber, CCcode, CCexpirationdate));
            }
        } catch (AlreadyExistsException | DoesNotExistException ex ) {
            System.out.println(ex.getMessage());
        }
        // empty values
        this.email = "";
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.password = "";
    }   
    protected static User findByUsername(String username) throws DoesNotExistException {
        for (User user : MockDatabase.getInstance().getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new DoesNotExistException("The user " + username + " does not exist.");
    }

    private boolean emailExists() throws AlreadyExistsException {
        for (User user : MockDatabase.getInstance().getUsers()) {
            if (user.getEmail().equals(email)) {
                throw new AlreadyExistsException("The email " + email + " already in use.");
            }
        }
        return false;
    }
    
    private boolean usernameExists() throws DoesNotExistException {
        for (User user : MockDatabase.getInstance().getUsers()) {
            if (user.getUsername().equals(username)) {
                throw new DoesNotExistException("The username " + username + " already in use.");
            }
        }
        return false;
    }
    

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
        
    public String getCCnumber() {
        return CCnumber;
    }
    
    public String getCCcode() {
        return CCcode;
    }
    
    public Date getCCexpirationdate() {
        return CCexpirationdate;
    }
    
    public YearMonth getCurrentTime() {
        return CurrentTime;
    }

    public LocalDate getCurrentTimeLong() {
        return CurrentTimeLong;
    }
    
    
    public DateTimeFormatter getFormatter() {
        return formatter;
    }
    
    public DateTimeFormatter getDateFormatter() {
        return Dateformatter;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setCCnumber(String nccnumber) {
         this.CCnumber = nccnumber;
    }
    
    public void setCCcode(String ncccode) {
         this.CCcode = ncccode;
    }
    
    public void setCCexpirationdate(Date nccexpirationdate) {
        this.CCexpirationdate = nccexpirationdate;
    } 
    
    public void completeBooking() {
        try {
            LoginBean.getUserLoggedIn().completeBooking();
        } catch (InvalidCreditCardDateException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

}

    
    

