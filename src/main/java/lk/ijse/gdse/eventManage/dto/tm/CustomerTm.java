package lk.ijse.gdse.eventManage.dto.tm;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerTm {
    private String custId;
    private String name;
    private int coNumber;
}
