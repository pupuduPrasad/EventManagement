package lk.ijse.gdse.eventManage.bo;

import lk.ijse.gdse.eventManage.bo.custom.impl.CustomerBOImpl;
import lk.ijse.gdse.eventManage.bo.custom.impl.EventBOImpl;
import lk.ijse.gdse.eventManage.bo.custom.impl.FeedbackBOImpl;
import lk.ijse.gdse.eventManage.bo.custom.impl.ReservationBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}
    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }
    public enum BOType {
        CUSTOMER,EVENT,RESERVATION,FEEDBACK
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
            default:
                return null;
        }
    }
}
