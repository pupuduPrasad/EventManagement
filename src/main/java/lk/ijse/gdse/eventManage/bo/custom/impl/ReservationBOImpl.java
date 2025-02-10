package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.ReservationBO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.ReservationDAO;
import lk.ijse.gdse.eventManage.dto.ReservationDto;
import lk.ijse.gdse.eventManage.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {
    ReservationDAO reservationDAO= (ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESERVATION);

    @Override
    public String getNextId() throws SQLException {
        return reservationDAO.getNextId();
    }

    @Override
    public boolean save(ReservationDto entity) throws SQLException {
        return reservationDAO.save(new Reservation(
                entity.getRId()
                ,entity.getDate(),
                entity.getEventVenue(),
                entity.getEventId()));
    }

    @Override
    public boolean update(ReservationDto entity) throws SQLException {
        return reservationDAO.update(new Reservation(
                entity.getRId(),
                entity.getDate(),
                entity.getEventVenue(),
                entity.getEventId()));
    }

    @Override
    public boolean delete(String rId) throws SQLException {
        return reservationDAO.delete(rId);
    }

    @Override
    public ArrayList<ReservationDto> getAll() throws SQLException {
        ArrayList<Reservation> reservationArrayList=reservationDAO.getAll();
        ArrayList<ReservationDto> reservationDtoArrayList=new ArrayList<>();
        for(Reservation reservation:reservationArrayList){
            reservationDtoArrayList.add(new ReservationDto(
                    reservation.getRId(),
                    reservation.getDate(),
                    reservation.getEventVenue(),
                    reservation.getEventId()
            ));

        }
        return reservationDtoArrayList;
    }
}
