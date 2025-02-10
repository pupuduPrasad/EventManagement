package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.FeedbackBO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.EventDAO;
import lk.ijse.gdse.eventManage.dao.custom.FeedbackDAO;
import lk.ijse.gdse.eventManage.dto.FeedbackDto;
import lk.ijse.gdse.eventManage.entity.Customer;
import lk.ijse.gdse.eventManage.entity.Feedback;

import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackBOImpl implements FeedbackBO {
    FeedbackDAO feedbackDAO= (FeedbackDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.FEEDBACK);
    @Override
    public String getNextId() throws SQLException {
        return feedbackDAO.getNextId();
    }

    @Override
    public boolean save(FeedbackDto entity) throws SQLException {
        return feedbackDAO.save(new Feedback(
                entity.getFId(),
                entity.getComment(),
                entity.getCustId()));
    }

    @Override
    public boolean update(FeedbackDto entity) throws SQLException {
        return feedbackDAO.update(new Feedback(
                entity.getFId(),
                entity.getComment(),
                entity.getCustId()));
    }

    @Override
    public boolean delete(String fId) throws SQLException {
        return feedbackDAO.delete(fId);
    }

    @Override
    public ArrayList<FeedbackDto> getAll() throws SQLException {
        ArrayList<Feedback> feedbackArrayList = feedbackDAO.getAll();
        ArrayList<FeedbackDto> feedbackDtoList = new ArrayList<>();
        for (Feedback feedback : feedbackArrayList) {
            feedbackDtoList.add(new FeedbackDto(
                    feedback.getFId(),
                    feedback.getComment(),
                    feedback.getCustId()
            ));

        }
        return feedbackDtoList;
    }
}
