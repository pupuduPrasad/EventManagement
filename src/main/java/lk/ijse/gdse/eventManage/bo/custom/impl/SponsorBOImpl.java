package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.SponsorBO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.SponsorDAO;
import lk.ijse.gdse.eventManage.dto.SponsorDto;
import lk.ijse.gdse.eventManage.entity.Sponsor;

import java.sql.SQLException;
import java.util.ArrayList;

public class SponsorBOImpl implements SponsorBO {
    private final SponsorDAO sponsorDAO= (SponsorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SPONSOR);
    @Override
    public String getNextId() throws SQLException {
        return sponsorDAO.getNextId();
    }

    @Override
    public boolean save(SponsorDto entity) throws SQLException {
        return sponsorDAO.save(new Sponsor(entity.getSId(),entity.getName(),entity.getContactNumber(),entity.getAddress(),entity.getEmail(),entity.getAmount(),entity.getEventID()));
    }

    @Override
    public boolean update(SponsorDto entity) throws SQLException {
        return sponsorDAO.update(new Sponsor(entity.getSId(),entity.getName(),entity.getContactNumber(),entity.getAddress(),entity.getEmail(),entity.getAmount(),entity.getEventID()));
    }

    @Override
    public boolean delete(String rId) throws SQLException {
        return sponsorDAO.delete(rId);
    }

    @Override
    public ArrayList<SponsorDto> getAll() throws SQLException {
        ArrayList<SponsorDto> sponsorDtos= new ArrayList<>();
        ArrayList<Sponsor> sponsors = sponsorDAO.getAll();
        for (Sponsor sponsor : sponsors) {
            SponsorDto sponsorDto = new SponsorDto();
            sponsorDto.setSId(sponsor.getSId());
            sponsorDto.setName(sponsor.getName());
            sponsorDto.setContactNumber(sponsor.getContactNumber());
            sponsorDto.setAddress(sponsor.getAddress());
            sponsorDto.setEmail(sponsor.getEmail());
            sponsorDto.setAmount(sponsor.getAmount());
            sponsorDto.setEventID(sponsor.getEventID());
            sponsorDtos.add(sponsorDto);
        }
        return sponsorDtos;
    }
}
