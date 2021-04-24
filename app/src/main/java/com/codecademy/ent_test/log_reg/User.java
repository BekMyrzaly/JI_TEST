package com.codecademy.ent_test.log_reg;

public class User {
    String fullname;
    String nickname;
    String phone_num;
    String email;


    public  User(){}

    public User(String fullname, String nickname, String phone_num, String email){
        this.fullname = fullname;
        this.nickname = nickname;
        this.phone_num = phone_num;
        this.email = email;
    }

    public  String getFullname(){
        return fullname;
    }

    public void setFullname(String fullname){
        this.fullname = fullname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
