/*
 * This source file was generated by the Gradle 'init' task
 */
package ticket.booking;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.services.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;

public class App {


    public static void main(String[] args) {
        System.out.println("Running Train Booking System");
        Scanner scanner= new Scanner(System.in);
        int option=0;
        UserBookingService userBookingService;
        try{
            userBookingService=new UserBookingService();
        }catch(IOException ex){
            System.out.println("There is something wrong"+ ex.getMessage());
            return;
        }

        //these will give the options
        //this is'nt running shit
        //heeloo i am backk
        //ek din kuch bada banaunga
        //++
        //;/'
        //jasbs tumkeomdenkasoih skla
//        sksdbhudaik
        //jjjiikkjj
        //kkmmnnbb
        //u
        //rox
        //kaj
        //vv
        //ll
        //smooth operator
        //helloo paaji
        //ki haal
        //dckmdcimd
        //knsjf
        //sjbjs
        //asjja
        //jjjnj\/
        //sldhs;
        //sdkbdjk
        //dksj
        //njjnj

        //lako
        //ajks

        //skjasgf
        //afkj
//        asdk
        //saihd
        //askjdjhl
        //kdshfgdasj
        while(option !=7){
            System.out.println("Choose Option:");
            System.out.println("1. SignUp");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");
            option = scanner.nextInt();
            Train trainSelectedForBooking=new Train();
            switch(option){
                case 1:
                    System.out.println("Enter the username to signup:");
                    String nameToSignUp= scanner.next();
                    System.out.println("Enter the password to signup:");
                    String passwordToSignUp= scanner.next();
                    User userToSignUp= new User(nameToSignUp, passwordToSignUp, UserServiceUtil.hashPassword(passwordToSignUp), new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUp(userToSignUp);
                    break;

                case 2:
                    System.out.println("Enter the username to login:");
                    String nameToLogin = scanner.next();
                    System.out.println("Enter the password to login:");
                    String passwordToLogin= scanner.next();
                    User userToLogin= new User(nameToLogin, passwordToLogin, UserServiceUtil.hashPassword(passwordToLogin), new ArrayList<>(), UUID.randomUUID().toString());
                    try{
                        userBookingService=new UserBookingService(userToLogin);
                    }catch(IOException ex){
                        return;
                    }

                    break;

                case 3:
                    System.out.println("Fetching your Bookings");
                    userBookingService.fetchBooking();
                    break;

                case 4:
                    System.out.println("Enter source station:");
                    String source= scanner.next();
                    System.out.println("Enter destination station:");
                    String destination= scanner.next();
                    List<Train> trains= userBookingService.getTrains(source, destination);
                    int index=1;
                    for(Train t: trains){
                        System.out.println(index + " Train id: "+t.getTrainId());
                        for(Map.Entry<String, String> entry: t.getStationTime().entrySet()){
                            System.out.println("Station "+entry.getKey()+" time: "+entry.getValue());
                        }
                    }
                    System.out.println("Select a train by typing 1,2,3..");
                    trainSelectedForBooking=trains.get(scanner.nextInt());
                    break;

                case 5:
                    System.out.println("Select a seat out of these seats:");
                    List<List<Integer>> seats=userBookingService.fetchSeats(trainSelectedForBooking);
                    for(List<Integer> row:seats){
                        for(Integer val:row){
                            System.out.print(val+" ");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the seat by typing the row and column");
                    System.out.println("Enter the row:");
                    int row= scanner.nextInt();
                    System.out.println("Enter the column:");
                    int column= scanner.nextInt();
                    System.out.println("Booking your seat...");
                    Boolean booked =userBookingService.bookTrainSeat(trainSelectedForBooking, row, column);
                    if(booked.equals(Boolean.TRUE)){
                        System.out.println("Booked!");
                    }else {
                        System.out.println("Can't book this seat");
                    }
                    break;

                case 6:
                    System.out.println("Canceling the booking");
                    System.out.println("Enter the ticketID to cancel:");
                    String ticketIdToCancel=scanner.next();
                    Boolean isCanceled=userBookingService.cancelBooking(ticketIdToCancel);
                    if(isCanceled.equals(Boolean.TRUE)){
                        System.out.println("Your Booking is canceled");
                    }else{
                        System.out.println("Failed to cancel your booking");
                    }
                    break;

                case 7:
                    System.out.println("Exiting the train booking app");
                    break;

                default:
                    break;
            }
        }

    }
}
