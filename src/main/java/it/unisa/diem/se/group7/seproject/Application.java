package it.unisa.diem.se.group7.seproject;

import it.unisa.diem.se.group7.seproject.Controller.MainController;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleBackup;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleManager;
import it.unisa.diem.se.group7.seproject.Model.Scheduler.RuleScheduler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Desktop RuleMaster");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();

        //Create and start RuleScheduler to check rules activation periodically
        RuleScheduler ruleScheduler = RuleScheduler.getInstance();
        ruleScheduler.startScheduler();

        RuleManager rm = RuleManager.getInstance();

        stage.setOnCloseRequest(event -> {
            //save rules content on the binary file with the specified path
            String backupPath = "src/main/resources/saved.bin";
            RuleBackup.saveOnBinaryFile(rm.getRules(), backupPath);

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