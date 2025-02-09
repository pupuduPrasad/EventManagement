package lk.ijse.gdse.eventManage.bo;

import lk.ijse.gdse.eventManage.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}
    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }
    public enum BOType {
        CUSTOMER,EVENT,RESERVATION,FEEDBACK,TICKET,PAYMENT,USER,SPONSOR,SPONSOREVENT
    }
    public SuperBo getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
                case EVENT:
                    return new EventBOImpl();
                    case RESERVATION:
                        return new ReservationBOImpl();
                        case FEEDBACK:
                            return new FeedbackBOImpl();
                            case TICKET:
                                return new TicketBOImpl();
                                case PAYMENT:
                                    return new PaymentBOImpl();
                                    case USER:
                                        return new UserBOImpl();
                                        case SPONSOR:
                                            return new SponsorBOImpl();

            default:
                return null;
        }
    }
}
