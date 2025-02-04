package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.PaymentBO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.PaymentDAO;
import lk.ijse.gdse.eventManage.dto.FeedbackDto;
import lk.ijse.gdse.eventManage.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    private final PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    @Override
    public String getNextId() throws SQLException {
        return paymentDAO.getNextId();
    }

    @Override
    public boolean save(PaymentDto paymentDto) throws SQLException {
        return paymentDAO.save(paymentDto);
    }

    @Override
    public boolean update(PaymentDto paymentDto) throws SQLException {
        return paymentDAO.update(paymentDto);
    }

    @Override
    public boolean delete(String pId) throws SQLException {
        return paymentDAO.delete(pId);
    }

    @Override
    public ArrayList<PaymentDto> getAll() throws SQLException {
        return paymentDAO.getAll();
    }
}
