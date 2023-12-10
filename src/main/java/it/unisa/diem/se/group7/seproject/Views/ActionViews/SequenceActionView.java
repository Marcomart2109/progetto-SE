package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.CompositeAction;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * The SequenceActionView class represents a view for a sequence of actions.
 * It implements the ActionView interface.
 * <p>
 * The SequenceActionView class has the following responsibilities:
 * - Create a container node to hold the sequence of actions.
 * - Provide a method to add a new action to the sequence.
 * - Handle the user action of adding a new action to the sequence.
 * - Retrieve the composite action that represents the sequence of actions.
 * - Check if all the actions in the sequence are valid.
 */
public class SequenceActionView implements ActionView {
    private final List<ActionView> actionViewList;

    public SequenceActionView() {
        actionViewList = new ArrayList<>();
        actionViewList.add(new SingleActionView());
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(INCREASED_SPACING);

        HBox actionContainer = new HBox(DEFAULT_SPACING);
        Button addActionButton = new Button("Add Action");
        addActionButton.setOnAction(actionEvent -> handleAddAction(container));
        actionContainer.getChildren().addAll(addActionButton);
        container.getChildren().add(actionContainer);

        for (ActionView actionView : actionViewList) {
            container.getChildren().add(actionView.getView());
        }

        return container;
    }

    private void handleAddAction(VBox container) {
        actionViewList.add(new SingleActionView());
        container.getChildren().add(actionViewList.getLast().getView());
    }

    @Override
    public Action getAction() {
        CompositeAction compositeAction = new CompositeAction();
        for (ActionView actionView : actionViewList) {
            Action individualAction = actionView.getAction();
            if (individualAction != null) {
                compositeAction.add(individualAction);
            }
        }
        return compositeAction;
    }

    @Override
    public boolean isValid() {
        return actionViewList.stream().allMatch(ActionView::isValid);
    }
}
