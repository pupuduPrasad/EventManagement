package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.dao.custom.ReservationDAO;
import lk.ijse.gdse.eventManage.entity.Reservation;

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

    public boolean save(Reservation reservation) throws SQLException {
        return CrudUtil.execute("insert into reservation values(?,?,?,?)",
                reservation.getRId(),
                reservation.getDate(),
                reservation.getEventVenue(),
                reservation.getEventId());
    }

    public boolean update(Reservation reservation) throws SQLException {
        return CrudUtil.execute("update reservation set date=?, eventVenue=?, eventId=? where rId=?",
                reservation.getDate(),
                reservation.getEventVenue(),
                reservation.getEventId(),
                reservation.getRId());
    }

    public boolean delete(String rId) throws SQLException {
        return CrudUtil.execute("delete from reservation where rId=?", rId);
    }

    public ArrayList<Reservation> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from reservation");
        ArrayList<Reservation> reservationDtos = new ArrayList<>();

        while (rst.next()) {
            Reservation reservation = new Reservation(
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getString(3),
                    rst.getString(4));
            reservationDtos.add(reservation);
        }
        return reservationDtos;
    }
}
