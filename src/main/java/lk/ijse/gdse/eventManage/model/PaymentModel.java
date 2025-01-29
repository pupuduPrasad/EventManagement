package lk.ijse.gdse.eventManage.model;

import lk.ijse.gdse.eventManage.dto.PaymentDto;
import lk.ijse.gdse.eventManage.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {
    public String getNextPaymentId() throws SQLException {
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

    public boolean savePayment(PaymentDto paymentDto) throws SQLException {
        return CrudUtil.execute("insert into payment values(?,?,?,?)", paymentDto.getPId(), paymentDto.getDate(), paymentDto.getAmount(), paymentDto.getReservationId());
    }

    public boolean updatePayment(PaymentDto paymentDto) throws SQLException {
        return CrudUtil.execute("update payment set date=?, amount=?, reservationId=? where pId=?", paymentDto.getDate(), paymentDto.getAmount(), paymentDto.getReservationId(), paymentDto.getPId());
    }

    public boolean deletePayment(String paymentId) throws SQLException {
        return CrudUtil.execute("delete from payment where pId=?", paymentId);
    }

    public ArrayList<PaymentDto> getAllPayment() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from payment");

        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        while (rst.next()) {
            PaymentDto paymentDto = new PaymentDto(rst.getString(1), rst.getDate(2), rst.getDouble(3), rst.getString(4));
            paymentDtos.add(paymentDto);
        }
        return paymentDtos;
    }
}
