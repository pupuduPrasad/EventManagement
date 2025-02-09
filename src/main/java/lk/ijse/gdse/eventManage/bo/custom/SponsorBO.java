package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.EventSponsorsDto;
import lk.ijse.gdse.eventManage.dto.JoinSponserEventDetailDto;
import lk.ijse.gdse.eventManage.dto.SponsorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SponsorBO extends SuperBo {
     String getNextId() throws SQLException;
     boolean save(ArrayList<SponsorDto>sponsorDtos, ArrayList<EventSponsorsDto>eventSponsorsDtos) throws SQLException;
     boolean update(ArrayList<SponsorDto>sponsorDtos, ArrayList<EventSponsorsDto>eventSponsorsDtos) throws SQLException;
     boolean delete(String rId) throws SQLException ;
     ArrayList<JoinSponserEventDetailDto> getAll() throws SQLException ;
}
