package com.software.trans;

public class WarehoursePerformance 
{
	private String warehourseid;
	private String warehoursename;
	private String principalid;
	private String principalname;
	private String performancedetail;
	private String clientnum;
	private String orderNum;
	private String sumPrice;
	private String starttime;
	private String endtime;
	
	public String getWarehourseid() 
	{
		return warehourseid;
	}
	public void setWarehourseid(String warehourseid) 
	{
		this.warehourseid = warehourseid;
	}
	public String getWarehoursename() 
	{
		return warehoursename;
	}
	public void setWarehoursename(String warehoursename) 
	{
		this.warehoursename = warehoursename;
	}
	public String getPrincipalid() 
	{
		return principalid;
	}
	public void setPrincipalid(String principalid) 
	{
		this.principalid = principalid;
	}
	public String getPrincipalname() 
	{
		return principalname;
	}
	public void setPrincipalname(String principalname) {
		this.principalname = principalname;
	}
	public String getPerformancedetail() 
	{
		return performancedetail;
	}
	public void setPerformancedetail(String performancedetail) 
	{
		this.performancedetail = performancedetail;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	public String getClientnum() {
		return clientnum;
	}
	public void setClientnum(String clientnum) {
		this.clientnum = clientnum;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}
	@Override
	public String toString() {
		return "WarehoursePerformance [warehourseid=" + warehourseid + ", warehoursename=" + warehoursename
				+ ", principalid=" + principalid + ", principalname=" + principalname + ", performancedetail="
				+ performancedetail + ", clientnum=" + clientnum + ", orderNum=" + orderNum + ", sumPrice=" + sumPrice
				+ ", starttime=" + starttime + ", endtime=" + endtime + "]";
	}
}
