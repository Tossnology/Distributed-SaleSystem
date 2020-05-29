package com.software.domain;

public class SaleorderItem 
{

	private String tablename;
    /**
     * 副ID，记录物品 viceID
     */
    private Integer viceid;

    /**
     * 记录订单ID ID
     */
    private Integer id;

    /**
     * 物品ID itemID
     */
    private Integer itemid;

    /**
     *  itemName
     */
    private String itemname;

    /**
     * 物品数量 itemNum
     */
    private Integer itemnum;

    /**
     * 物品每一个价格 perPrice
     */
    private Float perprice;

    /**
     * 价格类型 sumPrice
     */
    private Float sumprice;

    /**
     * 价格类型 priceType
     */
    private String pricetype;

    
    public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public Integer getViceid() {
        return viceid;
    }

    public void setViceid(Integer viceid) {
        this.viceid = viceid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname == null ? null : itemname.trim();
    }

    public Integer getItemnum() {
        return itemnum;
    }

    public void setItemnum(Integer itemnum) {
        this.itemnum = itemnum;
    }

    public Float getPerprice() {
        return perprice;
    }

    public void setPerprice(Float perprice) {
        this.perprice = perprice;
    }

    public Float getSumprice() {
        return sumprice;
    }

    public void setSumprice(Float sumprice) {
        this.sumprice = sumprice;
    }

    public String getPricetype() {
        return pricetype;
    }

    public void setPricetype(String pricetype) {
        this.pricetype = pricetype == null ? null : pricetype.trim();
    }
}