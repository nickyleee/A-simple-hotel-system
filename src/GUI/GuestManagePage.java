package GUI;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Guest;
import model.HotelSystem;
import model.Room;

import javax.script.Bindings;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuestManagePage {
    private Button buttonInHouse;
    private Button buttonAL;
    private Button buttonDL;
    private VBox hvBox;
    private DatePicker datePicker;
    private ToolBar MainToolBar;
    private ObservableList<Guest> data;
    private TableView<Guest> tableView;
    private Button buttonCheckIn;
    private Button buttonCheckOut;
    private Button buttonSearch;
    private Button cancelButton;
    private TextField textExtraFee;
    private Button searchSubbutton;
    private ChoiceBox searchTypeChoice;
    private TextField searchTypeText;
    private Stage stage;
    private Button buttonCISubmit;
    private Button buttonCICancel;
    private ChoiceBox choiceLoc;
    private TextField tfPrice;
    private TextField tfRoom;

    public void guestManagePage(VBox vBox, List<Guest> list)
    {
        tableView = new TableView();
        tableView.setMinWidth(610);

        tableView.setEditable(true);

        TableColumn Name = new TableColumn("Name");
        Name.setMinWidth(150);
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn room = new TableColumn("Room");
        room.setMinWidth(80);
        room.setCellValueFactory(new PropertyValueFactory<>("roomNum"));

        TableColumn date_in = new TableColumn("Check In");
        date_in.setMinWidth(115);
        date_in.setCellValueFactory(new PropertyValueFactory<>("check_In"));

        TableColumn date_out = new TableColumn("Check out");
        date_out.setMinWidth(115);
        date_out.setCellValueFactory(new PropertyValueFactory<>("check_Out"));

        TableColumn location = new TableColumn("Location");
        location.setMinWidth(80);
        location.setCellValueFactory(new PropertyValueFactory<Guest, Boolean>("location"));

        TableColumn isArrival = new TableColumn("Arrived");
        isArrival.setCellValueFactory(new PropertyValueFactory<>("arrived"));

        data = FXCollections.observableArrayList(list);
        tableView.setItems(data);
        tableView.getColumns().addAll(Name, room, date_in, date_out,location,isArrival);

        datePicker = new DatePicker(LocalDate.now());
        MainToolBar = new ToolBar();

        buttonInHouse = new Button("In house");
        buttonInHouse.setMaxSize(100, 200);
        buttonInHouse.setAlignment(Pos.CENTER);
        setButtonStyle(buttonInHouse);
        buttonAL = new Button("Arrival List");
        buttonAL.setMaxSize(100, 200);
        buttonAL.setAlignment(Pos.CENTER);
        setButtonStyle(buttonAL);

        buttonDL = new Button("Departure List");
        buttonDL.setMaxSize(150, 200);
        buttonDL.setAlignment(Pos.CENTER);
        setButtonStyle(buttonDL);


        MainToolBar.getItems().addAll(buttonInHouse, new Separator(), buttonAL, new Separator(), buttonDL);
        hvBox = new VBox(MainToolBar, tableView);
        HBox hBox = new HBox(datePicker,hvBox);

        hBox.setSpacing(87);
        vBox.getChildren().add(hBox);
    }

    public void addData(ObservableList<Guest> data, List<Guest> guests)
    {
        data.clear();
        for(Guest guest : guests)
        {
            data.addAll(guest);
        }
    }

    public void Manage(VBox vBox)
    {
        ToolBar guestToolBar = new ToolBar();

        TextField textField = new TextField("Room Number");
        textField. setMaxWidth(130);
        textField.clear();
        ChoiceBox choiceLoc = new ChoiceBox();
        choiceLoc.setMaxWidth(180);
        choiceLoc.getItems().addAll("Växjö","Kalmar");

        buttonCheckIn = new Button("Check In");
        buttonCheckIn.setMaxSize(100, 200);
        buttonCheckIn.setAlignment(Pos.CENTER);
        buttonCheckIn.disableProperty().bind(javafx.beans.binding.Bindings.isNull(tableView.getSelectionModel().selectedItemProperty()));
        setButtonStyle(buttonCheckIn);
        buttonCheckOut = new Button("Print Invoice");
        buttonCheckOut.setMaxSize(100, 200);
        buttonCheckOut.setAlignment(Pos.CENTER);
        buttonCheckOut.disableProperty().bind(javafx.beans.binding.Bindings.isNull(tableView.getSelectionModel().selectedItemProperty()));
        setButtonStyle(buttonCheckOut);
        buttonSearch = new Button("Search");
        buttonSearch.setAlignment(Pos.CENTER);
        setButtonStyle(buttonSearch);

        guestToolBar.setPadding(new Insets(5,10,5,10));
        guestToolBar.getItems().addAll(buttonCheckIn, new Separator(), buttonCheckOut, new Separator(),buttonSearch);

        vBox.getChildren().add(guestToolBar);
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

    public void CheckInPage( TableView<Guest> tableView) {
        Guest guest = tableView.getSelectionModel().getSelectedItem();
        stage = new Stage();
        stage.setTitle("Check In");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        choiceLoc = new ChoiceBox();
        choiceLoc.setMaxWidth(180);
        choiceLoc.getItems().addAll("Cash", "Card");

        tfPrice = new TextField();
        tfPrice.setText("" + guest.getPayment().getRoom_Price());

        tfRoom = new TextField();
        tfRoom.setText("" + guest.getRoomNum());

        buttonCISubmit = new Button("Submit");
        buttonCISubmit.disableProperty().bind(javafx.beans.binding.Bindings.isNull(choiceLoc.valueProperty()));

        buttonCICancel = new Button("Cancel Order");
        setButtonStyle(buttonCICancel);
        setButtonStyle(buttonCISubmit);
        gridPane.add(new Label("Name: "), 0, 0);
        gridPane.add(new Label(guest.getName()), 1, 0);
        gridPane.add(new Label("Room"), 0, 1);
        gridPane.add(tfRoom, 1, 1);
        gridPane.add(new Label("Price"), 0, 2);
        gridPane.add(tfPrice, 1, 2);
        gridPane.add(new Label("Payment Type"), 0, 3);
        gridPane.add(choiceLoc, 1, 3);
        gridPane.add(buttonCISubmit, 0, 5);
        gridPane.add(buttonCICancel, 1, 5);
        gridPane.setStyle("-fx-background-color: \n" +
                "        #c3c4c4,\n" +
                "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%)");

        Scene scene = new Scene(gridPane, 330, 215);
        stage.setScene(scene);
        stage.show();

    }

    public void CancelPage()
    {
        stage = new Stage();
        stage.setTitle("Cancel");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        textExtraFee = new TextField();
        textExtraFee.setMaxWidth(100);

        gridPane.add(new Label("Extra Fee"), 0,1);
        gridPane.add(textExtraFee, 1, 1);
        cancelButton = new Button("Submit");
        cancelButton.disableProperty().bind(javafx.beans.binding.Bindings.isEmpty(textExtraFee.textProperty()));

        setButtonStyle(cancelButton);
        gridPane.add(cancelButton, 1, 2);
        gridPane.setStyle("-fx-background-color: \n" +
                "        #c3c4c4,\n" +
                "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%)");

        Scene scene = new Scene(gridPane, 300, 150);
        stage.setScene(scene);
        stage.show();
    }

    public void searchButtonOnAction()
    {
        stage = new Stage();
        stage.setTitle("Search Guest");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(15,15,15,15));

        ChoiceBox searchChoiceLoc = new ChoiceBox();
        searchChoiceLoc.getItems().addAll("Växjö","Kalmar");

        searchTypeChoice = new ChoiceBox();
        searchTypeChoice.getItems().add("Name");
        searchTypeChoice.getItems().add("Room");

        searchTypeText = new TextField();
        searchTypeText.setMaxWidth(80);

        searchSubbutton = new Button("Submit");
        setButtonStyle(searchSubbutton);

        gridPane.add(new Label("Location"), 0,1);
        gridPane.add(searchChoiceLoc,1,1);


        gridPane.add(new Label("Search"),0,2);
        gridPane.add(searchTypeChoice,1,2);
        gridPane.add(searchTypeText,2,2);

        gridPane.add(searchSubbutton,1,3);
        gridPane.setStyle("-fx-background-color: \n" +
                "        #c3c4c4,\n" +
                "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%)");

        searchSubbutton.disableProperty().bind(javafx.beans.binding.Bindings.isEmpty(searchTypeText.textProperty()).or(javafx.beans.binding.Bindings.isEmpty(searchTypeChoice.getProperties())));

        Scene scene = new Scene(gridPane, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    public Button getButtonAL() {
        return buttonAL;
    }

    public Button getButtonDL() {
        return buttonDL;
    }

    public Button getButtonInHouse() {
        return buttonInHouse;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public VBox getHvBox() {
        return hvBox;
    }

    public ObservableList<Guest> getData() {
        return data;
    }

    public TableView<Guest> getTableView() {
        return tableView;
    }

    public ToolBar getMainToolBar() {
        return MainToolBar;
    }

    public Button getButtonSearch() {
        return buttonSearch;
    }

    public Button getButtonCheckIn() {
        return buttonCheckIn;
    }

    public Button getButtonCheckOut() {
        return buttonCheckOut;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public TextField getTextExtraFee() {
        return textExtraFee;
    }

    public Button getSearchSubbutton() {
        return searchSubbutton;
    }

    public ChoiceBox getSearchTypeChoice() {
        return searchTypeChoice;
    }

    public TextField getSearchTypeText() {
        return searchTypeText;
    }

    public Stage getStage() {
        return stage;
    }

    public ChoiceBox getChoiceLoc() {
        return choiceLoc;
    }

    public Button getButtonCICancel() {
        return buttonCICancel;
    }

    public Button getButtonCISubmit() {
        return buttonCISubmit;
    }

    public TextField getTfPrice() {
        return tfPrice;
    }

    public TextField getTfRoom() {
        return tfRoom;
    }
}
