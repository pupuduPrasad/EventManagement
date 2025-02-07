package lk.ijse.gdse.eventManage.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class SponserAndEvent {
    private String sId;
    private String eventId;
    private String name;
    private String contactNumber;
    private String address;
    private double amount;


    public SponserAndEvent(String sponsorId, String eventID , String sponsorName, String phoneNumber, String address , double amount) {
        this.sId = sponsorId;
        this.name = sponsorName;
        this.contactNumber = phoneNumber;
        this.address = address;
        this.eventId = eventID;
        this.amount = amount;
    }

    public SponserAndEvent(String eventId, String sId, double amount) {
        this.sId = sId;
        this.eventId = eventId;
        this.amount = amount;
    }
//    public String getEventID() {
//        return eventId;
//    }
//
//    public void setEventID(String eventID) {
//        this.eventId = eventID;
//
//    }
}
