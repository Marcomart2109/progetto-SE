package it.unisa.diem.se.group7.seproject;

import it.unisa.diem.se.group7.seproject.Controller.MainController;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleBackupManager;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleManager;
import it.unisa.diem.se.group7.seproject.Model.Scheduler.RuleScheduler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String cssPath = getClass().getResource("style/style.css").toExternalForm();
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
            if (ruleScheduler != null) {
                ruleScheduler.stopScheduler();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}