package cooperativa_malvinas;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class AppLauncher {
    public static void main(String[] args) {
        Application.launch(MainFX.class, args);
    }
}