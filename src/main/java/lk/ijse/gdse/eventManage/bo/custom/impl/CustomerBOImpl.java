package lk.ijse.gdse.eventManage.bo.custom.impl;
import lk.ijse.gdse.eventManage.bo.custom.CustomerBO;
import lk.ijse.gdse.eventManage.dao.CrudDAO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.CustomerDAO;
import lk.ijse.gdse.eventManage.dto.CustomerDto;
import lk.ijse.gdse.eventManage.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    @Override
    public String getNextId() throws SQLException {
        return customerDAO.getNextId();
    }
    @Override
    public boolean save(CustomerDto entity) throws SQLException {
        return customerDAO.save(new Customer(entity.getCustId() , entity.getName() , entity.getCoNumber())
        );
    }
    @Override
    public boolean update(CustomerDto entity) throws SQLException {
        return customerDAO.update(new Customer(entity.getCustId() , entity.getName() , entity.getCoNumber())
        );
    }
    @Override
    public boolean delete(String custId) throws SQLException {
        return customerDAO.delete(custId);
    }
    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException {
        ArrayList<Customer> customerList = customerDAO.getAll();
        ArrayList<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer customer : customerList) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustId(customer.getCustId());
            customerDto.setName(customer.getName());
            customerDto.setCoNumber(customer.getCoNumber());
            customerDtoList.add(customerDto);
        }
        return customerDtoList;
    }

}