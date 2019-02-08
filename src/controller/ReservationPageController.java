package controller;

import GUI.ReservationManagePage;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;
import model.Guest;
import model.HotelSystem;
import model.Payment;
import model.Room;

public class ReservationPageController {
    public void reservationPage(VBox vBox, HotelSystem hotelSystem)
    {
        class LocationHolder { String location; }
        LocationHolder locationHolder = new LocationHolder();
        locationHolder.location = "" ;

        ReservationManagePage reservationManagePage = new ReservationManagePage();
        reservationManagePage.reservationManagePage(vBox);

        reservationManagePage.getSubmit().setOnAction(value ->{
            Payment payment = new Payment(Integer.parseInt(reservationManagePage.getTextDays().getText()),0.0," ",reservationManagePage.getTextCard().getText(), Double.parseDouble(reservationManagePage.getTextPrice().getText()));
            Guest guest = new Guest(reservationManagePage.getChoiceBox().getValue().toString(),reservationManagePage.getTextID().getText(),reservationManagePage.getTextN().getText(),reservationManagePage.getTextAddress().getText(),
                    Integer.parseInt(reservationManagePage.getTextTEL().getText()),reservationManagePage.getTextEmail().getText(),Integer.parseInt(reservationManagePage.getTextRoom().getText().toString()),reservationManagePage.getDatePickerAT().getValue().toString(),reservationManagePage.getDatePickerDT().getValue().toString()," ",reservationManagePage.getTextCompany().getText(),reservationManagePage.getCheckSmoking().isSelected(),payment);
            guest.getPayment().setTotal_Cost(guest.getPayment().getRoom_Price()*guest.getPayment().getNumber_days());
            guest.setLocation(locationHolder.location);
            hotelSystem.addGuest(guest);
            vBox.getChildren().clear();
            reservationManagePage.reservationManagePage(vBox);
        });

        reservationManagePage.getButton().setOnAction(value -> {
            Guest guest = new Guest("","","","",0,"",0,reservationManagePage.getDatePickerAT().getValue().toString(),reservationManagePage.getDatePickerDT().getValue().toString()," ","",false,new Payment());
            reservationManagePage.checkAvailability(hotelSystem.queryAllRoom());
            reservationManagePage.getButtonSearch().setOnAction(v ->{
                Room room = new Room(0,reservationManagePage.getChoiceType().getValue().toString(),false,false,"",reservationManagePage.getChoiceLoc().getValue().toString(),0,"");
                reservationManagePage.getData().clear();
                for(Room r : hotelSystem.getMutipleSearch(room, guest))
                {
                    reservationManagePage.getData().add(r);
                }
            });

            reservationManagePage.getButtonSub().setOnAction(e ->{
                Room room = reservationManagePage.getTableView().getSelectionModel().getSelectedItem();
                reservationManagePage.getTextRoom().setText(room.getRoom_no()+"");
                locationHolder.location = room.getRoom_location();
                reservationManagePage.getStage().close();
            });

        });
    }
}
