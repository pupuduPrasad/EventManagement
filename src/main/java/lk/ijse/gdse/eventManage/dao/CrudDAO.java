package lk.ijse.gdse.eventManage.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO {
     String getNextId() throws SQLException;
     boolean save(T dto) throws SQLException;
     boolean update(T dto) throws SQLException;
     boolean delete(String id) throws SQLException ;
     ArrayList<T> getAll() throws SQLException ;
}
