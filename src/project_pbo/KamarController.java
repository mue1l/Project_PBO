/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project_pbo;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import static javax.swing.UIManager.get;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class KamarController implements Initializable {

    @FXML
    void pelanggan(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("pelanggann.fxml"));
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

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void kamarTersedia(ActionEvent event) {

    }

    @FXML
    private FontAwesomeIcon close_btn;
    @FXML
    private FontAwesomeIcon minimize_btn;
    @FXML
    private Label username;
    @FXML
    private Button dashboard_btn;
    @FXML
    private Button kamar_btn;
    @FXML
    private Button pelanggan_btn;
    @FXML
    private Button logout_btn;
    @FXML
    private TextField kamar_room;
    @FXML
    private TextField kamar_harga;
    @FXML
    private ComboBox kamar_roomtype;
    @FXML
    private ComboBox kamar_roomstatus;
    @FXML
    private Button btn_tambah_kmr;
    @FXML
    private Button btn_checkin_kmr;
    @FXML
    private Button btn_Clear_kmr;
    @FXML
    private Button btn_hapus_kmr;
    @FXML
    private Button btn_update_kmr;
    @FXML
    private TableView<datakamar> tabel_kamar;
    @FXML
    private TableColumn kamar_col_room;
    @FXML
    private TableColumn kamar_col_harga;
    @FXML
    private TableColumn kamar_col_typeroom;
    @FXML
    private TableColumn kamar_col_status;
    @FXML
    private TextField kamar_search;
    @FXML
    private FontAwesomeIcon LOG_OUT;

    /**
     * Initializes the controller class.
     */
    // function untuk kamartersedia
    // aturan database
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public ObservableList<datakamar> kamarlistData() throws SQLException {

        ObservableList<datakamar> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM kamar";

        connect = database.getDBConnection();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                datakamar roomD = new datakamar(result.getInt("roomNumber"), result.getString("type"), result.getString("status"), result.getDouble("price"));
                listData.add(roomD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<datakamar> datakamarList;

    public void kamarShowData() throws SQLException {

        datakamarList = kamarlistData();

        kamar_col_room.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        kamar_col_typeroom.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        kamar_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        kamar_col_harga.setCellValueFactory(new PropertyValueFactory<>("price"));
        tabel_kamar.setItems(datakamarList);
    }

    // 
    public void kamarAdd() throws SQLException {
        String sql = "INSERT INTO kamar(roomNumber,type,status,price) VALUES (?,?,?,?)";

        connect = database.getDBConnection();

        try {

            String roomNumber = kamar_room.getText();
            String type = kamar_roomtype.getSelectionModel().getSelectedItem().toString();
            String status = kamar_roomstatus.getSelectionModel().getSelectedItem().toString();
            String price = kamar_harga.getText();

            Alert alert;

            // periksa apakah ada bidang kosong
            if (roomNumber.isEmpty() || type.isEmpty() || status.isEmpty() || price.isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, roomNumber);
                prepare.setString(2, type);
                prepare.setString(3, status);
                prepare.setString(4, price);

                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Massage");
                alert.setHeaderText(null);
                alert.setContentText("Seccessfully added");
                alert.showAndWait();

                // mengupdate data ke tabel view
                kamarShowData();

                // untuk membersihkan field
                kamarClear();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void kamarUpdate() {
        try {
            connect = database.getDBConnection();
            String query1 = "UPDATE kamar SET "
                    + " roomNumber='" + kamar_room.getText()
                    + "', type='" + kamar_roomtype.getSelectionModel().getSelectedItem()
                    + "', status='" + kamar_roomstatus.getSelectionModel().getSelectedItem()
                    + "', price='" + kamar_harga.getText()
                    + "' WHERE roomNumber = " + kamar_room.getText() + ";";
            prepare = connect.prepareStatement(query1);
            prepare.execute();
            kamarShowData();
            kamarClear();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
//            ex.getSQLState();
        }
    }

    public void kamarSelect() {

        int num = tabel_kamar.getSelectionModel().getSelectedIndex();

        datakamar dataKmr = tabel_kamar.getSelectionModel().getSelectedItem();

        if (num - 1 < -1) {
            return;
        }

        kamar_room.setText(String.valueOf(dataKmr.getRoomNumber()));
        kamar_harga.setText(String.valueOf(dataKmr.getPrice()));
        kamar_roomtype.getSelectionModel().select(dataKmr.getRoomType());
        kamar_roomstatus.getSelectionModel().select(dataKmr.getStatus());
    }

    public void kamarDelete() {
        try {

            connect = database.getDBConnection();

            String sql = "delete from kamar where roomNumber = ?";

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, kamar_room.getText());
            prepare.execute();
            JOptionPane.showMessageDialog(null, "Berhasil di Hapus");
            kamarShowData();
            kamarClear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void kamarClear() {
        kamar_room.setText("");
        kamar_roomtype.getSelectionModel().clearSelection();
        kamar_roomstatus.getSelectionModel().clearSelection();
        kamar_harga.setText("");

    }

    public void kamarCheckIn() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("check_in.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setMinHeight(494 + 15);
            stage.setMinWidth(398 + 15);

            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double x = 0;
    private double y = 0;

    public void logout() {
        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Apakah kamu yakin ingin keluar?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();

                logout_btn.getScene().getWindow().hide();
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exit() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            kamarShowData();
        } catch (SQLException ex) {
            Logger.getLogger(KamarController.class.getName()).log(Level.SEVERE, null, ex);
        }

        kamarClear();
    }

}
