package it.unisa.diem.se.group7.seproject.Controller;

import it.unisa.diem.se.group7.seproject.Model.Actions.*;
import it.unisa.diem.se.group7.seproject.Model.Rules.Rule;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleManager;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleSleepDecorator;
import it.unisa.diem.se.group7.seproject.Model.Rules.SimpleRule;
import it.unisa.diem.se.group7.seproject.Model.Triggers.*;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RuleController implements Initializable {
    @FXML
    public Button appendFileChooserButton;

    @FXML
    public HBox copyFileBoxInput;

    @FXML
    public Button copyFileChooserButton;

    @FXML
    public Button copyDirectoryChooserButton;

    @FXML
    public Spinner<Integer> sleepingDaySpinner;

    @FXML
    public Spinner<Integer> sleepingHourSpinner;

    @FXML
    public Spinner<Integer> sleepingMinuteSpinner;

    @FXML
    public VBox sleepingBoxInput;

    @FXML
    public CheckBox onceActivationCheckbox;

    @FXML
    public CheckBox twiceActivationCheckbox;

    @FXML
    public HBox dayOfTheWeekBoxInput;

    @FXML
    public ComboBox<DayOfWeek> dayOfTheWeekInput;

    @FXML
    public HBox dayOfTheYearBoxInput;

    @FXML
    public DatePicker dayOfTheYearInput;

    @FXML
    public HBox dayOfTheMonthBoxInput;

    @FXML
    public Spinner<Integer> dayOfTheMonthInput;

    @FXML
    public VBox activationBoxInput;

    @FXML
    public HBox exitValueBoxInput;

    @FXML
    public Button exitValueButton;

    @FXML
    public Spinner<Integer> exitValueSpinner;

    @FXML
    public HBox externalProgramBoxInput;

    @FXML
    public Button chooseProgramButton;

    @FXML
    public TextField commandLineArgumentsTextField;

    @FXML
    private RuleManager ruleManager;

    @FXML
    private Label titleLabel;

    @FXML
    private ComboBox<ActionType> actionMenu;

    @FXML
    private TextField ruleNameField;

    @FXML
    private ComboBox<TriggerType> triggerMenu;

    private File selectedAudioFile;

    @FXML
    private Button selectFileButton;

    @FXML
    private HBox timeTriggerInput;

    @FXML
    private HBox dialogBoxInput;

    @FXML
    private HBox audioFileInput;

    @FXML
    private HBox appendToFileInputBox;

    @FXML
    private Spinner<Integer> hourTimeInput;

    @FXML
    private Spinner<Integer> minuteTimeInput;

    @FXML
    private TextField messageActionInput;

    @FXML
    private  TextField appendToFileTextfield;

    @FXML
    private Button createRuleButton;

    @FXML
    private Button editRuleButton;

    private Rule ruleBeingEdited;

    private File selectedAppendFile;

    private File selectedCopyFile;

    private File selectedCopyDirectory;
    private File selectedExternalProgramFile;

    private File exitValueProgramFile;


    //TODO: Refractor initialize method creating a createRuleInit method to improve code readability
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ruleManager = RuleManager.getInstance();
        titleLabel.setText("Create a rule");
        //Initialization of the combo box menus
        triggerMenu.getItems().addAll(TriggerType.values());
        actionMenu.getItems().addAll(ActionType.values());
        //Hidden elements don't occupy space
        timeTriggerInput.managedProperty().bind(timeTriggerInput.visibleProperty());
        dayOfTheWeekBoxInput.managedProperty().bind(dayOfTheWeekBoxInput.visibleProperty());
        dayOfTheMonthBoxInput.managedProperty().bind(dayOfTheMonthBoxInput.visibleProperty());
        dayOfTheYearBoxInput.managedProperty().bind(dayOfTheYearBoxInput.visibleProperty());
        dialogBoxInput.managedProperty().bind(dialogBoxInput.visibleProperty());
        audioFileInput.managedProperty().bind(audioFileInput.visibleProperty());
        appendToFileInputBox.managedProperty().bind(appendToFileInputBox.visibleProperty());
        copyFileBoxInput.managedProperty().bind(copyFileBoxInput.visibleProperty());
        exitValueBoxInput.managedProperty().bind(exitValueBoxInput.visibleProperty());
        externalProgramBoxInput.managedProperty().bind(externalProgramBoxInput.visibleProperty());

        //Display of the inputs according to user choice in the comboBox menu
        //Triggers
        timeTriggerInput.visibleProperty().bind(triggerMenu.valueProperty().isEqualTo(TriggerType.TIME_TRIGGER));
        dayOfTheWeekBoxInput.visibleProperty().bind(triggerMenu.valueProperty().isEqualTo(TriggerType.DAY_OF_WEEK));
        dayOfTheMonthBoxInput.visibleProperty().bind(triggerMenu.valueProperty().isEqualTo(TriggerType.DAY_OF_MONTH));
        dayOfTheYearBoxInput.visibleProperty().bind(triggerMenu.valueProperty().isEqualTo(TriggerType.DAY_OF_YEAR));
        exitValueBoxInput.visibleProperty().bind(triggerMenu.valueProperty().isEqualTo(TriggerType.EXIT_VALUE));
        //Actions
        dialogBoxInput.visibleProperty().bind(actionMenu.valueProperty().isEqualTo(ActionType.SHOW_DIALOG_BOX));
        audioFileInput.visibleProperty().bind(actionMenu.valueProperty().isEqualTo(ActionType.PLAY_AUDIO));
        appendToFileInputBox.visibleProperty().bind(actionMenu.valueProperty().isEqualTo(ActionType.APPEND_TO_FILE));
        copyFileBoxInput.visibleProperty().bind(actionMenu.valueProperty().isEqualTo(ActionType.COPY_FILE));
        externalProgramBoxInput.visibleProperty().bind(actionMenu.valueProperty().isEqualTo(ActionType.EXECUTE_PROGRAM));

        setUpTimeSpinner();
        setUpDateSpinner();

        setUpDayOfTheWeekComboBox();
        setUpDayOfTheMonthSpinner();

        setUpExitValueInput();

        //Bindings for Activation checkboxes
        onceActivationCheckbox.disableProperty().bind(twiceActivationCheckbox.selectedProperty());
        twiceActivationCheckbox.disableProperty().bind(onceActivationCheckbox.selectedProperty());
        //Bindings for sleeping time display when the user select the right checkbox
        sleepingBoxInput.visibleProperty().bind(sleepingBoxInput.managedProperty());
        sleepingBoxInput.managedProperty().bind(twiceActivationCheckbox.selectedProperty());

        editRuleButton.setManaged(false);

    }
    private void setUpDayOfTheWeekComboBox() {
        dayOfTheWeekInput.getItems().addAll(DayOfWeek.values());
    }

    private void setUpDayOfTheMonthSpinner() {
        SpinnerValueFactory<Integer> dayOfTheMonthFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31);
        dayOfTheMonthInput.setValueFactory(dayOfTheMonthFactory);
    }

    private void setUpTimeSpinner() {
        //Setup spinner component for time and minutes
        Integer currenthours = LocalTime.now().getHour();
        Integer currentminutes = LocalTime.now().getMinute();

        SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        hourValueFactory.setValue(currenthours);
        minuteValueFactory.setValue(currentminutes);

        hourTimeInput.setValueFactory(hourValueFactory);
        minuteTimeInput.setValueFactory(minuteValueFactory);
    }
    private void setUpDateSpinner() {
        SpinnerValueFactory<Integer> dayValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99);
        SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);

        sleepingDaySpinner.setValueFactory(dayValueFactory);
        sleepingHourSpinner.setValueFactory(hourValueFactory);
        sleepingMinuteSpinner.setValueFactory(minuteValueFactory);
    }

    private void setUpExitValueInput() {
        SpinnerValueFactory<Integer> exitValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-99, 99);
        exitValueSpinner.setValueFactory(exitValueFactory);
    }

    @FXML
    void createNewRule(ActionEvent event) {
        Rule rule = null;
        if(validInputs()) {
            if(onceActivationCheckbox.isSelected()) {
                rule = new SimpleRule(ruleNameField.getText(), createTrigger(triggerMenu.getSelectionModel().getSelectedItem()), createAction(actionMenu.getSelectionModel().getSelectedItem()));
            }
            if(twiceActivationCheckbox.isSelected()) {
                rule = new RuleSleepDecorator(new SimpleRule(ruleNameField.getText(), createTrigger(triggerMenu.getSelectionModel().getSelectedItem()), createAction(actionMenu.getSelectionModel().getSelectedItem())),
                        sleepingDaySpinner.getValue(), sleepingHourSpinner.getValue(), sleepingMinuteSpinner.getValue());
            }
            ruleManager.addRule(rule);
            closeWindow();

        } else {
            showErrorAlert("ERROR", "You need to fill all the inputs to create a Rule!");
        }
    }

    private void closeWindow() {
        // Get the Stage from any JavaFX Node in the current scene
        Stage stage = (Stage) ruleNameField.getScene().getWindow();
        stage.close();
    }
    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private Trigger createTrigger(TriggerType selectedTrigger) {
        var trigger = switch (selectedTrigger) {
            case TIME_TRIGGER -> new TimeTrigger(hourTimeInput.getValue(), minuteTimeInput.getValue());
            case DAY_OF_WEEK -> new DayOfWeekTrigger(dayOfTheWeekInput.getValue());
            case DAY_OF_MONTH -> new DayOfTheMonthTrigger(dayOfTheMonthInput.getValue());
            case DAY_OF_YEAR -> new DayOfTheYearTrigger(dayOfTheYearInput.getValue());
            case EXIT_VALUE -> new ExitValueTrigger(exitValueProgramFile, exitValueSpinner.getValue());

            default -> throw new IllegalStateException("Unexpected value: " + selectedTrigger);
        };

        return trigger;
    }

    private Action createAction(ActionType selectedAction) {
        var action = switch (selectedAction) {
            case SHOW_DIALOG_BOX -> new ShowDialogBoxAction(messageActionInput.getText());
            case PLAY_AUDIO -> new PlayAudioAction(selectedAudioFile);
            case APPEND_TO_FILE -> new AppendToFileAction(selectedAppendFile,appendToFileTextfield.getText());
            case COPY_FILE -> new CopyFileAction(selectedCopyDirectory, selectedCopyFile);
            case EXECUTE_PROGRAM -> new ExecuteProgramAction(selectedExternalProgramFile, commandLineArgumentsTextField.getText());

            default -> throw new IllegalStateException("Unexpected value: " + selectedAction);
        };
        return action;
    }
    // TODO: This method should be implemented
    private boolean validInputs() {
        return true;
    }

    @FXML
    public void chooseAudioFileAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Audio File");

        // Set the file extension filters if needed
        // Example: Allow only audio files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.ogg");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show the file chooser dialog
        selectedAudioFile = fileChooser.showOpenDialog(null);
        if(selectedAudioFile != null) {
            selectFileButton.setText("File selected");
        }
    }

    public void editRuleInit(Rule ruleToEdit) {
        this.ruleBeingEdited = ruleToEdit;
        titleLabel.setText("Edit a rule");
        ruleNameField.setDisable(true);
        ruleNameField.setText(ruleToEdit.getName());
        triggerMenu.setValue(ruleToEdit.getTrigger().getTYPE());
        actionMenu.setValue(ruleToEdit.getAction().getTYPE());
        activationBoxInput.setDisable(true);
        createRuleButton.setManaged(false);
        editRuleButton.setManaged(true);

    }

    @FXML
    public void editRule(ActionEvent event) {
        if (validInputs()) {
            if (ruleBeingEdited != null) {
                // Update the existing rule with the edited values
                ruleBeingEdited.setName(ruleNameField.getText());
                ruleBeingEdited.setTrigger(createTrigger(triggerMenu.getSelectionModel().getSelectedItem()));
                ruleBeingEdited.setAction(createAction(actionMenu.getSelectionModel().getSelectedItem()));
                closeWindow();
            } else {
                showErrorAlert("ERROR", "No rule is being edited.");
            }
        } else {
            showErrorAlert("ERROR", "You need to fill all the inputs to edit a Rule!");
        }
    }

    @FXML
    public void appendFileChooseAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");

        // Set the file extension filters if needed
        // Example: Allow only text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show the file chooser dialog
        selectedAppendFile = fileChooser.showOpenDialog(null);
        if(selectedAppendFile != null) {
            appendFileChooserButton.setText("File selected");
        }
    }

    @FXML
    public void chooseCopyFileAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file to copy");

        // Show the file chooser dialog
        selectedCopyFile = fileChooser.showOpenDialog(null);
        if(selectedCopyFile != null) {
            copyFileChooserButton.setText("File selected");
        }
    }

    @FXML
    public void chooseCopyDirectoryAction(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a destination directory");

        selectedCopyDirectory = directoryChooser.showDialog(null);
        if (selectedCopyDirectory != null) {
            copyDirectoryChooserButton.setText("Directory selected");
        }
    }

    public void exitValueFileChooseAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open External Program File");

        // Set the file extension filters if needed
        // Example: Allow only text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Executable Files", "*.exe", "*.sh", "*.bat");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show the file chooser dialog
        exitValueProgramFile = fileChooser.showOpenDialog(null);
        if(exitValueProgramFile != null) {
            exitValueButton.setText("File selected");
        }
    }
    @FXML
    public void chooseProgramAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select External Program");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Executable Files", "*.exe", "*.sh", "*.bat")
        );
        // Show open file dialog
        selectedExternalProgramFile = fileChooser.showOpenDialog(null);

        if (selectedExternalProgramFile != null) {
            // The user selected a file, you can use 'selectedFile' in your logic
            chooseProgramButton.setText("External program chosen");
        }
    }
}

