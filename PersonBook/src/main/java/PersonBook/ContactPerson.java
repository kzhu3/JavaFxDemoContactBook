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
public class ContactPerson {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String birthday;
    private Boolean showYear;
    private Boolean personalImportance;
    private Boolean businessImportance;
    private String fullBirthday;
    //private ArrayList<String> caseNotes;

    public ContactPerson(int id, String name, String email, String phoneNumber, String birthday, Boolean showYear, Boolean personalImportance, Boolean businessImportance, String fullBirthday/*, ArrayList<String> caseNotes*/) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.showYear = showYear;
        this.personalImportance = personalImportance;
        this.businessImportance = businessImportance;
        this.fullBirthday = fullBirthday;
        //this.caseNotes = caseNotes;
    }
    
    public ContactPerson(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public ContactPerson(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Boolean getShowYear() {
        return showYear;
    }

    public void setShowYear(Boolean showYear) {
        this.showYear = showYear;
    }
    
    /**
     * @return the personalImportance
     */
    public Boolean getPersonalImportance() {
        return personalImportance;
    }

    /**
     * @param personalImportance the personalImportance to set
     */
    public void setPersonalImportance(Boolean personalImportance) {
        this.personalImportance = personalImportance;
    }

    /**
     * @return the businessImportance
     */
    public Boolean getBusinessImportance() {
        return businessImportance;
    }

    /**
     * @param businessImportance the businessImportance to set
     */
    public void setBusinessImportance(Boolean businessImportance) {
        this.businessImportance = businessImportance;
    }

//    public ArrayList<String> getCaseNotes() {
//        return caseNotes;
//    }
//
//    public void setCaseNotes(ArrayList<String> caseNotes) {
//        this.caseNotes = caseNotes;
//    }

    public String getFullBirthday() {
        return fullBirthday;
    }

    public void setFullBirthday(String fullBirthday) {
        this.fullBirthday = fullBirthday;
    }
    
    
    
    @Override
    public String toString() {
        return this.getName();
    }

}
