package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.SponsorBO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.SponsorDAO;
import lk.ijse.gdse.eventManage.dao.custom.SponsorEventDAO;
import lk.ijse.gdse.eventManage.dao.custom.impl.QueryDAOImpl;
import lk.ijse.gdse.eventManage.db.DBConnection;
import lk.ijse.gdse.eventManage.dto.EventSponsorsDto;
import lk.ijse.gdse.eventManage.dto.JoinSponserEventDetailDto;
import lk.ijse.gdse.eventManage.dto.SponsorDto;
import lk.ijse.gdse.eventManage.entity.EventSponsors;
import lk.ijse.gdse.eventManage.entity.JoinSponserEvent;
import lk.ijse.gdse.eventManage.entity.Sponsor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SponsorBOImpl implements SponsorBO {
     SponsorDAO sponsorDAO= (SponsorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SPONSOR);
     SponsorEventDAO sponsorEventDAO= (SponsorEventDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SPONSOREVENT);
     QueryDAOImpl queryDAO = (QueryDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public String getNextId() throws SQLException {
        return sponsorDAO.getNextId();
    }

    @Override
    public boolean save(ArrayList<SponsorDto> sponsorDtos, ArrayList<EventSponsorsDto> eventSponsorsDtos) throws SQLException {
        Connection connection = null;
        try{
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
         for (SponsorDto sponsorDto : sponsorDtos) {
             boolean b1=sponsorDAO.save(new Sponsor(
                     sponsorDto.getSId(),
                     sponsorDto.getName(),
                     sponsorDto.getContactNumber(),
                     sponsorDto.getAddress()
             ));
             if (!b1){
                 connection.rollback();
                 return false;
             }
         }
         for (EventSponsorsDto eventSponsorDto : eventSponsorsDtos) {
             boolean b2=sponsorEventDAO.save(new EventSponsors(
                     eventSponsorDto.getEventId(),
                     eventSponsorDto.getSId(),
                     eventSponsorDto.getAmount()
             ));
             if (!b2){
                 connection.rollback();
                 return false;
             }
         }
         connection.commit();
         return true;

        } catch (SQLException e) {
           if (connection != null){
               connection.rollback();
           }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(ArrayList<SponsorDto> sponsorDtos, ArrayList<EventSponsorsDto> eventSponsorsDtos) throws SQLException {
        Connection connection = null;

        try{
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            for (SponsorDto sponsorDto : sponsorDtos) {
                boolean b1=sponsorDAO.update(new Sponsor(
                        sponsorDto.getSId(),
                        sponsorDto.getName(),
                        sponsorDto.getContactNumber(),
                        sponsorDto.getAddress()
                ));
                if (!b1){
                    System.out.println("sponser update failed");
                    connection.rollback();
                    return false;
                }
            }
            for (EventSponsorsDto eventSponsorDto : eventSponsorsDtos) {
                boolean b2=sponsorEventDAO.update(new EventSponsors(
                        eventSponsorDto.getEventId(),
                        eventSponsorDto.getSId(),
                        eventSponsorDto.getAmount()
                ));
                if (!b2){
                    System.out.println("sponserEvent update failed");
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (Exception e) {
           if (connection != null){
               connection.rollback();
           }
           e.printStackTrace();
           return false;
        }
    }

    @Override
    public boolean delete(String rId) throws SQLException {
        return sponsorDAO.delete(rId);
    }

    @Override
    public ArrayList<JoinSponserEventDetailDto> getAll() throws SQLException {
        ArrayList<JoinSponserEventDetailDto> sponsorDtos= new ArrayList<>();
        ArrayList<JoinSponserEvent> sponsors = queryDAO.getAllSponser();
       for (JoinSponserEvent sponsorEvent : sponsors) {
           sponsorDtos.add(new JoinSponserEventDetailDto(
                   sponsorEvent.getSId(),
                   sponsorEvent.getEventId(),
                   sponsorEvent.getName(),
                   sponsorEvent.getContactNumber(),
                   sponsorEvent.getAddress(),
                   sponsorEvent.getAmount()
           ));
       }
       return sponsorDtos;
    }
}
