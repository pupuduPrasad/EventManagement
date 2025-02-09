package lk.ijse.gdse.eventManage.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO<T> extends SuperDAO {
     ArrayList<T> getAllSponser() throws SQLException;
}
