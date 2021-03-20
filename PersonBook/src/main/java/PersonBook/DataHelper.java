/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonBook;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author zhusk
 */
public class DataHelper {
    int size = 5;
    public static ArrayList<ArrayList<String>> notes1 = new ArrayList<ArrayList<String>>(5);
    
    public static void preloadNotes() {
        for (int i = 0; i < 5; i++) {
            notes1.add(new ArrayList());
        }
//        notes1.get(0).add("Hello");
//        notes1.get(1).add("World");
//        notes1.get(1).add("It's Kev");
        
        for (int i = 0; i < 5; i++) {
            int countSize = notes1.get(i).size();
            for (int j = 0; j < countSize; j++) {
                String start = notes1.get(i).get(j);
                System.out.println(start + i + j);
            }
        }
    }
    
    public static ObservableList<CaseNotes> getNotes() throws SQLException {
        ObservableList<CaseNotes> notesList = FXCollections.observableArrayList(
            new CaseNotes (1, "01/03/2021", "We met up today and got coffee in the city and watched the birds"),
            new CaseNotes (2, "02/11/2020", "We went skydiving"),
            new CaseNotes (3, "23/06/2019", "We watched a movie")
        );

        return notesList;
        }
    
    
    
    public static ArrayList<ContactPerson> person = new ArrayList<>();
    
    public static void preloadData() {
        ContactPerson kevin = new ContactPerson(1, "Kevin Zhu", "kzhu@gmail.com", "0435626333", "03 October", false, true, true, "1998-10-03");
        DataHelper.person.add(kevin);
        DataHelper.person.add(new ContactPerson(2, "Fred Wang", "fred.wang@gmail.com", "0431123245", "5 November",false,  false, true, "1998-11-05"));
        DataHelper.person.add(new ContactPerson(3, "Michael Zhu", "Michael.zhu@gmail.com","0431128565", "29 September 1990", true, true, false, "1990-09-29"));
    }
}
