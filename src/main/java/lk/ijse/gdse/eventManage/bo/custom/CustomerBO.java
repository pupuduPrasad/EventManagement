package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBo {
    public String getNextId() throws SQLException;
    public boolean save(CustomerDto customerDto) throws SQLException;
    public boolean update(CustomerDto customerDto) throws SQLException;
    public boolean delete(String custId) throws SQLException ;
    public ArrayList<CustomerDto> getAll() throws SQLException ;
    public ArrayList<CustomerDto> getAllFeedback() throws SQLException;

}
