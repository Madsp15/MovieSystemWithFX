package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import dk.easv.entities.TopMovie;
import dk.easv.entities.User;
import dk.easv.entities.UserSimilarity;
import dk.easv.presentation.model.AppModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class MovieLayoutController implements Initializable {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextArea text1,text2,text3,text4, text5;
    @FXML
    private Label title1,title2,title3,title4, title5;
    @FXML
    private ImageView image1,image2,image3,image4, image5, imgMoon, imgSun;
    @FXML
    private ImageView imgRating1, imgRating2, imgRating3, imgRating4, imgRating5,
                        imgRating12, imgRating22, imgRating32, imgRating42, imgRating52,
                        imgRating13, imgRating23, imgRating33, imgRating43, imgRating53,
                        imgRating14, imgRating24, imgRating34, imgRating44, imgRating54,
                        imgRating15, imgRating25, imgRating35, imgRating45, imgRating55;
    @FXML
    private Label labelUser, labelPageNumber;
    @FXML
    private MFXButton buttonNext, buttonPrevious;
    @FXML
    private ToggleButton menuHome,menuRecommended,menuDiscover, menuWatchAgain,menuRandomMovies, menuLogOut;

    private AppModel model;

    private List<User> allUsers = new ArrayList<>();
    private List<UserSimilarity> similarUsers = new ArrayList<>();
    private List<Movie> topForUser = new ArrayList<>();
    private List<Movie> topAvgNotSeen = new ArrayList<>();
    private List<Movie> randomMovies = new ArrayList<>();
    private List<TopMovie> topFromSimilar = new ArrayList<>();
    private List<Image> titleImages = new ArrayList<>();
    private List<String> descriptions = new ArrayList<>();
    boolean isDarkModeOn = false;


    private int pageNumber = 0;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuHome.setSelected(true);
    }

    public void setModel(AppModel model) {
        labelUser.setText("Greetings" +  " " + model.getObsLoggedInUser().getName().trim() + "!");
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
            titleImages.add(new Image(f.getName()));
        }
        imgMoon.setImage(titleImages.get(13));
        imgSun.setImage(titleImages.get(14));
        descriptions.add("This is a movie that takes place on the planet 'Earth' in the Milky Way " +
                "galaxy. There are many events in this film. The story is driven by a protagonist" +
                "and his supporting roles. Thrill? Excite? All!");
        descriptions.add("What's a mouse got to do to get some cheese around here? Find out " +
                "in this epic musical staring live mice from all around the world");
        descriptions.add("Time and space, surround us all. But what if neither existed. A wrinkle in time " +
                "and a wrinkle on your forehead as you watch this suspenseful mystery");
        descriptions.add("Ten tangible things take themselves to the top! This thriller will knock " +
                "your socks off as events go by with you watching with your eyes");
        descriptions.add("In 2001, things were really taking place! A new president in charge, ready to take " +
                "the world by storm, but he never could've expected what would happen next");
        descriptions.add("4 friends rent a room in the upper east side, hoping a night out on the town " +
                "will help them ease there summer blues");
        descriptions.add("Love, romance, other things, all take place at the same time in this classic " +
                "from the 1980's");
        descriptions.add("Things have been rough for animals on the farm since Nana left. How will " +
                "Odin cheer up the chicks in time for this summer's egg festival?");
        descriptions.add("Once she moved to the big city, Becky knew there was no turning back. " +
                "She left her old life behind her and is ready to face the future.");
        fillOutMovies();
    }

    public void setMovieViews(String menu){
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
        if(index == 1||index == 2|| index == 0){
            image1.setImage(titleImages.get(Math.abs(movies.get(pageNumber).getId()%10)));
            image2.setImage(titleImages.get(Math.abs(movies.get(pageNumber+1).getId()%10)));
            image3.setImage(titleImages.get(Math.abs(movies.get(pageNumber+2).getId()%10)));
            image4.setImage(titleImages.get(Math.abs(movies.get(pageNumber+3).getId()%10)));
            image5.setImage(titleImages.get(Math.abs(movies.get(pageNumber+4).getId()%10)));

            text1.setText(descriptions.get(Math.abs(movies.get(pageNumber).getId()%9)));
            text2.setText(descriptions.get(Math.abs(movies.get(pageNumber+1).getId()%9)));
            text3.setText(descriptions.get(Math.abs(movies.get(pageNumber+2).getId()%9)));
            text4.setText(descriptions.get(Math.abs(movies.get(pageNumber+3).getId()%9)));
            text5.setText(descriptions.get(Math.abs(movies.get(pageNumber+4).getId()%9)));

            setStars(movies.get(pageNumber).getAverageRating(), imgRating1, imgRating2, imgRating3, imgRating4, imgRating5);
            setStars(movies.get(pageNumber+1).getAverageRating(), imgRating12, imgRating22, imgRating32, imgRating42, imgRating52);
            setStars(movies.get(pageNumber+2).getAverageRating(), imgRating13, imgRating23, imgRating33, imgRating43, imgRating53);
            setStars(movies.get(pageNumber+3).getAverageRating(), imgRating14, imgRating24, imgRating34, imgRating44, imgRating54);
            setStars(movies.get(pageNumber+4).getAverageRating(), imgRating15, imgRating25, imgRating35, imgRating45, imgRating55);


            title1.setText(movies.get(pageNumber).getTitle());
            title2.setText(movies.get(pageNumber+1).getTitle());
            title3.setText(movies.get(pageNumber+2).getTitle());
            title4.setText(movies.get(pageNumber+3).getTitle());
            title5.setText(movies.get(pageNumber+4).getTitle());

        }
        if(index == 3){
            image1.setImage(titleImages.get(Math.abs(topMovies.get(0).getYear()%10)));
            image2.setImage(titleImages.get(Math.abs(topMovies.get(1).getYear()%10)));
            image3.setImage(titleImages.get(Math.abs(topMovies.get(2).getYear()%10)));
            image4.setImage(titleImages.get(Math.abs(topMovies.get(3).getYear()%10)));
            image5.setImage(titleImages.get(Math.abs(topMovies.get(4).getYear()%10)));

            text1.setText(descriptions.get(Math.abs(topMovies.get(0).getYear()%9)));
            text2.setText(descriptions.get(Math.abs(topMovies.get(1).getYear()%9)));
            text3.setText(descriptions.get(Math.abs(topMovies.get(2).getYear()%9)));
            text4.setText(descriptions.get(Math.abs(topMovies.get(3).getYear()%9)));
            text5.setText(descriptions.get(Math.abs(topMovies.get(4).getYear()%9)));

            setStars(topMovies.get(pageNumber).getAverageRating(), imgRating1, imgRating2, imgRating3, imgRating4, imgRating5);
            setStars(topMovies.get(pageNumber+1).getAverageRating(), imgRating12, imgRating22, imgRating32, imgRating42, imgRating52);
            setStars(topMovies.get(pageNumber+2).getAverageRating(), imgRating13, imgRating23, imgRating33, imgRating43, imgRating53);
            setStars(topMovies.get(pageNumber+3).getAverageRating(), imgRating14, imgRating24, imgRating34, imgRating44, imgRating54);
            setStars(topMovies.get(pageNumber+4).getAverageRating(), imgRating15, imgRating25, imgRating35, imgRating45, imgRating55);

            title1.setText(topMovies.get(0).getTitle());
            title2.setText(topMovies.get(1).getTitle());
            title3.setText(topMovies.get(2).getTitle());
            title4.setText(topMovies.get(3).getTitle());
            title5.setText(topMovies.get(4).getTitle());
        }
        if(index == 4){
            Random random = new Random();
            int rd = random.nextInt((5500-500)+500);
            image1.setImage(titleImages.get(Math.abs(movies.get(rd).getId()%10)));
            image2.setImage(titleImages.get(Math.abs(movies.get(rd+1).getId()%10)));
            image3.setImage(titleImages.get(Math.abs(movies.get(rd+2).getId()%10)));
            image4.setImage(titleImages.get(Math.abs(movies.get(rd+3).getId()%10)));
            image5.setImage(titleImages.get(Math.abs(movies.get(rd+4).getId()%10)));

            text1.setText(descriptions.get(Math.abs(movies.get(rd).getId()%9)));
            text2.setText(descriptions.get(Math.abs(movies.get(rd+1).getId()%9)));
            text3.setText(descriptions.get(Math.abs(movies.get(rd+2).getId()%9)));
            text4.setText(descriptions.get(Math.abs(movies.get(rd+3).getId()%9)));
            text5.setText(descriptions.get(Math.abs(movies.get(rd+4).getId()%9)));

            setStars(movies.get(rd).getAverageRating(), imgRating1, imgRating2, imgRating3, imgRating4, imgRating5);
            setStars(movies.get(rd+1).getAverageRating(), imgRating12, imgRating22, imgRating32, imgRating42, imgRating52);
            setStars(movies.get(rd+2).getAverageRating(), imgRating13, imgRating23, imgRating33, imgRating43, imgRating53);
            setStars(movies.get(rd+3).getAverageRating(), imgRating14, imgRating24, imgRating34, imgRating44, imgRating54);
            setStars(movies.get(rd+4).getAverageRating(), imgRating15, imgRating25, imgRating35, imgRating45, imgRating55);


            title1.setText(movies.get(rd).getTitle());
            title2.setText(movies.get(rd+1).getTitle());
            title3.setText(movies.get(rd+2).getTitle());
            title4.setText(movies.get(rd+3).getTitle());
            title5.setText(movies.get(rd+4).getTitle());
        }

    }
    public void fillOutMovies(){
        if(menuDiscover.isSelected()){
            setMovieViews("Discover");
            labelUser.setText("Discover");}
        else if (menuRecommended.isSelected()){
            setMovieViews("Recommended");
            labelUser.setText("Recommended");}
        else if(menuRandomMovies.isSelected()){
            setMovieViews("Random Movies");
            labelUser.setText("Random Movies");}
        else if(menuHome.isSelected()){
            setMovieViews("Home");
            labelUser.setText("Greetings" +  " " + model.getObsLoggedInUser().getName().trim() + "!");}
        else
            setMovieViews("Home");
    }
    public void setStars(Double rating, ImageView star1, ImageView star2, ImageView star3, ImageView star4, ImageView star5){
        if(rating <=-4){
            star1.setImage(titleImages.get(11));
            star2.setImage(titleImages.get(12));
            star3.setImage(titleImages.get(12));
            star4.setImage(titleImages.get(12));
            star5.setImage(titleImages.get(12));
        }else if(rating >-4 && rating <= -3){
            star1.setImage(titleImages.get(10));
            star2.setImage(titleImages.get(12));
            star3.setImage(titleImages.get(12));
            star4.setImage(titleImages.get(12));
            star5.setImage(titleImages.get(12));
        } else if (rating > -3 && rating <= -2) {
            star1.setImage(titleImages.get(10));
            star2.setImage(titleImages.get(11));
            star3.setImage(titleImages.get(12));
            star4.setImage(titleImages.get(12));
            star5.setImage(titleImages.get(12));
        } else if (rating > -2 && rating <= 0) {
            star1.setImage(titleImages.get(10));
            star2.setImage(titleImages.get(10));
            star3.setImage(titleImages.get(12));
            star4.setImage(titleImages.get(12));
            star5.setImage(titleImages.get(12));
        }else if (rating > 0 && rating <= 1) {
            star1.setImage(titleImages.get(10));
            star2.setImage(titleImages.get(10));
            star3.setImage(titleImages.get(11));
            star4.setImage(titleImages.get(12));
            star5.setImage(titleImages.get(12));
        }else if (rating > 1 && rating <= 2) {
            star1.setImage(titleImages.get(10));
            star2.setImage(titleImages.get(10));
            star3.setImage(titleImages.get(10));
            star4.setImage(titleImages.get(12));
            star5.setImage(titleImages.get(12));
        }else if (rating > 2 && rating <= 3) {
            star1.setImage(titleImages.get(10));
            star2.setImage(titleImages.get(10));
            star3.setImage(titleImages.get(10));
            star4.setImage(titleImages.get(11));
            star5.setImage(titleImages.get(12));
        }else if (rating > 3 && rating <= 4) {
            star1.setImage(titleImages.get(10));
            star2.setImage(titleImages.get(10));
            star3.setImage(titleImages.get(10));
            star4.setImage(titleImages.get(10));
            star5.setImage(titleImages.get(12));
        }else if (rating > 4 && rating <= 5) {
            star1.setImage(titleImages.get(10));
            star2.setImage(titleImages.get(10));
            star3.setImage(titleImages.get(10));
            star4.setImage(titleImages.get(10));
            star5.setImage(titleImages.get(11));
        }
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
        pageNumber = 0;
        updatePageNumber();
        getRandom();
        scrollPane.setVvalue(0.0);
        fillOutMovies();
    }

    public void clickHome(ActionEvent actionEvent) {
        pageNumber = 0;
        updatePageNumber();
        getHome();
        scrollPane.setVvalue(0.0);
        fillOutMovies();
    }

    public void clickRecommended(ActionEvent actionEvent) {
        pageNumber = 0;
        updatePageNumber();
        getRecommended();
        labelUser.setText("Recommended");
        scrollPane.setVvalue(0.0);
        fillOutMovies();
    }

    public void clickDiscover(ActionEvent actionEvent) {
        pageNumber = 0;
        updatePageNumber();
        getDiscover();
        scrollPane.setVvalue(0.0);
        fillOutMovies();
    }

    public void clickSwitchMode(ActionEvent actionEvent) {
        if(isDarkModeOn == true){
            isDarkModeOn = false;
        }
        else if (isDarkModeOn == false)
        {
            isDarkModeOn = true;
        }
        if(menuHome.isSelected())
        {
            getHome();
        }
        else if (menuRandomMovies.isSelected())
        {
            getRandom();
        }
        else if (menuDiscover.isSelected())
        {
            getDiscover();
        }
        else if (menuRecommended.isSelected())
        {
            getRecommended();
        }
    }

    public void getHome()
    {
        if(isDarkModeOn == true){
            anchorPane.getStylesheets().clear();
            anchorPane.getStylesheets().add(
                    getClass()
                            .getResource("/dk/easv/presentation/view/DarkModeHome.css")
                            .toExternalForm());
        }
        else{
            anchorPane.getStylesheets().clear();
            anchorPane.getStylesheets().add(
                    getClass()
                            .getResource("/dk/easv/presentation/view/Home.css")
                            .toExternalForm());}
    }

    public void getRandom()
    {
        if(isDarkModeOn == true){
            anchorPane.getStylesheets().clear();
            anchorPane.getStylesheets().add(
                    getClass()
                            .getResource("/dk/easv/presentation/view/DarkModeRandom.css")
                            .toExternalForm()
            );}
        else{
            anchorPane.getStylesheets().clear();
            anchorPane.getStylesheets().add(
                    getClass()
                            .getResource("/dk/easv/presentation/view/Random.css")
                            .toExternalForm()

            );}
    }

    public void getDiscover()
    {
        if(isDarkModeOn == true)
        {
            anchorPane.getStylesheets().clear();
            anchorPane.getStylesheets().add(
                    getClass()
                            .getResource("/dk/easv/presentation/view/DarkModeDiscover.css")
                            .toExternalForm()
            );
        }
        else{
            anchorPane.getStylesheets().clear();
            anchorPane.getStylesheets().add(
                    getClass()
                            .getResource("/dk/easv/presentation/view/Discover.css")
                            .toExternalForm()
            );}
    }

    public void getRecommended()
    {
        if(isDarkModeOn==true){
            anchorPane.getStylesheets().clear();
            anchorPane.getStylesheets().add(
                    getClass()
                            .getResource("/dk/easv/presentation/view/DarkModeRecommended.css")
                            .toExternalForm()
            );
        }
        else{
            anchorPane.getStylesheets().clear();
            anchorPane.getStylesheets().add(
                    getClass()
                            .getResource("/dk/easv/presentation/view/Recommended.css")
                            .toExternalForm()
            );}
    }

    public void clickPrevious(ActionEvent actionEvent) {
        if(pageNumber>0){
            pageNumber = pageNumber-5;
            scrollPane.setVvalue(0.0);
            fillOutMovies();
            updatePageNumber();
        }
    }

    public void clickNext(ActionEvent actionEvent) {
        pageNumber+=5;
        scrollPane.setVvalue(0.0);
        fillOutMovies();
        updatePageNumber();
    }
    public void updatePageNumber(){
        labelPageNumber.setText(String.valueOf(((pageNumber/5)+1)));
    }
}
