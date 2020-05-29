package com.software.trans;

import java.util.List;

import com.software.domain.SaleorderCommon;
import com.software.domain.WarehourseOrderCommon;
import com.software.domain.WarehourseOrderItem;

public class SendWarehourseOrder 
{
    private Integer id;
    private Integer sourceid;
    private String sourcename;
    private Integer targetid;
    private String targetname;
    private String principalid;
    private String principalname;
    private String type;
    private Float sumprice;
    private String createtime;
    private String checktime;
    private String status;
    
    private List<WarehourseOrderItem> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSourceid() {
		return sourceid;
	}

	public void setSourceid(Integer sourceid) {
		this.sourceid = sourceid;
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public Integer getTargetid() {
		return targetid;
	}

	public void setTargetid(Integer targetid) {
		this.targetid = targetid;
	}

	public String getTargetname() {
		return targetname;
	}

	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}

	public String getPrincipalid() {
		return principalid;
	}

	public void setPrincipalid(String principalid) {
		this.principalid = principalid;
	}

	public String getPrincipalname() {
		return principalname;
	}

	public void setPrincipalname(String principalname) {
		this.principalname = principalname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getSumprice() {
		return sumprice;
	}

	public void setSumprice(Float sumprice) {
		this.sumprice = sumprice;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getChecktime() {
		return checktime;
	}

	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<WarehourseOrderItem> getItems() {
		return items;
	}

	public void setItems(List<WarehourseOrderItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "SendWarehourseOrder [id=" + id + ", sourceid=" + sourceid + ", sourcename=" + sourcename + ", targetid="
				+ targetid + ", targetname=" + targetname + ", principalid=" + principalid + ", principalname="
				+ principalname + ", type=" + type + ", sumprice=" + sumprice + ", createtime=" + createtime
				+ ", checktime=" + checktime + ", status=" + status + ", items=" + items + "]";
	}

	
	public void initByCommon(WarehourseOrderCommon common){
		this.id = common.getId();
		this.sourceid = common.getSourceid();
		this.sourcename = common.getSourcename();
		this.targetid = common.getTargetid();
		this.targetname = common.getTargetname();
		this.principalid = common.getPrincipalid();
		this.principalname = common.getPrincipalname();
		this.type = common.getType();
		this.sumprice = common.getSumprice();
		this.createtime = common.getCreatetime();
		this.checktime = common.getChecktime();
		this.status = common.getStatus();
	}
    
}
