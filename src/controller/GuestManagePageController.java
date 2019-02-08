package controller;

import GUI.GuestManagePage;
import GUI.PrintBill;
import javafx.scene.layout.VBox;
import model.Guest;
import model.HotelSystem;
import model.Room;

public class GuestManagePageController {
    public void guestPageController(VBox vBox, HotelSystem hotelSystem)
    {
        GuestManagePage guestManagePage = new GuestManagePage();
        guestManagePage.guestManagePage(vBox,hotelSystem.getALLGuest());

        guestManagePage.getButtonInHouse().setOnAction(value ->{
            guestManagePage.addData(guestManagePage.getData(),hotelSystem.getInhouseRoom(guestManagePage.getDatePicker().getValue().toString()));
            guestManagePage.getHvBox().getChildren().clear();
            guestManagePage.getHvBox().getChildren().addAll(guestManagePage.getMainToolBar(), guestManagePage.getTableView());
            Manage(guestManagePage,hotelSystem,guestManagePage.getTableView());
            });

        guestManagePage.getButtonAL().setOnAction(value ->{
            guestManagePage.addData(guestManagePage.getData(),hotelSystem.getArrivalSearchGuest(guestManagePage.getDatePicker().getValue().toString()));
            guestManagePage.getHvBox().getChildren().clear();
            guestManagePage.getHvBox().getChildren().addAll(guestManagePage.getMainToolBar(), guestManagePage.getTableView());
            Manage(guestManagePage,hotelSystem,guestManagePage.getTableView());
        });

        guestManagePage.getButtonDL().setOnAction(value ->{
            guestManagePage.addData(guestManagePage.getData(),hotelSystem.getDepartureSearchGuest(guestManagePage.getDatePicker().getValue().toString()));
            guestManagePage.getHvBox().getChildren().clear();
            guestManagePage.getHvBox().getChildren().addAll(guestManagePage.getMainToolBar(), guestManagePage.getTableView());
            Manage(guestManagePage,hotelSystem,guestManagePage.getTableView());
        });
    }

    public void Manage(GuestManagePage guestManagePage, HotelSystem hotelSystem, javafx.scene.control.TableView<Guest> tableView)
    {
        guestManagePage.Manage(guestManagePage.getHvBox());
        guestManagePage.getButtonCheckIn().setOnAction(v -> {
            Guest guest = tableView.getSelectionModel().getSelectedItem();
            guestManagePage.CheckInPage(tableView);
            guestManagePage.getButtonCISubmit().setOnAction(e ->{
                guest.setArrived(true);
                guest.setRoomNum(Integer.parseInt(guestManagePage.getTfRoom().getText()));
                guest.getPayment().setPayment_Type(guestManagePage.getChoiceLoc().getValue().toString());
                guest.getPayment().setRoom_Price(Double.parseDouble(guestManagePage.getTfPrice().getText()));
                guest.getPayment().setTotal_Cost(guest.getPayment().getRoom_Price() * guest.getPayment().getNumber_days());
                guestManagePage.getData().remove(guest);
                guestManagePage.getData().add(guest);
                hotelSystem.editGuest(guest);
                guestManagePage.getStage().close();
            });
            guestManagePage.getButtonCICancel().setOnAction(e -> {
                guestManagePage.getStage().close();
                guestManagePage.CancelPage();
                guestManagePage.getCancelButton().setOnAction(c -> {
                    guest.getPayment().setExtra_fee(Double.parseDouble(guestManagePage.getTextExtraFee().getText()));
                    guest.getPayment().setTotal_Cost(Double.parseDouble(guestManagePage.getTextExtraFee().getText()));
                    guest.getPayment().setNumber_days(0);
                    guest.setArrived(false);
                    hotelSystem.editGuest(guest);
                    print(guest,hotelSystem);
                    guestManagePage.getStage().close();
                });
            });

        });

        guestManagePage.getButtonCheckOut().setOnAction(v -> {
            Guest guest = tableView.getSelectionModel().getSelectedItem();
            print(guest,hotelSystem);
        });

        guestManagePage.getButtonSearch().setOnAction(v -> {
            guestManagePage.searchButtonOnAction();
            guestManagePage.getSearchSubbutton().setOnAction(value ->{
                if(guestManagePage.getSearchTypeChoice().getValue().toString().equalsIgnoreCase("Room"))
                {
                    guestManagePage.addData(guestManagePage.getData(),hotelSystem.getRoomNUMSearchGuest(guestManagePage.getSearchTypeText().getText(),hotelSystem.getInhouseRoom(guestManagePage.getDatePicker().getValue().toString())));
                } else if(guestManagePage.getSearchTypeChoice().getValue().toString().equalsIgnoreCase("Name"))
                {
                    guestManagePage.addData(guestManagePage.getData(),hotelSystem.getRoomNUMSearchGuest(guestManagePage.getSearchTypeText().getText(),hotelSystem.getInhouseRoom(guestManagePage.getDatePicker().getValue().toString())));
                }
                guestManagePage.getStage().close();
            });
        });

    }

    private void print(Guest guest, HotelSystem hotelSystem)
    {
        StringBuffer stringBuffer = new StringBuffer();
        for(Room room: hotelSystem.getRoomNUMSearchRoom("" + guest.getRoomNum()))
        {
            if(room.getRoom_location().equalsIgnoreCase(guest.getLocation()))
            {
                stringBuffer.append("Type: " + room.getRoom_type() + "\n" + "View: " + room.getView()+"\n");
                stringBuffer.append("Animal: " + room.isAnimal() + "\n" + "Smoking: " + room.isSmoking()+"\n");
            }
        }
        new PrintBill().print(guest,stringBuffer.toString());
    }
}
