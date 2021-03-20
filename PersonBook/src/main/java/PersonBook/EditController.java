/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonBook;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Locale;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author zhusk
 */
public class EditController {
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
    String originalName;
    
    @FXML
    public void initialize() throws SQLException {
        if (DataHelper.person.size() > 0) {
            //There are already people in the contact list
            for (ContactPerson thisPerson : DataHelper.person) {
                listMenu.getItems().add(thisPerson);
                //userDidChangeSelection();
            }
        } else {
            //todo: change this for case where there is none
            for (ContactPerson thisPerson : DataHelper.person) {
                listMenu.getItems().add(thisPerson);
            }
        }
        //Set up note tables
        notesTable.setFixedCellSize(50.0);
        ObservableList<CaseNotes> notesList = DataHelper.getNotes();
        tblDate.setCellValueFactory(new PropertyValueFactory<>("dateNotes"));
        tblNotes.setCellValueFactory(new PropertyValueFactory<>("caseNotes"));
        notesTable.setItems(notesList);

    }
    
    @FXML
    public void userDidChangeSelection() {
        //Set person user changed to
        ContactPerson selectedPerson = listMenu.getSelectionModel().getSelectedItem();
        //Get person id and convert to int
        lblPersonId.setText(String.valueOf(selectedPerson.getId()));
        //Change name, phone, email and birthday details
        txtPersonName.setText(selectedPerson.getName());
        txtEmail.setText(selectedPerson.getEmail());
        txtPhoneNumber.setText(String.valueOf(selectedPerson.getPhoneNumber()));
        dpBirthday.setValue(LocalDate.parse(selectedPerson.getFullBirthday()));
        //Set optional show year
        cbShowYear.setSelected(selectedPerson.getShowYear());
        //Set Personal and Business Importance
        cbPersonal.setSelected(selectedPerson.getPersonalImportance());
        cbBusiness.setSelected(selectedPerson.getBusinessImportance());
        originalName = txtPersonName.getText();
        
    }
    @FXML
    private void userDidSavePerson() throws IOException {
        //Check for blank entries
        if (txtPersonName.getText().trim().isEmpty()
                || txtPhoneNumber.getText().isEmpty()){
            createErrorMessage();
        } else {
            //Set temporary name
            //String originalName = txtPersonName.getText();
            //Convert name, email and phone inputs into variables
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
            
            ContactPerson selectedPerson = listMenu.getSelectionModel().getSelectedItem();
            personId = selectedPerson.getId();

            //Create new contact and insert to ArrayList in DataHelper
            ContactPerson editedPerson = new ContactPerson(personId, name, email, phoneNumber, birthday, showYear, personalImportance, businessImportance, fullBirthday);

            //Edit selected person back on person id
            for (int i = 0; i < DataHelper.person.size(); i++) {
//                System.out.println(i + 1 + " is i + 1");
//                System.out.println(personId + " is personId");
//                System.out.println((i + 1) - personId == 0);
                if ((i + 1) - personId == 0) {
                    DataHelper.person.set((i), editedPerson);
                    personId = (i + 1);
                    break;
                } else {
                    //Do nothing or error
                }
            }
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

    //Check for errors
    @FXML
    private void createErrorMessage() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("New contact cannot be created as there is a blank field");
        errorAlert.setContentText("Please enter a name or phone number if the field is blank.");
        errorAlert.showAndWait();
    }
    
    @FXML
    private void switchToMain() throws IOException {
        App.setRoot("primary");
    }

}
