package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.dao.CrudUtil;
import lk.ijse.gdse.eventManage.dao.custom.FeedbackDAO;
import lk.ijse.gdse.eventManage.entity.Feedback;

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

    public boolean save(Feedback feedback) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO feedback VALUES (?, ?, ?)",
                feedback.getFId(),
                feedback.getCustId(),
                feedback.getComment()
        );
    }

    public boolean update(Feedback feedback) throws SQLException {
        return CrudUtil.execute(
                "UPDATE feedback SET custId=?, comment=? WHERE fId=?",
                feedback.getCustId(),
                feedback.getComment(),
                feedback.getFId()
        );
    }

    public boolean delete(String feedbackId) throws SQLException {
        return CrudUtil.execute("DELETE FROM feedback WHERE fId=?", feedbackId);
    }
    @Override
    public ArrayList<Feedback> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM feedback");

        ArrayList<Feedback> feedbackList = new ArrayList<>();
        while (rst.next()) {
            Feedback feedback = new Feedback(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
            feedbackList.add(feedback);
        }
        return feedbackList;
    }
}
