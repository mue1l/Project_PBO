/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package project_pbo;


import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 *
 * @author ACER
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private StackPane stack_form;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private TextField username;
    
    

    
     //aturan database
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet result;
    
    private double x = 0;
    private double y = 0;
    
     public void login() throws SQLException{
        try{
            
            connection = database.getDBConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String query = "SELECT * FROM admin WHERE username = ? and password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username.getText());
            preparedStatement.setString(2, password.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            
             Alert alert;
             
             if(username.getText().isEmpty() || password.getText().isEmpty()){
                 
                 alert = new Alert(AlertType.ERROR);
                 alert.setTitle("Admin Message");
                 alert.setHeaderText(null);
                 alert.setContentText("Please fill all blank fields.");
                 alert.showAndWait();
             }else{
                 if(resultSet.next()){
                     
                     
                     alert = new Alert(AlertType.INFORMATION);
                     alert.setTitle("Admin Message");
                     alert.setHeaderText(null);
                     alert.setContentText("Successfully Login!");
                     alert.showAndWait();
                     
                    // TO HIDE THE LOGIN FORM
                    loginBtn.getScene().getWindow().hide();
                     
                    // FOR DASHBOARD
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    root.setOnMousePressed((MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getSceneY();
                  
                 });
                    
                    root.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getScreenX() -x);
                        stage.setY(event.getScreenY() -y);
                    });
                    
                    stage.initStyle(StageStyle.TRANSPARENT);
                    
                    
                    stage.setScene(scene);
                    stage.show();
                    
                 }else{
                     alert = new Alert(AlertType.ERROR);
                     alert.setTitle("Admin Message");
                     alert.setHeaderText(null);
                     alert.setContentText("Wrong username or password");
                     alert.showAndWait();
                 }
             }
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void exit(){
        System.exit(0);
    }
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}
