package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.ShowDialogBoxAction;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * The ShowDialogBoxActionView class implements the ActionView interface and provides
 * a view for showing a dialog box with a custom message. It creates a graphical user interface
 * with a text field for entering the message.
 */
public class ShowDialogBoxActionView implements ActionView {
    private final TextField messageTextField;

    public ShowDialogBoxActionView() {
        messageTextField = new TextField();
        messageTextField.setMaxWidth(PREF_TEXTFIELD_SIZE);
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(DEFAULT_SPACING);

        Label messageLabel = new Label("Enter Message:");
        container.getChildren().addAll(messageLabel, messageTextField);

        return container;
    }

    private String getEnteredMessage() {
        return messageTextField.getText().trim();
    }

    @Override
    public Action getAction() {
        return new ShowDialogBoxAction(getEnteredMessage());
    }

    @Override
    public boolean isValid() {
        // Check if the message is not empty
        return !getEnteredMessage().isEmpty();
    }
}
