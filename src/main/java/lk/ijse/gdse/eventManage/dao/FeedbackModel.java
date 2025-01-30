package lk.ijse.gdse.eventManage.dao;

import lk.ijse.gdse.eventManage.dto.FeedbackDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackModel {

    public String getNextFeedbackId() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT fId FROM feedback ORDER BY fId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int id = Integer.parseInt(substring) + 1;
            return String.format("F%03d", id);
        }
        return "F001";
    }

    public boolean saveFeedback(FeedbackDto feedbackDto) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO feedback VALUES (?, ?, ?)",
                feedbackDto.getFId(),
                feedbackDto.getCustId(),
                feedbackDto.getComment()
        );
    }

    public boolean updateFeedback(FeedbackDto feedbackDto) throws SQLException {
        return CrudUtil.execute(
                "UPDATE feedback SET custId=?, comment=? WHERE fId=?",
                feedbackDto.getCustId(),
                feedbackDto.getComment(),
                feedbackDto.getFId()
        );
    }

    public boolean deleteFeedback(String feedbackId) throws SQLException {
        return CrudUtil.execute("DELETE FROM feedback WHERE fId=?", feedbackId);
    }

    public ArrayList<FeedbackDto> getAllFeedback() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM feedback");

        ArrayList<FeedbackDto> feedbackList = new ArrayList<>();
        while (rst.next()) {
            FeedbackDto feedbackDto = new FeedbackDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
            feedbackList.add(feedbackDto);
        }
        return feedbackList;
    }
}
