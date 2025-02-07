package lk.ijse.gdse.eventManage.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventSponsors {
    private String eventId;
    private String sId;
    private double amount;
}
