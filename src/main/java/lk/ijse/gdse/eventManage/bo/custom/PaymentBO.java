package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.FeedbackDto;
import lk.ijse.gdse.eventManage.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBo {
    public String getNextId() throws SQLException;
    public boolean save(PaymentDto paymentDto) throws SQLException;
    public boolean update(PaymentDto paymentDto) throws SQLException;
    public boolean delete(String pId) throws SQLException ;
    public ArrayList<PaymentDto> getAll() throws SQLException ;
}
