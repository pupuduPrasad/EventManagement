package lk.ijse.gdse.eventManage.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JoinSponserEventDetailDto {
    private String sId;
    private String eventId;
    private String name;
    private String contactNumber;
    private String address;
    private double amount;

}
