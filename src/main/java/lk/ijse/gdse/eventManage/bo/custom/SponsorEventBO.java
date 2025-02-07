package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.EventSponsorsDto;

import java.sql.SQLException;

public interface SponsorEventBO extends SuperBo {
     boolean save(EventSponsorsDto sponserAndEventDto) throws SQLException;
     boolean update(EventSponsorsDto sponserAndEventDto) throws SQLException;
}
