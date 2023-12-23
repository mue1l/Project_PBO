/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project_pbo;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class Check_inController implements Initializable {

    @FXML
    private DatePicker C_O_Date;

    @FXML
    private Label total;
    @FXML
    private Label total_hari;

    @FXML
    private TextField Email;

    @FXML
    private Button Check_in_btn;

    @FXML
    private Button exit_btn;

    @FXML
    private TextField nTelephone;

    @FXML
    private Button reset_btn;

    @FXML
    private DatePicker C_I_date;

    @FXML
    private TextField nBelakang;

    @FXML
    private FontAwesomeIcon close_btn1;

    @FXML
    private TextField nDepan;

    @FXML
    private Button receipt_btn;

     @FXML
    private TextField custumer_id;

    @FXML
    private ComboBox<?> roomtype;

    @FXML
    private ComboBox<?> roomNumber;

    // aturan database
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void customerCheckIn() throws SQLException {
        String insertCustomerData = "INSERT INTO customer(custumer_id,roomtype,roomNumber,firstName,lastName,phoneNumber,email) VALUES (?,?,?,?,?,?,?)";

        connect = database.getDBConnection();

        try {
            String customerid = custumer_id.getText();
            String roomT = roomtype.getSelectionModel().getSelectedItem().toString();
            String roomN = roomNumber.getSelectionModel().getSelectedItem().toString();
            String firstN = nDepan.getText();
            String lastN = nBelakang.getText();
            String phoneNum = nTelephone.getText();
            String email = Email.getText();

            Alert alert;

            if (customerid.isEmpty() || roomT.isEmpty() || lastN.isEmpty() || firstN.isEmpty() || lastN.isEmpty() || phoneNum.isEmpty() || email.isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {

                
                    prepare = connect.prepareStatement(insertCustomerData);
                    prepare.setString(1, customerid);
                    prepare.setString(2, roomT);
                    prepare.setString(3, roomN);
                    prepare.setString(4, firstN);
                    prepare.setString(5, lastN);
                    prepare.setString(6, phoneNum);
                    prepare.setString(7, email);
                    
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully checked-In!");
                    alert.showAndWait();
                        
                    checkInClear();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
      
    }
//
//                    String date = String.valueOf(C_I_date.getValue());
//                    String totalC = String.valueOf(totalP);
//                    String customerN = customerNumber.getText();
//
//                    String customerReceipt = "INSERT INTO customer_receipt(custumer_num,total,date)"
//                            + "VALUES(?,?,?,)";
//
//                    prepare = connect.prepareStatement(customerReceipt);
//                    prepare.setString(1, customerN);
//                    prepare.setString(2, totalC);
//                    prepare.setString(3, date);
//
//                    prepare.execute();

//                    
//
//    public void totalHari() throws SQLException {
//
//        Alert alert;
//
//        if (C_O_Date.getValue().isAfter(C_I_date.getValue())) {
//
//            ambilData.totalDays = C_O_Date.getValue().compareTo(C_I_date.getValue());
//
//        } else {
//
//            alert = new Alert(AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Invalid Check-out date");
//            alert.showAndWait();
//
//            C_O_Date.setValue(null);
//
//        }
//    }

//    private float totalP = 0;
//
//    public void displayTotal() throws SQLException {
//
//        String totalD = String.valueOf(ambilData.totalDays);
//
//        total_hari.setText(totalD);
//
//        String selectItem = (String) roomNumber.getSelectionModel().getSelectedItem();
//        String sql = "SELECT * FROM kamar WHERE roomNumber = '" + selectItem + "'";
//        double priceData = 0;
//
//        connect = database.getDBConnection();
//
//        try {
//
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            if (result.next()) {
//                priceData = result.getDouble("price");
//            }
//
//            totalP = (float) ((priceData) * ambilData.totalDays);
//            System.out.println("Total Payment: " + totalP);
//            System.out.println("priceData: " + priceData);
//            total.setText("RP" + String.valueOf(totalP));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

//    public void customerNumber() throws SQLException {
//        String customerNumber = "SELECT custumer_id FROM customer";
//        connect = database.getDBConnection();
//        try {
//
//            prepare = connect.prepareStatement(customerNumber);
//            result = prepare.executeQuery();
//
//            while (result.next()) {
//                ambilData.customerNum = result.getInt("custumer_id");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    
    public void checkInClear() {
        custumer_id.setText("");
        roomtype.getSelectionModel().clearSelection();
        roomNumber.getSelectionModel().clearSelection();
        nDepan.setText("");
        nBelakang.setText("");
        nTelephone.setText("");
        Email.setText("");
        

    }
    
    public void roomTypelist() throws SQLException {
        String listType = "SELECT * FROM kamar WHERE status = 'Tersedia'";

        connect = database.getDBConnection();

        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listType);
            result = prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("type"));
            }

            roomtype.setItems(listData);
            roomNumberList();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void roomNumberList() throws SQLException {

        String item = (String) roomtype.getSelectionModel().getSelectedItem();

        String avaibleRoomNumber = "SELECT * FROM kamar WHERE type = '" + item + "'";

        connect = database.getDBConnection();

        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(avaibleRoomNumber);
            result = prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("roomNumber"));
            }
            roomNumber.setItems(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public void displayCustomerNumber() throws SQLException {
//        customerNumber();
//        customerNumber.setText(String.valueOf(ambilData.customerNum + 1));
//    }

    public void exit() {
        System.exit(0);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            roomTypelist();
        } catch (SQLException ex) {
            Logger.getLogger(Check_inController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            roomNumberList();
        } catch (SQLException ex) {
            Logger.getLogger(Check_inController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
