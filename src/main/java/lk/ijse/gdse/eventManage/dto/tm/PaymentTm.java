package lk.ijse.gdse.eventManage.dto.tm;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentTm {
    private String pId;
    private Date date;
    private double amount;
    private String reservationId;
}
