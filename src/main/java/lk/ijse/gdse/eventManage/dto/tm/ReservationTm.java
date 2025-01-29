package lk.ijse.gdse.eventManage.dto.tm;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationTm extends SponsorTm {
    private String rId;
    private Date date;
    private String eventVenue;
    private String eventId;
}
