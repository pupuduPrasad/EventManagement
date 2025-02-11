package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.util.CrudUtil;
import lk.ijse.gdse.eventManage.dao.custom.PaymentDAO;
import lk.ijse.gdse.eventManage.entity.Payment;

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

    public boolean save(Payment payment) throws SQLException {
        return CrudUtil.execute("insert into payment values(?,?,?,?)",
                payment.getPId(),
                payment.getDate(),
                payment.getAmount(),
                payment.getReservationId());
    }

    public boolean update(Payment payment) throws SQLException {
        return CrudUtil.execute("update payment set date=?, amount=?, reservationId=? where pId=?",
                payment.getDate(),
                payment.getAmount(),
                payment.getReservationId(),
                payment.getPId());
    }

    public boolean delete(String paymentId) throws SQLException {
        return CrudUtil.execute("delete from payment where pId=?", paymentId);
    }

    public ArrayList<Payment> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from payment");

        ArrayList<Payment> paymentDtos = new ArrayList<>();

        while (rst.next()) {
            Payment payment = new Payment(
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getDouble(3),
                    rst.getString(4));
            paymentDtos.add(payment);
        }
        return paymentDtos;
    }
}
