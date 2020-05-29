package com.software.domain;

public class VIPLevel 
{
    private Integer id;

    private String vipname;

    private Float pointtoprice;

    private Float pricetopoint;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVipname() {
        return vipname;
    }

    public void setVipname(String vipname) {
        this.vipname = vipname == null ? null : vipname.trim();
    }

    public Float getPointtoprice() {
        return pointtoprice;
    }

    public void setPointtoprice(Float pointtoprice) {
        this.pointtoprice = pointtoprice;
    }

    public Float getPricetopoint() {
        return pricetopoint;
    }

    public void setPricetopoint(Float pricetopoint) {
        this.pricetopoint = pricetopoint;
    }

	@Override
	public String toString() {
		return "VIPLevel [id=" + id + ", vipname=" + vipname + ", pointtoprice=" + pointtoprice + ", pricetopoint="
				+ pricetopoint + "]";
	}
    
}