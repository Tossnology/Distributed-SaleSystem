package com.software.domain;

public class SubBranchDetailMap {
    /**
     *  ID
     */
    private Integer id;

    /**
     * 子仓库ID warehourseID
     */
    private Integer warehourseid;

    /**
     *  warehourseName
     */
    private String warehoursename;

    /**
     *  warehourseLocation
     */
    private String warehourselocation;

    /**
     * 子仓库负责人ID principalID
     */
    private String principalid;

    /**
     *  principalName
     */
    private String principalname;

   
    /**
     * 子仓库销售表的名称 saleOrderItemTable
     */
    private String saleorderitemtable;

    /**
     *  saleOrderCommonTable
     */
    private String saleordercommontable;

    /**
     * 子仓库员工表的名称 staffTable
     */
    private String stafftable;

    /**
     * 子仓库的表的名称 warehourseDetailTable
     */
    private String warehoursedetailtable;
    
    /**
     * 子仓库商品表的名称 itemTable
     */
    private String itemtable;


    /**
     * 更新时间 time
     */
    private String time;

    /**
     * 标志是否有效 label
     */
    private String label;

    private String flag;
    
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

    public String getWarehoursename() {
        return warehoursename;
    }

    public void setWarehoursename(String warehoursename) {
        this.warehoursename = warehoursename == null ? null : warehoursename.trim();
    }

    public String getWarehourselocation() {
        return warehourselocation;
    }

    public void setWarehourselocation(String warehourselocation) {
        this.warehourselocation = warehourselocation == null ? null : warehourselocation.trim();
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

    public String getItemtable() {
        return itemtable;
    }

    public void setItemtable(String itemtable) {
        this.itemtable = itemtable == null ? null : itemtable.trim();
    }

    public String getSaleorderitemtable() {
        return saleorderitemtable;
    }

    public void setSaleorderitemtable(String saleorderitemtable) {
        this.saleorderitemtable = saleorderitemtable == null ? null : saleorderitemtable.trim();
    }

    public String getSaleordercommontable() {
        return saleordercommontable;
    }

    public void setSaleordercommontable(String saleordercommontable) {
        this.saleordercommontable = saleordercommontable == null ? null : saleordercommontable.trim();
    }

    public String getStafftable() {
        return stafftable;
    }

    public void setStafftable(String stafftable) {
        this.stafftable = stafftable == null ? null : stafftable.trim();
    }

    public String getWarehoursedetailtable() {
        return warehoursedetailtable;
    }

    public void setWarehoursedetailtable(String warehoursedetailtable) {
        this.warehoursedetailtable = warehoursedetailtable == null ? null : warehoursedetailtable.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "SubBranchDetailMap [id=" + id + ", warehourseid=" + warehourseid + ", warehoursename=" + warehoursename
				+ ", warehourselocation=" + warehourselocation + ", principalid=" + principalid + ", principalname="
				+ principalname + ", saleorderitemtable=" + saleorderitemtable + ", saleordercommontable="
				+ saleordercommontable + ", stafftable=" + stafftable + ", warehoursedetailtable="
				+ warehoursedetailtable + ", itemtable=" + itemtable + ", time=" + time + ", label=" + label + ", flag="
				+ flag + "]";
	}
	
	
}