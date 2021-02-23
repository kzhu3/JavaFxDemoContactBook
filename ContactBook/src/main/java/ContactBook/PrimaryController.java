package ContactBook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private ListView<ContactPerson> myMasterSideMenu;
    
    @FXML
    private Label lblPersonName;
    
    @FXML
    private TextField txtEmailAddress;
    
    @FXML
    public void initialize() {
        System.out.println("initialize() success yay");
        
        List<ContactPerson> myContacts = new ArrayList<ContactPerson>();
        myContacts.add(new ContactPerson("Blair Wang", "blair.wang@unsw.edu.au", "0123"));
        myContacts.add(new ContactPerson("George Washington", "example@example.com", "14568"));
        myContacts.add(new ContactPerson("Winston Churchhill", "churchhill@example.com", "875645846"));
        
        for (ContactPerson thisPerson : myContacts) {
            myMasterSideMenu.getItems().add(thisPerson);
        }
    }
    
    @FXML
    public void userDidChangeSelection() {
        ContactPerson selectedPerson = myMasterSideMenu.getSelectionModel().getSelectedItem();
        lblPersonName.setText(selectedPerson.getName());
        txtEmailAddress.setText(selectedPerson.getEmailAddress());
    }
}
