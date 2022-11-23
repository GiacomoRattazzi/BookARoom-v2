package bookaroom.v2.database;

import bookaroom.v2.models.Room;
import bookaroom.v2.models.User;
import bookaroom.v2.models.Comment;
import java.util.ArrayList;
import java.time.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.primefaces.component.rating.Rating;
/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Team BookARoom
 */
public class MockDatabase {
    
    private static MockDatabase instance;

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList<Comment> comments = new ArrayList<Comment>();
    private ArrayList<Rating> ratings = new ArrayList<Rating>;
    private HashMap<String, ArrayList<LocalDate>> bookedDates = new HashMap<String, ArrayList<LocalDate>>();
    private ArrayList<LocalDate> test = new ArrayList<LocalDate>();
    private ArrayList<LocalDate> test2 = new ArrayList<LocalDate>();
    private MockDatabase() {
 
        
 
        
        test.add(LocalDate.of(2022,12,11));
        test.add(LocalDate.of(2023,12,11));
        test.add(LocalDate.of(2024,12,11));
        bookedDates.put("Room 1", test);
       
        test2.add(LocalDate.of(2026,12,11));
        test2.add(LocalDate.of(2027,12,11));
        test2.add(LocalDate.of(2028,12,11));
        bookedDates.put("Room 2", test2);
        
        
    
        users.add(new User("jwang", "jingmin", "wang", "jingmin.wang@unil.ch", "1234","0000000000000000","123","10/26"));
        users.add(new User("gratt", "giacomo", "rattazzi", "giacomo.rattazzi@gmail.com", "1234", "0000000000000000","123","10/26"));
        users.add(new User("danes", "daniel", "do vale anes", "daniel.dovaleanes@gmail.com", "1234","0000000000000000","123","10/26"));
        users.add(new User("afarh", "ahmed", "farhat", "ahmed.farhat@gmail.com", "1234", "0000000000000000","123","10/26"));

    

        rooms.add(new Room("Room 1", 200, new ArrayList<String>() {
            {
                add("One double bed");
                add("Maximum capacity: 1"); 
                add("r001");
            }
        }, new ArrayList<> (Arrays.asList())
        ));
        rooms.add(new Room("Room 2", 250, new ArrayList<String>() {
            {
                add("Two double beds");
                add("Maximum capacity: 2");
                add("r002");
            }
        }, new ArrayList<String>() {
            {
                add(LocalDate.of(2012,12,11).toString());
            }
        }));
        rooms.add(new Room("Room 3", 280, new ArrayList<String>() {
            {
                add("2 beds");
                add("Maximum capacity: 4");
                add("r003");
            }
        }, new ArrayList<String>() {
            {
                add(LocalDate.of(2012,12,11).toString());
            }
        }));
        rooms.add(new Room("Room 4", 200, new ArrayList<String>() {
            {
                add("1 bed");
                add("Maximum capacity: 1");
                add("r004");
            }
        }, new ArrayList<String>() {
            {
                add(LocalDate.of(2012,12,11).toString());
            }
        }));

        rooms.add(new Room("Room 5", 250, new ArrayList<String>() {
            {
                add("2 beds");
                add("Maximum capacity: 2");
                add("r005");
            }
        }, new ArrayList<String>() {
            {
                add(LocalDate.of(2012,12,11).toString());
            }
        }));

        rooms.add(new Room("Room 6", 280, new ArrayList<String>() {
            {
                add("2 beds");
                add("Maximum capacity: 4");
                add("r006");
            }
        }, new ArrayList<String>() {
            {
                add(LocalDate.of(2012,12,11).toString());
            }
        }));
        rooms.add(new Room("Room 7", 200,  new ArrayList<String>() {
            {
                add("1 bed");
                add("Maximum capacity: 1");
                add("r007");
            }
        }, new ArrayList<String>() {
            {
                add(LocalDate.of(2012,12,11).toString());
            }
        }));
        rooms.add(new Room("Room 8", 250, new ArrayList<String>() {
            {
                add("2 beds");
                add("Maximum capacity: 2");
                add("r008");
            }
        }, new ArrayList<String>() {
            {
                add(LocalDate.of(2012,12,11).toString());
            }
        }));
        rooms.add(new Room("Room 9", 280, new ArrayList<String>() {
            {
                add("2 bunk beds");
                add("Maximum capacity: 4");
                add("r009");
            }
        }, new ArrayList<String>() {
            {
                add(LocalDate.of(2012,12,11).toString());
            }
        }));
        rooms.add(new Room("Room 10", 200, new ArrayList<String>() {
            {
                add("1 bed");
                add("Maximum capacity: 1");
                add("r010");
            }
        }, new ArrayList<String>() {
            {
                add(LocalDate.of(2012,12,11).toString());
            }
        }));
               
       
        comments.add(new Comment("Paul: The beds are comfortable. (08/10/2022)"));
        comments.add(new Comment("Marine: The bathroom is not very clean. (24/10/2022)"));
        
        ratings.add(new Rating());    
        
        }
    
     
    public static MockDatabase getInstance() {
        if (instance == null) {
            instance = new MockDatabase();
        }
        return instance;
    }
    public void addAUser(User user) {
        users.add(user);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
    
    public void addAComment(Comment comment) {
        comments.add(comment);
    }


    public void removeAUser(User user) {
        users.remove(user);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
    public ArrayList<Comment> getComments() {
        return comments;
    }
    
    public ArrayList<LocalDate> getTest() {
        return test;
    }
          
    
    public HashMap<String, ArrayList<LocalDate>> getBookedDates() {
        return bookedDates;
    }

}

