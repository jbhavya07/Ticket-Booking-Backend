package ticket.booking.entities;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private String trainNo;
    private List<List<Integer>> trainSeats;
    private Map<String , String> stationTime;
    private List<String> stations;

    public Train(){}

    public Train(String trainId, String trainNo, List<List<Integer>> trainSeats, Map<String, String> stationTime, List<String> stations){
        this.trainId=trainId;
        this.trainNo=trainNo;
        this.trainSeats=trainSeats;
        this.stationTime=stationTime;
        this.stations=stations;
    }
    public String getTrainId(){
        return trainId;
    }
    public String getTrainNo(){
        return trainNo;
    }
    public void setTrainNo(String trainNo){
        this.trainNo=trainNo;
    }
    public void setTrainId(String trainId){
        this.trainId=trainId;
    }

    public Map<String, String> getStationTime() {
        return stationTime;
    }

    public void setStationTime(Map<String, String> stationTime){
        this.stationTime=stationTime;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations){
        this.stations=stations;
    }
    public String getTrainInfo(){
        return String.format("Train ID: %s Train No: %s", trainId, trainNo);

    }

}
