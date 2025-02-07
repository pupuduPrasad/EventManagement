package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.dao.custom.TicketDAO;
import lk.ijse.gdse.eventManage.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDAOImpl implements TicketDAO {
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select ticketId from ticket order by ticketId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("T%03d", newIdIndex);
        }
        return "T001";
    }

    public boolean save(Ticket ticket) throws SQLException {
        return CrudUtil.execute("insert into ticket values(?,?,?,?)", ticket.getTicketId(), ticket.getPrice(), ticket.getCustId(), ticket.getEventId());
    }

    public boolean update(Ticket ticket) throws SQLException {
        return CrudUtil.execute("update ticket set price=?, custId=?, eventId=? where ticketId=?", ticket.getPrice(), ticket.getCustId(), ticket.getEventId(), ticket.getTicketId());
    }

    public boolean delete(String ticketId) throws SQLException {
        return CrudUtil.execute("delete from ticket where ticketId=?", ticketId);
    }

    public ArrayList<Ticket> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from ticket");

        ArrayList<Ticket> ticketDtos = new ArrayList<>();

        while (rst.next()) {
            Ticket ticket = new Ticket(rst.getString(1), rst.getDouble(2), rst.getString(3), rst.getString(4));
            ticketDtos.add(ticket);
        }
        return ticketDtos;
    }
}
