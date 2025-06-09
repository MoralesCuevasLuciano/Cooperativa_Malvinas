package cooperativa_malvinas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import javafx.scene.Scene;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


public class MainFX extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        context = new SpringApplicationBuilder(CooperativaMalvinasApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/member-form.fxml"));
        loader.setControllerFactory(context::getBean);
        Parent root = loader.load();

        stage.setTitle("Formulario de Miembro");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop() {
        context.close();
    }
}