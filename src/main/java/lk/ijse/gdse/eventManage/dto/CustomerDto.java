package lk.ijse.gdse.eventManage.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDto {
    private String custId;
    private String name;
    private int coNumber;
}
