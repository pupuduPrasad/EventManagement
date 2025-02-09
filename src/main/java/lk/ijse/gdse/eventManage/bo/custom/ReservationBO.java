package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.ReservationDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO extends SuperBo {
     String getNextId() throws SQLException;
     boolean save(ReservationDto reservationDto) throws SQLException;
     boolean update(ReservationDto reservationDto) throws SQLException;
     boolean delete(String rId) throws SQLException ;
     ArrayList<ReservationDto> getAll() throws SQLException ;
}
