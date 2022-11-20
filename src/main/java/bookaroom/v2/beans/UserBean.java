
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
    private String CCexpirationdate = "";
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
    
    private boolean ccNumberCorrect() throws InvalidCreditCardException {
        for (User user : MockDatabase.getInstance().getUsers()) {
            if (user.getCCnumber() !=16) {
                throw new InvalidCreditCardException("this shit wrong");
            }
        }
        return false;
    }

    private boolean ExpiredCC() throws InvalidCreditCardDateException {
        for (User user : MockDatabase.getInstance().getUsers()) {
            YearMonth userexpdateFormat = YearMonth.parse(user.getCCExpirationDate(), formatter); 
            boolean expired = CurrentTime.isBefore(userexpdateFormat);
                    if (expired==true) {
                        System.out.println("Credit Card is still valid.");
                    } else {
                        throw new InvalidCreditCardDateException("Credit Card has expired.");
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

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
        
    public String getCCnumber() {
        return CCnumber;
    }
    
    public String getCCCode() {
        return CCcode;
    }
    
    public String getCCExpirationDate() {
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
    
    public void setCCNumber(String nccnumber) {
                String code1 = "";
                boolean numCorrect = false;
                while(!numCorrect){

                    System.out.println("Update credit card number (16-digit number):"); 
                    code1 = sc.nextLine();
                    if (code1.length()!=16) {
                        System.out.println("The credit card number should a 16-digit number, yours is a "+code1.length()+"-digit number. Please enter it again");
                        }
                    else if (code1.length()==16)
                        numCorrect =true;
                nccnumber = code1;}
         this.CCnumber = nccnumber;
    }
    
    public void setCCcode(String ncccode) {
                String code2 = "";
                boolean codeCorrect = false;
                while(!codeCorrect){
                    System.out.println("Enter a verification code (3-digit number):");
                    code2 = sc.nextLine();
                    if (code2.length()!=3) {
                        System.out.println("The credit card number should a 3-digit number, yours is a "+code2.length()+"-digit number. Please enter it again");
                        }
                    else if (code2.length()==3)
                        codeCorrect =true;
                ncccode = code2;}
         this.CCcode = ncccode;
    }
    
    public void setCCExpirationDate(String nccexpirationdate) {
                        /*        boolean CCDateValid = false;
                                while(!CCDateValid){
                                System.out.println("Update expiration date (month/year => MM/yy):");
                                nccexpirationdate = sc.nextLine();
                        
                                try
                                {
                                    YearMonth ccexpdateFormat = YearMonth.parse(nccexpirationdate, UserController.getFormatter()); 
                                    System.out.println(nccexpirationdate+" is valid date format.");
                                    boolean valid = UserController.getCurrentTime().isBefore(ccexpdateFormat);
                            
                                    if (valid==true) {
                                        System.out.println("Credit Card is still valid.");
                                        CCDateValid = true;
                                    } 
                                    else {
                                        System.out.println("Credit Card has expired.");
                                    }
                                }
                                catch (DateTimeParseException e)
                                {
                                    System.out.println(nccexpirationdate+" is not a valid Date format.");
                                }
                                } */
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

    
    

