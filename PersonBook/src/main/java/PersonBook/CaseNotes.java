/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonBook;

import java.util.ArrayList;

/**
 *
 * @author zhusk
 */
public class CaseNotes {
    private int id;
    private String dateNotes;
    private String caseNotes;

    public CaseNotes(int id, String dateNotes, String caseNotes) {
        this.id = id;
        this.dateNotes = dateNotes;
        this.caseNotes = caseNotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateNotes() {
        return dateNotes;
    }

    public void setDateNotes(String dateNotes) {
        this.dateNotes = dateNotes;
    }
    
    public String getCaseNotes() {
        return caseNotes;
    }

    public void setCaseNotes(String caseNotes) {
        this.caseNotes = caseNotes;
    }
    
    
}
