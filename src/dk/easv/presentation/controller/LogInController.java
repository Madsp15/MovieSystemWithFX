package dk.easv.presentation.controller;

import dk.easv.presentation.model.AppModel;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    public MFXPasswordField passwordField;
    @FXML private TextField userId;
    private AppModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new AppModel();
    }

    public void logIn(ActionEvent actionEvent) {
        model.loadUsers();
        model.loginUserFromUsername(userId.getText());
        if(!passwordField.getText().equals("password")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect Username or Password");
            alert.showAndWait();
            return;
        }
        if(model.getObsLoggedInUser()!=null){
        try {
            model.loadData(model.getObsLoggedInUser());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/presentation/view/MovieLayout.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Movie Recommendation System 0.5 Beta");
            stage.setMaxWidth(800);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            MovieLayoutController controller = loader.getController();

            controller.setModel(model);
            Node n = (Node) actionEvent.getSource();
            Stage stage2 = (Stage) n.getScene().getWindow();
            stage2.close();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load MovieLayout.fxml");
            alert.showAndWait();
        }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong username or password");
            alert.showAndWait();
        }
    }


    public void clickForgot(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your password is 'password'.");
        alert.showAndWait();
    }


}
