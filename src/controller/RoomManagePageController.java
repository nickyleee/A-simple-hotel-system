package controller;

import GUI.RoomManagePage;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.HotelSystem;
import model.Room;

public class RoomManagePageController {

    public void roomManageController(VBox vBox, HotelSystem hotelSystem)
    {
        RoomManagePage roomManagePage = new RoomManagePage();
        roomManagePage.roomManagePage(vBox,hotelSystem.queryAllRoom());

        roomManagePage.getMainAddButton().setOnAction(value ->{
            AddRoomPage(roomManagePage,hotelSystem);
        });

        roomManagePage.getMainSearchButton().setOnAction(value ->{
            roomManagePage.getData().clear();
            for(Room room : hotelSystem.getRoomNUMSearchRoom(roomManagePage.getTfSearch().getText().toString()))
            {
                roomManagePage.getData().addAll(room);
            }
        });

        roomManagePage.getMainEditButton().setOnAction(value ->{
            EditRoom(roomManagePage,hotelSystem);
        });

        roomManagePage.getMainDeleteButton().setOnAction(value ->{
            Room room = roomManagePage.getTableView().getSelectionModel().getSelectedItem();
            roomManagePage.getTableView().getItems().remove(room);
            hotelSystem.deleteRoom(room);
        });

    }

    private void AddRoomPage(RoomManagePage roomManagePage,HotelSystem hotelSystem)
    {
        roomManagePage.RoomInformationPage("Add Room");
        roomManagePage.getSubmitButton().setOnAction(v-> {
            Room r = new Room(Integer.parseInt(roomManagePage.getTextRoom().getText()),
                    roomManagePage.getChoiceBox().getValue().toString(),
                    roomManagePage.getCheckSmoking().isSelected(),
                    roomManagePage.getCheckAnimal().isSelected(),
                    roomManagePage.getChoiceView().getValue().toString(),
                    roomManagePage.getChoiceloc().getValue().toString(),
                    Integer.parseInt(roomManagePage.getChoiceFloor().getValue().toString()),
                    roomManagePage.getAdjoinRoom().getText().toString());
            roomManagePage.getData().add(r);
            hotelSystem.addRoom(r);
            roomManagePage.getStage().close();
        });
        roomManagePage.getCancelButton().setOnAction(v ->{
            roomManagePage.getStage().close();
        });

    }
    private void EditRoom(RoomManagePage roomManagePage,HotelSystem hotelSystem)
    {
        roomManagePage.RoomInformationPage("Edit Room");
        Room room = roomManagePage.getTableView().getSelectionModel().getSelectedItem();
        roomManagePage.getTextRoom().setText("" + room.getRoom_no());
        roomManagePage.getChoiceBox().setValue(room.getRoom_type());
        roomManagePage.getChoiceloc().setValue(room.getRoom_location());
        roomManagePage.getCheckSmoking().setSelected(room.isSmoking());
        roomManagePage.getCheckAnimal().setSelected(room.isAnimal());
        roomManagePage.getChoiceFloor().setValue(room.getFloor());
        roomManagePage.getChoiceView().setValue(room.getView());

        roomManagePage.getSubmitButton().setOnAction(v-> {
            Room r = new Room(Integer.parseInt(roomManagePage.getTextRoom().getText()),
                    roomManagePage.getChoiceBox().getValue().toString(),
                    roomManagePage.getCheckSmoking().isSelected(),
                    roomManagePage.getCheckAnimal().isSelected(),
                    roomManagePage.getChoiceView().getValue().toString(),
                    roomManagePage.getChoiceloc().getValue().toString(),
                    Integer.parseInt(roomManagePage.getChoiceFloor().getValue().toString()),
                    roomManagePage.getAdjoinRoom().getText().toString());

            roomManagePage.getData().remove(room);
            roomManagePage.getData().add(r);
            hotelSystem.editRoom(r);
            roomManagePage.getStage().close();
        });
        roomManagePage.getCancelButton().setOnAction(v ->{
            roomManagePage.getStage().close();
        });
    }

}
