package com.software.domain;

public class Staff 
{
	private String tablename;

    private String id;
    private String password;
    private Integer hourseid;
    private String hoursename;
    private String name;
    private String gender;
    private String phone;
    private String email;
    private String label;

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

    public Integer getHourseid() {
        return hourseid;
    }

    public void setHourseid(Integer hourseid) {
        this.hourseid = hourseid;
    }

    public String getHoursename() {
        return hoursename;
    }

    public void setHoursename(String hoursename) {
        this.hoursename = hoursename == null ? null : hoursename.trim();
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
    
	public void filltablename()
	{
		this.tablename = "sub_staff_"+String.format("%04d", this.hourseid);
	}

	@Override
	public String toString() {
		return "Staff [tablename=" + tablename + ", id=" + id + ", password=" + password + ", hourseid=" + hourseid
				+ ", hoursename=" + hoursename + ", name=" + name + ", gender=" + gender + ", phone=" + phone
				+ ", email=" + email + ", label=" + label + "]";
	}
	
}