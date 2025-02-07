package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.FeedbackDto;
import lk.ijse.gdse.eventManage.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBo {
     String getNextId() throws SQLException;
     boolean save(PaymentDto paymentDto) throws SQLException;
     boolean update(PaymentDto paymentDto) throws SQLException;
     boolean delete(String pId) throws SQLException ;
     ArrayList<PaymentDto> getAll() throws SQLException ;
}
