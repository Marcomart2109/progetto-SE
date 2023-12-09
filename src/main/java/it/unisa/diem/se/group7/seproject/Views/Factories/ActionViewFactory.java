package it.unisa.diem.se.group7.seproject.Views.Factories;

import it.unisa.diem.se.group7.seproject.Model.Actions.ActionType;
import it.unisa.diem.se.group7.seproject.Views.ActionViews.*;

public class ActionViewFactory {
    public static ActionView createView(ActionType triggerType) {
        switch (triggerType) {
            case SHOW_DIALOG_BOX:
                return new ShowDialogBoxActionView();
            case PLAY_AUDIO:
                return new PlayAudioActionView();
            case APPEND_TO_FILE:
                return new AppendToFileActionView();
            case COPY_FILE:
                return new CopyFileActionView();
            case DELETE_FILE:
                return new DeleteFileActionView();
            case EXECUTE_PROGRAM:
                return new ExecuteProgramActionView();
            default:
                throw new IllegalArgumentException("Unsupported trigger type: " + triggerType);
        }
    }
}
