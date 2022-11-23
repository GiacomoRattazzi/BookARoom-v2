package bookaroom.v2.beans;

//import static bookaroom.v2.beans.UserController.findByUsername;
import bookaroom.v2.exceptions.DoesNotExistException;
import bookaroom.v2.models.User;
import bookaroom.v2.models.Comment;
import bookaroom.v2.database.MockDatabase;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/*
 * @author Team BookARoom
 */

@Named(value = "commentBean")
@SessionScoped
public class CommentBean implements Serializable{

 
    private String comment = "";
    private LocalDateTime now = LocalDateTime.now();
    private DateTimeFormatter formatterComment = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    

    public ArrayList<Comment> getComments() {
        return MockDatabase.getInstance().getComments();
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public LocalDateTime getTodayDate() {
        return now;
    }
    

    public void addCommentFromUser() {
        User user = LoginBean.getUserLoggedIn();
        MockDatabase.getInstance().addAComment(new Comment(user.getUsername()+": "+comment+" ("+getTodayDate().format(formatterComment)+")"));
        

    }
    
 
}
