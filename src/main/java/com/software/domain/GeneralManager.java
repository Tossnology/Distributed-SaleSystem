package com.software.domain;

public class GeneralManager {
    /**
     * 总经理ID ID
     */
    private String id;

    /**
     *  password
     */
    private String password;

    /**
     * 姓名 name
     */
    private String name;

    /**
     * 性别 gender
     */
    private String gender;

    /**
     * 总经理phone phone
     */
    private String phone;

    /**
     * 总经理Email email
     */
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}