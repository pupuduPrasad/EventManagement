package lk.ijse.gdse.eventManage.entity;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {
    private String pId;
    private Date date;
    private double amount;
    private String reservationId;
}
