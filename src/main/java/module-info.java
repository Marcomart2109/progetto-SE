module it.unisa.diem.se.group7.seproject {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens it.unisa.diem.se.group7.seproject to javafx.fxml;
    exports it.unisa.diem.se.group7.seproject;
    exports it.unisa.diem.se.group7.seproject.Controller;
    opens it.unisa.diem.se.group7.seproject.Controller to javafx.fxml;
    opens it.unisa.diem.se.group7.seproject.Model.Triggers;
    opens it.unisa.diem.se.group7.seproject.Model.Actions;
    opens it.unisa.diem.se.group7.seproject.Model.Rules;
    opens it.unisa.diem.se.group7.seproject.Model.Scheduler;
}