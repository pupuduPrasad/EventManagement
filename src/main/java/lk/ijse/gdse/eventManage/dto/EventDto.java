package lk.ijse.gdse.eventManage.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventDto {
    private String eventId;
    private String eventName;
    private String eventFaculty;
    private String description;
    private Date date;
    private String time;
}
