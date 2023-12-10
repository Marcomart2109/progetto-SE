package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.ActionType;
import it.unisa.diem.se.group7.seproject.Views.Factories.ActionViewFactory;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SingleActionView implements ActionView {
    private HBox actionsBox;
    private ComboBox<ActionType> actionMenu;
    private ActionView currentActionView;

    public SingleActionView() {
        actionsBox = new HBox(DEFAULT_SPACING);
        actionMenu = new ComboBox<>();
        actionMenu.getItems().setAll(ActionType.values());
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(DEFAULT_SPACING);

        HBox actionContainer = new HBox(DEFAULT_SPACING);
        actionContainer.setAlignment(Pos.CENTER_LEFT);

        Label actionLabel = new Label("Select an action");

        actionMenu.setOnAction(actionEvent -> handleActionChoice());
        actionContainer.getChildren().addAll(actionMenu, actionsBox);

        container.getChildren().addAll(actionLabel, actionContainer);

        return container;
    }

    private void handleActionChoice() {
        actionsBox.getChildren().clear();
        ActionType selectedActionType = actionMenu.getValue();

        if (selectedActionType != null) {
            currentActionView = ActionViewFactory.createView(selectedActionType);
            Separator separator = new Separator(Orientation.VERTICAL);
            actionsBox.getChildren().addAll(separator, currentActionView.getView());
        }
    }

    @Override
    public Action getAction() {
        return currentActionView.getAction();
    }

    @Override
    public boolean isValid() {
        return currentActionView != null && currentActionView.isValid();
    }
}
