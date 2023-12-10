package it.unisa.diem.se.group7.seproject;

import it.unisa.diem.se.group7.seproject.Model.Rules.RuleBackupManager;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleManager;
import it.unisa.diem.se.group7.seproject.Model.Scheduler.RuleScheduler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The Application class extends the javafx.application.Application class and represents the main entry point of the application.
 * It is responsible for initializing and configuring the application's user interface.
 * The start() method is overridden to load the main view, set the application title, and start the rule scheduler.
 * The main() method is used to launch the application.
 */
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String cssPath = Objects.requireNonNull(getClass().getResource("style/style.css")).toExternalForm();
        scene.getStylesheets().add(cssPath);

        stage.setTitle("Desktop RuleMaster");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();

        //Create and start RuleScheduler to check rules activation periodically
        RuleScheduler ruleScheduler = RuleScheduler.getInstance();
        ruleScheduler.startScheduler();

        RuleManager rm = RuleManager.getInstance();

        stage.setOnCloseRequest(event -> {
            RuleBackupManager rbm = RuleBackupManager.getInstance();
            rbm.saveOnBinaryFile(rm.getRules());

            // Stop the scheduler when the window is closed
            ruleScheduler.stopScheduler();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}