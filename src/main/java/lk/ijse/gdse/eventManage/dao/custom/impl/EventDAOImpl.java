package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.dao.custom.EventDAO;
import lk.ijse.gdse.eventManage.entity.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventDAOImpl implements EventDAO {
    @Override
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

    public boolean save(Event event) throws SQLException {
        return CrudUtil.execute("insert into event values(?,?,?,?,?,?)",
                event.getEventId(),
                event.getEventName(),
                event.getEventFaculty(),
                event.getDescription(),
                event.getDate(),
                event.getTime());
    }

    public boolean update(Event event) throws SQLException {
        return CrudUtil.execute("update event set eventName=?, eventFaculty=?, description=?, date=?, time=? where eventId=?",
                event.getEventName(),
                event.getEventFaculty(),
                event.getDescription(),
                event.getDate(),
                event.getTime(),
                event.getEventId());
    }

    public boolean delete(String eventId) throws SQLException {
        return CrudUtil.execute("delete from event where eventId=?", eventId);
    }

    public ArrayList<Event> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from event");

        ArrayList<Event> customers = new ArrayList<>();

        while (rst.next()) {
            Event event = new Event(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getString(6));
            customers.add(event);
        }
        return customers;
    }
}
