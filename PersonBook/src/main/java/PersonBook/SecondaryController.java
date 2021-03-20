package PersonBook;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SecondaryController {
    @FXML
    private ListView<ContactPerson> listMenu;
    
    @FXML
    private TextField txtPersonName;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtPhoneNumber;
    
    @FXML
    private Label lblBirthday;
    
    @FXML
    private TextField txtBirthday;    
    
    @FXML
    private DatePicker dpBirthday;
    
    @FXML
    private CheckBox cbShowYear;
    
    @FXML
    private CheckBox cbPersonal;
    
    @FXML
    private CheckBox cbBusiness;
    
    @FXML
    private Button btnBack;
    
    @FXML
    private Button btnSave;
    
    @FXML
    private Label lblPersonId;
    
    @FXML
    private TextField txtNotes;
    
    @FXML
    TableView<CaseNotes> notesTable;
    
    @FXML
    TableColumn<CaseNotes, Integer> tblDate;
    
    @FXML
    TableColumn<CaseNotes, String> tblNotes;
    
    String fullBirthday;
    boolean showYear;
    boolean personalImportance;
    boolean businessImportance;
    int personId;
    
    @FXML
    public void initialize() throws SQLException {
        if (DataHelper.person.size() > 0) {
            //There are already people in the contact list
            for (ContactPerson thisPerson : DataHelper.person) {
                listMenu.getItems().add(thisPerson);
            }
        } else {
            for (ContactPerson thisPerson : DataHelper.person) {
                listMenu.getItems().add(thisPerson);
            }
        }
        //Set up note tables but keep notes blank
        notesTable.setFixedCellSize(50.0);
        ObservableList<CaseNotes> notesList = DataHelper.getNotes();
        tblDate.setCellValueFactory(new PropertyValueFactory<>(" "));
        tblNotes.setCellValueFactory(new PropertyValueFactory<>(" "));
        notesTable.setItems(notesList);

    }
        
    @FXML
    private void userDidSavePerson() throws IOException {
        //Check for blank entries
        if (txtPersonName.getText().trim().isEmpty()
                || txtPhoneNumber.getText().isEmpty()){
            createErrorMessage();
        } else {
            //Convert name, email and phone inputs into variables
            createPersonId();
            lblPersonId.setText(String.valueOf(personId));
            String name = txtPersonName.getText();
            String email = txtEmail.getText();
            String phoneNumber = txtPhoneNumber.getText();
            //int phoneNumber = Integer.parseInt(txtPhone);
            //Convert date in datepicker into variable
            showDate();
            String birthday = txtBirthday.getText();
            //Check if year is optional
            checkShowYear();
            //Check personal and business importance
            checkPersonalImportance();
            checkBusinessImportance();

            //Create new contact and insert to ArrayList in DataHelper
            ContactPerson newPerson = new ContactPerson(personId, name, email, phoneNumber, birthday, showYear, personalImportance, businessImportance, fullBirthday);
            DataHelper.person.add(newPerson);
            //Return to main screen
            App.setRoot("primary");
        }
    }
    
    @FXML
    private void showDate() {
        //Get date from datepicker
        LocalDate birthday = dpBirthday.getValue();
        //Create formats for full birthday and birthday with no year
        DateTimeFormatter fullDate = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault());
        DateTimeFormatter noYear = DateTimeFormatter.ofPattern("dd MMMM", Locale.getDefault());
        if (cbShowYear.isSelected()){
            txtBirthday.setText(birthday.format(fullDate));
            fullBirthday = birthday.toString();
        } else {
            txtBirthday.setText(birthday.format(noYear));
            fullBirthday = birthday.toString();
        }
    }
    
    //Check if showYear is ticked
    @FXML
    private void checkShowYear() {
        if (cbShowYear.isSelected()) {
            showYear = true;
        } else {
            showYear = false;
        }
    }
    
    @FXML
    private void checkPersonalImportance() {
        if (cbPersonal.isSelected()) {
            personalImportance = true;
        } else {
            personalImportance = false;
        }
    }
    
    @FXML
    private void checkBusinessImportance() {
        if (cbBusiness.isSelected()) {
            businessImportance = true;
        } else {
            businessImportance = false;
        }
    }
        
    //Get person id 
    private void createPersonId() {
        for (int i = 0; i < DataHelper.person.size(); i++) {
            if (i == DataHelper.person.size() - 1) {
                personId = i + 2;
                System.out.println(personId);
            } else {
                //Do nothing
            }
        }
    }
    
    //Check for errors
    @FXML
    private void createErrorMessage() {
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setHeaderText("New contact cannot be created as there is a blank field");
        errorAlert.setContentText("Please enter a name or phone number if the field is blank.");
        errorAlert.showAndWait();
    }
    
    @FXML
    private void switchToMain() throws IOException {
        App.setRoot("primary");
    }
}