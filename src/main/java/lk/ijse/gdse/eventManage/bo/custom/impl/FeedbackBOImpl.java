package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.FeedbackBO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.FeedbackDAO;
import lk.ijse.gdse.eventManage.dto.FeedbackDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackBOImpl implements FeedbackBO {
    private final FeedbackDAO feedbackDAO= (FeedbackDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.FEEDBACK);
    @Override
    public String getNextId() throws SQLException {
        return feedbackDAO.getNextId();
    }

    @Override
    public boolean save(FeedbackDto feedbackDto) throws SQLException {
        return feedbackDAO.save(feedbackDto);
    }

    @Override
    public boolean update(FeedbackDto feedbackDto) throws SQLException {
        return feedbackDAO.update(feedbackDto);
    }

    @Override
    public boolean delete(String fId) throws SQLException {
        return feedbackDAO.delete(fId);
    }

    @Override
    public ArrayList<FeedbackDto> getAll() throws SQLException {
        return feedbackDAO.getAll();
    }
}
