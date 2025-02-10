package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.TicketBO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.TicketDAO;
import lk.ijse.gdse.eventManage.dto.TicketDto;
import lk.ijse.gdse.eventManage.entity.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;

public class TicketBOImpl implements TicketBO {
    TicketDAO ticketDAO= (TicketDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TICKET);
    @Override
    public String getNextId() throws SQLException {
        return ticketDAO.getNextId();
    }

    @Override
    public boolean save(TicketDto entity) throws SQLException {
        return ticketDAO.save(new Ticket(
                entity.getTicketId(),
                entity.getPrice(),
                entity.getCustId(),
                entity.getEventId()));
    }

    @Override
    public boolean update(TicketDto entity) throws SQLException {
        return ticketDAO.update(new Ticket(
                entity.getTicketId(),
                entity.getPrice(),
                entity.getCustId(),
                entity.getEventId()));
    }

    @Override
    public boolean delete(String ticketId) throws SQLException {
        return ticketDAO.delete(ticketId);
    }

    @Override
    public ArrayList<TicketDto> getAll() throws SQLException {
        ArrayList<Ticket> tickets = ticketDAO.getAll();
        ArrayList<TicketDto> ticketDtos = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticketDtos.add(new TicketDto(
                    ticket.getTicketId(),
                    ticket.getPrice(),
                    ticket.getCustId(),
                    ticket.getEventId()
            ));
        }
        return ticketDtos;
    }
}
