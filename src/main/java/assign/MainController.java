package assign;


import assign.DataModels.Person;
import assign.Exceptions.UnauthorizedException;
import assign.Exceptions.UnknownException;
import assign.Session.Session;
import assign.DocRobsStuff.DocRobsAlert;
import assign.DocRobsStuff.DocRobsHash;
import assign.Gateways.PersonGateway;
import assign.Gateways.SessionGateway;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class MainController extends Application implements Initializable{

    static AnchorPane root;
    private static Stage primaryStage;
    static List<GridPane> grid = new ArrayList<GridPane>();

    Session session = null;
    private static final Logger logger = LogManager.getLogger();
    private static MainController instance = null;
    public static int idx_cur = 0;
    public ListView<String> detailListView;
    private static int runTimes = 0;
    public Label menuName;
    public Button deleteButton;
    private Person person;
    public static int userindex;
    static long lastRefreshTime = 0;
    //public static ObservableList<Person> people = FXCollections.observableArrayList ();
    public static ObservableList<Person> people = FXCollections.observableArrayList ();
    public static ObservableList<String> userAttList = FXCollections.observableArrayList();
    public ObservableList<String> peopleFirstNameList = FXCollections.observableArrayList();
    public static String lastName;
    public ListView<String> userAtributes = new ListView<>();
    @FXML
    public ListView<String> userList = new ListView<>();
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField userNameTextfield;
    @FXML
    private TextField shownPass;
    @FXML
    private RadioButton showPassword;
    @FXML
    private Label userName;
    @FXML
    private static GridPane gridRootBase;

    public MainController() {

    }
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle arg1) {
        logger.info("init Main controller");

    }

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        MainController.primaryStage = primaryStage;
        setPane(0);
    }

    @FXML
    void loginPressed(ActionEvent event) throws IOException {
        String s = userNameTextfield.getText();
        String hashedPassword = DocRobsHash.getCryptoHash(this.passwordField.getText(), "SHA-256");
        Session session = null;
        try {
            session = SessionGateway.login(s, hashedPassword);

        } catch(UnauthorizedException e) {
            DocRobsAlert.infoAlert("Login failed!", "Either your username or your password is incorrect.");
            return;
        } catch(UnknownException e1) {
            DocRobsAlert.infoAlert("Login failed!", "Something bad happened: " + e1.getMessage());
            logger.fatal("Ouch\n");
            return;
        }

        ArrayList<Person> persons = (ArrayList<Person>) PersonGateway.fetchPersons(session.getSessionId().toString());
        //people.setAll(persons);
        people.addAll(persons);
        updateList();
        logger.info(persons);
        MainController.getInstance().setSession(session);
        setPane(1);
    }
    @FXML
    void showPasswordPressed(ActionEvent event) {
        boolean isSelected = showPassword.isSelected();
        String s = passwordField.getText();
        logger.info("Password Show Pressed");
        if(isSelected) {
            shownPass.setText(s);
            shownPass.toFront();
            passwordField.toBack();
        }
        else{
            shownPass.toBack();
            passwordField.toFront();
        }

        System.out.println(s);
    }
    public void updateList(){
        if(runTimes == 0) {
            logger.warn("User list is empty!");
            for (Person value : people) {
                peopleFirstNameList.add(value.getFirstName());
                logger.info("Added person: " + value.getFirstName());
                logger.info("First person should match: " + peopleFirstNameList.get(0));
            }
            logger.info("people first name list is: " + peopleFirstNameList);
            userList.setItems(peopleFirstNameList);
            userList.refresh();
            logger.info(userList.toString());
            runTimes++;
        }
        for (Person value : people) {
            if (!userList.getItems().contains(value.getFirstName())) {
                peopleFirstNameList.add(value.getFirstName());
                userList.setItems(peopleFirstNameList);
                userList.refresh();
            }
        }
        //peopleFirstNameList.addAll(people.get(i).getFirstName());
    }
