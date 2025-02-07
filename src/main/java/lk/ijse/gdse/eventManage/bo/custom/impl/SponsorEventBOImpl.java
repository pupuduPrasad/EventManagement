package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.SponsorEventBO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.SponsorEventDAO;
import lk.ijse.gdse.eventManage.dto.EventSponsorsDto;
import lk.ijse.gdse.eventManage.dto.SponserAndEventDto;

import java.sql.SQLException;

public class SponsorEventBOImpl implements SponsorEventBO {
    private final SponsorEventDAO sponsorEventDAO= (SponsorEventDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SPONSOREVENT);
    @Override
    public boolean save(EventSponsorsDto dto) throws SQLException {

        return sponsorEventDAO.save(new SponserAndEventDto(dto.getEventId() , dto.getSId(), dto.getAmount()));
    }

    @Override
    public boolean update(EventSponsorsDto dto) throws SQLException {
        return sponsorEventDAO.update(new SponserAndEventDto(dto.getEventId() , dto.getSId(), dto.getAmount()));
    }
}
