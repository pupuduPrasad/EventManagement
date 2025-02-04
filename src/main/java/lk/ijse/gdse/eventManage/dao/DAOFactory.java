package lk.ijse.gdse.eventManage.dao;

import lk.ijse.gdse.eventManage.dao.custom.CustomerDAO;
import lk.ijse.gdse.eventManage.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType {
        CUSTOMER,EVENT,RESERVATION,FEEDBACK,TICKET,PAYMENT,USER
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
                            case TICKET:
                                return new TicketDAOImpl();
                                case PAYMENT:
                                    return new PaymentDAOImpl();
                                    case USER:
                                        return new UserDAOImpl();
                default:
                    return null;




        }
    }
}
