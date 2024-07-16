package com.example.project58.register;

public class DRecords {
    String name,dob,sex,group,contact,centre,prefdate,address,email,illness,medication;

    public DRecords() {
    }

    public DRecords(String name, String dob, String sex, String group, String contact, String centre, String date, String address, String email, String illness,String medication) {
        this.name = name;
        this.dob = dob;
        this.sex = sex;
        this.group = group;
        this.contact = contact;
        this.centre = centre;
        this.prefdate = date;
        this.address = address;
        this.email = email;
        this.illness = illness;
        this.medication = medication;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getContact() {return contact;  }

    public void setContact(String contact) { this.contact = contact; }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public String getDate() {
        return prefdate;
    }

    public void setDate(String date) {
        this.prefdate = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIllness() { return illness;    }

    public void setIllness(String illness) { this.illness = illness;    }

    public String getMedication() { return medication;  }

    public void setMedication(String medication) { this.medication = medication; }
}
