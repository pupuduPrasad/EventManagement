package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.dao.custom.FeedbackDAO;
import lk.ijse.gdse.eventManage.dto.FeedbackDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackDAOImpl implements FeedbackDAO {
    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select fId from feedback order by fId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("F%03d", newIdIndex);
        }
        return "F001";
    }

    public boolean save(FeedbackDto feedbackDto) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO feedback VALUES (?, ?, ?)",
                feedbackDto.getFId(),
                feedbackDto.getCustId(),
                feedbackDto.getComment()
        );
    }

    public boolean update(FeedbackDto feedbackDto) throws SQLException {
        return CrudUtil.execute(
                "UPDATE feedback SET custId=?, comment=? WHERE fId=?",
                feedbackDto.getCustId(),
                feedbackDto.getComment(),
                feedbackDto.getFId()
        );
    }

    public boolean delete(String feedbackId) throws SQLException {
        return CrudUtil.execute("DELETE FROM feedback WHERE fId=?", feedbackId);
    }

    public ArrayList<FeedbackDto> getAll() throws SQLException {
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
