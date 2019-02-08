package GUI;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
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
import javafx.util.Callback;
import model.Guest;
import model.Room;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class ReservationManagePage {
    private ChoiceBox choiceBox;
    private TextField textID;
    private TextField textN;
    private TextField textCompany;
    private TextField textAddress;
    private TextField textTEL;
    private TextField textEmail;
    private DatePicker datePickerAT;
    private DatePicker datePickerDT;
    private TextField textDays;
    private TextField textCard;
    private TextField textPrice;
    private TextField textRoom;
    private Button button;
    private CheckBox checkSmoking;
    private Button submit;
    private ObservableList<Room> data;
    private ChoiceBox choiceLoc;
    private ChoiceBox choiceType;
    private Button buttonSearch;
    private Button buttonSub;
    private TableView<Room> tableView;
    private Stage stage;

    public void reservationManagePage(VBox vBox)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25,25,25,25));

        choiceBox = new ChoiceBox();
        choiceBox.getItems().add("Passport");
        choiceBox.getItems().add("Personal Number");
        choiceBox.getItems().add("Driving License");
        textID = new TextField();
        textN = new TextField();
        textCompany = new TextField();
        textAddress = new TextField();
        textTEL = new TextField();
        textEmail = new TextField();

        gridPane.add(new Label("ID"), 0, 1);
        gridPane.add(choiceBox, 1, 1);
        gridPane.add(textID, 2,1);

        gridPane.add(new Label("Name: "), 0, 2);
        gridPane.add(textN, 1, 2);

        gridPane.add(new Label("Company: "), 0, 3);
        gridPane.add(textCompany, 1, 3);

        gridPane.add(new Label("Address: "), 0, 4);
        gridPane.add(textAddress, 1, 4);

        gridPane.add(new Label("TEL: "), 0, 5);
        gridPane.add(textTEL, 1, 5);

        gridPane.add(new Label("Email: "), 0, 6);
        gridPane.add(textEmail, 1, 6);

        datePickerAT = new DatePicker(LocalDate.now());
        datePickerDT = new DatePicker();
        setDatePicker(datePickerAT,datePickerDT);
        textDays = new TextField();
        textCard = new TextField();
        textPrice = new TextField();
        textRoom = new TextField();
        button = new Button("Check Availability");
        setButtonStyle(button);
        checkSmoking = new CheckBox("Smoker");
        submit = new Button("Submit");
        setButtonStyle(submit);
        BooleanBinding booleanBind = javafx.beans.binding.Bindings.isEmpty(textID.textProperty()).or(Bindings.isEmpty(textN.textProperty()))
                .or(Bindings.isEmpty(textCompany.textProperty())).or(Bindings.isEmpty(textAddress.textProperty())).or(Bindings.isEmpty(textTEL.textProperty()))
                .or(Bindings.isEmpty(textEmail.textProperty())).or(Bindings.isEmpty(textDays.textProperty())).or(Bindings.isEmpty(textCard.textProperty()))
                .or(Bindings.isEmpty(textPrice.textProperty())).or(Bindings.isEmpty(textRoom.textProperty())).or(Bindings.isNull(datePickerAT.valueProperty()))
                .or(Bindings.isNull(datePickerDT.valueProperty()).or(Bindings.isNull(choiceBox.valueProperty())));
        submit.disableProperty().bind(booleanBind);

        gridPane.add(submit, 2,8);
        gridPane.add(new Label("Arrival Time: "), 3, 1);
        gridPane.add(datePickerAT, 4, 1);

        gridPane.add(new Label("Departure Time: "), 3, 2);
        gridPane.add(datePickerDT, 4, 2);

        gridPane.add(new Label("Days: "), 3, 4);
        gridPane.add(textDays, 4, 4);

        gridPane.add(new Label("Card: "), 3, 5);
        gridPane.add(textCard, 4, 5);

        gridPane.add(new Label("Price"), 3, 6);
        gridPane.add(textPrice, 4, 6);

        gridPane.add(checkSmoking, 3,7);

        gridPane.add(new Label("Room: "), 3,3);
        gridPane.add(textRoom, 4,3);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);

        button.disableProperty().bind(Bindings.or(datePickerDT.valueProperty().isNull(), datePickerDT.valueProperty().isNull()));

        hbBtn.getChildren().add(button);

        gridPane.add(hbBtn, 4, 7);
        vBox.getChildren().add(gridPane);

    }

    public void checkAvailability(List<Room> roomList)
    {
        data = FXCollections.observableArrayList(roomList);
        stage = new Stage();
        stage.setTitle("Check Room");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(15,15,15,15));

        choiceType = new ChoiceBox();
        choiceType.getItems().add("Single Room");
        choiceType.getItems().add("Double Room");
        choiceType.getItems().add("Triple Room");
        choiceType.getItems().add("Apartment");

        choiceLoc = new ChoiceBox();
        choiceLoc.setMaxWidth(180);
        choiceLoc.getItems().addAll("Växjö","Kalmar");

        buttonSearch = new Button("Search");
        setButtonStyle(buttonSearch);
        buttonSearch.disableProperty().bind(javafx.beans.binding.Bindings.isNull(choiceType.valueProperty()).or(Bindings.isNull(choiceLoc.valueProperty())));

        buttonSub = new Button("Submit");
        buttonSub.disableProperty().bind(javafx.beans.binding.Bindings.isNull(choiceType.valueProperty()).or(Bindings.isNull(choiceLoc.valueProperty())));

        setButtonStyle(buttonSub);

        gridPane.add(new Label("Room Type"), 0,1);
        gridPane.add(choiceType, 1, 1);
        gridPane.add(new Label("Location"),2,1);
        gridPane.add(choiceLoc,3,1);

        gridPane.add(buttonSearch,4,1);

        gridPane.add(buttonSub,5,1);

        tableView = new TableView();

        TableColumn room_NO = new TableColumn("Room");
        room_NO.setMinWidth(100);
        room_NO.setCellValueFactory(new PropertyValueFactory<>("room_no"));

        TableColumn floor = new TableColumn("Floor");
        floor.setCellValueFactory(new PropertyValueFactory<>("floor"));

        TableColumn type = new TableColumn("Type");
        type.setMinWidth(150);
        type.setCellValueFactory(new PropertyValueFactory<>("room_type"));

        TableColumn view = new TableColumn("View");
        view.setMinWidth(180);
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

        tableView.setItems(data);
        tableView.getColumns().addAll(room_NO,floor,smoking,animal,Location,type,view,adjoinRoomC);
        VBox vBox = new VBox(gridPane,tableView);

        gridPane.setStyle("-fx-background-color: \n" +
                "        #c3c4c4,\n" +
                "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%)");

        Scene scene = new Scene(vBox, 818.5, 300);
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

    private void setDatePicker(DatePicker startDateEvent, DatePicker endDateEvent)
    {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>()
        {
            public DateCell call(final DatePicker datePicker)
            {
                return new DateCell()
                {
                    public void updateItem(LocalDate item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now().plusDays(0)))
                        {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }

                        if(startDateEvent.getValue()!=null)
                        {
                            if(item.isBefore(startDateEvent.getValue().plusDays(1)))
                            {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                            long days = ChronoUnit.DAYS.between(startDateEvent.getValue(), item);
                            setTooltip(new Tooltip("You're choose " + days + " days"));
                        }
                    }
                };
            }
        };

        final Callback<DatePicker, DateCell> startDayCellFactory = new Callback<DatePicker, DateCell>()
        {
            public DateCell call(final DatePicker datePicker)
            {
                return new DateCell()
                {
                    public void updateItem(LocalDate item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now().plusDays(0)))
                        {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }

                        if(endDateEvent.getValue()!=null)
                        {
                            if(item.isAfter(endDateEvent.getValue().plusDays(0)))
                            {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                        }
                    }
                };
            }
        };
        endDateEvent.setDayCellFactory(dayCellFactory);
        startDateEvent.setDayCellFactory(startDayCellFactory);
    }

    public TextField getTextRoom() {
        return textRoom;
    }

    public ChoiceBox getChoiceBox() {
        return choiceBox;
    }

    public DatePicker getDatePickerAT() {
        return datePickerAT;
    }

    public DatePicker getDatePickerDT() {
        return datePickerDT;
    }

    public TextField getTextAddress() {
        return textAddress;
    }

    public TextField getTextCard() {
        return textCard;
    }

    public Button getButton() {
        return button;
    }

    public TextField getTextCompany() {
        return textCompany;
    }

    public TextField getTextDays() {
        return textDays;
    }

    public TextField getTextEmail() {
        return textEmail;
    }

    public TextField getTextID() {
        return textID;
    }

    public TextField getTextN() {
        return textN;
    }

    public TextField getTextTEL() {
        return textTEL;
    }

    public TextField getTextPrice() {
        return textPrice;
    }

    public CheckBox getCheckSmoking() {
        return checkSmoking;
    }

    public Button getSubmit() {
        return submit;
    }

    public ObservableList<Room> getData() {
        return data;
    }

    public Button getButtonSearch() {
        return buttonSearch;
    }

    public ChoiceBox getChoiceLoc() {
        return choiceLoc;
    }

    public ChoiceBox getChoiceType() {
        return choiceType;
    }

    public Button getButtonSub() {
        return buttonSub;
    }

    public TableView<Room> getTableView() {
        return tableView;
    }

    public Stage getStage() {
        return stage;
    }
}
