package lk.ijse.gdse.eventManage.model;

import lk.ijse.gdse.eventManage.db.DBConnection;
import lk.ijse.gdse.eventManage.dto.TicketDto;
import lk.ijse.gdse.eventManage.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketModel{
    public String getNextTicketId() throws SQLException {
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

    public boolean saveTicket(TicketDto ticketDto) throws SQLException {
        return CrudUtil.execute("insert into ticket values(?,?,?,?)", ticketDto.getTicketId(), ticketDto.getPrice(), ticketDto.getCustId(), ticketDto.getEventId());
    }

    public boolean updateTicket(TicketDto ticketDto) throws SQLException {
        return CrudUtil.execute("update ticket set price=?, custId=?, eventId=? where ticketId=?", ticketDto.getPrice(), ticketDto.getCustId(), ticketDto.getEventId(), ticketDto.getTicketId());
    }

    public boolean deleteTicket(String ticketId) throws SQLException {
        return CrudUtil.execute("delete from ticket where ticketId=?", ticketId);
    }

    public ArrayList<TicketDto> getAllTicket() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from ticket");

        ArrayList<TicketDto> ticketDtos = new ArrayList<>();

        while (rst.next()) {
            TicketDto ticketDto = new TicketDto(rst.getString(1), rst.getDouble(2), rst.getString(3), rst.getString(4));
            ticketDtos.add(ticketDto);
        }
        return ticketDtos;
    }
}
