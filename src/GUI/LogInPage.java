package GUI;

import javafx.beans.binding.Bindings;
import javafx.css.Styleable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LogInPage {
    private Button button;
    private PasswordField pwBox;
    private TextField userTextField;
    private Stage stageLogIn;

    public void loginPage() {
        stageLogIn = new Stage();
        stageLogIn.setTitle("lOG IN");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        javafx.scene.text.Text scenetitle = new javafx.scene.text.Text("Welcome To Linnaeus Hotel");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        scenetitle.setFill(Color.valueOf("#524E4E"));
        gridPane.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        gridPane.add(userName, 0, 1);

        userTextField = new TextField();
        gridPane.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        gridPane.add(pw, 0, 2);

        pwBox = new PasswordField();
        gridPane.add(pwBox, 1, 2);

        button = new Button("Sign in");
        button.setStyle("-fx-background-color: \n" +
                "        #c3c4c4,\n" +
                "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%)");
        button.setStyle("-fx-background-radius: 30");
        button.setStyle("-fx-background-insets: 0,1,1");
        button.setTextFill(Color.valueOf("#524E4E"));
        button.disableProperty().bind(Bindings.or(userTextField.textProperty().isEmpty(), pwBox.textProperty().isEmpty()));

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(button);
        gridPane.add(hBox, 1, 4);

        Scene scene = new Scene(gridPane, 300, 275);
        gridPane.setStyle("-fx-background-color: \n" +
                "        #c3c4c4,\n" +
                "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%)");
        stageLogIn.setScene(scene);
        stageLogIn.show();
    }

    public void alert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Wrong username or wrong password");
        alert.showAndWait();
    }

    public Button getButton() {
        return button;
    }

    public PasswordField getPwBox() {
        return pwBox;
    }

    public Stage getStageLogIn() {
        return stageLogIn;
    }

    public TextField getUserTextField() {
        return userTextField;
    }
}

