import javafx.application.Application;
import javafx.stage.Stage;
import controller.*;

public class program extends Application {

        public static void main(String[] args) {
            launch(args);

        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            controller con = new controller();
            con.loginController();
        }

}
