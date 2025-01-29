package lk.ijse.gdse.eventManage.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventSponsorsDto {
    private String eventId;
    private String sId;
    private double amount;
}
