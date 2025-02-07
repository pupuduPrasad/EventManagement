package lk.ijse.gdse.eventManage.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class SponserAndEventDto {
    private String sId;
    private String eventId;
    private String name;
    private String contactNumber;
    private String address;
    private double amount;


    public SponserAndEventDto(String sponsorId,String eventID , String sponsorName, String phoneNumber, String address , double amount) {
        this.sId = sponsorId;
        this.name = sponsorName;
        this.contactNumber = phoneNumber;
        this.address = address;
        this.eventId = eventID;
        this.amount = amount;
    }

    public SponserAndEventDto(String eventId, String sId, double amount) {
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
