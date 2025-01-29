package lk.ijse.gdse.eventManage.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeedbackDto {
    private String fId;
    private String comment;
    private String custId;

}
