package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import dk.easv.entities.TopMovie;
import dk.easv.entities.User;
import dk.easv.entities.UserSimilarity;
import dk.easv.presentation.model.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    private List<User> allUsers = new ArrayList<>();
    private List<UserSimilarity> similarUsers = new ArrayList<>();
    private List<Movie> topForUser = new ArrayList<>();
    private List<Movie> topAvgNotSeen = new ArrayList<>();
    private List<TopMovie> topFromSimilar = new ArrayList<>();
    private List<Image> images = new ArrayList<>();
    private List<String> descriptions = new ArrayList<>();

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setModel(AppModel model) {
        lableUser.setText(model.getObsLoggedInUser().getName() + "!");
        this.model = model;
        allUsers.addAll(model.getObsUsers());
        similarUsers.addAll(model.getObsSimilarUsers());
        topForUser.addAll(model.getObsTopMovieSeen());
        topAvgNotSeen.addAll(model.getObsTopMovieNotSeen());
        topFromSimilar.addAll(model.getObsTopMoviesSimilarUsers());
        File path = new File("Pictures");
        File[] allFiles = path.listFiles();
        for (File f: allFiles
             ) {
            images.add(new Image(f.getName()));
        }
        descriptions.add("This is a movie that takes place on the planet 'Earth' in the Milky Way \n" +
                "galaxy. There are many events in this film. The story is driven by a protagonist\n" +
                "and his supporting roles. Thrill? Excite? All!");
        descriptions.add("What's a mouse got to do to get some cheese around here? Find out\n" +
                "in this epic musical staring live mice from all around the world");
        descriptions.add("Time and space, surround us all. But what if neither existed. A wrinkle in time\n" +
                "and a wrinkle on your forehead as you watch this suspenseful mystery");
        descriptions.add("Ten tangible things take themselves to the top! This thriller will knock\n" +
                "your socks off as events go by with you watching with your eyes");
        descriptions.add("In 2001, things were really taking place! A new president in charge, ready to take\n" +
                "the world by storm, but he never could've expected what would happen next");
        descriptions.add("4 friends rent a room in the upper east side, hoping a night out on the town\n" +
                "will help them ease there summer blues");
        descriptions.add("Love, romance, other things, all take place at the same time in this classic\n" +
                "from the 1980's");
        descriptions.add("Things have been rough for animals on the farm since Nana left. How will\n" +
                "Odin cheer up the chicks in time for this summer's egg festival?");
        descriptions.add("Once she moved to the big city, Becky knew there was no turning back.\n" +
                "She left her old life behind her and is ready to face the future.");
        fillOutMovies();
    }

    public void setImages(String menu){
        List<Movie> movies = topForUser;
        List<TopMovie> topMovies = new ArrayList<>();
        int index = 0;
        switch (menu) {
            case "Home" -> {
                movies = topForUser;
                index = 1;
            }
            case "Discover" -> {
                movies = topAvgNotSeen;
                index = 2;
            }
            case "Recommended" -> {
                topMovies = topFromSimilar;
                index = 3;
            }
            case "Random Movies" -> {
                movies = topAvgNotSeen;
                index = 4;
            }
        }
        if(index == 1||index == 2||index ==4||index == 0){
            image1.setImage(images.get(Math.abs(movies.get(0).getId()%10)));
            image2.setImage(images.get(Math.abs(movies.get(1).getId()%10)));
            image3.setImage(images.get(Math.abs(movies.get(2).getId()%10)));
            image4.setImage(images.get(Math.abs(movies.get(3).getId()%10)));
            image5.setImage(images.get(Math.abs(movies.get(4).getId()%10)));

            text1.setText(descriptions.get(Math.abs(movies.get(0).getId()%10)));
            text2.setText(descriptions.get(Math.abs(movies.get(1).getId()%10)));
            text3.setText(descriptions.get(Math.abs(movies.get(2).getId()%10)));
            text4.setText(descriptions.get(Math.abs(movies.get(3).getId()%10)));
            text5.setText(descriptions.get(Math.abs(movies.get(4).getId()%10)));


            title1.setText(movies.get(0).getTitle());
            title2.setText(movies.get(1).getTitle());
            title3.setText(movies.get(2).getTitle());
            title4.setText(movies.get(3).getTitle());
            title5.setText(movies.get(4).getTitle());

        }
        if(index == 3){
            image1.setImage(images.get(Math.abs(topMovies.get(0).getYear()%10)));
            image2.setImage(images.get(Math.abs(topMovies.get(1).getYear()%10)));
            image3.setImage(images.get(Math.abs(topMovies.get(2).getYear()%10)));
            image4.setImage(images.get(Math.abs(topMovies.get(3).getYear()%10)));
            image5.setImage(images.get(Math.abs(topMovies.get(4).getYear()%10)));

            text1.setText(descriptions.get(Math.abs(topMovies.get(0).getYear()%10)));
            text2.setText(descriptions.get(Math.abs(topMovies.get(1).getYear()%10)));
            text3.setText(descriptions.get(Math.abs(topMovies.get(2).getYear()%10)));
            text4.setText(descriptions.get(Math.abs(topMovies.get(3).getYear()%10)));
            text5.setText(descriptions.get(Math.abs(topMovies.get(4).getYear()%10)));

            title1.setText(topMovies.get(0).getTitle());
            title2.setText(topMovies.get(1).getTitle());
            title3.setText(topMovies.get(2).getTitle());
            title4.setText(topMovies.get(3).getTitle());
            title5.setText(topMovies.get(4).getTitle());
        }

    }
    public void fillOutMovies(){
        if(menuDiscover.isSelected())
            setImages("Discover");
        else if (menuRecommended.isSelected())
            setImages("Recommended");
        else if(menuRandomMovies.isSelected())
            setImages("Random Movies");
        else if(menuHome.isSelected())
            setImages("Home");
        else
            setImages("Home");
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
        fillOutMovies();
    }

    public void clickHome(ActionEvent actionEvent) {
        anchorPane.getStylesheets().clear();
        anchorPane.getStylesheets().add(
                getClass()
                        .getResource("/dk/easv/presentation/view/Home.css")
                        .toExternalForm()
        );
        fillOutMovies();
    }

    public void clickRecommended(ActionEvent actionEvent) {
        anchorPane.getStylesheets().clear();
        anchorPane.getStylesheets().add(
                getClass()
                        .getResource("/dk/easv/presentation/view/Recommended.css")
                        .toExternalForm()
        );
        fillOutMovies();
    }

    public void clickDiscover(ActionEvent actionEvent) {
        anchorPane.getStylesheets().clear();
        anchorPane.getStylesheets().add(
                getClass()
                        .getResource("/dk/easv/presentation/view/Discover.css")
                        .toExternalForm()
        );
        fillOutMovies();
    }


}
