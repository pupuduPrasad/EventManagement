package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.custom.SponsorDAO;
import lk.ijse.gdse.eventManage.dto.SponserAndEventDto;
import lk.ijse.gdse.eventManage.dto.SponsorDto;
import lk.ijse.gdse.eventManage.dao.CrudUtil;

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

    public boolean save(SponsorDto sponsorDto) throws SQLException {
        return CrudUtil.execute("insert into sponsors values(?,?,?,?)", sponsorDto.getSId(), sponsorDto.getName(), sponsorDto.getContactNumber(), sponsorDto.getAddress());
    }

    public boolean update(SponsorDto sponsorDto) throws SQLException {
        return CrudUtil.execute("update sponsors set name=?, contactNumber=?, address=? where sId=?", sponsorDto.getName(), sponsorDto.getContactNumber(), sponsorDto.getAddress(), sponsorDto.getSId());
    }

    public boolean delete(String sponsorId) throws SQLException {
        return CrudUtil.execute("delete from sponsors where sId=?", sponsorId);
    }

    public ArrayList<SponsorDto> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select s.sId, es.eventId, s.name, s.contactNumber, s.address, es.amount from sponsors s join eventsponsors es on s.sId = es.sId");

        ArrayList<SponserAndEventDto> sponserAndEventDtos = new ArrayList<>();

        while (rst.next()) {
            SponserAndEventDto sponserAndEventDto = new
                    SponserAndEventDto(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDouble(6));
            sponserAndEventDtos.add(sponserAndEventDto);
        }

        ArrayList<SponsorDto> returnList = new ArrayList<>();
        for(SponserAndEventDto sponserAndEventDto : sponserAndEventDtos) {
            SponsorDto sponsorDto = new SponsorDto();
            sponsorDto.setSId(sponserAndEventDto.getSId());
            sponsorDto.setEventID(sponserAndEventDto.getEventId());
            sponsorDto.setName(sponserAndEventDto.getName());
            sponsorDto.setContactNumber(sponserAndEventDto.getContactNumber());
            sponsorDto.setAddress(sponserAndEventDto.getAddress());
            sponsorDto.setAmount(sponserAndEventDto.getAmount());
            sponsorDto.setEmail(sponsorDto.getEmail());
            returnList.add(sponsorDto);
        }

        return returnList;
    }
}
