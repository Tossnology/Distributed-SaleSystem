package com.software.domain;

import java.io.Serializable;

public class WarehourseDetail implements Serializable
{
	private String tablename;
    /**
     * 物品ID itemID
     */
    private Integer id;
    private Integer itemid;
    private Integer warehourseid;
    /**
     * 物品数量 itemNum
     */
    private Integer itemnum;

    /**
     *  time
     */
    private String time;

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

    public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Integer getItemnum() {
        return itemnum;
    }

    public void setItemnum(Integer itemnum) {
        this.itemnum = itemnum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public void fillTableName() 
    {
    	if (this.tablename.equals("-1")) 
    	{
    		this.tablename = "base_warehourse_detail";
		}
    	else
    	{
    		this.tablename = "sub_warehourse_detail";
    	}
	}

    @Override
    public String toString() {
        return "WarehourseDetail{" +
                "tablename='" + tablename + '\'' +
                ", id=" + id +
                ", itemid=" + itemid +
                ", warehourseid=" + warehourseid +
                ", itemnum=" + itemnum +
                ", time='" + time + '\'' +
                '}';
    }
}