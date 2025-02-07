package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.dao.custom.TicketDAO;
import lk.ijse.gdse.eventManage.dto.TicketDto;

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

    public boolean save(TicketDto ticketDto) throws SQLException {
        return CrudUtil.execute("insert into ticket values(?,?,?,?)", ticketDto.getTicketId(), ticketDto.getPrice(), ticketDto.getCustId(), ticketDto.getEventId());
    }

    public boolean update(TicketDto ticketDto) throws SQLException {
        return CrudUtil.execute("update ticket set price=?, custId=?, eventId=? where ticketId=?", ticketDto.getPrice(), ticketDto.getCustId(), ticketDto.getEventId(), ticketDto.getTicketId());
    }

    public boolean delete(String ticketId) throws SQLException {
        return CrudUtil.execute("delete from ticket where ticketId=?", ticketId);
    }

    public ArrayList<TicketDto> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from ticket");

        ArrayList<TicketDto> ticketDtos = new ArrayList<>();

        while (rst.next()) {
            TicketDto ticketDto = new TicketDto(rst.getString(1), rst.getDouble(2), rst.getString(3), rst.getString(4));
            ticketDtos.add(ticketDto);
        }
        return ticketDtos;
    }
}
