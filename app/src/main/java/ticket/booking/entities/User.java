package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)

public class User {
    private String name;
    private String password;
    private String hashedPassword;

    private List<Ticket> ticketsBooked;
    private String userId;


    //its a constructor(a parametrised constructor);
    public User(String name, String password, String hashedPassword, List<Ticket> ticketsBooked, String userId){
        this.name=name;
        this.password=password;
        this.hashedPassword=hashedPassword;
        this.ticketsBooked=ticketsBooked;
        this.userId= userId;

    }
    //a default constructor agar koi bhi value pass nhi kar rhe to ye use hoyega.
    public User(){}




    //These are the getters

    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public String getHashedPassword(){
        return hashedPassword;
    }
    public  List<Ticket> getTicketsBooked(){
        return  ticketsBooked;
    }
    public void printTickets(){
        for(int i=0; i<ticketsBooked.size(); i++){
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }

}
