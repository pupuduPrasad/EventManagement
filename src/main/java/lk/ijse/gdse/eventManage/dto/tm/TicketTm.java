package lk.ijse.gdse.eventManage.dto.tm;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TicketTm {
    private String ticketId;
    private Double price;
    private String custId;
    private String eventId;
}
