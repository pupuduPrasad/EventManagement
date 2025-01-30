package lk.ijse.gdse.eventManage.dao;

import lk.ijse.gdse.eventManage.dto.ReservationDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationModel {
    public String getNextReservationId() throws SQLException {
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

    public boolean saveReservation(ReservationDto reservationDto) throws SQLException {
        return CrudUtil.execute("insert into reservation values(?,?,?,?)", reservationDto.getRId(), reservationDto.getDate(), reservationDto.getEventVenue(), reservationDto.getEventId());
    }

    public boolean updateReservation(ReservationDto reservationDto) throws SQLException {
        return CrudUtil.execute("update reservation set date=?, eventVenue=?, eventId=? where rId=?", reservationDto.getDate(), reservationDto.getEventVenue(), reservationDto.getEventId(), reservationDto.getRId());
    }

    public boolean deleteReservation(String reservationId) throws SQLException {
        return CrudUtil.execute("delete from reservation where rId=?", reservationId);
    }

    public ArrayList<ReservationDto> getAllReservation() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from reservation");

        ArrayList<ReservationDto> reservationDtos = new ArrayList<>();

        while (rst.next()) {
            ReservationDto reservationDto = new ReservationDto(rst.getString(1), rst.getDate(2), rst.getString(3), rst.getString(4));
            reservationDtos.add(reservationDto);
        }
        return reservationDtos;
    }
}
