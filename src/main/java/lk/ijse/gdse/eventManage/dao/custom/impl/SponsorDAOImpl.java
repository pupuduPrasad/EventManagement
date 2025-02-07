package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.custom.SponsorDAO;
import lk.ijse.gdse.eventManage.dto.SponserAndEventDto;
import lk.ijse.gdse.eventManage.dto.SponsorDto;
import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.entity.SponserAndEvent;
import lk.ijse.gdse.eventManage.entity.Sponsor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SponsorDAOImpl implements SponsorDAO {
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select sId from sponsors order by sId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("S%03d", newIdIndex);
        }
        return "S001";
    }

    public boolean save(Sponsor sponsor) throws SQLException {
        return CrudUtil.execute("insert into sponsors values(?,?,?,?)", sponsor.getSId(), sponsor.getName(), sponsor.getContactNumber(), sponsor.getAddress());
    }

    public boolean update(Sponsor sponsor) throws SQLException {
        return CrudUtil.execute("update sponsors set name=?, contactNumber=?, address=? where sId=?", sponsor.getName(), sponsor.getContactNumber(), sponsor.getAddress(), sponsor.getSId());
    }

    public boolean delete(String sponsorId) throws SQLException {
        return CrudUtil.execute("delete from sponsors where sId=?", sponsorId);
    }

    public ArrayList<Sponsor> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select s.sId, es.eventId, s.name, s.contactNumber, s.address, es.amount from sponsors s join eventsponsors es on s.sId = es.sId");

        ArrayList<SponserAndEvent> sponserAndEvents = new ArrayList<>();

        while (rst.next()) {
            SponserAndEvent sponserAndEvent = new
                    SponserAndEvent(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDouble(6));
            sponserAndEvents.add(sponserAndEvent);
        }

        ArrayList<Sponsor> returnList = new ArrayList<>();
        for(SponserAndEvent sponserAndEvent : sponserAndEvents) {
            Sponsor sponsor = new Sponsor();
            sponsor.setSId(sponserAndEvent.getSId());
            sponsor.setEventID(sponserAndEvent.getEventId());
            sponsor.setName(sponserAndEvent.getName());
            sponsor.setContactNumber(sponserAndEvent.getContactNumber());
            sponsor.setAddress(sponserAndEvent.getAddress());
            sponsor.setAmount(sponserAndEvent.getAmount());
            sponsor.setEmail(sponsor.getEmail());
            returnList.add(sponsor);
        }

        return returnList;
    }
}
