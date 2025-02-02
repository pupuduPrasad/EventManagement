package lk.ijse.gdse.eventManage.dao;

import lk.ijse.gdse.eventManage.dao.custom.CustomerDAO;
import lk.ijse.gdse.eventManage.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.gdse.eventManage.dao.custom.impl.EventDAOImpl;
import lk.ijse.gdse.eventManage.dao.custom.impl.FeedbackDAOImpl;
import lk.ijse.gdse.eventManage.dao.custom.impl.ReservationDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType {
        CUSTOMER,EVENT,RESERVATION,FEEDBACK
        }
    public SuperDAO getDAO(DAOType type) {
        switch (type){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EVENT:
                    return new EventDAOImpl();
                    case RESERVATION:
                        return new ReservationDAOImpl();
                        case FEEDBACK:
                            return new FeedbackDAOImpl();
                default:
                    return null;




        }
    }
}
