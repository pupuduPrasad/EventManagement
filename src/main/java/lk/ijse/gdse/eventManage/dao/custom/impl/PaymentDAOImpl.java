package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.dao.custom.PaymentDAO;
import lk.ijse.gdse.eventManage.dto.PaymentDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select pId from payment order by pId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("P%03d", newIdIndex);
        }
        return "P001";
    }

    public boolean save(PaymentDto paymentDto) throws SQLException {
        return CrudUtil.execute("insert into payment values(?,?,?,?)", paymentDto.getPId(), paymentDto.getDate(), paymentDto.getAmount(), paymentDto.getReservationId());
    }

    public boolean update(PaymentDto paymentDto) throws SQLException {
        return CrudUtil.execute("update payment set date=?, amount=?, reservationId=? where pId=?", paymentDto.getDate(), paymentDto.getAmount(), paymentDto.getReservationId(), paymentDto.getPId());
    }

    public boolean delete(String paymentId) throws SQLException {
        return CrudUtil.execute("delete from payment where pId=?", paymentId);
    }

    public ArrayList<PaymentDto> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from payment");

        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        while (rst.next()) {
            PaymentDto paymentDto = new PaymentDto(rst.getString(1), rst.getDate(2), rst.getDouble(3), rst.getString(4));
            paymentDtos.add(paymentDto);
        }
        return paymentDtos;
    }
}
