package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.custom.CustomerDAO;
import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.entity.Customer;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT custId FROM customer ORDER BY custId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int id = Integer.parseInt(substring) + 1;
            return String.format("C%03d", id);
        }
        return "C001";
    }

    public boolean save(Customer customer) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO customer VALUES (?, ?, ?)",
                customer.getCustId(),
                customer.getName(),
                customer.getCoNumber()
        );
    }

    public boolean update(Customer customer) throws SQLException {
        return CrudUtil.execute(
                "UPDATE customer SET name=? ,coNumber =? WHERE custId=?",
                customer.getName(),
                String.valueOf(customer.getCoNumber()),
                customer.getCustId()
                );
    }

    public boolean delete(String custId) throws SQLException {
        return CrudUtil.execute("DELETE FROM customer WHERE custId=?", custId);
    }

    public ArrayList<Customer> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer");

        ArrayList<Customer> feedbackList = new ArrayList<>();
        while (rst.next()) {
            Customer customer = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            );
            feedbackList.add(customer);
        }
        return feedbackList;
    }

}
