package com.emma.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import javafx.scene.control.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PantallaController extends BaseController implements Initializable {

    @FXML
    private TextField tf_parametro;

    private Connection conn = null;
    private int id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/test",
                    "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(conn);

    }

    public void aceptar(ActionEvent actionEvent) {

        id = Integer.parseInt(tf_parametro.getText());

        Map<String,Object> parametros = new HashMap<>();
        parametros.put("DocumentID", id);

        JasperPrint print = null;

        try {
            print = JasperFillManager.fillReport(PantallaController.class.getResourceAsStream("/jasper/Factura.jasper"), parametros, conn);
        } catch (JRException e) {
            e.printStackTrace();
        }

        try {
            JasperExportManager.exportReportToPdfFile(print, "informes/factura"+id+".pdf");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
