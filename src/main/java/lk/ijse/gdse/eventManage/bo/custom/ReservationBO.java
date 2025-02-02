package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.EventDto;
import lk.ijse.gdse.eventManage.dto.ReservationDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO extends SuperBo {
    public String getNextId() throws SQLException;
    public boolean save(ReservationDto reservationDto) throws SQLException;
    public boolean update(ReservationDto reservationDto) throws SQLException;
    public boolean delete(String rId) throws SQLException ;
    public ArrayList<ReservationDto> getAll() throws SQLException ;
}
