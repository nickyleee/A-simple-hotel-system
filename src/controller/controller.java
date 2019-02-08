package controller;

import GUI.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.HotelSystem;
import model.Room;
import sun.rmi.runtime.Log;
import sun.util.locale.provider.LocaleServiceProviderPool;

import java.io.IOException;


public class controller {
    private HotelSystem hotelSystem = new HotelSystem();

    public void loginController()
    {
        LogInPage logInPage = new LogInPage();
        logInPage.loginPage();
        logInPage.getButton().setOnAction(value -> {
            DOAction(logInPage);
        });
    }

    private void DOAction(LogInPage logInPage)
    {
        try{
            if(hotelSystem.logIn(logInPage.getUserTextField().getText().toString(),logInPage.getPwBox().getText()))
            {
                logInPage.getStageLogIn().close();
                mainController();
            } else {
                logInPage.alert();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void mainController() throws IOException
    {
        MainPage mainPage = new MainPage();
        mainPage.mainController();
        mainPage.getRmButton().setOnAction(value ->{
            mainPage.getMvbox().getChildren().clear();
            new ReservationPageController().reservationPage(mainPage.getMvbox(), hotelSystem);
        });
        mainPage.getGmButton().setOnAction(value ->{
            mainPage.getMvbox().getChildren().clear();
            new GuestManagePageController().guestPageController(mainPage.getMvbox(), hotelSystem);
        });
        mainPage.getRoomManageButoon().setOnAction(value ->{
            mainPage.getMvbox().getChildren().clear();
            new RoomManagePageController().roomManageController(mainPage.getMvbox(), hotelSystem);
        });
    }

}
