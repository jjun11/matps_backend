package com.kh.matps.vo;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
public class MemberVO {
    private String my_id;
    private String my_pw;
    private String my_email;
    private String my_gender;
    private String my_name;
    private String my_nickname;
    private String my_profile_img;

    public String getMy_id() {
        return my_id;
    }

    public void setMy_id(String my_id) {
        this.my_id = my_id;
    }

    public String getMy_pw() {
        return my_pw;
    }

    public void setMy_pw(String my_pw) {
        this.my_pw = my_pw;
    }

    public String getMy_email() {
        return my_email;
    }

    public void setMy_email(String my_email) {
        this.my_email = my_email;
    }

    public String getMy_gender() {
        return my_gender;
    }

    public void setMy_gender(String my_gender) {
        this.my_gender = my_gender;
    }

    public String getMy_name() {
        return my_name;
    }

    public void setMy_name(String my_name) {
        this.my_name = my_name;
    }

    public String getMy_nickname() {
        return my_nickname;
    }

    public void setMy_nickname(String my_nickname) {
        this.my_nickname = my_nickname;
    }

    public String getMy_profile_img() {
        return my_profile_img;
    }

    public void setMy_profile_img(String my_profile_img) {
        this.my_profile_img = my_profile_img;
    }
}