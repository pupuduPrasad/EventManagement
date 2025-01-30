package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.CustomerDto;
import lk.ijse.gdse.eventManage.dto.EventDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EventBo extends SuperBo {
    public String getNextId() throws SQLException;
    public boolean save(EventDto eventDto) throws SQLException;
    public boolean update(EventDto eventDto) throws SQLException;
    public boolean delete(String eventId) throws SQLException ;
    public ArrayList<EventDto> getAll() throws SQLException ;
}
