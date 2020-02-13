module EjercicioInformes {

    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires jasperreports;
    requires jasperreports.functions;
    requires javafx.controls;

    exports com.emma;
    exports com.emma.views;

    opens com.emma.views to javafx.fxml;
}