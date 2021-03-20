package PersonBook;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {
    @FXML
    private ListView<ContactPerson> listMenu;
    
    @FXML
    private Label lblPersonName;
    
    @FXML
    private Label txtEmail;
    
    @FXML
    private Label txtPhoneNumber;
    
    @FXML
    private Label txtBirthday;
    
    @FXML
    private CheckBox cbPersonal;
    
    @FXML
    private CheckBox cbBusiness;
    
    @FXML
    private Button btnAddPerson;
    
    @FXML
    private Button btnEditPerson;
    
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
    
    @FXML
    public void initialize() throws SQLException {
        if (DataHelper.person.size() > 0) {
            //There are already people in the contact list
            for (ContactPerson thisPerson : DataHelper.person) {
                listMenu.getItems().add(thisPerson);
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
        //Get person id
        lblPersonId.setText(String.valueOf(selectedPerson.getId()));
        //Change name, phone, email and birthday details
        lblPersonName.setText(selectedPerson.getName());
        txtEmail.setText(selectedPerson.getEmail());
        txtPhoneNumber.setText(String.valueOf(selectedPerson.getPhoneNumber()));
        txtBirthday.setText(selectedPerson.getBirthday());
        //Set Personal and Business Importance
        cbPersonal.setSelected(selectedPerson.getPersonalImportance());
        cbBusiness.setSelected(selectedPerson.getBusinessImportance());
    }
    
    //Creating a method to make checkboxes read-only in the home page
    @FXML
    public void makeReadOnly() {
        ContactPerson selectedPerson = listMenu.getSelectionModel().getSelectedItem();
        cbPersonal.setSelected(selectedPerson.getPersonalImportance());
        cbBusiness.setSelected(selectedPerson.getBusinessImportance());
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
        System.out.println("Switching to Add Contact");
    }
    
    @FXML
    private void switchToEdit() throws IOException {
        App.setRoot("edit");
        System.out.println("Switching to Edit Contact");
    }
}
