package com.software.domain;

public class WarehourseOrderCommon {
    /**
     * 查看表ID ID
     */
    private Integer id;

    /**
     * 货源地ID sourceID
     */
    private Integer sourceid;

    /**
     *  sourceName
     */
    private String sourcename;

    /**
     * 目的地ID targetID
     */
    private Integer targetid;

    /**
     *  targetName
     */
    private String targetname;

    /**
     * 发起人ID principalID
     */
    private String principalid;

    /**
     * 接受人ID principalName
     */
    private String principalname;

    /**
     * 货品货源地类型 type
     */
    private String type;

    /**
     * 总价 sumPrice
     */
    private Float sumprice;

    /**
     * 创建时间 createTime
     */
    private String createtime;

    /**
     * 审核时间 checkTime
     */
    private String checktime;

    /**
     * 记录销售单状态 status
     */
    private String status;

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
        this.sourcename = sourcename == null ? null : sourcename.trim();
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
        this.targetname = targetname == null ? null : targetname.trim();
    }

    public String getPrincipalid() {
        return principalid;
    }

    public void setPrincipalid(String principalid) {
        this.principalid = principalid == null ? null : principalid.trim();
    }

    public String getPrincipalname() {
        return principalname;
    }

    public void setPrincipalname(String principalname) {
        this.principalname = principalname == null ? null : principalname.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime == null ? null : checktime.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	@Override
	public String toString() {
		return "WarehourseOrderCommon [id=" + id + ", sourceid=" + sourceid + ", sourcename=" + sourcename
				+ ", targetid=" + targetid + ", targetname=" + targetname + ", principalid=" + principalid
				+ ", principalname=" + principalname + ", type=" + type + ", sumprice=" + sumprice + ", createtime="
				+ createtime + ", checktime=" + checktime + ", status=" + status + "]";
	}
    
}