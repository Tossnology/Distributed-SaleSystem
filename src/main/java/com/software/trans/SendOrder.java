package com.software.trans;

import java.util.List;

import com.software.domain.SaleorderCommon;
import com.software.domain.SaleorderItem;


public class SendOrder 
{
 
    private Integer id;
    private Integer warehourseid;
    private String warehoursename;
    private Integer clientid;
    private String clientname;
    private String principalid;
    private String principalname;
    private String createtime;
    private String checktime;
    private String gathertime;
    private String returntime;
    private String postime;
    private Integer status;
    private Float sumprice;
    private Float gather;
    private Float change;
    private Float margin;
    private String type;
    private String note;
    private String exception;
    private List<SaleorderItem> items;
    
    
    public List<SaleorderItem> getItems() 
    {
		return items;
	}

	public void setItems(List<SaleorderItem> items) 
	{
		this.items = items;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWarehourseid() {
        return warehourseid;
    }

    public void setWarehourseid(Integer warehourseid) {
        this.warehourseid = warehourseid;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public String getPrincipalid() {
        return principalid;
    }

    public void setPrincipalid(String principalid) {
        this.principalid = principalid == null ? null : principalid.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime == null ? null : checktime.trim();
    }

    public String getGathertime() {
        return gathertime;
    }

    public void setGathertime(String gathertime) {
        this.gathertime = gathertime == null ? null : gathertime.trim();
    }

    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime == null ? null : returntime.trim();
    }

    public String getPostime() {
        return postime;
    }

    public void setPostime(String postime) {
        this.postime = postime == null ? null : postime.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Float getSumprice() {
        return sumprice;
    }

    public void setSumprice(Float sumprice) {
        this.sumprice = sumprice;
    }

    public Float getGather() {
        return gather;
    }

    public void setGather(Float gather) {
        this.gather = gather;
    }

    public Float getChange() {
        return change;
    }

    public void setChange(Float change) {
        this.change = change;
    }

    public Float getMargin() {
        return margin;
    }

    public void setMargin(Float margin) {
        this.margin = margin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception == null ? null : exception.trim();
    }
    
    public String getWarehoursename() {
		return warehoursename;
	}

	public void setWarehoursename(String warehoursename) {
		this.warehoursename = warehoursename;
	}


	public String getClientname() {
		return clientname;

	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getPrincipalname() {
		return principalname;
	}

	public void setPrincipalname(String principalname) {
		this.principalname = principalname;
	}

	public void initByCommon(SaleorderCommon common)
    {
    	this.id = common.getId();
    	this.warehourseid = common.getWarehourseid();
    	this.warehoursename = common.getWarehoursename();
    	this.clientid = common.getClientid();
    	this.clientname = common.getClientname();
    	this.principalid = common.getPrincipalid();
    	this.principalname = common.getPrincipalname();
    	this.createtime = common.getCreatetime();
    	this.checktime = common.getChecktime();
    	this.gathertime = common.getGathertime();
    	this.returntime = common.getReturntime();
    	this.postime = common.getPostime();
    	this.status = common.getStatus();
    	this.sumprice = common.getSumprice();
    	this.gather = common.getGather();
    	this.change = common.getChange();
    	this.margin = common.getMargin();
    	this.type = common.getType();
    	this.note = common.getNote();
    	this.exception = common.getException();
    }

	@Override
	public String toString() {
		return "SendOrder [id=" + id + ", warehourseid=" + warehourseid + ", warehoursename=" + warehoursename
				+ ", clientid=" + clientid + ", clientname=" + clientname + ", principalid=" + principalid
				+ ", principalname=" + principalname + ", createtime=" + createtime + ", checktime=" + checktime
				+ ", gathertime=" + gathertime + ", returntime=" + returntime + ", postime=" + postime + ", status="
				+ status + ", sumprice=" + sumprice + ", gather=" + gather + ", change=" + change + ", margin=" + margin
				+ ", type=" + type + ", note=" + note + ", exception=" + exception + ", items=" + items + "]";
	}
	
	
}
