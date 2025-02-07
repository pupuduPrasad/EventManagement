package lk.ijse.gdse.eventManage.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ticket {
    private String ticketId;
    private Double price;
    private String custId;
    private String eventId;
}
