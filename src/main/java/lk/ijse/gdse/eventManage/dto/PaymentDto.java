package lk.ijse.gdse.eventManage.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDto {
    private String pId;
    private Date date;
    private double amount;
    private String reservationId;
}
