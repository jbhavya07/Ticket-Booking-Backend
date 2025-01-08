package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {

    private User user;

    private List<User> userList;
    private ObjectMapper objMapper= new ObjectMapper();

    private static final String USERS_PATH= "app/src/main/java/ticket/booking/localDb/users.json";
    public UserBookingService(User user1) throws IOException {
        this.user= user1;

        loadUsers();
        
    }

    public  UserBookingService() throws IOException{
        loadUsers();
    }

    public List<User> loadUsers() throws IOException{
        File users= new File(USERS_PATH);
        return objMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean loginUser(){
        Optional<User> foundUser= userList.stream().filter(user1 -> {
            return user1.getName().equalsIgnoreCase(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();

    }
    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch(IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException{
        File userFile=new  File(USERS_PATH);
        objMapper.writeValue(userFile, userList);
    }

    public void fetchBooking(){
        user.printTickets();
    }
    public  Boolean cancelBooking(String ticketId){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the ticket id to cancel");
        ticketId=s.next();

        if(ticketId==null || ticketId.isEmpty()){
            System.out.println("Ticket ID cannot be null or empty!!");
            return Boolean.FALSE;
        }
        String finalTicketId1=ticketId;
        boolean removed=user.getTicketsBooked().removeIf(Ticket-> Ticket.getTicketId().equals(finalTicketId1));
        if(removed){
            System.out.println("Ticket with Id "+ticketId+"has been canceled.");
            return Boolean.TRUE;
        }else{
            System.out.println("No ticket found with ID "+ticketId);
            return Boolean.FALSE;
        }

    }

    public List<Train> getTrains(String source, String destination){
        try{
            TrainBookingService trainBookingService= new TrainBookingService();
            return trainBookingService.searchTrains(source, destination);
        }catch(IOException ex){
            return new ArrayList<>();
        }
    }
    public  List<List<Integer>> fetchSeats(Train train){
        return train.getTrainSeats();
    }
    public boolean bookTrainSeat(Train train, int row, int seat){
        try{
            TrainBookingService trainBookingService=new TrainBookingService();
            List<List<Integer>>seats=train.getTrainSeats();
            if(row>=0 && row<seats.size() && seat>=0 && seat < seats.get(row).size()){
                if(seats.get(row).get(seat)==0){
                    seats.get(row).set(seat, 1);
                    train.setTrainSeats(seats);
                    trainBookingService.addTrain(train);
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }catch(IOException ex){
            return Boolean.FALSE;
        }
    }
}
