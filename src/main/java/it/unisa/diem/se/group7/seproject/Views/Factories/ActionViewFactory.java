package it.unisa.diem.se.group7.seproject.Views.Factories;

import it.unisa.diem.se.group7.seproject.Model.Actions.ActionType;
import it.unisa.diem.se.group7.seproject.Views.ActionViews.*;

/**
 * The ActionViewFactory class provides a static method to create instances of ActionView based on the given ActionType.
 * It uses a switch statement to determine the ActionType and returns the corresponding ActionView implementation.
 * <p>
 * Example usage:
 * ActionView view = ActionViewFactory.createView(ActionType.SHOW_DIALOG_BOX);
 */
public class ActionViewFactory {
    public static ActionView createView(ActionType triggerType) {
        return switch (triggerType) {
            case SHOW_DIALOG_BOX -> new ShowDialogBoxActionView();
            case PLAY_AUDIO -> new PlayAudioActionView();
            case APPEND_TO_FILE -> new AppendToFileActionView();
            case COPY_FILE -> new CopyFileActionView();
            case DELETE_FILE -> new DeleteFileActionView();
            case EXECUTE_PROGRAM -> new ExecuteProgramActionView();
        };
    }
}
