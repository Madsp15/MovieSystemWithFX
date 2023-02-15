package dk.easv.presentation.controller;

import dk.easv.presentation.model.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieLayoutController implements Initializable {
    @FXML
    private TextArea text1,text2,text3,text4, text5;
    @FXML
    private Label title1,title2,title3,title4, title5;
    @FXML
    private ImageView image1,image2,image3,image4, image5;
    @FXML

    private Circle ratingOne1,ratingOne2,ratingOne3,ratingOne4,ratingOne5,ratingOne6,ratingOne7,ratingOne8,ratingOne9,ratingOne10;
    @FXML

    private Circle ratingTwo1,ratingTwo2,ratingTwo3,ratingTwo4,ratingTwo5,ratingTwo6,ratingTwo7,ratingTwo8,ratingTwo9,ratingTwo10;
    @FXML
    private Circle ratingThree1,ratingThree2,ratingThree3,ratingThree4,ratingThree5,ratingThree6,ratingThree7,ratingThree8,ratingThree9,ratingThree10;
    @FXML
    private Circle ratingFour1,ratingFour2,ratingFour3,ratingFour4,ratingFour5,ratingFour6,ratingFour7,ratingFour8,ratingFour9,ratingFour10;
    @FXML
    private Circle ratingFive1,ratingFive2,ratingFive3,ratingFive4,ratingFive5,ratingFive6,ratingFive7,ratingFive8,ratingFive9,ratingFive10;
    @FXML
    private Label lableUser;
    @FXML
    private ToggleButton menuHome,menuRecommended,menuDiscover, menuWatchAgain,menuRandomMovies, menuLogOut;

    private AppModel model;

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setModel(AppModel model) {
        lableUser.setText(model.getObsLoggedInUser().getName() + "!");
        this.model = model;

    }
    public void fillOutMovies(){

    }

    public void clickLogout(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/presentation/view/Login.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Movie Recommendation System 0.5 Beta");
            stage.show();

            Node n = (Node) actionEvent.getSource();
            Stage stage2 = (Stage) n.getScene().getWindow();
            stage2.close();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load LogIn.fxml");
            alert.showAndWait();
        }

    }

    public void clickRandomMovies(ActionEvent actionEvent) {
        anchorPane.getStylesheets().clear();
        anchorPane.getStylesheets().add(
                getClass()
                        .getResource("/dk/easv/presentation/view/Random.css")
                        .toExternalForm()
        );
    }

    public void clickHome(ActionEvent actionEvent) {
        anchorPane.getStylesheets().clear();
        anchorPane.getStylesheets().add(
                getClass()
                        .getResource("/dk/easv/presentation/view/Home.css")
                        .toExternalForm()
        );
    }

    public void clickRecommended(ActionEvent actionEvent) {
        anchorPane.getStylesheets().clear();
        anchorPane.getStylesheets().add(
                getClass()
                        .getResource("/dk/easv/presentation/view/Recommended.css")
                        .toExternalForm()
        );
    }

    public void clickDiscover(ActionEvent actionEvent) {
        anchorPane.getStylesheets().clear();
        anchorPane.getStylesheets().add(
                getClass()
                        .getResource("/dk/easv/presentation/view/Discover.css")
                        .toExternalForm()
        );
    }


}
