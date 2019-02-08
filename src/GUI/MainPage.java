package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sun.rmi.rmic.Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainPage {
    private Button rmButton;
    private Button gmButton;
    private Button roomManageButoon;
    private VBox mvbox;
    public void mainController() throws IOException
    {
        Stage stage = new Stage();
        stage.setTitle("Manage Page");

        ToolBar toolBar = new ToolBar();
        rmButton = new Button("Reservation Manage");
        rmButton.setMaxSize(200, 200);
        rmButton.setAlignment(Pos.CENTER);
        setButtonStyle(rmButton);
        gmButton = new Button("Guest Manage");
        gmButton.setMaxSize(200, 200);
        gmButton.setAlignment(Pos.CENTER);
        setButtonStyle(gmButton);

        roomManageButoon = new Button("Room Manage");
        roomManageButoon.setMaxSize(200, 200);
        roomManageButoon.setAlignment(Pos.CENTER);
        setButtonStyle(roomManageButoon);

        toolBar.getItems().addAll(rmButton, new Separator(), gmButton, new Separator(), roomManageButoon);

        Group group = new Group();
        group.getChildren().add(toolBar);
        VBox vbox = new VBox(toolBar);
        mvbox = new VBox();
        Image image = new Image(MainPage.class.getResourceAsStream("/resourse/logo.png"), 60, 80,false,false);
        ImageView imageView = new ImageView(image);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        gridPane.add(imageView,0,1);
        Label label = new Label("Welcome to Linnaeus Hotel System");
        label.setFont(new Font("Arial", 20));
        label.setTextFill(Color.valueOf("#524E4E"));
        gridPane.add(label,0,2);

        mvbox.getChildren().addAll(gridPane);
        vbox.getChildren().add(mvbox);
        vbox.setStyle("-fx-background-color: \n" +
                "        #c3c4c4,\n" +
                "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%)");

        Scene scene = new Scene(vbox, 900, 380);
        stage.setScene(scene);
        stage.show();
    }

    private void setButtonStyle(Button button)
    {
        button.setStyle("-fx-background-color: \n" +
                "        #c3c4c4,\n" +
                "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%)");
        button.setStyle("-fx-background-radius: 30");
        button.setStyle("-fx-background-insets: 0,1,1");
        button.setTextFill(Color.valueOf("#524E4E"));
    }


    public Button getGmButton() {
        return gmButton;
    }

    public Button getRmButton() {
        return rmButton;
    }

    public Button getRoomManageButoon() {
        return roomManageButoon;
    }

    public VBox getMvbox() {
        return mvbox;
    }
}
