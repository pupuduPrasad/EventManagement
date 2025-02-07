package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.EventDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EventBo extends SuperBo {
     String getNextId() throws SQLException;
     boolean save(EventDto eventDto) throws SQLException;
     boolean update(EventDto eventDto) throws SQLException;
     boolean delete(String eventId) throws SQLException ;
     ArrayList<EventDto> getAll() throws SQLException ;
}
