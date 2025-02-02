package lk.ijse.gdse.eventManage.dao;

import lk.ijse.gdse.eventManage.dto.SponserAndEventDto;
import lk.ijse.gdse.eventManage.dto.SponsorDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class
SponsorModel {
    public String getNextSponsortId() throws SQLException {
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

    public boolean saveSponsor(SponsorDto sponsorDto) throws SQLException {
        return CrudUtil.execute("insert into sponsors values(?,?,?,?)", sponsorDto.getSId(), sponsorDto.getName(), sponsorDto.getContactNumber(), sponsorDto.getAddress());
    }

    public boolean updateSponsor(SponsorDto sponsorDto) throws SQLException {
        return CrudUtil.execute("update sponsors set name=?, contactNumber=?, address=? where sId=?", sponsorDto.getName(), sponsorDto.getContactNumber(), sponsorDto.getAddress(), sponsorDto.getSId());
    }

    public boolean deleteSponsor(String sponsorId) throws SQLException {
        return CrudUtil.execute("delete from sponsors where sId=?", sponsorId);
    }

    public ArrayList<SponserAndEventDto> getAllSponsors() throws SQLException {
        ResultSet rst = CrudUtil.execute("select s.sId, es.eventId, s.name, s.contactNumber, s.address, es.amount from sponsors s join eventsponsors es on s.sId = es.sId");

        ArrayList<SponserAndEventDto> sponserAndEventDtos = new ArrayList<>();

        while (rst.next()) {
            SponserAndEventDto sponserAndEventDto = new SponserAndEventDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getDouble(6));
            sponserAndEventDtos.add(sponserAndEventDto);
        }
        return sponserAndEventDtos;
    }
}
