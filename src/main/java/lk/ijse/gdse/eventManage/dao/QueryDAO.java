package lk.ijse.gdse.eventManage.dao;

import lk.ijse.gdse.eventManage.dto.SponserAndEventDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
     ArrayList<SponserAndEventDto> getAll() throws SQLException;
}