// These two methods will help later, They allow for some advanced functionality.
public static GridPane getPane(int idx) {
    return grid.get(idx);
}
    public static int getCurPane() {
        return idx_cur;
    }

    public static MainController getInstance() {
        if(instance == null)
            instance = new MainController();
        return instance;
    }
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {

        this.session = session;
    }
    public static void setPane(int idx) throws IOException {
        switch (idx) {
            case (0) -> {
                logger.info("Got a 0");
                FXMLLoader fxmlLoader0 = new FXMLLoader(MainController.class.getResource("LoginScreen.fxml"));
                Scene scene0 = new Scene(fxmlLoader0.load(), 800, 380);
                primaryStage.setScene(scene0);
                primaryStage.show();
            }
            case (1) -> {
                logger.info("Got a 1");
                FXMLLoader fxmlLoader1 = new FXMLLoader(MainController.class.getResource("people_ListView.fxml"));
                Scene scene1 = new Scene(fxmlLoader1.load(), 800, 600);
                primaryStage.setScene(scene1);

            }
            case (2) -> {
                logger.info("Got a 2");
                FXMLLoader fxmlLoader2 = new FXMLLoader(MainController.class.getResource("personDetailView.fxml"));
                Scene scene2 = new Scene(fxmlLoader2.load(), 800, 600);
                primaryStage.setScene(scene2);
            }
            default -> logger.error("He's dead jim");
        }

    }


    @FXML
    public void userSelected(MouseEvent contextMenuEvent) {
        updateList();
        if(runTimes == 0) {
            userList.getSelectionModel().select(0);
            runTimes++;
        }
        userAtributes.getItems().clear();
        userName.setTextFill(Color.BLACK);
        userindex = userList.getSelectionModel().getSelectedIndex();
        try {
            String selectedItem = userList.getSelectionModel().getSelectedItem().toString();
            userName.setText(selectedItem);
            lastName = people.get(userindex).getLastName();
            userAttList.add(("User ID: \t" + people.get(userindex).getId()));
            userAttList.add(("First Name:\t" + people.get(userindex).getFirstName()));
            userAttList.add(("Last Name: \t" + people.get(userindex).getLastName()));
            userAttList.add(("BirthDate : \t" + people.get(userindex).getDateOfBirth()));
            userAttList.add(("Age: \t" + people.get(userindex).getAge()));
            userAtributes.setItems(userAttList);

        }catch ( NullPointerException e){
            logger.error("Must select a person!");
        }


    }

    @FXML
    void pplButttonPressed(ActionEvent event) throws IOException {
        updateList();
        userList.refresh();
        setPane(2);
    }

    public void deleteButtonPressed(ActionEvent event) {
        updateList();
        userindex = userList.getSelectionModel().getSelectedIndex();
        userList.getItems().remove(userindex);
        people.remove(userindex);
        userAtributes.getItems().clear();
        logger.warn("DELETING <" + (people.get(userindex).getFirstName()) + " " +(people.get(userindex).getLastName())+ ">");
    }

    public void addUserButtonPressed(ActionEvent event) {
        Person person = new Person(0, "Robert"," Wire", (LocalDate.parse("1981-02-03")), 41);
        //person.Person(0, "Robert"," Wire", (LocalDate.parse("2017-02-03")), 41);
        people.add(person);
        peopleFirstNameList.add(people.get(people.size()-1).getFirstName().toString());
        userName.setTextFill(Color.RED);
        userName.setText("You should Probably edit that user!");
        logger.info("Person Created: " + person.getFirstName());
        updateList();
    }
    public static <T> void triggerUpdate(ListView<T> listView, T newValue, int i) {
        EventType<? extends ListView.EditEvent<T>> type = ListView.editCommitEvent();
        Event event = new ListView.EditEvent<>(listView, type, newValue, i);
        listView.fireEvent(event);
    }

}
