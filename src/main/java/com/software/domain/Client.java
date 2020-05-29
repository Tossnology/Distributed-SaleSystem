package com.software.domain;

public class Client 
{
    private Integer id;

    private String name;

    private String gender;

    private String phone;

    private String email;

    private String note;

    private String type;

    private String label;

    private String authority;

    private Float remain;

    private Float debt;

    private Float point;

    private Float pricetopoint;

    private Float pointtoprice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority == null ? null : authority.trim();
    }

    public Float getRemain() {
        return remain;
    }

    public void setRemain(Float remain) {
        this.remain = remain;
    }

    public Float getDebt() {
        return debt;
    }

    public void setDebt(Float debt) {
        this.debt = debt;
    }

    public Float getPoint() {
        return point;
    }

    public void setPoint(Float point) {
        this.point = point;
    }

    public Float getPricetopoint() {
        return pricetopoint;
    }

    public void setPricetopoint(Float pricetopoint) {
        this.pricetopoint = pricetopoint;
    }

    public Float getPointtoprice() {
        return pointtoprice;
    }

    public void setPointtoprice(Float pointtoprice) {
        this.pointtoprice = pointtoprice;
    }

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", gender=" + gender + ", phone=" + phone + ", email=" + email
				+ ", note=" + note + ", type=" + type + ", label=" + label + ", authority=" + authority + ", remain="
				+ remain + ", debt=" + debt + ", point=" + point + ", pricetopoint=" + pricetopoint + ", pointtoprice="
				+ pointtoprice + "]";
	}
    
    
}