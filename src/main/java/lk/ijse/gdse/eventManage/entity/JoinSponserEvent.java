package lk.ijse.gdse.eventManage.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JoinSponserEvent {
    private String sId;
    private String eventId;
    private String name;
    private String contactNumber;
    private String address;
    private double amount;
}
