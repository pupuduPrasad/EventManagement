module lk.ijse.gdse.eventManage {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires net.sf.jasperreports.core;
    requires java.desktop;


    opens lk.ijse.gdse.eventManage.controller to javafx.fxml;
    exports lk.ijse.gdse.eventManage.controller;
    exports lk.ijse.gdse.eventManage;
    opens lk.ijse.gdse.eventManage.dto.tm to javafx.base;
    opens lk.ijse.gdse.eventManage to javafx.fxml;
}