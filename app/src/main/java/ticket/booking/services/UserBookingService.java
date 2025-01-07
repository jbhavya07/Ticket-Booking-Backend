package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

        return Boolean.FALSE;
    }

    public List<Train> getTrains(String source, String destination){
        return trainService.searchTrains(source, destination);
    }
}
