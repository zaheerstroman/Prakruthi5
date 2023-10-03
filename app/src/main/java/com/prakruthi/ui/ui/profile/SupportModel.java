package com.prakruthi.ui.ui.profile;

public class SupportModel {

//    private int id;

//    private String mobile;
private String support_mobile;

//    private String email;
private String support_email;

//    private String address;
private String support_address;

    public SupportModel( String support_mobile,String support_email,String support_address) {

//    public SupportModel(int id, String support_mobile,String support_email,String support_address) {
//        this.id = id;
        this.support_mobile = support_mobile;
        this.support_email = support_email;
        this.support_address = support_address;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }


    public String getSupport_mobile() {
        return support_mobile;
    }

    public void setSupport_mobile(String support_mobile) {
        this.support_mobile = support_mobile;
    }

    public String getSupport_email() {
        return support_email;
    }

    public void setSupport_email(String support_email) {
        this.support_email = support_email;
    }

    public String getSupport_address() {
        return support_address;
    }

    public void setSupport_address(String support_address) {
        this.support_address = support_address;
    }
}
