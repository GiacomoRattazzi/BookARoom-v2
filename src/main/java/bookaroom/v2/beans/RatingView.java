/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookaroom.v2.beans;

/**
 *
 * @author team BookARoom
 */
public class RatingView {
    private Integer rating1 = 5;
    private Integer rating2 = 2;
    private Integer rating3;
    
    public Integer getRating1(){
        return rating1;
    }
    
    public void setRating1(Integer rating1) {
        this.rating1 = rating1;
    }
    
     public Integer getRating2() {
        return rating2;
    }
 
    public void setRating2(Integer rating2) {
        this.rating2 = rating2;
    }
 
    public Integer getRating3() {
        return rating3;
    }
 
    public void setRating3(Integer rating3) {
        this.rating3 = rating3;
    }    
    
}
