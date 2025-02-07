package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.custom.SponsorDAO;
import lk.ijse.gdse.eventManage.dao.custom.SponsorEventDAO;
import lk.ijse.gdse.eventManage.dto.EventSponsorsDto;
import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.dto.SponserAndEventDto;
import lk.ijse.gdse.eventManage.dto.SponsorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class SponsorEventDAOImpl implements SponsorEventDAO {
    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    public boolean save(SponserAndEventDto sponsorDto) throws SQLException {
        return CrudUtil.execute("insert into eventsponsors values(?,?,?)", sponsorDto.getEventId(), sponsorDto.getSId(), sponsorDto.getAmount());
    }

    public boolean update(SponserAndEventDto sponsorDto) throws SQLException {
        return CrudUtil.execute("update sponsors set eventId=?, amount=? where sId=?", sponsorDto.getEventId(), sponsorDto.getAmount(), sponsorDto.getSId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<SponserAndEventDto> getAll() throws SQLException {
        return null;
    }


}
