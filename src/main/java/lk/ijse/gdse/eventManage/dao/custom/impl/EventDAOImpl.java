package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.dao.custom.EventDAO;
import lk.ijse.gdse.eventManage.dto.EventDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventDAOImpl implements EventDAO {
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select eventId from event order by eventId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("E%03d", newIdIndex);
        }
        return "E001";
    }

    public boolean save(EventDto eventDto) throws SQLException {
        return CrudUtil.execute("insert into event values(?,?,?,?,?,?)", eventDto.getEventId(), eventDto.getEventName(), eventDto.getEventFaculty(), eventDto.getDescription(), eventDto.getDate(), eventDto.getTime());
    }

    public boolean update(EventDto eventDto) throws SQLException {
        return CrudUtil.execute("update event set eventName=?, eventFaculty=?, description=?, date=?, time=? where eventId=?", eventDto.getEventName(), eventDto.getEventFaculty(), eventDto.getDescription(), eventDto.getDate(), eventDto.getTime(), eventDto.getEventId());
    }

    public boolean delete(String eventId) throws SQLException {
        return CrudUtil.execute("delete from event where eventId=?", eventId);
    }

    public ArrayList<EventDto> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from event");

        ArrayList<EventDto> eventDtos = new ArrayList<>();

        while (rst.next()) {
            EventDto eventDto = new EventDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getDate(5), rst.getString(6));
            eventDtos.add(eventDto);
        }
        return eventDtos;
    }
}
