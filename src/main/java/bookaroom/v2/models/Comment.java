package bookaroom.v2.models;

/*
 * @author Team BookARoom
 */

public class Comment {
    private String comment;
    private Integer rating;
    
    public Comment(String comment, Integer rating) {
            this.comment = comment;
            this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
       }
    
    public Integer getRating(){
        return rating;
    }
    
    public Integer setRating(){
        this.rating = rating;
        return null;  /* Error shows without this line*/
        }

    
    @Override
    public String toString() {
        return "\n" +"\""+comment+"\"";
       
    }
    }
