package com.example.project58.register;

public class Feed {
    String email1;
    String suggestion;

    public Feed()
    {
    }

    public Feed(String email, String suggestion) {
        this.email1 = email;
        this.suggestion = suggestion;
    }

    public String getEmail() {
        return email1;
    }

    public void setEmail1(String email) {
        this.email1 = email;
    }

    public String getSuggestion(){return suggestion;}

    public void setSuggestion(String suggestion){this.suggestion=suggestion;}
}
