package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.BOFactory;
import lk.ijse.gdse.eventManage.bo.custom.ReservationBO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.ReservationDAO;
import lk.ijse.gdse.eventManage.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.gdse.eventManage.dto.ReservationDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {
    private final ReservationDAO reservationDAO= (ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESERVATION);

    @Override
    public String getNextId() throws SQLException {
        return reservationDAO.getNextId();
    }

    @Override
    public boolean save(ReservationDto reservationDto) throws SQLException {
        return reservationDAO.save(reservationDto);
    }

    @Override
    public boolean update(ReservationDto reservationDto) throws SQLException {
        return reservationDAO.update(reservationDto);
    }

    @Override
    public boolean delete(String rId) throws SQLException {
        return reservationDAO.delete(rId);
    }

    @Override
    public ArrayList<ReservationDto> getAll() throws SQLException {
        return  reservationDAO.getAll();
    }
}
