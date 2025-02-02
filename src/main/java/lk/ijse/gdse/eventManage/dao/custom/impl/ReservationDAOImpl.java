package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.dao.custom.ReservationDAO;
import lk.ijse.gdse.eventManage.dto.ReservationDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select rId from reservation order by rId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("R%03d", newIdIndex);
        }
        return "R001";
    }

    public boolean save(ReservationDto reservationDto) throws SQLException {
        return CrudUtil.execute("insert into reservation values(?,?,?,?)", reservationDto.getRId(), reservationDto.getDate(), reservationDto.getEventVenue(), reservationDto.getEventId());
    }

    public boolean update(ReservationDto reservationDto) throws SQLException {
        return CrudUtil.execute("update reservation set date=?, eventVenue=?, eventId=? where rId=?", reservationDto.getDate(), reservationDto.getEventVenue(), reservationDto.getEventId(), reservationDto.getRId());
    }

    public boolean delete(String rId) throws SQLException {
        return CrudUtil.execute("delete from reservation where rId=?", rId);
    }

    public ArrayList<ReservationDto> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from reservation");
        ArrayList<ReservationDto> reservationDtos = new ArrayList<>();

        while (rst.next()) {
            ReservationDto reservationDto = new ReservationDto(rst.getString(1), rst.getDate(2), rst.getString(3), rst.getString(4));
            reservationDtos.add(reservationDto);
        }
        return reservationDtos;
    }
}
