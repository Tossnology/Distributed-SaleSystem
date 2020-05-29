package com.software.domain;

public class WarehourseDetail 
{
	private String tablename;
    /**
     * 物品ID itemID
     */
    private Integer itemid;

    /**
     * 物品数量 itemNum
     */
    private Integer itemnum;

    /**
     *  time
     */
    private String time;

    
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
    		this.tablename = "sub_warehourse_detail_"+String.format("%04d", Integer.valueOf(this.tablename));
    	}
	}
	@Override
	public String toString() {
		return "WarehourseDetail [tablename=" + tablename + ", itemid=" + itemid + ", itemnum=" + itemnum + ", time="
				+ time + "]";
	}
}