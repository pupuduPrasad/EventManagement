package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.FeedbackDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FeedbackBO extends SuperBo {
    public String getNextId() throws SQLException;
    public boolean save(FeedbackDto feedbackDto) throws SQLException;
    public boolean update(FeedbackDto feedbackDto) throws SQLException;
    public boolean delete(String fId) throws SQLException ;
    public ArrayList<FeedbackDto> getAll() throws SQLException ;

}
