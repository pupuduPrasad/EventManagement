package lk.ijse.gdse.eventManage.dto.tm;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SponsorTm {
    private String sId;
    private String eventId;
    private String name;
    private String contactNumber;
    private String address;
    private double amount;
}
