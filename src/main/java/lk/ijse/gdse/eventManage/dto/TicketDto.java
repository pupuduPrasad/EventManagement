package lk.ijse.gdse.eventManage.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TicketDto {
    private String ticketId;
    private Double price;
    private String custId;
    private String eventId;
}
