package Main;

import DataModels.Person;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;
/*import static mvc.MainController.setPane;
import static mvc.MainController.*;*/


public class PersonController  implements Initializable {
    private static final Logger logger = LogManager.getLogger();
    public ListView detailListView;
    public Label personDetailLabel;
    public Button saveButton;
    public Button backButton;
    public Button editButton;

    @FXML
    private TextField firstName;

    @FXML
    private TextField personAge;

    private Person person;
    public static int index = 0;

    public PersonController() {
    }
    @FXML
    void saveButtonPressed(ActionEvent event) {
        logger.info("UPDATING <" + MainController.lastName + ">");
        personDetailLabel.setText(MainController.lastName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // this is where we connect the model data to the GUI components like textfields
        logger.info("Person controller init\n");
        if (index == 0) {
            detailListView.getItems().clear();
            detailListView.setItems(MainController.userAttList);
        }
        personDetailLabel.setText(MainController.lastName);
    }
    public void backButtonPressed(ActionEvent event) {
        personDetailLabel.setTextFill(Color.BLACK);
        personDetailLabel.setText(" ");
        detailListView.setEditable(false);
        detailListView.refresh();
        try {
            MainController.setPane(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void editButtonPressed(ActionEvent event) {
        logger.info("Edit pressed");
        personDetailLabel.setText("Edit Mode Engaged!");
        personDetailLabel.setTextFill(Color.RED);
        detailListView.setEditable(true);
        detailListView.setCellFactory(TextFieldListCell.forListView());
        detailListView.setOnEditCommit((EventHandler<ListView.EditEvent<String>>) t -> {
            detailListView.getItems().set(t.getIndex(), t.getNewValue());
            String changedValue = t.getNewValue();
            System.out.println(t.getIndex());
            switch (t.getIndex()) {
                case 0: changedValue = changedValue.substring(9);
                        MainController.people.get(MainController.userindex).setId((parseInt(changedValue)));
                        break;
                case 1: changedValue = changedValue.substring(11);
                        MainController.people.get(MainController.userindex).setFirstName(changedValue);
                        break;
                case 2: changedValue = changedValue.substring(10);
                        MainController.people.get(MainController.userindex).setLastName(changedValue);
                        break;
                case 3: changedValue = changedValue.substring(12);
                        MainController.people.get(MainController.userindex).setDateOfBirth(LocalDate.parse(changedValue));
                        break;
                case 4: changedValue = changedValue.substring(5);
                        MainController.people.get(MainController.userindex).setAge((parseInt(changedValue)));
                        break;
                default: logger.error("Invalid Selection!");
            }
        });

    }
}

