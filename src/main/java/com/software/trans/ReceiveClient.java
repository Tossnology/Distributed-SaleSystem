package com.software.trans;

public class ReceiveClient 
{
	private String id;
	private String name;
	private String gender;
	private String phone;
	private String email;
	private String note;
	private String type;
	private String label;
	private String authority;
    private String remain;
    private String debt;
    private String point;
    private String pricetopoint;
    private String pointtoprice;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getRemain() {
		return remain;
	}
	public void setRemain(String remain) {
		this.remain = remain;
	}
	public String getDebt() {
		return debt;
	}
	public void setDebt(String debt) {
		this.debt = debt;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getPricetopoint() {
		return pricetopoint;
	}
	public void setPricetopoint(String pricetopoint) {
		this.pricetopoint = pricetopoint;
	}
	public String getPointtoprice() {
		return pointtoprice;
	}
	public void setPointtoprice(String pointtoprice) {
		this.pointtoprice = pointtoprice;
	}
	@Override
	public String toString() {
		return "ReceiveClient [id=" + id + ", name=" + name + ", gender=" + gender + ", phone=" + phone + ", email="
				+ email + ", note=" + note + ", type=" + type + ", label=" + label + ", authority=" + authority
				+ ", remain=" + remain + ", debt=" + debt + ", point=" + point + ", pricetopoint=" + pricetopoint
				+ ", pointtoprice=" + pointtoprice + "]";
	}
	
}
