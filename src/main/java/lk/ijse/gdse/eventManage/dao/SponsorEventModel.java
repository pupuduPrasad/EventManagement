package lk.ijse.gdse.eventManage.dao;

import lk.ijse.gdse.eventManage.dto.EventSponsorsDto;
import lk.ijse.gdse.eventManage.dao.CrudUtil;

import java.sql.SQLException;

public class SponsorEventModel {
    public boolean saveSponsor(EventSponsorsDto sponsorDto) throws SQLException {
        return CrudUtil.execute("insert into eventsponsors values(?,?,?)", sponsorDto.getEventId(), sponsorDto.getSId(), sponsorDto.getAmount());
    }

    public boolean updateSponsor(EventSponsorsDto sponsorDto) throws SQLException {
        return CrudUtil.execute("update sponsors set eventId=?, amount=? where sId=?", sponsorDto.getEventId(), sponsorDto.getAmount(), sponsorDto.getSId());
    }

}
