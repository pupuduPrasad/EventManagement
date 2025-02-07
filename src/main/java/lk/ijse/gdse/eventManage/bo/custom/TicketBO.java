package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.CustomerDto;
import lk.ijse.gdse.eventManage.dto.TicketDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TicketBO extends SuperBo {
     String getNextId() throws SQLException;
     boolean save(TicketDto ticketDto) throws SQLException;
     boolean update(TicketDto ticketDto) throws SQLException;
     boolean delete(String ticketId) throws SQLException ;
     ArrayList<TicketDto> getAll() throws SQLException ;
}
