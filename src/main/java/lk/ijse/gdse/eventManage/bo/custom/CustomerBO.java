package lk.ijse.gdse.eventManage.bo.custom;
import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.CustomerDto;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBo {

     String getNextId() throws SQLException;
     boolean save(CustomerDto customerDto) throws SQLException;
     boolean update(CustomerDto customerDto) throws SQLException;
     boolean delete(String custId) throws SQLException ;
     ArrayList<CustomerDto> getAll() throws SQLException ;
}