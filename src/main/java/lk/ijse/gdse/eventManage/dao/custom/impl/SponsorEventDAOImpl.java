package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.custom.SponsorDAO;
import lk.ijse.gdse.eventManage.dao.custom.SponsorEventDAO;
import lk.ijse.gdse.eventManage.dto.EventSponsorsDto;
import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.dto.SponserAndEventDto;
import lk.ijse.gdse.eventManage.dto.SponsorDto;
import lk.ijse.gdse.eventManage.entity.SponserAndEvent;

import java.sql.SQLException;
import java.util.ArrayList;

public class SponsorEventDAOImpl implements SponsorEventDAO {
    @Override
    public String getNextId() throws SQLException {
        return null;
    }

    public boolean save(SponserAndEvent sponserAndEvent) throws SQLException {
        return CrudUtil.execute("insert into eventsponsors values(?,?,?)", sponserAndEvent.getEventId(), sponserAndEvent.getSId(), sponserAndEvent.getAmount());
    }

    public boolean update(SponserAndEvent sponserAndEvent) throws SQLException {
        return CrudUtil.execute("update sponsors set eventId=?, amount=? where sId=?", sponserAndEvent.getEventId(), sponserAndEvent.getAmount(), sponserAndEvent.getSId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<SponserAndEvent> getAll() throws SQLException {
        return null;
    }


}
