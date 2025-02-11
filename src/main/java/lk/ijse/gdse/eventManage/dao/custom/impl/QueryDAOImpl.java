package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.util.CrudUtil;
import lk.ijse.gdse.eventManage.dao.custom.QueryDAO;
import lk.ijse.gdse.eventManage.entity.JoinSponserEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<JoinSponserEvent> getAllSponser() throws SQLException {
        ResultSet rst = CrudUtil.execute("select s.sId, es.eventId, s.name, s.contactNumber, s.address, es.amount from sponsors s join eventsponsors es on s.sId = es.sId");
        ArrayList<JoinSponserEvent> js = new ArrayList<>();
        while (rst.next()) {
            JoinSponserEvent dto = new JoinSponserEvent(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDouble(6)

            );
            js.add(dto);
        }
        return js;


    }
}
