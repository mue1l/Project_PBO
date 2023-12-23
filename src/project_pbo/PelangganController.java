/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project_pbo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static javax.swing.UIManager.getString;

/**
 * FXML Controller class
 *
 * @author ACER
 */

public class PelangganController implements Initializable {

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button exit_btn;

    @FXML
    private Button exit_btn1;

    @FXML
    private Button kamar_btn;

    @FXML
    private TableColumn<?, ?> nol;

    @FXML
    private Button pelanggan_btn;

    @FXML
    private TableColumn<dataPelanggan, String> pelanggan_namaBelakang;

    @FXML
    private TableColumn<dataPelanggan, String> pelanggan_namadepan;

    @FXML
    private TableColumn<dataPelanggan, Integer> pelanggan_pelanggan;

    @FXML
    private TextField pelanggan_search;

    @FXML
    private TableColumn<dataPelanggan, String> pelanggan_telepon;

    @FXML
    private TableColumn<dataPelanggan, String> pelanggan_email;

    @FXML
    private TableColumn<dataPelanggan, String> roomType;

    @FXML
    private TableColumn<dataPelanggan, String> room;

    @FXML
    private TableColumn<dataPelanggan, String> pelanggan_ttlpembaaran1;

    @FXML
    private TableColumn<dataPelanggan, String> pelanggan_ttlpembaaran11;

    @FXML
    private TableView<dataPelanggan> tabel_pelanggan;

    @FXML
    private Label username;

    @FXML
    void exit(ActionEvent event) {

    }

   // aturan database
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    

    public ObservableList<dataPelanggan> CheckInData() throws SQLException {

        ObservableList<dataPelanggan> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customer";

        connect = database.getDBConnection();

        try {
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                dataPelanggan checkI = new dataPelanggan(result.getInt("custumer_id"), result.getString("roomType"), result.getString("roomNumber"), result.getString("firstName"), result.getString("lastName"),result.getString("phoneNumber"), result.getString("email"));
                listData.add(checkI);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<dataPelanggan> dataCheckIn;

    public void checkInShowData() throws SQLException {

        dataCheckIn = CheckInData();

        pelanggan_pelanggan.setCellValueFactory(new PropertyValueFactory<>("custumer_id"));
        pelanggan_namadepan.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        pelanggan_namaBelakang.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        pelanggan_telepon.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        pelanggan_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        room.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        tabel_pelanggan.setItems(dataCheckIn);
    }
    
        public void exit() {
        System.exit(0);
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            checkInShowData();
        } catch (SQLException ex) {
            Logger.getLogger(PelangganController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 @FXML
    void kamar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("kamarr.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void dashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
       
    
    
    
}
