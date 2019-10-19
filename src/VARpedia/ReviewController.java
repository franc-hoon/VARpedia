package VARpedia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReviewController implements Initializable{
    public ImageView star1;
    public ImageView star2;
    public ImageView star3;
    public ImageView star4;
    public ImageView star5;
    public TableColumn<Creation, String> tableName;
    public TableColumn<Creation, Integer> tableRating;
    public TableColumn<Creation, Integer> tableViewed;
    public TableView table;
    public Text creationName;
    ObservableList<Creation> creationObservableList = FXCollections.observableArrayList();

    public void initData(ObservableList<Creation> creationObservableList){
        this.creationObservableList = creationObservableList;
        setTable();
    }

    public void handlePlayButton(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No Creation selected");
            alert.show();
        } else {
            /*
              Create and setup Media, MediaPlayer and MediaView before switching scene
             */
            Creation creation = (Creation) table.getSelectionModel().getSelectedItem();
            String fileName = creation.getName() + ".mp4";
            File videoFile = new File("creations/" + fileName);
            Media video = new Media(videoFile.toURI().toString());
            MediaPlayer player = new MediaPlayer(video);
            player.setAutoPlay(true);
            player.setOnReady(new Runnable() {
                @Override
                public void run() {
                    MediaView mediaView = new MediaView(player);

                    mediaView.setFitHeight(360);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("media.fxml"));

                    MediaController mediaController = new MediaController(player);
                    loader.setController(mediaController);
                    BorderPane root = null;
                    try {
                        root = (BorderPane) loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    root.setCenter(mediaView);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root));
                    window.show();
                }
            });

        }
    }

    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainMenu.fxml"));
        Parent mainParent = loader.load();

        Scene mainMenu = new Scene(mainParent);

        MainMenuController mainMenuController = loader.getController();
        mainMenuController.initData(creationObservableList);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(mainMenu);
        window.show();
        window.setHeight(429);
        window.setWidth(640);
    }

    public void handleClick1(MouseEvent mouseEvent) {
        setStar1();
        Creation creation = (Creation) table.getSelectionModel().getSelectedItem();
        creation.setConfidenceRating(1);
        setTable();
    }

    public void handleClick2(MouseEvent mouseEvent) {
        setStar2();
        Creation creation = (Creation) table.getSelectionModel().getSelectedItem();
        creation.setConfidenceRating(2);
        setTable();
    }

    public void handleClick3(MouseEvent mouseEvent) {
        setStar3();
        Creation creation = (Creation) table.getSelectionModel().getSelectedItem();
        creation.setConfidenceRating(3);
        setTable();
    }

    public void handleClick4(MouseEvent mouseEvent) {
        setStar4();
        Creation creation = (Creation) table.getSelectionModel().getSelectedItem();
        creation.setConfidenceRating(4);
        setTable();
    }


    public void handleClick5(MouseEvent mouseEvent) {
        setStar5();
        Creation creation = (Creation) table.getSelectionModel().getSelectedItem();
        creation.setConfidenceRating(5);
        setTable();
    }

    public void setTable() {
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableRating.setCellValueFactory(new PropertyValueFactory<>("confidenceRating"));
        tableViewed.setCellValueFactory(new PropertyValueFactory<>("viewCount"));
        table.setItems(creationObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Creation creation = (Creation) table.getSelectionModel().getSelectedItem();
                creationName.setText(creation.getName());
                if (creation.getConfidenceRating() == 0) {
                    setStar0();
                }
                if (creation.getConfidenceRating() == 1) {
                    setStar1();
                }
                if (creation.getConfidenceRating() == 2) {
                    setStar2();
                }
                if (creation.getConfidenceRating() == 3) {
                    setStar3();
                }
                if (creation.getConfidenceRating() == 4) {
                    setStar4();
                }
                if (creation.getConfidenceRating() == 5) {
                    setStar5();
                }
            }
        });
    }

    public void setStar0() {
        star1.setImage(new Image("Images/icons8-star-48.png"));
        star2.setImage(new Image("Images/icons8-star-48.png"));
        star3.setImage(new Image("Images/icons8-star-48.png"));
        star4.setImage(new Image("Images/icons8-star-48.png"));
        star5.setImage(new Image("Images/icons8-star-48.png"));
    }

    public void setStar1() {
        star1.setImage(new Image("Images/icons8-star-filled-48.png"));
        star2.setImage(new Image("Images/icons8-star-48.png"));
        star3.setImage(new Image("Images/icons8-star-48.png"));
        star4.setImage(new Image("Images/icons8-star-48.png"));
        star5.setImage(new Image("Images/icons8-star-48.png"));
    }
    public void setStar2() {
        star1.setImage(new Image("Images/icons8-star-filled-48.png"));
        star2.setImage(new Image("Images/icons8-star-filled-48.png"));
        star3.setImage(new Image("Images/icons8-star-48.png"));
        star4.setImage(new Image("Images/icons8-star-48.png"));
        star5.setImage(new Image("Images/icons8-star-48.png"));
    }
    public void setStar3() {
        star1.setImage(new Image("Images/icons8-star-filled-48.png"));
        star2.setImage(new Image("Images/icons8-star-filled-48.png"));
        star3.setImage(new Image("Images/icons8-star-filled-48.png"));
        star4.setImage(new Image("Images/icons8-star-48.png"));
        star5.setImage(new Image("Images/icons8-star-48.png"));
    }
    public void setStar4() {
        star1.setImage(new Image("Images/icons8-star-filled-48.png"));
        star2.setImage(new Image("Images/icons8-star-filled-48.png"));
        star3.setImage(new Image("Images/icons8-star-filled-48.png"));
        star4.setImage(new Image("Images/icons8-star-filled-48.png"));
        star5.setImage(new Image("Images/icons8-star-48.png"));
    }
    public void setStar5() {
        star1.setImage(new Image("Images/icons8-star-filled-48.png"));
        star2.setImage(new Image("Images/icons8-star-filled-48.png"));
        star3.setImage(new Image("Images/icons8-star-filled-48.png"));
        star4.setImage(new Image("Images/icons8-star-filled-48.png"));
        star5.setImage(new Image("Images/icons8-star-filled-48.png"));
    }
}
