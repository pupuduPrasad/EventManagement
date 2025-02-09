package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.custom.SponsorDAO;
import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.entity.JoinSponserEvent;
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
        System.out.println("sponser update");
        return CrudUtil.execute("update sponsors set name=?, contactNumber=?, address=? where sId=?", sponsor.getName(), sponsor.getContactNumber(), sponsor.getAddress(), sponsor.getSId());
    }

    public boolean delete(String sponsorId) throws SQLException {
        return CrudUtil.execute("delete from sponsors where sId=?", sponsorId);
    }

    public ArrayList<Sponsor> getAll() throws SQLException {
       return null;
    }
}
