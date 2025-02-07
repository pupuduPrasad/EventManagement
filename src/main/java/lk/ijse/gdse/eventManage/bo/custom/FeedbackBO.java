package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.FeedbackDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FeedbackBO extends SuperBo {
     String getNextId() throws SQLException;
     boolean save(FeedbackDto feedbackDto) throws SQLException;
     boolean update(FeedbackDto feedbackDto) throws SQLException;
     boolean delete(String fId) throws SQLException ;
     ArrayList<FeedbackDto> getAll() throws SQLException ;

}
