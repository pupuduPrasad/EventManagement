package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.ReservationDto;
import lk.ijse.gdse.eventManage.dto.SponsorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SponsorBO extends SuperBo {
     String getNextId() throws SQLException;
     boolean save(SponsorDto sponsorDto) throws SQLException;
     boolean update(SponsorDto sponsorDto) throws SQLException;
     boolean delete(String rId) throws SQLException ;
     ArrayList<SponsorDto> getAll() throws SQLException ;
}
