package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.custom.SponsorEventDAO;
import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.entity.EventSponsors;
import lk.ijse.gdse.eventManage.entity.JoinSponserEvent;

import java.sql.SQLException;
import java.util.ArrayList;

public class SponsorEventDAOImpl implements SponsorEventDAO {
    @Override
    public String getNextId() throws SQLException {
        return null;
    }

    @Override
    public boolean save(EventSponsors entity) throws SQLException {
        return CrudUtil.execute("insert into eventsponsors values(?,?,?)",
                entity.getEventId(),
                entity.getSId(),
                entity.getAmount()
        );
    }

    @Override
    public boolean update(EventSponsors entity) throws SQLException {
        System.out.println("update in spnserevent");
        return CrudUtil.execute("update eventsponsors set sId=?, amount=? where eventId=?",
                entity.getSId(),
                entity.getAmount(),
                entity.getEventId()
                );
    }

    public boolean save(JoinSponserEvent JoinSponserEvent) throws SQLException {
        return false;
    }

    public boolean update(JoinSponserEvent JoinSponserEvent) throws SQLException {
            return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<EventSponsors> getAll() throws SQLException {
        return null;
    }


}
