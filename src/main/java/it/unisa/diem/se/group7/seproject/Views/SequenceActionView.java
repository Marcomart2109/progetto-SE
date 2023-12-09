package it.unisa.diem.se.group7.seproject.Views;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.CompositeAction;
import it.unisa.diem.se.group7.seproject.Views.ActionViews.ActionView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SequenceActionView implements ActionView {
    private List<ActionView> actionViewList;

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
        container.getChildren().add(actionViewList.get(actionViewList.size() - 1).getView());
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
