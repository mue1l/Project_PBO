/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project_pbo;


import java.sql.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class DashboardController implements Initializable {
    
    @FXML
    private FontAwesomeIcon minimize_btn;

    @FXML
    private AnchorPane dashboard_penghasilan;

    @FXML
    private Button pelanggan_btn;

    @FXML
    private AnchorPane dashboard_Riwayat;

    @FXML
    private Button close_btn;

    @FXML
    private AnchorPane dashboard_total;
                                                                        
    @FXML
    private Button dashboard_btn;

    @FXML
    private Button kamar_btn;

    @FXML
    private AreaChart<?, ?> dashboard_areachart;

    @FXML
    private Button logout_btn;

    @FXML
    private Label username;
    
    
    public void exit(){
    System.exit(0);
}
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try{
         totalPelanggan();
         totalPenghasilan();

        }catch(SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

  @FXML
  private Label pelangganL;
  PreparedStatement preparedStatement = null;
  Connection connect;
  ResultSet result;
  public void totalPelanggan() throws SQLException{
        
        connect = database.getDBConnection();
        String sql = "SELECT count(id) FROM customer";

        try{
            
            preparedStatement = connect.prepareStatement(sql);
            result = preparedStatement.executeQuery();
            
            while(result.next()){
                
                String totalPelanggan = result.getString("count(id)");
                
                pelangganL.setText(totalPelanggan);
                
            }
            
        }catch(SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
  @FXML
  private Label penghasilanL;
  
  public void totalPenghasilan() throws SQLException{
        
        connect = database.getDBConnection();
        String sql = "SELECT sum(price) FROM kamar";

        try{
            
            preparedStatement = connect.prepareStatement(sql);
            result = preparedStatement.executeQuery();
            
            while(result.next()){
                
                double totalPelanggan = result.getDouble("sum(price)");
                
                penghasilanL.setText(String.valueOf(totalPelanggan));
                
            }
            
        }catch(SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  @FXML
    void pelanggan(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("pelanggann.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    void kamar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("kamarr.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    private double x = 0;
    private double y = 0;

    public void logout() {
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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

  

}
