package lk.ijse.gdse.eventManage.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Sponsor {
    private String sId;
    private String name;
    private String contactNumber;
    private String address;
    private String email;
    private Double amount;
    private String eventID;

    public Sponsor(String sponsorId, String sponsorName, String phoneNumber, String address) {
        this.sId = sponsorId;
        this.name = sponsorName;
        this.contactNumber = phoneNumber;
        this.address = address;

    }

    public Sponsor(String sId, String name, String contactNumber, String address, String email, Double amount, String eventID) {
        this.sId = sId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
        this.email = email;
        this.amount = amount;
        this.eventID = eventID;
    }
}
