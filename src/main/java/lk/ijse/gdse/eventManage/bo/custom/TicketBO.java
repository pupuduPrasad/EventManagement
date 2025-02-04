package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.CustomerDto;
import lk.ijse.gdse.eventManage.dto.TicketDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TicketBO extends SuperBo {
    public String getNextId() throws SQLException;
    public boolean save(TicketDto ticketDto) throws SQLException;
    public boolean update(TicketDto ticketDto) throws SQLException;
    public boolean delete(String ticketId) throws SQLException ;
    public ArrayList<TicketDto> getAll() throws SQLException ;
}
