package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.EventBo;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.CustomerDAO;
import lk.ijse.gdse.eventManage.dao.custom.EventDAO;
import lk.ijse.gdse.eventManage.dao.custom.impl.EventDAOImpl;
import lk.ijse.gdse.eventManage.dto.EventDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class EventBOImpl implements EventBo {
    private final EventDAO eventDAO= (EventDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EVENT);
 /*   EventDAO eventDAO= new EventDAOImpl();*/

    @Override
    public String getNextId() throws SQLException {
        return eventDAO.getNextId();
    }

    @Override
    public boolean save(EventDto eventDto) throws SQLException {
        return eventDAO.save(eventDto);
    }

    @Override
    public boolean update(EventDto eventDto) throws SQLException {
        return eventDAO.update(eventDto);
    }

    @Override
    public boolean delete(String eventId) throws SQLException {
        return eventDAO.delete(eventId);
    }

    @Override
    public ArrayList<EventDto> getAll() throws SQLException {
        return eventDAO.getAll();
    }
}
