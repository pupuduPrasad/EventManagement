package lk.ijse.gdse.eventManage.bo.custom.impl;
import lk.ijse.gdse.eventManage.bo.custom.CustomerBO;
import lk.ijse.gdse.eventManage.dao.CrudDAO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.CustomerDAO;
import lk.ijse.gdse.eventManage.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.gdse.eventManage.dto.CustomerDto;
import java.sql.SQLException;
import java.util.ArrayList;
public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    @Override
    public String getNextId() throws SQLException {
        return customerDAO.getNextId();
    }
    @Override
    public boolean save(CustomerDto customerDto) throws SQLException {
        return customerDAO.save(customerDto);
    }
    @Override
    public boolean update(CustomerDto customerDto) throws SQLException {
        return customerDAO.update(customerDto);
    }
    @Override
    public boolean delete(String custId) throws SQLException {
        return customerDAO.delete(custId);
    }
    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException {
        return customerDAO.getAll();
    }

}