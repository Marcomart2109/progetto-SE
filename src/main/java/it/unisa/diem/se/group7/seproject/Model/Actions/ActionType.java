package it.unisa.diem.se.group7.seproject.Model.Actions;

/**
 * An enumeration representing different types of actions that can be performed.
 * <p>
 * The ActionType enum has the following values:
 * 1. SHOW_DIALOG_BOX - Represents the action of showing a dialog box.
 * 2. PLAY_AUDIO - Represents the action of playing an audio file.
 * 3. APPEND_TO_FILE - Represents the action of appending text to a file.
 * 4. COPY_FILE - Represents the action of copying a file.
 * 5. DELETE_FILE - Represents the action of deleting a file.
 * 6. EXECUTE_PROGRAM - Represents the action of executing a program.
 * <p>
 * The ActionType enum is used in the following scenarios:
 * 1. In the createView() method of the ActionViewFactory class, ActionType is used to determine the type of ActionView to create based on the trigger type.
 * 2. In the getView() method of the SingleActionView class, ActionType is used to display a list of available actions in a ComboBox.
 * 3. In the handleActionChoice() method of the SingleActionView class, ActionType is used to create an instance of the selected ActionView.
 * <p>
 * Example usage with ActionViewFactory:
 * ActionView view = ActionViewFactory.createView(ActionType.SHOW_DIALOG_BOX);
 * <p>
 * Example usage with SingleActionView:
 * SingleActionView singleActionView = new SingleActionView();
 * singleActionView.setActionType(ActionType.SHOW_DIALOG_BOX);
 * Node view = singleActionView.getView();

 */
public enum ActionType {
    SHOW_DIALOG_BOX,
    PLAY_AUDIO,
    APPEND_TO_FILE,
    COPY_FILE,
    DELETE_FILE,
    EXECUTE_PROGRAM
}
