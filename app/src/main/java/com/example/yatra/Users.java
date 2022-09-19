package com.example.yatra;

public class Users {
    String profilepic ,userName,mail,password;

    public Users(String profilepic, String userName, String mail, String password) {
        this.profilepic = profilepic;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
    }
    public Users(){

    }

    //signUp constructor
    public Users( String userName, String mail, String password) {

        this.userName = userName;
        this.mail = mail;
        this.password = password;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
