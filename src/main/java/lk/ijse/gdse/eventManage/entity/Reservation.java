package lk.ijse.gdse.eventManage.entity;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation {
    private String rId;
    private Date date;
    private String eventVenue;
    private String eventId;
}
