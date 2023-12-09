package it.unisa.diem.se.group7.seproject.Views;

import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.TriggerType;
import it.unisa.diem.se.group7.seproject.Views.Factories.TriggerViewFactory;
import it.unisa.diem.se.group7.seproject.Views.TriggerViews.TriggerView;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ElementaryTriggerView implements TriggerView {
    private HBox triggersBox;
    private ComboBox<TriggerType> triggerMenu;
    private TriggerView currentTriggerView;

    public ElementaryTriggerView() {
        triggersBox = new HBox(DEFAULT_SPACING);
        triggerMenu = new ComboBox<>();
        triggerMenu.getItems().setAll(TriggerType.values());
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(DEFAULT_SPACING);

/*        Label titleLabel = new Label("Triggers");
        titleLabel.setStyle("-fx-font-size: 16;");*/

        HBox triggerContainer = new HBox(DEFAULT_SPACING);
        triggerContainer.setAlignment(Pos.CENTER_LEFT);

        Label triggerLabel = new Label("Select a trigger");

        triggerMenu.setOnAction(actionEvent -> handleTriggerChoice());
        triggerContainer.getChildren().addAll(triggerMenu, triggersBox);

        container.getChildren().addAll(triggerLabel, triggerContainer);

        return container;
    }

    private void handleTriggerChoice() {
        triggersBox.getChildren().clear();
        TriggerType selectedTriggerType = triggerMenu.getValue();
        currentTriggerView = TriggerViewFactory.createView(selectedTriggerType);
        Separator separator = new Separator(Orientation.VERTICAL);
        triggersBox.getChildren().addAll(separator, currentTriggerView.getView());
    }

    @Override
    public Trigger getTrigger() {
        return currentTriggerView.getTrigger();
    }

    @Override
    public boolean isValid() {
        return currentTriggerView != null && currentTriggerView.isValid();
    }
}
