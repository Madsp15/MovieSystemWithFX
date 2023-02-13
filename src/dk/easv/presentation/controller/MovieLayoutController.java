package dk.easv.presentation.controller;

import dk.easv.presentation.model.AppModel;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieLayoutController implements Initializable {
    public TextArea text1,text2,text3,text4, text5;
    public Label title1,title2,title3,title4, title5;
    public ImageView image1,image2,image3,image4, image5;

    public Circle ratingOne1,ratingOne2,ratingOne3,ratingOne4,ratingOne5,ratingOne6,ratingOne7,ratingOne8,ratingOne9,ratingOne10;

    public Circle ratingTwo1,ratingTwo2,ratingTwo3,ratingTwo4,ratingTwo5,ratingTwo6,ratingTwo7,ratingTwo8,ratingTwo9,ratingTwo10;

    public Circle ratingThree1,ratingThree2,ratingThree3,ratingThree4,ratingThree5,ratingThree6,ratingThree7,ratingThree8,ratingThree9,ratingThree10;

    public Circle ratingFour1,ratingFour2,ratingFour3,ratingFour4,ratingFour5,ratingFour6,ratingFour7,ratingFour8,ratingFour9,ratingFour10;

    public Circle ratingFive1,ratingFive2,ratingFive3,ratingFive4,ratingFive5,ratingFive6,ratingFive7,ratingFive8,ratingFive9,ratingFive10;
    public Label lableUser;
    public ToggleButton menuHome,menuRecommended,menuDiscover, menuWatchAgain,menuRandomMovies, menuLogOut;
    public ToggleGroup MenuGroup;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setModel(AppModel model) {
    }
}
