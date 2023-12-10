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

/**
 * The SingleActionView class represents a view for a single action.
 * It implements the ActionView interface.
 * <p>
 * The SingleActionView class has the following responsibilities:
 * - Create a container node to hold the single action view.
 * - Provide a ComboBox to select the action type.
 * - Provide a method to handle the action choice and update the view accordingly.
 * - Retrieve the action from the current action view.
 * - Check if the current action view is valid.
 */
public class SingleActionView implements ActionView {
    private final HBox actionsBox;
    private final ComboBox<ActionType> actionMenu;
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
