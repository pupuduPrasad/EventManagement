package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.TicketBO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.TicketDAO;
import lk.ijse.gdse.eventManage.dto.TicketDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class TicketBOImpl implements TicketBO {
    private final TicketDAO ticketDAO= (TicketDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TICKET);

    @Override
    public String getNextId() throws SQLException {
        return ticketDAO.getNextId();
    }

    @Override
    public boolean save(TicketDto ticketDto) throws SQLException {
        return ticketDAO.save(ticketDto);
    }

    @Override
    public boolean update(TicketDto ticketDto) throws SQLException {
        return ticketDAO.update(ticketDto);
    }

    @Override
    public boolean delete(String ticketId) throws SQLException {
        return ticketDAO.delete(ticketId);
    }

    @Override
    public ArrayList<TicketDto> getAll() throws SQLException {
        return ticketDAO.getAll();
    }
}
