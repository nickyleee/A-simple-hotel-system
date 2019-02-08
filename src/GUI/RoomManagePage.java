package GUI;

import javafx.beans.binding.Binding;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.HotelSystem;
import model.Room;

import javax.script.Bindings;
import java.util.List;


public class RoomManagePage {
    private final int FLOOR = 5;
    private Button mainAddButton;
    private Button mainEditButton;
    private Button mainDeleteButton;
    private Button mainSearchButton;
    private TableView<Room> tableView;
    private ObservableList<Room> data;
    private TextField tfSearch;
    private GridPane gridPane;
    private  Stage stage;
    private Button submitButton;
    private Button cancelButton;
    private TextField textRoom;
    private ChoiceBox choiceBox;
    private ChoiceBox choiceloc;
    private CheckBox checkSmoking;
    private CheckBox checkAnimal;
    private ChoiceBox choiceFloor;
    private ChoiceBox choiceView;
    private TextField adjoinRoom;

    public void roomManagePage(VBox vBox, List<Room> rooms) {
        tableView = new TableView();

        tableView.getColumns().clear();
        tableView.setEditable(true);

        TableColumn room_NO = new TableColumn("Room");
        room_NO.setMinWidth(100);
        room_NO.setCellValueFactory(new PropertyValueFactory<>("room_no"));

        TableColumn floor = new TableColumn("Floor");
        floor.setCellValueFactory(new PropertyValueFactory<>("floor"));

        TableColumn type = new TableColumn("Type");
        type.setMinWidth(150);
        type.setCellValueFactory(new PropertyValueFactory<>("room_type"));

        TableColumn view = new TableColumn("View");
        view.setMinWidth(200);
        view.setCellValueFactory(new PropertyValueFactory<>("view"));

        TableColumn smoking = new TableColumn("Smoking");
        smoking.setCellValueFactory(new PropertyValueFactory<>("smoking"));

        TableColumn animal = new TableColumn("Animal");
        animal.setCellValueFactory(new PropertyValueFactory<>("animal"));

        TableColumn Location = new TableColumn("Location");
        Location.setMinWidth(100);
        Location.setCellValueFactory(new PropertyValueFactory<>("room_location"));

        TableColumn adjoinRoomC = new TableColumn("Adjoin Room");
        adjoinRoomC.setMinWidth(100);
        adjoinRoomC.setCellValueFactory(new PropertyValueFactory<>("adjoinRoom"));

        data = FXCollections.observableArrayList(rooms);
        tableView.setItems(data);
        tableView.getColumns().addAll(room_NO, floor, smoking, animal, Location, type, view, adjoinRoomC);

        ToolBar toolBar = new ToolBar();

        mainAddButton = new Button("Add");
        mainAddButton.setMaxSize(100, 200);
        mainAddButton.setAlignment(Pos.CENTER);
        setButtonStyle(mainAddButton);

        mainEditButton = new Button("Edit");
        mainEditButton.setMaxSize(100, 200);
        mainEditButton.setAlignment(Pos.CENTER);
        mainEditButton.disableProperty().bind(javafx.beans.binding.Bindings.isNull(tableView.getSelectionModel().selectedItemProperty()));
        setButtonStyle(mainEditButton);

        mainDeleteButton = new Button("Delete");
        mainDeleteButton.setMaxSize(100, 200);
        mainDeleteButton.setAlignment(Pos.CENTER);
        setButtonStyle(mainDeleteButton);

        tfSearch = new TextField();

        mainSearchButton = new Button("Search");
        mainSearchButton.setMaxSize(100, 200);
        mainSearchButton.setAlignment(Pos.CENTER);
        mainSearchButton.disableProperty().bind(javafx.beans.binding.Bindings.isEmpty(tfSearch.textProperty()));

        toolBar.getItems().addAll(mainAddButton, new Separator(), mainEditButton, new Separator(), mainDeleteButton, new Separator(), new Label("Room"), tfSearch, mainSearchButton, new Separator());

        vBox.getChildren().addAll(tableView, toolBar);
    }

    public void RoomInformationPage(String title)
    {
        stage = new Stage();
        stage.setTitle(title);

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(15,15,15,15));

        textRoom = new TextField();

        choiceBox = new ChoiceBox();
        choiceBox.getItems().add("Single Room");
        choiceBox.getItems().add("Double Room");
        choiceBox.getItems().add("Triple Room");
        choiceBox.getItems().add("Apartment");

        choiceloc = new ChoiceBox();
        choiceloc.getItems().addAll("Växjö","Kalmar");

        checkSmoking = new CheckBox("Smoking");
        checkAnimal = new CheckBox("Animal");

        choiceFloor = new ChoiceBox();
        for(int i = 0; i < FLOOR; i++)
        {
            choiceFloor.getItems().add(i+1);

        }

        choiceView = new ChoiceBox();
        choiceView.getItems().add("Larger Room with View");
        choiceView.getItems().add("Small Room with View");
        adjoinRoom = new TextField();

        gridPane.add(new Label("Room"), 0,1);
        gridPane.add(textRoom, 1, 1);

        gridPane.add(new Label("Room Type"), 0,2);
        gridPane.add(choiceBox, 1, 2);

        gridPane.add(new Label("View"), 0,3);
        gridPane.add(choiceView,1,3);

        gridPane.add(new Label("Location"), 0,5);
        gridPane.add(choiceloc,1,5);

        gridPane.add(new Label("Floor"),0,4);
        gridPane.add(choiceFloor, 1,4);

        gridPane.add(checkSmoking, 0,7);
        gridPane.add(checkAnimal,1,7);

        gridPane.add(new Label("Adjoin Room"),0,6);
        gridPane.add(adjoinRoom, 1,6);
        submitButton = new Button("Submit");
        cancelButton = new Button("Cancel");
        setButtonStyle(submitButton);
        setButtonStyle(cancelButton);

        gridPane.add(submitButton, 0,8);
        gridPane.add(cancelButton,1,8);
        gridPane.setStyle("-fx-background-color: \n" +
                "        #c3c4c4,\n" +
                "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%)");

        VBox vBox = new VBox(gridPane);
        Scene scene = new Scene(vBox, 350, 310);
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

    public Button getMainAddButton() {
        return mainAddButton;
    }

    public Button getMainDeleteButton() {
        return mainDeleteButton;
    }

    public Button getMainEditButton() {
        return mainEditButton;
    }

    public Button getMainSearchButton() {
        return mainSearchButton;
    }

    public ObservableList<Room> getData() {
        return data;
    }

    public TableView<Room> getTableView() {
        return tableView;
    }

    public TextField getTfSearch() {
        return tfSearch;
    }

    public Stage getStage() {
        return stage;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public CheckBox getCheckAnimal() {
        return checkAnimal;
    }

    public CheckBox getCheckSmoking() {
        return checkSmoking;
    }

    public ChoiceBox getChoiceBox() {
        return choiceBox;
    }

    public ChoiceBox getChoiceFloor() {
        return choiceFloor;
    }

    public ChoiceBox getChoiceloc() {
        return choiceloc;
    }

    public TextField getTextRoom() {
        return textRoom;
    }

    public ChoiceBox getChoiceView() {
        return choiceView;
    }

    public TextField getAdjoinRoom() {
        return adjoinRoom;
    }
}
