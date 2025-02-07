package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.EventBo;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.EventDAO;
import lk.ijse.gdse.eventManage.dto.CustomerDto;
import lk.ijse.gdse.eventManage.dto.EventDto;
import lk.ijse.gdse.eventManage.entity.Customer;
import lk.ijse.gdse.eventManage.entity.Event;

import java.sql.SQLException;
import java.util.ArrayList;

public class EventBOImpl implements EventBo {
    private final EventDAO eventDAO= (EventDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EVENT);
    @Override
    public String getNextId() throws SQLException {
        return eventDAO.getNextId();
    }

    @Override
    public boolean save(EventDto entity) throws SQLException {
        return eventDAO.save(new Event(
                entity.getEventId(),
                entity.getEventName(),
                entity.getEventFaculty(),
                entity.getDescription(),
                entity.getDate(),
                entity.getTime()));
    }

    @Override
    public boolean update(EventDto entity) throws SQLException {
        return eventDAO.update(new Event(
                entity.getEventId(),
                entity.getEventName(),
                entity.getEventFaculty(),
                entity.getDescription(),
                entity.getDate(),
                entity.getTime()));
    }

    @Override
    public boolean delete(String eventId) throws SQLException {
        return eventDAO.delete(eventId);
    }

    @Override
    public ArrayList<EventDto> getAll() throws SQLException {
        ArrayList<Event> eventArrayList = eventDAO.getAll();
        ArrayList<EventDto> eventDtoArrayList = new ArrayList<>();
        for (Event event : eventArrayList) {
            EventDto eventDto = new EventDto();
            eventDto.setEventId(event.getEventId());
            eventDto.setEventName(event.getEventName());
            eventDto.setEventFaculty(event.getEventFaculty());
            eventDto.setDescription(event.getDescription());
            eventDto.setDate(event.getDate());
            eventDto.setTime(event.getTime());
            eventDtoArrayList.add(eventDto);
        }
        return eventDtoArrayList;
    }
}
