package com.software.trans;


import com.software.domain.Item;
import com.software.domain.WarehourseDetail;

public class Stock 
{
    private String hourseid;
	private String tablename;
    private String itemid;
    private String itemname;
    private String specification;
    private String picture;
    private String type;
    private String label;
    private String itemnum;
    private String afterstock;
    private String time;
    private String overstock;
    
	public String getOverstock() {
		return overstock;
	}
	public void setOverstock(String overstock) {
		this.overstock = overstock;
	}
	public String getHourseid() {
		return hourseid;
	}
	public void setHourseid(String hourseid) {
		this.hourseid = hourseid;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getItemnum() {
		return itemnum;
	}
	public void setItemnum(String itemnum) {
		this.itemnum = itemnum;
	}
	public String getAfterstock() {
		return afterstock;
	}
	public void setAfterstock(String afterstock) {
		this.afterstock = afterstock;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
    
    public void fillTablename()
    {
    	if (hourseid.equals("-1")) 
    	{
    		this.tablename = "base_warehourse_detail";
		}
    	else
    	{
    		this.tablename = "sub_warehourse_detail_"+String.format("%04d", Integer.valueOf(this.hourseid));
		}
    }
    
    public void initByItem(Item item)
    {
    	this.itemid = item.getId()+"";
    	this.itemname = item.getName();
    	this.specification = item.getSpecification();
    	this.picture = item.getPicture();
    	this.type = item.getType();
    	this.label = item.getLabel();
    }
    
    public void initByDetail(WarehourseDetail detail)
    {
    	this.itemnum = detail.getItemnum()+"";
    	this.afterstock = "";
    	this.time = detail.getTime();
    }
    
    public Item toItem()
    {
    	Item item = new Item();
    	if(!this.itemid.equals("")){
    		item.setId(Integer.valueOf(this.itemid));
    	}
    	item.setName(this.itemname);
    	item.setSpecification(this.specification);
    	item.setPicture(this.picture);
    	item.setType(this.type);
    	item.setLabel(this.label);
    	return item;
    }
	
    public WarehourseDetail toDetail()
    {
    	WarehourseDetail detail = new WarehourseDetail();
    	detail.setTablename(this.tablename);
    	if(!this.itemid.equals("")){
    		detail.setItemid(Integer.valueOf(this.itemid));
    	}
    	detail.setTime(this.time);
    	if (isLegal(this.afterstock)) 
    	{
    		detail.setItemnum(Integer.valueOf(this.afterstock));
		}
    	else
    	{
    		detail.setItemnum(Integer.valueOf(this.itemnum));
		}
    	return detail;
    }
	
    private boolean isLegal(String num)
    {
    	if (num==null) 
    	{
    		return false;
		}
    	try 
    	{
    		Integer temp = Integer.valueOf(num);
    		if (temp < 0) 
    		{
    			return false;
			}
    		else
    		{
    			return true;
			}
		} 
    	catch (Exception e) 
    	{
    		return false;
		}
    }
}
