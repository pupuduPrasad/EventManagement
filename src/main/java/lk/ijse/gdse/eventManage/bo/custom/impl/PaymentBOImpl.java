package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.PaymentBO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.PaymentDAO;
import lk.ijse.gdse.eventManage.dto.PaymentDto;
import lk.ijse.gdse.eventManage.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    private final PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    @Override
    public String getNextId() throws SQLException {
        return paymentDAO.getNextId();
    }

    @Override
    public boolean save(PaymentDto entity) throws SQLException {
        return paymentDAO.save(new Payment(entity.getPId(),entity.getDate(),entity.getAmount(),entity.getReservationId()));
    }

    @Override
    public boolean update(PaymentDto entity) throws SQLException {
        return paymentDAO.update(new Payment(entity.getPId(),entity.getDate(),entity.getAmount(),entity.getReservationId()));
    }

    @Override
    public boolean delete(String pId) throws SQLException {
        return paymentDAO.delete(pId);
    }

    @Override
    public ArrayList<PaymentDto> getAll() throws SQLException {
        ArrayList<Payment> paymentArrayList = paymentDAO.getAll();
        ArrayList<PaymentDto> paymentDtoList = new ArrayList<>();
        for (Payment payment : paymentArrayList) {
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setPId(payment.getPId());
            paymentDto.setDate(payment.getDate());
            paymentDto.setAmount(payment.getAmount());
            paymentDto.setReservationId(payment.getReservationId());
            paymentDtoList.add(paymentDto);
        }
        return paymentDtoList;
    }
}
