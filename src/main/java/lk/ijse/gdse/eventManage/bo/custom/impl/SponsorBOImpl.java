package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.SponsorBO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.SponsorDAO;
import lk.ijse.gdse.eventManage.dto.SponsorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class SponsorBOImpl implements SponsorBO {
    private final SponsorDAO sponsorDAO= (SponsorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SPONSOR);
    @Override
    public String getNextId() throws SQLException {
        return sponsorDAO.getNextId();
    }

    @Override
    public boolean save(SponsorDto sponsorDto) throws SQLException {
        return sponsorDAO.save(sponsorDto);
    }

    @Override
    public boolean update(SponsorDto sponsorDto) throws SQLException {
        return sponsorDAO.update(sponsorDto);
    }

    @Override
    public boolean delete(String rId) throws SQLException {
        return sponsorDAO.delete(rId);
    }

    @Override
    public ArrayList<SponsorDto> getAll() throws SQLException {
        return sponsorDAO.getAll();
    }
}
