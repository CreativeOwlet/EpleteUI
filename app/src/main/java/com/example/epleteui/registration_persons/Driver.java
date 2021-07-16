package com.example.epleteui.registration_persons;

import java.util.Date;

public class Driver {
    private String mobileNumber,email,fName,mName,lName,address,operator_id,licenseNo,nbiNo,gender,status_id,username,selfie,validId;

    private Date birthdate;

    public String getSelfie() {
        return selfie;
    }

    public void setSelfie(String selfie) {
        this.selfie = selfie;
    }

    public String getValidId() {
        return validId;
    }

    public void setValidId(String validId) {
        this.validId = validId;
    }

    public Driver(String email, String fName, String mName, String lName, String address, String operator_id, String licenseNo, String nbiNo, String gender, String username, String mobileNumber, Date birthdate, String selfie, String validId) {
        this.email = email;
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.address = address;
        this.operator_id = operator_id;
        this.licenseNo = licenseNo;
        this.nbiNo = nbiNo;
        this.gender = gender;
        this.status_id = "2";
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.birthdate = birthdate;
        this.selfie = selfie;
        this.validId = validId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getNbiNo() {
        return nbiNo;
    }

    public void setNbiNo(String nbiNo) {
        this.nbiNo = nbiNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
